package com.dus.taxe;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * An in-game player
 */
public class Player {
	private final ArrayList<Goal> currentGoals = new ArrayList<Goal>();
	private final ArrayList<Train> currentTrains = new ArrayList<Train>();
	private final ArrayList<Engine> engineInventory = new ArrayList<Engine>();
	private final ArrayList<String> messageList = new ArrayList<String>();
	private final String name;
	private final ArrayList<Upgrade> upgradeInventory = new ArrayList<Upgrade>();
	private int points;

	public Player(String name) {
		this.name = name;
		this.points = 0;
		addTrains();
		addGoal();
		giveRandomEngine();
		giveRandomUpgrade();
	}

	/**
	 * Adds a random {@link com.dus.taxe.Goal} to this {@link com.dus.taxe.Player}
	 */
	public void addGoal() {
		if (!hasMaxGoals()) {
			currentGoals.add(generateGoal());
		} else {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane
					.showConfirmDialog(null, "Would You Like Receive a New Goal?", "New Goal",
							dialogButton);
			if (dialogResult == JOptionPane.YES_OPTION) {
				Goal discardedGoal = discardUnstartedGoal();
				if (discardedGoal == null) {
					JOptionPane.showMessageDialog(null,
							"There are no unstarted goals, no goal is eligible to be discarded",
							"No Unstarted Goal", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"Goal \"" + discardedGoal.getDescription() + "\" discarded",
							"Goal Discarded", JOptionPane.PLAIN_MESSAGE);
					currentGoals.add(generateGoal());
				}
			}
		}
	}

	/**
	 * Adds a point to this {@link com.dus.taxe.Player}
	 */
	void addPoint() {
		this.points += 1;
	}

	/**
	 * Generates {@link com.dus.taxe.Train}s for this {@link com.dus.taxe.Player}
	 */
	public void addTrains() {
		while (currentTrains.size() < 3) {
			currentTrains.add(new Train());
		}
	}

	/**
	 * Checks this {@link com.dus.taxe.Player}'s {@link com.dus.taxe.Goal}s for completion, and
	 * handles them if necessary
	 */
	public void completeGoals() {
		ArrayList<Goal> goalsToRemove = new ArrayList<Goal>();
		ArrayList<Train> trainsToRemove = new ArrayList<Train>();
		for (Goal g : currentGoals) {
			for (Train t : currentTrains) {
				if ((t.getGoal() != null) && (t.getGoal().equals(g) && t.hasCompletedGoal())) {
					System.out.println("Completed goal");
					addPoint();
					goalsToRemove.add(g);
					trainsToRemove.add(t);
				}
			}
		}
		for (Goal g : goalsToRemove) {
			messageList
					.add("Congratulations you completed your goal \"" + g.getDescription() + "\"!");
			currentGoals.remove(g);
		}
		for (Train t : trainsToRemove) {
			currentTrains.remove(t);
		}
	}

	/**
	 * Discards a random {@link com.dus.taxe.Engine} from this {@link com.dus.taxe.Player}'s
	 * inventory
	 * @return the {@link com.dus.taxe.Engine} that was removed
	 */
	Engine discardRandEngine() {
		//This method removes a random engine from the list, provided the player has maximum number
		//of upgrades, and returns it.
		Engine discardedEngine = null;
		if (this.hasMaxEngines()) {
			int randomIndex = new Random().nextInt(engineInventory.size());
			discardedEngine = engineInventory.get(randomIndex);
			engineInventory.remove(randomIndex);
		}
		return discardedEngine;
	}

	/**
	 * Discards a random {@link com.dus.taxe.Upgrade} from this {@link com.dus.taxe.Player}'s
	 * inventory
	 * @return the {@link com.dus.taxe.Upgrade} that was removed
	 */
	Upgrade discardRandUpgrade() {
		//This method removes a random upgrade from the list, provided the player has maximum number
		//of upgrades, and returns it.
		Upgrade discardedUpgrade = null;
		if (this.hasMaxUpgrades()) {
			int randomIndex = new Random().nextInt(upgradeInventory.size());
			discardedUpgrade = upgradeInventory.get(randomIndex);
			upgradeInventory.remove(randomIndex);

		}
		return discardedUpgrade;
	}

	/**
	 * Discards a random unstarted {@link com.dus.taxe.Goal}
	 * @return the {@link com.dus.taxe.Goal} that was removed
	 */
	Goal discardUnstartedGoal() {
		ArrayList<Goal> discardable = new ArrayList<Goal>();
		Goal discardedGoal = null;
		for (Goal g : currentGoals) {
			boolean discard = true;
			for (Train t : currentTrains) {
				if (g.getStart().getId() == t.getGoal().getStart().getId()) {
					discard = false;
				}
			}
			if (discard) {
				discardable.add(g);
			}
		}
		if (discardable.size() != 0) {
			discardedGoal = discardable.get(new Random().nextInt(discardable.size()));
			currentGoals.remove(discardedGoal);
		}
		return discardedGoal;
	}

	/**
	 * Displays any messages that are needed
	 */
	public void displayMessages() {
		for (String message : messageList) {
			JOptionPane
					.showMessageDialog(null, message, "Goal Completed!", JOptionPane.PLAIN_MESSAGE);
		}
		messageList.clear();
	}

