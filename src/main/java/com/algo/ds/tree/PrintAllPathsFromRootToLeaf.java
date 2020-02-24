package com.algo.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.algo.common.TreeNode;

/**
 * https://www.techiedelight.com/print-all-paths-from-root-to-leaf-nodes-binary-tree/
 *
 */
public class PrintAllPathsFromRootToLeaf {

	// T - O(n) S - O(h)
	public void printPathsRecursive(TreeNode root) {
		Queue<Integer> queue = new LinkedList<>();
		printPathsRecursive(root, queue);
	}

	private void printPathsRecursive(TreeNode root, Queue<Integer> queue) {
		if (root == null)
			return;

		// include current node to path
		queue.add(root.data);

		if (isLeafNode(root))
			System.out.println(queue);

		// recur for left and right subtree
		printPathsRecursive(root.left, queue);
		printPathsRecursive(root.right, queue);

		// remove current node after left and right subtree are done
		queue.remove(root.data);
	}

	private boolean isLeafNode(TreeNode root) {
		return root.left == null && root.right == null;
	}

	public void printPathsIterative(TreeNode root) {
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(root, ""));

		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			TreeNode node = pair.node;
			String path = pair.path;
			path += " " + node.data;

			// if leaf node
			if (node.left == null && node.right == null)
				System.out.println(path);

			if (node.right != null)
				queue.add(new Pair(node.right, path));

			if (node.left != null)
				queue.add(new Pair(node.left, path));
		}
	}

	public static void main(String[] args) {
		PrintAllPathsFromRootToLeaf pr = new PrintAllPathsFromRootToLeaf();

		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);

		pr.printPathsRecursive(root);

		System.out.println();

		pr.printPathsIterative(root);
	}
}

class Pair {
	TreeNode node;
	String path;

	public Pair(TreeNode n, String p) {
		this.node = n;
		this.path = p;
	}
}