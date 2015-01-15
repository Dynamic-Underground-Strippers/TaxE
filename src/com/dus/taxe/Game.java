package com.dus.taxe;

import com.dus.taxe.gui.GUI;
import com.dus.taxe.gui.GuiController;

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
        currentPlayer = new Player("Test Player 1");
        //Player presses start game button
        //Game asks for player's names
       /* currentPlayer = new Player(GetNameFromGUIInput);
        otherPlayer = new Player(GetNameFromGUIInput);*/
        //Loads map from storage of different maps
        //Game starts, enters playing loop
//        while ((currentPlayer.getPoints() < maxPoints) && (otherPlayer.getPoints() < maxPoints)){
            //While the players' have less than the max score then the game keeps playing
            //Need some kind of timer here to time the turn
            //TODO: Give player a goal
            //TODO: Give player 2 resources: System to include assign resources randomly based on weightings
            //TODO: Work out how the hell we are going to store all possible resources and goals
//            endTurn();
//        }

//        endGame();
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
        //TODO: Make trains move on their route
        currentPlayer.completeGoals();
        swapPlayers();
        turn += 1;
    }

    public int getTurn() {
        return this.turn;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
}