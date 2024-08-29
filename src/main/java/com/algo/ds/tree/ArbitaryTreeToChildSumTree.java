package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * http://www.geeksforgeeks.org/convert-an-arbitrary-binary-tree-to-a-tree-that-holds-children-sum-property/
 * 
 * You can only increment data values in any node (You cannot change the structure of the tree and cannot decrement the
 * value of any node).
 * 
 * Given a binary tree, the task is to check for every node, its value is equal to the sum of values of its immediate
 * left and right child. For NULL values, consider the value to be 0.
 * 
 * Time Complexity: O(n) as we are doing traversal of the tree only once. Auxiliary Space: O(ht of tree)
 * 
 * Category : Hard
 */
public class ArbitaryTreeToChildSumTree {

	// method to convert the binary tree
	public void convertTree(TreeNode root) {
		if (root == null)
			return;

		int childSum = 0;

		// sum of children's data
		if (root.left != null)
			childSum += root.left.data;
		if (root.right != null)
			childSum += root.right.data;

		// if children sum is greater than root
		if (childSum >= root.data) {
			root.data = childSum;
		} else {
			// if children sum is less than root
			// change both children
			if (root.left != null)
				root.left.data = root.data;
			if (root.right != null)
				root.right.data = root.data;
		}

		// now go down the tree and check others - Inorder
		convertTree(root.left);
		convertTree(root.right);

		// here is the backtracking part happens, as changes
		// may happen
		int totVal = 0;

		if (root.left != null)
			totVal += root.left.data;
		if (root.right != null)
			totVal += root.right.data;

		// Leaf node dont update, as it will make it 0
		if (root.left != null || root.right != null)
			root.data = totVal;
		// So that at last the children Sum property stays
		// TRUE
	}

	public static void main(String args[]) {
		ArbitaryTreeToChildSumTree att = new ArbitaryTreeToChildSumTree();
		TreeTraversals tt = new TreeTraversals();

		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(15);
		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(7);
		root.right = new TreeNode(19);
		root.right.left = new TreeNode(20);
		root.right.right = new TreeNode(-1);

		tt.inOrder(root);

		att.convertTree(root);

		System.out.println();
		tt.inOrder(root);
	}

}
