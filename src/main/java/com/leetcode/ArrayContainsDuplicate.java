package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ArrayContainsDuplicate {

	/**
	 * Determine whether an array of integers, nums, contains any duplicate elements.
	 * 
	 * T - O(n) S - O(n)
	 */
	public boolean containsDuplicates(int[] arr) {
		Set<Integer> set = new HashSet<>();

		for (int num : arr) {
			if (set.contains(num)) {
				return true;
			}
			set.add(num);
		}
		return false;
	}

	/**
	 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
	 * such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
	 * 
	 * Example 1: Input: nums = [1,2,3,1] , k = 3 Output: true
	 * 
	 * Example 2: Input: nums = [1,0,1,1] , k = 1 Output: true
	 * 
	 * Example 3: Input: nums = [1,2,3,1,2,3] , k = 2 Output: false
	 * 
	 * T- O(n) S - O(n)
	 *
	 * 
	 */
	public boolean containsNearbyDuplicate(int[] arr, int k) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				if (i - map.get(arr[i]) <= k) {
					return true;
				}
			}
			map.put(arr[i], i);
		}

		return false;
	}

	/**
	 * Given an array of integers nums, find out whether there are two distinct indices i and j in the array such that
	 * the difference between nums[i] and nums[j] is at most valueDiff and the difference between i and j is at most
	 * indexDiff.
	 * 
	 * Find a pair of indices (i, j) such that:
	 * 
	 * i != j,
	 * 
	 * abs(i - j) <= indexDiff.
	 * 
	 * abs(nums[i] - nums[j]) <= valueDiff
	 * 
	 * Example 1:
	 * 
	 * Input: nums = [1,2,3,1], indexDiff = 3, valueDiff = 0 Output: true
	 * 
	 * Explanation: We can choose (i, j) = (0, 3). We satisfy the three conditions:
	 * 
	 * i != j --> 0 != 3
	 * 
	 * abs(i - j) <= indexDiff --> abs(0 - 3) <= 3
	 * 
	 * abs(nums[i] - nums[j]) <= valueDiff --> abs(1 - 1) <= 0
	 * 
	 * Example 2:
	 * 
	 * Input: nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3 Output: false
	 * 
	 * Explanation: After trying all the possible pairs (i, j), we cannot satisfy the three conditions, so we return
	 * false.
	 * 
	 * T - since the size of the sorted set is capped by the indexDiff, let’s use k to denote indexDiff as the maximum
	 * number of elements the sorted set can contain. The complexity now becomes O(k) for insertion and removal, and the
	 * complexity of bisect_left is O(log k). Therefore, the time complexity is O(n * log k) where n is the length of
	 * the input array and k is indexDiff.
	 * 
	 * S - The space complexity is determined by the maximum size of the sorted set s, which can grow up to the largest
	 * indexDiff. Therefore, the space complexity is O(k), where k is the maximum number of entries in the SortedSet,
	 * which is bounded by indexDiff.
	 * 
	 * Category : Hard
	 */
	public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
		// Use TreeSet to maintain a sorted set.(guaranteed log(n) time cost for the basic operations (add, remove and
		// contains).)
		TreeSet<Integer> set = new TreeSet<>();

		for (int i = 0; i < nums.length; ++i) {
			// Find the successor of current element
			Integer ceilingValue = set.ceiling(nums[i]);
			if (ceilingValue != null && (ceilingValue - nums[i]) <= valueDiff) {
				return true;
			}

			// Find the predecessor of current element
			Integer floorValue = set.floor(nums[i]);
			if (floorValue != null && (nums[i] - floorValue) <= valueDiff) {
				return true;
			}

			set.add(nums[i]);
			// If the sorted set size exceeded the allowed index difference, remove the oldest value.
			if (set.size() > indexDiff) {
				set.remove(nums[i - indexDiff]);
			}
		}

		return false;
	}

	public static void main(String[] args) {
		ArrayContainsDuplicate ad = new ArrayContainsDuplicate();

		System.out.println(ad.containsDuplicates(new int[] { 1, 2, 3, 1 })); // true
		System.out.println(ad.containsDuplicates(new int[] { 1, 2, 3 })); // false

		System.out.println(ad.containsNearbyDuplicate(new int[] { 1, 2, 3, 1 }, 3));// true
		System.out.println(ad.containsNearbyDuplicate(new int[] { 1, 0, 1, 1 }, 1));// true
		System.out.println(ad.containsNearbyDuplicate(new int[] { 1, 2, 3, 1, 2, 3 }, 2)); // false

		System.out.println(ad.containsNearbyAlmostDuplicate(new int[] { 1, 2, 3, 1 }, 3, 0)); // true
		System.out.println(ad.containsNearbyAlmostDuplicate(new int[] { 1, 5, 9, 1, 5, 9 }, 2, 3)); // false

	}
}