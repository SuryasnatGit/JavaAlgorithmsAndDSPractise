package com.algo.ds.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import com.algo.common.TreeNode;

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
	public boolean areCousinNodesRecursive(TreeNode root, int a, int b) {
		if (root == null)
			return false;

		return level(root, a, 1) == level(root, b, 1) && !isSibling(root, a, b);
	}

	private boolean isSibling(TreeNode root, int a, int b) {
		if (root == null)
			return false;

		return (root.left.data == a && root.left.data == b) || (root.left.data == b && root.right.data == a)
				|| (isSibling(root.left, a, b) || isSibling(root.right, a, b));
	}

	private int level(TreeNode root, int target, int level) {
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
	public boolean areCousinNodesIterative(TreeNode root, int a, int b) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);

		int levelSize = 1;
		int tempLevelSize = 1;
		boolean foundFirst = false;
		while (!queue.isEmpty()) {
			levelSize = 0;
			while (tempLevelSize > 0) {
				TreeNode node = queue.poll();
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

	private boolean checkSameParent(TreeNode root, int a, int b) {
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
	public void printAllCousins(TreeNode root, TreeNode node) {
		// Using AtomicBoolean as Integer is passed by value in Java
		AtomicInteger level = new AtomicInteger(0);

		// find level of given node
		findLevel(root, node, 1, level);

		// print all cousins of given node using its level number
		printLevel(root, node, level.get());
	}

	// Function to find level of given node x
	private void findLevel(TreeNode root, TreeNode x, int index, AtomicInteger level) {
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

	private void printLevel(TreeNode root, TreeNode node, int level) {
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
		TreeNode head = null;
		bt.insert(10);
		bt.insert(15);
		bt.insert(5);
		bt.insert(7);
		bt.insert(19);
		bt.insert(20);
		bt.insert(-1);
		bt.insert(21);
		bt.insert(-6);
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
