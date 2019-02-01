package com.algo.ds.tree;

/**
 * CTCI - 4.4
 *
 * Given a binary tree, determine if it is height-balanced. For this problem, a height-balanced
 * binary tree is defined as:
 * 
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Time complexity O(logn)
 *
 * Reference https://leetcode.com/problems/balanced-binary-tree/
 * 
 * Problem 4.4 - Trees and graphs. CTCI book.
 */
public class HeightBalanced {
	public boolean isBalanced(Node root) {
		return isBalancedUtil(root) >= 0;
	}

	private int isBalancedUtil(Node root) {
		if (root == null) {
			return 0;
		}
		int left = isBalancedUtil(root.left);
		if (left < 0) {
			return -1;
		}
		int right = isBalancedUtil(root.right);
		if (right < 0) {
			return -1;
		}
		int diff = Math.abs(left - right);
		return diff <= 1 ? (Math.max(left, right) + 1) : -1;
	}
}
