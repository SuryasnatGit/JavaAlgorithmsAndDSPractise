package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.algo.common.TreeNode;

/**
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * 
 * minimum depth of binary tree follow up : what if this tree left subtree may be infinite.
 *
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 */
public class DepthInBinaryTree {

	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		if (root.left == null && root.right == null) {
			return 1;
		}

		int leftDepth = Integer.MAX_VALUE;
		if (root.left != null) {
			leftDepth = minDepth(root.left);
		}

		int rightDepth = Integer.MAX_VALUE;
		if (root.right != null) {
			rightDepth = minDepth(root.right);
		}

		return Math.min(leftDepth, rightDepth) + 1;
	}

	public int minDepthBFS(TreeNode root) {
		if (root == null) {
			return 0;
		}

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		int level = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode now = queue.poll();

				if (now.left == null && now.right == null) {
					return level + 1;
				}

				if (now.left != null) {
					queue.offer(now.left);
				}

				if (now.right != null) {
					queue.offer(now.right);
				}
			}

			level++;
		}

		return 0;
	}

	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		if (root.left == null && root.right == null) {
			return 1;
		}

		int leftDepth = Integer.MIN_VALUE;
		if (root.left != null) {
			leftDepth = maxDepth(root.left);
		}

		int rightDepth = Integer.MIN_VALUE;
		if (root.right != null) {
			rightDepth = maxDepth(root.right);
		}

		return Math.max(leftDepth, rightDepth) + 1;
	}

	// Program to print max depth path in binary tree
	private int max = 0;
	private List<Integer> res = null;

	public void printMaxDepth(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		helper(list, root, 1);

		System.out.println(max);
		for (int val : res) {
			System.out.print(val + "--");
		}
	}

	private void helper(List<Integer> list, TreeNode root, int level) { // 用return value as height不大好使
		if (root == null) {
			return;
		}

		if (root.left == null && root.right == null) {
			list.add(root.data);
			if (level > max) {
				res = new ArrayList<Integer>(list);
				max = Math.max(max, level);
			}
			list.remove(list.size() - 1);
			return;
		}

		list.add(root.data);
		helper(list, root.left, level + 1);
		helper(list, root.right, level + 1);
		list.remove(list.size() - 1);
	}

	public static void main(String[] args) {
		DepthInBinaryTree mind = new DepthInBinaryTree();
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);

		System.out.println(mind.minDepth(root));
		System.out.println(mind.minDepthBFS(root));
		System.out.println(mind.maxDepth(root));
		mind.printMaxDepth(root);
	}

}
