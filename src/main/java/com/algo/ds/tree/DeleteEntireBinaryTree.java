package com.algo.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.algo.common.Node;

/**
 * https://www.techiedelight.com/delete-given-binary-tree-iterative-recursive/
 * 
 * T - O(n)
 */
public class DeleteEntireBinaryTree {

	/**
	 * Using post order traversal as children need to be deleted first before parent
	 * 
	 * @param root
	 */
	public void delete_recursive(Node root) {
		if (root == null)
			return;

		delete_recursive(root.left);
		delete_recursive(root.right);

		root = null;
	}

	/**
	 * In iterative version, we perform level order traversal of the tree. The idea is to delete each node in the queue
	 * one by one after pushing their children to the queue. Note that we’re deleting a parent here before deleting its
	 * children’s as we’re pushing them into the queue and they will be processed and deleted afterwards.
	 * 
	 * @param root
	 */
	public void delete_iterative(Node root) {
		if (root == null)
			return;

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node r = queue.poll();
			if (r.left != null)
				queue.add(r.left);
			if (r.right != null)
				queue.add(r.right);

			r = null;
		}
		root = null;
	}
}
