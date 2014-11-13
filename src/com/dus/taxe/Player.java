package com.dus.taxe;

public class Player {
    private final String name;
    private int points;

    public Player(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void selectRandomEngine() {
    }

    public void selectRandomGoal() {
    }

    public void selectRandomUpgrade() {
    }
}
