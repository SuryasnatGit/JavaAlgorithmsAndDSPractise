package com.algo.ds.array;

/**
 * Category : Medium
 * 
 * TODO : to understand all cases
 *
 */
public class JumpGame {

	/**
	 * Given an array of non-negative integers, you are initially positioned at the first index of the array. Each
	 * element in the array represents your maximum jump length at that position. Determine if you are able to reach the
	 * last index.
	 *
	 * Time complexity O(n) Space complexity O(1)
	 *
	 * https://leetcode.com/problems/jump-game/.
	 * 
	 * Example 1:
	 * 
	 * Input: [2,3,1,1,4] Output: true Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index. <br/>
	 * Example 2:
	 * 
	 * Input: [3,2,1,0,4] Output: false Explanation: You will always arrive at index 3 no matter what. Its maximum jump
	 * length is 0, which makes it impossible to reach the last index.
	 */
	public boolean canJump(int[] nums) {
		int jump = 0;
		int i;
		for (i = 0; i < nums.length && i <= jump; i++) {
			jump = Math.max(jump, i + nums[i]);
		}
		return i == nums.length;
	}

	/**
	 * Given an array of non-negative integers, you are initially positioned at the first index of the array. Each
	 * element in the array represents your maximum jump length at that position. Your goal is to reach the last index
	 * in the minimum number of jumps.
	 *
	 * Input: [2,3,1,1,4] Output: 2 Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step
	 * from index 0 to 1, then 3 steps to the last index.
	 * 
	 * Time complexity O(n) Space complexity O(1)
	 *
	 * https://leetcode.com/problems/jump-game-ii/
	 */
	public int jump(int[] nums) {
		int current = 0;
		int max = 0;
		int count = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			max = Math.max(max, i + nums[i]);
			if (current == i) {
				count++;
				current = max;
			}
		}
		return count;
	}

	/**
	 * A naive approach is to start from the first element and recursively call for all the elements reachable from
	 * first element. The minimum number of jumps to reach end from first can be calculated using minimum number of
	 * jumps needed to reach end from the elements reachable from first.
	 * 
	 * minJumps(start, end) = Min ( minJumps(k, end) ) for all k reachable from start
	 * 
	 * 
	 * @param nums
	 * @param low
	 * @param high
	 * @return
	 */
	public int minJumps_recursive(int[] nums, int low, int high) {
		// base case when source and destination are same
		if (low == high)
			return 0;

		// if none of the elements are reachable from source
		if (nums[low] == 0)
			return Integer.MAX_VALUE;

		// traverse through all points reachable from nums[low]. Recursively get the minimum number of jumps
		// needed to reach nums[high] from these reachable points
		int min = Integer.MAX_VALUE;
		for (int i = low + 1; i <= high && i <= low + nums[low]; i++) {
			int jumps = minJumps_recursive(nums, i, high);
			if (jumps != Integer.MAX_VALUE && jumps + 1 < min)
				min = jumps + 1;
		}
		return min;
	}

	/**
	 * In this method, we build a jumps[] array from left to right such that jumps[i] indicates the minimum number of
	 * jumps needed to reach arr[i] from arr[0]. Finally, we return jumps[n-1].
	 * 
	 * Time Complexity: O(n^2)
	 * 
	 * 
	 * @param nums
	 * @return
	 */
	public int minJumps_dp(int[] nums) {
		int n = nums.length;
		int[] jumps = new int[n]; // jumps[n-1] will hold the result

		// if first element is 0 then end cannot be reached
		if (n == 0 || nums[0] == 0)
			return Integer.MAX_VALUE;

		jumps[0] = 0;
		// Find the minimum number of jumps to reach arr[i] from arr[0], and assign this value to jumps[i]
		for (int i = 1; i < n; i++) {
			jumps[i] = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				if (i <= j + nums[j] && jumps[j] != Integer.MAX_VALUE) {
					jumps[i] = Math.min(jumps[i], jumps[j] + 1);
				}
			}
		}
		return jumps[n - 1];
	}

	/**
	 * complexity - O(n)
	 * 
	 * @param nums
	 * @return
	 */
	public int minJumps_faster(int[] nums) {
		int n = nums.length;
		if (n <= 1)
			return 0;

		// if jump not possible
		if (nums[0] == 0)
			return -1;

		// initialize
		int maxReach = nums[0];
		int step = nums[0];
		int jump = 1;

		for (int i = 1; i < n; i++) {
			// if reached the end of array
			if (i == n - 1)
				return jump;

			// update max reach
			maxReach = Math.max(maxReach, i + nums[i]);

			// we use a step to get to the current index, so decrease step by 1
			step--;

			// If no further steps left
			if (step == 0) {
				// we must have used a jump
				jump++;

				// Check if the current index/position or lesser index
				// is the maximum reach point from the previous indexes
				if (i >= maxReach)
					return -1;

				// re-initialize the steps to the amount
				// of steps to reach maxReach from position i.
				step = maxReach - i;
			}
		}
		return -1;
	}

	public static void main(String args[]) {
		JumpGame jumpGame = new JumpGame();
		int[] nums = { 3, 2, 3, 0, 2, 1 };
		System.out.println(jumpGame.jump(nums));
		int[] nums1 = { 3, 0, 2, 0, 0, 1 };
		System.out.println(jumpGame.minJumps_recursive(nums1, 0, nums1.length - 1));
		System.out.println(jumpGame.minJumps_recursive(nums, 0, nums.length - 1));
		System.out.println(jumpGame.minJumps_dp(nums));
	}
}
