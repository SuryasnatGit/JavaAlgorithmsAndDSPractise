package com.algo.ds.array;

/**
 * You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to
 * the north, then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so
 * on. In other words, after each move your direction changes counter-clockwise.
 * 
 * Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or
 * not.
 * 
 * Example 1: Given x = [2, 1, 1, 2] Return true (self crossing)
 * 
 * Example 2: Given x = [1, 2, 3, 4] Return false (not self crossing)
 * 
 * Example 3: Given x = [1, 1, 1, 1] Return true (self crossing)
 * 
 */
public class SelfCrossing {

	/**
	 * Understand the problem:
	 * 
	 * The problem is tricky to solve. There are in total three cases to consider if there is no cross
	 * 
	 * 1. Only have internal squirrels. In this case, the length of each step should go smaller and
	 * smaller. So we only need to check if x[i] < x[i - 2].
	 * 
	 * 2. Only have external squirrels. In this case, the length of each step should go larger and
	 * larger. So we only need to check if x[i] > x[i - 2].
	 * 
	 * 3. In the third case, it goes external squirrel first then go internal. In this case, the trick
	 * part is we may need to update the base of the internal squirrel.
	 * 
	 * 
	 * @param x
	 * @return
	 */
	public boolean isSelfCrossing(int[] x) {
		if (x.length < 4) {
			return false;
		}
		int v1 = -x[0];
		int v2 = -x[1];

		int i = 2;
		while (i < x.length) {
			if (i % 2 == 0) {
				if (i % 4 == 0) {
					v1 -= x[i];
				} else {
					v1 += x[i];
				}
			} else {
				if ((i + 1) % 4 == 0) {
					v2 += x[i];
				} else {
					v2 -= x[i];
				}
			}
			if (i % 2 != 0) {
				if ((v1 >= 0 && v2 <= 0) || (v1 <= 0 && v2 >= 0)) {
					return true;
				}
			}
			i++;
		}
		return false;
	}

	public static void main(String args[]) {
		SelfCrossing sc = new SelfCrossing();
		int input[] = { 3, 3, 4, 2, 2 };
		System.out.print(sc.isSelfCrossing(input));
	}
}
