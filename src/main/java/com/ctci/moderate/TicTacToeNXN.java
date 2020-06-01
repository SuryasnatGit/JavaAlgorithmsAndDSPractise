package com.ctci.moderate;

/**
 * Design an algorithm to figure out if someone has won a game of tic-tac-toe.
 * 
 * CTCI : 16.4
 * 
 * TODO: to implement
 */
public class TicTacToeNXN {

	enum Piece {
		Empty, RED, BLUE
	}

	public int convertBoardToInt(Piece[][] board) {
		int sum = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				int val = board[i][j].ordinal();
				sum = sum * 3 + val;
			}
		}
		return sum;
	}

	// SOlution 1
	public static Piece hasWon(Piece[][] board) {
		int size = board.length;
		if (board[0].length != size)
			return Piece.Empty;
		Piece first;

		/* Check rows. */
		for (int i = 0; i < size; i++) {
			first = board[i][0];
			if (first == Piece.Empty)
				continue;
			for (int j = 1; j < size; j++) {
				if (board[i][j] != first) {
					break;
				} else if (j == size - 1) {
					return first;
				}
			}
		}

		/* Check columns. */
		for (int i = 0; i < size; i++) {
			first = board[0][i];
			if (first == Piece.Empty)
				continue;
			for (int j = 1; j < size; j++) {
				if (board[j][i] != first) {
					break;
				} else if (j == size - 1) {
					return first;
				}
			}
		}

		/* Check diagonals. */
		first = board[0][0];
		if (first != Piece.Empty) {
			for (int i = 1; i < size; i++) {
				if (board[i][i] != first) {
					break;
				} else if (i == size - 1) {
					return first;
				}
			}
		}

		first = board[0][size - 1];
		if (first != Piece.Empty) {
			for (int i = 1; i < size; i++) {
				if (board[i][size - i - 1] != first) {
					break;
				} else if (i == size - 1) {
					return first;
				}
			}
		}

		return Piece.Empty;
	}

	// Solution 2
	public static Piece hasWon1(Piece[][] board) {
		Piece winner = Piece.Empty;

		/* Check rows. */
		for (int i = 0; i < board.length; i++) {
			winner = hasWon(board, i, 0, 0, 1);
			if (winner != Piece.Empty) {
				return winner;
			}
		}

		/* Check columns. */
		for (int i = 0; i < board[0].length; i++) {
			winner = hasWon(board, 0, i, 1, 0);
			if (winner != Piece.Empty) {
				return winner;
			}
		}

		/* Check top/left -> bottom/right diagonal. */
		winner = hasWon(board, 0, 0, 1, 1);
		if (winner != Piece.Empty) {
			return winner;
		}

		/* Check top/right -> bottom/left diagonal. */
		return hasWon(board, 0, board[0].length - 1, 1, -1);
	}

	public static Piece hasWon(Piece[][] board, int row, int col, int incrementRow, int incrementCol) {
		Piece first = board[row][col];
		while (row < board.length && col < board[row].length) {
			if (board[row][col] != first) {
				return Piece.Empty;
			}
			row += incrementRow;
			col += incrementCol;
		}
		return first;
	}

	// Solution 3
	public static Piece hasWon(Piece[][] board, int row, int column) {
		if (board.length != board[0].length)
			return Piece.Empty;

		Piece piece = board[row][column];

		if (piece == Piece.Empty)
			return Piece.Empty;
		if (hasWonRow(board, row) || hasWonColumn(board, column)) {
			return piece;
		}

		if (row == column && hasWonDiagonal(board, 1)) {
			return piece;
		}

		if (row == (board.length - column - 1) && hasWonDiagonal(board, -1)) {
			return piece;
		}

		return Piece.Empty;
	}

	public static boolean hasWonRow(Piece[][] board, int row) {
		for (int c = 1; c < board[row].length; c++) {
			if (board[row][c] != board[row][0]) {
				return false;
			}
		}
		return true;
	}

	public static boolean hasWonColumn(Piece[][] board, int column) {
		for (int r = 1; r < board.length; r++) {
			if (board[r][column] != board[0][column]) {
				return false;
			}
		}
		return true;
	}

	public static boolean hasWonDiagonal(Piece[][] board, int direction) {
		int row = 0;
		int column = direction == 1 ? 0 : board.length - 1;
		Piece first = board[0][column];
		for (int i = 0; i < board.length; i++) {
			if (board[row][column] != first) {
				return false;
			}
			row += 1;
			column += direction;
		}
		return true;
	}

}
