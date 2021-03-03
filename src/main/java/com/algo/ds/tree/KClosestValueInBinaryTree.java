package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import com.algo.common.TreeNode;

/**
 * Date 10/08/2016
 * 
 * @author Tushar Roy
 *
 *         Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the
 *         target.
 *
 *         Time complexity O(log(n) + k)
 *
 *         https://leetcode.com/problems/closest-binary-search-tree-value-ii/
 */
public class KClosestValueInBinaryTree {

	// Disregard the fact that it is a BST, just preorder traversal all. O(N * logK)
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
		PriorityQueue<TreeNode> heap = new PriorityQueue<TreeNode>(k, new Comparator<TreeNode>() {
			public int compare(TreeNode node1, TreeNode node2) {
				double diff1 = Math.abs(node1.data - target);
				double diff2 = Math.abs(node2.data - target);

				// Max heap
				return (int) (diff2 - diff1);
			}
		});

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		while (!stack.isEmpty()) {
			TreeNode now = stack.pop();

			if (heap.size() < k) {
				heap.offer(now);
			} else {
				double diff = Math.abs(now.data - target);
				double peek = Math.abs(heap.peek().data - target);

				if (peek > diff) {
					heap.poll();
					heap.offer(now);
				}
			}

			if (now.right != null) {
				stack.push(now.right);
			}

			if (now.left != null) {
				stack.push(now.left);
			}
		}

		List<Integer> res = new ArrayList<Integer>();
		while (!heap.isEmpty()) {
			res.add(heap.poll().data);
		}

		return res;
	}

	public List<Integer> closestKValues1(TreeNode root, double target, int k) {
		if (root == null || k == 0) {
			return new ArrayList<>();
		}

		Stack<TreeNode> predecessor = new Stack<>();
		Stack<TreeNode> successor = new Stack<>();

		double closestDiff = Double.MAX_VALUE;
		TreeNode closestDiffNode = null;
		while (root != null) {
			predecessor.push(root);
			successor.push(root);
			if (Math.abs(target - root.data) < closestDiff) {
				closestDiff = Math.abs(target - root.data);
				closestDiffNode = root;
			}
			if (root.data == target) {
				break;
			}

			else if (target > root.data) {
				root = root.right;
			} else {
				root = root.left;
			}
		}

		while (predecessor.peek() != closestDiffNode) {
			predecessor.pop();
			successor.pop();
		}
		predecessor.pop();
		successor.pop();
		List<Integer> result = new ArrayList<>();
		result.add(closestDiffNode.data);
		TreeNode prec = closestDiffNode;
		TreeNode succ = closestDiffNode;
		k--;
		prec = predecessor(predecessor, prec);
		succ = successor(successor, succ);
		while (k > 0) {
			if (succ == null || (prec != null && Math.abs(target - prec.data) < Math.abs(target - succ.data))) {
				result.add(prec.data);
				prec = predecessor(predecessor, prec);
			} else {
				result.add(succ.data);
				succ = successor(successor, succ);
			}
			k--;
		}
		return result;
	}

	private TreeNode predecessor(Stack<TreeNode> stack, TreeNode current) {
		if (current == null) {
			return null;
		}
		if (current.left != null) {
			stack.push(current);
			current = current.left;
			while (current.right != null) {
				stack.push(current);
				current = current.right;
			}
			return current;
		} else {
			while (!stack.isEmpty() && stack.peek().left == current) {
				current = stack.pop();
			}
			if (stack.isEmpty()) {
				return null;
			} else {
				return stack.pop();
			}
		}
	}

	private TreeNode successor(Stack<TreeNode> stack, TreeNode current) {
		if (current == null) {
			return null;
		}
		if (current.right != null) {
			stack.push(current);
			current = current.right;
			while (current.left != null) {
				stack.push(current);
				current = current.left;
			}
			return current;
		} else {
			while (!stack.isEmpty() && stack.peek().right == current) {
				current = stack.pop();
			}
			if (stack.isEmpty()) {
				return null;
			} else {
				return stack.pop();
			}
		}
	}
}
