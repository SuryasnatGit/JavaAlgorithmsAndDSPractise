package com.algo.ds.array;

public class MaximumConsequetiveOnes {

	/**
	 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
	 * 
	 * Return the length of the longest (contiguous) subarray that contains only 1s.
	 * 
	 * Example 1:
	 * 
	 * Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
	 * 
	 * Output: 6
	 * 
	 * Explanation:
	 * 
	 * [1,1,1,0,0,1,1,1,1,1,1]
	 * 
	 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
	 * 
	 * Example 2:
	 * 
	 * Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
	 * 
	 * Output: 10
	 * 
	 * Explanation:
	 * 
	 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
	 * 
	 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
	 *
	 */
	public int longestOnes(int[] A, int K) {
		if (A == null || A.length == 0) {
			return 0;
		}

		int left = 0, right;
		for (right = 0; right < A.length; right++) {
			if (A[right] == 0) {
				K--;
			}

			// if num of 0s exceed K
			if (K < 0) {
				// move sliding window so increase K
				if (A[left] == 0) {
					K++;
				}
				left++;
			}
		}

		return right - left;
	}

	public int maxLength(int[] arr) {
		int count = 0;
		int maxIndex = -1;
		int prev0Index = -1;
		int maxCount = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1)
				count++;
			else {
				count = i - prev0Index;
				prev0Index = i;
			}

			if (count > maxCount) {
				maxCount = count;
				maxIndex = prev0Index;
			}
		}

		return maxIndex;
	}

	// Find index of 0 to replaced with 1 to get maximum sequence
	// of continuous 1's using sliding window technique
	public int findIndexofZero(int[] A) {
		int left = 0; // left represents window's starting index
		int count = 0; // stores number of zeros in current window
		int max_count = 0; // stores maximum number of 1's (including 0)

		int max_index = -1; // stores index of 0 to be replaced
		int prev_zero_index = -1; // stores index of previous zero

		// maintain a window [left..i] containing at-most one zero
		for (int i = 0; i < A.length; i++) {
			// if current element is 0, update prev_zero_index and
			// increase count of zeros in current window by 1
			if (A[i] == 0) {
				prev_zero_index = i;
				count++;
			}

			// window becomes unstable if number of zeros in it becomes 2
			if (count == 2) {
				// remove elements from the window's left side till
				// we found a zero
				while (A[left] != 0) {
					left++;
				}

				// remove leftmost 0 so that window becomes stable again
				left++;

				// decrement count as 0 is removed
				count = 1;
			}

			// when we reach here, the window [left..i] contains only
			// at-most one zero we update maximum count and index of 0
			// to be replaced if required
			if (i - left + 1 > max_count) {
				max_count = i - left + 1;
				max_index = prev_zero_index;
			}
		}

		// return index of 0 to be replaced or -1 if array contains all 1's
		return max_index;
	}

	public static void main(String[] args) {
		MaximumConsequetiveOnes m = new MaximumConsequetiveOnes();
		System.out.println(m.maxLength(new int[] { 0, 0, 1, 0, 1, 1, 1, 0, 1, 1 }));
		System.out.println(m.findIndexofZero(new int[] { 0, 0, 1, 0, 1, 1, 1, 0, 1, 1 }));
		System.out.println(m.longestOnes(new int[] { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 }, 2));
		System.out.println(m.longestOnes(new int[] { 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 }, 3));
	}
}
