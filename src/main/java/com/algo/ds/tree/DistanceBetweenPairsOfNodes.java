package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * https://www.techiedelight.com/distance-between-given-pairs-of-nodes-binary-tree/
 *
 */
public class DistanceBetweenPairsOfNodes {

	// Helper function to check if given node is present in binary tree or not
	public static boolean isNodePresent(TreeNode root, TreeNode node) {
		// base case
		if (root == null)
			return false;

		// if node is found, return true
		if (root == node)
			return true;

		// return true if node is found in the left subtree or right subtree
		return isNodePresent(root.left, node) || isNodePresent(root.right, node);
	}

	// Helper function to find level of given node present in binary tree
	public static int findLevel(TreeNode root, TreeNode node, int level) {
		// base case
		if (root == null)
			return Integer.MIN_VALUE;

		// return level if node is found
		if (root == node)
			return level;

		// search node in left subtree
		int left = findLevel(root.left, node, level + 1);

		// if node is found in left subtree, return
		if (left != Integer.MIN_VALUE)
			return left;

		// else continue the search in right subtree
		return findLevel(root.right, node, level + 1);
	}

	// Function to find lowest common ancestor of given nodes x and y where
	// both x and y are present in the binary tree.
	public static TreeNode findLCA(TreeNode root, TreeNode x, TreeNode y) {
		// base case 1: if tree is empty
		if (root == null)
			return null;

		// base case 2: if either x or y is found
		if (root == x || root == y)
			return root;

		// recursively check if x or y exists in the left subtree
		TreeNode left = findLCA(root.left, x, y);

		// recursively check if x or y exists in the right subtree
		TreeNode right = findLCA(root.right, x, y);

		// if x is found in one subtree and y is found in other subtree,
		// update lca to current node
		if (left != null && right != null) {
			return root;
		}

		// if x and y exists in left subtree
		if (left != null) {
			return left;
		}

		// if x and y exists in right subtree
		if (right != null) {
			return right;
		}

		return null;
	}

	// Function to find the distance between node 'x' and node 'y' in a
	// given binary tree rooted at 'root' node
	public static int findDistance(TreeNode root, TreeNode x, TreeNode y) {
		// lca stores lowest common ancestor of x and y
		TreeNode lca = null;

		// call LCA procedure only if both x and y are present in the tree
		if (isNodePresent(root, y) && isNodePresent(root, x))
			lca = findLCA(root, x, y);
		else
			return Integer.MIN_VALUE;

		// return distance of x from lca + distance of y from lca
		return findLevel(lca, x, 0) + findLevel(lca, y, 0);

		// above return statement is equivalent to
		// findLevel(root, x, 0) + findLevel(root, y, 0) - 2*findLevel(root, lca, 0)

		// we can avoid calling isNodePresent() function by using return values of
		// findLevel() function to check if x and y are present in the tree or not
	}
}
