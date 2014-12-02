package com.dus.taxe.gui;

import com.dus.taxe.Goal;
import com.dus.taxe.Map;
import com.dus.taxe.Node;
import com.dus.taxe.gui.goals.GoalContainer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GUI extends JFrame {
	public static GUI self;
	Image mapImage;
	ArrayList<GuiElement> guiElements = new ArrayList<GuiElement>();
	Map map;

	public GUI() {
		self = this;
		mapImage = new ImageIcon(getClass().getClassLoader().getResource("map.png")).getImage();
		GoalContainer gc = new GoalContainer(50, 50, 900, 150);
		gc.setGoals(new Goal[]{new Goal("Goal 1", "Description 1", 100, null, null), new Goal("Goal 2", "Description 2", 200, null, null), new Goal("Goal 2", "Description 2", 300, null, null)});
		addGuiElement(gc);
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
			}

			public void mouseMoved(MouseEvent e) {
				for (GuiElement guiElement : guiElements) {
					guiElement.mouseMoved(e);
				}
			}
		});
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void paint(Graphics graphics) {
		BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		int imageWidth = (int) ((float) mapImage.getWidth(this) * ((float) getHeight() / (float) mapImage.getHeight(this)));
		g.drawImage(mapImage, getWidth() - imageWidth, 0, imageWidth, getHeight(), this);
		for (GuiElement guiElement : guiElements) {
			guiElement.draw(g);
		}
		g.setColor(Color.MAGENTA);
		if (map != null) {
			for (Node n : map.listOfNodes) {
				g.fillOval((int) (n.getLocation().getX() * getWidth()), (int) (n.getLocation().getY() * getHeight()), 20, 20);
			}
		}
		graphics.drawImage(image, 0, 0, this);
		repaint();
	}

	public void addGuiElement(GuiElement guiElement) {
		guiElements.add(guiElement);
		repaint();
	}
}
