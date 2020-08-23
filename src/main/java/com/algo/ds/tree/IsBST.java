package com.algo.ds.tree;

import java.util.Deque;
import java.util.LinkedList;

import com.algo.common.TreeNode;

/**
 * CTCI - 4.5
 * 
 * Youtube link - https://youtu.be/MILxfAbIhrE
 * 
 * Given a binary tree, return true if it is binary search tree else return false. CTCI Ch 4 Problem 4.5
 * 
 * Solution Keep min, max for every recursion. Initial min and max is very small and very larger number. Check if
 * root.data is in this range. When you go left pass min and root.data and for right pass root.data and max.
 * 
 * Time complexity is O(n) since we are looking at all nodes.
 * 
 * Test cases: Null tree 1 or 2 nodes tree Binary tree which is actually BST Binary tree which is not a BST
 */
public class IsBST {

	/**
	 * Approach 1: A better solution looks at each node only once. The trick is to write a utility helper function that
	 * traverses down the tree keeping track of the narrowing min and max allowed values as it goes, looking at each
	 * node only once. The initial values for min and max should be INT_MIN and INT_MAX they narrow from there.
	 * 
	 * Run on IDE
	 * 
	 * Time Complexity: O(n) Auxiliary Space : O(1) if Function Call Stack size is not considered, otherwise O(n)
	 * 
	 * @param root
	 * @return
	 */
	public boolean isBST(TreeNode root) {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBST(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		}
		if (root.data <= min || root.data > max) {
			return false;
		}
		return isBST(root.left, min, root.data - 1) && isBST(root.right, root.data + 1, max);
	}

	/**
	 * Approach 2 - iterative way
	 * 
	 * @param root
	 * @return
	 */
	public boolean isBSTIterative(TreeNode root) {
		if (root == null) {
			return true;
		}

		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode node = root;
		int prev = Integer.MIN_VALUE;
		int current;
		while (true) {
			if (node != null) {
				stack.addFirst(node);
				node = node.left;
			} else {
				if (stack.isEmpty()) {
					break;
				}
				node = stack.pollFirst();
				current = node.data;
				if (current < prev) {
					return false;
				}
				prev = current;
				node = node.right;
			}
		}
		return true;
	}

	/**
	 * Approach 3 - using inorder traversal.
	 * 
	 * 1) Do In-Order Traversal of the given tree and store the result in a temp array. 3) Check if the temp array is
	 * sorted in ascending order, if it is, then the tree is BST.
	 * 
	 * Time Complexity: O(n)
	 * 
	 * We can avoid the use of Auxiliary Array. While doing In-Order traversal, we can keep track of previously visited
	 * node. If the value of the currently visited node is less than the previous value, then tree is not BST.
	 * 
	 * @return
	 */
	private TreeNode prev = null; // to keep track of previous node in inorder traversal

	public boolean isBST_inorderTraversal(TreeNode root) {
		if (root != null) {
			if (!isBST_inorderTraversal(root.left))
				return false;
			if (prev != null && root.data <= prev.data)
				return false;
			prev = root;
			return isBST_inorderTraversal(root.right);
		}
		return true;
	}

	public static void main(String args[]) {
		BinaryTree bt = new BinaryTree();
		TreeNode root = null;
		root = bt.addNode(10, root);
		root = bt.addNode(15, root);
		root = bt.addNode(-10, root);
		root = bt.addNode(17, root);
		root = bt.addNode(20, root);
		root = bt.addNode(0, root);

		IsBST isBST = new IsBST();
		System.out.println(isBST.isBST(root));
		System.out.println(isBST.isBSTIterative(root));
		System.out.println(isBST.isBST_inorderTraversal(root));
	}
}
