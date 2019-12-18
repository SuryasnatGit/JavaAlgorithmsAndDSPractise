package com.algo.ds.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * http://www.geeksforgeeks.org/bipartite-graph/
 * 
 * A Bipartite Graph is a graph whose vertices can be divided into two independent sets, U and V
 * such that every edge (u, v) either connects a vertex from U to V or a vertex from V to U. In
 * other words, for every edge (u, v), either u belongs to U and v to V, or u belongs to V and v to
 * U. We can also say that there is no edge that connects vertices of same set.
 * 
 * A bipartite graph is possible if the graph coloring is possible using two colors such that
 * vertices in a set are colored with the same color. Note that it is possible to color a cycle
 * graph with even cycle using two colors
 * 
 * It is not possible to color a cycle graph with odd cycle using two colors.
 * 
 * Includes both DFS and BFS method.
 * 
 * Algorithm to check if a graph is Bipartite:<br/>
 * One approach is to check whether the graph is 2-colorable or not using backtracking algorithm m
 * coloring problem.<br/>
 * 
 */
public class BiparteGraph {

	/**
	 * Following is a simple algorithm to find out whether a given graph is Birpartite or not using
	 * Breadth First Search (BFS).<br/>
	 * 
	 * 1. Assign RED color to the source vertex (putting into set U).
	 * 
	 * 2. Color all the neighbors with BLUE color (putting into set V).
	 * 
	 * 3. Color all neighbors neighbor with RED color (putting into set U).
	 * 
	 * 4. This way, assign color to all vertices such that it satisfies all the constraints of m way
	 * coloring problem where m = 2.
	 * 
	 * 5. While assigning colors, if we find a neighbor which is colored with same color as current
	 * vertex, then the graph cannot be colored with 2 vertices (or graph is not Bipartite)
	 * 
	 * @param graph
	 * @return
	 */
	public boolean isBiparteBFS(Graph<Integer> graph) {

		Set<Vertex<Integer>> redSet = new HashSet<Vertex<Integer>>();
		Set<Vertex<Integer>> blueSet = new HashSet<Vertex<Integer>>();

		Queue<Vertex<Integer>> queue = new LinkedList<Vertex<Integer>>();

		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			if (!redSet.contains(vertex) && !blueSet.contains(vertex)) {
				queue.add(vertex);
				redSet.add(vertex);
				while (!queue.isEmpty()) {
					vertex = queue.poll();
					for (Vertex<Integer> v : vertex.getAdjacentVertexes()) {
						if (redSet.contains(vertex)) {
							if (redSet.contains(v)) {
								return false;
							}
							if (blueSet.contains(v)) {
								continue;
							}
							blueSet.add(v);
						} else if (blueSet.contains(vertex)) {
							if (blueSet.contains(v)) {
								return false;
							}
							if (redSet.contains(v)) {
								continue;
							}
							redSet.add(v);
						}
						queue.add(v);
					}
				}
			}
		}
		System.out.println(redSet);
		System.out.println(blueSet);
		return true;
	}

	public boolean isBiparteDFS(Graph<Integer> graph) {
		Set<Vertex<Integer>> redSet = new HashSet<Vertex<Integer>>();
		Set<Vertex<Integer>> blueSet = new HashSet<Vertex<Integer>>();
		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			if (redSet.contains(vertex) || blueSet.contains(vertex)) {
				continue;
			}
			boolean flag = isBiparteDFS(vertex, redSet, blueSet, true);
			if (!flag) {
				return false;
			}
		}
		return true;
	}

	private boolean isBiparteDFS(Vertex<Integer> vertex, Set<Vertex<Integer>> redSet, Set<Vertex<Integer>> blueSet,
			boolean wasRed) {

		if (wasRed) {
			if (redSet.contains(vertex)) {
				return false;
			} else if (blueSet.contains(vertex)) {
				return true;
			}
			blueSet.add(vertex);
		}

		else if (!wasRed) {
			if (blueSet.contains(vertex)) {
				return false;
			}
			if (redSet.contains(vertex)) {
				return true;
			}
			redSet.add(vertex);
		}

		for (Vertex<Integer> adj : vertex.getAdjacentVertexes()) {
			boolean flag = isBiparteDFS(adj, redSet, blueSet, !wasRed);
			if (!flag) {
				return false;
			}
		}
		return true;

	}

	public static void main(String argsp[]) {
		Graph<Integer> graph = new Graph<Integer>(false);
		graph.addEdge(1, 2);
		graph.addEdge(2, 5);
		graph.addEdge(1, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 6);
		graph.addEdge(5, 6);
		graph.addEdge(7, 9);
		graph.addEdge(7, 8);
		BiparteGraph bi = new BiparteGraph();
		boolean result = bi.isBiparteDFS(graph);
		System.out.print(result);
	}
}
