package com.algo.ds.tree;

import com.algo.common.TreeNode;

public class BinaryTree {

	private TreeNode root;

	public BinaryTree() {
		this.root = null;
	}

	public BinaryTree(int val) {
		this.root = new TreeNode(val);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void insert(int data) {
		insert(root, data);
	}

	// method to insert data recursively
	private TreeNode insert(TreeNode node, int data) {
		if (node == null) {
			node = new TreeNode(data);
		} else {
			if (node.getRight() == null) {
				node.right = insert(node.right, data);
			} else {
				node.left = insert(node.left, data);
			}
		}

		return node;
	}

	public int countNodes() {
		return countNodes(root);
	}

	private int countNodes(TreeNode node) {
		if (node == null) {
			return 0;
		} else {
			int count = 0;
			count += countNodes(node.left);
			count += countNodes(node.right);
			return count;
		}
	}

	public boolean search(int val) {
		return search(root, val);
	}

	private boolean search(TreeNode node, int data) {
		if (node.getData() == data) {
			return true;
		} else if (node.getLeft() != null) {
			if (search(node.getLeft(), data)) {
				return true;
			}
		} else if (node.getRight() != null) {
			if (search(node.getRight(), data)) {
				return true;
			}
		}

		return false;
	}

	public TreeNode getRoot() {
		return root;
	}

	public static void main(String args[]) {
		BinaryTree bt = new BinaryTree();

	}
}
