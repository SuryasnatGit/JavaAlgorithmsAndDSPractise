package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.algo.common.TreeNode;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by
 * column). If two nodes are in the same row and column, the order should be from left to right.
 *
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/.
 * 
 * For each node, its left child's degree is -1 and is right child's degree is +1. We can do a level order traversal and
 * save the degree information.
 * 
 * 
 */
public class VerticalOrderTraversal {
	public List<List<Integer>> verticalOrder2Queues(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}
		int minLevel = 0;
		int maxLevel = 0;
		Map<Integer, List<Integer>> map = new HashMap<>();

		Deque<TreeNode> queue = new LinkedList<>();
		Deque<Integer> level = new LinkedList<>();

		queue.offerFirst(root);
		level.offerFirst(0);

		while (!queue.isEmpty()) {
			root = queue.pollFirst();
			int verticalLevel = level.pollFirst();
			minLevel = Math.min(minLevel, verticalLevel);
			maxLevel = Math.max(maxLevel, verticalLevel);

			List<Integer> r = map.get(verticalLevel);
			if (r == null) {
				r = new ArrayList<>();
				map.put(verticalLevel, r);
			}
			r.add(root.data);

			if (root.left != null) {
				queue.offerLast(root.left);
				level.offerLast(verticalLevel - 1);
			}

			if (root.right != null) {
				queue.offerLast(root.right);
				level.offerLast(verticalLevel + 1);
			}
		}

		List<List<Integer>> result = new ArrayList<>();
		for (int i = minLevel; i <= maxLevel; i++) {
			List<Integer> r = map.get(i);
			result.add(r);
		}
		return result;
	}

	// As TreeNode and Column matters (Row doesn't matter, since we use level order traversal),
	// we can build a Java class with these 2 attributes. In this way, we don't need to use 2 queues
	public List<List<Integer>> verticalOrder1Queue(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();

		if (root == null) {
			return res;
		}

		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		// This Queue contains both node and col
		Queue<TreeColumnNode> queue = new LinkedList<TreeColumnNode>();
		queue.offer(new TreeColumnNode(root, 0));

		// 记录一下两头
		int min = 0, max = 0;

		// Level order traversal
		while (!queue.isEmpty()) {
			TreeColumnNode cNode = queue.poll();
			TreeNode node = cNode.node;
			int col = cNode.col;

			if (!map.containsKey(col)) {
				map.put(col, new ArrayList<Integer>());
			}
			map.get(col).add(node.data);

			if (node.left != null) {
				TreeColumnNode leftCNode = new TreeColumnNode(node.left, col - 1);
				queue.offer(leftCNode);
				min = Math.min(min, col - 1);
			}

			if (node.right != null) {
				TreeColumnNode rightCNode = new TreeColumnNode(node.right, col + 1);
				queue.offer(rightCNode);
				max = Math.max(max, col + 1);
			}
		}

		for (int col = min; col <= max; col++) {
			res.add(map.get(col));
		}

		return res;
	}

	class TreeColumnNode {
		TreeNode node;
		int col;

		public TreeColumnNode(TreeNode node, int col) {
			this.col = col;
			this.node = node;
		}
	}
}