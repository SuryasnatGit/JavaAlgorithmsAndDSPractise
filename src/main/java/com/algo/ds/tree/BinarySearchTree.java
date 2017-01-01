package com.algo.ds.tree;

public class BinarySearchTree {

	private class Node {
		private int data;
		private Node leftChild;
		private Node rightChild;

		public void displayNode() {
			System.out.println("Node: " + data);
		}
	}

	private Node root;

	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Time complexity- O(logN)
	 * 
	 * @param key
	 * @return
	 */
	public Node find(int key) {
		Node current = root;
		while (current.data != key) {
			if (key < current.data)
				current = current.leftChild;
			else
				current = current.rightChild;
			if (current == null)
				return null;
		}
		return current;
	}

	public void insert(int d) {
		Node newNode = new Node();
		newNode.data = d;

		if (root == null)
			root = newNode;
		else {
			Node current = root;
			Node parent;
			while (true) {
				parent = current;
				if (d < current.data) { // go left
					current = current.leftChild;
					if (current == null) {
						parent.leftChild = newNode;
						return;
					}
				} else { // go right
					current = current.rightChild;
					if (current == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	public void inorder(Node root) {
		if (root != null) {
			inorder(root.leftChild);
			root.displayNode();
			inorder(root.rightChild);
		}
	}

	public void delete(int key) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		while (current.data != key) {
			parent = current;
			if (key < current.data) {
				isLeftChild = true;
				current = current.leftChild;
			} else {
				isLeftChild = false;
				current = current.rightChild;
			}
			// if(current == null)
			// return false;
		}

		if (current.leftChild == null && current.rightChild == null) {
			if (current == root)
				root = null;
			else if (isLeftChild)
				parent.leftChild = null;
			else
				parent.rightChild = null;
		}
	}
}
