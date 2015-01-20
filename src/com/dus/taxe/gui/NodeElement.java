package com.dus.taxe.gui;

import com.dus.taxe.Connection;
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
	private static Image stationImage;
	private final BasicStroke dottedStroke = new BasicStroke(3, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10, new float[]{3}, 0);
	private final Node n;
	private final BasicStroke solidStroke = new BasicStroke(3);

	public NodeElement(Node n) {
		super(new Rect((int) (n.getLocation().getX() * Screen.WIDTH) - 15,
				(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30));
		this.n = n;
		if (stationImage == null) {
			//noinspection ConstantConditions
			stationImage = new ImageIcon(getClass().getClassLoader().getResource("StationRed.png"))
					.getImage();
		}
		setTooltip(n.getName());
	}

	boolean special = false;

	@Override
	public void draw(Graphics2D graphics) {
		special = false;
		graphics.drawImage(stationImage, (int) (n.getLocation().getX() * Screen.WIDTH) - 15,
				(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30, GUI.self);
		if (GUI.settingRoute) {
			if (GUI.tempRouteNodes.contains(n)) {
				graphics.setColor(Color.green);
				graphics.setStroke(solidStroke);
				graphics.drawOval((int) (n.getLocation().getX() * Screen.WIDTH) - 15,
						(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30);
			} else if (!GUI.tempRouteNodes.isEmpty() &&
					GUI.map.connections[GUI.tempRouteNodes.get(GUI.tempRouteNodes.size() - 1)
														  .getId()][n.getId()] != null) {
				graphics.setColor(Color.cyan);
				graphics.setStroke(solidStroke);
				graphics.drawOval((int) (n.getLocation().getX() * Screen.WIDTH) - 15,
						(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30);
			} else if (GUI.tempRouteGoal != null) {
				if (GUI.tempRouteGoal.getStart().equals(n) ||
						GUI.tempRouteGoal.getEnd().equals(n)) {
					graphics.setColor(
							GUI.tempRouteGoal.getStart().equals(n) ? Color.green : Color.cyan);
					graphics.setStroke(dottedStroke);
					graphics.drawOval((int) (n.getLocation().getX() * Screen.WIDTH) - 15,
							(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30);
				}
			} else {
				for (Goal goal : Game.getCurrentPlayer().getCurrentGoals()) {
					if (goal.getStart().equals(n) || goal.getEnd().equals(n)) {
						graphics.setColor(goal.getStart().equals(n) ? Color.green : Color.cyan);
						graphics.setStroke(dottedStroke);
						graphics.drawOval((int) (n.getLocation().getX() * Screen.WIDTH) - 15,
								(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30);
					}
				}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		GUI.self.setCursor(Cursor.HAND_CURSOR);
	}

	@Override
	public void onClick(MouseEvent e) {
		if (GUI.settingRoute) {
			Connection c;
			if (GUI.tempRouteNodes.isEmpty()) {
				for (Goal goal : Game.getCurrentPlayer().getCurrentGoals()) {
					if (goal.getStart().equals(n)) {
						GUI.tempRouteGoal = goal;
						GUI.tempRouteNodes.add(n);
					}
				}
			} else if (
					(c = GUI.map.connections[GUI.tempRouteNodes.get(GUI.tempRouteNodes.size() - 1)
															   .getId()][n.getId()]) != null) {
				GUI.tempRouteNodes.add(n);
				GUI.tempRouteConnections.add(c);
			} if (GUI.tempRouteGoal != null && GUI.tempRouteGoal.getEnd().equals(n)) {
				GUI.tempRouteGoal = null;
				GUI.tempRouteNodes.clear();
			}
		}
	}

	@Override
	public void onMouseDown(MouseEvent e) {

	}

	@Override
	public void onMouseUp(MouseEvent e) {

	}
}
