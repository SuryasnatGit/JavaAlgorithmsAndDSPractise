package com.ctci.bigo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import com.algo.ds.array.FindElementsOccurringMoreThanNByKTimes.Pair;

/**
 * Given an array of distinct integer values, count the number of distinct pairs of integers that have difference k. For
 * example, given the array {1, 7, 5, 9, 2, 12, 3} and the difference k = 2, there are four pairs with difference 2: (1,
 * 3), (3, 5), (5, 7), (7, 9) .
 * 
 * LeetCode 532 - K-diff Pairs in an Array
 * 
 *
 */
public class IntegerPairWithDifferenceK {

	public void getPairs_hashing_duplicates(int[] nums, int k) {
		Set<Integer> set = new HashSet<>();

		for (int num : nums) {
			if (set.contains(num - k)) {
				System.out.println(num + "," + (num - k));
			}
			if (set.contains(num + k)) {
				System.out.println(num + "," + (num + k));
			}
			set.add(num);
		}
	}

	public void getPairs_hashing_distinct(int[] nums, int k) {
		Arrays.sort(nums);

		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < nums.length; i++) {
			// to avoid duplicates
			while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
				i++;
			}

			if (set.contains(nums[i] - k)) {
				System.out.println(nums[i] + "," + (nums[i] - k));
			}
			if (set.contains(nums[i] + k)) {
				System.out.println(nums[i] + "," + (nums[i] + k));
			}
			set.add(nums[i]);
		}
	}

	/**
	 * Another approach using java sliding window
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public int getPairs_slidingWindow(int[] nums, int k) {
		if (k < 0 || nums.length <= 1) {
			return 0;
		}

		Arrays.sort(nums);

		int count = 0;
		int left = 0;
		int right = 1;

		while (right < nums.length) {
			int firNum = nums[left];
			int secNum = nums[right];
			// If less than k, increase the right index
			if (secNum - firNum < k) {
				right++;
			}
			// If larger than k, increase the left index
			else if (secNum - firNum > k) {
				left++;
			}
			// If equal, move left and right to next different number
			else {
				count++;
				while (left < nums.length && nums[left] == firNum) {
					left++;
				}
				while (right < nums.length && nums[right] == secNum) {
					right++;
				}

			}
			// left and right should not be the same number
			if (right == left) {
				right++;
			}
		}
		return count;
	}

	/**
	 * self balancing BST like AVL tree or Red black tree. complexity - O(N log N)
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	// TODO
//	public List<Pair> getPairs_balancedBST(int[] arr, int k) {
//		return null;
//	}

	public static void main(String[] args) {
		IntegerPairWithDifferenceK intk = new IntegerPairWithDifferenceK();
		intk.getPairs_hashing_duplicates(new int[] { 1, 7, 5, 9, 2, 12, 3 }, 2);
		System.out.println();
		intk.getPairs_hashing_distinct(new int[] { 1, 7, 5, 9, 2, 12, 3 }, 2);
		System.out.println();
		System.out.println(intk.getPairs_slidingWindow(new int[] { 1, 7, 5, 9, 2, 12, 3 }, 2));
		System.out.println();

		intk.getPairs_hashing_duplicates(new int[] { 3, 1, 4, 1, 5 }, 2);
		System.out.println();
		intk.getPairs_hashing_distinct(new int[] { 3, 1, 4, 1, 5 }, 2);
		System.out.println();
		System.out.println(intk.getPairs_slidingWindow(new int[] { 3, 1, 4, 1, 5 }, 2));
		System.out.println();

		intk.getPairs_hashing_duplicates(new int[] { 1, 3, 1, 5, 4 }, 0);
		System.out.println();
		intk.getPairs_hashing_distinct(new int[] { 1, 3, 1, 5, 4 }, 0);
		System.out.println();
		System.out.println(intk.getPairs_slidingWindow(new int[] { 1, 3, 1, 5, 4 }, 0));
	}

}
