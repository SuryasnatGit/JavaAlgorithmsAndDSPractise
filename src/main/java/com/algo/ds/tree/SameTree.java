package com.algo.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.algo.common.TreeNode;

/**
 * Youtube link - https://youtu.be/ySDDslG8wws
 * 
 * Given roots of two tree, return true if they have same data and same structure or return false.
 * 
 * Solution Keep comparing root of both data and then recursively check left and right.
 * 
 * Time complexity is O(n)
 */
public class SameTree {

	/**
	 * Using recursion : T - O(N) since each node is visited exactly once. S - O(log(N)) in the best case of completely
	 * balanced tree and O(N) in the worst case of completely unbalanced tree, to keep a recursion stack
	 * 
	 */
	public boolean sameTree1(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return false;
		}

		return root1.data == root2.data && sameTree1(root1.left, root2.left) && sameTree1(root1.right, root2.right);
	}

	/**
	 * Using iteration : T - O(N) since each node is visited exactly once. S - O(log(N)) in the best case of completely
	 * balanced tree and O(N) in the worst case of completely unbalanced tree, to keep a recursion stack
	 * 
	 */
	public boolean sameTree2(TreeNode root1, TreeNode root2) {
		// Return true if both trees are empty
		if (root1 == null && root2 == null)
			return true;

		// Return false if one is empty and other is not
		if (root1 == null || root2 == null)
			return false;

		// Create an empty queues for simultaneous traversals
		Queue<TreeNode> q1 = new LinkedList<TreeNode>();
		Queue<TreeNode> q2 = new LinkedList<TreeNode>();

		// Enqueue Roots of trees in respective queues
		q1.add(root1);
		q2.add(root2);

		while (!q1.isEmpty() && !q2.isEmpty()) {
			// Get front nodes and compare them
			TreeNode n1 = q1.peek();
			TreeNode n2 = q2.peek();

			if (n1.data != n2.data)
				return false;

			// Remove front nodes from queues
			q1.remove();
			q2.remove();

			/* Enqueue left children of both nodes */
			if (n1.left != null && n2.left != null) {
				q1.add(n1.left);
				q2.add(n2.left);
			}

			// If one left child is empty and other is not
			else if (n1.left != null || n2.left != null)
				return false;

			// Right child code (Similar to left child code)
			if (n1.right != null && n2.right != null) {
				q1.add(n1.right);
				q2.add(n2.right);
			} else if (n1.right != null || n2.right != null)
				return false;
		}

		return true;

	}

	public static void main(String args[]) {

		TreeNode root1 = new TreeNode(10);
		root1.left = new TreeNode(15);
		root1.right = new TreeNode(20);
		root1.left.left = new TreeNode(2);

		TreeNode root2 = new TreeNode(10);
		root2.left = new TreeNode(15);
		root2.right = new TreeNode(20);
		root2.left.left = new TreeNode(2);

		SameTree st = new SameTree();
		System.out.println(st.sameTree1(root1, root2));
	}
}
