package com.algo.ds.tree;

public class Node {

	public int data;
	public Node left;
	public Node right;
	public Node next;
	public int height;
	public int size;

	// needed for some problems
	public Node parent;

	// default constructor
	public Node() {

	}

	public Node(int d) {
		this.data = d;
		left = null;
		right = null;
	}

	public int getData() {
		return data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", left=" + left + ", right=" + right + "]";
	}
}
