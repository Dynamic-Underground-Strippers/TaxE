package com.dus.taxe;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	private final String name;
	private final ArrayList<Goal> currentGoals = new ArrayList<Goal>();
	private final ArrayList<Train> currentTrains = new ArrayList<Train>();
	private final ArrayList<Engine> engineInventory = new ArrayList<Engine>();
	private int points;
	private final ArrayList<Upgrade> upgradeInventory = new ArrayList<Upgrade>();
	private final ArrayList<String> messageList = new ArrayList<String>();

	public Player(String name) {
		this.name = name;
		this.points = 0;
		addTrains();
		addGoal();
		giveRandomEngine();
		giveRandomUpgrade();
	}

	public void addGoal() {
		if (!hasMaxGoals()) {
			currentGoals.add(generateGoal());
		} else{
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like Receive a New Goal?", "New Goal", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION) {
				Goal discardedGoal = discardUnstartedGoal();
				if (discardedGoal==null){
					JOptionPane.showMessageDialog(null, "There are no unstarted goals, no goal is eligible to be discarded" , "No Unstarted Goal", JOptionPane.PLAIN_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Goal \"" + discardedGoal.getDescription() + "\" discarded", "Goal Discarded", JOptionPane.PLAIN_MESSAGE);
					currentGoals.add(generateGoal());
				}
			}
		}
	}

	void addPoint() {
		this.points += 1;
	}

	public void addTrains() {
		while (currentTrains.size()<3){
			currentTrains.add(new Train());
		}
	}

	public void completeGoals() {
		ArrayList<Goal> goalsToRemove = new ArrayList<Goal>();
		ArrayList<Train> trainsToRemove = new ArrayList<Train>();
		for (Goal g : currentGoals) {
			for (Train t : currentTrains) {
				if ((t.getGoal() !=null) && (t.getGoal().equals(g) && t.hasCompletedGoal())) {
					System.out.println("Completed goal");
					addPoint();
					goalsToRemove.add(g);
					trainsToRemove.add(t);
				}
			}
		}
		for (Goal g : goalsToRemove){
			messageList.add("Congratulations you completed your goal \"" + g.getDescription() + "\"!");
			currentGoals.remove(g);
		}
		for (Train t : trainsToRemove){
			currentTrains.remove(t);
		}
	}

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

	public void displayMessages(){
		for (String message: messageList){
			JOptionPane.showMessageDialog(null, message , "Goal Completed!", JOptionPane.PLAIN_MESSAGE);
		}
		messageList.clear();
	}
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

	Goal discardUnstartedGoal() {
		ArrayList<Goal> discardable = new ArrayList<Goal>();
		for (Goal g : currentGoals) {
			for (Train t : currentTrains) {
				if (!g.equals(t.getGoal())) {
					discardable.add(g);
				}
			}
		}
		Goal discardedGoal = discardable.get((int) (Math.random() * discardable.size()));
		currentGoals.remove(discardedGoal);
		return discardedGoal;
	}

	Goal generateGoal() {
		Goal g;
		Node start = Game.currentMap.getRandomNode();
		Node end = Game.currentMap.getRandomNode();
		boolean valid=false;
		while (!valid){
			valid = true;
			for (Goal cg: currentGoals){
				if ((cg.containsNode(start))||(cg.containsNode(end))){
					valid = false;
					start = Game.currentMap.getRandomNode();
					end = Game.currentMap.getRandomNode();
				}
			}
			while (start.getId()==end.getId()){
				valid = false;
				end = Game.currentMap.getRandomNode();
			}
		}
		g = new Goal(start,end);
		return g;
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
		}else{
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like Receive a New Engine?", "New Engine", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION) {
				Engine discardedEngine = discardRandEngine();
				JOptionPane.showMessageDialog(null, "Engine \"" + discardedEngine.getName() + "\" discarded" , "Engine Discarded", JOptionPane.PLAIN_MESSAGE);
				this.engineInventory.add(new Engine());
			}
		}
	}

	public void giveRandomUpgrade() { //Need to come up with more upgrades
		if (!this.hasMaxUpgrades()) {
			this.upgradeInventory.add(new Upgrade());
		}else{
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like Receive a New Upgrade?", "New Upgrade", dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION) {
				Upgrade discardedUpgrade =discardRandUpgrade();
				JOptionPane.showMessageDialog(null, "Upgrade \"" + discardedUpgrade.getName() + "\" discarded" , "Upgrade Discarded", JOptionPane.PLAIN_MESSAGE);
				this.upgradeInventory.add(new Upgrade());
			}
		}
	}

	boolean hasMaxEngines() {
		if (engineInventory.size() == 3) {
			return true;

		}
		return false;

	}

	boolean hasMaxGoals() {
		if (currentGoals.size() == 3) {
			return true;
		}
		return false;
	}

	boolean hasMaxUpgrades() {
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

	public void removeUpgrade(Upgrade upgrade) {
		upgradeInventory.remove(upgrade);
	}

}