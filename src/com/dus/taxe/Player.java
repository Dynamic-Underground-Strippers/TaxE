package com.dus.taxe;

import java.util.ArrayList;

public class Player {
    private final String name;
    private int points;
    private ArrayList<Goal> currentGoals;
    private ArrayList<Upgrade> inventory;

    public Player(String name) {
        this.name = name;
        this.points = 0;
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

    public void selectRandomGoal() {
    }

    public void selectRandomUpgrade() {
    }
}