	/**
	 * Generates a random {@link com.dus.taxe.Goal}
	 * @return the generates {@link com.dus.taxe.Goal}
	 */
	Goal generateGoal() {
		Goal g;
		Node start = Game.currentMap.getRandomNode();
		Node end = Game.currentMap.getRandomNode();
		boolean valid = false;
		while (!valid) {
			valid = true;
			for (Goal cg : currentGoals) {
				if ((cg.containsNode(start)) || (cg.containsNode(end))) {
					valid = false;
					start = Game.currentMap.getRandomNode();
					end = Game.currentMap.getRandomNode();
				}
			}
			while (start.getId() == end.getId()) {
				valid = false;
				end = Game.currentMap.getRandomNode();
			}
		}
		g = new Goal(start, end);
		return g;
	}

	/**
	 * @return this {@link com.dus.taxe.Player}'s current {@link com.dus.taxe.Goal}s
	 */
	public ArrayList<Goal> getCurrentGoals() {
		return currentGoals;
	}

	/**
	 * @return this {@link com.dus.taxe.Player}'s current {@link com.dus.taxe.Train}s
	 */
	public ArrayList<Train> getCurrentTrains() {
		return currentTrains;
	}

	/**
	 * @return all {@link com.dus.taxe.Engine}s that are in this {@link com.dus.taxe.Player}'s
	 * inventory
	 */
	public ArrayList<Engine> getEngineInventory() {
		return engineInventory;
	}

	/**
	 * @return this {@link com.dus.taxe.Player}'s name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the amount of points that this {@link com.dus.taxe.Player} currently has
	 */
	public int getPoints() {
		return this.points;
	}

	/**
	 * @return all {@link com.dus.taxe.Upgrade}s that are in this {@link com.dus.taxe.Player}'s
	 * inventory
	 */
	public ArrayList<Upgrade> getUpgradeInventory() {
		return upgradeInventory;
	}

	/**
	 * Generates a random {@link com.dus.taxe.Engine} for this {@link com.dus.taxe.Player}
	 */
	public void giveRandomEngine() {
		if (!this.hasMaxEngines()) {
			this.engineInventory.add(new Engine());
		} else {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane
					.showConfirmDialog(null, "Would You Like Receive a New Engine?", "New Engine",
							dialogButton);
			if (dialogResult == JOptionPane.YES_OPTION) {
				Engine discardedEngine = discardRandEngine();
				JOptionPane.showMessageDialog(null,
						"Engine \"" + discardedEngine.getName() + "\" discarded",
						"Engine Discarded", JOptionPane.PLAIN_MESSAGE);
				this.engineInventory.add(new Engine());
			}
		}
	}

	/**
	 * Generates a random {@link com.dus.taxe.Upgrade} for this {@link com.dus.taxe.Player}
	 */
	public void giveRandomUpgrade() { //Need to come up with more upgrades
		if (!this.hasMaxUpgrades()) {
			this.upgradeInventory.add(new Upgrade());
		} else {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane
					.showConfirmDialog(null, "Would You Like Receive a New Upgrade?", "New Upgrade",
							dialogButton);
			if (dialogResult == JOptionPane.YES_OPTION) {
				Upgrade discardedUpgrade = discardRandUpgrade();
				JOptionPane.showMessageDialog(null,
						"Upgrade \"" + discardedUpgrade.getName() + "\" discarded",
						"Upgrade Discarded", JOptionPane.PLAIN_MESSAGE);
				this.upgradeInventory.add(new Upgrade());
			}
		}
	}

	/**
	 * @return whether or not this {@link com.dus.taxe.Player} already has the maximum number of
	 * {@link com.dus.taxe.Engine}s in their inventory
	 */
	boolean hasMaxEngines() {
		if (engineInventory.size() == 3) {
			return true;
		}
		return false;

	}

	/**
	 * @return whether or not this {@link com.dus.taxe.Player} already has the maximum number of
	 * {@link com.dus.taxe.Goal}s
	 */
	boolean hasMaxGoals() {
		if (currentGoals.size() == 3) {
			return true;
		}
		return false;
	}

	/**
	 * @return whether or not this {@link com.dus.taxe.Player} already has the maximum number of
	 * {@link com.dus.taxe.Upgrade}s in their inventory
	 */
	boolean hasMaxUpgrades() {
		if (upgradeInventory.size() == 4) {
			return true;
		}
		return false;

	}

	/**
	 * Moves this {@link com.dus.taxe.Player}'s {@link com.dus.taxe.Train}s
	 */
	public void moveTrains() {
		for (Train train : currentTrains) {
			train.moveTrain();
		}
	}

	/**
	 * Removes the given {@link com.dus.taxe.Engine} from this {@link com.dus.taxe.Player}'s
	 * inventory
	 * @param engine the {@link com.dus.taxe.Engine} to remove
	 */
	public void removeEngine(Engine engine) {
		engineInventory.remove(engine);
	}

	/**
	 * Removes the given {@link com.dus.taxe.Upgrade} from this {@link com.dus.taxe.Player}'s
	 * inventory
	 * @param upgrade the {@link com.dus.taxe.Upgrade} to remove
	 */
	public void removeUpgrade(Upgrade upgrade) {
		upgradeInventory.remove(upgrade);
	}

}
