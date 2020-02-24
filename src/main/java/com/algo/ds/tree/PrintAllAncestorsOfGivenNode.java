package com.algo.ds.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import com.algo.common.TreeNode;

/**
 * https://www.techiedelight.com/find-ancestors-of-given-node-binary-tree/
 */
public class PrintAllAncestorsOfGivenNode {

	// T - O(n) S - O(h) where h = height of tree
	public boolean printAncestors(TreeNode root, int data) {
		if (root == null)
			return false;

		if (root.data == data)
			return true;

		// search in left sub tree
		boolean left = printAncestors(root.left, data);

		boolean right = false;
		if (!left)
			right = printAncestors(root.right, data);

		if (left || right)
			System.out.println(root.data);

		return left || right;
	}

	// Iterative function to print all ancestors of given node in a binary tree
	public void printAncestors(Node root, int node) {
		// Base Case
		if (root == null)
			return;

		// create an empty map to store parent pointers of binary tree nodes
		Map<Integer, Integer> parent = new HashMap<>();

		// set parent of root node as null
		parent.put(root.data, 0);

		// set parent nodes for all nodes of binary tree
		setParent(root, parent);

		// print ancestors of given node using parent map
		printTopToBottomPath(parent, node);
	}

	// iterative function to set parent nodes for all nodes of binary tree
	// in given map. The function is similar to iterative pre-order traversal
	private void setParent(Node root, Map<Integer, Integer> parent) {
		// create an empty stack and push root node to it
		Deque<Node> stack = new ArrayDeque<>();
		stack.add(root);

		// run till stack is not empty
		while (!stack.isEmpty()) {
			// Pop the top item from stack
			Node curr = stack.poll();

			// push its right child to stack and set its parent in the map
			if (curr.right != null) {
				parent.put(curr.right.data, curr.data);
				stack.add(curr.right);
			}

			// push its left child to stack and set its parent in the map
			if (curr.left != null) {
				parent.put(curr.left.data, curr.data);
				stack.add(curr.left);
			}
		}
	}

	// Function to print root to leaf paths without using recursion
	private void printTopToBottomPath(Map<Integer, Integer> parent, int key) {
		while ((key = parent.get(key)) != 0) {
			System.out.print(key + " ");
		}
	}

	public static void main(String[] args) {
		PrintAllAncestorsOfGivenNode pr = new PrintAllAncestorsOfGivenNode();

		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(5);

		pr.printAncestors(root, 5);
	}
}
