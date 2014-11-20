package com.dus.taxe;

import java.awt.*;
import java.util.ArrayList;

public class Game {
    private final int maxPoints = 1000;
    private int turn;
    public static Map currentMap;
    private static Player currentPlayer;
    private static Player otherPlayer;

    public static void main(String [ ] args)
    {
        //Here is where stuff goes that makes the game work
        currentMap = new Map();
        Station node1 = new Station(1,"One",new Point(1,1));
        Junction node2 = new Junction(2,"Two", new Point(2,2));
        ArrayList<Node> testList = new ArrayList<Node>();
        testList.add(node1);
        testList.add(node2);
        Route currentRoute = new Route(testList);
        System.out.println(currentRoute.toString());
    }

    public void endGame() {

    }
    private void swapPlayers(){
        Player temp;
        temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
    }

    public void endTurn() {
        swapPlayers();
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public int getTurn() {
        return turn;
    }

}