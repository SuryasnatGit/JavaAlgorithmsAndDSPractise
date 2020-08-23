package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * 
 * Given an array representation of binary tree where index is data while value at index is parent create the binary
 * tree. Value of -1 indicates root node.
 * 
 * References: http://www.geeksforgeeks.org/construct-a-binary-tree-from-parent-array-representation/.
 * 
 * Given an array that represents a tree in such a way that array indexes are values in tree nodes and array values give
 * the parent node of that particular index (or node). The value of the root node index would always be -1 as there is
 * no parent for root. Construct the standard linked representation of given Binary Tree from this given representation.
 * 
 * Input: parent[] = {1, 5, 5, 2, 2, -1, 3} Output: root of below tree 5 / \ 1 2 / / \ 0 3 4 / 6 Explanation: Index of
 * -1 is 5. So 5 is root. 5 is present at indexes 1 and 2. So 1 and 2 are children of 5. 1 is present at index 0, so 0
 * is child of 1. 2 is present at indexes 3 and 4. So 3 and 4 are children of 2. 3 is present at index 6, so 6 is child
 * of 3.
 * 
 * 
 * Input: parent[] = {-1, 0, 0, 1, 1, 3, 5}; Output: root of below tree 0 / \ 1 2 / \ 3 4 / 5 / 6
 * 
 * Complexity - O(n). Space complexity - O(n)
 */
public class BinaryTreeFromParentRepresentation {

	public TreeNode create(int input[]) {
		TreeNode t[] = new TreeNode[input.length];

		for (int i = 0; i < input.length; i++) {
			t[i] = new TreeNode();
			t[i].data = i;
		}

		TreeNode root = null;
		for (int i = 0; i < input.length; i++) {
			int parentIndex = input[i];
			if (parentIndex == -1) {
				root = t[i];
				continue;
			}
			TreeNode parent = t[parentIndex];
			if (parent.left == null) {
				parent.left = t[i];
			} else {
				parent.right = t[i];
			}
		}
		return root;
	}

	public static void main(String args[]) {
		BinaryTreeFromParentRepresentation btpp = new BinaryTreeFromParentRepresentation();
		int input[] = { 1, 5, 5, 2, 2, -1, 3 };
		TreeNode root = btpp.create(input);
		TreeTraversals tt = new TreeTraversals();
		tt.inOrder(root);
		System.out.println();
		tt.preOrder(root);
	}
}
