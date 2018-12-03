package com.algo.ds.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 
 * https://leetcode.com/problems/clone-graph/
 */
public class CloneGraph {

	class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;

		UndirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>();
		}
	};

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) {
			return null;
		}
		UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
		Set<Integer> visited = new HashSet<>();
		Map<Integer, UndirectedGraphNode> map = new HashMap<>();
		map.put(clone.label, clone);
		dfs(node, clone, map, visited);
		return clone;
	}

	private void dfs(UndirectedGraphNode current, UndirectedGraphNode clone, Map<Integer, UndirectedGraphNode> map,
			Set<Integer> visited) {
		if (visited.contains(current.label)) {
			return;
		}
		visited.add(current.label);
		for (UndirectedGraphNode adj : current.neighbors) {

			if (adj.label != current.label) {
				UndirectedGraphNode adjClone = map.get(adj.label);
				if (adjClone == null) {
					adjClone = new UndirectedGraphNode(adj.label);
					map.put(adjClone.label, adjClone);
				}
				clone.neighbors.add(adjClone);
				dfs(adj, adjClone, map, visited);
			} else {
				clone.neighbors.add(clone);
			}
		}
	}

	public UndirectedGraphNode cloneGraphBfs(UndirectedGraphNode node) {
		// base case
		if (node == null)
			return null;

		// have a queue for bfs
		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		// have a map to contain cloned nodes
		Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

		queue.add(node);
		map.put(node, new UndirectedGraphNode(node.label));

		while (!queue.isEmpty()) {
			UndirectedGraphNode polledNode = queue.poll();
			// handle the neighbours
			for (UndirectedGraphNode neighbour : polledNode.neighbors) {
				if (!map.containsKey(neighbour)) {
					// clone neighbour
					map.put(neighbour, new UndirectedGraphNode(neighbour.label));
					queue.add(neighbour);
				}
				map.get(polledNode).neighbors.add(map.get(neighbour));
			}
		}
		return map.get(node);
	}
}
