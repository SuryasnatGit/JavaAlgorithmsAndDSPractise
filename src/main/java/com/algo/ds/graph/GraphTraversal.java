package com.algo.ds.graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
 * 
 * Breadth First Traversal (or Search) for a graph is similar to Breadth First Traversal of a tree
 * (See method 2 of this post). The only catch here is, unlike trees, graphs may contain cycles, so
 * we may come to the same node again. To avoid processing a node more than once, we use a boolean
 * visited array. For simplicity, it is assumed that all vertices are reachable from the starting
 * vertex. For example, in the following graph, we start traversal from vertex 2. When we come to
 * vertex 0, we look for all adjacent vertices of it. 2 is also an adjacent vertex of 0. If we don’t
 * mark visited vertices, then 2 will be processed again and it will become a non-terminating
 * process.
 * 
 * Time Complexity: O(V+E) where V is number of vertices in the graph and E is number of edges in
 * the graph.
 * 
 * http://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/
 * 
 * Depth First Traversal (or Search) for a graph is similar to Depth First Traversal of a tree. The
 * only catch here is, unlike trees, graphs may contain cycles, so we may come to the same node
 * again. To avoid processing a node more than once, we use a boolean visited array.
 */
public class GraphTraversal {

	public void DFS(Graph<Integer> graph) {
		Set<Integer> visited = new HashSet<>();
		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			if (!visited.contains(vertex.getId())) {
				DFSUtil(vertex, visited);
			}
		}

	}

	private void DFSUtil(Vertex<Integer> v, Set<Integer> visited) {
		visited.add(v.getId());
		System.out.print(v.getId() + " ");
		for (Vertex<Integer> vertex : v.getAdjacentVertexes()) {
			if (!visited.contains(vertex.getId()))
				DFSUtil(vertex, visited);
		}
	}

	// The function to do DFS traversal. It uses recursive DFSUtil()
	public void DFS(int v) {
		// Mark all the vertices as not visited(set as
		// false by default in java)
		boolean visited[] = new boolean[V];

		// Call the recursive helper function to print DFS traversal
		DFSUtil(v, visited);
	}

	// A function used by DFS
	private void DFSUtil(int v, boolean visited[]) {
		// Mark the current node as visited and print it
		visited[v] = true;
		System.out.print(v + " ");

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = adj[v].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n])
				DFSUtil(n, visited);
		}
	}

	public void BFS(Graph<Integer> graph) {
		Set<Integer> visited = new HashSet<>();
		Queue<Vertex<Integer>> q = new LinkedList<Vertex<Integer>>();

		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			if (!visited.contains(vertex.getId())) {
				q.add(vertex);
				visited.add(vertex.getId());
				while (q.size() != 0) {
					Vertex<Integer> vq = q.poll();
					System.out.print(vq.getId() + " ");
					for (Vertex<Integer> v : vq.getAdjacentVertexes()) {
						if (!visited.contains(v.getId())) {
							q.add(v);
							visited.add(v.getId());
						}
					}
				}
			}
		}
	}

	/**
	 * BFS of graph using adjacency list.
	 */
	private int V = 4; // No. of vertices
	private LinkedList<Integer> adj[]; // Adjacency Lists

	// prints BFS traversal from a given source s
	public void BFS(int s) {
		adj = new LinkedList[4];
		for (int i = 0; i < 4; ++i)
			adj[i] = new LinkedList();

		// Mark all the vertices as not visited(By default
		// set as false)
		boolean visited[] = new boolean[V];

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it
		visited[s] = true;
		queue.add(s);

		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			s = queue.poll();
			System.out.print(s + " ");

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			Iterator<Integer> i = adj[s].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}

	public static void main(String args[]) {

		Graph<Integer> graph = new Graph<Integer>(true);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(3, 4);
		graph.addEdge(4, 6);
		graph.addEdge(6, 5);
		// graph.addEdge(5, 1);
		graph.addEdge(5, 3);

		GraphTraversal g = new GraphTraversal();
		g.BFS(graph);
	}
}
