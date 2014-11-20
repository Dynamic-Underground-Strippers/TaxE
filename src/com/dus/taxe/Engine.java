package com.dus.taxe;

public class Engine extends Resource {
    private int speed;

    public enum engineType {
        diesel,
        petrol,
        steam,
        nuclear
    }

    public Engine(engineType type) {
        String name = "";
        String description = "";

        switch(type){
            case steam:
                name = "Steam Engine";
                description = "";
                this.speed = 25;
                break;

            case diesel:
                name = "Diesel Engine";
                description = "";
                this.speed = 50;
                break;

            case petrol:
                name = "Petrol Engine";
                description = "";
                this.speed = 75;
                break;

            case nuclear:
                name = "Nuclear Engine";
                description = "";
                this.speed = 100;
                break;
        }

        super.setName(name);
        super.setDescription(description);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
