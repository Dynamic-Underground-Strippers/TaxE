package com.dus.taxe.gui;

import com.dus.taxe.Connection;
import com.dus.taxe.Engine;
import com.dus.taxe.Engine.EngineType;
import com.dus.taxe.Game;
import com.dus.taxe.Goal;
import com.dus.taxe.Map;
import com.dus.taxe.Node;
import com.dus.taxe.Station;
import com.dus.taxe.Train;
import com.dus.taxe.Upgrade;
import com.dus.taxe.Upgrade.UpgradeType;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
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
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GUI extends JFrame {
	public static GUI self;
	Image mapImage;
	Image stationImage;
	ArrayList<GuiElement> guiElements = new ArrayList<GuiElement>();
	Map map;
	ResourceContainer resourceContainer;
	private static final float X_SCALE = Screen.WIDTH / 1920f;
	private static final float Y_SCALE = Screen.HEIGHT / 1080f;
	public static long frameTime = 0;
	private static long lastFrame = 0;
	private BasicStroke solidStroke = new BasicStroke(3);
	private BasicStroke trackStroke = new BasicStroke(8, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10, new float[]{8}, 0);
	private ArrayList<Node> tempRouteNodes = new ArrayList<Node>();
	private ArrayList<Connection> tempRouteConnections = new ArrayList<Connection>();

	public GUI(Map map) {
		self = this;
		this.map = map;
		mapImage = new ImageIcon(getClass().getClassLoader().getResource("map.png")).getImage();
		stationImage = new ImageIcon(getClass().getClassLoader().getResource("StationRed.png"))
				.getImage();
		Train[] trains = {new Train(), new Train(), new Train()};
		trains[0].setEngine(new Engine(Engine.EngineType.STEAM));
		trains[1].setEngine(new Engine(Engine.EngineType.DIESEL));
		trains[2].setEngine(new Engine(Engine.EngineType.ELECTRIC));
		Goal[] goals = {new Goal(100, this.map.retrieveNode(0),
				new Station(0, "Place 2", new com.dus.taxe.Point(0, 0))),
						new Goal(200, new Station(0, "Place 1", new com.dus.taxe.Point(0, 0)),
								new Station(0, "Place 2", new com.dus.taxe.Point(0, 0))),
						new Goal(300, new Station(0, "Place 1", new com.dus.taxe.Point(0, 0)),
								new Station(0, "Place 2", new com.dus.taxe.Point(0, 0)))};
		TrainGoalElement[] trainGoalElements = {
				new TrainGoalElement(new Rect(-490, Screen.HEIGHT - 620, 900, 150)),
				new TrainGoalElement(new Rect(-490, Screen.HEIGHT - 413, 900, 150)),
				new TrainGoalElement(new Rect(-490, Screen.HEIGHT - 207, 900, 150))};
		for (int i = 0; i < 3; i++) {
			goals[i].setCurrentTrain(trains[i]);
			trainGoalElements[i].setGoal(goals[i]);
			addGuiElement(trainGoalElements[i]);
		}
		addGuiElement(new SolidColourRect(new Rect(0, 0, 110, Screen.HEIGHT), Color.white));
		resourceContainer = new ResourceContainer(new Rect(10, Screen.HEIGHT - 650, 100, 640));
		resourceContainer.addResource(new Engine(EngineType.HAND_CART));
		resourceContainer.addResource(new Engine(EngineType.STEAM));
		resourceContainer.addResource(new Engine(EngineType.ELECTRIC));
		resourceContainer.addResource(new Upgrade(UpgradeType.DOUBLE_SPEED));
		resourceContainer.addResource(new Upgrade(UpgradeType.TELEPORT));
		resourceContainer.addResource(new Upgrade(UpgradeType.DOUBLE_SPEED));
		resourceContainer.addResource(new Upgrade(UpgradeType.TELEPORT));
		addGuiElement(resourceContainer);
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (Node n : GUI.this.map.listOfNodes) {
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
							if ((c = GUI.this.map.connections[n.getId()][n.getId()]) != null) {
								tempRouteNodes.add(n);
								tempRouteConnections.add(c);
							}
						}
					}
				}
				Collections.reverse(guiElements);
				for (GuiElement guiElement : guiElements) {
					if (guiElement.bounds.contains(e.getPoint())) {
						guiElement.click(e);
					}
				}
				Collections.reverse(guiElements);
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				boolean found = false;
				for (GuiElement guiElement : guiElements) {
					if (guiElement.bounds.contains(e.getPoint())) {
						guiElement.mouseMoved(e);
						found = true;
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

	BufferedImage image;

	public void paint(Graphics graphics) {
		frameTime = System.currentTimeMillis() - lastFrame;
		lastFrame = System.currentTimeMillis();
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
			for (Node n : map.listOfNodes) {
				g.drawImage(stationImage, (int) (n.getLocation().getX() * getWidth()) - 15,
						(int) (n.getLocation().getY() * getHeight()) - 15, 30, 30, GUI.self);
				for (Goal goal : Game.getCurrentPlayer().getCurrentGoals()) {
					if (goal.getStart().equals(n)) {
						g.setColor(Color.green);
						g.setStroke(solidStroke);
						g.drawOval((int) (n.getLocation().getX() * getWidth()) - 15,
								(int) (n.getLocation().getY() * getHeight()) - 15, 30, 30);
					}
				}
			}
		}
		for (GuiElement guiElement : guiElements) {
			guiElement.update();
			guiElement.draw(g);
		}
		graphics.drawImage(image, 0, 0, Screen.WIDTH, Screen.HEIGHT, this);
		repaint();
	}

	public void addGuiElement(GuiElement guiElement) {
		guiElements.add(guiElement);
		repaint();
	}
}
