package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.algo.ds.stack.LinkedListStack;
import com.algo.ds.stack.Stack;

public class TreeTraversalUtility<K, V> {

	/**
	 * Preorder binary tree traversal is a classic interview problem about trees. The key to solve this problem is to
	 * understand the following: <br/>
	 * What is preorder? (parent node is processed before its children) Use Stack from Java Core library.<br/>
	 * The key is using a stack to store left and right children, and push right child first so that it is processed
	 * after the left child.<br/>
	 * order is P-> LC -> RC
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal(TreeNode<K, V> root) {
		List<Integer> list = new ArrayList<>();
		if (root == null)
			return list;

		// define a stack
		Stack<TreeNode<K, V>> stack = new LinkedListStack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode<K, V> node = stack.pop();
			list.add(node.value);
			if (node.right != null)
				stack.push(node.right);
			if (node.left != null)
				stack.push(node.left);
		}
		return list;
	}

	/**
	 * The key to solve inorder traversal of binary tree includes the following:
	 * 
	 * The order of "inorder" is: left child -> parent -> right child <br/>
	 * Use a stack to track nodes <br/>
	 * Understand when to push node into the stack and when to pop node out of the stack <br/>
	 * order is LC -> P -> RC
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversal_1(TreeNode<K, V> root) {
		List<Integer> list = new ArrayList<>();
		if (root == null)
			return list;

		Stack<TreeNode<K, V>> stack = new LinkedListStack<>();
		TreeNode<K, V> p = root;
		while (!stack.isEmpty() || p != null) {
			if (p != null) {
				stack.push(p);
				p = p.left;
			} else {
				TreeNode<K, V> node = stack.pop();
				list.add(node.value);
				node = node.right;
			}
		}
		return list;
	}

	// for inorder traversal by recursive way
	private List<Integer> list = new ArrayList<>();

	/**
	 * in-order traversal using recursive approach
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversal_2(TreeNode<K, V> root) {
		if (root != null)
			helper(root);

		return list;
	}

	private void helper(TreeNode<K, V> node) {
		if (node.left != null)
			helper(node.left);

		list.add(node.value);

		if (node.right != null)
			helper(node.right);
	}

	/**
	 * inorder traversal using stack
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversal_3(TreeNode<K, V> root) {
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode<K, V>> stack = new LinkedListStack<>();

		TreeNode<K, V> p = root;
		// go to end of left edge
		while (p != null) {
			stack.push(p);
			p = p.left;
		}
		while (!stack.isEmpty()) {
			TreeNode<K, V> node = stack.pop();
			list.add(node.value);
			if (node.right != null) {
				node = node.right;
				if (node != null) {
					stack.push(node);
					node = node.left;
				}
			}
		}
		return list;
	}

	public void postOrderTraversal_recursive(TreeNode<K, V> root) {
		if (root != null) {
			postOrderTraversal_recursive(root.left);
			postOrderTraversal_recursive(root.right);
			System.out.println(root.value);
		}
	}

	public List<Integer> postOrderTraversal_iterative(TreeNode<K, V> root) {
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode<K, V>> stack = new LinkedListStack<>();

		if (root == null)
			return list;

		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode<K, V> temp = stack.top(); // we will just peek at the top
												// rather than popping it tp
												// determine if it's
												// left child and right child are empty. if so then add the value to
												// list.
			if (temp.left == null && temp.right == null) {// this means the node is a child node
				temp = stack.pop();
				list.add(temp.value);
			} else {
				// check the right sub tree and add to stack, so that left comes out first from stack in LIFO fashion
				if (temp.right != null) {
					stack.push(temp.right);
					temp.right = null; // to signify that this node is already visited
				}
				if (temp.left != null) {
					stack.push(temp.left);
					temp.left = null;
				}
			}
		}
		return list;
	}

	/**
	 * recursive approach. time complexity is O(N^2) worst case
	 * 
	 * @param root
	 */
	public void levelOrderTraversal(TreeNode<K, V> root) {
		int h = height(root);
		for (int i = 1; i <= h; i++) {
			printGivenLevel(root, i);
		}
	}

	private void printGivenLevel(TreeNode<K, V> root, int level) {
		if (root == null)
			return;
		if (level == 1)
			System.out.print(root.value + " ");
		else if (level > 1) {
			printGivenLevel(root.left, level - 1);
			printGivenLevel(root.right, level - 1);
		}
	}

	private int height(TreeNode<K, V> root) {
		if (root == null)
			return 0;
		else {
			int lh = height(root.left);
			int rh = height(root.right);
			if (lh > rh)
				return lh + 1;
			else
				return rh + 1;
		}
	}

