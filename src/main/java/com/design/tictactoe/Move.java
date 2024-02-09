package com.ooad.tictactoe;

/**
 * The Move class is applied to mark the location of the symbol by the row-column method. The
 * variables and methods in Move class are described as follows:
 * 
 * row: integer variable, represents the row number of the Tic-Tac-Toe board;
 * 
 * col: integer variable, represents the column number of the Tic-Tac-Toe board;
 * 
 * Move (r:int, c:int): constructor, according to the two integer parameters r and c, this method
 * sets the location of the symbol on Tic-Tac-Toe board.
 * 
 * 
 * @author surya
 *
 */
public class Move {

	private int row;
	private int column;

	public Move(int r, int c) {
		this.row = r;
		this.column = c;
	}

}
