package com.ctci.recursendp;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3 steps at
 * a time. Implement a method to count how many possible ways the child can run up the stairs.
 * 
 * @author surya
 *
 */
public class TripleStep {

	/**
	 * since each call branches out to 3 more calls. so time complexity is roughly O(3^n)
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
	 * Regardless of whether or not you use memoization, note that the number of ways will quickly
	 * overflow the bounds of an integer. By the time you get to just n = 37, the result has already
	 * overflowed. Using a long will delay, but not completely solve, this issue. It is great to
	 * communicate this issue to your interviewer. He probably won't ask you to work around it (although
	 * you could, with a BigInteger class), but it's nice to demonstrate that you think about these
	 * issues.
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

	public static void main(String[] args) {
		TripleStep ts = new TripleStep();
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(ts.tripleStep_recursive(10));
		// System.out.println(ts.tripleStep_recursive(36));
		// System.out.println(ts.tripleStep_recursive(37));
		System.out.println(ts.tripleStep_memoization(36));
		System.out.println(ts.tripleStep_memoization(37)); // int overflow. but not with BigInteger usage.
	}

}
