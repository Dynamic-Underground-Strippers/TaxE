package com.dus.taxe;

public class Obstacle {
    private final ObstacleType type;
    private int turnsRemaining;

    enum ObstacleType {}

    public Obstacle(ObstacleType type, int turnsRemaining) {
        this.type = type;
        this.turnsRemaining = turnsRemaining;
    }

    public int getTurnsRemaining() {
        return turnsRemaining;
    }

    public void setTurnsRemaining(int turnsRemaining) {
        this.turnsRemaining = turnsRemaining;
    }

    public ObstacleType getType() {
        return type;
    }
}
