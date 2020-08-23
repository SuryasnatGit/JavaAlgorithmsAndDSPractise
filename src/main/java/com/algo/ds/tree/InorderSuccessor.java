package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * CTCI - 4.6
 *
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 *
 * Time complexity O(h) Space complexity O(h)
 *
 * https://leetcode.com/problems/inorder-successor-in-bst/
 * 
 * CTCI CH 4. Problem 4.6
 */
public class InorderSuccessor {
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		if (p.right != null) {
			p = p.right;
			while (p.left != null) {
				p = p.left;
			}
			return p;
		} else {
			return succ(root, p.data);
		}
	}

	private TreeNode succ(TreeNode root, int data) {
		if (root.data == data) {
			return null;
		}
		if (root.data < data) {
			return succ(root.right, data);
		} else {
			TreeNode s = succ(root.left, data);
			return s == null ? root : s;
		}
	}
}
