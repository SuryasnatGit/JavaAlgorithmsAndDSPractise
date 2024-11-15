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
 * 
 * Category : Medium
 */
public class IncreasingTripletSubsequence {

	// Method to check if there exists an increasing triplet in the array.
	public boolean increasingTriplet(int[] nums) {
		// Initialize two variables to hold the smallest and the middle value found so far.
		int smallest = Integer.MAX_VALUE;
		int middle = Integer.MAX_VALUE;

		// Iterate over each number in the array.
		for (int num : nums) {
			// If the current number is greater than the middle value found,
			// an increasing triplet sequence exists.
			if (num > middle) {
				return true;
			}

			// If the current number is the smallest we've seen so far,
			// we update the smallest value.
			if (num <= smallest) {
				smallest = num;
			} else {
				// Otherwise, if it's between the smallest and the middle value,
				// we update the middle value.
				middle = num;
			}
		}

		// If we did not return true within the loop, then no increasing
		// triplet sequence was found.
		return false;
	}

	public static void main(String args[]) {
		IncreasingTripletSubsequence tripletSubsequence = new IncreasingTripletSubsequence();
		int input[] = { 9, 10, -2, 12, 6, 7, -1 };
		System.out.print(tripletSubsequence.increasingTriplet(input));
	}
}
