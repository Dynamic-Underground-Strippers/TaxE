package com.dus.taxe;

public class Connection {
    private final int distance;

    public Connection(int distance, Obstacle obstacle) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }
}
