package com.algo.ds.tree;

import com.algo.common.TreeNode;

public class BinaryTree {
	public TreeNode addNode(int data, TreeNode head) {
		TreeNode tempHead = head;
		TreeNode n = new TreeNode(data);
		if (head == null) {
			head = n;
			return head;
		}
		TreeNode prev = null;
		while (head != null) {
			prev = head;
			if (head.data < data) {
				head = head.right;
			} else {
				head = head.left;
			}
		}
		if (prev.data < data) {
			prev.right = n;
		} else {
			prev.left = n;
		}
		return tempHead;
	}

	class IntegerRef {
		int height;
	}

	public int height(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftHeight = height(root.left);
		int rightHeight = height(root.right);
		return Math.max(leftHeight, rightHeight) + 1;
	}

	public static void main(String args[]) {
		BinaryTree bt = new BinaryTree();
		TreeNode head = null;
		head = bt.addNode(10, head);
		head = bt.addNode(15, head);
		head = bt.addNode(5, head);
		head = bt.addNode(7, head);
		head = bt.addNode(19, head);
		head = bt.addNode(20, head);
		head = bt.addNode(-1, head);
		head = bt.addNode(21, head);
		System.out.println(bt.height(head));

	}
}
