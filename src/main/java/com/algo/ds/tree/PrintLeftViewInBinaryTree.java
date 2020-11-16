package com.algo.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.algo.common.TreeNode;

public class PrintLeftViewInBinaryTree {

	/**
	 * prints 1st element in each level
	 */
	public void printLeftView(TreeNode root) {
		if (root == null)
			return;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		TreeNode curr;
		while (!queue.isEmpty()) {
			int i = 0;
			int size = queue.size();
			while (i++ < size) {
				curr = queue.poll();
				// if this is the first node in current level then print it. for last node in curr level check against
				// size
				if (i == 1)
					System.out.println(curr.data);

				if (curr.left != null) {
					queue.add(curr.left);
				}
				if (curr.right != null) {
					queue.add(curr.left);
				}
			}
		}
	}
}
