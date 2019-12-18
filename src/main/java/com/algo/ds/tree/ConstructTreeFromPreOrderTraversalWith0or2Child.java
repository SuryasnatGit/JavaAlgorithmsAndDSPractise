package com.algo.ds.tree;

/**
 * http://www.geeksforgeeks.org/construct-a-special-tree-from-given-preorder-traversal/
 * 
 * Given an array pre[] that represents Preorder traversal of a spacial binary tree where every
 * node has either 0 or 2 children. One more array preLN[] is given which has only two possible
 * values L and N. The value L in preLN[] indicates that the corresponding node in Binary
 * Tree is a leaf node and value N indicates that the corresponding node is non-leaf node. Write a
 * function to construct the tree from the given two arrays.
 * 
 * Source: Amazon Interview Question
 * 
 * Example:
 * 
 * Input: pre[] = {10, 30, 20, 5, 15}, preLN[] = {'N', 'N', 'L', 'L', 'L'} 
 * 
 * Output: Root of following tree
          10
         /  \
        30   15
       /  \
      20   5

time - O(n)

 * 
 * Test cases:
 * 
 * 1. Length of pre and val is not same.
 * 
 * 2. val contains values other than N or L
 */
class PreIndex {
	int index;
}

public class ConstructTreeFromPreOrderTraversalWith0or2Child {

	public Node createTree(int pre[], char val[]) {
		PreIndex pi = new PreIndex();
		pi.index = 0;
		return createTree(pre, val, pi);
	}

	private Node createTree(int pre[], char val[], PreIndex ind) {
		if (ind.index >= pre.length) {
			return null;
		}
		Node root = Node.newNode(pre[ind.index]);

		if (val[ind.index] == 'L') {
			ind.index++;
		} else {
			ind.index++;
			root.left = createTree(pre, val, ind);
			root.right = createTree(pre, val, ind);
		}
		return root;
	}

	public static void main(String args[]) {
		int pre[] = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
		char val[] = { 'N', 'N', 'N', 'L', 'L', 'L', 'N', 'L', 'L' };
		ConstructTreeFromPreOrderTraversalWith0or2Child tfp = new ConstructTreeFromPreOrderTraversalWith0or2Child();
		Node root = tfp.createTree(pre, val);
		TreeTraversals tt = new TreeTraversals();
		tt.preOrder(root);
		System.out.println();
		tt.inOrder(root);
	}
}
