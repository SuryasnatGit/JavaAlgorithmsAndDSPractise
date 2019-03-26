package com.algo.backtracking;

/**
 * How many ways are there to place a black and a white knight on an N * M chessboard such that they
 * do not attack each other? The knights have to be placed on different squares. A knight can move
 * two squares horizontally and one square vertically, or two squares vertically and one square
 * horizontally. The knights attack each other if one can reach the other in one move.
 * 
 * Input:
 * 
 * The first line contains the number of test cases T. Each of the next T lines contains two
 * integers N and M.
 * 
 * Output:
 * 
 * Output T lines, one for each test case, each containing the required answer for the corresponding
 * test case.
 * 
 * 
 * Constraints:
 * 
 * 1 <= T <= 100
 * 
 * 1 <= N,M <= 100000
 * 
 * 
 * Example:
 * 
 * Input:
 * 
 * 3
 * 
 * 2 2
 * 
 * 2 3
 * 
 * 4 5
 * 
 * 
 * Output:
 * 
 * 12
 * 
 * 26
 * 
 * 312
 * 
 * @author surya
 *
 */
public class BlackAndWhiteKnights {

	// A 1 on the board represents a visited location
	private static final int VISITED = 1;

	// a 0 represents all other locations on the board
	private static final int UNVISITED_LOCATION = 0;

	private int[][] board;

	private int rows = 0;
	private int columns = 0;

	public int getNoAttackCount(int rowSize, int columnSize) {
		this.rows = rowSize;
		this.columns = columnSize;

		initializeBoard();

		int count = 0;
		int totalPlaces = rows * columns;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				count += (totalPlaces - possibleMoves(i, j) - 1);// possible moves for black, then -1 due to presence of
																	// black on board
			}
		}
		return count;
	}

	private void initializeBoard() {
		board = new int[rows][columns];
		// initialize all squares to 0 representing a
		// board with all squares unvisited.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				board[i][j] = UNVISITED_LOCATION;
			}
		}
	}

	/**
	 * Given a row and column, this method will return all possible next moves.
	 * 
	 * @param row
	 *            The row to check
	 * @param column
	 *            the column to check
	 * @return All possible next moves from location: row,column
	 */
	private int possibleMoves(int row, int column) {

		int count = 0;

		// Each location has up to eight possible valid moves:
		// can either move up or down two spots and left or right one spot
		// or left or right two spots and up or down one spot

		// check up two, right one
		if (isValidMove(row + 2, column + 1)) {
			count++;
		}

		// check up two, left one
		if (isValidMove(row + 2, column - 1)) {
			count++;
		}

		// check down two, right one
		if (isValidMove(row - 2, column + 1)) {
			count++;
		}

		// check down two left one
		if (isValidMove(row - 2, column - 1)) {
			count++;
		}

		// check up one, right two
		if (isValidMove(row + 1, column + 2)) {
			count++;
		}

		// check up one, left two
		if (isValidMove(row + 1, column - 2)) {
			count++;
		}

		// check down one, right two
		if (isValidMove(row - 1, column + 2)) {
			count++;
		}

		// check down one, left two
		if (isValidMove(row - 1, column - 2)) {
			count++;
		}

		return count;
	}

	/**
	 * This method checks to see if the location is valid
	 * 
	 * In: The location to check validity Out: True if the location can be moved to Pre: None Post:None
	 */
	private boolean isValidMove(int row, int column) {

		// position is out of the range of the board
		if (row >= rows || row < 0 || column >= columns || column < 0) {
			return false;
		}

		// the location has already been visited
		if (board[row][column] == VISITED) {
			return false;
		}

		// valid
		return true;
	}

	public static void main(String[] args) {
		BlackAndWhiteKnights bw = new BlackAndWhiteKnights();
		System.out.println("For size[2][2] result is :" + bw.getNoAttackCount(2, 2));
		System.out.println("For size[2][3] result is :" + bw.getNoAttackCount(2, 3));
		System.out.println("For size[4][5] result is :" + bw.getNoAttackCount(4, 5));
	}
}
