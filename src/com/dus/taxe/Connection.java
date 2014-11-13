package com.dus.taxe;

public class Connection {
    private final int distance;
    private Obstacle obstacle;

    public Connection(int distance, Obstacle obstacle) {
        this.distance = distance;
        this.obstacle = obstacle;
    }

    public int getDistance() {
        return distance;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }
}
