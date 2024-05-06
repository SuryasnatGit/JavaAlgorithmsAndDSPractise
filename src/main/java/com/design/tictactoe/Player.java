package com.design.tictactoe;

/**
 * The Player class is applied to manage the player’s information. The number of wins and symbol of
 * the player should be got by the methods in the Player class. The variables and methods in Player
 * class are described as follows:
 * 
 * wins: integer variable, represents the number of wins;
 * 
 * symbol: char variable, represents the symbol that is used in the board by the player;
 * 
 * Player (symbol: char): constructor, creates a new player, defines the new player’s symbol and
 * initializes the number of wins is equal to zero;
 * 
 * getWins(): obtains the number of wins;
 * 
 * addWins(): the number of wins is increased 1;
 * 
 * getSymbol(): obtains the symbol of the player.
 * 
 * 
 * @author surya
 *
 */
public class Player {

	private int wins;
	private char symbol;

	public Player(char s) {
		this.symbol = s;
	}

	public int getWins() {
		return wins;
	}

	public void addWin(int w) {
		wins += w;
	}

	public char getSymbol() {
		return symbol;
	}
}
