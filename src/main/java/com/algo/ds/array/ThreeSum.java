package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	/**
	 * To demonstrate the brute way of counting the number of triplets of doing a 3 sum computation for a N integer
	 * array, so that for (i,j,k) where i < j < k , a[i] + a[j] + a[k] = 0 complexity - O(N*N*N) = O(N^3)
	 * 
	 * @author Suryasnat
	 *
	 */
	public void threeSumSlow(int[] intArr) {
		int N = intArr.length;
		int count = 0;
		for (int i = 0; i < N; i++)
			for (int j = i + 1; j < N; j++)
				for (int k = j + 1; k < N; k++)
					if (intArr[i] + intArr[j] + intArr[k] == 0)
						count++;

		System.out.println(count);
	}

	/**
	 * complexity is N^2 log N (linearithimic)
	 * 
	 * @param arr
	 * @throws Exception
	 */
	public void threeSumFast(int[] arr) throws Exception {
		int N = arr.length;
		int count = 0;
		java.util.Arrays.sort(arr); // Done using merge sort. Complexity - N log N
		if (duplicatePresent(arr))
			throw new Exception("");

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				int k = Arrays.binarySearch(arr, -(arr[i] + arr[j])); // Complexity for N binary search is log N
				if (k > j)
					count++;
			}
		}

		System.out.println(count);
	}

	private boolean duplicatePresent(int[] arr) {
		// complexity - O(N)
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[i - 1])
				return true;
		}
		return false;
	}

	/**
	 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets
	 * in the array which gives the sum of zero.
	 * 
	 * Note: Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c) The solution set must not
	 * contain duplicate triplets.
	 * 
	 * For example, given array S = {-1 0 1 2 -1 -4},
	 * 
	 * A solution set is: (-1, 0, 1)<br/>
	 * (-1, -1, 2)
	 * 
	 * This problem can be solved by using two pointers. Time complexity is O(n^2).
	 * 
	 * To avoid duplicate, we can take advantage of sorted arrays, i.e., move pointers by >1 to use same element only
	 * once.
	 * 
	 * A typical k-sum problem. Time is N to the power of (k-1).
	 * 
	 * @param arr
	 * @return
	 */
	public List<List<Integer>> findThreeSums(int[] arr) {
		List<List<Integer>> result = new ArrayList<>();
		if (arr == null || arr.length < 2)
			return result;

		// sort the array
		Arrays.sort(arr); // O(N log N)
		// O(N*N) = O(N^2)
		for (int i = 0; i < arr.length - 2; i++) {
			if (i == 0 || arr[i] > arr[i - 1]) {
				int j = i + 1;
				int k = arr.length - 1;
				while (j < k) {
					// 3 cases.
					// equals to 0
					if (arr[i] + arr[j] + arr[k] == 0) {
						List<Integer> temp = new ArrayList<>();
						temp.add(arr[i]);
						temp.add(arr[j]);
						temp.add(arr[k]);
						result.add(temp);
						j++;
						k--;

						// handle duplicate cases
						if (j < k && arr[j] == arr[j - 1])
							j++;
						if (j < k && arr[k] == arr[k + 1])
							k--;
					} else if (arr[i] + arr[j] + arr[k] < 0) // < 0
						j++;
					else // > 0
						k--;
				}
			}
		}
		return result;
	}

	/**
	 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
	 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
	 * 
	 * For example, given array S = {-1 2 1 -4}, and target = 1. The sum that is closest to the target is 2. (-1 + 2 + 1
	 * = 2). Time Complexity is O(n^2).
	 * 
	 * @param arr
	 * @param target
	 * @return
	 */
	public int threeSumClosest(int[] arr, int target) {
		int min = Integer.MAX_VALUE;
		int result = 0;

		if (arr == null || arr.length < 3)
			return result;

		for (int i = 0; i < arr.length; i++) {
			int j = i + 1;
			int k = arr.length - 1;
			while (j < k) {
				int sum = arr[i] + arr[j] + arr[k];
				int diff = Math.abs(sum - target);
				if (diff == 0)
					return sum;
				if (diff < min) {
					min = diff;
					result = sum;
				}
				if (sum < target)
					j++;
				else
					k--;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		ThreeSum ts = new ThreeSum();
		int[] s = { -1, 2, 1, -4 };
		System.out.println(ts.findThreeSums(s));
		System.out.println(ts.threeSumClosest(s, 1));
	}

}
