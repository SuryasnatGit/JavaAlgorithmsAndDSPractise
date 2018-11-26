package com.algo.ds.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Given a graph representing a tree. Find all minimum height trees.
 * 
 * For an undirected graph with tree characteristics, we can choose any node as the root. The result
 * graph is then a rooted tree. Among all possible rooted trees, those with minimum height are
 * called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and
 * return a list of their root labels.
 * 
 * Format: The graph contains n nodes which are labeled from 0 to n - 1. You will be given the
 * number n and a list of undirected edges (each edge is a pair of labels).
 * 
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,
 * 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 * Time complexity O(n). Space complexity O(n)
 *
 * https://leetcode.com/problems/minimum-height-trees/
 */
public class MinimumHeightTree {
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		if (n == 1) {
			return Collections.singletonList(0);
		}
		List<Set<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adj.add(new HashSet<Integer>());
		}
		for (int[] edge : edges) {
			adj.get(edge[0]).add(edge[1]);
			adj.get(edge[1]).add(edge[0]);
		}

		List<Integer> leaves = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (adj.get(i).size() == 1) {
				leaves.add(i);
			}
		}

		while (n > 2) {
			n -= leaves.size();
			List<Integer> newLeaves = new ArrayList<>();
			for (int leaf : leaves) {
				int node = adj.get(leaf).iterator().next();
				adj.get(node).remove(leaf);
				if (adj.get(node).size() == 1) {
					newLeaves.add(node);
				}
			}
			leaves = newLeaves;
		}

		return leaves;
	}

	public static void main(String args[]) {
		MinimumHeightTree mht = new MinimumHeightTree();
		int input[][] = { { 1, 0 }, { 1, 2 }, { 1, 3 } };
		List<Integer> result = mht.findMinHeightTrees(4, input);
		result.forEach(r -> System.out.print(r + " "));
	}
}
