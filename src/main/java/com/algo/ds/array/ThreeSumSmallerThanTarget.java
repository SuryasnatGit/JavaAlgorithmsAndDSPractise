package com.algo.ds.array;

import java.util.Arrays;

/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0
 * <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
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
public class ThreeSumSmallerThanTarget {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length < 3) {
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
		ThreeSumSmallerThanTarget sum = new ThreeSumSmallerThanTarget();
		int[] nums = new int[] { -2, 0, 1, 3 };
		int c = sum.threeSumSmaller(nums, 2);
		System.out.println(c);
	}
}
