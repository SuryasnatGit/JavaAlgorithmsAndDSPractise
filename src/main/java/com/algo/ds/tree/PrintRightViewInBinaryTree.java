package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.algo.common.TreeNode;

public class PrintRightViewInBinaryTree {

	public List<Integer> rightSideView(TreeNode root) {

		List<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}

		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		while (!queue.isEmpty()) {
			int i = 0;
			int size = queue.size();

			while (i++ < size) {
				TreeNode current = queue.poll();
				if (i == size) {
					result.add(current.data);
				}
				if (current.left != null) {
					queue.add(current.left);
				}
				if (current.right != null) {
					queue.add(current.right);
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {

		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);

		PrintRightViewInBinaryTree right = new PrintRightViewInBinaryTree();
		System.out.println(right.rightSideView(root));
	}
}
