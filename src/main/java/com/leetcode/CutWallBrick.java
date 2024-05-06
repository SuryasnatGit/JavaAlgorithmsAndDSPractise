package com.leetcode;

/**
 * The interviewer was a white man with an accent that sounded like he was from Eastern Europe. The problem is, there is
 * a wall, and then the width and length are fixed. This wall is represented by a vector<vector<int>> wall. Each row
 * represents a row of bricks, and the width of each brick is not fixed. Then wall represents the width of the brick.
 * Then if you draw a line on the wall, it will cross these bricks.
 * 
 * Q1 : I asked you how to draw the line so that the minimum number of bricks can be crossed (if the line you draw
 * happens to be on one side of the brick, it does not count as a cross). Just return the minimum number of bricks.
 * Asked about time and space complexity.
 * 
 * Q2 : follow Up is that the height of this wall is very small, but the width is very large. Each row may have a very
 * large number of bricks. What should you do?
 *
 * You only need pointers with the number of wall heights. Compare the currently pointed value to see if it overlaps,
 * and then use the one with the smallest increment.
 *
 * Q3 : The question in the third round is to draw a straight line from top to bottom. In fact, it is to find the sides
 * of the most bricks that are connected up and down. Drawing a line along such sides does not count as crossing bricks.
 * 
 * T - O(n) where n is number of bricks, S - O(T) where T is width of wall.
 * 
 * https://medium.com/analytics-vidhya/brick-and-wall-problem-competetive-programming-a-complete-algorithm-with-code-e351354b6234
 * 
 * Advanced: https://algo.monster/liteproblems/2184
 */
public class CutWallBrick {

	public static void main(String[] args) {
		int[][] matrix = { { 3, 5, 1, 1 }, { 2, 3, 3, 2 }, { 5, 5 }, { 4, 4, 2 }, { 1, 3, 3, 3 }, { 1, 1, 6, 1, 1 } };
		CutWallBrick c = new CutWallBrick();
		System.out.println(c.leastNumber(matrix, 10));
	}

	public int leastNumber(int[][] array, int width) {
		// Total number of possible edges is width + 1
		int[] hash = new int[width + 1];
		int max = 0;
		// iterating over input array
		for (int i = 0; i < array.length; i++) {
			int sum = 0;
			for (int j = 0; j < array[i].length; j++) {
				sum = sum + array[i][j];
				array[i][j] = sum; // replacing the actual values with the position of edges
				hash[sum] += 1; // storing the occurrences of edges.
			}
		}
		// Loop to iterate over the input array which now contains the position of edges
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length - 1; j++) { // excluding the last element in the row
				int ele = array[i][j];
				int val = hash[ele]; // retrieving the occurrence of the edge from hash array
				max = Math.max(max, val);// finding the maximum of occurrences of all edges
			}
		}
		return array.length - max;
	}
}
