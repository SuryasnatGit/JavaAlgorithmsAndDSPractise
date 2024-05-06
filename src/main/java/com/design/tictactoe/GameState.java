package com.design.tictactoe;

import java.util.List;

/**
 * The GameState class contains all the information about the current state of a Tic Tac Toe game. The squares array
 * contains a character value for each square in the Tic Tac Toe board. An empty square is represented by a space
 * character. The player array contains the Players, who each have their own specified symbol which is used to fill the
 * squares array. The playerTurn integer is an index which corresponds to the player in the player array whose turn it
 * is. The constructor creates an empty Tic Tac Toe board, both Players, and initializes the playerTurn index to zero.
 * The reseatBoard method returns the board to an empty state and resets the playerTurn to zero.
 * 
 * 
 * @author surya
 *
 */
public class GameState {

	private List<Player> players;
	private char[][] squares;
	private int playerTurn;

	public GameState() {
		// TODO Auto-generated constructor stub
	}

	public void resetBoard() {

	}

	public void doMove(Move move) {

	}

	public void undoMove() {

	}

	public List<Move> getMoves() {
		return null;
	}

	public Move getRandomMove() {
		return null;
	}

	public void setSquare(int row, int column, char ch) {
		squares[row][column] = ch;
	}

	public char getSquare(int row, int column) {
		return squares[row][column];
	}

	public Player getPlayer(int i) {
		return players.get(i);
	}

	public Player getPlayer(char ch) {
		return null;
	}

	public Player getPlayetTurn() {
		return null;
	}

	public void incrementPlayerTurn() {

	}

	public void decrementPlayerTurn() {

	}

	public boolean isTie() {
		return false;
	}

	public Player getWInner() {
		return null;
	}

}
