package com.dus.taxe;

import java.util.ArrayList;
//TODO: Give player a random engine
//TODO: Give player a random upgrade
//TODO: Implement basic trains: storing them, check max for each; implement discarding random upgrade and engines and unstarted goals
public class Player {
    private final String name;
    private int points;
    private ArrayList<Goal> currentGoals;
    private ArrayList<Upgrade> upgradeInventory;
    private ArrayList<Engine> engineInventory;

    public Player(String name) {
        //TODO: Update constructor
        this.name = name;
        this.points = 0;
        this.currentGoals = new ArrayList<Goal>();
        this.upg = new ArrayList<Resource>();
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void selectRandomEngine() {
    }

    public void completeGoals(){
        for (Goal goal: currentGoals){
            if (goal.isComplete()){
                addPoints(goal.getPoints());
                currentGoals.remove(goal);
            }
        }
    }

    public void selectRandomUpgrade() {
    }

    public void getNewGoal(){
        //Need a way of finding appropriate points for a goal
        Goal newGoal = GameData.getRandomGoal();
        currentGoals.add(newGoal);
    }
}
