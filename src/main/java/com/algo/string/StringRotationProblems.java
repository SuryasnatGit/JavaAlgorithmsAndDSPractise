package com.algo.string;

public class StringRotationProblems {

	/**
	 * Check if a given string can be derived from another string by circularly rotating it. Rotation can be clock wise
	 * or anti clockwise.
	 * 
	 * example, X = ABCD, Y = DABC . Result : Yes
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isRotatedString(String x, String y) {
		int l = x.length();

		if (l != y.length())
			return false;

		for (int i = 0; i < l; i++) {
			x = x.substring(1) + x.charAt(0);
			if (x.equals(y))
				return true;
		}
		return false;
	}

	/**
	 * Check if given set of moves is circular or not. The move is circular if its starting and ending coordinates are
	 * the same. The moves can contain instructions to move one unit in same direction (M), to change direction to left
	 * of current direction (L) and to change direction to right of current direction (R).
	 * 
	 * @param str
	 * @return
	 */
	public boolean checkCircularMove(String str) {
		// start from coordinates (0, 0)
		int x = 0, y = 0;

		// assume initial direction is North
		char dir = 'N';

		// read each instruction from input String
		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
			// move one unit in same direction
			case 'M':
				if (dir == 'N') {
					y++;
				} else if (dir == 'S') {
					y--;
				} else if (dir == 'E') {
					x++;
				} else if (dir == 'W') {
					x--;
				}
				break;

			// change direction to left of current direction
			case 'L':
				if (dir == 'N') {
					dir = 'W';
				} else if (dir == 'W') {
					dir = 'S';
				} else if (dir == 'S') {
					dir = 'E';
				} else if (dir == 'E') {
					dir = 'N';
				}
				break;

			// change direction to right of current direction
			case 'R':
				if (dir == 'N') {
					dir = 'E';
				} else if (dir == 'E') {
					dir = 'S';
				} else if (dir == 'S') {
					dir = 'W';
				} else if (dir == 'W') {
					dir = 'N';
				}
			}
		}

		// if we're back to starting coordinates (0, 0),
		// the move is circular
		return (x == 0 && y == 0);
	}

	public static void main(String[] args) {
		StringRotationProblems p = new StringRotationProblems();
		System.out.println(p.isRotatedString("abcd", "dabc"));
		System.out.println(p.isRotatedString("abcd", "bcad"));
	}
}
