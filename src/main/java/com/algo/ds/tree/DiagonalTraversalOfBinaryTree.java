package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://www.techiedelight.com/print-diagonal-traversal-binary-tree/
 */
public class DiagonalTraversalOfBinaryTree {

	// T - O(n log n) S - O(n)
	public void diagonal(Node root) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		diagonalRecursive(root, 0, map);

		for (List<Integer> list : map.values()) {
			System.out.println(list);
		}
	}

	private void diagonalRecursive(Node root, int diagonal, Map<Integer, List<Integer>> map) {
		if (root == null)
			return;

		if (!map.containsKey(diagonal)) {
			map.put(diagonal, new ArrayList<>());
		}
		map.get(diagonal).add(root.data);

		// increment diagonal value for left child
		diagonalRecursive(root.left, diagonal + 1, map);

		// keep same diagonal value for right child
		diagonalRecursive(root.right, diagonal, map);
	}

	// T - O(n) S - O(n)
	public void diagonalIterative(Node root) {
		if (root == null)
			return;

		Node sentinel = Node.newNode(-1);

		Queue<Node> queue = new LinkedList<>();
		// add first diaginal to queue
		while (root != null) {
			queue.add(root);
			root = root.right;
		}
		// add sentinel node to queue to mark the end of the diagonal
		queue.add(sentinel);

		// run till only sentinel is left
		while (queue.size() != 1) {
			Node node = queue.poll();
			if (node != sentinel) {
				System.out.print(node.data + " ");
				node = node.left;
				while (node != null) {
					queue.add(node);
					node = node.right;
				}
			} else {
				// if end of current diagonal is reached then add sentinel
				queue.add(sentinel);
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		DiagonalTraversalOfBinaryTree diag = new DiagonalTraversalOfBinaryTree();

		Node n = Node.newNode(1);
		n.left = Node.newNode(2);
		n.right = Node.newNode(3);
		n.left.right = Node.newNode(4);

		diag.diagonal(n);
		diag.diagonalIterative(n);
	}
}
