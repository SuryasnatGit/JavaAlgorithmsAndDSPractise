package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * http://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/.
 * 
 * We break the problem in 3 parts: <br/>
 * 1. Print the left boundary in top-down manner. <br/>
 * 2. Print all leaf nodes from left to right, which can again be sub-divided into two sub-parts: <br/>
 * .2.1 Print all leaf nodes of left sub-tree from left to right. <br/>
 * 2.2 Print all leaf nodes of right subtree from left to right. <br/>
 * 3. Print the right boundary in bottom-up manner.
 * 
 * Test cases : All left children All right children Full tree Complete tree
 */
public class BoundaryTraversal {

	public void traversal(TreeNode root) {
		// find starting point for right side
		TreeNode current = root;
		while (current != null) {
			if (current.right != null && current.left != null) {
				current = current.right;
				break;
			}
			current = current.left != null ? current.left : current.right;
		}
		printRightSide(current);
		printLeaves(root);
		printLeftSide(root);
	}

	private void printRightSide(TreeNode root) {
		if (root == null || (root.left == null && root.right == null)) {
			return;
		}
		System.out.println(root.data);
		if (root.right != null) {
			printRightSide(root.right);
		} else if (root.left != null) {
			printRightSide(root.left);
		}
	}

	private void printLeftSide(TreeNode root) {
		if (root == null || (root.left == null && root.right == null)) {
			return;
		}
		if (root.left != null) {
			printLeftSide(root.left);
		} else if (root.right != null) {
			printLeftSide(root.right);
		}
		System.out.println(root.data);
	}

	private void printLeaves(TreeNode root) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			System.out.println(root.data);
		}
		printLeaves(root.right);
		printLeaves(root.left);
	}

	public static void main(String args[]) {
//		BinaryTree bt = new BinaryTree();
//		TreeNode head = null;
//		head = bt.addNode(100, head);
//		head = bt.addNode(90, head);
//		head = bt.addNode(10, head);
//		head = bt.addNode(15, head);
//		head = bt.addNode(25, head);
//		head = bt.addNode(5, head);
//		head = bt.addNode(7, head);
//		head = bt.addNode(-7, head);
//		BoundaryTraversal bd = new BoundaryTraversal();
//		bd.traversal(head);
	}
}
