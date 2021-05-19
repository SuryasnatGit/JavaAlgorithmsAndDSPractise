
package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * http://www.geeksforgeeks.org/vertex-cover-problem-set-2-dynamic-programming-solution-tree/
 * http://en.wikipedia.org/wiki/Vertex_cover Using lis to store the cover data Test cases: null root Only left child
 * Only right child Tree with only one child at every node
 * 
 * TODO : result not correct
 */
public class VertexCoverBinaryTreeDP {

	public int cover(TreeNode root) {
		if (root == null) {
			return 0;
		}
		// no need to include leaf node ever
		if (root.left == null && root.right == null) {
			return 0;
		}
		// store result
		if (root.data != 0) {
			return root.data;
		}
		// if root is included
		int inclRoot = 1 + cover(root.left) + cover(root.right);

		// if root is not included
		int exclRoot = 0;
		if (root.left != null) {
			exclRoot += (1 + cover(root.left.left) + cover(root.left.right));
		}
		if (root.right != null) {
			exclRoot += (1 + cover(root.right.left) + cover(root.right.right));
		}
		root.data = Math.min(inclRoot, exclRoot);
		return root.data;
	}

	public static void main(String args[]) {

		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(20);
		root.right = new TreeNode(30);
		root.left.left = new TreeNode(40);
		root.left.right = new TreeNode(50);
		root.left.right.left = new TreeNode(70);
		root.left.right.right = new TreeNode(80);
		root.right.right = new TreeNode(60);

		VertexCoverBinaryTreeDP vc = new VertexCoverBinaryTreeDP();
		System.out.println(vc.cover(root));
	}
}
