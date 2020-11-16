package com.algo.ds.array;

/**
 * Find if there exists an increasing triplet subsequence. Similar method to longest increasing subsequence in nlogn
 * time.
 * 
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * 
 * Formally the function should:
 * 
 * Return true if there exists i, j, k such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * 
 * Example 1:
 * 
 * Input: [1,2,3,4,5] Output: true
 * 
 * Example 2:
 * 
 * Input: [5,4,3,2,1] Output: false
 *
 * Time complexity is O(n) Space complexity is O(1)
 *
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 */
public class IncreasingTripletSubsequence {
	public boolean increasingTriplet(int[] nums) {
		int T[] = new int[3]; // O(1) space
		int len = 0;
		for (int i = 0; i < nums.length; i++) {// O(n) time
			boolean found = false;
			for (int j = 0; j < len; j++) {
				if (T[j] >= nums[i]) {
					T[j] = nums[i];
					found = true;
					break;
				}
			}
			if (!found) {
				T[len++] = nums[i];
			}
			if (len == 3) {
				return true;
			}
		}
		return false;
	}

	// O(N)
	public boolean increasingTripletN(int[] nums) {
		// start with two largest values, as soon as we find a number bigger than both, while both have been updated,
		// return true.
		int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
		for (int n : nums) {
			if (n <= small) {
				small = n;
			} // update small if n is smaller than both
			else if (n <= big) {
				big = n;
			} // update big only if greater than small but smaller than big
			else
				return true; // return if you find a number bigger than both
		}
		return false;
	}

	public static void main(String args[]) {
		IncreasingTripletSubsequence tripletSubsequence = new IncreasingTripletSubsequence();
		int input[] = { 9, 10, -2, 12, 6, 7, -1 };
		System.out.print(tripletSubsequence.increasingTriplet(input));
	}
}
