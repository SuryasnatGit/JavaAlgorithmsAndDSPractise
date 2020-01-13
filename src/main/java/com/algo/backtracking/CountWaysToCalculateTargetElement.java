package com.algo.backtracking;

import java.util.List;

/**
 * https://www.techiedelight.com/find-ways-calculate-target-elements-array/
 *
 */
public class CountWaysToCalculateTargetElement {

	// T - O(3^n)
	public int countWays(int[] arr, int target) {
		int l = arr.length;
		return countWaysRecursive(arr, l - 1, target);
	}

	private int countWaysRecursive(int[] arr, int l, int target) {
		// if target is reached
		if (target == 0)
			return 1;

		if (l < 0)
			return 0;

		// excude current element
		int exclude = countWaysRecursive(arr, l - 1, target);

		// include current elemtn
		int include = countWaysRecursive(arr, l - 1, target - arr[l]) + countWaysRecursive(arr, l - 1, target + arr[l]);

		return include + exclude;
	}

	// print the list as well
	private void printList(List<Pair> list) {
		for (Pair p : list) {
			System.out.print("(" + p.sign + ")" + p.num + " ");
		}
		System.out.println();
	}

	// Print all ways to calculate a target from elements of specified array
	public void printWays(int[] arr, int n, int sum, int target, List<Pair> list) {
		// base case: if target is found, print result list
		if (sum == target) {
			printList(list);
			return;
		}

		// base case: No elements are left
		if (n < 0) {
			return;
		}

		// Ignore the current element
		printWays(arr, n - 1, sum, target, list);

		// Consider the current element and subtract it from the target
		list.add(new Pair(arr[n], '+'));
		printWays(arr, n - 1, sum + arr[n], target, list);
		list.remove(list.size() - 1); // backtrack

		// Consider the current element and add it to the target
		list.add(new Pair(arr[n], '-'));
		printWays(arr, n - 1, sum - arr[n], target, list);
		list.remove(list.size() - 1); // backtrack
	}

	public static void main(String[] args) {
		CountWaysToCalculateTargetElement c = new CountWaysToCalculateTargetElement();
		int[] arr = { 5, 3, -6, 2 };
		int target = 6;
		System.out.println(c.countWays(arr, target));
	}
}

class Pair {
	Integer num;
	Character sign;

	Pair(Integer num, Character sign) {
		this.num = num;
		this.sign = sign;
	}
}
