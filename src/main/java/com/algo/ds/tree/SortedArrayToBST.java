package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 *
 * 
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * Time complexity O(n)
 * 
 * Reference https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class SortedArrayToBST {
	public TreeNode sortedArrayToBST(int[] nums) {
		return toBST(nums, 0, nums.length - 1);
	}

	private TreeNode toBST(int[] nums, int low, int high) {
		if (low > high) {
			return null;
		}
		int mid = (low + high) / 2;
		TreeNode n = new TreeNode(nums[mid]);
		n.left = toBST(nums, low, mid - 1);
		n.right = toBST(nums, mid + 1, high);
		return n;
	}
}
