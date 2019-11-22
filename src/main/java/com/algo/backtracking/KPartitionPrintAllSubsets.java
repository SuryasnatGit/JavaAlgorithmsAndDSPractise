package com.algo.backtracking;

import java.util.Arrays;

/**
 * In k-partition problem, we need to partition an array of positive integers into k disjoint subsets that all have
 * equal sum and they completely covers the set.
 * 
 * 
 * For example, consider below set S = { 7, 3, 5, 12, 2, 1, 5, 3, 8, 4, 6, 4 }
 * 
 * 1. S can be partitioned into 2 partitions each having sum 30. = { 5, 3, 8, 4, 6, 4 } and = { 7, 3, 5, 12, 2, 1 }
 * 
 * 2. S can be partitioned into 3 partitions each having sum 20. = { 2, 1, 3, 4, 6, 4 }, = { 7, 5, 8 }, = { 3, 5, 12 }
 * 
 * and so on.
 * 
 * We can start by calculating the sum of all elements in the set. If sum is not divisible by k, we can’t divide the
 * array into k subsets with equal sum. If sum is divisible by k, we check if k subsets with sum of elements equal to
 * sum/k exists or not. We can find this by considering each item in the given array one by one and for each item we
 * include it in the i’th subset & recur for remaining items with remaining sum. We backtrack if solution is not found
 * by including current item in i’th subset and try for (i + 1) th subset.
 * 
 *
 */
public class KPartitionPrintAllSubsets {

	public void kPartition(int[] arr, int k) {
		int l = arr.length;
		if (l < k) {
			System.out.println("k partitions not possible");
			return;
		}

		// contains the result
		int[] result = new int[l];

		// sum of all elems in arr
		int sum = 0;
		for (int e : arr) {
			sum += e;
		}

		// create a sum left array and initialize with sum/k value in each elem.
		int[] sumLeft = new int[k];
		Arrays.fill(sumLeft, sum / k);

		boolean res = (sum % k == 0 && subsetSum(arr, l - 1, result, sumLeft, k));

		if (!res) {
			System.out.println("k partitions not possible");
			return;
		}

		// print result
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < l; j++) {
				if (result[j] == i + 1)
					System.out.print(arr[j] + " ");
			}
			System.out.println();
		}
	}

	private boolean subsetSum(int[] arr, int l, int[] result, int[] sumLeft, int k) {
		// return true if subset is found
		if (checkSum(sumLeft, k))
			return true;

		// base case - no items left
		if (l < 0)
			return false;

		boolean res = false;

		// consider current item and explore all possibilities using back tracking
		for (int i = 0; i < k; i++) {
			if (!res && (sumLeft[i] - arr[l]) >= 0) {
				// mark current element subset
				result[l] = i + 1;

				sumLeft[i] = sumLeft[i] - arr[l];
				res = subsetSum(arr, l - 1, result, sumLeft, k);
				// back track
				sumLeft[i] = sumLeft[i] + arr[l];
			}
		}
		return res;
	}

	private boolean checkSum(int[] sumLeft, int k) {
		for (int i = 0; i < k; i++) {
			if (sumLeft[i] != 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		KPartitionPrintAllSubsets kp = new KPartitionPrintAllSubsets();
		int S[] = { 7, 3, 5, 12, 2, 1, 5, 3, 8, 4, 6, 4 };
		int k = 5;
		kp.kPartition(S, k);
	}

}
