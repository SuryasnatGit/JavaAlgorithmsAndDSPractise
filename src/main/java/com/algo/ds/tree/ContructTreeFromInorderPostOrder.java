package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class ContructTreeFromInorderPostOrder {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		return buildTree(inorder, postorder, 0, inorder.length - 1, postorder.length - 1);
	}

	public TreeNode buildTree(int[] inorder, int[] postorder, int start, int end, int index) {
		if (start > end) {
			return null;
		}

		int i;
		for (i = start; i <= end; i++) {
			if (postorder[index] == inorder[i]) {
				break;
			}
		}

		TreeNode tn = new TreeNode(postorder[index]);
		tn.left = buildTree(inorder, postorder, start, i - 1, index - (end - i + 1));
		tn.right = buildTree(inorder, postorder, i + 1, end, index - 1);
		return tn;
	}
}
