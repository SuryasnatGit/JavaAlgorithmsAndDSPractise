package com.algo.ds.tree;

import java.util.Stack;

import com.algo.common.TreeNode;

/**
 * Youtube link - https://youtu.be/nzmtCFNae9k Youtube link - https://youtu.be/elQcrJrfObg Youtube link -
 * https://youtu.be/qT65HltK2uE Youtube link - https://youtu.be/ZM-sV9zQPEs
 * 
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
 * http://www.geeksforgeeks.org/iterative-preorder-traversal/
 */
public class TreeTraversals {

	// T - O(n)
	// S - O(n) worst O(log n) average
	public void inOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}

	public void preOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}

	public void postOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data + " ");
	}

	/**
	 * inorder traversal without recursion using iterative way using stack
	 * 
	 * @param root
	 */
	public void inorderIterative(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode node = root;

		while (!stack.isEmpty() || node != null) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				System.out.print(node.data + " ");
				node = node.right;
			}
		}
	}

	public void preOrderIterative(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		while (!stack.isEmpty()) {
			root = stack.pop();
			System.out.print(root.data + " ");
			if (root.right != null) {
				stack.push(root.right);
			}
			if (root.left != null) {
				stack.push(root.left);
			}
		}
	}

	public void postOrderIterativeTwoStack(TreeNode root) {
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		Stack<TreeNode> stack2 = new Stack<TreeNode>();
		stack1.push(root);
		while (!stack1.isEmpty()) {
			root = stack1.pop();
			if (root.left != null) {
				stack1.push(root.left);
			}
			if (root.right != null) {
				stack1.push(root.right);
			}
			stack2.push(root);
		}
		while (!stack2.isEmpty()) {
			System.out.print(stack2.pop().data + " ");
		}
	}

	public void postOrderIterativeOneStack(TreeNode root) {
		TreeNode current = root;
		Stack<TreeNode> stack = new Stack<>();

		while (!stack.isEmpty() || current != null) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else {
				TreeNode temp = stack.peek().right;
				if (temp == null) {
					temp = stack.pop();
					System.out.print(temp.data + " ");
					while (!stack.isEmpty() && temp == stack.peek().right) {
						temp = stack.pop();
						System.out.print(temp.data + " ");
					}
				} else {
					current = temp;
				}
			}
		}
	}

	public static void main(String args[]) {

		TreeNode head = new TreeNode(10);
		head.left = new TreeNode(15);
		head.right = new TreeNode(19);
		head.left.left = new TreeNode(17);
		head.left.right = new TreeNode(11);

		TreeTraversals tt = new TreeTraversals();
		tt.inorderIterative(head);
		System.out.println();
		tt.preOrderIterative(head);
		System.out.println();
		tt.postOrder(head);
		System.out.println();
		tt.postOrderIterativeTwoStack(head);
		System.out.println();
		tt.postOrderIterativeOneStack(head);
	}
}
