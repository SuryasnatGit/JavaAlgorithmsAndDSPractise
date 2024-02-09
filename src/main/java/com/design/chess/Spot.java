package com.ooad.chess;

/**
 * A spot represents one block of the 8×8 grid and an optional piece
 * 
 * @author M_402201
 *
 */
public class Spot {

	private int x;
	private int y;
	private Piece piece;

	public Spot(int x, int y, Piece piece) {
		this.x = x;
		this.y = y;
		this.piece = piece;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Piece getPiece() {
		return this.piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	// return original piece
	public Piece occupySpot(Piece piece) {
		Piece origin = this.piece;
		// if piece already here, delete it, i. e. set it dead
		if (this.piece != null) {
			this.piece.setAvailable(false);
		}
		// place piece here
		this.piece = piece;
		return origin;
	}

	public boolean isOccupied() {
		if (piece != null)
			return true;
		return false;
	}

	public Piece releaseSpot() {
		Piece releasedPiece = this.piece;
		this.piece = null;
		return releasedPiece;
	}


}
