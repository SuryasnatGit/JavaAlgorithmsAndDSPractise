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

	/**
	 * Time Complexity: O(n^2) Worst case occurs in case of skewed tree.
	 * 
	 * 
	 * @param root
	 * @return
	 */
	public boolean isBalanced(Node root) {
		if (root == null)
			return true;

		int leftHeight = height(root.left);
		int rightHeight = height(root.right);

		if (Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right))
			return true;

		return false;
	}

	private int height(Node root) {
		if (root == null)
			return 0;

		return 1 + Math.max(height(root.left), height(root.right));
	}

	class Height {
		int height = 0;
	}

	/**
	 * time complexity - O(n)
	 * 
	 * @param root
	 * @param height
	 * @return
	 */
	public boolean isBalancedOptimized(Node root, Height height) {

		// base method
		if (root == null) {
			height.height = 0;
			return true;
		}

		Height leftHeight = new Height();
		Height rightHeight = new Height();
		boolean left = isBalancedOptimized(root.left, leftHeight);
		boolean right = isBalancedOptimized(root.right, rightHeight);
		int lh = leftHeight.height;
		int rh = rightHeight.height;

		// calculate current height
		height.height = 1 + Math.max(lh, rh);

		if (Math.abs(lh - rh) > 1)
			return false; // tree unbalanced
		else
			return left && right; // both left and right sub trees are balanced.
	}

	public static void main(String[] args) {
		HeightBalanced hb = new HeightBalanced();
		Node root = Node.newNode(1);
		root.left = Node.newNode(2);
		root.right = Node.newNode(3);
		root.left.left = Node.newNode(4);
		Height h = new HeightBalanced().new Height();
		System.out.println(hb.isBalanced(root));
		System.out.println(hb.isBalancedOptimized(root, h));
		root.left.left.right = Node.newNode(5);
		System.out.println(hb.isBalanced(root));

		System.out.println(hb.isBalancedOptimized(root, h));
	}
}
