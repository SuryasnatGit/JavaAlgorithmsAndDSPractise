
package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same
 * parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left
 * leaf nodes. Return the new root. https://leetcode.com/problems/binary-tree-upside-down/. <br/>
 * left most node becomes root. <br/>
 * its parent becomes right child <br/>
 * its right sibling becomes left child <br/>
 * recursively follow rule for all nodes.
 */
public class UpsidedownBinaryTree {
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		if (root == null || (root.left == null && root.right == null)) { // only root.left == null?
			return root;
		}
		TreeNode newRoot = upsideDownBinaryTree(root.left);
		root.left.left = root.right;
		root.left.right = root;
		root.left = null;
		root.right = null;
		return newRoot;
	}

}
