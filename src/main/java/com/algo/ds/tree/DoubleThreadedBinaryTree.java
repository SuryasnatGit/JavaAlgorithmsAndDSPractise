package com.algo.ds.tree;

/**
 * "A binary tree is threaded by making all right child pointers that would normally be null point to the inorder
 * successor of the node (if it exists), and all left child pointers that would normally be null point to the inorder
 * predecessor of the node. <br/>
 * Double threaded: each node is threaded towards both the in-order predecessor and successor (left and right).
 * 
 * @author Suryasnat
 *
 */
public class DoubleThreadedBinaryTree {

	class Node {
		int data;
		int leftBit;
		int rightBit;
		Node left;
		Node right;

		public Node(int d) {
			this.data = d;
		}
	}

	private Node root;
	private boolean directionLeft;
	private boolean directionRight;

	public DoubleThreadedBinaryTree() {
		root = new Node(Integer.MAX_VALUE);
		root.leftBit = 0;
		root.rightBit = 1;
		root.left = root;
		root.right = root;
	}

	public void insert(int data) {
		Node n = new Node(data);
		// check if node to be inserted is the first node
		if (root == root.left && root == root.right) {
			n.left = root;
			root.left = n;
			n.leftBit = root.leftBit;
			root.leftBit = 1;
			n.right = root;
		} else {
			Node current = root.left;
			while (true) {
				if (current.data > n.data) {
					// look in left subtree of current
					if (current.leftBit == 0) {
						// new node needs to be added here as left child
						directionLeft = true;
						directionRight = false;
						break;
					} else {
						current = current.left;
					}
				} else {
					// look in right subtree of current
					if (current.rightBit == 0) {
						// new node needs to be inserted here as right child
						directionLeft = false;
						directionRight = true;
						break;
					} else {
						current = current.right;
					}
				}
			}
			if (directionLeft) {
				// add node as left child
				n.left = current.left;
				current.left = n;
				n.leftBit = current.leftBit;
				current.leftBit = 1;
				n.right = current;
			} else if (directionRight) {
				// add node as right child
				n.right = current.right;
				current.right = n;
				n.rightBit = current.rightBit;
				current.rightBit = 1;
				n.left = current;
			}
		}
	}

	public void inorder() {
		Node current = root.left;
		// go to the left most node
		while (current.leftBit == 1) {
			current = current.left;
		}

		// now keep traversing the next inorder successor and print it
		while (current != root) {
			System.out.println(current.data + " ");
			current = findNextInorder(current);
		}
	}

	private Node findNextInorder(Node current) {
		// if rightBit of current node is 0 means current node does not
		// have right child so use the right pointer to move to its
		// inorder successor.
		if (current.rightBit == 0) {
			return current.right;
		}
		// if rightBit of current node is not 0 means current node does
		// have right child so go to the left most node in right sub tree.
		current = current.right;
		while (current.leftBit != 0) {
			current = current.left;
		}
		return current;
	}

}
