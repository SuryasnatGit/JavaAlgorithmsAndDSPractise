package com.algo.ds.tree;

import java.util.Stack;

import com.algo.common.TreeNode;

/**
 * Given a binary tree, flatten it to a linked list in-place in preorder traversal. Usage of auxiliary data structure is
 * not allowed. After flattening, left of each node should point to NULL and right should contain next node in level
 * order.
 *
 * 
 * 
 * Time complexity O(n)
 *
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 */
public class FlattenLinkListToBinaryTreePreorder {

	/**
	 * Approach: A pre-order traversal of the binary tree using stack has been implied in this approach. In this
	 * traversal, every time a right child is pushed in the stack, the right child is made equal to the left child and
	 * left child is made equal to NULL. If the right child of the node becomes NULL, the stack is popped and the right
	 * child becomes the popped value from the stack. The above steps are repeated until the size of the stack is zero
	 * or root is NULL.
	 * 
	 * Time Complexity: O(N) Auxiliary Space: O(Log N)
	 * 
	 * 
	 * @param root
	 */
	public void flatten(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		TreeNode prev = null;
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
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

	public void flatten1(TreeNode root) {
		if (root == null)
			return;
		flatten1(root.left);
		flatten1(root.right);
		TreeNode left = root.left;
		TreeNode right = root.right;
		root.left = null;
		root.right = left;
		while (root.right != null)
			root = root.right;
		root.right = right;
	}

	public static void main(String[] args) {
		TreeTraversals ts = new TreeTraversals();
		FlattenLinkListToBinaryTreePreorder f = new FlattenLinkListToBinaryTreePreorder();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right = new TreeNode(5);
		root.right.right = new TreeNode(6);
		ts.inOrder(root);
		System.out.println();
		f.flatten1(root);

		ts.inOrder(root);
	}
}
