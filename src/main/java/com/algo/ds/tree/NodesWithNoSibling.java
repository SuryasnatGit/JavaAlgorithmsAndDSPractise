package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * http://www.geeksforgeeks.org/print-nodes-dont-sibling-binary-tree/
 * 
 * Given a Binary Tree, print all nodes that dont have a sibling (a sibling is a node that has same parent. In a Binary
 * Tree, there can be at most one sibling). Root should not be printed as root cannot have a sibling.
 * 
 * 
 * This does not print root node even though it has no sibling
 * 
 * Test cases: Null tree. Only one node tree. All left side tree. All right side tree. Regular mix tree.
 */
public class NodesWithNoSibling {

	public void printNodes(TreeNode root) {
		if (root == null) {
			return;
		}
		if (root.left == null || root.right == null) {
			if (root.left != null) {
				System.out.print(root.left.data + " ");
			}
			if (root.right != null) {
				System.out.print(root.right.data + " ");
			}
		}
		printNodes(root.left);
		printNodes(root.right);
	}

	public static void main(String args[]) {
		BinaryTree bt = new BinaryTree();
		TreeNode root = null;
		bt.insert(10);
		bt.insert(5);
		bt.insert(-1);
		bt.insert(-5);
		bt.insert(20);
		bt.insert(25);
		NodesWithNoSibling nws = new NodesWithNoSibling();
		nws.printNodes(root);
	}
}
