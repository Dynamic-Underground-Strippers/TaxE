package com.dus.taxe;

import java.awt.*;
import java.util.ArrayList;

public class Game {
    private final int maxPoints = 1000;
    private int turn;
    public static Map currentMap;
    public static void main(String [ ] args)
    {
        //Here is where stuff goes that makes the game work
        currentMap = new Map();
        Station node1 = new Station(1,"One",new Point(1,1));
        ArrayList<Node> testList = new ArrayList<Node>();
        testList.add(node1);
        Route currentRoute = new Route(testList);
        System.out.println(currentRoute.toString());
    }
    public void endGame() {
    }

    public void endTurn() {
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}