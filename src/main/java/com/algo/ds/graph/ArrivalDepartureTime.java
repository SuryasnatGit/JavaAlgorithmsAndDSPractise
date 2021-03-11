package com.algo.ds.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.techiedelight.com/arrival-departure-time-vertices-dfs/
 * 
 * Arrival time is the time at which the vertex was explored the first time in DFS. Departure time is the time at which
 * we have explored all neighbours of the vertex and we are ready to back track
 */
public class ArrivalDepartureTime {

	// Function to perform DFS Traversal
	public static int DFS(Graph2 graph, int v, boolean[] discovered, int[] arrival, int[] departure, int time) {

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

	public static void main(String[] args) {
		// List of graph edges as per the above diagram
		List<Edge2> edges = Arrays.asList(new Edge2(0, 1), new Edge2(0, 2), new Edge2(2, 3), new Edge2(2, 4),
				new Edge2(3, 1), new Edge2(3, 5), new Edge2(4, 5), new Edge2(6, 7));

		// total number of nodes in the graph
		final int N = 8;

		// build a graph from the given edges
		Graph2 graph = new Graph2(edges, N);

		// array to store the arrival time of vertex
		int[] arrival = new int[N];

		// array to store the departure time of vertex
		int[] departure = new int[N];

		// Mark all the vertices as not discovered
		boolean[] discovered = new boolean[N];
		int time = -1;

		// Perform DFS traversal from all undiscovered nodes to
		// cover all unconnected components of a graph
		for (int i = 0; i < N; i++) {
			if (!discovered[i]) {
				time = DFS(graph, i, discovered, arrival, departure, time);
			}
		}

		// print arrival and departure time of each
		// vertex in DFS
		for (int i = 0; i < N; i++) {
			System.out.println("Vertex " + i + " (" + arrival[i] + ", " + departure[i] + ")");
		}
	}

}

// Data structure to store graph edges
class Edge2 {
	int source, dest;

	public Edge2(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
}

// Class to represent a graph object
class Graph2 {
	// A List of Lists to represent an adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	Graph2(List<Edge2> edges, int N) {
		adjList = new ArrayList<>(N);

		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the undirected graph
		for (Edge2 edge : edges) {
			adjList.get(edge.source).add(edge.dest);
		}
	}
}