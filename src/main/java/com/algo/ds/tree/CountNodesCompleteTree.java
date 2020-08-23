package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 *
 * Given a complete binary tree, count the number of nodes.
 *
 * Time complexity O(log(n) ^ 2)
 *
 * Reference https://leetcode.com/problems/count-complete-tree-nodes/
 */
public class CountNodesCompleteTree {

	/**
	 * The height of a tree can be found by just going left. Let a single node tree have height 0. Find the height h of
	 * the whole tree. If the whole tree is empty, i.e., has height -1, there are 0 nodes.
	 * 
	 * Otherwise check whether the height of the right subtree is just one less than that of the whole tree, meaning
	 * left and right subtree have the same height.
	 * 
	 * If yes, then the last node on the last tree row is in the right subtree and the left subtree is a full tree of
	 * height h-1. So we take the 2^h-1 nodes of the left subtree plus the 1 root node plus recursively the number of
	 * nodes in the right subtree. If no, then the last node on the last tree row is in the left subtree and the right
	 * subtree is a full tree of height h-2. So we take the 2^(h-1)-1 nodes of the right subtree plus the 1 root node
	 * plus recursively the number of nodes in the left subtree. Since I halve the tree in every recursive step, I have
	 * O(log(n)) steps. Finding a height costs O(log(n)). So overall O(log(n)^2).
	 * 
	 * 
	 * @param root
	 * @return
	 */
	public int countNodes(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int lh = leftHeight(root.left);
		int rh1 = rightHeight(root.left);
		int rh = rightHeight(root.right);

		if (lh == rh) {
			return (1 << lh + 1) - 1;
		} else {
			if (lh == rh1) {
				return 1 + countNodes(root.right) + (1 << lh) - 1;
			} else {
				return 1 + countNodes(root.left) + (1 << rh) - 1;
			}
		}
	}

	int leftHeight(TreeNode root) {
		int h = 0;
		while (root != null) {
			root = root.left;
			h++;
		}
		return h;
	}

	int rightHeight(TreeNode root) {
		int h = 0;
		while (root != null) {
			root = root.right;
			h++;
		}
		return h;
	}

	/**
	 * Here's an iterative version as well, with the benefit that I don't recompute h in every step.
	 * 
	 * 
	 * @param root
	 * @return
	 */
	public int countNodesIterative(TreeNode root) {
		int nodes = 0, h = height(root);
		while (root != null) {
			if (height(root.right) == h - 1) {
				nodes += 1 << h;
				root = root.right;
			} else {
				nodes += 1 << h - 1;
				root = root.left;
			}
			h--;
		}
		return nodes;
	}

	private int height(TreeNode root) {
		return root == null ? -1 : 1 + height(root.left);
	}

}
