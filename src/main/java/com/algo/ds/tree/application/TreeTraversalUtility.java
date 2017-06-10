package com.algo.ds.tree.application;

import java.util.ArrayList;
import java.util.List;

import com.algo.ds.stack.LinkedListStack;
import com.algo.ds.stack.Stack;
import com.algo.ds.tree.TreeNode;

public class TreeTraversalUtility {

	/**
	 * Preorder binary tree traversal is a classic interview problem about trees. The key to solve this problem is to
	 * understand the following:
	 * 
	 * What is preorder? (parent node is processed before its children) Use Stack from Java Core library The key is
	 * using a stack to store left and right children, and push right child first so that it is processed after the left
	 * child.<br/>
	 * order is P-> LC -> RC
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root == null)
			return list;

		// define a stack
		Stack<TreeNode> stack = new LinkedListStack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
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
	public List<Integer> inorderTraversal_1(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root == null)
			return list;

		Stack<TreeNode> stack = new LinkedListStack<>();
		TreeNode p = root;
		while (!stack.isEmpty() || p != null) {
			if (p != null) {
				stack.push(p);
				p = p.left;
			} else {
				TreeNode node = stack.pop();
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
	public List<Integer> inorderTraversal_2(TreeNode root) {
		if (root != null)
			helper(root);

		return list;
	}

	private void helper(TreeNode node) {
		if (node.left != null)
			helper(node.left);

		list.add(node.value);

		if (node.right != null)
			helper(node.right);
	}

	public List<Integer> inorderTraversal_3(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new LinkedListStack<>();

		TreeNode p = root;
		// go to end of left edge
		while (p != null) {
			stack.push(p);
			p = p.left;
		}
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
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

	public void postOrderTraversal_recursive(TreeNode root) {
		if (root != null) {
			postOrderTraversal_recursive(root.left);
			postOrderTraversal_recursive(root.right);
			System.out.println(root.value);
		}
	}

	public List<Integer> postOrderTraversal_iterative(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new LinkedListStack<>();

		if (root == null)
			return list;

		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode temp = stack.top(); // we will just peek at the top rather than popping it tp determine if it's
											// left child and right child are empty. if so then add the value to list.
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
}
