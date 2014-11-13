package com.dus.taxe;

public class Game {
    private final int maxPoints = 1000;
    private int turn;

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