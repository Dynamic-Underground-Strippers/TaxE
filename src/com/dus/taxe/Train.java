package com.dus.taxe;

import java.util.ArrayList;

public class Train {
    private Node currentNode;
    private int distanceAlongConnection;
    private Engine engine;
    private boolean engineer;
    private int speed;
    private boolean frozen;
    private ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
    private ArrayList<Node> visitedNodes = new ArrayList<Node>();
    private int defaultSpeed = 10; //speed of base train
    public Route route;


    public Train(Node currentNode, int distanceAlongConnection, Engine engine, boolean frozen, ArrayList<Upgrade> upgrades, ArrayList<Node> visitedNodes) {
        this.currentNode = currentNode;
        this.distanceAlongConnection = distanceAlongConnection;
        this.engine = engine;
        this.frozen = frozen;
        this.upgrades = upgrades;
        this.visitedNodes = visitedNodes;
        this.speed = defaultSpeed;
    }

    public void associateRoute(Route route){
        this.route = route;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
       this.speed = speed;
    }

    public boolean haveEngineer() {
        return engineer;
    }

    public void setEngineer(boolean engineer) {
        this.engineer = engineer;
    }

    public void addUpgrade(Upgrade upgrade) {
        upgrades.add(upgrade);
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public int getDistanceAlongConnection() {
        return distanceAlongConnection;
    }

    public void setDistanceAlongConnection(int distanceAlongConnection) {
        this.distanceAlongConnection = distanceAlongConnection;
    }

    public Engine getEngine() {
        return engine;
    }

    public boolean isFrozen() {
        return frozen;
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
