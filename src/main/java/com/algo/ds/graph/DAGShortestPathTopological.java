package com.algo.ds.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * http://www.geeksforgeeks.org/shortest-path-for-directed-acyclic-graphs/
 * 
 * Given a Weighted Directed Acyclic Graph and a source vertex in the graph, find the shortest paths
 * from given source to all other vertices.
 * 
 * We initialize distances to all vertices as infinite and distance to source as 0, then we find a
 * topological sorting of the graph. Topological Sorting of a graph represents a linear ordering of
 * the graph (See below, figure (b) is a linear representation of figure (a) ). Once we have
 * topological order (or linear representation), we one by one process all vertices in topological
 * order. For every vertex being processed, we update distances of its adjacent using distance of
 * current vertex.
 * 
 * Time complexity of topological sorting is O(V+E). After finding topological order, the algorithm
 * process all vertices and for every vertex, it runs a loop for all adjacent vertices. Total
 * adjacent vertices in a graph is O(E). So the inner loop runs O(V+E) times. Therefore, overall
 * time complexity of this algorithm is O(V+E).
 * 
 */
public class DAGShortestPathTopological<T> {

	private int V;
	private LinkedList<AdjListNode> adj[];

	static final int INF = Integer.MAX_VALUE;

	class AdjListNode {
		private int v;
		private int weight;

		AdjListNode(int _v, int _w) {
			v = _v;
			weight = _w;
		}

		int getV() {
			return v;
		}

		int getWeight() {
			return weight;
		}
	}

	public DAGShortestPathTopological(int v) {
		V = v;
		adj = new LinkedList[V];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList<AdjListNode>();
	}

	void shortestPath(int s) {
		Stack stack = new Stack();
		int dist[] = new int[V];

		// Mark all the vertices as not visited
		Boolean visited[] = new Boolean[V];
		for (int i = 0; i < V; i++)
			visited[i] = false;

		// Call the recursive helper function to store Topological
		// Sort starting from all vertices one by one
		for (int i = 0; i < V; i++)
			if (visited[i] == false)
				topologicalSortUtil(i, visited, stack);

		// Initialize distances to all vertices as infinite and
		// distance to source as 0
		for (int i = 0; i < V; i++)
			dist[i] = INF;
		dist[s] = 0;

		// Process vertices in topological order
		while (!stack.empty()) {
			// Get the next vertex from topological order
			int u = (int) stack.pop();

			// Update distances of all adjacent vertices
			Iterator<AdjListNode> it;
			if (dist[u] != INF) {
				it = adj[u].iterator();
				while (it.hasNext()) {
					AdjListNode i = it.next();
					if (dist[i.getV()] > dist[u] + i.getWeight())
						dist[i.getV()] = dist[u] + i.getWeight();
				}
			}
		}

		// Print the calculated shortest distances
		for (int i = 0; i < V; i++) {
			if (dist[i] == INF)
				System.out.print("INF ");
			else
				System.out.print(dist[i] + " ");
		}
	}

	void topologicalSortUtil(int v, Boolean visited[], Stack stack) {
		// Mark the current node as visited.
		visited[v] = true;
		Integer i;

		// Recur for all the vertices adjacent to this vertex
		Iterator<AdjListNode> it = adj[v].iterator();
		while (it.hasNext()) {
			AdjListNode node = it.next();
			if (!visited[node.getV()])
				topologicalSortUtil(node.getV(), visited, stack);
		}
		// Push current vertex to stack which stores result
		stack.push(new Integer(v));
	}


    
    public static void main(String args[]){
        Graph<Integer> graph = new Graph<Integer>(true);
        graph.addEdge(1, 2,4);
        graph.addEdge(2, 3,3);
        graph.addEdge(2, 4,2);
        graph.addEdge(1, 3,2);
        graph.addEdge(3, 5,1);
        graph.addEdge(4, 5,5);
        graph.addEdge(5, 6,2);
        graph.addEdge(4, 7,3);

		// DAGShortestPathTopological<Integer> shortestPath = new DAGShortestPathTopological<Integer>();
		// Map<Vertex<Integer>,Integer> distance = shortestPath.shortestPath(graph,
		// graph.getAllVertex().iterator().next());
		// System.out.print(distance);
        
    }
}
