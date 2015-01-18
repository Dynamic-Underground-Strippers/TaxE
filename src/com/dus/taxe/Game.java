package com.dus.taxe;

import com.dus.taxe.gui.GUI;

import javax.swing.JOptionPane;

public class Game {
	private static final int maxPoints = 1000;
	private static int turn;
	public static Map currentMap;
	private static Player currentPlayer;
	private static Player otherPlayer;

	public Game(Map currentMap) {
		turn = 0;
		Game.currentMap = currentMap;
	}

	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	public static void main(String[] args) {
		String s;
		if ((s = JOptionPane.showInputDialog("Enter player 1's name:")) == null) {
			currentPlayer = new Player("Player 1");
		} else {
			currentPlayer = new Player(s);
		}
		if ((s = JOptionPane.showInputDialog("Enter player 2's name:")) == null) {
			otherPlayer = new Player("Player 2");
		} else {
			otherPlayer = new Player(s);
		}
		new GUI(new Map());
	}

	public static void endGame() {
		System.out.println("Congratulations " + currentPlayer.getName());
	}

	private static void swapPlayers() {
		Player temp;
		temp = currentPlayer;
		currentPlayer = otherPlayer;
		otherPlayer = temp;
	}

	public static void newTurn() {
		currentPlayer.addGoal(currentMap.getRandomGoal());
		// Need to somehow add in GUI validation here
		currentPlayer.giveRandomEngine();
		currentPlayer.giveRandomUpgrade();
	}

	public static void endTurn() {
		currentPlayer.moveTrains();
		//This will instantly move their trains, may want to have some kind of animation?
		currentPlayer.completeGoals();
		if (currentPlayer.getPoints() >= maxPoints) {
			endGame();
		}
		swapPlayers();
		turn += 1;
	}

	public int getTurn() {
		return this.turn;
	}
}