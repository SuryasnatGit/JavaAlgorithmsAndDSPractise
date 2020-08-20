package com.algo.ds.tree;

import com.algo.common.TreeNode;

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

	private TreeNode root;

	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Time complexity- O(logN)
	 * 
	 * @param key
	 * @return
	 */
	public TreeNode find(int key) {
		TreeNode current = root;
		while (current.data != key) {
			if (key < current.data)
				current = current.left;
			else
				current = current.right;
			if (current == null)
				return null;
		}
		return current;
	}

	public void insert(int d) {
		TreeNode newNode = new TreeNode();
		newNode.data = d;

		if (root == null)
			root = newNode;
		else {
			TreeNode current = root;
			TreeNode parent;
			while (true) {
				parent = current;
				if (d < current.data) { // go left
					current = current.left;
					if (current == null) {
						parent.left = newNode;
						return;
					}
				} else { // go right
					current = current.right;
					if (current == null) {
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}

	public void inorder(TreeNode root) {
		if (root != null) {
			inorder(root.left);
			System.out.println(root.data);
			inorder(root.right);
		}
	}

	public boolean delete(int key) {
		TreeNode current = root;
		TreeNode parent = root;
		boolean isleft = true;
		while (current.data != key) {
			parent = current;
			if (key < current.data) {
				isleft = true;
				current = current.left;
			} else {
				isleft = false;
				current = current.right;
			}
			if (current == null)
				return false;
		}

		// when we reach this point we know that current is the node which is to be deleted.
		// case 1 : when current(node to be deleted does not have any children
		if (current.left == null && current.right == null) {
			if (current == root)
				root = null;
			else {
				if (isleft)
					parent.left = null;
				else
					parent.right = null;
			}
		}

		// case 2: when current(node to be deleted) has only left child
		if (current.right == null) {
			if (current == root)
				root = null;
			else {
				if (isleft)
					parent.left = current.left;
				else
					parent.right = current.left;
			}
		}

		// case 3: when current(node to be deleted) has only right child
		if (current.left == null) {
			if (current == root)
				root = null;
			else {
				if (isleft)
					parent.left = current.right;
				else
					parent.right = current.right;
			}
		}

		// case 4: when current node(node to be deleted) has both left and right children.
		// find the successor here.
		if (current.left != null && current.right != null) {
			TreeNode successor = findSuccessor(current);
			if (current == root)
				root = null;
			else {
				if (isleft)
					parent.left = successor;
				else
					parent.right = successor;
			}
			successor.left = current.left;
		}
		return true;

	}

	private TreeNode findSuccessor(TreeNode deleteNode) {
		TreeNode successor = null;
		TreeNode successorParent = null;
		TreeNode current = deleteNode.right;
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.left;
		}
		// at this point there is no more left child of successor.
		// check if successor has right child, if so add to left of successor parent
		if (successor != deleteNode.right) {
			successorParent.left = successor.right;
			successor.right = deleteNode.right;
		}
		return successor;
	}

	public TreeNode getRoot() {
		return root;
	}

	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(1);
		bst.inorder(bst.root);
		System.out.println();
		bst.insert(2);
		bst.inorder(bst.root);
		System.out.println();
		bst.insert(3);
		bst.inorder(bst.root);
		System.out.println();
		bst.insert(4);
		bst.inorder(bst.root);
		System.out.println();
		bst.insert(5);
		bst.inorder(bst.root);
		System.out.println();
		bst.insert(6);
		bst.inorder(bst.root);
		System.out.println();
		bst.insert(7);
		bst.inorder(bst.root);
	}
}
