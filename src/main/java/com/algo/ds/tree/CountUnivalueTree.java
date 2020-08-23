package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 *
 * Given a binary tree, count the number of uni-value subtrees. A Uni-value subtree means all nodes of the subtree have
 * the same value.
 *
 * https://leetcode.com/problems/count-univalue-subtrees/
 */
public class CountUnivalueTree {
	private int count = 0;

	public int countUnivalSubtrees(TreeNode root) {
		countUnivalSubtreesUtil(root, 0);
		return count;
	}

	private int countUnivalSubtreesUtil(TreeNode root, int val) {
		if (root == null) {
			return val;
		}
		int left = countUnivalSubtreesUtil(root.left, root.data);
		int right = countUnivalSubtreesUtil(root.right, root.data);
		if (left == right && left == root.data) {
			count++;
			return root.data;
		} else {
			return Integer.MAX_VALUE;
		}
	}
}
