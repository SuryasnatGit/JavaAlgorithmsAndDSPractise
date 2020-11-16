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

	/**
	 * Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings, s1
	 * and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring (e.g., waterbottle is a
	 * rotation of erbottlewat ).
	 * 
	 * We ask what the rotation point is. If a string is broken down into x and y, so thst xy = s1 and yx = s2, then yx
	 * is a substring of xyxy. i.e s2 is always a substring of s1s1.
	 * 
	 * The runtime of this varies based on the runtime of isSubString. But if you assume that isSubstring runs in O(A+B)
	 * time (on strings of length A and B), then the runtime of isRotation is O( N) .
	 * 
	 * CTCI - 1.9
	 */
	public boolean stringRotation(String s1, String s2) {
		int len1 = s1.length();
		if (len1 == s2.length() && len1 > 0) {
			String s1s1 = s1 + s1;
			return isSubString(s1s1, s2);
		}
		return false;
	}

	private boolean isSubString(String s1, String s2) {
		return s1.contains(s2);
	}

	/**
	 * Rotating to the left by n is the same as rotating to the right by length-n. time and space complexity - O(N)
	 * 
	 * @param input
	 * @param d
	 * @return
	 */
	public String leftRotation(String input, int d) {
		int n = input.length();
		if (d > n)
			d %= n;
		char[] ch = new char[n];
		for (int i = 0; i < n; i++) {
			ch[(i + (n - d)) % n] = input.charAt(i);
		}
		return new String(ch);
	}

	public String rightRotation(String input, int d) {
		int n = input.length();
		if (d > n)
			d %= n;
		char[] ch = new char[n];
		for (int i = 0; i < n; i++) {
			ch[(i + d) % n] = input.charAt(i);
		}
		return new String(ch);
	}

	public static void main(String[] args) {
		StringRotationProblems p = new StringRotationProblems();
		System.out.println(p.isRotatedString("abcd", "dabc"));
		System.out.println(p.isRotatedString("abcd", "bcad"));
	}
}
