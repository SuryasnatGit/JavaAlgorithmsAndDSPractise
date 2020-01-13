package com.algo.ds.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://www.techiedelight.com/maximum-cost-path-graph-source-destination/
 *
 */
public class MaxCostPathInGraph {

	// Perform BFS on graph g starting from vertex v
	public static int modifiedBFS(Graph g, int src, int k) {
		// create a queue used to do BFS
		Queue<Node> q = new ArrayDeque<>();

		// add source vertex to set and push it into the queue
		Set<Integer> vertices = new HashSet<>();
		vertices.add(0);
		q.add(new Node(src, 0, vertices));

		// stores maximum-cost of path from source
		int maxCost = Integer.MIN_VALUE;

		// run till queue is not empty
		while (!q.isEmpty()) {
			// pop front node from queue
			Node node = q.poll();

			int v = node.vertex;
			int cost = node.weight;
			vertices = new HashSet<>(node.s);

			// if destination is reached and BFS depth is equal to m
			// update minimum cost calculated so far
			if (cost > k) {
				maxCost = Math.max(maxCost, cost);
			}

			// do for every adjacent edge of v
			for (Edge edge : g.adj.get(v)) {
				// check for cycle
				if (!vertices.contains(edge.dest)) {
					// add current node into the path
					Set<Integer> s = new HashSet<>(vertices);
					s.add(edge.dest);

					// push every vertex (discovered or undiscovered) into
					// the queue with cost equal to (cost of parent + weight
					// of current edge)
					q.add(new Node(edge.dest, cost + edge.weight, s));
				}
			}
		}

		// return max-cost
		return maxCost;
	}

	public static void main(String[] args) {
		// List of graph edges as per above diagram
		List<Edge> edges = Arrays.asList(Edge.of(0, 6, 11), Edge.of(0, 1, 5), Edge.of(1, 6, 3), Edge.of(1, 5, 5),
				Edge.of(1, 2, 7), Edge.of(2, 3, -8), Edge.of(3, 4, 10), Edge.of(5, 2, -1), Edge.of(5, 3, 9),
				Edge.of(5, 4, 1), Edge.of(6, 5, 2), Edge.of(7, 6, 9), Edge.of(7, 1, 6));

		// Number of nodes in the graph
		int N = 9;

		// create a graph from edges
		Graph g = new Graph(edges, N);

		int src = 0;
		int cost = 50;

		// Do modified BFS traversal from source vertex src
		int maxCost = modifiedBFS(g, src, cost);

		if (maxCost != Integer.MIN_VALUE) {
			System.out.println(maxCost);
		} else {
			System.out.println("All paths from source have their costs < " + cost);
		}
	}

}

// data structure to store graph edges
class Edge {
	public final int src, dest, weight;

	private Edge(int src, int dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}

	// Factory method for creating a Edge immutable instance
	public static Edge of(int a, int b, int c) {
		return new Edge(a, b, c); // calls private constructor
	}
}

// BFS Node
class Node {
	// current vertex number and cost of current path
	int vertex, weight;

	// set of nodes visited so far in current path
	Set<Integer> s;

	Node(int vertex, int weight, Set<Integer> s) {
		this.vertex = vertex;
		this.weight = weight;
		this.s = s;
	}
}

// class to represent a graph object
class Graph {
	// A list of lists to represent adjacency list
	List<List<Edge>> adj = new ArrayList<>();

	// Constructor to construct graph
	public Graph(List<Edge> edges, int N) {
		// resize the List to N elements of type List<Edge>
		for (int i = 0; i < N; i++) {
			adj.add(i, new ArrayList<>());
		}

		// add edges to the undirected graph
		for (Edge e : edges) {
			adj.get(e.src).add(Edge.of(e.src, e.dest, e.weight));
			adj.get(e.dest).add(Edge.of(e.dest, e.src, e.weight));
		}
	}
}
