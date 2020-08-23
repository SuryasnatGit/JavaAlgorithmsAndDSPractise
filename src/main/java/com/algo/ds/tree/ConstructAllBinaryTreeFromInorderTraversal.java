package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.algo.common.TreeNode;

/**
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n. Total number of binary
 * search tree possible is Catalan number.
 *
 * https://leetcode.com/problems/unique-binary-search-trees-ii/
 */
public class ConstructAllBinaryTreeFromInorderTraversal {

	public List<TreeNode> generateTrees(int n) {
		if (n == 0) {
			return Collections.emptyList();
		}
		return construct(1, n);
	}

	private List<TreeNode> construct(int start, int end) {
		if (start > end) {
			return Collections.singletonList(null);
		}
		List<TreeNode> allTrees = new ArrayList<>();
		for (int root = start; root <= end; root++) {
			// get all subtrees from left and right side.
			List<TreeNode> allLeftSubstrees = construct(start, root - 1);
			List<TreeNode> allRightSubstrees = construct(root + 1, end);
			// iterate through them in all combination and them connect them to root
			// and add to allTrees.
			for (TreeNode left : allLeftSubstrees) {
				for (TreeNode right : allRightSubstrees) {
					TreeNode node = new TreeNode(root);
					node.left = left;
					node.right = right;
					allTrees.add(node);
				}
			}
		}
		return allTrees;
	}

	public void printAllTrees(List<TreeNode> allTrees) {
		TreeTraversals tt = new TreeTraversals();
		System.out.println("Total number of trees " + allTrees.size());
		for (TreeNode node : allTrees) {
			tt.inOrder(node);
			System.out.println();
			tt.preOrder(node);
			System.out.print("\n\n");
		}
	}

	public static void main(String args[]) {
		ConstructAllBinaryTreeFromInorderTraversal ct = new ConstructAllBinaryTreeFromInorderTraversal();
		List<TreeNode> allTrees = ct.generateTrees(3);
		ct.printAllTrees(allTrees);
	}
}
