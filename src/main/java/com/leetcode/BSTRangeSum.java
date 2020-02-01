package com.leetcode;

import java.util.Stack;

import com.algo.common.TreeNode;

/**
 * Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R
 * (inclusive).
 * 
 * The binary search tree is guaranteed to have unique values.
 * 
 * Example 1:
 * 
 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
 * 
 * Output: 32
 * 
 * Example 2:
 * 
 * Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * 
 * Output: 23
 *
 * Complexity Analysis
 * 
 * Time Complexity: O(N), where N is the number of nodes in the tree.
 * 
 * Space Complexity: O(H), where H is the height of the tree.
 * 
 */
public class BSTRangeSum {

	private int sum = 0;

	public int rangeSumRecursive(TreeNode root, int left, int right) {
		dfs(root, left, right);
		return sum;
	}

	private void dfs(TreeNode root, int left, int right) {
		if (root != null) {
			if (left <= root.data && root.data <= right) {
				sum += root.data;
			}

			if (left < root.data) {
				dfs(root.left, left, right);
			}

			if (root.data < right) {
				dfs(root.right, left, right);
			}
		}
	}

	// for dfs we use stack.
	public int rangeSumIterative(TreeNode root, int left, int right) {
		Stack<TreeNode> stack = new Stack<>();
		stack.add(root);

		int sum = 0;
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			if (node != null) {
				if (left <= node.data && node.data <= right) {
					sum += node.data;
				}

				if (left < node.data) {
					stack.push(node.left);
				}

				if (node.data < right) {
					stack.push(node.right);
				}
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		BSTRangeSum bst = new BSTRangeSum();
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.right = new TreeNode(15);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(7);
		root.right.right = new TreeNode(18);

		// System.out.println(bst.rangeSumRecursive(root, 7, 15));
		// System.out.println(bst.rangeSumRecursive(root, 5, 10));

		System.out.println(bst.rangeSumIterative(root, 7, 15));
	}

}
