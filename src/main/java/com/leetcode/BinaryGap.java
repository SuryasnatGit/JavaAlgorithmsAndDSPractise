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
 * distance of 1. The answer is the largest of these two distances, which is 2. Note that "10110" is not a valid pair
 * since there is a 1 separating the two 1's underlined.
 * 
 * TODO : understand more
 *
 */
public class BinaryGap {

	public int binaryGap(int N) {
		int[] A = new int[32];
		int t = 0;
		for (int i = 0; i < 32; ++i)
			if (((N >> i) & 1) != 0)
				A[t++] = i;

		int ans = 0;
		for (int i = 0; i < t - 1; ++i)
			ans = Math.max(ans, A[i + 1] - A[i]);
		return ans;
	}

	public int binaryGap1(int n) {
		int res = 0, prev = 0, cur = 0, count = 0;
		while (n > 0) {
			int digit = n % 2;
			n /= 2;
			if (digit == 1 && count > 0) {
				res = Math.max(res, cur - prev);
			}
			if (digit == 1) {
				prev = cur;
				count++;
			}
			cur++;
		}
		return res;
	}

	public static void main(String[] args) {
		BinaryGap bg = new BinaryGap();
		System.out.println(bg.binaryGap(22));
		System.out.println(bg.binaryGap1(22));
	}
}
