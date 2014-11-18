package com.dus.taxe;

public class Game {
    private final int maxPoints = 1000;
    private int turn;
    public static Map currentMap;
    public static void main(String [ ] args)
    {
        //Here is where stuff goes that makes the game work
        currentMap = new Map();
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