package com.algo.ds.tree;

import java.util.Stack;

import com.algo.common.TreeNode;

/**
 * CTCI - 4.5
 * 
 * Youtube link - https://youtu.be/MILxfAbIhrE
 * 
 * Given a binary tree, return true if it is binary search tree else return false. CTCI Ch 4 Problem 4.5
 * 
 * Solution Keep min, max for every recursion. Initial min and max is very small and very larger number. Check if
 * root.data is in this range. When you go left pass min and root.data and for right pass root.data and max.
 * 
 * Time complexity is O(n) since we are looking at all nodes.
 * 
 * Test cases: Null tree 1 or 2 nodes tree Binary tree which is actually BST Binary tree which is not a BST
 */
public class IsBST {

	/**
	 * Approach 2 - iterative way
	 *
	 * T - O(n) S - O(n)
	 */
	public boolean isBSTIterative(TreeNode root) {
		if (root == null) {
			return true;
		}

		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;
		int prev = Integer.MIN_VALUE;

		while (!stack.isEmpty() || node != null) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}

			node = stack.pop();

			if (node.data <= prev) {
				return false;
			}
			prev = node.data;
			node = node.right;
		}

		return true;
	}

	/**
	 * Approach 3 - using inorder traversal.
	 * 
	 * 1) Do In-Order Traversal of the given tree and store the result in a temp array. 3) Check if the temp array is
	 * sorted in ascending order, if it is, then the tree is BST.
	 * 
	 * Time Complexity: O(n)
	 * 
	 * We can avoid the use of Auxiliary Array. While doing In-Order traversal, we can keep track of previously visited
	 * node. If the value of the currently visited node is less than the previous value, then tree is not BST.
	 * 
	 * @return
	 */
	private TreeNode prev = null; // to keep track of previous node in inorder traversal

	public boolean isBST_inorderTraversal(TreeNode root) {
		if (root != null) {
			if (!isBST_inorderTraversal(root.left))
				return false;
			if (prev != null && root.data <= prev.data)
				return false;
			prev = root;
			return isBST_inorderTraversal(root.right);
		}
		return true;
	}

	public static void main(String args[]) {
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);

		TreeNode root1 = new TreeNode(Integer.MIN_VALUE);

		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(1);

		IsBST isBST = new IsBST();
		System.out.println(isBST.isBSTIterative(root));
		System.out.println(isBST.isBSTIterative(root1));
		System.out.println(isBST.isBSTIterative(root2));
		System.out.println(isBST.isBST_inorderTraversal(root));
		System.out.println(isBST.isBST_inorderTraversal(root1));
		System.out.println(isBST.isBST_inorderTraversal(root2));
	}
}
