package com.ctci.recursendp;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * A child is running up a staircase with n steps and can hop either 1 step, 2
 * steps, or 3 steps at a time. Implement a method to count how many possible
 * ways the child can run up the stairs.
 * 
 * @author surya
 *
 */
public class TripleStep {

	/**
	 * since each call branches out to 3 more calls. so time complexity is roughly
	 * O(3^n)
	 * 
	 * @param n
	 * @return
	 */
	public int tripleStep_recursive(int n) {
		if (n < 0)
			return 0;
		else if (n == 0)
			return 1;
		else
			return tripleStep_recursive(n - 1) + tripleStep_recursive(n - 2) + tripleStep_recursive(n - 3);
	}

	public BigInteger tripleStep_memoization(int n) {
		BigInteger[] memo = new BigInteger[n + 1];
		Arrays.fill(memo, BigInteger.valueOf(-1));
		return ts_memo(n, memo);
	}

	/**
	 * Regardless of whether or not you use memoization, note that the number of
	 * ways will quickly overflow the bounds of an integer. By the time you get to
	 * just n = 37, the result has already overflowed. Using a long will delay, but
	 * not completely solve, this issue. It is great to communicate this issue to
	 * your interviewer. He probably won't ask you to work around it (although you
	 * could, with a BigInteger class), but it's nice to demonstrate that you think
	 * about these issues.
	 * 
	 * @param n
	 * @param memo
	 * @return
	 */
	private BigInteger ts_memo(int n, BigInteger[] memo) {
		if (n < 0)
			return BigInteger.valueOf(0);
		else if (n == 0)
			return BigInteger.valueOf(1);
		else if (memo[n].intValue() > -1)
			return memo[n];
		else {
			memo[n] = ts_memo(n - 1, memo).add(ts_memo(n - 2, memo)).add(ts_memo(n - 3, memo));
			return memo[n];
		}
	}

	/**
	 * time complexity - O(mn)
	 * 
	 * @param n
	 * @return
	 */
	public int ts_dp(int n) {
		int[] res = new int[n + 1];
		res[0] = 1;
		res[1] = 1;

		for (int i = 2; i < n; i++) {
			res[i] = 0;
			for (int j = 1; j <= i; j++) {
				res[i] += res[i - j];
			}
		}
		return res[n - 1];
	}

	/**
	 * Number of ways to reach Nth floor by taking at-most K leaps.
	 * 
	 * Time Complexity: O(N*K) Auxiliary Space: O(N)
	 * 
	 * 
	 * @param N
	 * @param K
	 * @return
	 */
	public int solve(int N, int K) {

		// elements of combo[] stores
		// the no. of possible ways
		// to reach it by all combinations
		// of k leaps or less
		int[] combo;
		combo = new int[50];

		// assuming leap 0 exist
		// and assigning its value
		// to 1 for calculation
		combo[0] = 1;

		// loop to iterate over all
		// possible leaps upto k;
		for (int i = 1; i <= K; i++) {

			// in this loop we count all
			// possible leaps to reach
			// the jth stair with the
			// help of ith leap or less
			for (int j = 0; j <= N; j++) {

				// if the leap is not
				// more than the i-j
				if (j >= i) {

					// calculate the value and
					// store in combo[j] to
					// reuse it for next leap
					// calculation for the
					// jth stair
					combo[j] += combo[j - i];
				}
			}
		}

		// returns the no of possible
		// number of leaps to reach
		// the top of building of
		// n stairs
		return combo[N];
	}

	public static void main(String[] args) {
		TripleStep ts = new TripleStep();
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(ts.tripleStep_recursive(10));
		// System.out.println(ts.tripleStep_recursive(36));
		// System.out.println(ts.tripleStep_recursive(37));
		System.out.println(ts.tripleStep_memoization(36));
		System.out.println(ts.tripleStep_memoization(37)); // int overflow. but not with BigInteger usage.
		System.out.println(ts.ts_dp(37));
	}

}
