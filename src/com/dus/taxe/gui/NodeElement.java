package com.dus.taxe.gui;

import com.dus.taxe.Connection;
import com.dus.taxe.Game;
import com.dus.taxe.Goal;
import com.dus.taxe.Junction;
import com.dus.taxe.Node;
import com.dus.taxe.Route;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class NodeElement extends GuiElement {
	private static Image stationImage;
	private static Image junctionImage;
	private final BasicStroke dottedStroke = new BasicStroke(3, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10, new float[]{3}, 0);
	private final Node n;
	private final BasicStroke solidStroke = new BasicStroke(3);
	private final boolean isJunction;

	public NodeElement(Node n) {
		super(new Rect((int) (n.getLocation().getX() * Screen.WIDTH) - 15,
				(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30));
		this.n = n;
		if (n instanceof Junction) {
			isJunction = true;
		} else {
			isJunction = false;
		}
		if (stationImage == null) {
			//noinspection ConstantConditions
			stationImage = new ImageIcon(getClass().getClassLoader().getResource("station.png"))
					.getImage();
		}
		if (junctionImage == null) {
			//noinspection ConstantConditions
			junctionImage = new ImageIcon(getClass().getClassLoader().getResource("junction.png"))
					.getImage();
		}
		setTooltip(n.getName());
	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.drawImage(isJunction ? junctionImage : stationImage,
				(int) (n.getLocation().getX() * Screen.WIDTH) - 15,
				(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30, GUI.self);
		if (GUI.settingRoute) {
			if (GUI.tempRouteNodes.contains(n)) {
				graphics.setColor(Color.green);
				graphics.setStroke(solidStroke);
				graphics.drawOval((int) (n.getLocation().getX() * Screen.WIDTH) - 15,
						(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30);
			} else if (!GUI.tempRouteNodes.isEmpty() &&
					GUI.map.getConnections()[GUI.tempRouteNodes.get(GUI.tempRouteNodes.size() - 1)
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
			} else if ((c = GUI.map.getConnections()[GUI.tempRouteNodes
					.get(GUI.tempRouteNodes.size() - 1).getId()][n.getId()]) != null) {
				GUI.tempRouteNodes.add(n);
				GUI.tempRouteConnections.add(c);
			}
			if (GUI.tempRouteGoal != null && GUI.tempRouteGoal.getEnd().equals(n)) {
				GUI.tempRouteTrainGoalElement.getTrain().setRoute(
						new Route(new ArrayList<Node>(GUI.tempRouteNodes)));
				GUI.tempRouteTrainGoalElement.getTrain().setGoal(GUI.tempRouteGoal);
				GUI.tempRouteTrainGoalElement.getEditRouteButton().setTooltip("Edit route");
				GUI.tempRouteGoal = null;
				GUI.tempRouteTrainGoalElement = null;
				GUI.settingRoute = false;
				GUI.tempRouteNodes.clear();
				GUI.tempRouteConnections.clear();
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
