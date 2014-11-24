package com.dus.taxe;

import java.util.ArrayList;

public class Player {
    private final String name;
    private int points;
    private ArrayList<Goal> currentGoals;
    private ArrayList<Resource> inventory;

    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.currentGoals = new ArrayList<Goal>();
        this.inventory = new ArrayList<Resource>();
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
                inventory.remove(goal);
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
