package com.algo.ds.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.techiedelight.com/arrival-departure-time-vertices-dfs/
 * 
 */
public class ArrivalDepartureTime {

	// Function to perform DFS Traversal
	public static int DFS(Graph graph, int v, boolean[] discovered, int[] arrival, int[] departure, int time) {

		// set arrival time of vertex v
		arrival[v] = ++time;

		// mark vertex as discovered
		discovered[v] = true;

		for (int i : graph.adjList.get(v)) {
			if (!discovered[i]) {
				time = DFS(graph, i, discovered, arrival, departure, time);
			}
		}

		// set departure time of vertex v
		departure[v] = ++time;

		return time;
	}

	// Data structure to store graph edges
	class Edge {
		int source, dest;

		public Edge(int source, int dest) {
			this.source = source;
			this.dest = dest;
		}
	};

	// Class to represent a graph object
	class Graph {
		// A List of Lists to represent an adjacency list
		List<List<Integer>> adjList = null;

		// Constructor
		Graph(List<Edge> edges, int N) {
			adjList = new ArrayList<>(N);

			for (int i = 0; i < N; i++) {
				adjList.add(i, new ArrayList<>());
			}

			// add edges to the undirected graph
			for (Edge edge : edges) {
				adjList.get(edge.source).add(edge.dest);
			}
		}
	}

}
