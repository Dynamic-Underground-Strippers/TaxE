package com.dus.taxe;

import java.util.ArrayList;

public class Train {

    private Engine engine;
    private boolean engineer;
    private int speed;
    private boolean frozen;
    private ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
    private ArrayList<Node> visitedNodes = new ArrayList<Node>();
    public Route route;

    public Train() {
        this.engine = new Engine(Engine.EngineType.steam);
        this.speed = this.engine.getSpeed();
    }

    public void associateRoute(Route route){
        this.route = route;
    }

    public int getSpeed() {
        return speed;
    }

    public void addUpgrade(Upgrade upgrade) {
        upgrades.add(upgrade);
    }
    
    public Engine getEngine() {
        return engine;
    }


    public void setEngine(Engine engine) {
        this.engine = engine;
        for (Upgrade upgrade :this.upgrades){
            if (upgrade.getType() == Upgrade.UpgradeType.doubleSpeed){
                this.speed = engine.getSpeed()*2; //if train has double, upgrade should be applied again
            }
        }
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public ArrayList<Upgrade> getUpgrades () {
        //returns upgrades for GUI
        return this.upgrades;
    }

    public ArrayList<Node> getVisitedNodes () {
        //returns nodes for GUI
        return this.visitedNodes;
    }

    public boolean hasUpgrade(String name){
        for (Upgrade up: upgrades){
            if (up.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

}
