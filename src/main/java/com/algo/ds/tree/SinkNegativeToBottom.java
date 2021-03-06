package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * Given a binary tree containing many zero nodes, sink nodes having zero value to the bottom of the sub-tree rooted at
 * that node. In other words, the output binary tree should not contain any node having zero value that is parent of
 * node having non-zero value.
 * 
 * 
 * Sink Zero in Binary Tree. Swap zero value of a node with non-zero value of one of its descendants so that no node
 * with value zero could be parent of node with non-zero
 * 
 * Category : Medium
 */
public class SinkNegativeToBottom {

	public void sinkZero(TreeNode root) {
		if (root == null) {
			return;
		}

		sinkZero(root.left);
		sinkZero(root.right);

		if (root.data < 0) {
			pushDown(root);
		}
	}

	// this method is similar to heapify of heap sort.
	private void pushDown(TreeNode root) {
		if (root == null) {
			return;
		}
		// find a child with non zero node value
		if (root.left == null && root.right == null) {
			// already leaf node. nothing to do. just return
			return;
		}

		// if root left is not null and root left data is not 0 pick it to swap
		if (root.left != null && root.left.data >= 0) {
			int temp = root.data;
			root.data = root.left.data;
			root.left.data = temp;
			pushDown(root.left);
		} else if (root.right != null && root.right.data >= 0) {
			int temp = root.data;
			root.data = root.right.data;
			root.right.data = temp;
			pushDown(root.right);
		}
	}

	public static void main(String args[]) {
		int preorder[] = { -1, 1, 6, -2, 11, 3, 2, -3, 31, 22, 17 };
		int inorder[] = { -2, 6, 11, 1, 3, -1, 31, -3, 22, 2, 17 };
		ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
		TreeNode root = ctf.createTree(inorder, preorder);
		SinkNegativeToBottom szb = new SinkNegativeToBottom();
		szb.sinkZero(root);
		LevelOrderTraversal lot = new LevelOrderTraversal();
		TreeTraversals tt = new TreeTraversals();
		tt.inOrder(root);
		lot.levelOrder(root);
	}
}
