package com.leetcode;

import com.algo.common.TreeNode;

/**
 * Category : Medium
 *
 */
public class HouseRobber {

	/**
	 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
	 * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system
	 * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
	 * 
	 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum
	 * amount of money you can rob tonight without alerting the police.
	 * 
	 * Example 1:
	 * 
	 * Input: nums = [1,2,3,1] Output: 4 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3). Total
	 * amount you can rob = 1 + 3 = 4.
	 * 
	 * Example 2:
	 * 
	 * Input: nums = [2,7,9,3,1] Output: 12 Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house
	 * 5 (money = 1). Total amount you can rob = 2 + 9 + 1 = 12.
	 * 
	 * T - O(n) S - O(n)
	 */
	public int robHousesInStraightLine(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		if (nums.length == 1) {
			return nums[0];
		}

		int[] hash = new int[nums.length];
		hash[0] = nums[0];
		hash[1] = Math.max(nums[0], nums[1]);

		for (int i = 2; i < nums.length; i++) {
			hash[i] = Math.max(hash[i - 1], hash[i - 2] + nums[i]);
		}

		return hash[nums.length - 1];
	}

	/**
	 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
	 * stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the
	 * last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police
	 * if two adjacent houses were broken into on the same night.
	 * 
	 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum
	 * amount of money you can rob tonight without alerting the police.
	 * 
	 * Example 1:
	 * 
	 * Input: [2,3,2] Output: 3 Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
	 * because they are adjacent houses.
	 * 
	 * Example 2:
	 * 
	 * Input: [1,2,3,1] Output: 4 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3). Total amount
	 * you can rob = 1 + 3 = 4.
	 * 
	 * Since House[1] and House[n] are adjacent, they cannot be robbed together. Therefore, the problem becomes to rob
	 * either House[1] to House[n-1] or House[2] to House[n], depending on which choice offers more money. Now the
	 * problem has degenerated to the House Robber, which is already been solved.
	 */
	public int robHousesInCircle(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		} else if (nums.length == 1) {
			return nums[0];
		} else if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}

		int[] hash1 = new int[nums.length];
		int[] hash2 = new int[nums.length];

		// for thief robbing from H(0) to H(n-2)
		hash1[0] = nums[0];
		hash1[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i <= nums.length - 2; i++) {
			hash1[i] = Math.max(hash1[i - 1], hash1[i - 2] + nums[i]);
		}

		// for thief robbing from H(1) to H(n-1)
		hash2[1] = nums[1];
		hash2[2] = Math.max(nums[1], nums[2]);
		for (int i = 3; i <= nums.length - 1; i++) {
			hash2[i] = Math.max(hash2[i - 1], hash2[i - 2] + nums[i]);
		}

		return Math.max(hash1[nums.length - 2], hash2[nums.length - 1]);
	}

	/**
	 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called
	 * the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief
	 * realized that "all houses in this place forms a binary tree". It will automatically contact the police if two
	 * directly-linked houses were broken into on the same night.
	 * 
	 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
	 * 
	 * Example 1:
	 * 
	 * Input: [3,2,3,null,3,null,1]
	 * 
	 * 3 / \ 2 3 \ \ 3 1
	 * 
	 * Output: 7
	 * 
	 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
	 * 
	 * Example 2:
	 * 
	 * Input: [3,4,5,1,3,null,1]
	 * 
	 * 3 / \ 4 5 / \ \ 1 3 1
	 * 
	 * Output: 9
	 * 
	 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
	 * 
	 * TODO : understand clearly
	 */
	public int robHousesInTree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return root.data;
		}

		int[] money = new int[2];
		money = getMaxRobMoney(root);

		return money[0];
	}

	int[] getMaxRobMoney(TreeNode root) {
		// deal with the leaf node (node in the last level of the tree)
		if (root.left == null && root.right == null) {
			return new int[] { root.data, 0 };
		}

		// deal with node in the last secondary level
		if (isLastSecondLevelNode(root)) {
			int nextLevelMoney = 0; // store the sum of all the child, represents dp[nextLevel]
			if (root.left != null) {
				nextLevelMoney += root.left.data;
			}
			if (root.right != null) {
				nextLevelMoney += root.right.data;
			}

			// currentLevelMax store the max money that can steal in current sub tree
			int currentLevelMax = Math.max(root.data, nextLevelMoney);

			// return the max money that can steal in current sub tree and the sum of all the child
			// remember you always need dp[i-2] and dp[i-1] when compute dp[i] in problem house robber
			// as the state transition equation is dp[i] = max(dp[i-1], dp[i-2]+nums[i])
			return new int[] { currentLevelMax, nextLevelMoney };
		}

		int[] leftSubTreeMoney = new int[2];
		if (root.left != null) {
			leftSubTreeMoney = getMaxRobMoney(root.left);
		}
		int[] rightSubTreeMoney = new int[2];
		if (root.right != null) {
			rightSubTreeMoney = getMaxRobMoney(root.right);
		}

		int nextLevelMoney = leftSubTreeMoney[0] + rightSubTreeMoney[0];
		int nextNextLevelMoney = leftSubTreeMoney[1] + rightSubTreeMoney[1];

		// just like dp[i] = max(dp[i-1], dp[i-2]+nums[i]) in problem house robber, the state transition equation
		// in this problem is dp[currentLevel] = max(dp[nextLevel], dp[nextNextLevel] + currentNode.val)
		return new int[] { Math.max(nextLevelMoney, nextNextLevelMoney + root.data), nextLevelMoney };
	}

	boolean isLastSecondLevelNode(TreeNode node) {
		// node's left child and right child are not null and are both leaf nodes
		if (node.left != null && node.left.left == null && node.left.right == null && node.right != null
				&& node.right.left == null && node.right.right == null) {
			return true;
		}

		// node's left child is null and right child is leaf node
		if (node.left == null && node.right != null && node.right.left == null && node.right.right == null) {
			return true;
		}

		// node's right child is null and left child is leaf node
		if (node.right == null && node.left != null && node.left.left == null && node.left.right == null) {
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		HouseRobber hr = new HouseRobber();
		System.out.println(hr.robHousesInStraightLine(new int[] { 1, 2, 3, 1 }));
		System.out.println(hr.robHousesInStraightLine(new int[] { 2, 7, 9, 3, 1 }));

		System.out.println(hr.robHousesInCircle(new int[] { 2, 3, 2 }));
		System.out.println(hr.robHousesInCircle(new int[] { 1, 2, 3, 1 }));
	}
}
