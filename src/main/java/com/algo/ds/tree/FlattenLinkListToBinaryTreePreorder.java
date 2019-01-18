package com.algo.ds.tree;

import java.util.Stack;

/**
 * Given a binary tree, flatten it to a linked list in-place in preorder traversal. Usage of
 * auxiliary data structure is not allowed. After flattening, left of each node should point to NULL
 * and right should contain next node in level order.
 *
 * 
 * 
 * Time complexity O(n)
 *
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class FlattenLinkListToBinaryTreePreorder {

	/**
	 * Approach: A pre-order traversal of the binary tree using stack has been implied in this approach.
	 * In this traversal, every time a right child is pushed in the stack, the right child is made equal
	 * to the left child and left child is made equal to NULL. If the right child of the node becomes
	 * NULL, the stack is popped and the right child becomes the popped value from the stack. The above
	 * steps are repeated until the size of the stack is zero or root is NULL.
	 * 
	 * Time Complexity: O(N) Auxiliary Space: O(Log N)
	 * 
	 * 
	 * @param root
	 */
	public void flatten(Node root) {
		if (root == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		Node prev = null;
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			if (prev != null) {
				prev.right = node;
			}
			prev = node;
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
			node.left = null;
			node.right = null;
		}
	}

	public void flatten1(Node root) {
		if (root == null)
			return;
		flatten1(root.left);
		flatten1(root.right);
		Node left = root.left;
		Node right = root.right;
		root.left = null;
		root.right = left;
		while (root.right != null)
			root = root.right;
		root.right = right;
	}

	public static void main(String[] args) {
		TreeTraversals ts = new TreeTraversals();
		FlattenLinkListToBinaryTreePreorder f = new FlattenLinkListToBinaryTreePreorder();
		Node root = Node.newNode(1);
		root.left = Node.newNode(2);
		root.left.left = Node.newNode(3);
		root.left.right = Node.newNode(4);
		root.right = Node.newNode(5);
		root.right.right = Node.newNode(6);
		ts.inOrder(root);
		System.out.println();
		f.flatten1(root);

		ts.inOrder(root);
	}
}
