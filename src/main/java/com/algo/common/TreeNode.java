package com.algo.common;

public class TreeNode {

	public int data;
	public TreeNode left;
	public TreeNode right;
	public TreeNode next;
	public int height;
	public int size;

	// needed for some problems
	public TreeNode parent;

	// default constructor
	public TreeNode() {

	}

	public TreeNode(int d) {
		this.data = d;
		left = null;
		right = null;
	}

	public int getData() {
		return data;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", left=" + left + ", right=" + right + "]";
	}
}
