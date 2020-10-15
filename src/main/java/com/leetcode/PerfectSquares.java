package com.leetcode;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum
 * to n.
 * 
 * Example 1:
 * 
 * Input: n = 12 Output: 3 Explanation: 12 = 4 + 4 + 4. Example 2:
 * 
 * Input: n = 13 Output: 2 Explanation: 13 = 4 + 9.
 *
 */
public class PerfectSquares {

	/**
	 * For every number x, we recur for n-x. Below is the recursive formula. If n = 1 and x*x <= n
	 * 
	 * T - Exponential
	 */
	public int numSquares(int n) {
		if (n <= 3) {
			return n;
		}

		int result = n; // min number of perfect squares

		for (int i = 1; i <= n; i++) {
			int temp = i * i;
			if (temp > n) {
				break;
			} else {
				result = Math.min(result, numSquares(n - temp) + 1);
			}
		}

		return result;
	}

	/**
	 * T - O(n) S - O(n)
	 * 
	 * @param n
	 * @return
	 */
	public int numSquares1(int n) {
		// We need to add a check here for n. If user enters 0 or 1 or 2
		// the below array creation will go OutOfBounds.
		if (n <= 3)
			return n;

		// Create a dynamic programming table
		// to store sq
		int dp[] = new int[n + 1];

		// getMinSquares table for base case entries
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;

		// getMinSquares rest of the table using recursive
		// formula
		for (int i = 4; i <= n; i++) {
			// max value is i as i can always be represented
			// as 1*1 + 1*1 + ...
			dp[i] = i;

			// Go through all smaller numbers to
			// to recursively find minimum
			for (int x = 1; x <= Math.ceil(Math.sqrt(i)); x++) {
				int temp = x * x;
				if (temp > i)
					break;
				else
					dp[i] = Math.min(dp[i], 1 + dp[i - temp]);
			}
		}

		// Store result and free dp[]
		int res = dp[n];

		return res;
	}
}
