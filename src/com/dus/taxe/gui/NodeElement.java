package com.dus.taxe.gui;

import com.dus.taxe.Connection;
import com.dus.taxe.Game;
import com.dus.taxe.Goal;
import com.dus.taxe.Junction;
import com.dus.taxe.Node;
import com.dus.taxe.Route;
import com.dus.taxe.Train;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * {@link com.dus.taxe.gui.GuiElement} to represent a {@link com.dus.taxe.Node} on the map
 */
class NodeElement extends GuiElement {
	private static Image junctionImage;
	private static Image stationImage;
	private final BasicStroke dottedStroke = new BasicStroke(3, BasicStroke.CAP_BUTT,
			BasicStroke.JOIN_MITER, 10, new float[]{3}, 0);
	private final boolean isJunction;
	private final Node n;
	private final BasicStroke solidStroke = new BasicStroke(3);

	NodeElement(Node n) {
		super(new Rect((int) (n.getLocation().getX() * Screen.WIDTH) - 15 * GUI.scale,
				(int) (n.getLocation().getY() * Screen.HEIGHT) - 15 * GUI.scale, 30 * GUI.scale,
				30 * GUI.scale));
		this.n = n;
		isJunction = n instanceof Junction;
		if (stationImage == null) {
			try {
				stationImage = ImageIO.read(getClass().getResourceAsStream("/station.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (junctionImage == null) {
			try {
				junctionImage = ImageIO.read(getClass().getResourceAsStream("/junction.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setTooltip(n.getName());
	}

	@Override
	public void draw(Graphics2D graphics) {
		String s = n.getName();
		for (int i = 0; i < Game.getCurrentPlayer().getCurrentTrains().size(); i++) {
			Train t = Game.getCurrentPlayer().getCurrentTrains().get(i);
			if (t.getRoute() != null && t.getRoute().getCurrentNode().equals(n)) {
				s += " - Train " + (i + 1);
			}
		}
		for (Train t : Game.getOtherPlayer().getCurrentTrains()) {
			if (t.getRoute() != null && t.getRoute().getCurrentNode().equals(n)) {
				s += " - Enemy Train";
			}
		}
		setTooltip(s);
		graphics.drawImage(isJunction ? junctionImage : stationImage,
				(int) (n.getLocation().getX() * Screen.WIDTH) - 15,
				(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30, GUI.self);
		if (GUI.settingRoute) {
			if (GUI.tempRouteNodes.contains(n)) { // if the node is already in the route
				graphics.setColor(Color.green);
				graphics.setStroke(solidStroke);
				graphics.drawOval((int) (n.getLocation().getX() * Screen.WIDTH) - 15,
						(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30);
			} else if (!GUI.tempRouteNodes.isEmpty() &&
					GUI.map.getConnections()[GUI.tempRouteNodes.get(GUI.tempRouteNodes.size() - 1)
															   .getId()][n.getId()] != null) { //
				// if the route isn't empty and
				// the node is connected to the
				// last added node
				graphics.setColor(Color.cyan);
				graphics.setStroke(solidStroke);
				graphics.drawOval((int) (n.getLocation().getX() * Screen.WIDTH) - 15,
						(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30);
			} else if (GUI.tempRouteGoal != null && (GUI.tempRouteGoal.getStart().equals(n) ||
					GUI.tempRouteGoal.getEnd().equals(n))) {
				if (GUI.tempRouteGoal.getStart().equals(n)) {
					graphics.setColor(Color.green);
					for (Train t : Game.getCurrentPlayer().getCurrentTrains()) {
						if (t.getGoal() != null && t.getGoal().getStart().equals(n)) {
							return;
						}
					}
					if (GUI.tempRouteTrainGoalElement.getTrain().getRoute() != null) {
						return;
					}
				} else if (GUI.tempRouteGoal.getEnd().equals(n)) {
					graphics.setColor(Color.cyan);
				}
				graphics.setStroke(dottedStroke);
				graphics.drawOval((int) (n.getLocation().getX() * Screen.WIDTH) - 15,
						(int) (n.getLocation().getY() * Screen.HEIGHT) - 15, 30, 30);
			} else if (GUI.tempRouteTrainGoalElement.getTrain().getRoute() == null) {
				for (Train t : Game.getCurrentPlayer().getCurrentTrains()) {
					if (t.getGoal() != null && t.getGoal().getStart().equals(n)) {
						return;
					}
				}
				for (Goal goal : Game.getCurrentPlayer().getCurrentGoals()) {
					if (goal.getStart().equals(n)) {
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
				for (Train t : Game.getCurrentPlayer().getCurrentTrains()) {
					if (t.getGoal() != null && t.getGoal().getStart().equals(n)) {
						return;
					}
				}
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
