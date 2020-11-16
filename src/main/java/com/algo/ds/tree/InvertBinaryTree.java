package com.algo.ds.tree;

import java.util.LinkedList;

import com.algo.common.TreeNode;

public class InvertBinaryTree {

	public TreeNode invertBinaryTree_recursive(TreeNode node) {
		if (node != null)
			recursive(node);

		return node;
	}

	private void recursive(TreeNode root) {
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;

		if (root.left != null)
			recursive(root.left);
		if (root.right != null)
			recursive(root.right);
	}

	public TreeNode invertBinaryTree_iterative(TreeNode root) {
		LinkedList<TreeNode> queue = new LinkedList<>();
		if (root != null)
			queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node.left != null)
				queue.add(node.left);
			if (node.right != null)
				queue.add(node.right);

			TreeNode temp = node.left;
			node.left = node.right;
			node.right = temp;
		}

		return root;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		InvertBinaryTree inv = new InvertBinaryTree();
		TreeNode inverted = inv.invertBinaryTree_iterative(root);

	}
}
