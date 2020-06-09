package com.algo.ds.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://www.techiedelight.com/maximum-cost-path-graph-source-destination/
 *
 */
public class MaxCostPathInGraph {

	/**
	 * Class definitions
	 */
	// data structure to store graph edges
	class Edge {
		public final int src, dest, weight;

		public Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
	}

	// BFS Node
	class Node {
		// current vertex number and cost of current path
		public int vertex, weight;

		// set of nodes visited so far in current path
		public Set<Integer> s;

		// maintain parent node for printing final path
		public Node parent;

		public Node(int vertex, int weight, Set<Integer> s) {
			this.vertex = vertex;
			this.weight = weight;
			this.s = s;
		}

		public Node(int vertex, int weight, Set<Integer> s, Node parent) {
			this.vertex = vertex;
			this.weight = weight;
			this.s = s;
			this.parent = parent;
		}
	}

	// class to represent a graph object
	class Graph {
		// A list of lists to represent adjacency list
		public List<List<Edge>> adj = new ArrayList<>();

		// Constructor to construct graph
		public Graph(List<Edge> edges, int N) {
			// resize the List to N elements of type List<Edge>
			for (int i = 0; i < N; i++) {
				adj.add(i, new ArrayList<>());
			}

			// add edges to the undirected graph
			for (Edge e : edges) {
				adj.get(e.src).add(new Edge(e.src, e.dest, e.weight));
				adj.get(e.dest).add(new Edge(e.dest, e.src, e.weight));
			}
		}
	}

	// Perform BFS on graph g starting from vertex v
	public int maxCostBFS(Graph g, int src, int k) {
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
			// update maximum cost calculated so far
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

	// Perform BFS on graph g starting from vertex v
	public Node maxCostBFSPath(Graph g, int src, int k) {
		// create a queue used to do BFS
		Queue<Node> q = new ArrayDeque<>();

		// add source vertex to set and push it into the queue
		Set<Integer> vertices = new HashSet<>();
		vertices.add(0);
		q.add(new Node(src, 0, vertices, null));

		// stores maximum-cost path information
		Node maxCostPathLastNode = null;

		// stores front node of queue
		Node front = null;

		// run till queue is not empty
		while (!q.isEmpty()) {
			// pop front node from queue
			front = q.poll();

			int v = front.vertex;
			int cost = front.weight;
			vertices = new HashSet<>(front.s);

			// if destination is reached and BFS depth is equal to m
			// update minimum cost calculated so far
			if (cost > k && (maxCostPathLastNode == null || maxCostPathLastNode.weight < cost)) {
				maxCostPathLastNode = front;
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
					q.add(new Node(edge.dest, cost + edge.weight, s, front));
				}
			}
		}

		// return last node containing max-cost path information
		return maxCostPathLastNode;
	}

	private void printPath(Node node) {
		if (node == null) {
			return;
		}
		printPath(node.parent);
		System.out.print(node.vertex + " -> ");
	}

	public static void main(String[] args) {

		MaxCostPathInGraph path = new MaxCostPathInGraph();

		// List of graph edges as per above diagram
		List<Edge> edges = new ArrayList<>();
		Edge e1 = path.new Edge(0, 6, 11);
		Edge e2 = path.new Edge(0, 1, 5);
		Edge e3 = path.new Edge(1, 6, 3);
		Edge e4 = path.new Edge(1, 5, 5);
		Edge e5 = path.new Edge(0, 1, 5);
		Edge e6 = path.new Edge(1, 2, 7);
		Edge e7 = path.new Edge(2, 3, -8);
		Edge e8 = path.new Edge(3, 4, 10);
		Edge e9 = path.new Edge(5, 2, -1);
		Edge e10 = path.new Edge(5, 3, 9);
		Edge e11 = path.new Edge(5, 4, 1);
		Edge e12 = path.new Edge(6, 5, 2);
		Edge e13 = path.new Edge(7, 6, 9);
		Edge e14 = path.new Edge(7, 1, 6);
		edges.add(e1);
		edges.add(e2);
		edges.add(e3);
		edges.add(e4);
		edges.add(e5);
		edges.add(e6);
		edges.add(e7);
		edges.add(e8);
		edges.add(e9);
		edges.add(e10);
		edges.add(e11);
		edges.add(e12);
		edges.add(e13);
		edges.add(e14);

		int N = 9;

		// create a graph from edges
		Graph g = path.new Graph(edges, N);

		int src = 0;
		int cost = 50;

		// Do modified BFS traversal from source vertex src
		int maxCost = path.maxCostBFS(g, src, cost);

		if (maxCost != Integer.MIN_VALUE) {
			System.out.println(maxCost);
		} else {
			System.out.println("All paths from source have their costs < " + cost);
		}

		Node maxCostBFSPath = path.maxCostBFSPath(g, src, cost);
		path.printPath(maxCostBFSPath);
	}

}
