package com.algo.ds.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  
 */
public class PrimMST {

	private static final int V = 5; // number of vertices

	/**
	 * Time Complexity of the above program is O(V^2).
	 * 
	 * @param graph
	 */
	public void primMST_adjacentMatrix(int[][] graph) {
		// parent array containing result of MST
		int[] parent = new int[V];
		// key array containing the value of the key
		int[] key = new int[V];
		// boolean array which checks for the presence of the vertex in the MST set
		boolean[] mstSet = new boolean[V];

		// start with initializing value to infinity
		for (int i = 0; i < V; i++) {
			key[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}

		// initialize first element to 0 to start MST.
		key[0] = 0;
		parent[0] = -1;

		for (int count = 0; count < V - 1; count++) {
			// find minimum value key from other vertices not present in MST set
			int u = findMinKey(key, mstSet);
			mstSet[u] = true;
			// Update key value and parent index of the adjacent
			// vertices of the picked vertex. Consider only those
			// vertices which are not yet included in MST
			for (int v = 0; v < V; v++) {
				// graph[u][v] is non zero only for adjacent vertices of m
				// mstSet[v] is false for vertices not yet included in MST
				// Update the key only if graph[u][v] is smaller than key[v]
				if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
					key[v] = graph[u][v];
					parent[v] = u;
				}
			}
		}

		printMST(parent, graph);
	}

	private void printMST(int[] parent, int[][] graph) {
		System.out.println("Edge   Weight");
		for (int i = 1; i < V; i++)
			System.out.println(parent[i] + " - " + i + "    " + graph[i][parent[i]]);

	}

	/**
	 * // A utility function to find the vertex with minimum key // value, from the set of vertices not
	 * yet included in MST
	 * 
	 * @param key
	 * @param mstSet
	 * @return
	 */
	private int findMinKey(int[] key, boolean[] mstSet) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int i = 0; i < V - 1; i++) {
			if (key[i] < min && mstSet[i] == false) {
				min = key[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	/**
	 * Main method of Prim's algorithm. Find minimum spanning tree using Prim's algorithm.
	 * 
	 * As discussed in the previous post, in Prims algorithm, two sets are maintained, one set contains
	 * list of vertices already included in MST, other set contains vertices not yet included. With
	 * adjacency list representation, all vertices of a graph can be traversed in O(V+E) time using BFS.
	 * The idea is to traverse all vertices of graph using BFS and use a Min Heap to store the vertices
	 * not yet included in MST. Min Heap is used as a priority queue to get the minimum weight edge from
	 * the cut. Min Heap is used as time complexity of operations like extracting minimum element and
	 * decreasing key value is O(LogV) in Min Heap.
	 * 
	 * Space complexity - O(E + V) Time complexity - O(ElogV)
	 *
	 * References https://en.wikipedia.org/wiki/Prim%27s_algorithm CLRS book
	 */
//	public List<Edge<Integer>> primMST_adjacencyList(Graph<Integer> graph) {
//
//		// binary heap + map data structure
//		BinaryMinHeap<Vertex<Integer>> minHeap = new BinaryMinHeap<>();
//
//		// map of vertex to edge which gave minimum weight to this vertex.
//		Map<Vertex<Integer>, Edge<Integer>> vertexToEdge = new HashMap<>();
//
//		// stores final result
//		List<Edge<Integer>> result = new ArrayList<>();
//
//		// insert all vertices with infinite value initially.
//		for (Vertex<Integer> v : graph.getAllVertex()) {
//			minHeap.addInterval(Integer.MAX_VALUE, v);
//		}
//
//		// start from any random vertex
//		Vertex<Integer> startVertex = graph.getAllVertex().iterator().next();
//
//		// for the start vertex decrease the value in heap + map to 0
//		minHeap.decrease(startVertex, 0);
//
//		// iterate till heap + map has elements in it
//		while (!minHeap.empty()) {
//			// extract min value vertex from heap + map
//			Vertex<Integer> current = minHeap.extractMin();
//
//			// get the corresponding edge for this vertex from map if present and add it to final result.
//			// This edge wont be present for first vertex.
//			Edge<Integer> spanningTreeEdge = vertexToEdge.get(current);
//			if (spanningTreeEdge != null) {
//				result.add(spanningTreeEdge);
//			}
//
//			// iterate through all the adjacent vertices
//			for (Edge<Integer> edge : current.getEdges()) {
//				Vertex<Integer> adjacent = getVertexForEdge(current, edge);
//				// check if adjacent vertex exist in heap + map and weight attached with this vertex is greater than
//				// this edge weight
//				if (minHeap.containsData(adjacent) && minHeap.getWeight(adjacent) > edge.getWeight()) {
//					// decrease the value of adjacent vertex to this edge weight.
//					minHeap.decrease(adjacent, edge.getWeight());
//					// add vertex->edge mapping in the graph.
//					vertexToEdge.put(adjacent, edge);
//				}
//			}
//		}
//		return result;
//	}

	private Vertex<Integer> getVertexForEdge(Vertex<Integer> v, Edge<Integer> e) {
		return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
	}

	public static void main(String args[]) {
		Graph<Integer> graph = new Graph<>(false);
		/*
		 * graph.addEdge(0, 1, 4); graph.addEdge(1, 2, 8); graph.addEdge(2, 3, 7); graph.addEdge(3, 4, 9);
		 * graph.addEdge(4, 5, 10); graph.addEdge(2, 5, 4); graph.addEdge(1, 7, 11); graph.addEdge(0, 7, 8);
		 * graph.addEdge(2, 8, 2); graph.addEdge(3, 5, 14); graph.addEdge(5, 6, 2); graph.addEdge(6, 8, 6);
		 * graph.addEdge(6, 7, 1); graph.addEdge(7, 8, 7);
		 */

		graph.addEdge(1, 2, 3);
		graph.addEdge(2, 3, 1);
		graph.addEdge(3, 1, 1);
		graph.addEdge(1, 4, 1);
		graph.addEdge(2, 4, 3);
		graph.addEdge(4, 5, 6);
		graph.addEdge(5, 6, 2);
		graph.addEdge(3, 5, 5);
		graph.addEdge(3, 6, 4);

		PrimMST prims = new PrimMST();
//		Collection<Edge<Integer>> edges = prims.primMST_adjacencyList(graph);
//		for (Edge<Integer> edge : edges) {
//			System.out.println(edge);
//		}

		int graph1[][] = new int[][] { { 0, 2, 0, 6, 0 }, { 2, 0, 3, 8, 5 }, { 0, 3, 0, 0, 7 }, { 6, 8, 0, 0, 9 },
				{ 0, 5, 7, 9, 0 }, };

		prims.primMST_adjacentMatrix(graph1);

	}

}
