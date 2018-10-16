package com.algo.ds.array;

/**
 * References https://oj.leetcode.com/problems/trapping-rain-water/
 * https://leetcode.com/problems/trapping-rain-water/
 * 
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining. Input: [0,1,0,2,1,0,1,3,2,1,2,1] Output:
 * 6
 */
public class TrappingWater {

	public int trap(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int len = height.length;
		int left[] = new int[len];
		int right[] = new int[len];
		left[0] = height[0];
		right[len - 1] = height[len - 1];
		for (int i = 1; i < len; i++) {
			left[i] = Math.max(height[i], left[i - 1]);
			right[len - i - 1] = Math.max(height[len - i - 1], right[len - i]);
		}

		int maxWaterTrapped = 0;
		for (int i = 1; i < len - 1; i++) {
			int min = Math.min(left[i], right[i]);
			if (height[i] < min) {
				maxWaterTrapped += min - height[i];
			}
		}
		return maxWaterTrapped;
	}

	/**
	 * better and easy solution
	 * 
	 * @param height
	 * @return
	 */
	public int trap1(int[] height) {
		int res = 0;
		int lo = 0;
		int high = height.length - 1;
		int leftMax = 0;
		int rightMax = 0;
		while (lo < high) {
			if (height[lo] < height[high]) {
				if (height[lo] > leftMax)
					leftMax = height[lo];
				res += leftMax - height[lo];
				lo++;
			} else {
				if (height[high] > rightMax)
					rightMax = height[high];
				res += rightMax - height[high];
				high--;
			}
		}
		return res;
	}

	public static void main(String args[]) {
		int input[] = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		TrappingWater tw = new TrappingWater();
		System.out.println(tw.trap(input));
		System.out.println(tw.trap1(input));
	}
}
