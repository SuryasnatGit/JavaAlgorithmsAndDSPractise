package com.leetcode;

/**
 * https://leetcode.com/problems/powx-n/
 * 
 * Example 1: Input: x = 2.00000, n = 10 Output: 1024.00000
 * 
 * Example 2: Input: x = 2.10000, n = 3 Output: 9.26100
 * 
 * Example 3: Input: x = 2.00000, n = -2 Output: 0.25000 Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 * Category : Medium
 */
public class PowerLC {

	public double myPow(double x, int n) {
		if (n == 0) {
			return 1;
		}
		double result = myPow(x, n / 2);
		if (n % 2 == 0) {
			return result * result;
		} else if (n < 0) {
			return result * result / x;
		}
		return result * result * x;
	}

	public double myPowIter(double x, int n) {
		// iterative way
		// everytime we square x so we need to find the square root of the n
		// convert n into binary, when the last digit is 1, which means current x should be in the answer
		// if n is an odd, we times this x into the anwser
		if (n == 0)
			return 1;

		if (n < 0) {
			n = -n;
			x = 1 / x;
		}
		double ans = 1;
		while (n > 0) {
			if ((n & 1) == 1)
				ans *= x;
			x *= x;
			n >>= 1;
		}
		return ans;
	}
}
