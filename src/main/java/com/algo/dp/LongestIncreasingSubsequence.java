package com.algo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a given sequence
 * such that all elements of the subsequence are sorted in increasing order. For example, the length of LIS for {10, 22,
 * 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}.
 * 
 * @author surya
 *
 */
public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
		int[] nums = { 10, 22, 9, 33, 21, 50, 41, 60, 20, 100 };
		System.out.println(lis.lis_dp_naive(nums));
		System.out.println(lis.lis_recursive(nums, 0, nums.length, Integer.MIN_VALUE));
		System.out.println(lis.lis_binarySearch(nums));
	}

	/**
	 * Time - Exponential, S - O(1)
	 */
	public int lis_recursive(int[] nums, int start, int end, int prev) {
		if (start == end)
			return 0;

		// case 1 - exclude current element and recur for remaining elements
		int exclude = lis_recursive(nums, start + 1, end, prev);

		// case 2 - include current element in LIS if its greater than prev elem
		int include = 0;
		if (nums[start] > prev) {
			include = 1 + lis_recursive(nums, start + 1, end, nums[start]);
		}

		return Math.max(exclude, include);
	}

	/**
	 * time complexity - O(n^2) space - O(n)
	 * 
	 * @param nums
	 * @return
	 */
	public int lis_dp_naive(int[] nums) {
		// base condition
		if (nums == null || nums.length == 0)
			return 0;

		// prefill
		int[] max = new int[nums.length];
		Arrays.fill(max, 1);

		int result = 1;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					max[i] = Math.max(max[i], max[j] + 1);
				}
			}
			result = Math.max(max[i], result);
		}

		return result;
	}

	/**
	 * for each num in nums if(list.size()==0) add num to list else if(num > last element in list) add num to list else
	 * replace the element in the list which is the smallest but bigger than num.
	 * 
	 * time complexity - O(n log n), Space complexity - O(n)
	 * 
	 * @param nums
	 * @return
	 */
	public int lis_binarySearch(int[] nums) {
		// base condition
		if (nums == null || nums.length == 0)
			return 0;

		List<Integer> list = new ArrayList<>();
		for (int num : nums) {
			if (list.size() == 0 || num > list.get(list.size() - 1)) {
				list.add(num);
			} else {
				// do a binary search and replace element
				int i = 0;
				int j = list.size() - 1;
				while (i < j) {
					int mid = (i + j) / 2;
					if (list.get(mid) < num) {
						i = mid + 1;
					} else {
						j = mid;
					}
				}
				list.set(j, num);
			}
		}
		return list.size();
	}

	/**
	 * Iterative function to find longest increasing subsequence of given array. T - O(n^2) S - O(n^2)
	 * 
	 * @param arr
	 */
	public void findLIS(int[] arr) {
		// LIS.get(i) stores the longest increasing subsequence of subarray
		// arr[0..i] that ends with arr[i]
		List<List<Integer>> LIS = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			LIS.add(i, new ArrayList<>());
		}

		// LIS[0] denotes longest increasing subsequence ending with arr[0]
		LIS.get(0).add(arr[0]);

		// start from second element in the array
		for (int i = 1; i < arr.length; i++) {
			// do for each element in subarray arr[0..i-1]
			for (int j = 0; j < i; j++) {
				// find longest increasing subsequence that ends with arr[j]
				// where arr[j] is less than the current element arr[i]

				if (arr[j] < arr[i] && LIS.get(j).size() > LIS.get(i).size()) {
					LIS.set(i, new ArrayList<>(LIS.get(j)));
				}
			}

			// include arr[i] in LIS.get(i)
			LIS.get(i).add(arr[i]);
		}

		// uncomment below lines to print contents of vector LIS
		/*
		 * for (int i = 0; i < arr.length; i++) { System.out.println("LIS[" + i + "] - " + LIS.get(i)); }
		 */

		// j will contain index of LIS
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if (LIS.get(j).size() < LIS.get(i).size()) {
				j = i;
			}
		}

		// print LIS
		System.out.print(LIS.get(j));
	}

}
