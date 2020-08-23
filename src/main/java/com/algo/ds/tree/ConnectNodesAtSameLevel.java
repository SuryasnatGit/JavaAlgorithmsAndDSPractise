package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * Populate next pointer for each node of binary tree.
 *
 * Time complexity O(n) Space complexity O(1)
 *
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/.
 * 
 * Given a binary tree
 * 
 * struct TreeLinkNode { TreeLinkNode *left; TreeLinkNode *right; TreeLinkNode *next; } Populate each next pointer to
 * point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * Note:
 * 
 * You may only use constant extra space. Recursive approach is fine, implicit stack space does not count as extra space
 * for this problem. Example:
 * 
 * Given the following binary tree,
 * 
 * 1 / \ 2 3 / \ \ 4 5 7 After calling your function, the tree should look like:
 * 
 * 1 -> NULL / \ 2 -> 3 -> NULL / \ \ 4-> 5 -> 7 -> NULL
 */
public class ConnectNodesAtSameLevel {

	/**
	 * Starting at any node, its left child's next value will be the node's right child, and it's right child's next
	 * value will be the node's next value's left child. We need to make sure the next value isn't null, so we add in a
	 * ternary conditional check.
	 * 
	 * @param root
	 */
	public void connect_simpleRecursive(TreeNode root) {
		connect_simpleRecursive(root, null);
	}

	private void connect_simpleRecursive(TreeNode root, TreeNode next) {
		if (root == null)
			return;
		root.next = next;
		connect_simpleRecursive(root.left, root.right);
		connect_simpleRecursive(root.right, root.next != null ? root.next.left : null);
	}

	/**
	 * O(1) space and O(n) time
	 * 
	 * @param root
	 */
	public void connect_iterative(TreeNode root) {
		if (root == null)
			return;
		TreeNode curr_level = root;
		while (curr_level != null) {
			TreeNode temp = curr_level;
			while (temp != null) {
				if (temp.left != null)
					temp.left.next = temp.right;
				if (temp.right != null && temp.next != null)
					temp.right.next = temp.next.left;
				temp = temp.next;
			}
			curr_level = curr_level.left;
		}
	}

	public void connect(TreeNode root) {
		if (root == null) {
			return;
		}

		TreeNode firstNode = root;
		TreeNode prevNode = null;
		while (firstNode != null) {
			root = firstNode;
			firstNode = null;
			prevNode = null;
			while (root != null) {
				if (root.left != null) {
					if (firstNode == null) {
						firstNode = root.left;
					}
					if (prevNode != null) {
						prevNode.next = root.left;
					}
					prevNode = root.left;
				}
				if (root.right != null) {
					if (firstNode == null) {
						firstNode = root.right;
					}
					if (prevNode != null) {
						prevNode.next = root.right;
					}
					prevNode = root.right;
				}
				root = root.next;
			}
		}
	}

	public static void main(String args[]) {
		BinaryTree bt = new BinaryTree();
		TreeNode root = null;
		root = bt.addNode(10, root);
		root = bt.addNode(15, root);
		root = bt.addNode(5, root);
		root = bt.addNode(7, root);
		root = bt.addNode(19, root);
		root = bt.addNode(20, root);
		root = bt.addNode(-1, root);
		root = bt.addNode(21, root);
		ConnectNodesAtSameLevel cns = new ConnectNodesAtSameLevel();

		cns.connect(root);
	}
}
