package com.algo.ds.tree;

/**
 * CTCI - 4.6
 *
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 *
 * Time complexity O(h) Space complexity O(h)
 *
 * https://leetcode.com/problems/inorder-successor-in-bst/
 * 
 * CTCI CH 4. Problem 4.6
 */
public class InorderSuccessor {
	public Node inorderSuccessor(Node root, Node p) {
		if (p.right != null) {
			p = p.right;
			while (p.left != null) {
				p = p.left;
			}
			return p;
		} else {
			return succ(root, p.data);
		}
	}

	private Node succ(Node root, int data) {
		if (root.data == data) {
			return null;
		}
		if (root.data < data) {
			return succ(root.right, data);
		} else {
			Node s = succ(root.left, data);
			return s == null ? root : s;
		}
	}
}
