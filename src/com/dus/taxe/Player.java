package com.dus.taxe;

import java.util.ArrayList;
import java.util.Random;


//TODO: Give player a random engine
//TODO: Give player a random upgrade
//TODO: Implement basic trains: storing them, check max for each; implement discarding random upgrade and engines and unstarted goals
public class Player {
	private final String name;
	private ArrayList<Goal> currentGoals = new ArrayList<Goal>();
	private ArrayList<Train> currentTrains = new ArrayList<Train>();
	private ArrayList<Engine> engineInventory = new ArrayList<Engine>();
	private Map map;
	private int points;
	private ArrayList<Upgrade> upgradeInventory = new ArrayList<Upgrade>();


	public Player(String name, Map map) {
		this.name = name;
		this.map = map;
		this.points = 0;
		for (int i = 0; i < 3; i++) {
			addTrain();
		}
		generateGoals();
		for (int i = 0; i < 3; i++) {
			giveRandomEngine();
		}
		for (int i = 0; i < 4; i++) {
			giveRandomUpgrade();
		}
	}

	public void addGoal(Goal goal) {
		if (!hasMaxGoals()) {
			currentGoals.add(goal);
		}
	}

	public void addPoints(int points) {

		this.points += points;
	}

	public void addTrain() {
		if (this.trainSize() < 3) {
			currentTrains.add(new Train());
		}
	}

	public void completeGoals() {
		for (Goal g : currentGoals) {
			for (Train t : currentTrains) {
				if (t.getGoal().equals(g) && t.hasCompletedGoal()) {
					addPoints(g.getPoints());
					currentGoals.remove(g);
				}
			}
		}
	}

	public Engine discardRandEngine() {
		//This method removes a random engine from the list, provided the player has maximum number
		//of upgrades, and returns it.
		if (this.hasMaxEngines()) {
			int randomIndex = new Random().nextInt(engineInventory.size());
			Engine discardedEngine = engineInventory.get(randomIndex);
			engineInventory.remove(randomIndex);

		}
		return null;
	}

	public Upgrade discardRandUpgrade() {
		//This method removes a random upgrade from the list, provided the player has maximum number
		//of upgrades, and returns it.
		if (this.hasMaxUpgrades()) {
			int randomIndex = new Random().nextInt(upgradeInventory.size());
			Upgrade discardedUpgrade = upgradeInventory.get(randomIndex);
			upgradeInventory.remove(randomIndex);
			return discardRandUpgrade();

		}
		return null;
	}

	public Goal discardUnstartedGoal() {
		ArrayList<Goal> discardable = new ArrayList<Goal>();
		for (Goal g : currentGoals) {
			for (Train t : currentTrains) {
				if (!g.equals(t.getGoal())) {
					discardable.add(g);
				}
			}
		}
		Goal discardedGoal = discardable.get((int) (Math.random() * discardable.size()));
		return discardedGoal;
	}

	public void generateGoals() {
		Goal g;
		while (!hasMaxGoals()) {
			if (!currentGoals.contains(g = map.getRandomGoal())) {
				currentGoals.add(g);
			}
		}
	}

	public ArrayList<Goal> getCurrentGoals() {
		return currentGoals;
	}

	public ArrayList<Train> getCurrentTrains() {
		return currentTrains;
	}

	public ArrayList<Engine> getEngineInventory() {
		return engineInventory;
	}

	public String getName() {

		return this.name;
	}

	public int getPoints() {

		return this.points;
	}

	public Train getTrain(int i) {
		return currentTrains.get(i);
	}

	public int getTrainIndex(Train t) {
		return currentTrains.indexOf(t);
	}

	public ArrayList<Train> getTrains() {
		return currentTrains;
	}

	public ArrayList<Upgrade> getUpgradeInventory() {
		return upgradeInventory;
	}

	public void giveRandomEngine() {
		if (!this.hasMaxEngines()) {
			this.engineInventory.add(new Engine());
		}
	}

	public void giveRandomUpgrade() { //Need to come up with more upgrades
		if (!this.hasMaxUpgrades()) {
			this.upgradeInventory.add(new Upgrade());
		}
	}

	public boolean hasMaxEngines() {
		if (engineInventory.size() == 3) {
			return true;

		}
		return false;

	}

	public boolean hasMaxGoals() {
		if (currentGoals.size() == 3) {
			return true;
		}
		return false;
	}

	public boolean hasMaxUpgrades() {
		if (upgradeInventory.size() == 4) {
			return true;

		}
		return false;

	}

	public void moveTrains() {
		for (Train train : currentTrains) {
			train.moveTrain();
		}
	}

	public void removeEngine(Engine engine) {
		engineInventory.remove(engine);
	}

	public void removeTrain(int i) {
		currentTrains.remove(i);
	}

	public void removeUpgrade(Upgrade upgrade) {
		upgradeInventory.remove(upgrade);
	}

	public void selectRandomEngine() {
	}

	public void selectRandomUpgrade() {
	}

	public int trainSize() {
		return currentTrains.size();
	}

	public int upgradeSize() {
		return upgradeInventory.size();

	}
}
