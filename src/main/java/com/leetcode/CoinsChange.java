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

	// DP using Bottom up approach iteratively.
	public int coinChangeDP(int[] coins, int amount) {
		// to capture the counts of coins
		int[] hash = new int[amount + 1];
		Arrays.fill(hash, Integer.MAX_VALUE);

		for (int i = 0; i < coins.length; i++) {
			if (coins[i] <= amount)
				hash[coins[i]] = 1;
		}

		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (i - coins[j] >= 0 && hash[i - coins[j]] != Integer.MAX_VALUE) {
					hash[i] = Math.min(hash[i], hash[i - coins[j]] + 1);
				}
			}
		}

		return hash[amount] == Integer.MAX_VALUE ? -1 : hash[amount];
	}

	public int coinChangeBFS(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		}

		Arrays.sort(coins); // 排序一下，可以提前stop

		Queue<Integer> queue = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		queue.offer(0); // Queue里边存的是amount
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

	public static void main(String[] args) {
		CoinsChange cc = new CoinsChange();
		int[] coins = { 1, 2, 5 };
		int amount = 11;
		System.out.println(cc.coinChangeDP(coins, amount));
	}
}
