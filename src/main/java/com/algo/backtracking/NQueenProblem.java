package com.algo.backtracking;

/**
 * The N Queen is the problem of placing N chess queens on an NN chessboard so that no two queens
 * attack each other
 * 
 * Backtracking Algorithm The idea is to place queens one by one in different columns, starting from
 * the leftmost column. When we place a queen in a column, we check for clashes with already placed
 * queens. In the current column, if we find a row for which there is no clash, we mark this row and
 * column as part of the solution. If we do not find such a row due to clashes then we backtrack and
 * return false.
 * 
 * 1) Start in the leftmost column
 * 
 * 2) If all queens are placed return true
 * 
 * 3) Try all rows in the current column. Do following for every tried row. a) If the queen can be
 * placed safely in this row then mark this [row, column] as part of the solution and recursively
 * check if placing queen here leads to a solution. b) If placing the queen in [row, column] leads
 * to a solution then return true. c) If placing queen doesn't lead to a solution then umark this
 * [row, column] (Backtrack) and go to step (a) to try other rows. 3) If all rows have been tried
 * and nothing worked, return false to trigger backtracking.
 * 
 * @author surya
 *
 */
public class NQueenProblem {

	private int N = 4;

	public boolean nQueens() {
		int[][] maze = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
		if (nQueensUtil(maze, 0)) {
			printMaze(maze);
			return true;
		}
		System.out.println("no proper arrangement found");
		return false;
	}

	private boolean nQueensUtil(int[][] maze, int col) {
		// base case: if all queens are placed then return true
		if (col >= N)
			return true;

		// consider this column and try placing the queens in all rows one by one
		for (int i = 0; i < N; i++) {
			if (isSafe(maze, i, col)) {
				maze[i][col] = 1; // place the queen
				// recur to place other queens
				if (nQueensUtil(maze, col + 1))
					return true;

				// if placing queen in maze[i][col] does not lead to a solution the reverse by backtracking
				maze[i][col] = 0;
			}
		}
		// if queen cannot be found in any row of this column then return false
		return false;
	}

	/*
	 * A utility function to check if a queen can be placed on board[row][col]. Note that this function
	 * is called when "col" queens are already placeed in columns from 0 to col -1. So we need to check
	 * only left side for attacking queens
	 */
	private boolean isSafe(int board[][], int row, int col) {
		int i, j;

		/* Check this row on left side */
		for (i = 0; i < col; i++)
			if (board[row][i] == 1)
				return false;

		/* Check upper diagonal on left side */
		for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
			if (board[i][j] == 1)
				return false;

		/* Check lower diagonal on left side */
		for (i = row, j = col; j >= 0 && i < N; i++, j--)
			if (board[i][j] == 1)
				return false;

		return true;
	}

	private void printMaze(int[][] maze) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		NQueenProblem q = new NQueenProblem();
		System.out.println(q.nQueens());
	}

}
