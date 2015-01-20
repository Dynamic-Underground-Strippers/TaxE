package com.dus.taxe.gui;

import com.dus.taxe.Connection;
import com.dus.taxe.Game;
import com.dus.taxe.Goal;
import com.dus.taxe.Map;
import com.dus.taxe.Node;
import com.dus.taxe.Player;
import com.dus.taxe.Point;
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
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GUI extends JFrame {
	static final ArrayList<Node> tempRouteNodes = new ArrayList<Node>();
	static Goal tempRouteGoal;
	private static final float X_SCALE = Screen.WIDTH / 1920f;
	private static final float Y_SCALE = Screen.HEIGHT / 1080f;
	static final ArrayList<Connection> tempRouteConnections = new ArrayList<Connection>();
	public static GUI self;
	static Image draggingImage;
	static Rect draggingRect;
	static Resource draggingResource;
	static long frameTime = 0;
	static boolean settingRoute = false;
	private static long lastFrame = 0;
	static Map map;
	static TrainGoalElement tempRouteTrainGoalElement;
	static float scale;
	private final ArrayList<GuiElement> guiElements = new ArrayList<GuiElement>();
	private final BufferedImage image;
	private final Image mapImage;
	private final BasicStroke trackStroke = new BasicStroke(8, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10, new float[]{8}, 0);
	static Font baseFont;
	private final Font font;
	private TrainGoalElement[] trainGoalElements = new TrainGoalElement[3];
	static Rect reticuleRect;
	private static Image reticuleImage;
	private final Color c = new Color(0, 0, 0, 0.8f);
	private final Font bigFont;
	private final Color trainBlue = new Color(84, 198, 198);
	private final Color trainGreen = new Color(45, 242, 145);
	private final Color trainPink = new Color(230, 113, 229);

	public GUI(Map map) {
		self = this;
		scale = Screen.WIDTH / 1920f;
		GUI.map = map;
		try {
			baseFont = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getClassLoader().getResourceAsStream("font.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		font = baseFont.deriveFont(16f);
		bigFont = font.deriveFont(60f);
		//noinspection ConstantConditions
		mapImage = new ImageIcon(getClass().getClassLoader().getResource("map.png")).getImage();
		//noinspection ConstantConditions
		reticuleImage = new ImageIcon(getClass().getClassLoader().getResource("crosshair.png"))
				.getImage();
		for (Node n : map.getListOfNodes()) {
			addGuiElement(new NodeElement(n));
		}
		trainGoalElements = new TrainGoalElement[]{new TrainGoalElement(
				new Rect(-490 * scale, Screen.HEIGHT - 620 * scale, 900 * scale, 150 * scale), 0),
												   new TrainGoalElement(new Rect(-490 * scale,
														   Screen.HEIGHT - 413 * scale, 900 * scale,
														   150 * scale), 1), new TrainGoalElement(
				new Rect(-490 * scale, Screen.HEIGHT - 207 * scale, 900 * scale, 150 * scale), 2)};
		for (int i = 0; i < 3; i++) {
			addGuiElement(trainGoalElements[i]);
			trainGoalElements[i].setEditRouteButton();
		}
		addGuiElement(new SolidColourRect(new Rect(0, 0, 110 * scale, Screen.HEIGHT), Color.white));
		addGuiElement(new ResourceContainer(
				new Rect(10 * scale, Screen.HEIGHT - 650 * scale, 100 * scale, 640 * scale)));
		addGuiElement(
				new GoalsContainer(new Rect(10 * scale, 10 * scale, 900 * scale, 200 * scale)));
		addGuiElement(
				new ButtonElement(new Rect(10 * scale, 330 * scale, 0, 0), "End turn", bigFont,
						new Runnable() {
							public void run() {
								Game.endTurn();
								Game.newTurn();
							}
						}));
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
//                for (Node n : GUI.map.listOfNodes) {
//                    if (Point.distance(e.getX(), e.getY(), n.getLocation().getX() * getWidth(),
//                            n.getLocation().getY() * getHeight()) < 10) {
//                        if (tempRouteNodes.isEmpty()) {
//                            for (Goal goal : Game.getCurrentPlayer().getCurrentGoals()) {
//                                if (goal.getStart().equals(n)) {
//                                    tempRouteNodes.add(n);
//                                }
//                            }
//                        } else {
//                            Connection c;
//                            if ((c = GUI.map.connections[n.getId()][n.getId()]) != null) {
//                                tempRouteNodes.add(n);
//                                tempRouteConnections.add(c);
//                            }
//                        }
//                    }
//                }
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
				reticuleRect = null;
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
	}

	public void addGuiElement(GuiElement guiElement) {
		guiElements.add(guiElement);
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
		int imageWidth = (int) (1485 * scale);
		g.drawImage(mapImage, getWidth() - imageWidth, 0, imageWidth, getHeight(), this);
		if (map != null) {
			g.setColor(Color.BLACK);
			g.setStroke(trackStroke);
			Color colour = Color.black;
			if (settingRoute) {
				switch (tempRouteTrainGoalElement.getIndex()) {
					case 0:
						colour = trainBlue;
						break;
					case 1:
						colour = trainGreen;
						break;
					case 2:
						colour = trainPink;
						break;
				}
			}
			for (int i = 0; i < map.getConnections().length; i++) {
				for (int j = 0; j <= i; j++) {
					if (map.getConnections()[i][j] != null) {
						g.setColor((tempRouteConnections.contains(map.getConnections()[i][j]) ||
								tempRouteConnections.contains(
										map.getConnections()[j][i])) ? colour : Color.black);
						g.drawLine((int) (map.retrieveNode(i).getLocation().getX() * Screen.WIDTH),
								(int) (map.retrieveNode(i).getLocation().getY() * Screen.HEIGHT),
								(int) (map.retrieveNode(j).getLocation().getX() * Screen.WIDTH),
								(int) (map.retrieveNode(j).getLocation().getY() * Screen.HEIGHT));
						g.setColor(c);
						Point middle = Point.middle(map.retrieveNode(i).getLocation(),
								map.retrieveNode(j).getLocation());
						g.fillOval((int) (middle.getX() * Screen.WIDTH - 15 * scale),
								(int) (middle.getY() * Screen.HEIGHT - 15 * scale),
								(int) (30 * scale), (int) (30 * scale));
						g.setColor(Color.white);
						g.drawString(String.valueOf(map.getConnections()[i][j].getDistance()),
								middle.getX() * Screen.WIDTH - g.getFontMetrics().stringWidth(
										String.valueOf(map.getConnections()[i][j].getDistance())) /
										2f, middle.getY() * Screen.HEIGHT +
										g.getFontMetrics().getHeight() * 0.3f);
					}
				}
			}
		}
		g.setFont(font);
		for (GuiElement guiElement : guiElements) {
			guiElement.update();
			guiElement.draw(g);
			g.setFont(font);
		}
		g.setFont(bigFont);
		g.setColor(c);
		g.drawString(Game.getCurrentPlayer().getName() + "'s Turn: " + String.valueOf((int) Math.floor(Game.getTurn() / 2) + 1), 10 * scale, 290 * scale);
		if (draggingRect != null && draggingImage != null) {
			g.drawImage(draggingImage, (int) draggingRect.x, (int) draggingRect.y,
					(int) draggingRect.width, (int) draggingRect.height, this);
		} else {
			g.setFont(font);
			for (GuiElement guiElement : guiElements) {
				guiElement.drawTooltip(g);
			}
		}
		if (reticuleRect != null && reticuleImage != null) {
			g.drawImage(reticuleImage, (int) reticuleRect.x, (int) reticuleRect.y,
					(int) reticuleRect.width, (int) reticuleRect.height, this);
		}
		graphics.drawImage(image, 0, 0, Screen.WIDTH, Screen.HEIGHT, this);
		repaint();
	}

	public void setPlayer(Player player) {
		for (int i = 0; i < player.getCurrentTrains().size(); i++) {
			trainGoalElements[i].setTrain(player.getCurrentTrains().get(i));
		}
	}
}
