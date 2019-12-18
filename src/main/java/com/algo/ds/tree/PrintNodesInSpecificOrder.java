package com.algo.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.algo.common.TreeNode;

/**
 * https://www.techiedelight.com/print-nodes-binary-tree-specific-order/
 *
 */
public class PrintNodesInSpecificOrder {

	public void printNodes(TreeNode root) {
		if (root == null)
			return;

		System.out.println(root.data);

		Queue<TreeNode> q1 = new LinkedList<>();
		Queue<TreeNode> q2 = new LinkedList<>();
		q1.add(root.left);
		q2.add(root.right);

		while (!q1.isEmpty()) {
			int n = q1.size();
			while (n-- > 0) {
				TreeNode node1 = q1.poll();
				System.out.println(node1.data);
				// push left and right child to first Q
				if (node1.left != null) {
					q1.add(node1.left);
				}
				if (node1.right != null) {
					q1.add(node1.right);
				}

				TreeNode node2 = q2.poll();
				System.out.println(node2.data);
				// push right and left child to second Q
				if (node2.right != null) {
					q2.add(node2.right);
				}
				if (node2.left != null) {
					q2.add(node2.left);
				}
			}
		}
	}

	public static void main(String[] args) {
		PrintNodesInSpecificOrder p = new PrintNodesInSpecificOrder();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		p.printNodes(root);
	}
}
