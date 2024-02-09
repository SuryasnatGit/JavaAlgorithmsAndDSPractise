package com.ooad.chess;

/**
 * The basic building block of the system, every piece will be placed on a spot.
 * Piece class is an abstract class. The extended classes (Pawn, King, Queen,
 * Rook, Knight, Bishop) implements the abstracted operations
 * 
 * @author M_402201
 *
 */
public abstract class Piece {

	private boolean available; // mark as live or dead
	private boolean isWhite; // mark if color is white or black

	public Piece(boolean available, boolean color) {
		this.available = available;
		this.isWhite = color;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}

	public abstract boolean canMove(Board board, Spot start, Spot end);

	public boolean isCastlingMove() {
		return false;
	}

}
