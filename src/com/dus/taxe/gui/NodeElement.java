package com.dus.taxe.gui;

import com.dus.taxe.Game;
import com.dus.taxe.Goal;
import com.dus.taxe.Node;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class NodeElement extends GuiElement {
	private Node n;
	private static Image stationImage;
	private BasicStroke solidStroke = new BasicStroke(3);
	private BasicStroke dottedStroke = new BasicStroke(3, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10, new float[]{3}, 0);

	public NodeElement(Node n) {
		super(new Rect((int) (n.getLocation().getX() * Screen.WIDTH) - 15,
				(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30));
		this.n = n;
		if (stationImage == null) {
			stationImage = new ImageIcon(getClass().getClassLoader().getResource("StationRed.png"))
					.getImage();
		}
		setTooltip(n.getName());
	}

	@Override
	public void onClick(MouseEvent e) {

	}

	@Override
	public void onMouseDown(MouseEvent e) {

	}

	@Override
	public void onMouseUp(MouseEvent e) {

	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.drawImage(stationImage, (int) (n.getLocation().getX() * Screen.WIDTH) - 15,
				(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30, GUI.self);
		for (Goal goal : Game.getCurrentPlayer().getCurrentGoals()) {
			if (goal.getStart().equals(n)) {
				graphics.setColor(Color.green);
				graphics.setStroke(GUI.tempRouteNodes.contains(n) ? solidStroke : dottedStroke);
//				graphics.setStroke(solidStroke);
				graphics.drawOval((int) (n.getLocation().getX() * Screen.WIDTH) - 15,
						(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		GUI.self.setCursor(Cursor.HAND_CURSOR);
	}
}
