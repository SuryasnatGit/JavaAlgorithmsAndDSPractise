package com.algo.ds.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import com.algo.common.TreeNode;

/**
 *
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to
 * right, then right to left for the next level and alternate between).
 * 
 * Time complexity is O(n) Space complexity is O(n)
 *
 * Reference https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * 
 * Category : Medium
 */
public class ZigZagLevelOrderTraversal {

	/**
	 * Two stack to print in spiral way
	 */
	public void spiralWithTwoStack(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> s1 = new Stack<>();
		Stack<TreeNode> s2 = new Stack<>();
		s1.push(root);

		while (!s1.isEmpty() || !s2.isEmpty()) {
			while (!s1.isEmpty()) {
				root = s1.pop();
				System.out.print(root.data + " ");
				if (root.left != null) {
					s2.push(root.left);
				}
				if (root.right != null) {
					s2.push(root.right);
				}
			}
			while (!s2.isEmpty()) {
				root = s2.pop();
				System.out.print(root.data + " ");
				if (root.right != null) {
					s1.push(root.right);
				}
				if (root.left != null) {
					s1.push(root.left);
				}
			}
		}
	}

	/**
	 * One deque with count method to print tree in spiral order
	 */
	public void spiralWithOneDeque(TreeNode root) {
		if (root == null) {
			return;
		}

		Deque<TreeNode> deque = new LinkedList<TreeNode>();
		deque.offerFirst(root);

		int count = 1;
		boolean flip = true;

		while (!deque.isEmpty()) {
			int currentCount = 0;

			while (count > 0) {
				if (flip) {
					root = deque.pollFirst();
					System.out.print(root.data + " ");
					if (root.left != null) {
						deque.offerLast(root.left);
						currentCount++;
					}
					if (root.right != null) {
						deque.offerLast(root.right);
						currentCount++;
					}
				} else {
					root = deque.pollLast();
					System.out.print(root.data + " ");
					if (root.right != null) {
						deque.offerFirst(root.right);
						currentCount++;
					}
					if (root.left != null) {
						deque.offerFirst(root.left);
						currentCount++;
					}
				}
				count--;
			}

			flip = !flip;
			count = currentCount;
		}
	}

	public static void main(String args[]) {

		TreeNode head = new TreeNode(3);
		head.left = new TreeNode(3);
		head.right = new TreeNode(-6);
		head.left.left = new TreeNode(-7);
		head.left.right = new TreeNode(2);
		head.right.left = new TreeNode(9);
		head.right.right = new TreeNode(6);
		head.right.right.left = new TreeNode(11);
		head.right.right.right = new TreeNode(13);

		ZigZagLevelOrderTraversal tt = new ZigZagLevelOrderTraversal();
		System.out.println("Two stack method");
		tt.spiralWithTwoStack(head);
		System.out.println("\nOne deque with count");
		tt.spiralWithOneDeque(head);

	}
}
