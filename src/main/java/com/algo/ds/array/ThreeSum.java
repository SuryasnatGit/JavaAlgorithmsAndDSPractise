package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Category : Medium
 *
 */
public class ThreeSum {

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
	 * This problem can be solved by using two pointers. Time complexity is O(n^2). Space complexity varies from O(log
	 * n) to O(n) depending on implementation of sorting algorithm
	 * 
	 * To avoid duplicate, we can take advantage of sorted arrays, i.e., move pointers by >1 to use same element only
	 * once.
	 * 
	 * A typical k-sum problem. Time is N to the power of (k-1).
	 * 
	 * 
	 */
	public List<List<Integer>> findThreeSumUniqueTriplets(int[] nums) {

		// make use of set to avoid duplicates.
		Set<List<Integer>> result = new HashSet<>();

		if (nums == null || nums.length < 3) {
			return new ArrayList<>();
		}

		// sort the array
		Arrays.sort(nums); // O(N log N)

		// O(N*N) = O(N^2)
		for (int i = 0; i < nums.length - 2; i++) {
			// check duplicates
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}

			int j = i + 1;
			int k = nums.length - 1;
			int target = 0;

			while (j < k) {

				int sum = nums[i] + nums[j] + nums[k];

				// 3 cases.
				// equals to 0
				if (sum == target) {
					List<Integer> temp = new ArrayList<>();
					temp.add(nums[i]);
					temp.add(nums[j]);
					temp.add(nums[k]);
					result.add(temp);
					j++;
					k--;

					// check duplicates
					while (j < k && nums[j] == nums[j - 1]) {
						j++;
					}
					while (j < k && nums[k] == nums[k + 1]) {
						k--;
					}
				} else if (sum < target) { // < 0
					j++;
				} else { // > 0
					k--;
				}
			}
		}

		return new ArrayList<>(result);
	}

	/**
	 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
	 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
	 * 
	 * For example, given array S = {-1 2 1 -4}, and target = 1. The sum that is closest to the target is 2. (-1 + 2 + 1
	 * = 2).
	 * 
	 * Time Complexity is O(n^2). Space complexity varies from O(log n) to O(n) depending on implementation of sorting
	 * algorithm
	 * 
	 * 
	 */
	public int threeSumClosestToTarget(int[] arr, int target) {
		int min = Integer.MAX_VALUE;
		int result = 0;

		if (arr == null || arr.length < 3)
			return result;

		Arrays.sort(arr);

		for (int i = 0; i < arr.length; i++) {
			int j = i + 1;
			int k = arr.length - 1;
			while (j < k) {
				int sum = arr[i] + arr[j] + arr[k];
				int diff = Math.abs(sum - target);
				if (diff == 0)
					return sum;

				if (diff < Math.abs(min)) {
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

	/**
	 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n
	 * that satisfy the condition nums[i] + nums[j] + nums[k] < target.
	 *
	 * https://leetcode.com/problems/3sum-smaller/.
	 * 
	 * For example, given nums = [-2, 0, 1, 3], and target = 2.
	 * <p>
	 * Return 2. Because there are two triplets which sums are less than 2:
	 * <p>
	 * [-2, 0, 1] [-2, 0, 3]
	 * 
	 * Complexity - O(n^2)
	 * 
	 */
	public int numberOfTripletsSmallerThanTarget(int[] nums, int target) {
		if (nums == null || nums.length < 3) {
			return 0;
		}

		Arrays.sort(nums);

		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			int j = i + 1;
			int k = nums.length - 1;
			while (j < k) {
				if (nums[i] + nums[j] + nums[k] >= target) {
					k--;
				} else {
					count += k - j;
					j++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		ThreeSum ts = new ThreeSum();
		System.out.println(ts.findThreeSumUniqueTriplets(new int[] { -1, 0, 1, 2, -1, -4 }));
		System.out
				.println(ts.findThreeSumUniqueTriplets(new int[] { -2, 0, 3, -1, 4, 0, 3, 4, 1, 1, 1, -3, -5, 4, 0 }));
		System.out.println(ts.threeSumClosestToTarget(new int[] { -1, 2, 1, -4 }, 1));
		System.out.println(ts.numberOfTripletsSmallerThanTarget(new int[] { -2, 0, 1, 3 }, 2));
	}

}
