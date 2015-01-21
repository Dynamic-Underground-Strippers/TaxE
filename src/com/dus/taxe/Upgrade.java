package com.dus.taxe;

import java.util.Random;

/**
 * Representation of an in-game upgrade
 */
public class Upgrade implements Resource, Comparable<Upgrade> {
	private final UpgradeType type;

	public enum UpgradeType { //enumerated type containing types of engine
		//UpgradeType (String name, String description, boolean reapply)
		DOUBLE_SPEED("Double Speed", "This upgrade doubles the speed of one of your trains!",
				true), //apply to trains
		//engineer ("Engineer","Carrying an engineer allows you to instantly repair an obstacle", false), //apply to trains should be implemented with obstacles
		TELEPORT("Teleport", "Brings a train to a Station instantly",
				false)// use on train, modifies route and current node
		//obstacle,
		//removeObstacle,
		;

		private final String description; //description variable internal to enumerated type
		private final String name; //name variable internal to enumerated type
		private final boolean reapply; //true if upgrade must be reapplied for new engine

		private UpgradeType(String name, String description,
				boolean reapply) { //setting upgrade type sets names to those defined in enum
			this.name = name;
			this.description = description;
			this.reapply = reapply;
		}
	}

	public Upgrade() { //Create upgrade with random upgrade type
		Random rand = new Random();
		int chance = rand.nextInt(100) + 1; //random number between 1 and 100
		if (chance < 80) {
			this.type = UpgradeType.DOUBLE_SPEED;
		} else {
			this.type = UpgradeType.TELEPORT;
		}
	}

	@Override
	public int compareTo(Upgrade upgrade) {
		return (this.getName().compareTo(upgrade.getName()));
	}

	/**
	 * @return this {@link com.dus.taxe.Upgrade}'s description
	 */
	public String getDescription() {
		return type.description;
	}

	/**
	 * @return this {@link com.dus.taxe.Upgrade}'s name
	 */
	public String getName() {
		return type.name;
	}

	/**
	 * @return whether or not to reapply this {@link com.dus.taxe.Upgrade}'s type
	 */
	public boolean getReapply() {
		return type.reapply;
	}

	/**
	 * @return this {@link com.dus.taxe.Upgrade}'s type
	 */
	public UpgradeType getType() {
		return type;
	}

	/**
	 * <p>Allows an {@link com.dus.taxe.Upgrade} to be applied to a {@link com.dus.taxe
	 * .Train}
	 * <p/>
	 * If called with a {@link com.dus.taxe.Train} object as parameter allows to apply modifiers
	 * to {@link com.dus.taxe.Train}s
	 * <p/>
	 * If called with a {@link com.dus.taxe.Train} object and a {@link Node} as parameters allows
	 * to warp a {@link com.dus.taxe.Train} to a node given that the node is contained in the
	 * route
	 * <p/>
	 * If called with a {@link com.dus.taxe.Node} or a {@link com.dus.taxe.Connection} as parameter allows to use
	 * obstacles
	 *
	 * @param train the {@link com.dus.taxe.Train} to apply this {@link com.dus.taxe.Upgrade} to
	 */
	public void use(Train train) {
		if (train.hasUpgrade(type.name)) {
			//throw exception - can't have 2 of an upgrade applied
		} else {
			switch (type) {
				case DOUBLE_SPEED:
					train.setSpeed(train.getSpeed() * 2);
					break;
			}

		}
	}

}
