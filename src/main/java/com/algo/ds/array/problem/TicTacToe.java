package com.algo.ds.array.problem;

public class TicTacToe {

	private static final int X = 1, O = -1, EMPTY = 0;
	private int[][] game = new int[3][3];
	private int player;

	public TicTacToe() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				game[i][j] = EMPTY;
			}
		}
		player = X;
	}

	public void putMark(int i, int j) {
		if (i < 0 || i > 2 || j < 0 || j > 2) {
			throw new IllegalArgumentException("invalid input");
		}
		if (game[i][j] != EMPTY) {
			throw new IllegalArgumentException("game is full");
		}
		game[i][j] = player; // O is -X
		player = -player; // once insert is done then next player gets the
							// chance
	}

	public boolean isWin(int mark) {
		return (game[0][0] + game[0][1] + game[0][2] == mark * 3) || (game[1][0] + game[1][1] + game[1][2] == mark * 3)
				|| (game[2][0] + game[2][1] + game[2][2] == mark * 3)
				|| (game[0][0] + game[1][0] + game[2][0] == mark * 3)
				|| (game[0][1] + game[2][1] + game[2][2] == mark * 3)
				|| (game[0][2] + game[1][2] + game[2][2] == mark * 3)
				|| (game[0][0] + game[1][1] + game[2][2] == mark * 3)
				|| (game[0][2] + game[1][1] + game[2][0] == mark * 3);
	}

	public int getWinner() {
		if (isWin(X))
			return X;
		if (isWin(O))
			return O;
		else
			return 0;
	}

}
