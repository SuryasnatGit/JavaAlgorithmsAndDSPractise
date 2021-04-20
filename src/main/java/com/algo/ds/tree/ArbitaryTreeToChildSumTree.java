package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * http://www.geeksforgeeks.org/convert-an-arbitrary-binary-tree-to-a-tree-that-holds-children-sum-property/
 * 
 * Time Complexity: O(n^2), Worst case complexity is for a skewed tree such that nodes are in decreasing order from root
 * to leaf.
 * 
 * Category : Hard
 */
public class ArbitaryTreeToChildSumTree {

	public void childSumTree(TreeNode root) {
		toChildSumTree(root);
	}

	private int toChildSumTree(TreeNode root) {
		if (root == null) {
			return 0;
		}

		if (root.left == null && root.right == null) {
			return root.data;
		}

		int sum1 = toChildSumTree(root.left);
		int sum2 = toChildSumTree(root.right);

		if (root.data < sum1 + sum2) {
			root.data = sum1 + sum2;
		} else if (root.data > sum1 + sum2) {
			incrementChild(root, root.data - sum1 - sum2);
		}

		return root.data;
	}

	private void incrementChild(TreeNode root, int increment) {
		if (root == null || (root.left == null && root.right == null)) {
			return;
		}

		if (root.left != null) {
			root.left.data = root.left.data + increment;
			incrementChild(root.left, increment);
		} else {
			root.right.data = root.right.data + increment;
			incrementChild(root.right, increment);
		}
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

		att.childSumTree(root);
		System.out.println();
		tt.inOrder(root);
	}

}
