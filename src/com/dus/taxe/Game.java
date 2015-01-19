package com.dus.taxe;

import com.dus.taxe.gui.GUI;

import javax.swing.JOptionPane;

public class Game {
	private static final int maxPoints = 1000;
	public static Map currentMap;
	private static Player currentPlayer;
	private static Player otherPlayer;
	private static int turn;

	public Game(Map currentMap) {
		turn = 0;
		Game.currentMap = currentMap;
	}

	public static void endGame() {
		System.out.println("Congratulations " + currentPlayer.getName());
	}

	public static void endTurn() {
		currentPlayer.moveTrains();
		//This will instantly move their trains, may want to have some kind of animation?
		currentPlayer.completeGoals();
		if (turn==20) {
			endGame();
		}
		swapPlayers();
		turn += 1;
	}

	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	public static void main(String[] args) {
		Map m = new Map();
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
		new GUI(m).setPlayer(currentPlayer);
	}

	public static void newTurn() {
		currentPlayer.addGoal();
		// Need to somehow add in GUI validation here
		currentPlayer.giveRandomEngine();
		currentPlayer.giveRandomUpgrade();
	}

	private static void swapPlayers() {
		Player temp;
		temp = currentPlayer;
		currentPlayer = otherPlayer;
		otherPlayer = temp;
		GUI.self.setPlayer(currentPlayer);
	}

	public int getTurn() {
		return this.turn;
	}
}