package com.algo.ds.tree;

public class SingleThreadedBinaryTree {

	class Node {
		int data;
		Node left;
		Node right;
		boolean rightPointer;

		public Node(int d) {
			this.data = d;
			this.rightPointer = false;
		}
	}

	public void insert(Node root, int num) {
		Node newNode = new Node(num);
		Node current = root;
		Node parent = null;
		while (true) {
			parent = current;
			if (num < current.data) {
				// check on left sub tree
				current = current.left;
				if (current == null) {
					// insert new node here
					parent.left = newNode;
					newNode.right = parent;
					newNode.rightPointer = true;
					break;
				}
			} else {
				// check on right sub tree
				if (current.rightPointer == false) {
					current = current.right;
					if (current == null) {
						parent.right = newNode;
						break;
					}
				} else {
					Node temp = current.right;
					current.right = newNode;
					newNode.right = temp;
					newNode.rightPointer = true;
					break;
				}
			}
		}
	}

	public void traverse(Node root) {
		Node current = leftMostNode(root);
		// trave using right pointers
		while (current != null) {
			System.out.println(current.data + " ");
			if (current.rightPointer) // check if node has right pointer
				current = current.right;
			else // else go to left most node in right sub tree
				current = leftMostNode(current.right);
		}
	}

	private Node leftMostNode(Node root) {
		if (root == null)
			return null;
		else {
			while (root.left != null)
				root = root.left;
			return root;
		}
	}

}
