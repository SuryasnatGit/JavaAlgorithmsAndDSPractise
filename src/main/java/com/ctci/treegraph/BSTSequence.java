package com.ctci.treegraph;

import java.util.ArrayList;
import java.util.List;

import com.algo.common.TreeNode;

/**
 * CTCI - 4.9
 * 
 * BST Sequences: A binary search tree was created by traversing through an array from left to right and inserting each
 * element. Given a binary search tree with distinct elements, print all possible arrays that could have led to this
 * tree.
 * 
 * @author surya
 *
 */
public class BSTSequence {

	private List<List<TreeNode>> allSeq;

	public List<List<TreeNode>> getBstSequence(TreeNode root) {
		List<TreeNode> visited = new ArrayList<>();
		List<TreeNode> possible = new ArrayList<>();
		bstHelper(root, visited, possible);
		return allSeq;
	}

	private void bstHelper(TreeNode root, List<TreeNode> visited, List<TreeNode> possible) {
		visited.add(root);

		if (root.left != null)
			possible.add(root.left);
		if (root.right != null)
			possible.add(root.right);
		if (possible.isEmpty())
			allSeq.add(visited);

		for (TreeNode possibleNode : possible) {
			// clone visited and possible
			possible.remove(possibleNode);

			bstHelper(root, visited, possible);
		}
	}
}
