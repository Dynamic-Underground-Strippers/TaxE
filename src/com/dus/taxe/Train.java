package com.dus.taxe;

import java.util.ArrayList;

public class Train {

    private Engine engine;
    private boolean engineer;
    private int speed;
    private boolean frozen;
    private ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
    private ArrayList<Node> visitedNodes = new ArrayList<Node>();
    private Route route;

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
        upgrade.use(this); //applies upgrade
        upgrades.add(upgrade);
    }

    public void setSpeed(int speed){ this.speed = speed;}
    
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        engine.use(this);
        this.engine = engine;
        for (Upgrade upgrade :this.upgrades){
            if (upgrade.getReapply()){
                upgrade.use(this);
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


    public Route getRoute() {
        return route;
    }
}
