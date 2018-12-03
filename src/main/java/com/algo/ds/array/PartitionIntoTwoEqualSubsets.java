package com.algo.ds.array;

/**
 * Partition problem is to determine whether a given set can be partitioned into two subsets such
 * that the sum of elements in both subsets is same.
 * 
 * Examples
 * 
 * arr[] = {1, 5, 11, 5} Output: true The array can be partitioned as {1, 5, 5} and {11}
 * 
 * arr[] = {1, 5, 3} Output: false The array cannot be partitioned into equal sum sets.
 * 
 * Following are the two main steps to solve this problem: 1) Calculate sum of the array. If sum is
 * odd, there can not be two subsets with equal sum, so return false. 2) If sum of array elements is
 * even, calculate sum/2 and find a subset of array with sum equal to sum/2.
 * 
 * The first step is simple. The second step is crucial, it can be solved either using recursion or
 * Dynamic Programming
 * 
 * @author surya
 *
 */
public class PartitionIntoTwoEqualSubsets {

	/**
	 * Time Complexity: O(2^n) In worst case, this solution tries two possibilities (whether to include
	 * or exclude) for every element.
	 * 
	 * @param arr
	 * @return
	 */
	public boolean isSubsetsEqual_recursive(int[] arr) {
		int n = arr.length;
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += arr[i];

		if (sum % 2 != 0)
			return false;

		// Find if there is subset with sum equal to half of total sum
		return isSubsetsSum(arr, sum / 2, n);
	}

	private boolean isSubsetsSum(int[] arr, int sum, int n) {
		// base case
		if (sum == 0)
			return true;
		if (n == 0 && sum != 0)
			return false;

		// if last element is greater than sum, omit it
		if (arr[n - 1] > sum)
			return isSubsetsSum(arr, sum, n - 1);

		// otherwise check if sum can be obtained by retaining last element or excluding it
		return isSubsetsSum(arr, sum, n - 1) || isSubsetsSum(arr, sum - arr[n - 1], n - 1);
	}

	// Returns true if arr[] can be partitioned in two subsets of
	// equal sum, otherwise false
	public boolean isSubsetsEqual_DP(int arr[], int n) {
		int sum = 0;
		int i, j;

		// Caculcate sun of all elements
		for (i = 0; i < n; i++)
			sum += arr[i];

		if (sum % 2 != 0)
			return false;

		boolean part[][] = new boolean[sum / 2 + 1][n + 1];

		// initialize top row as true
		for (i = 0; i <= n; i++)
			part[0][i] = true;

		// initialize leftmost column, except part[0][0], as 0
		for (i = 1; i <= sum / 2; i++)
			part[i][0] = false;

		// Fill the partition table in botton up manner
		for (i = 1; i <= sum / 2; i++) {
			for (j = 1; j <= n; j++) {
				part[i][j] = part[i][j - 1];
				if (i >= arr[j - 1])
					part[i][j] = part[i][j] || part[i - arr[j - 1]][j - 1];
			}
		}

		/*
		 * // uncomment this part to print table for (i = 0; i <= sum/2; i++) { for (j = 0; j <= n; j++)
		 * printf ("%4d", part[i][j]); printf("\n"); }
		 */

		return part[sum / 2][n];
	}

	public static void main(String[] args) {
		PartitionIntoTwoEqualSubsets p = new PartitionIntoTwoEqualSubsets();
		int arr1[] = { 1, 5, 11, 5 };
		System.out.println(p.isSubsetsEqual_recursive(arr1));
		int arr2[] = { 1, 5, 3 };
		System.out.println(p.isSubsetsEqual_recursive(arr2));
	}

}
