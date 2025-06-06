package com.leetcode;

import com.algo.common.TreeNode;

/**
 * 
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is
 * changed to the original key plus the sum of all keys greater than the original key in BST.
 * 
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 * 
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * 
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * 
 * Both the left and right subtrees must also be binary search trees.
 * 
 * Example 1:
 * 
 * Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 
 * Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * 
 * Example 2:
 * 
 * Input: root = [0,null,1]
 * 
 * Output: [1,null,1]
 * 
 * Category : Medium
 */
public class BiggerTree {
	public static void main(String[] args) {
		TreeNode n3 = new TreeNode(13);
		TreeNode n9 = new TreeNode(9);
		TreeNode n20 = new TreeNode(20);
		TreeNode n15 = new TreeNode(15);
		TreeNode n7 = new TreeNode(27);

		n3.left = n9;
		n3.right = n20;
		n20.left = n15;
		n20.right = n7;

		BiggerTree bt = new BiggerTree();
		bt.convertBST(n3);

		System.out.println(n3.data);
	}

	public static int sum;

	public TreeNode convertBST(TreeNode root) {
		sum = 0;
		solve(root);
		return root;
	}

	public void solve(TreeNode root) {
		if (root == null) {
			return;
		}
		solve(root.right);
		int curr = root.data;
		root.data = sum + root.data;
		sum += curr;
		solve(root.left);

	}
}
