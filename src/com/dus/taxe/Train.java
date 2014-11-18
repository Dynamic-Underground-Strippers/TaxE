package com.dus.taxe;

import java.util.ArrayList;

public class Train {
    private Node currentNode;
    private int distanceAlongConnection;
    private Engine engine;
    private boolean Engineer;
    private int Speed;
    private boolean frozen;
    private ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
    private ArrayList<Node> visitedNodes = new ArrayList<Node>();


    public Train(Node currentNode, int distanceAlongConnection, Engine engine, boolean frozen, ArrayList<Upgrade> upgrades, ArrayList<Node> visitedNodes) {
        this.currentNode = currentNode;
        this.distanceAlongConnection = distanceAlongConnection;
        this.engine = engine;
        this.frozen = frozen;
        this.upgrades = upgrades;
        this.visitedNodes = visitedNodes;
    }

    public int getSpeed() {
        return Speed;
    }

    public void setSpeed(int speed) {
        Speed = speed;
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

    public void setEngine(Engine engine) {
        this.engine = engine;
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
        //returns upgrades for GUI
        return this.visitedNodes;
    }

}
