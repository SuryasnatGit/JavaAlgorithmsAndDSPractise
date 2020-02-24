package com.leetcode;

/**
 * Given an integer n, calculate square of a number without using *, / and pow().
 *
 * https://www.geeksforgeeks.org/calculate-square-of-a-number-without-using-and-pow/
 */
public class FindSquareOfNumber {

	// T - O(n)
	public int square(int n) {

		// handle negative input
		if (n < 0)
			n = -n;

		// Initialize result
		int res = n;

		// Add n to res n-1 times
		for (int i = 1; i < n; i++)
			res += n;

		return res;
	}

	// T - O(log n)
	public int square1(int n) {

		// Base case
		if (n == 0)
			return 0;

		// Handle negative number
		if (n < 0)
			n = -n;

		// Get floor(n/2) using
		// right shift
		int x = n >> 1;

		// If n is odd
		;
		if (n % 2 != 0)
			return ((square1(x) << 2) + (x << 2) + 1);
		else // If n is even
			return (square1(x) << 2);
	}

}
