package com.ooad.chess;

public class Board {

	Spot[][] boxes;

	public Board() {
		this.resetBoard();
	}

	public Spot getBox(int x, int y) throws Exception {

		if (x < 0 || x > 7 || y < 0 || y > 7) {
			throw new Exception("Index out of bound");
		}

		return boxes[x][y];
	}

	public void resetBoard() {
		// initialize white pieces
		boxes[0][0] = new Spot(0, 0, new Rook(true, true));
		boxes[0][1] = new Spot(0, 1, new Knight(true, true));
		boxes[0][2] = new Spot(0, 2, new Bishop(true, true));
		// ...
		boxes[1][0] = new Spot(1, 0, new Pawn(true, true));
		boxes[1][1] = new Spot(1, 1, new Pawn(true, true));
		// ...

		// initialize black pieces
		boxes[7][0] = new Spot(7, 0, new Rook(true, false));
		boxes[7][1] = new Spot(7, 1, new Knight(true, false));
		boxes[7][2] = new Spot(7, 2, new Bishop(true, false));
		// ...
		boxes[6][0] = new Spot(6, 0, new Pawn(true, false));
		boxes[6][1] = new Spot(6, 1, new Pawn(true, false));
		// ...

		// initialize remaining boxes without any piece
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 8; j++) {
				boxes[i][j] = new Spot(i, j, null);
			}
		}
	}

}
