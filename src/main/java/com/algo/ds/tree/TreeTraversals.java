package com.algo.ds.tree;

import java.util.Stack;

/**
 * Youtube link - https://youtu.be/nzmtCFNae9k Youtube link - https://youtu.be/elQcrJrfObg Youtube link -
 * https://youtu.be/qT65HltK2uE Youtube link - https://youtu.be/ZM-sV9zQPEs
 * 
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
 * http://www.geeksforgeeks.org/iterative-preorder-traversal/
 */
public class TreeTraversals {

	public void inOrder(Node root) {
		if (root == null) {
			return;
		}
		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}

	public void preOrder(Node root) {
		if (root == null) {
			return;
		}
		System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}

	public void postOrder(Node root) {
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
	public void inorderIterative(Node root) {
		Stack<Node> stack = new Stack<Node>();
		Node node = root;
		while (true) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				if (stack.isEmpty()) {
					break;
				}
				node = stack.pop();
				System.out.println(node.data);
				node = node.right;
			}
		}
	}

	public void preOrderIterative(Node root) {
		Stack<Node> stack = new Stack<Node>();
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

	public void postOrderIterativeTwoStack(Node root) {
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
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

	public void postOrderIterativeOneStack(Node root) {
		Node current = root;
		Stack<Node> stack = new Stack<>();
		while (current != null || !stack.isEmpty()) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else {
				Node temp = stack.peek().right;
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
		BinaryTree bt = new BinaryTree();
		Node head = null;
		head = bt.addNode(10, head);
		head = bt.addNode(15, head);
		head = bt.addNode(19, head);
		head = bt.addNode(17, head);
		head = bt.addNode(11, head);

		head = bt.addNode(-11, head);

		TreeTraversals tt = new TreeTraversals();
		tt.postOrder(head);
		System.out.println();
		tt.postOrderIterativeTwoStack(head);
		System.out.println();
		tt.postOrderIterativeOneStack(head);
	}
}
