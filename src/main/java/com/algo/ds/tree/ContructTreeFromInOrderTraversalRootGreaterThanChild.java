package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * http://www.geeksforgeeks.org/construct-binary-tree-from-inorder-traversal/
 * 
 * Given inorder traversal of binary tree where every node is greater than its left and right child.
 * 
 * Input: inorder[] = {5, 10, 40, 30, 28} Output: root of following tree 40 / \ 10 30 / \ 5 28
 * 
 * Input: inorder[] = {1, 5, 10, 40, 30, 15, 28, 20} Output: root of following tree 40 / \ 10 30 / \ 5 28 / / \ 1 15 20
 * The idea used in Construction of Tree from given Inorder and Preorder traversals can be used here. Let the given
 * array is {1, 5, 10, 40, 30, 15, 28, 20}. The maximum element in given array must be root. The elements on left side
 * of the maximum element are in left subtree and elements on right side are in right subtree.
 * 
 * 40 / \ {1,5,10} {30,15,28,20} We recursively follow above step for left and right subtrees, and finally get the
 * following tree.
 * 
 * 40 / \ 10 30 / \ 5 28 / / \ 1 15 20
 * 
 * Time Complexity: O(n^2)
 * 
 * 
 * 
 * Test cases: One two or more nodes in the tree
 */
public class ContructTreeFromInOrderTraversalRootGreaterThanChild {

	public TreeNode createTree(int inorder[]) {
		return createTree(inorder, 0, inorder.length - 1);
	}

	private TreeNode createTree(int inorder[], int low, int high) {
		if (low > high) {
			return null;
		}
		int maxIndex = low;
		for (int i = low; i <= high; i++) {
			if (inorder[maxIndex] > inorder[i]) {
				maxIndex = i;
			}
		}
		TreeNode root = new TreeNode(inorder[maxIndex]);
		root.left = createTree(inorder, low, maxIndex - 1);
		root.right = createTree(inorder, maxIndex + 1, high);
		return root;
	}

	public static void main(String args[]) {
		int inorder[] = { 9, 15, 25, 6, 18, -1, 3, -3 };
		ContructTreeFromInOrderTraversalRootGreaterThanChild tf = new ContructTreeFromInOrderTraversalRootGreaterThanChild();
		TreeNode root = tf.createTree(inorder);
		TreeTraversals tt = new TreeTraversals();
		tt.inOrder(root);
	}
}
