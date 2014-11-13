package com.dus.taxe;

public class Engine extends Resource {
    private int speed;

    public Engine(String name, String description, int speed) {
        super(name, description);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
