package com.dus.taxe;

import com.dus.taxe.gui.GUI;

import javax.swing.JOptionPane;

/**
 * Main class for the game
 */
public class Game {
	public static Map currentMap;
	private static Player currentPlayer;
	private static Player otherPlayer;
	private static int turn;

	/**
	 * Ends the game
	 */
	private static void endGame() {
		Player winner;
		if (currentPlayer.getPoints()>otherPlayer.getPoints()){
			winner = currentPlayer;
		} else if (currentPlayer.getPoints()<otherPlayer.getPoints()){
			winner = otherPlayer;
		} else{
			winner = null;
		}
		if (winner == null){
			JOptionPane.showMessageDialog(null, "Game Over! It's a tie!" , "Game Over! Tie!", JOptionPane.PLAIN_MESSAGE);

		} else{
			JOptionPane.showMessageDialog(null, "Congratulations " + winner.getName() + "! You are the winner!" , "Game Over! " + winner.getName()+ " wins!", JOptionPane.PLAIN_MESSAGE);
		}
		System.exit(0);
	}

	/**
	 * Ends the current turn
	 */
	public static void endTurn() {
		currentPlayer.moveTrains();
		//This will instantly move their trains, may want to have some kind of animation?
		currentPlayer.completeGoals();
		if (turn==19) {
			endGame();
		}else {
			swapPlayers();
			turn += 1;
		}
	}

	/**
	 * @return the {@link com.dus.taxe.Player} whose turn it currently is
	 */
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @return the {@link com.dus.taxe.Player} whose turn it currently is not
	 */
	public static Player getOtherPlayer() {
		return otherPlayer;
	}

	/**
	 * @return the current turn number of the game
	 */
	public static int getTurn() {
		return turn;
	}

	public static void main(String[] args) {
		turn = 0;
		currentMap=new Map();
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
		new GUI(currentMap).setPlayer(currentPlayer);
	}

	/**
	 * Starts a new turn
	 */
	public static void newTurn() {
		//if statement checks whether it is player 2's first turn or not. This was necessary as player 2 was getting a turn advantage.
		currentPlayer.displayMessages();
		if (turn!=1) {
			currentPlayer.addTrains();
			currentPlayer.addGoal();
			// Need to somehow add in GUI validation here
			currentPlayer.giveRandomEngine();
			currentPlayer.giveRandomUpgrade();
		}
	}

	/**
	 * Swaps {@link #currentPlayer} with {@link #otherPlayer}
	 */
	private static void swapPlayers() {
		Player temp;
		temp = currentPlayer;
		currentPlayer = otherPlayer;
		otherPlayer = temp;
		GUI.self.setPlayer(currentPlayer);
	}
}