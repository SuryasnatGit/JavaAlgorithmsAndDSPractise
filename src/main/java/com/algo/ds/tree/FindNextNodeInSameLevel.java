package com.algo.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.algo.common.TreeNode;

/**
 * https://www.techiedelight.com/find-next-node-in-same-level-binary-tree/
 *
 */
public class FindNextNodeInSameLevel {

	public TreeNode findNextNodeInSameLevel(TreeNode root, int val) {
		if (root == null)
			return null;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		TreeNode front = null;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				front = queue.poll();

				// if desired node is found then return the next right node.
				if (front.data == val) {
					if (size == 0)
						return null;

					return queue.peek();
				}

				if (front.left != null)
					queue.add(front.left);

				if (front.right != null)
					queue.add(front.right);
			}

		}

		return null;
	}

	public static void main(String[] args) {
		FindNextNodeInSameLevel fn = new FindNextNodeInSameLevel();

		TreeNode n = new TreeNode(1);
		n.left = new TreeNode(2);
		n.right = new TreeNode(3);

		System.out.println(fn.findNextNodeInSameLevel(n, 2));
	}
}
