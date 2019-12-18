package com.algo.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.algo.common.TreeNode;

/**
 * Category : Medium
 */
public class BinaryTreeHeightProblems {

	// Problem 1 - https://www.geeksforgeeks.org/find-height-binary-tree-represented-parent-array/
	/**
	 * A given array represents a tree in such a way that the array value gives the parent node of that particular
	 * index. The value of the root node index would always be -1. Find the height of the tree. Height of a Binary Tree
	 * is number of nodes on the path from root to the deepest leaf node, the number includes both root and leaf.
	 * 
	 * An efficient solution can solve the above problem in O(n) time. The idea is to first calculate depth of every
	 * node and store in an array depth[]. Once we have depths of all nodes, we return maximum of all depths.
	 * 
	 * 1) Find depth of all nodes and fill in an auxiliary array depth[].
	 * 
	 * 2) Return maximum value in depth[].
	 * 
	 * @param parent
	 * @return
	 */
	public int heightFromParentArray(int[] parent) {
		int n = parent.length;
		// make a temp array of size n which will contain the depth of each node
		int[] depth = new int[n];
		// initialize depth
		for (int i = 0; i < n; i++) {
			depth[i] = 0;
		}
		for (int i = 0; i < n; i++) {
			fillDepth(parent, i, depth);
		}

		int maxHt = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (depth[i] > maxHt)
				maxHt = depth[i];
		}
		return maxHt;
	}

	private void fillDepth(int[] parent, int i, int[] depth) {
		// if depth at i is already filled
		if (depth[i] != 0)
			return;

		// if node at i is root
		if (parent[i] == -1) {
			depth[i] = 1; // root starts at depth 1
			return;
		}

		// if parent's depth is not measured, then evaluate parent's depth first
		if (depth[parent[i]] == 0) {
			fillDepth(parent, parent[i], depth);
		}
		depth[i] = depth[parent[i]] + 1;
	}

	// problem 2 - Write an efficient algorithm to compute the height of binary tree. The height or depth of a tree is
	// number of edges or nodes on longest path from root node to leaf node.
	// both cases time complexity - O(n)
	public int heightOfBinaryTree_recursive(TreeNode root) {
		if (root == null)
			return 0;

		return 1 + Math.max(heightOfBinaryTree_recursive(root.left), heightOfBinaryTree_recursive(root.right));
	}

	public int heightOfBinaryTree_iterative(TreeNode root) {
		if (root == null)
			return 0;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		int height = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				TreeNode node = queue.poll();
				if (node.left != null)
					queue.add(node.left);

				if (node.right != null)
					queue.add(node.right);
			}
			// increment height at each level
			height++;
		}

		return height;
	}

	public static void main(String[] args) {
		BinaryTreeHeightProblems bth = new BinaryTreeHeightProblems();
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(10);
		root.right = new TreeNode(20);
		root.right.right = new TreeNode(30);
		System.out.println(bth.heightOfBinaryTree_recursive(root));
		System.out.println(bth.heightOfBinaryTree_iterative(root));
	}

}
