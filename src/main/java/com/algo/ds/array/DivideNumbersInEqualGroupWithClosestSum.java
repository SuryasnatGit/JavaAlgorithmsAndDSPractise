package com.algo.ds.array;

import java.util.Arrays;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k
 * non-empty subsets whose sums are all equal.
 * 
 * Example 1:
 * 
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4 Output: True Explanation: It's possible to divide it into 4 subsets (5),
 * (1, 4), (2,3), (2,3) with equal sums.
 * 
 * Category : Hard
 * 
 * Tags :DFS,Backtracking
 */
public class DivideNumbersInEqualGroupWithClosestSum {

	/**
	 * Approach 1 - Simple solution using recursion.
	 * 
	 * Time Complexity: O(k^(N-k) * k!), where N is the length of nums, and k is as given. As we skip additional zeroes
	 * in groups, naively we will make O(k!) calls to search, then an additional O(k^(N-k))calls after every element of
	 * groups is nonzero.
	 * 
	 * Space Complexity: O(N), the space used by recursive calls to search in our call stack.
	 * 
	 */
	public boolean canPartitionKSubSets1(int[] num, int k) {
		if (num == null || num.length == 0 || k == 0) {
			return false;
		}

		Arrays.sort(num); // O(n log n)

		int sum = 0;
		for (int n : num) { // O(n)
			sum += n;
		}

		if (sum % k != 0 || sum < k) {
			return false;
		}

		sum /= k;

		return possible(num, sum, new int[k], num.length - 1);
	}

	private boolean possible(int[] nums, int sum, int[] p, int index) {
		if (index == -1) {// base condition for exit
			for (int s : p) {
				if (s != sum) {
					return false;
				}
			}

			return true;
		}

		int num = nums[index];

		for (int i = 0; i < p.length; i++) {
			if (p[i] + num <= sum) {
				p[i] += num;
				if (possible(nums, sum, p, index - 1)) {
					return true;
				}
				// back track
				p[i] -= num;
			}
		}
		return false;
	}

	public static void main(String args[]) {

		DivideNumbersInEqualGroupWithClosestSum dn = new DivideNumbersInEqualGroupWithClosestSum();
		System.out.println(dn.canPartitionKSubSets1(new int[] { 4, 3, 2, 3, 5, 2, 1 }, 4));

	}
}
