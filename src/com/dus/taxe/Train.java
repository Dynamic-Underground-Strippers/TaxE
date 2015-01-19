package com.dus.taxe;

import com.dus.taxe.Engine.EngineType;

import java.util.ArrayList;
import java.util.Collections;

public class Train {

	private Engine engine = new Engine(EngineType.HAND_CART);
	private boolean engineer;
	private int speed;
	private boolean frozen;
	private ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
	private ArrayList<Node> visitedNodes = new ArrayList<Node>();
	private Route route;
	private Goal goal;

	public Train() {
		this.engine = new Engine(Engine.EngineType.HAND_CART);
		this.speed = this.engine.getSpeed();
	}

	public void associateRoute(Route route) {
		this.route = route;
	}

	public Goal getGoal() {
		return goal;
	}

	public int getSpeed() {
		return speed;
	}

	public void addUpgrade(Upgrade upgrade) {
		upgrade.use(this); //applies upgrade
		upgrades.add(upgrade);
	}

	public boolean hasCompletedGoal() {
		return visitedNodes.get(visitedNodes.size() - 1).equals(goal.getEnd());
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		engine.use(this);
		this.engine = engine;
		for (Upgrade upgrade : this.upgrades) {
			if (upgrade.getReapply()) {
				this.upgrades.remove(upgrade);
				upgrade.use(this);
				this.upgrades.add(upgrade);
			}
		}
		Collections.sort(this.upgrades);

	}

	public void setFrozen(boolean frozen) {
		this.frozen = frozen;
	}

	public ArrayList<Upgrade> getUpgrades() {
		//returns upgrades for GUI
		return this.upgrades;
	}

	public ArrayList<Node> getVisitedNodes() {
		//returns nodes for GUI
		return this.visitedNodes;
	}

	public boolean hasUpgrade(String name) {
		for (Upgrade up : upgrades) {
			if (up.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public void moveTrain() {
		this.route.updateDistanceAlongConnection();
	}

	public Route getRoute() {
		return route;
	}
}
