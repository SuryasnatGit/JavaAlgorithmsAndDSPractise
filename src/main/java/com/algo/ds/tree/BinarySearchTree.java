package com.algo.ds.tree;

/**
 * Binary Search Tree, is a node-based binary tree data structure which has the following properties: <br/>
 * 
 * The left subtree of a node contains only nodes with keys less than the nodes key. <br/>
 * The right subtree of a node contains only nodes with keys greater than the nodes key. <br/>
 * The left and right subtree each must also be a binary search tree. <br/>
 * There must be no duplicate nodes.
 * 
 * @author Suryasnat
 *
 */
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

	public boolean delete(int key) {
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
			if (current == null)
				return false;
		}

		// when we reach this point we know that current is the node which is to be deleted.
		// case 1 : when current(node to be deleted does not have any children
		if (current.leftChild == null && current.rightChild == null) {
			if (current == root)
				root = null;
			else {
				if (isLeftChild)
					parent.leftChild = null;
				else
					parent.rightChild = null;
			}
		}

		// case 2: when current(node to be deleted) has only left child
		if (current.rightChild == null) {
			if (current == root)
				root = null;
			else {
				if (isLeftChild)
					parent.leftChild = current.leftChild;
				else
					parent.rightChild = current.leftChild;
			}
		}

		// case 3: when current(node to be deleted) has only right child
		if (current.leftChild == null) {
			if (current == root)
				root = null;
			else {
				if (isLeftChild)
					parent.leftChild = current.rightChild;
				else
					parent.rightChild = current.rightChild;
			}
		}

		// case 4: when current node(node to be deleted) has both left and right children.
		// find the successor here.
		if (current.leftChild != null && current.rightChild != null) {
			Node successor = findSuccessor(current);
			if (current == root)
				root = null;
			else {
				if (isLeftChild)
					parent.leftChild = successor;
				else
					parent.rightChild = successor;
			}
			successor.leftChild = current.leftChild;
		}
		return true;

	}

	private Node findSuccessor(Node deleteNode) {
		Node successor = null;
		Node successorParent = null;
		Node current = deleteNode.rightChild;
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		// at this point there is no more left child of successor.
		// check if successor has right child, if so add to left of successor parent
		if (successor != deleteNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = deleteNode.rightChild;
		}
		return successor;
	}
}