	/**
	 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
	 * 
	 * For example, given binary tree {3,9,20,#,#,15,7},
	 * 
	 * 3 / \ 9 20 / \ 15 7 return its level order traversal as [[15,7], [9,20],[3]]
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrderTraversal_bottomUp(TreeNode<K, V> root) {

		List<List<Integer>> result = levelOrderTraversal_topDown(root);

		// reverse the list
		Collections.reverse(result);

		return result;
	}

	/**
	 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by
	 * level).
	 * 
	 * For example: Given binary tree {3,9,20,#,#,15,7},
	 * 
	 * 3 / \ 9 20 / \ 15 7 return its level order traversal as [[3], [9,20], [15,7]]
	 * 
	 * Analysis
	 * 
	 * It is obvious that this problem can be solve by using a queue. However, if we use one queue we can not track when
	 * each level starts. So we use two queues to track the current level and the next level.
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrderTraversal_topDown(TreeNode<K, V> root) {
		List<List<Integer>> result = new ArrayList<>();

		if (root == null)
			return result;

		LinkedList<TreeNode<K, V>> current = new LinkedList<>();// keep track of current level
		LinkedList<TreeNode<K, V>> next = new LinkedList<>();// keep track of next level
		current.offer(root);

		List<Integer> numberList = new ArrayList<>();
		while (!current.isEmpty()) {
			TreeNode<K, V> head = current.poll();
			numberList.add(head.value);
			if (head.left != null)
				next.offer(head.left);
			if (head.right != null)
				next.offer(head.right);

			if (current.isEmpty()) {
				current = next;
				next = new LinkedList<>();
				result.add(numberList);
				numberList = new ArrayList<>();
			}
		}
		return result;
	}

	/**
	 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by
	 * column).
	 * 
	 * Java Solution
	 * 
	 * For each node, its left child's degree is -1 and is right child's degree is +1. We can do a level order traversal
	 * and save the degree information.
	 * 
	 * @param root
	 * @return
	 */
	public List<List<Integer>> verticalOrderTraversal(TreeNode<K, V> root) {
		List<List<Integer>> result = new ArrayList<>();

		if (root == null)
			return result;

		Map<Integer, List<Integer>> map = new HashMap<>();

		LinkedList<TreeNode<K, V>> queue = new LinkedList<>();
		LinkedList<Integer> level = new LinkedList<>();

		queue.offer(root);
		level.offer(0);

		int minLevel = 0;
		int maxLevel = 0;

		while (!queue.isEmpty()) {
			TreeNode<K, V> p = queue.poll();
			int l = level.poll();

			minLevel = Math.min(minLevel, l);
			maxLevel = Math.max(maxLevel, l);

			if (map.containsKey(l)) {
				map.get(l).add(p.value);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(p.value);
				map.put(l, list);
			}

			if (p.left != null) {
				queue.offer(p.left);
				level.offer(l - 1);
			}

			if (p.right != null) {
				queue.offer(p.right);
				level.offer(l + 1);
			}
		}

		System.out.println(map);
		for (int i = minLevel; i <= maxLevel; i++) {
			if (map.containsKey(i)) {
				result.add(map.get(i));
			}
		}

		return result;
	}

	/**
	 * Using Morris Traversal, we can traverse the tree without using stack and recursion. The idea of Morris Traversal
	 * is based on Threaded Binary Tree. In this traversal, we first create links to Inorder successor and print the
	 * data using these links, and finally revert the changes to restore original tree.
	 * 
	 * @param root
	 */
	public void morrisTraversal(TreeNode<K, V> root) {
		TreeNode<K, V> curr, pre;
		if (root == null)
			return;
		curr = root;
		while (curr != null) {
			if (curr.left == null) {
				System.out.println(curr.value + " ");
				curr = curr.right;
			} else {
				pre = curr.left;
				while (pre.right != null && pre.right != curr)
					pre = pre.right;

				if (pre.right == null) {
					pre.right = curr;
					curr = curr.left;
				} else {
					// restore
					pre.right = null;
					System.out.println(curr.value);
					curr = curr.right;
				}
			}
		}
	}

	public static void main(String[] args) {
		TreeTraversalUtility<Integer, Integer> ut = new TreeTraversalUtility<>();
		TreeNode<Integer, Integer> root = new TreeNode<>(1, 1);
		root.left = new TreeNode<>(2, 2);
		root.right = new TreeNode<>(3, 3);
		root.left.left = new TreeNode<>(4, 4);
		root.left.right = new TreeNode<>(5, 5);
		ut.levelOrderTraversal(root);
		System.out.println();
		System.out.println(ut.levelOrderTraversal_topDown(root));
		System.out.println(ut.levelOrderTraversal_bottomUp(root));
		System.out.println(ut.verticalOrderTraversal(root));
	}
}
