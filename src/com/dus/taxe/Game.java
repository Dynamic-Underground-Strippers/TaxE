package com.dus.taxe;

import com.dus.taxe.gui.GUI;
import com.dus.taxe.gui.GuiController;

import java.util.ArrayList;

public class Game {
    private static final int maxPoints = 1000;
    private static int turn;
    public static Map currentMap;
    private static Player currentPlayer;
    private static Player otherPlayer;

    public Game(Map currentMap){
        this.turn = 0;
        this.currentMap = currentMap;
    }

    public static void main(String [ ] args)
    {
        GuiController.init(new GUI());
        GuiController.setMap(new Map());
    }

    public static void endGame() {
        System.out.println("Congratulations " + currentPlayer.getName());
    }

    private static void swapPlayers(){
        Player temp;
        temp = currentPlayer;
        currentPlayer = otherPlayer;
        otherPlayer = temp;
    }
    public static void newTurn(){
        currentPlayer.addGoal(currentMap.getRandomGoal());
        currentPlayer.giveRandomEngine();
        currentPlayer.giveRandomUpgrade();
    }
    public static void endTurn() {
        //TODO: Make trains move on their route
        currentPlayer.completeGoals();
        if (currentPlayer.getPoints() >= maxPoints){
            endGame();
        }
        swapPlayers();
        turn += 1;
    }

    public int getTurn() {
        return this.turn;
    }
}