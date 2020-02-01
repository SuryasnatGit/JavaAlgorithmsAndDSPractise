package com.algo.ds.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * http://www.geeksforgeeks.org/check-two-nodes-cousins-binary-tree/
 * 
 * Given the binary Tree and the two nodes say a and b, determine whether the two nodes are cousins of each other or
 * not. Two nodes are cousins of each other if they are at same level and have different parents.
 * 
 * 
 * 
 * Assumption that both a and b are unique in tree. Test cases: Empty tree Tree with only root Tree and input with a and
 * b as cousin node Tree and input with a and b not cousin node Tree with input a and b being siblings(not cousin)
 */
public class CousinNodes {

	// T - O(n)
	public boolean areCousinNodesRecursive(Node root, int a, int b) {
		if (root == null)
			return false;

		return level(root, a, 1) == level(root, b, 1) && !isSibling(root, a, b);
	}

	private boolean isSibling(Node root, int a, int b) {
		if (root == null)
			return false;

		return (root.left.data == a && root.left.data == b) || (root.left.data == b && root.right.data == a)
				|| (isSibling(root.left, a, b) || isSibling(root.right, a, b));
	}

	private int level(Node root, int target, int level) {
		// base case
		if (root == null)
			return 0;

		if (root.data == target)
			return level;

		// search in left sub tree
		int l = level(root.left, target, level + 1);
		if (l != 0)
			return l;

		// search in right sub tree
		return level(root.right, target, level + 1);
	}

	// T - O(n) S - O(n)
	public boolean areCousinNodesIterative(Node root, int a, int b) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);

		int levelSize = 1;
		int tempLevelSize = 1;
		boolean foundFirst = false;
		while (!queue.isEmpty()) {
			levelSize = 0;
			while (tempLevelSize > 0) {
				Node node = queue.poll();
				// this is to make sure a and b are not siblings of each other
				// if they are return false since they cant be cousins
				if (checkSameParent(node, a, b)) {
					return false;
				}
				if (node.data == a || node.data == b) {
					if (foundFirst) {
						return true;
					}
					foundFirst = true;
				}
				if (node.left != null) {
					queue.add(node.left);
					levelSize++;
				}
				if (node.right != null) {
					queue.add(node.right);
					levelSize++;
				}
				tempLevelSize--;
			}

			if (foundFirst) {
				return false;
			}

			tempLevelSize = levelSize;
		}
		return false;
	}

	private boolean checkSameParent(Node root, int a, int b) {
		if (root.left != null && root.right != null) {
			if ((root.left.data == a || root.left.data == b) && (root.right.data == a || root.right.data == b)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * https://www.techiedelight.com/print-cousins-of-given-node-binary-tree/
	 * 
	 */
	// Function to print all cousins of given node
	public void printAllCousins(Node root, Node node) {
		// Using AtomicBoolean as Integer is passed by value in Java
		AtomicInteger level = new AtomicInteger(0);

		// find level of given node
		findLevel(root, node, 1, level);

		// print all cousins of given node using its level number
		printLevel(root, node, level.get());
	}

	// Function to find level of given node x
	private void findLevel(Node root, Node x, int index, AtomicInteger level) {
		// return if tree is empty or level is already found
		if (root == null || level.get() != 0) {
			return;
		}

		// if given node is found, update its level
		if (root == x) {
			level.set(index);
		}

		// recur for left and right subtree
		findLevel(root.left, x, index + 1, level);
		findLevel(root.right, x, index + 1, level);
	}

	private void printLevel(Node root, Node node, int level) {
		// base case
		if (root == null) {
			return;
		}

		// print cousins
		if (level == 1) {
			System.out.print(root.data + " ");
			return;
		}

		// recur for left and right subtree if given node is not child of the root
		if (!(root.left != null && root.left == node || root.right != null && root.right == node)) {
			printLevel(root.left, node, level - 1);
			printLevel(root.right, node, level - 1);
		}
	}

	public static void main(String args[]) {
		BinaryTree bt = new BinaryTree();
		Node head = null;
		head = bt.addNode(10, head);
		head = bt.addNode(15, head);
		head = bt.addNode(5, head);
		head = bt.addNode(7, head);
		head = bt.addNode(19, head);
		head = bt.addNode(20, head);
		head = bt.addNode(-1, head);
		head = bt.addNode(21, head);
		head = bt.addNode(-6, head);
		CousinNodes cn = new CousinNodes();
		System.out.println("iterative results");
		System.out.println(cn.areCousinNodesIterative(head, 10, 19));
		System.out.println(cn.areCousinNodesIterative(head, 19, 7));
		System.out.println(cn.areCousinNodesIterative(head, 19, -1));
		System.out.println(cn.areCousinNodesIterative(head, 19, -6));
		System.out.println(cn.areCousinNodesIterative(head, -1, 7));
		System.out.println(cn.areCousinNodesIterative(head, 7, -1));

		System.out.println("recursive results");
		System.out.println(cn.areCousinNodesRecursive(head, 10, 19));
		System.out.println(cn.areCousinNodesRecursive(head, 19, 7));
		System.out.println(cn.areCousinNodesRecursive(head, 19, -1));
		System.out.println(cn.areCousinNodesRecursive(head, 19, -6));
		System.out.println(cn.areCousinNodesRecursive(head, -1, 7));
		System.out.println(cn.areCousinNodesRecursive(head, 7, -1));
	}
}
