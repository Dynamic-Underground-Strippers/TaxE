package com.dus.taxe;
import java.util.ArrayList;

public class Engine implements Resource {
    private EngineType type;

    public enum EngineType {
        steam ("Steam Engine", "", 25),
        diesel ("Diesel Engine", "", 50),
        petrol ("Petrol Engine", "", 75),
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

   /* public void use (Train train) {
        train.setEngine(this);

        for (Upgrade upgrade : train.getUpgrades()){
            if (upgrade.getType() == Upgrade.UpgradeType.doubleSpeed){
                upgrade.useUpgrade(train); //if train has double, upgrade should be applied again
            }
        }
    }*/


}
