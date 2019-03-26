package com.algo.dp;

/**
 * Suppose you have N eggs and you want to determine from which floor in a
 * K-floor building you can drop an egg such that it doesn't break. You have to
 * determine the minimum number of attempts you need in order find the critical
 * floor in the worst case while using the best strategy.There are few rules
 * given below.
 * 
 * An egg that survives a fall can be used again.<br/>
 * A broken egg must be discarded.<br/>
 * The effect of a fall is the same for all eggs.<br/>
 * If the egg doesn't break at a certain floor, it will not break at any floor
 * below.<br/>
 * If the eggs breaks at a certain floor, it will break at any floor above.
 * <br/>
 * 
 * @author M_402201
 *
 */
public class EggDropping {

	/**
	 * 1) Optimal Substructure:When we drop an egg from a floor x, there can be two
	 * cases (1) The egg breaks (2) The egg doesn’t break. 1) If the egg breaks
	 * after dropping from xth floor, then we only need to check for floors lower
	 * than x with remaining eggs; so the problem reduces to x-1 floors and n-1 eggs
	 * 2) If the egg doesn’t break after dropping from the xth floor, then we only
	 * need to check for floors higher than x; so the problem reduces to k-x floors
	 * and n eggs.
	 * 
	 * 
	 * Since same suproblems are called again, this problem has Overlapping
	 * Subprolems property. So Egg Dropping Puzzle has both properties (overlapping
	 * subproblem and optimal substructure) of a dynamic programming problem
	 * 
	 * Time Complexity: O(nk^2) Auxiliary Space: O(nk)
	 * 
	 * 
	 * 
	 * @param n number of eggs
	 * @param k number of floors
	 * @return
	 */
	public int eggDropRecursive(int n, int k) {
		// if no floor then no trial needed. if 1 floor then 1 trial needed
		if (k == 1 || k == 0)
			return k;

		// need k trials for 1 egg and k floors
		if (n == 1)
			return k;

		int min = Integer.MAX_VALUE;
		// consider all droppings from 1st to kth floor and return the minimum trials
		for (int c = 1; c <= k; c++) {
			int res = Math.max(eggDropRecursive(n - 1, c - 1), eggDropRecursive(n, k - c));
			if (res < min)
				min = res;
		}
		return min + 1;
	}

	/**
	 * 
	 * Time Complexity: O(nk^2) Auxiliary Space: O(nk)
	 * 
	 * 
	 * 
	 * @param n number of eggs
	 * @param k number of floors
	 * @return
	 */
	public int eggDropDP(int n, int k) {
		/*
		 * A 2D table where entry eggFloor[i][j] will represent minimum number of trials
		 * needed for i eggs and j floors.
		 */
		int eggFloor[][] = new int[n + 1][k + 1];
		int res;
		int i, j, x;

		// We need one trial for one floor and 0 trials for 0 floors
		for (i = 1; i <= n; i++) {
			eggFloor[i][1] = 1;
			eggFloor[i][0] = 0;
		}

		// We always need j trials for one egg and j floors.
		for (j = 1; j <= k; j++)
			eggFloor[1][j] = j;

		// Fill rest of the entries in table using optimal substructure
		// property
		for (i = 2; i <= n; i++) {
			for (j = 2; j <= k; j++) {
				eggFloor[i][j] = Integer.MAX_VALUE;
				for (x = 1; x <= j; x++) {
					res = 1 + Math.max(eggFloor[i - 1][x - 1], eggFloor[i][j - x]);
					if (res < eggFloor[i][j])
						eggFloor[i][j] = res;
				}
			}
		}

		// eggFloor[n][k] holds the result
		return eggFloor[n][k];

	}

	/*
	 * https://www.geeksforgeeks.org/eggs-dropping-puzzle-binomial-coefficient-and-
	 * binary-search-solution/
	 */
	// Find sum of binomial coefficients xCi
	// (where i varies from 1 to n). If the sum
	// becomes more than K
	private int binomialCoeff(int x, int n, int k) {
		int sum = 0, term = 1;
		for (int i = 1; i <= n && sum < k; ++i) {
			term *= x - i + 1;
			term /= i;
			sum += term;
		}
		return sum;
	}

	// Do binary search to find minimum
	// number of trials in worst case.
	public int minTrials(int n, int k) {
		// Initialize low and high as 1st
		// and last floors
		int low = 1, high = k;

		// Do binary search, for every mid,
		// find sum of binomial coefficients and
		// check if the sum is greater than k or not.
		while (low < high) {
			int mid = (low + high) / 2;
			if (binomialCoeff(mid, n, k) < k)
				low = mid + 1;
			else
				high = mid;
		}

		return low;
	}

}
