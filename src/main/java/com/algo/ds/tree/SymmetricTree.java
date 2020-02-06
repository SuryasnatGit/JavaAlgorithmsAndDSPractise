package com.algo.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.algo.common.TreeNode;

// https://leetcode.com/problems/symmetric-tree/
public class SymmetricTree {

	// T - O(n) S - O(n) due to recursive calls on stack in worst case.
	public boolean isSymmetricRecursive(TreeNode root) {
		return isSymmetricRecursive(root, root);
	}

	private boolean isSymmetricRecursive(TreeNode node1, TreeNode node2) {
		if (node1 == null && node2 == null)
			return true;

		if (node1 == null || node2 == null)
			return false;

		return (node1.data == node2.data) && isSymmetricRecursive(node1.left, node2.right)
				&& isSymmetricRecursive(node1.right, node2.left);
	}

	// T - O(n) S - O(n)
	public boolean isSymmetricIterative(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode node1 = queue.poll();
			TreeNode node2 = queue.poll();

			if (node1 == null && node2 == null)
				return true;

			if (node1 == null || node2 == null)
				return false;

			if (node1.data != node2.data)
				return false;

			queue.add(node1.left);
			queue.add(node2.right);
			queue.add(node1.right);
			queue.add(node2.left);
		}
		return true;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(3);
		root.right = new TreeNode(2);
		root.left.right = new TreeNode(4);
		root.right.left = new TreeNode(4);

		SymmetricTree st = new SymmetricTree();
		System.out.println(st.isSymmetricRecursive(root));
		System.out.println(st.isSymmetricIterative(root));
	}

}
