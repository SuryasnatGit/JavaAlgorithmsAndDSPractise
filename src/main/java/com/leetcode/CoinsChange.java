package com.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the
 * fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return -1.
 * 
 * Example 1:
 * 
 * coins = [1, 2, 5], amount = 11
 * 
 * return 3 (11 = 5 + 5 + 1)
 * 
 * Example 2:
 * 
 * coins = [2], amount = 3
 * 
 * return -1.
 * 
 * Note: You may assume that you have an infinite number of each kind of coin.
 *
 */
public class CoinsChange {

	// T - O(m * n) S - O(m) where m = amount, n = number of coins
	public int coinChangeBottomUpDP(int[] coins, int amount) {
		// to capture the counts of coins for all values from 0 to given amount
		int[] hash = new int[amount + 1];
		Arrays.fill(hash, Integer.MAX_VALUE);

		for (int i = 0; i < coins.length; i++) {
			if (coins[i] <= amount)
				hash[coins[i]] = 1;
		}

		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				// if the coin value is less than amount
				if (i - coins[j] >= 0 && hash[i - coins[j]] != Integer.MAX_VALUE) {
					// select the coin and add 1 to solution of (amount - coin value)
					hash[i] = Math.min(hash[i], hash[i - coins[j]] + 1);
				}
			}
		}
		// return optimal solution for amount.
		return hash[amount] == Integer.MAX_VALUE ? -1 : hash[amount];
	}

	// Approach - https://www.geeksforgeeks.org/coin-change-bfs-approach/
	// T - O(m * n) S - O(m) where m = amount, n = number of coins
	public int coinChangeBFS(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}

		Arrays.sort(coins);

		Queue<Integer> queue = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		queue.offer(0);
		int depth = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			depth++;

			for (int i = 0; i < size; i++) {
				int cur = queue.poll();

				for (int j = 0; j < coins.length; j++) {
					int newVal = cur + coins[j];

					if (newVal == amount) {
						return depth;
					}
					if (newVal > amount) {
						break;
					}

					if (!visited.contains(newVal)) {
						visited.add(newVal);
						queue.offer(newVal);
					}
				}
			}
		}

		return -1;
	}

	/**
	 * Problem 2 :
	 *
	 * You are given coins of different denominations and a total amount of money. Write a function to compute the
	 * number of combinations that make up that amount. You may assume that you have infinite number of each kind of
	 * coin.
	 * 
	 * Example 1:
	 * 
	 * Input: amount = 5, coins = [1, 2, 5] Output: 4
	 * 
	 * Explanation: there are four ways to make up the amount:
	 * 
	 * 5=5
	 * 
	 * 5=2+2+1
	 * 
	 * 5=2+1+1+1
	 * 
	 * 5=1+1+1+1+1
	 * 
	 * Example 2:
	 * 
	 * Input: amount = 3, coins = [2] Output: 0
	 * 
	 * Explanation: the amount of 3 cannot be made up just with coins of 2.
	 * 
	 * Example 3:
	 * 
	 * Input: amount = 10, coins = [10]
	 * 
	 * Output: 1
	 * 
	 * Time complexity of this method is (arr.length * target)
	 */

	// T - O(2^n)
	public int numberOfCombinationsRecur(int[] coins, int amount) {
		return numberOfCombinationsRecur(coins, amount, 0);
	}

	private int numberOfCombinationsRecur(int[] coins, int amount, int index) {
		if (amount < 0)
			return 0;

		if (amount == 0)
			return 1;

		if (index == coins.length && amount > 0)
			return 0;

		// for every coin we have option to either include or exclude it
		return numberOfCombinationsRecur(coins, amount, index + 1)
				+ numberOfCombinationsRecur(coins, amount - coins[index], index);
	}

	public int numberOfCombinationsDP(int[] coins, int amount) {
		// Use first i item to form target j, how many solutions?
		int[][] hash = new int[coins.length + 1][amount + 1]; //

		for (int i = 0; i < hash.length; i++) {
			hash[i][0] = 1; // From first i items to form target 0, it has 1 solution. Just pick up nothing.
		}

		for (int i = 1; i < hash[0].length; i++) {
			hash[0][i] = 0; // From first 0 items to form target i(>=1), it has 0 solution. Impossible.
		}

		for (int i = 1; i <= coins.length; i++) {
			for (int j = 1; j <= amount; j++) {
				// check if the coin value is less than the amount needed
				if (coins[i - 1] <= j) {
					// reduce the amount by coin value and use the subproblem solution (amount-v[i]) and add the
					// solution from the top to it
					hash[i][j] = hash[i - 1][j] + hash[i][j - coins[i - 1]];
				} else {
					// just copy the value from the top
					hash[i][j] = hash[i - 1][j];
				}
			}
		}

		return hash[coins.length][amount]; // Final result
	}

	public static void main(String[] args) {
		CoinsChange cc = new CoinsChange();
		int[] coins = { 1, 2, 5 };
		int amount = 11;
		System.out.println(cc.coinChangeBottomUpDP(coins, amount));
		System.out.println(cc.coinChangeBFS(coins, amount));
		System.out.println(cc.numberOfCombinationsRecur(coins, 5));
		System.out.println(cc.numberOfCombinationsDP(coins, 5));
	}
}
