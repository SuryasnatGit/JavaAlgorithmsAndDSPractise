package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.algo.common.TreeNode;

/**
 * Given a root of a tree. The tree may be of any depth and width. Transform it in a way that each node(except probably
 * one) would either have N or 0 children
 * 
 * convert a binary tree to a N-ary complete tree
 *
 */
public class BinaryToNaryTree {
	static class Node {
		int val;
		List<Node> children = new ArrayList<Node>();

		Node(int val) {
			this.val = val;
		}
	}

	public Node binaryToNaryTree(TreeNode root, int N) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		List<TreeNode> list = new ArrayList<TreeNode>();

		while (!queue.isEmpty()) {
			TreeNode now = queue.poll();
			list.add(now);

			if (now.left != null) {
				queue.offer(now.left);
			}
			if (now.right != null) {
				queue.offer(now.right);
			}
		}

		Node newRoot = null;
		Queue<Node> visitedNodes = new LinkedList<Node>();
		for (int i = 0; i < list.size(); i++) {
			Node parent = null;
			if (i == 0) {
				parent = new Node(list.get(i).data);
				newRoot = parent;
			} else {
				parent = visitedNodes.poll(); // Always get from queue, no need to new
			}

			for (int j = i * N + 1; j <= i * N + N; j++) { // Children section
				if (j >= list.size()) {
					break;
				}
				Node nextNode = new Node(list.get(j).data);
				visitedNodes.offer(nextNode);

				parent.children.add(nextNode);
			}
		}

		return newRoot;
	}
}
