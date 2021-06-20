package com.leetcode;

import java.util.HashMap;
import java.util.Map;

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
 * Tags : HashTable
 *
 */
public class ArrayContainsDuplicate {

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

	public static void main(String[] args) {
		ArrayContainsDuplicate ad = new ArrayContainsDuplicate();
		System.out.println(ad.containsNearbyDuplicate(new int[] { 1, 2, 3, 1 }, 3));
		System.out.println(ad.containsNearbyDuplicate(new int[] { 1, 0, 1, 1 }, 1));
		System.out.println(ad.containsNearbyDuplicate(new int[] { 1, 2, 3, 1, 2, 3 }, 2));
	}
}