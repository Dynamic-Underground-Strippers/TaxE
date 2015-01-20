package com.dus.taxe;

import com.dus.taxe.Engine.EngineType;

import java.util.ArrayList;

public class Train {

	private Engine engine = new Engine(EngineType.HAND_CART);
	private Goal goal;
	private Route route;
	private int speed;
	private final ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();

	public Train() {
		this.engine = new Engine(Engine.EngineType.HAND_CART);
		this.speed = this.engine.getSpeed();
	}

	public void addUpgrade(Upgrade upgrade) {
		upgrade.use(this); //applies upgrade
		upgrades.add(upgrade);
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		engine.use(this);
		this.engine = engine;
		for (Upgrade upgrade : this.upgrades) {
			if (upgrade.getReapply()) {
				int index = this.upgrades.indexOf(upgrade);
				this.upgrades.remove(upgrade);
				upgrade.use(this);
				this.upgrades.add(index, upgrade);
			}
		}
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public Route getRoute() {
		return route;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ArrayList<Upgrade> getUpgrades() {
		//returns upgrades for GUI
		return this.upgrades;
	}

	public boolean hasCompletedGoal() {
		return route.isComplete();
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
		if (this.route!=null) {
			boolean teleport = false;
			for (int i =0; i<upgrades.size();i++){
				if (upgrades.get(i).getName().equals("Teleport")){
					teleport = true;
				}
			}
			if (teleport){
				this.route.useTeleport();
			}else{
				this.route.updateDistanceAlongConnection(this.speed);
			}

		}
	}
}
