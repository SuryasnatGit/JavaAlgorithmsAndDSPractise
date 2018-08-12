package com.algo.ds.array;

/**
 * https://leetcode.com/problems/first-missing-positive/.
 * 
 * Given an unsorted integer array, find the smallest missing positive integer.
 * 
 * Example 1: Input: [1,2,0] Output: 3 <br/>
 * Example 2:
 * 
 * Input: [3,4,-1,1] Output: 2 <br/>
 * Example 3:
 * 
 * Input: [7,8,9,11,12] Output: 1
 * 
 */
public class FirstPositiveMissing {
    public int firstMissingPositive(int[] nums) {
        int startOfPositive = segregate(nums);
        for (int i = startOfPositive; i < nums.length; i++) {
            int index = Math.abs(nums[i]) + startOfPositive - 1;
            if (index < nums.length) {
                nums[index] = -Math.abs(nums[index]);
            }
        }
        for (int i = startOfPositive; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i - startOfPositive + 1;
            }
        }
        return nums.length - startOfPositive + 1;
    }

    private int segregate(int[] nums) {
        int start = 0;
        int end = nums.length -1 ;
        while (start <= end) {
            if (nums[start] <= 0) {
                start++;
            } else if (nums[end] > 0) {
                end--;
            } else {
                swap(nums, start, end);
            }
        }
        return start;
    }

    private void swap(int[] nums, int start, int end) {
        int t = nums[start];
        nums[start] = nums[end];
        nums[end] = t;
    }

	public static void main(String[] args) {
		FirstPositiveMissing pos = new FirstPositiveMissing();
		int[] nums = new int[] { 3, 4, -1, 1 };
		System.out.println(pos.firstMissingPositive(nums));
	}
}
