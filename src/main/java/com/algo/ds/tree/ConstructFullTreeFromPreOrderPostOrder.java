package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * http://www.geeksforgeeks.org/full-and-complete-binary-tree-from-given-preorder-and-postorder-traversals/
 * 
 * 
 * Full tree is a tree with all nodes with either 0 or 2 child. Never has 1 child. Test cases Empty tree Tree with big
 * on left side Tree with big on right side
 */
public class ConstructFullTreeFromPreOrderPostOrder {

	// variable to hold index in pre[] array
	private int preindex;

	// A recursive function to construct Full
	// from pre[] and post[]. preIndex is used to keep track of index in pre[]. l is
	// low index and h is high index for the
	// current subarray in post[]
	public TreeNode constructTreeUtil(int pre[], int post[], int l, int h, int size) {

		// Base case
		if (preindex >= size || l > h)
			return null;

		// The first node in preorder traversal is
		// root. So take the node at preIndex from
		// preorder and make it root, and increment
		// preIndex
		TreeNode root = new TreeNode(pre[preindex]);
		preindex++;

		// If the current subarry has only one
		// element, no need to recur or
		// preIndex > size after incrementing
		if (l == h || preindex >= size)
			return root;
		int i;

		// Search the next element of pre[] in post[]
		for (i = l; i <= h; i++) {
			if (post[i] == pre[preindex])
				break;
		}
		// Use the index of element found in
		// postorder to divide postorder array
		// in two parts. Left subtree and right subtree
		if (i <= h) {
			root.left = constructTreeUtil(pre, post, l, i, size);
			root.right = constructTreeUtil(pre, post, i + 1, h, size);
		}
		return root;
	}

	// The main function to construct Full
	// Binary Tree from given preorder and
	// postorder traversals. This function
	// mainly uses constructTreeUtil()
	public TreeNode constructTree(int pre[], int post[], int size) {
		preindex = 0;
		return constructTreeUtil(pre, post, 0, size - 1, size);
	}

	public static void main(String args[]) {
		ConstructFullTreeFromPreOrderPostOrder cft = new ConstructFullTreeFromPreOrderPostOrder();
		int preorder[] = { 1, 2, 3, 6, 7, 8, 9 };
		int postorder[] = { 2, 6, 8, 9, 7, 3, 1 };
		TreeNode root = cft.constructTree(preorder, postorder, preorder.length);
		TreeTraversals tt = new TreeTraversals();
		tt.inOrder(root);
		System.out.println();
		tt.preOrder(root);
		System.out.println();
		tt.postOrder(root);
	}
}
