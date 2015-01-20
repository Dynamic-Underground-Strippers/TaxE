package com.dus.taxe;

import java.util.Random;

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

	public String getDescription() {
		return type.description;
	}

	public String getName() {
		return type.name;
	}

	public boolean getReapply() {
		return type.reapply;
	}

	public UpgradeType getType() {
		return type;
	}

    /*
	*
    * the method use allows an upgrade to be applied to a train
    *
    * if called with a train object as parameter allows to apply modifiers to trains
    *
    * if called with a train object and a node as parameters allows to warp a train to a node given that the node is contained in the route
    *
    * if called with a node or a connection as parameter allows to use obstacles related upgrades
    *
    * */

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
