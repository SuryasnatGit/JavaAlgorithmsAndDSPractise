package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * A complete tree. TreeNode has a parent pointer. The value of each TreeNode is 0 or 1 The value of each parent is the
 * "and" result of two child TreeNodes Now flip a leaf (0 to 1 or 1 to 0). Fix the tree again
 * 
 * Good question, although there is a parent node, it still has to be recursive
 */
public class FlipTreeNode {
	static void flip(TreeNode node) {
		if (node == null) {
			return;
		}

		if (node.parent == null) {
			flipCurrentNode(node);
			return;
		}

		int curVal = node.data;
		TreeNode parent = node.parent;
		// 分情况讨论 这里cover了不是完全树的情况
		if (curVal == 0) {
			if (parent.left == null || parent.right == null) {
				flipCurrentNode(node);
				flip(parent);
			} else if (parent.left.data == 1 || parent.right.data == 1) { // I dont know current node is left or right,
																			// but I know current value is 0
				flipCurrentNode(node);
				flip(parent);
			} // If both current node and the sibling node are 0, even if one node is flipped, parent doesnt need to
				// change
		} else { // Current Node is 1
			if (parent.data == 0) { // Sibling node is 0
				flipCurrentNode(node);
			} else { // Sibling node is 1.
				flipCurrentNode(node);
				flip(parent);
			}
		}
	}

	private static void flipCurrentNode(TreeNode node) {
		// node.data = 1 - node.data;
		if (node.data == 1) {
			node.data = 0;
		} else {
			node.data = 1;
		}
	}
}
