package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * Given a binary tree, find the maximum path sum. For this problem, a path is defined as any sequence of nodes from
 * some starting node to any node in the tree along the parent-child connections.
 * 
 * Time complexity O(n) Space complexity depends on depth of tree.
 *
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */
public class BinaryTreeMaximumPathSum {
	int max = 0;

	public int maxPathSum(TreeNode root) {
		max = Integer.MIN_VALUE;
		maxPathSumUtil(root);
		return max;
	}

	private int maxPathSumUtil(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = maxPathSumUtil(root.left);
		int right = maxPathSumUtil(root.right);
		if (left < 0) {
			left = 0;
		}
		if (right < 0) {
			right = 0;
		}
		max = Math.max(max, root.data + left + right);
		return root.data + Math.max(left, right);
	}

	public static void main(String[] args) {
		TreeNode n = new TreeNode(10);
		n.left = new TreeNode(2);
		n.left.left = new TreeNode(20);
		n.left.right = new TreeNode(1);
		n.right = new TreeNode(10);
		n.right.right = new TreeNode(-25);
		n.right.right.left = new TreeNode(3);
		n.right.right.right = new TreeNode(4);
		BinaryTreeMaximumPathSum b = new BinaryTreeMaximumPathSum();
		int sum = b.maxPathSum(n);
		System.out.println(sum);
	}
}
