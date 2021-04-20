package com.algo.ds.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algo.common.TreeNode;

/**
 * http://www.geeksforgeeks.org/tree-isomorphism-problem/
 * 
 * Write a function to detect if two trees are isomorphic. Two trees are called isomorphic if one of them can be
 * obtained from other by a series of flips, i.e. by swapping left and right children of a number of nodes. Any number
 * of nodes at any level can have their children swapped. Two empty trees are isomorphic.
 * 
 * Test cases: Same tree. Exact mirror. Some nodes flipped.
 * 
 * Algo:
 * 
 * We simultaneously traverse both trees. Let the current internal nodes of two trees being traversed be n1 and n2
 * respectively. There are following two conditions for subtrees rooted with n1 and n2 to be isomorphic. 1) Data of n1
 * and n2 is same. 2) One of the following two is true for children of n1 and n2 a) Left child of n1 is isomorphic to
 * left child of n2 and right child of n1 is isomorphic to right child of n2. b) Left child of n1 is isomorphic to right
 * child of n2 and right child of n1 is isomorphic to left child of n2.
 * 
 * 
 */
public class TreeIsomorphism {

	/**
	 * Time Complexity: The above solution does a traversal of both trees. So time complexity is O(min(m,n)*2) or
	 * O(min(m,n)) where m and n are number of nodes in given trees.
	 * 
	 */
	public boolean areIsomorphicTrees(TreeNode root1, TreeNode root2) {
		// Both roots are NULL, trees isomorphic by definition
		if (root1 == null && root2 == null) {
			return true;
		}

		// Exactly one of the n1 and n2 is NULL, trees not isomorphic
		if (root1 == null || root2 == null) {
			return false;
		}

		return root1.data == root2.data && ((areIsomorphicTrees(root1.left, root2.left)
				&& areIsomorphicTrees(root1.right, root2.right))
				|| (areIsomorphicTrees(root1.left, root2.right) && areIsomorphicTrees(root1.right, root2.left)));

	}

	/**
	 * The idea is to find out the canonical form of both the trees and comparing them. The leaf node will return “()”
	 * to its subsequent upper levels
	 * 
	 * https://www.geeksforgeeks.org/isomorphism-in-n-ary-trees/?ref=rp
	 * 
	 * TODO : to check later and complete.
	 * 
	 */
	private Map<TreeNode, List<TreeNode>> nAryTree = new HashMap<TreeNode, List<TreeNode>>();

	public boolean areIsomorphicNAryTree(TreeNode root1, TreeNode root2) {
		return false;
	}

	public static void main(String args[]) {
		int in1[] = { 8, 5, 6, 10, 11, 9, 12 };
		int pre1[] = { 10, 5, 8, 6, 9, 11, 12 };
		int in2[] = { 11, 9, 12, 10, 6, 5, 15 };
		int pre2[] = { 10, 9, 11, 12, 5, 6, 15 };
		ConstructTreeFromInOrderPreOrder ct = new ConstructTreeFromInOrderPreOrder();
		TreeNode root1 = ct.createTree(in1, pre1);
		TreeNode root2 = ct.createTree(in2, pre2);
		TreeIsomorphism ti = new TreeIsomorphism();
		System.out.println(ti.areIsomorphicTrees(root1, root2));
	}
}
