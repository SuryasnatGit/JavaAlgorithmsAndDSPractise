package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * Lowest common ancestor in binary search tree.
 *
 * Time complexity O(height of tree) Space complexity O(height of tree)
 * 
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 */
public class LowestCommonAncestoryBinarySearchTree {

	public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
		if (root == null)
			return root;
		if (root.data > Math.max(p, q)) {
			return lowestCommonAncestor(root.left, p, q);
		} else if (root.data < Math.min(p, q)) {
			return lowestCommonAncestor(root.right, p, q);
		} else {
			return root;
		}
	}
}
