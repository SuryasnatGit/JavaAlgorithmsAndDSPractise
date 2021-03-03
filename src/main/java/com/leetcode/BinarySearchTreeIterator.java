package com.leetcode;

import java.util.Stack;

import com.algo.common.TreeNode;

/**
 * Implement an iterator for a binary search tree that will iterate the nodes by value in ascending order
 *
 */
public class BinarySearchTreeIterator {

	private Stack<TreeNode> stack = new Stack<TreeNode>();

	public BinarySearchTreeIterator(TreeNode root) {
		pushToLeft(root);
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	/** @return the next smallest number */
	public int next() {
		TreeNode node = stack.pop();
		pushToLeft(node.right);
		return node.data;
	}

	private void pushToLeft(TreeNode node) {
		if (node != null) {
			stack.push(node);
			pushToLeft(node.left);
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right = new TreeNode(5);

		BinarySearchTreeIterator bst = new BinarySearchTreeIterator(root);
		for (int i = 0; i < 7; i++) {
			System.out.println(bst.hasNext() ? bst.next() : null);
		}
	}
}
