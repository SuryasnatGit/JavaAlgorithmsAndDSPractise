package com.algo.backtracking;

/**
 * Given an incomplete Sudoku configuration in terms of a 9x9 2-D square matrix
 * (mat[][]) the task to print a solution of the Sudoku. For simplicity you may
 * assume that there will be only one unique solution
 * 
 * Recursively fill the empty cell with available number that make the sudouku
 * board valid, if all numbers were not valid in this cell, backtrack to last
 * cell and iteratively try next number until all cells are filled with number.
 * 
 * This is a kind of standard solution for all recursion problem.
 * 
 * 1.Find the base case or terminating case.Usually was written in the fist line
 * of the recursion function(if(...) ...return)
 * 
 * 2. Iteratively try valid one and go to next cell.
 * 
 * 3. if the current trial doesn't work, reset the current trial to original
 * value and try next possibility.
 * 
 * So the code always looks like : setValue(i) / addItem(i); helper(...,i+1);
 * setValue(original)/removeItem(i);
 * 
 * @author M_402201
 *
 */
public class SudokuSolver {

	public void solveSudoku(char[][] board) {
		if (board == null || board.length == 0 || board[0].length == 0)
			return;
		boolean[][] row = new boolean[9][9];
		boolean[][] col = new boolean[9][9];
		boolean[][] block = new boolean[9][9];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] <= '9' && board[i][j] >= '1') {
					int num = board[i][j] - '1';
					int bnum = (i / 3) * 3 + j / 3;
					if (row[i][num] || col[j][num] || block[bnum][num])
						return;
					row[i][num] = col[j][num] = block[bnum][num] = true;
				}
			}
		}
		helper(board, 0, row, col, block);
	}

	public boolean helper(char[][] board, int ind, boolean[][] row, boolean[][] col, boolean[][] block) {
		if (ind == 81)
			return true;
		int i = ind / 9;
		int j = ind % 9;
		int b = (i / 3) * 3 + j / 3;
		if (board[i][j] == '.') {
			for (int k = 1; k <= 9; k++) {
				if ((!row[i][k - 1]) && (!col[j][k - 1]) && (!block[b][k - 1])) {
					board[i][j] = (char) ('0' + k);
					row[i][k - 1] = col[j][k - 1] = block[b][k - 1] = true;
					if (helper(board, ind + 1, row, col, block))
						return true;
					else {
						row[i][k - 1] = col[j][k - 1] = block[b][k - 1] = false;
						board[i][j] = '.';
					}
				}
			}
			return false;
		} else
			return helper(board, ind + 1, row, col, block);
	}

}
