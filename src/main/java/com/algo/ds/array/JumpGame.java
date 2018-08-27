package com.algo.ds.array;

public class JumpGame {

	/**
	 * Given an array of non-negative integers, you are initially positioned at the first index of the
	 * array. Each element in the array represents your maximum jump length at that position. Determine
	 * if you are able to reach the last index.
	 *
	 * Time complexity O(n) Space complexity O(1)
	 *
	 * https://leetcode.com/problems/jump-game/.
	 * 
	 * Example 1:
	 * 
	 * Input: [2,3,1,1,4] Output: true Explanation: Jump 1 step from index 0 to 1, then 3 steps to the
	 * last index. <br/>
	 * Example 2:
	 * 
	 * Input: [3,2,1,0,4] Output: false Explanation: You will always arrive at index 3 no matter what.
	 * Its maximum jump length is 0, which makes it impossible to reach the last index.
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
	 * Given an array of non-negative integers, you are initially positioned at the first index of the
	 * array. Each element in the array represents your maximum jump length at that position. Your goal
	 * is to reach the last index in the minimum number of jumps.
	 *
	 * Input: [2,3,1,1,4] Output: 2 Explanation: The minimum number of jumps to reach the last index is
	 * 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
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

    public static void main(String args[]) {
        JumpGame jumpGame = new JumpGame();
        int[] nums = {3, 2, 3, 0, 2, 1};
        System.out.println(jumpGame.jump(nums));
        int[] nums1 = {3, 0, 2, 0, 0, 1};
        System.out.println(jumpGame.canJump(nums1));
    }
}
