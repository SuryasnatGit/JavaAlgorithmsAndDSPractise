package com.algo.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.algo.common.TreeNode;

/**
 * http://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-complete-tree-or-not/
 * 
 * https://www.techiedelight.com/check-given-binary-tree-complete-binary-tree-not/
 * 
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all
 * nodes are as far left as possible. Test cases: A node with only right child. A node with only left child. A node with
 * both left and right child
 */
public class IsCompleteBinaryTree {

	/**
	 * Time Complexity: O(n) where n is the number of nodes in given Binary Tree
	 * 
	 * Auxiliary Space: O(n) for queue.
	 * 
	 */
	public boolean isComplete(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		boolean foundFirstNonFull = false;
		while (!queue.isEmpty()) {
			root = queue.poll();
			if (foundFirstNonFull) {
				if (root.left != null || root.right != null) {
					return false;
				}
				continue;
			}
			if (root.left != null && root.right != null) {
				queue.offer(root.left);
				queue.offer(root.right);
			} else if (root.left != null) {
				queue.offer(root.left);
				foundFirstNonFull = true;
			} else if (root.right != null) {
				return false;
			} else {
				foundFirstNonFull = true;
			}
		}
		return true;
	}

	/**
	 * Using array representation of complete tree.
	 * 
	 * @param root
	 * @return
	 */
	public boolean isComplete1(TreeNode root) {
		if (root == null)
			return true;

		int size = size(root);
		boolean[] arr = new boolean[size];

		// perform inorder traversal and fill the boolean array starting with index 0
		inorder(root, arr, 0);

		for (boolean b : arr) {
			if (!b)
				return false;
		}

		return true;
	}

	private void inorder(TreeNode root, boolean[] arr, int index) {
		if (root == null || index >= arr.length)
			return;

		inorder(root.left, arr, 2 * index + 1);
		arr[index] = true;
		inorder(root.left, arr, 2 * index + 2);
	}

	private int size(TreeNode root) {
		if (root == null)
			return 0;

		return 1 + size(root.left) + size(root.right);
	}

	public static void main(String args[]) {
//		BinaryTree bt = new BinaryTree();
//		TreeNode head = null;
//		bt.addNode(3, head);
//		bt.addNode(-6, head);
//		head = bt.addNode(7, head);
//		head = bt.addNode(-10, head);
//		head = bt.addNode(-15, head);
//		head = bt.addNode(-4, head);
//		head = bt.addNode(4, head);
//		head = bt.addNode(11, head);
//		head = bt.addNode(-9, head);
//
//		IsCompleteBinaryTree icbt = new IsCompleteBinaryTree();
//		System.out.println(icbt.isComplete(head));
	}
}
