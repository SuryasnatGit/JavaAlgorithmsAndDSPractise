package com.algo.ds.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.careercup.com/question?id=5988741646647296
 * 
 * Example: 0 1 2 1 0 2 4 1
 * 
 * Answer: 1
 * 
 * Given an undirected graph, find number of triangles in this graph.
 * 
 * Find cycle of length 3. Pass parent in DFS search. If there is a cycle check if my parent is
 * neighbor of the the node which caused it to be a cycle.
 */
public class NumberofTrianglesInGraph {

	// number of vertices in graph
	private int V = 4;

	/**
	 * The idea is to use three nested loops to consider every triplet (i, j, k) and check for the above
	 * condition (there is an edge from i to j, j to k and k to i) However in an undirected graph, the
	 * triplet (i, j, k) can be permuted to give six combination (See previous post for details). Hence
	 * we divide the total count by 6 to get the actual number of triangles. In case of directed graph,
	 * the number of permutation would be 3 (as order of nodes becomes relevant). Hence in this case the
	 * total number of triangles will be obtained by dividing total count by 3. For example consider the
	 * directed graph given below.
	 * 
	 * This method uses adjacency matrix way.
	 * 
	 * Advantages:
	 * 
	 * No need to calculate Trace. Matrix- multiplication is not required. Auxiliary matrices are not
	 * required hence optimized in space. Works for directed graphs.
	 * 
	 * Disadvantages:
	 * 
	 * The time complexity is O(n3) and cant be reduced any further.
	 * 
	 * @return
	 */
	public int countTrianglesSimple(int[][] adj, boolean isDirected) {
		int count = 0;
		// consider every possible triplets of edges in graph
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				for (int k = 0; k < V; k++) {
					if (adj[i][j] == 1 && adj[j][k] == 1 && adj[k][i] == 1)
						count++;
				}
			}
		}

		if (isDirected)
			count /= 3;
		else
			count /= 6;

		return count;
	}

	public int countTriangles(Graph<Integer> graph) {
		Map<Vertex<Integer>, Boolean> visited = new HashMap<Vertex<Integer>, Boolean>();
		int count = 0;
		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			count += DFS(vertex, visited, null);
		}
		return count;
	}

	private int DFS(Vertex<Integer> vertex, Map<Vertex<Integer>, Boolean> visited, Vertex<Integer> parent) {

		if (visited.containsKey(vertex)) {
			return 0;
		}
		visited.put(vertex, true);
		int count = 0;
		for (Vertex<Integer> child : vertex.getAdjacentVertexes()) {
			if (child.equals(parent)) {
				continue;
			}
			if (visited.containsKey(child)) {
				count += isNeighbor(child, parent) ? 1 : 0;
			} else {
				count += DFS(child, visited, vertex);
			}
		}
		return count;
	}

	private boolean isNeighbor(Vertex<Integer> vertex, Vertex<Integer> destVertex) {
		for (Vertex<Integer> child : vertex.getAdjacentVertexes()) {
			if (child.equals(destVertex)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String args[]) {
		Graph<Integer> graph = new Graph<Integer>(false);
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(1, 3);
		graph.addEdge(3, 4);
		graph.addEdge(0, 4);
		graph.addEdge(0, 3);
		NumberofTrianglesInGraph not = new NumberofTrianglesInGraph();
		System.out.println(not.countTriangles(graph));

	}
}
