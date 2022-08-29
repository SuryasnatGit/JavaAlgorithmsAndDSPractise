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

	class Node {
		int label;
		List<Node> neighbors;

		Node(int x) {
			label = x;
			neighbors = new ArrayList<Node>();
		}
	};

	public Node cloneGraph(Node node) {
		if (node == null) {
			return null;
		}
		Node clone = new Node(node.label);
		Set<Integer> visited = new HashSet<>();
		Map<Integer, Node> map = new HashMap<>();
		map.put(clone.label, clone);
		dfs(node, clone, map, visited);
		return clone;
	}

	private void dfs(Node current, Node clone, Map<Integer, Node> map,
			Set<Integer> visited) {
		if (visited.contains(current.label)) {
			return;
		}
		visited.add(current.label);
		for (Node adj : current.neighbors) {

			if (adj.label != current.label) {
				Node adjClone = map.get(adj.label);
				if (adjClone == null) {
					adjClone = new Node(adj.label);
					map.put(adjClone.label, adjClone);
				}
				clone.neighbors.add(adjClone);
				dfs(adj, adjClone, map, visited);
			} else {
				clone.neighbors.add(clone);
			}
		}
	}

	public Node cloneGraphBfs(Node node) {
		// base case
		if (node == null)
			return null;

		// have a queue for bfs
		Queue<Node> queue = new LinkedList<>();
		// have a map to contain cloned nodes
		Map<Node, Node> map = new HashMap<>();

		queue.add(node);
		map.put(node, new Node(node.label));

		while (!queue.isEmpty()) {
			Node polledNode = queue.poll();
			// handle the neighbours
			for (Node neighbour : polledNode.neighbors) {
				if (!map.containsKey(neighbour)) {
					// clone neighbour
					map.put(neighbour, new Node(neighbour.label));
					queue.add(neighbour);
				}
				map.get(polledNode).neighbors.add(map.get(neighbour));
			}
		}
		return map.get(node);
	}
}
