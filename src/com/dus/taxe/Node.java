package com.dus.taxe;

import java.awt.*;

public abstract class Node {
    private final int id;
    private final Point location;
    private final String name;
    private Obstacle obstacle;

    public Node(int id, String name, Point location, Obstacle obstacle) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.obstacle = obstacle;
    }

    public int getId() {
        return id;
    }

    public Point getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }
}
