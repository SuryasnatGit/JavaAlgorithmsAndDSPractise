package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * Morris inorder/preorder traversals.
 * 
 * Using Morris Traversal, we can traverse the tree without using stack and recursion. The idea of Morris Traversal is
 * based on Threaded Binary Tree. In this traversal, we first create links to Inorder successor and print the data using
 * these links, and finally revert the changes to restore original tree.
 *
 * Although the tree is modified through the traversal, it is reverted back to its original shape after the completion.
 * Unlike Stack based traversal, no extra space is required for this traversal.
 * 
 * Time complexity O(n) Space complexity O(1)
 * 
 */
public class MorrisTraversal {

	public void inorder(TreeNode root) {
		TreeNode current = root;
		while (current != null) {
			// left is null then print the node and go to right
			if (current.left == null) {
				System.out.print(current.data + " ");
				current = current.right;
			} else {
				// find the predecessor.
				TreeNode predecessor = current.left;
				// To find predecessor keep going right till right node is not null or right node is not current.
				while (predecessor.right != current && predecessor.right != null) {
					predecessor = predecessor.right;
				}
				// if right node is null then go left after establishing link from predecessor to current.
				if (predecessor.right == null) {
					predecessor.right = current;
					current = current.left;
				} else { // left is already visit. Go rigth after visiting current.
					predecessor.right = null;
					System.out.print(current.data + " ");
					current = current.right;
				}
			}
		}
	}

	public void preorder(TreeNode root) {
		TreeNode current = root;
		while (current != null) {
			if (current.left == null) {
				System.out.print(current.data + " ");
				current = current.right;
			} else {
				TreeNode predecessor = current.left;
				while (predecessor.right != current && predecessor.right != null) {
					predecessor = predecessor.right;
				}
				if (predecessor.right == null) {
					predecessor.right = current;
					System.out.print(current.data + " ");
					current = current.left;
				} else {
					predecessor.right = null;
					current = current.right;
				}
			}
		}
	}

	public static void main(String args[]) {
//		BinaryTree bt = new BinaryTree();
//		TreeNode root = null;
//		root = bt.addNode(10, root);
//		root = bt.addNode(50, root);
//		root = bt.addNode(-10, root);
//		root = bt.addNode(7, root);
//		root = bt.addNode(9, root);
//		root = bt.addNode(-20, root);
//		root = bt.addNode(30, root);
//		MorrisTraversal mt = new MorrisTraversal();
//		mt.inorder(root);
//		System.out.println();
//		mt.preorder(root);
	}
}
