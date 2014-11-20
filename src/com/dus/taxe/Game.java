package com.dus.taxe;

import java.awt.*;
import java.util.ArrayList;

public class Game {
    private static final int maxPoints = 1000;
    private static int turn;
    public static Map currentMap;
    private static Player currentPlayer;
    private static Player otherPlayer;

    public static void main(String [ ] args)
    {
        //Player presses start game button
        //Game asks for player's names
       /* currentPlayer = new Player(GetNameFromGUIInput);
        otherPlayer = new Player(GetNameFromGUIInput);*/
        //Loads map from storage of different maps
        //Game starts, enters playing loop
        while ((currentPlayer.getPoints() < maxPoints) && (otherPlayer.getPoints() < maxPoints)){
            //While the players' have less than the max score then the game keeps playing
            //Need some kind of timer here to time the turn
            //TODO:
            endTurn();
        }

        endGame();
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

    public static void endTurn() {
        swapPlayers();
        turn += 1;
    }

    public int getTurn() {
        return this.turn;
    }
    private Map chooseRandomMap(){
        //Random map is found in this subroutine
        return null;
    }
}