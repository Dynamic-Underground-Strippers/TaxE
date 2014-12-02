package com.dus.taxe;
import java.util.Random;


public class Engine implements Resource {
    private EngineType type;

    public enum EngineType { //enumerated type containing types of engine
        //EngineType (String name, String description, int Speed)
        handCart ("Hand Cart", "", 15),
        steam ("Steam Engine", "", 25),
        diesel ("Diesel Engine", "", 50),
        electric ("Electric Engine", "", 75),
        nuclear ("Nuclear Engine", "", 100),
        ;

        private String name; //name variable internal to enumerated type
        private String description; //description variable internal to enumerated type
        private int speed; //speed variable internal to enumerated type

        private EngineType(String name, String description, int speed){ //setting engine type sets names to those defined in enum
            this.name = name;
            this.description = description;
            this.speed = speed;
        }

    }

    public Engine(EngineType type) {
        this.type = type;
    } //generate engine with specified type

    public Engine() { //generate engine with random type
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        if (chance < 40){
            this.type = EngineType.steam;
        } else if (chance < 70) {
            this.type = EngineType.diesel;
        } else if (chance < 90) {
            this.type = EngineType.electric;
        } else {
            this.type = EngineType.nuclear;
        }
    }

    public String getName(){
        return this.type.name;
    }

    public String getDescription(){
        return this.type.description;
    }

    public int getSpeed() {
        return this.type.speed;
    }

    public void use (Train train) {
       train.setSpeed(type.speed);
    }

}