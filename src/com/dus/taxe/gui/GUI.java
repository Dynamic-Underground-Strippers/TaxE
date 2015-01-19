package com.dus.taxe.gui;

import com.dus.taxe.Connection;
import com.dus.taxe.Game;
import com.dus.taxe.Goal;
import com.dus.taxe.Map;
import com.dus.taxe.Node;
import com.dus.taxe.Player;
import com.dus.taxe.Resource;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GUI extends JFrame {
	static final ArrayList<Node> tempRouteNodes = new ArrayList<Node>();
	private static final float X_SCALE = Screen.WIDTH / 1920f;
	private static final float Y_SCALE = Screen.HEIGHT / 1080f;
	private static final ArrayList<Connection> tempRouteConnections = new ArrayList<Connection>();
	public static GUI self;
	static Image draggingImage;
	static Rect draggingRect;
	static Resource draggingResource;
	static long frameTime = 0;
	static boolean settingRoute = false;
	private static long lastFrame = 0;
	private static Map map;
	private final ArrayList<GuiElement> guiElements = new ArrayList<GuiElement>();
	private final BufferedImage image;
	private final Image mapImage;
	private final ResourceContainer resourceContainer;
	private final BasicStroke trackStroke = new BasicStroke(8, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10, new float[]{8}, 0);
	private Font font;
	private TrainGoalElement[] trainGoalElements = new TrainGoalElement[3];

	public GUI(Map map) {
		self = this;
		GUI.map = map;
		//noinspection ConstantConditions
		mapImage = new ImageIcon(getClass().getClassLoader().getResource("map.png")).getImage();
		for (Node n : map.listOfNodes) {
			addGuiElement(new NodeElement(n));
		}
		trainGoalElements = new TrainGoalElement[]{
				new TrainGoalElement(new Rect(-490, Screen.HEIGHT - 620, 900, 150)),
				new TrainGoalElement(new Rect(-490, Screen.HEIGHT - 413, 900, 150)),
				new TrainGoalElement(new Rect(-490, Screen.HEIGHT - 207, 900, 150))};
		for (int i = 0; i < 3; i++) {
			addGuiElement(trainGoalElements[i]);
			trainGoalElements[i].setEditRouteButton();
		}
		addGuiElement(new SolidColourRect(new Rect(0, 0, 110, Screen.HEIGHT), Color.white));
		resourceContainer = new ResourceContainer(new Rect(10, Screen.HEIGHT - 650, 100, 640));
		addGuiElement(resourceContainer);
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (Node n : GUI.map.listOfNodes) {
					if (Point.distance(e.getX(), e.getY(), n.getLocation().getX() * getWidth(),
							n.getLocation().getY() * getHeight()) < 10) {
						if (tempRouteNodes.isEmpty()) {
							for (Goal goal : Game.getCurrentPlayer().getCurrentGoals()) {
								if (goal.getStart().equals(n)) {
									tempRouteNodes.add(n);
								}
							}
						} else {
							Connection c;
							if ((c = GUI.map.connections[n.getId()][n.getId()]) != null) {
								tempRouteNodes.add(n);
								tempRouteConnections.add(c);
							}
						}
					}
				}
				Collections.reverse(guiElements);
				for (GuiElement guiElement : guiElements) {
					if (guiElement.bounds.contains(e.getPoint())) {
						guiElement.onClick(e);
						break;
					}
				}
				Collections.reverse(guiElements);
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Collections.reverse(guiElements);
				for (GuiElement guiElement : guiElements) {
					if (guiElement.bounds.contains(e.getPoint())) {
						guiElement.onMouseDown(e);
						break;
					}
				}
				Collections.reverse(guiElements);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Collections.reverse(guiElements);
				for (GuiElement guiElement : guiElements) {
					if (guiElement.bounds.contains(e.getPoint())) {
						guiElement.onMouseUp(e);
						break;
					}
				}
				Collections.reverse(guiElements);
				GUI.draggingRect = null;
				GUI.draggingImage = null;
				GUI.draggingResource = null;
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (draggingRect != null && draggingImage != null) {
					draggingRect.x = e.getX() - draggingRect.width / 2f;
					draggingRect.y = e.getY() - draggingRect.height / 2f;
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				boolean found = false;
				for (GuiElement guiElement : guiElements) {
					if (guiElement.bounds.contains(e.getPoint())) {
						guiElement.mouseMovedInternal(e);
						found = true;
					} else {
						guiElement.mouseMovedExternal(e);
					}
				}
				if (!found) {
					setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		GraphicsConfiguration config = device.getDefaultConfiguration();
		image = config.createCompatibleImage(Screen.WIDTH, Screen.HEIGHT, Transparency.TRANSLUCENT);
		try {
			font = Font.createFont(Font.TRUETYPE_FONT,
					new FileInputStream(new File("src/font" + ".ttf"))).deriveFont(16f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addGuiElement(GuiElement guiElement) {
		guiElements.add(guiElement);
		repaint();
	}

	public void paint(Graphics graphics) {
		if (image == null) return;
		frameTime = System.currentTimeMillis() - lastFrame;
		lastFrame = System.currentTimeMillis();
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		int imageWidth = (int) ((float) mapImage.getWidth(this) *
				((float) getHeight() / (float) mapImage.getHeight(this)));
		g.drawImage(mapImage, getWidth() - imageWidth, 0, imageWidth, getHeight(), this);
		if (map != null) {
			g.setColor(Color.BLACK);
			g.setStroke(trackStroke);
			for (int i = 0; i < map.connections.length; i++) {
				for (int j = 0; j <= i; j++) {
					if (map.connections[i][j] != null) {
						g.drawLine((int) (map.retrieveNode(i).getLocation().getX() * Screen.WIDTH),
								(int) (map.retrieveNode(i).getLocation().getY() * Screen.HEIGHT),
								(int) (map.retrieveNode(j).getLocation().getX() * Screen.WIDTH),
								(int) (map.retrieveNode(j).getLocation().getY() * Screen.HEIGHT));
					}
				}
			}
		}
		for (GuiElement guiElement : guiElements) {
			guiElement.update();
			guiElement.draw(g);
		}
		if (draggingRect != null && draggingImage != null) {
			g.drawImage(draggingImage, (int) draggingRect.x, (int) draggingRect.y,
					(int) draggingRect.width, (int) draggingRect.height, this);
		} else {
			for (GuiElement guiElement : guiElements) {
				guiElement.drawTooltip(g);
			}
		}
		graphics.drawImage(image, 0, 0, Screen.WIDTH, Screen.HEIGHT, this);
		repaint();
	}

	public void setPlayer(Player player) {
//		resourceContainer.removeAllResources();
		for (int i = 0; i < player.getCurrentGoals().size(); i++) {
			trainGoalElements[i].setTrain(player.getCurrentTrains().get(i));
		}
//		for (Engine e : player.getEngineInventory()) {
//			resourceContainer.addResource(e);
//		}
//		for (Upgrade u : player.getUpgradeInventory()) {
//			resourceContainer.addResource(u);
//		}
	}
}
