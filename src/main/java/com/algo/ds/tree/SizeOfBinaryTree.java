package com.algo.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.algo.common.TreeNode;

/**
 * Given a root of binary tree, return size of binary tree
 * 
 * Solution: Recursively find size of left side, right side and add one to them and return that to calling function.
 * 
 * Time complexity - O(n) Space complexity(because of recursion stack) - height of binary tree. Worst case O(n)
 * 
 * Test cases: Null tree 1 node tree multi node tree
 */
public class SizeOfBinaryTree {

	/**
	 * 
	 * Recursive way.
	 */
	public int size1(TreeNode root) {
		if (root == null) {
			return 0;
		}

		return size1(root.left) + size1(root.right) + 1;
	}

	/**
	 * Iterative way
	 * 
	 */
	public int size2(TreeNode root) {
		if (root == null) {
			return 0;
		}

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		int num = 0;
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();

			if (node.left != null) {
				queue.add(node.left);
			}

			if (node.right != null) {
				queue.add(node.right);
			}

			num++;
		}

		return num;
	}

	public static void main(String args[]) {
		TreeNode head = new TreeNode(10);
		head.left = new TreeNode(15);
		head.right = new TreeNode(5);
		head.left.left = new TreeNode(7);
		head.left.right = new TreeNode(19);
		head.right.left = new TreeNode(20);
		// head.right.right = new TreeNode(-1);

		SizeOfBinaryTree sbt = new SizeOfBinaryTree();
		System.out.println(sbt.size1(head));
		System.out.println(sbt.size2(head));
	}
}
