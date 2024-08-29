package com.algo.ds.array;

/**
 * You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north, then
 * x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on. In other words, after each move
 * your direction changes counter-clockwise.
 * 
 * Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.
 * 
 * Example 1: Given x = [2, 1, 1, 2] Return true (self crossing)
 * 
 * Example 2: Given x = [1, 2, 3, 4] Return false (not self crossing)
 * 
 * Example 3: Given x = [1, 1, 1, 1] Return true (self crossing)
 * 
 * Category : Hard
 */
public class SelfCrossing {

	/**
	 * Understand the problem:
	 * 
	 * To solve the problem, we must understand the conditions that can cause the path to cross itself. There are
	 * generally three cases where crossing can happen:
	 * 
	 * Case 1: Fourth Line Crosses the First - This case happens when the current step overshoots the first step. More
	 * specifically, the path crosses if the current distance is greater than or equal to the distance two steps back,
	 * and the distance a step before is less than or equal to the distance three steps back.
	 * 
	 * Case 2: Fifth Line Meets the First - Here, the path's fifth segment overlaps with the first segment if the
	 * current step is equal to the step three steps back, and the sum of the current step and the step four steps back
	 * is greater than or equal to the step two steps back.
	 * 
	 * Case 3: Sixth Line Crosses the First - This is a more complex scenario where the sixth line crosses over the
	 * first. For this to happen, several conditions need to match: the fourth step is greater than or equal to the
	 * second step, the fifth step is less than or equal to the third step, the sum of the third and the sixth steps is
	 * greater than or equal to the first step, and the sixth step is greater than or equal to the difference between
	 * the second and fourth steps.
	 * 
	 * 
	 * @param x
	 * @return
	 */
	public boolean isSelfCrossing(int[] distance) {
		// Enhanced for loop is unnecessary as we are directly accessing elements.
		// Naming of the variable 'd' is changed to 'distances' for better readability.

		// Start from the fourth element (index starts from 0) and check for self-crossing
		for (int i = 3; i < distance.length; ++i) {
			// Scenario 1: Current line crosses the line 3 steps behind it
			if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]) {
				return true;
			}

			// Scenario 2: Current line touches or crosses the line 4 steps behind it
			if (i >= 4 && distance[i - 1] == distance[i - 3] && distance[i] + distance[i - 4] >= distance[i - 2]) {
				return true;
			}

			// Scenario 3: Current line crosses the line 5 steps behind it
			if (i >= 5 && distance[i - 2] >= distance[i - 4] && distance[i - 1] <= distance[i - 3]
					&& distance[i] >= distance[i - 2] - distance[i - 4]
					&& distance[i - 1] + distance[i - 5] >= distance[i - 3]) {
				return true;
			}
		}

		// If none of the above scenarios occur, there is no self crossing
		return false;
	}

	public static void main(String args[]) {
		SelfCrossing sc = new SelfCrossing();
		System.out.println(sc.isSelfCrossing(new int[] { 3, 3, 4, 2, 2 }));
		System.out.println(sc.isSelfCrossing(new int[] { 1, 2, 3, 4 }));
		System.out.println(sc.isSelfCrossing(new int[] { 5, 4, 3, 2, 1 }));
		System.out.println(sc.isSelfCrossing(new int[] { 1, 1, 1, 1 }));
	}
}
