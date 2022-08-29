package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.algo.common.TreeNode;

/**
 * Youtube link - https://youtu.be/9PHkM0Jri_4
 * 
 * http://www.geeksforgeeks.org/level-order-tree-traversal/.
 * 
 * level order traversal is breadth first traversal for the tree.
 * 
 * Test cases:
 * 
 * Empty tree. Tree with only left side. Tree with only right side. Mixed size tree. Full tree. complete tree.
 */
public class LevelOrderTraversal {

	public void levelOrder(TreeNode root) {
		List<List<Integer>> list = new ArrayList<>();
		levelOrderRecursive(root, 0, list);
		list.forEach(a -> System.out.println(a));
	}

	private void levelOrderRecursive(TreeNode node, int level, List<List<Integer>> list) {
		if (node == null) {
			return;
		}

		if (level == list.size()) {
			list.add(new ArrayList<Integer>());
		}
		list.get(level).add(node.data);
		levelOrderRecursive(node.left, level + 1, list);
		levelOrderRecursive(node.right, level + 1, list);
	}

	public void levelOrderIterative(TreeNode root) {
		if (root == null) {
			System.out.println("Please enter a valid tree!");
			return;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		System.out.println();
		while (!queue.isEmpty()) {
			root = queue.poll();
			System.out.print(root.data + " ");
			if (root.left != null) {
				queue.offer(root.left);
			}
			if (root.right != null) {
				queue.offer(root.right);
			}
		}
	}

	public void reverseLevelOrderIterative(TreeNode root) {
		if (root == null) {
			return;
		}
		Queue<TreeNode> q = new LinkedList<>();
		Stack<TreeNode> s = new Stack<>();

		q.offer(root);
		while (!q.isEmpty()) {
			root = q.poll();
			if (root.right != null) {
				q.offer(root.right);
			}
			if (root.left != null) {
				q.offer(root.left);
			}
			s.push(root);
		}
		while (!s.isEmpty()) {
			System.out.print(s.pop().data + " ");
		}
	}

	public static void main(String args[]) {
		LevelOrderTraversal loi = new LevelOrderTraversal();

		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		loi.levelOrder(root);

		loi.levelOrderIterative(root);
		System.out.println();
		loi.reverseLevelOrderIterative(root);
	}
}
