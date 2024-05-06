package com.design.chess;

public class King extends Piece {

	public King(boolean available, boolean color) {
		super(available, color);
	}

	@Override
	public boolean canMove(Board board, Spot start, Spot end) {
		// can't move the king to a spot which has piece of same color
		if (end.getPiece().isWhite() == this.isWhite())
			return false;

		int x = Math.abs(start.getX() - end.getX());
		int y = Math.abs(start.getY() - end.getY());
		// check if this move will not result in the king being attacked.
		if (x + y == 1)
			return true;

		return false;
	}

}
