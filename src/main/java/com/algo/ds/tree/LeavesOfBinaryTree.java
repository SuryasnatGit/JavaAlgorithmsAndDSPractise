package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algo.common.TreeNode;

/**
 *
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until
 * the tree is empty.
 *
 * Time complexity O(n^2) for unbalanced tree.
 * 
 * Example:
 * 
 * Input: [1,2,3,4,5]
 * 
 * 1 / \ 2 3 / \ 4 5
 * 
 * Output: [[4,5,3],[2],[1]]
 *
 * https://leetcode.com/problems/find-leaves-of-binary-tree/
 * 
 * Category : Medium
 */
public class LeavesOfBinaryTree {

	public List<List<Integer>> findLeaves(TreeNode root) {
		if (root == null) {
			return Collections.emptyList();
		}

		List<List<Integer>> result = new ArrayList<>();
		List<Integer> leaves = new ArrayList<>();

		while (stripLeaves(root, leaves) != null) {
			result.add(leaves);
			leaves = new ArrayList<>();
		}

		result.add(leaves);
		return result;
	}

	private TreeNode stripLeaves(TreeNode root, List<Integer> leaves) {
		if (root == null) {
			return null;
		}

		if (root.left == null && root.right == null) {
			leaves.add(root.data);
			return null;
		}

		root.left = stripLeaves(root.left, leaves);
		root.right = stripLeaves(root.right, leaves);

		return root;
	}

	/**
	 * It is graph, rather than tree One big difference is, tree only has 1 path between 2 nodes, while graph can have
	 * several paths, which forms a circle could be isolated, but in our case, it is a connected graph
	 * 
	 * 
	 */
	public List<List<Integer>> findLeavesInGraph(TreeNode graph) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		// Use a map to remember not only the height, but also track loop
		Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
		helper(res, map, graph);
		return res;
	}

	private int helper(List<List<Integer>> res, Map<TreeNode, Integer> map, TreeNode node) {
		if (node == null) {
			return 0;
		}

		if (map.containsKey(node)) {
			if (map.get(node) == -1) {
				throw new RuntimeException("There is loop, done");
			}
			return map.get(node);
		}

		map.put(node, -1); // Seen this node, but not finalized yet, it is still too high
		int height = Math.max(helper(res, map, node.left), helper(res, map, node.right)) + 1;
		if (height > res.size()) {
			res.add(new ArrayList<Integer>());
		}

		res.get(height - 1).add(node.data);

		map.put(node, height);
		return height;
	}

	public static void main(String[] args) {
		LeavesOfBinaryTree lbt = new LeavesOfBinaryTree();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		System.out.println(lbt.findLeaves(root));
	}
}
