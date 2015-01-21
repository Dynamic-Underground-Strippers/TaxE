package com.dus.taxe;

import com.dus.taxe.Engine.EngineType;

import java.util.ArrayList;

/**
 * Representation of an in-game train
 */
public class Train {
	private final ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
	private Engine engine = new Engine(EngineType.HAND_CART);
	private Goal goal;
	private Route route;
	private int speed;

	public Train() {
		this.engine = new Engine(Engine.EngineType.HAND_CART);
		this.speed = this.engine.getSpeed();
	}

	/**
	 * Applies the given {@link com.dus.taxe.Upgrade} to this {@link com.dus.taxe.Train}
	 *
	 * @param upgrade the {@link com.dus.taxe.Upgrade} to apply
	 */
	public void addUpgrade(Upgrade upgrade) {
		upgrade.use(this); //applies upgrade
		upgrades.add(upgrade);
	}

	/**
	 * @return this {@link com.dus.taxe.Train}'s current {@link com.dus.taxe.Engine}
	 */
	public Engine getEngine() {
		return engine;
	}

	/**
	 * Sets this {@link com.dus.taxe.Train}'s {@link com.dus.taxe.Engine}
	 *
	 * @param engine the {@link com.dus.taxe.Engine} to set
	 */
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

	/**
	 * @return the {@link com.dus.taxe.Goal} that is associated with this {@link com.dus.taxe.Train}
	 */
	public Goal getGoal() {
		return goal;
	}

	/**
	 * Associates the given {@link com.dus.taxe.Goal} with this {@link com.dus.taxe.Train}
	 *
	 * @param goal the {@link com.dus.taxe.Goal} to associate
	 */
	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	/**
	 * @return the {@link com.dus.taxe.Route} that this {@link com.dus.taxe.Train} is currently
	 * following
	 */
	public Route getRoute() {
		return route;
	}

	/**
	 * Sets this {@link com.dus.taxe.Train}'s {@link com.dus.taxe.Route}
	 *
	 * @param route the {@link com.dus.taxe.Route} to set
	 */
	public void setRoute(Route route) {
		this.route = route;
	}

	/**
	 * @return this {@link com.dus.taxe.Train}'s current speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Sets this {@link com.dus.taxe.Train}'s current speed
	 *
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return all {@link com.dus.taxe.Upgrade}s that are currently applied to this {@link com.dus.taxe.Train}
	 */
	public ArrayList<Upgrade> getUpgrades() {
		//returns upgrades for GUI
		return this.upgrades;
	}

	/**
	 * @return whether or not this {@link com.dus.taxe.Train} has completed its {@link com.dus.taxe.Goal}
	 */
	public boolean hasCompletedGoal() {
		return route.isComplete();
	}

	/**
	 * Checks whether or not this {@link com.dus.taxe.Train} already has an {@link com.dus.taxe
	 * .Upgrade} with the given name
	 *
	 * @param name the name of the {@link com.dus.taxe.Upgrade} to check for
	 * @return whether or not this {@link com.dus.taxe.Train} already has an {@link com.dus.taxe
	 * .Upgrade} with the given name
	 */
	public boolean hasUpgrade(String name) {
		for (Upgrade up : upgrades) {
			if (up.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Moves this {@link com.dus.taxe.Train} along its current {@link com.dus.taxe.Route}, if it
	 * has one
	 */
	public void moveTrain() {
		if (this.route != null) {
			boolean teleport = false;
			for (int i = 0; i < upgrades.size(); i++) {
				if (upgrades.get(i).getName().equals("Teleport")) {
					teleport = true;
				}
			}
			if (teleport) {
				this.route.useTeleport();
			} else {
				this.route.updateDistanceAlongConnection(this.speed);
			}

		}
	}
}
