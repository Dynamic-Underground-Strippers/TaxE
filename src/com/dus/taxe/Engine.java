package com.dus.taxe;
import java.util.ArrayList;

public class Engine extends Resource {
    private EngineType type;

    public enum EngineType {
        steam ("Steam Engine", "", 25),
        diesel ("Diesel Engine", "", 50),
        petrol ("Petrol Engine", "", 75),
        nuclear ("Nuclear Engine", "", 100),

            ;

        private String name;
        private String description;
        private int speed;

        private EngineType(String name, String description, int speed){
            this.name = name;
            this.description = description;
            this.speed = speed;
        }

        private String getEngineName(){
            return this.name;
        }

        private String getEngineDescription(){
            return this.description;
        }

        private int getEngineSpeed(){
            return this.speed;
        }
    }

    public Engine(EngineType type) {
        this.type = type;
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


}
