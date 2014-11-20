package com.dus.taxe;

import java.util.ArrayList;

public class Train {
    private Node currentNode;
    private int distanceAlongConnection;
    private Engine engine;
    private ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
    private ArrayList<Node> visitedNodes = new ArrayList<Node>();


    public Train(Node currentNode, int distanceAlongConnection, Engine engine, ArrayList<Upgrade> upgrades, ArrayList<Node> visitedNodes) {
        this.currentNode = currentNode;
        this.distanceAlongConnection = distanceAlongConnection;
        this.engine = engine;
        this.upgrades = upgrades;
        this.visitedNodes = visitedNodes;
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

    public ArrayList<Node> getVisitedNodes() {
        return visitedNodes;
    }

}
