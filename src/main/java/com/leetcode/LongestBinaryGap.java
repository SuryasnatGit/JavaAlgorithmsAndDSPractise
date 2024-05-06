package com.leetcode;

/**
 * Given a positive integer n, find and return the longest distance between any two adjacent 1's in the binary
 * representation of n. If there are no two adjacent 1's, return 0.
 * 
 * Two 1's are adjacent if there are only 0's separating them (possibly no 0's). The distance between two 1's is the
 * absolute difference between their bit positions. For example, the two 1's in "1001" have a distance of 3.
 * 
 * Example 1:
 * 
 * Input: n = 22
 * 
 * Output: 2
 * 
 * Explanation: 22 in binary is "10110".
 * 
 * The first adjacent pair of 1's is "10110" with a distance of 2. The second adjacent pair of 1's is "10110" with a
 * distance of 1. The answer is the largest of these two distances, which is 2.
 * 
 */
public class LongestBinaryGap {

	public int binaryGap(int n) {
		System.out.println("Binary String :" + Integer.toBinaryString(n));
		int res = 0, prev = 0, cur = 0, count = 0;
		while (n > 0) {
			int digit = n % 2;
			n /= 2;

			// if count > 0 then check result
			if (digit == 1 && count > 0) {
				res = Math.max(res, cur - prev);
			}

			// increment count when digit is 1
			if (digit == 1) {
				prev = cur;
				count++;
			}

			cur++;
		}
		return res;
	}

	public static void main(String[] args) {
		LongestBinaryGap bg = new LongestBinaryGap();

		System.out.println(bg.binaryGap(1));
		System.out.println(bg.binaryGap(2));
		System.out.println(bg.binaryGap(3));

		System.out.println(bg.binaryGap(22));
		System.out.println(bg.binaryGap(145));
	}
}
