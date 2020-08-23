package com.algo.ds.tree;

import java.util.Stack;

import com.algo.common.TreeNode;

/**
 * http://www.glassdoor.com/Interview/Create-an-iterator-to-traverse-a-binary-tree-When-the-next-function-is-called-on-the-binary-tree-return-the-value-at-the-QTN_674695.htm
 * 
 * Create an iterator to traverse a binary tree. When the next function is called on the binary tree return the value at
 * the next node as if you are doing an inorder traversal of the tree. Restrictions: Nodes do not have pointers to their
 * parent node and you can't use recursion.
 * 
 * 
 */
public class NextInorderSuccessorIterator {

	private TreeNode root = null;
	Stack<TreeNode> stack = new Stack<TreeNode>();

	NextInorderSuccessorIterator(TreeNode root) {
		this.root = root;
	}

	public int next() {
		TreeNode node = null;
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
		root = stack.pop();
		node = root;
		root = root.right;
		return node.data;
	}

	public boolean hasNext() {
		if (root != null || stack.size() > 0) {
			return true;
		}
		return false;
	}

	public static void main(String args[]) {
		BinaryTree bt = new BinaryTree();
		TreeNode node = null;
		node = bt.addNode(10, node);
		node = bt.addNode(-5, node);
		node = bt.addNode(7, node);
		node = bt.addNode(20, node);
		node = bt.addNode(3, node);
		node = bt.addNode(14, node);
		NextInorderSuccessorIterator nis = new NextInorderSuccessorIterator(node);
		while (nis.hasNext()) {
			System.out.println(nis.next());
		}
	}
}
