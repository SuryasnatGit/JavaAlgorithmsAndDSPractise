package com.algo.ds.tree;

import java.util.LinkedList;
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
		if (root == null) {
			System.out.println("Please enter a valid tree!");
			return;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		System.out.println();
		while (queue.size() > 0) {
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

	public void reverseLevelOrderTraversal(TreeNode root) {
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
		BinaryTree bt = new BinaryTree();
		TreeNode head = null;
		head = bt.addNode(10, head);
		head = bt.addNode(15, head);
		head = bt.addNode(5, head);
		head = bt.addNode(7, head);
		head = bt.addNode(19, head);
		head = bt.addNode(20, head);
		head = bt.addNode(-1, head);
		loi.levelOrder(head);
	}
}
