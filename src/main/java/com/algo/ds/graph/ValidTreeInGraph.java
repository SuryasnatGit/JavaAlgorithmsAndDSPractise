package com.algo.ds.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function
 * to check whether these edges make up a valid tree.
 * 
 * https://leetcode.com/problems/graph-valid-tree/.
 * 
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.<br/>
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * 
 * This problem can be converted to finding a cycle in a graph. It can be solved by using DFS (Recursion) or BFS
 * (Queue).
 * 
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same
 * as [1, 0]and thus will not appear together in edges.
 * 
 * Thought process:
 * 
 * A tree is a graph that doesn't have a cycle.
 * 
 * 
 * BFS. When a node is polled from queue, iterate through its neighbors. If any of them is visited but not the node's
 * parent, there is a cycle.
 * 
 * 1. If there are no edges, then the graph is a tree only if it has only one node.
 * 
 * 2. Build graph. Use a map of int -> list of int. Iterate through the edge list and add nodes into map.
 * 
 * 3. BFS. Poll a node from queue. Iterate through its neighbors. If queue contains a neighbor, that means there is a
 * cycle in the graph. Return false. Otherwise, if the neighbor is not visited, offer it to queue.
 * 
 */
public class ValidTreeInGraph {

	public static void main(String[] args) {
		ValidTreeInGraph valid = new ValidTreeInGraph();

		System.out.println(valid.validTree_BFS(5, new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 } }));
		System.out.println(valid.validTree_BFS(5, new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 1, 3 }, { 1, 4 } }));
		System.out.println(valid.validTree_DFS(5, new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 } }));
		System.out.println(valid.validTree_DFS(5, new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 1, 3 }, { 1, 4 } }));
	}

	/**
	 * Building graph takes O(E). BFS takes O(V + VE) = O(VE) because queue.contains() is not constant time. So the
	 * overall time complexity is O(VE).
	 * 
	 * @param n
	 * @param edges
	 * @return
	 */
	public boolean validTree_BFS(int n, int[][] edges) {
		// no edges means graph is a tree with one node
		if (n <= 1)
			return true;

		Map<Integer, List<Integer>> graph = new HashMap<>();
		boolean[] isVisited = new boolean[n];

		// build graph
		for (int[] edge : edges) {
			List<Integer> neighbours0 = graph.getOrDefault(edge[0], new ArrayList<>());
			List<Integer> neighbours1 = graph.getOrDefault(edge[1], new ArrayList<>());
			neighbours0.add(edge[1]);
			neighbours1.add(edge[0]);
			graph.put(edge[0], neighbours0);
			graph.put(edge[1], neighbours1);
		}

		// use queue
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(0);
		while (!queue.isEmpty()) {
			Integer top = queue.poll();

			if (isVisited[top]) {
				return false;
			}

			isVisited[top] = true;

			for (int neighbour : graph.get(top)) {
				if (queue.contains(neighbour)) {
					return false;
				}

				if (!isVisited[neighbour]) {
					queue.offer(neighbour);
				}
			}
		}

		for (boolean b : isVisited) {
			if (!b) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Time complexity: O(V + E).
	 */
	public boolean validTree_DFS(int n, int[][] edges) {
		if (n <= 1) {
			return true;
		}

		Map<Integer, List<Integer>> graph = new HashMap<>();

		for (int[] edge : edges) {
			List<Integer> neighbours0 = graph.getOrDefault(edge[0], new ArrayList<>());
			List<Integer> neighbours1 = graph.getOrDefault(edge[1], new ArrayList<>());
			neighbours0.add(edge[1]);
			neighbours1.add(edge[0]);
			graph.put(edge[0], neighbours0);
			graph.put(edge[1], neighbours1);
		}

		boolean[] visited = new boolean[n];

		boolean hasCycle = isCycle(0, graph, -1, visited);
		if (hasCycle) {
			return false;
		}

		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				return false;
			}
		}

		return true;
	}

	private boolean isCycle(int vertex, Map<Integer, List<Integer>> graph, int parent, boolean[] visited) {
		if (visited[vertex]) {
			return true;
		}

		visited[vertex] = true;

		if (graph.get(vertex) == null) {
			return false;
		}

		for (int neighbour : graph.get(vertex)) {
			if (neighbour == parent) {
				continue;
			}
			if (isCycle(neighbour, graph, vertex, visited)) {
				return true;
			}
		}

		return false;
	}

}
