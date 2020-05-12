package com.algo.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Question: Given a list of items with values and weights, as well as a max weight, find the maximum value you can
 * generate from items where the sum of the weights is less than the max.
 * 
 * ● Eg.
 * 
 * items = {(w:1, v:6), (w:2, v:10), (w:3, v:12)}
 * 
 * maxWeight = 5
 * 
 * knapsack(items, maxWeight) = 22
 * 
 * You cannot break an item, either pick the complete item, or don’t pick it (0-1 property)
 * 
 * W(1+2) -> V(6+10) -> V(16)
 * 
 * W(1+3) -> V(6+12) -> V(18)
 * 
 * W(2+3) -> V(10+12) -> V(22)
 * 
 * Category : Hard
 *
 */
public class KnapsackProblem {

	// SOlution 1
	/**
	 * Recursively check every combination of items by traversing list of items and either including or excluding each
	 * item. Overlapping subproblem property. T - O(2^n)
	 */
	public int knapsackRecursive(Item[] items, int W) {
		return knapsackRecursive(items, W, 0);
	}

	// Overloaded recursive function for naiveKnapsack
	private int knapsackRecursive(Item[] items, int W, int i) {
		// Return when we reach the end of the list
		if (i == items.length)
			return 0;

		// If item is heavier than remaining weight, skip item
		if (W - items[i].weight < 0)
			return knapsackRecursive(items, W, i + 1);

		// Try both including and excluding the current item
		return Math.max(knapsackRecursive(items, W - items[i].weight, i + 1) + items[i].value,
				knapsackRecursive(items, W, i + 1));
	}

	// SOlution 2
	// Recursive solution that uses a cache to improve performance
	public int topDownKnapsack(Item[] items, int W) {
		// Map: i -> W -> value
		// Use a map instead of array because the data could be very sparse
		Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();
		return topDownKnapsack(items, W, 0, cache);
	}

	// Overloaded recursive function for topDownKnapsack
	private int topDownKnapsack(Item[] items, int W, int i, Map<Integer, Map<Integer, Integer>> cache) {
		// Return when we reach the end of the list
		if (i == items.length)
			return 0;

		// Check the cache and return value if we get a hit
		if (!cache.containsKey(i))
			cache.put(i, new HashMap<>());
		Integer cached = cache.get(i).get(W);
		if (cached != null)
			return cached;

		// If item is heavier than remaining weight, skip item
		if (W - items[i].weight < 0)
			return topDownKnapsack(items, W, i + 1, cache);

		// Try both including and excluding the current item
		int toReturn = Math.max(topDownKnapsack(items, W - items[i].weight, i + 1, cache) + items[i].value,
				topDownKnapsack(items, W, i + 1, cache));
		cache.get(i).put(W, toReturn);
		return toReturn;
	}

	// SOlution 3
	// Iterative dynamic programming solution. T - O(nW) where n is number of items and W is capacity of knapsack
	public int bottomUpKnapsack(Item[] items, int W) {
		// cache[i][j] = max value for the first i items with a max weight of j
		int[][] cache = new int[items.length + 1][W + 1];
		for (int i = 1; i <= items.length; i++) {
			for (int j = 0; j <= W; j++) {
				// If including item[i-1] would exceed max weight j, don't
				// include the item. Otherwise take the max value of including
				// or excluding the item
				if (items[i - 1].weight > j)
					cache[i][j] = cache[i - 1][j];
				else
					cache[i][j] = Math.max(cache[i - 1][j], cache[i - 1][j - items[i - 1].weight] + items[i - 1].value);
			}
		}

		return cache[items.length][W];
	}

	// TODO : Fractional knapsack, knapsack with large weights, unbounded fractional knapsack, double knapsack

	public static void main(String[] args) {
		KnapsackProblem ksp = new KnapsackProblem();
		Item[] items = new Item[3];
		items[0] = new Item(1, 6);
		items[1] = new Item(2, 10);
		items[2] = new Item(3, 12);
		System.out.println(ksp.knapsackRecursive(items, 5));
		System.out.println(ksp.topDownKnapsack(items, 5));
		System.out.println(ksp.bottomUpKnapsack(items, 5));
	}
}

// Item class
class Item {
	int weight;
	int value;

	public Item(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}
}
