package com.algo.ds.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * https://www.techiedelight.com/breadth-first-search/
 * 
 * https://www.techiedelight.com/depth-first-search/
 * 
 * http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
 * 
 * Breadth First Traversal (or Search) for a graph is similar to Breadth First Traversal of a tree (See method 2 of this
 * post). The only catch here is, unlike trees, graphs may contain cycles, so we may come to the same node again. To
 * avoid processing a node more than once, we use a boolean visited array. For simplicity, it is assumed that all
 * vertices are reachable from the starting vertex. For example, in the following graph, we start traversal from vertex
 * 2. When we come to vertex 0, we look for all adjacent vertices of it. 2 is also an adjacent vertex of 0. If we dont
 * mark visited vertices, then 2 will be processed again and it will become a non-terminating process.
 * 
 * Time Complexity: O(V+E) where V is number of vertices in the graph and E is number of edges in the graph.
 * 
 * http://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/
 * 
 * Depth First Traversal (or Search) for a graph is similar to Depth First Traversal of a tree. The only catch here is,
 * unlike trees, graphs may contain cycles, so we may come to the same node again. To avoid processing a node more than
 * once, we use a boolean visited array.
 */
public class GraphTraversal {
	/**
	 * 
	 * T - O(m + n) where m = num of vertices and n = num of edges
	 */

	// Perform BFS on graph starting from vertex v
	public void BFS_iterative(Graph1 graph, int v, boolean[] discovered) {
		// create a queue used to do BFS
		Queue<Integer> q = new ArrayDeque<>();

		// mark source vertex as discovered
		discovered[v] = true;

		// push source vertex into the queue
		q.add(v);

		// run till queue is not empty
		while (!q.isEmpty()) {
			// pop front node from queue and print it
			v = q.poll();
			System.out.print(v + " ");

			// do for every edge (v -> u)
			for (int u : graph.adjList.get(v)) {
				if (!discovered[u]) {
					// mark it discovered and push it into queue
					discovered[u] = true;
					q.add(u);
				}
			}
		}
	}

	// Perform BFS recursively on graph
	public void BFS_recursive(Graph1 graph, Queue<Integer> q, boolean[] discovered) {
		if (q.isEmpty())
			return;

		// pop front node from queue and print it
		int v = q.poll();
		System.out.print(v + " ");

		// do for every edge (v -> u)
		for (int u : graph.adjList.get(v)) {
			if (!discovered[u]) {
				// mark it discovered and push it into queue
				discovered[u] = true;
				q.add(u);
			}
		}

		BFS_recursive(graph, q, discovered);
	}

	// Perform iterative DFS on graph g starting from vertex v
	public void DFS_iterative(Graph1 graph, int v, boolean[] discovered) {
		// create a stack used to do iterative DFS
		Stack<Integer> stack = new Stack<>();

		// push the source node into stack
		stack.push(v);

		// run till stack is not empty
		while (!stack.empty()) {
			// Pop a vertex from stack
			v = stack.pop();

			// if the vertex is already discovered yet, ignore it
			if (discovered[v])
				continue;

			// we will reach here if the popped vertex v
			// is not discovered yet. We print it and process
			// its undiscovered adjacent nodes into stack
			discovered[v] = true;
			System.out.print(v + " ");

			// do for every edge (v -> u)
			List<Integer> adj = graph.adjList.get(v);
			for (int i = adj.size() - 1; i >= 0; i--) {
				int u = adj.get(i);
				if (!discovered[u]) {
					stack.push(u);
				}
			}
		}
	}

	// Function to perform DFS Traversal
	public void DFS_recursive(Graph1 graph, int v, boolean[] discovered) {
		// mark current node as discovered
		discovered[v] = true;

		// print current node
		System.out.print(v + " ");

		// do for every edge (v -> u)
		for (int u : graph.adjList.get(v)) {
			// u is not discovered
			if (!discovered[u]) {
				DFS_recursive(graph, u, discovered);
			}
		}
	}

	public static void main(String args[]) {

		GraphTraversal gt = new GraphTraversal();

		// List of graph edges as per above diagram
		List<Edge1> edges = Arrays.asList(new Edge1(1, 2), new Edge1(1, 3), new Edge1(1, 4), new Edge1(2, 5),
				new Edge1(2, 6), new Edge1(5, 9), new Edge1(5, 10), new Edge1(4, 7), new Edge1(4, 8), new Edge1(7, 11),
				new Edge1(7, 12)
		// vertex 0, 13 and 14 are single nodes
		);

		// Set number of vertices in the graph
		final int N = 15;

		// create a graph from edges
		Graph1 graph = new Graph1(edges, N);

		// stores vertex is discovered or not
		boolean[] discovered = new boolean[N];

		// create a queue used to do BFS
		Queue<Integer> q = new ArrayDeque<>();

		// Do BFS traversal from all undiscovered nodes to
		// cover all unconnected components of graph
		for (int i = 0; i < N; i++) {
			if (discovered[i] == false) {
				// mark source vertex as discovered
				discovered[i] = true;

				// push source vertex into the queue
				q.add(i);

				// start BFS traversal from vertex i
				gt.BFS_recursive(graph, q, discovered);
			}
		}
	}
}

// data structure to store graph edges
class Edge1 {
	int source, dest;

	public Edge1(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
};

// class to represent a graph object
class Graph1 {
	// A List of Lists to represent an adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	Graph1(List<Edge1> edges, int N) {
		adjList = new ArrayList<>(N);

		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the undirected graph
		for (int i = 0; i < edges.size(); i++) {
			int src = edges.get(i).source;
			int dest = edges.get(i).dest;

			adjList.get(src).add(dest);
			adjList.get(dest).add(src);
		}
	}
}
