package com.algo.dp;

/**
 * Bell Numbers problem :
 * https://www.geeksforgeeks.org/bell-numbers-number-of-ways-to-partition-a-set/
 * 
 * Given a set of n elements, find number of ways of partitioning it. Examples:
 * 
 * Input: n = 2 Output: Number of ways = 2 Explanation: Let the set be {1, 2} Result : { {1}, {2} }
 * { {1, 2} }
 * 
 * Input: n = 3 Output: Number of ways = 5 Explanation: Let the set be {1, 2, 3} Result: { {1}, {2},
 * {3} } { {1}, {2, 3} } { {2}, {1, 3} } { {3}, {1, 2} } { {1, 2, 3} }.
 * 
 * Time Complexity of above solution is O(n2).
 * 
 * @author M_402201
 *
 */
public class NumberOfWaysToPartitionASet {

	// Function to find n'th Bell Number
	static int bellNumber(int n) {
		int[][] bell = new int[n + 1][n + 1];
		bell[0][0] = 1;

		for (int i = 1; i <= n; i++) {
			// Explicitly fill for j = 0
			bell[i][0] = bell[i - 1][i - 1];

			// Fill for remaining values of j
			for (int j = 1; j <= i; j++)
				bell[i][j] = bell[i - 1][j - 1] + bell[i][j - 1];
		}

		return bell[n][0];
	}

}
