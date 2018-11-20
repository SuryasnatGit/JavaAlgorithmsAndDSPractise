package com.algo.backtracking;

import java.util.ArrayList;

/**
 * If all squares are visited print the solution Else a) Add one of the next moves to solution
 * vector and recursively check if this move leads to a solution. (A Knight can make maximum eight
 * moves. We choose one of the 8 moves in this step). b) If the move chosen in the above step
 * doesn't lead to a solution then remove this move from the solution vector and try other
 * alternative moves. c) If none of the alternatives work then return false (Returning false will
 * remove the previously added item in recursion and if false is returned by the initial call of
 * recursion then "no solution exists" )
 * 
 * @author surya
 *
 */
public class KnightsTour {

	private int N = 8;

	/**
	 * This function solves the Knight Tour problem using Backtracking. This function mainly uses
	 * solveKTUtil() to solve the problem. It returns false if no complete tour is possible, otherwise
	 * return true and prints the tour. Please note that there may be more than one solutions, this
	 * function prints one of the feasible solutions.
	 */
	public boolean solveKnightsTour() {
		int[][] sol = new int[8][8];

		// initialize sol
		initialize(sol);

		// initial position of knight
		sol[0][0] = 0;

		/*
		 * xMove[] and yMove[] define next move of Knight. xMove[] is for next value of x coordinate yMove[]
		 * is for next value of y coordinate. Note -- this pattern has to be correct or program will not
		 * terminate
		 */
		int xMove[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
		int yMove[] = { 1, 2, 2, 1, -1, -2, -2, -1 };

		if (solveKnightsTourUtil(0, 0, 1, sol, xMove, yMove)) {
			printTour(sol);
		} else {
			System.out.println("no solution exist");
			return false;
		}
		return true;
	}

	private void initialize(int[][] sol) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sol[i][j] = -1;
			}
		}
	}

	// recursive function
	private boolean solveKnightsTourUtil(int xPos, int yPos, int moveCount, int[][] sol, int[] xMove, int[] yMove) {
		// exit criteria
		if (moveCount == N * N)
			return true;
		for (int i = 0; i < N; i++) {
			int nextX = xPos + xMove[i];
			int nextY = yPos + yMove[i];
			if (isTourSafe(nextX, nextY, sol)) {
				sol[nextX][nextY] = moveCount;
				if (solveKnightsTourUtil(nextX, nextY, moveCount + 1, sol, xMove, yMove))
					return true;
				else
					sol[nextX][nextY] = -1; // backtracking
			}
		}
		return false;
	}

	private boolean isTourSafe(int nextX, int nextY, int[][] sol) {
		return (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && sol[nextX][nextY] == -1);
	}

	private void printTour(int[][] sol) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(sol[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * This class demonstrates the knights tour on a NxN sized board.
	 * 
	 * It uses H C Warnsdorff's technique which includes the following algorithm:
	 * 
	 * 1. Mark squares when you land on them.
	 * 
	 * 2. To decide where to land next, look at all the possible next unmarked moves. Rank each
	 * possibility by the number of possible next moves from that location. Move to the location with
	 * the lowest rank. In a tie, any of the lowest scoring locations will work.
	 * 
	 * 
	 */
	// A 1 on the board represents a visited location
	private static final int VISITED = 1;

	// a 0 represents all other locations on the board
	private static final int UNVISITED_LOCATION = 0;

	private static final int SIZE = 20;

	private int[][] board = new int[SIZE][SIZE];

	/**
	 * This method tests the Knights tour by starting the first knight at 0,0 Which also could be
	 * started from any point.
	 */
	private void test() {

		// initialize all squares to 0 representing a
		// board with all squares unvisited.
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; i < SIZE; i++) {
				board[i][j] = UNVISITED_LOCATION;
			}
		}

		// call nextMove to start the tour, starting at the first 0,0 position
		// starting position could also be randomly generated...
		nextMove(0, 0);
	}

	/**
	 * This method begins and produces the tour. First row and column start at 0, and go to 7 (for 8x8
	 * board)
	 * 
	 * @param board
	 *            This is the board for the knights tour
	 * @param row
	 *            The row of the knights current location
	 * @param column
	 *            The column of the knights current location
	 * @return true if the tour is complete, false otherwise
	 * 
	 *         In: board - The chess board row - The knights current row location column - The knights
	 *         current column location Out: True if the tour is complete, no more moves to go to false
	 *         otherwise, continue... Pre: A valid row and column, and board that has unvisited
	 *         locations Post: The knight will have moved and the method will be called recursively
	 *         again with the new row and column location.
	 */
	public boolean nextMove(int row, int column) {

		// This spot has been landed on. Mark it
		board[row][column] = VISITED;

		// search for all next possible valid moves
		ArrayList<Location> nextMovePossibilities = possibleMoves(row, column);

		// no more spots to go to. tour is done
		if (nextMovePossibilities.size() == 0) {
			printTour(board);
			System.out.println("Tour complete");
		} else {

			// Find the lowest scoring move

			Location nextMove = findBest(nextMovePossibilities);
			// move there
			int nextRow = nextMove.getRow();
			int nextColumn = nextMove.getColumn();

			// we moved. display the board again
			printTour(board);
			System.out.println();
			System.out.println();

			return nextMove(nextRow, nextColumn);
		}

		return true;
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
	private ArrayList<Location> possibleMoves(int row, int column) {

		// this arraylist will hold all of the validmoves
		ArrayList<Location> validMoves = new ArrayList<Location>();

		// Each location has up to eight possible valid moves:
		// can either move up or down two spots and left or right one spot
		// or left or right two spots and up or down one spot

		// check up two, right one
		if (isValidMove(new Location(row + 2, column + 1))) {
			validMoves.add(new Location(row + 2, column + 1));
		}

		// check up two, left one
		if (isValidMove(new Location(row + 2, column - 1))) {
			validMoves.add(new Location(row + 2, column - 1));
		}

		// check down two, right one
		if (isValidMove(new Location(row - 2, column + 1))) {
			validMoves.add(new Location(row - 2, column + 1));
		}

		// check down two left one
		if (isValidMove(new Location(row - 2, column - 1))) {
			validMoves.add(new Location(row - 2, column - 1));
		}

		// check up one, right two
		if (isValidMove(new Location(row + 1, column + 2))) {
			validMoves.add(new Location(row + 1, column + 2));
		}

		// check up one, left two
		if (isValidMove(new Location(row + 1, column - 2))) {
			validMoves.add(new Location(row + 1, column - 2));
		}

		// check down one, right two
		if (isValidMove(new Location(row - 1, column + 2))) {
			validMoves.add(new Location(row - 1, column + 2));
		}

		// check down one, left two
		if (isValidMove(new Location(row - 1, column - 2))) {
			validMoves.add(new Location(row - 1, column - 2));
		}

		return validMoves;
	}

	/**
	 * This method will find the best location given an array of locations and return it.
	 * 
	 * @param Locations
	 *            All of the possible locations
	 * @return The best location to move to, the one with the least amount of possible next moves
	 */
	private Location findBest(ArrayList<Location> locations) {
		Location bestMove = null;

		// Best move score cannot be greater than 8
		// The max amount of moves from any location is 8
		// The first location will become the best move
		// It will then be adjusted if better one is found
		int bestMoveScore = 1000;

		// Check all of the possible moves
		for (Location location : locations) {

			// get the possible moves and store them in a list. Use this list
			// to determine how many moves are possible from the location. Its
			// size is the number of moves.
			ArrayList<Location> movesFromThisPos = possibleMoves(location.getRow(), location.getColumn());

			// There is a better move, change the value of bestMove and bestMoveScore
			if (movesFromThisPos.size() < bestMoveScore) {
				bestMove = new Location(location.getRow(), location.getColumn());
				bestMoveScore = movesFromThisPos.size();
			}
		}

		return bestMove;
	}

	/**
	 * This method checks to see if the location is valid
	 * 
	 * In: The location to check validity Out: True if the location can be moved to Pre: None Post:None
	 */
	private boolean isValidMove(Location loc) {

		// position is out of the range of the board
		if (loc.getRow() >= SIZE || loc.getColumn() >= SIZE) {
			return false;
		}

		// position is out of the range of the board
		if (loc.getRow() < 0 || loc.getColumn() < 0) {
			return false;
		}

		// the location has already been visited
		if (board[loc.getRow()][loc.getColumn()] == VISITED) {
			return false;
		}

		// valid
		return true;
	}

	class Location {

		// The board location's row
		private int row = 0;

		// The board location's column
		private int column = 0;

		// Constructor, setting the locations row and column
		public Location(int row, int column) {
			this.row = row;
			this.column = column;
		}

		// Returns this.row
		public int getRow() {
			return row;
		}

		// Returns this.column
		public int getColumn() {
			return column;
		}

		// Not used, but can set the row.
		public void setRow(int row) {
			this.row = row;
		}

		// Not used, but can set the column
		public void setColumn(int column) {
			this.column = column;
		}
	}

	public static void main(String[] args) {
		KnightsTour kt = new KnightsTour();
		kt.solveKnightsTour();

	}

}
