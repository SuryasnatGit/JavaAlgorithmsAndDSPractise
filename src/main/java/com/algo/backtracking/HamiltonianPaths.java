package com.algo.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.techiedelight.com/print-all-hamiltonian-path-present-in-a-graph/
 * 
 * Given an undirected graph, print all Hamiltonian paths present in it. Hamiltonian path is a path in an undirected or
 * directed graph that visits each vertex exactly once.
 * 
 * . We check if every edge starting from an unvisited vertex leads to a solution or not. As Hamiltonian path visits
 * each vertex exactly once, we take help of visited[] array in proposed solution to process only unvisited vertices.
 * Also we use path[] array to stores vertices covered in current path. If all the vertices are visited, then
 * Hamiltonian path exists in the graph and we print the complete path stored in path[] array.
 * 
 * @author surya
 *
 */
public class HamiltonianPaths {

	public void printPaths(Graph graph, int vertex, boolean[] visited, List<Integer> path, int N) {
		// if path size equals N then all vertices are covered
		if (path.size() == N) {
			path.forEach(c -> System.out.print(c + "->"));
			System.out.println();
			return;
		}

		// Check if every edge starting from vertex v leads to a solution or not
		for (int vert : graph.adjList.get(vertex)) {
			// process only unvisted vertex
			if (!visited[vert]) {
				path.add(vert);
				visited[vert] = true;

				// check if adding vert to the path leads to a solution or not
				printPaths(graph, vertex + 1, visited, path, N);

				// if not then back track
				visited[vert] = false;
				path.remove(path.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		HamiltonianPaths hp = new HamiltonianPaths();

		// List of graph edges as per above diagram
		List<Edge> edges = Arrays.asList(new Edge(0, 1), new Edge(0, 2), new Edge(0, 3), new Edge(1, 2), new Edge(1, 3),
				new Edge(2, 3));

		// Set number of vertices in the graph
		final int N = 4;

		// create a graph from edges
		Graph g = new Graph(edges, N);

		// starting node
		int start = 0;

		// add starting node to the path
		List<Integer> path = new ArrayList<>();
		path.add(start);

		// mark start node as visited
		boolean[] visited = new boolean[N];
		visited[start] = true;

		hp.printPaths(g, start, visited, path, N);
	}

}

/*
 * helper classes
 */
class Edge {
	int source;
	int destination;

	public Edge(int s, int d) {
		this.source = s;
		this.destination = d;
	}
}

class Graph {
	// list of edges
	List<List<Integer>> adjList;

	// send list of edges and number of vertices to create the graph
	public Graph(List<Edge> edges, int N) {
		adjList = new ArrayList<>();

		// initialize edges
		for (int i = 0; i < N; i++) {
			adjList.add(new ArrayList<>());
		}

		for (int i = 0; i < edges.size(); i++) {
			int source = edges.get(i).source;
			int dest = edges.get(i).destination;
			//
			adjList.get(source).add(dest);
			adjList.get(dest).add(source);
		}
	}
}