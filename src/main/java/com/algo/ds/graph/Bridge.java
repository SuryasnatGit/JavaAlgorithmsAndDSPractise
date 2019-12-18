package com.algo.ds.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * http://www.geeksforgeeks.org/bridge-in-a-graph/
 * 
 * An edge in an undirected connected graph is a bridge if removing it disconnects the graph. For a
 * disconnected undirected graph, definition is similar, a bridge is an edge removing which
 * increases number of disconnected components. Like Articulation Points,bridges represent
 * vulnerabilities in a connected network and are useful for designing reliable networks. For
 * example, in a wired computer network, an articulation point indicates the critical computers and
 * a bridge indicates the critical wires or connections.
 * 
 * 
 * How to find all bridges in a given graph?
 * 
 * A simple approach is to one by one remove all edges and see if removal of a edge causes
 * disconnected graph. Following are steps of simple approach for connected graph.
 * 
 * 1) For every edge (u, v), do following.
 * 
 * ..a) Remove (u, v) from graph
 * 
 * ..b) See if the graph remains connected (We can either use BFS or DFS)
 * 
 * ..c) Add (u, v) back to the graph.
 * 
 * Time complexity of above method is O(E*(V+E)) for a graph represented using adjacency list. Can
 * we do better?
 * 
 * A O(V+E) algorithm to find all Bridges:
 * 
 * The idea is similar to O(V+E) algorithm for Articulation Points. We do DFS traversal of the given
 * graph. In DFS tree an edge (u, v) (u is parent of v in DFS tree) is bridge if there does not
 * exist any other alternative to reach u or an ancestor of u from subtree rooted with v. As
 * discussed in the previous post, the value low[v] indicates earliest visited vertex reachable from
 * subtree rooted with v. The condition for an edge (u, v) to be a bridge is, low[v] > disc[u].
 * 
 * 
 */
public class Bridge<T> {

	private int time;

	public Set<Edge<T>> getBridge(Graph<T> graph) {

		Set<Edge<T>> result = new HashSet<Edge<T>>();
		Map<Vertex<T>, Integer> discovery = new HashMap<Vertex<T>, Integer>();
		Map<Vertex<T>, Integer> low = new HashMap<Vertex<T>, Integer>();
		Map<Vertex<T>, Vertex<T>> parent = new HashMap<Vertex<T>, Vertex<T>>();
		Map<Vertex<T>, Boolean> visited = new HashMap<Vertex<T>, Boolean>();

		for (Vertex<T> vertex : graph.getAllVertex()) {
			if (!visited.containsKey(vertex)) {
				bridgeUtil(vertex, result, discovery, low, parent, visited);
			}
		}
		return result;
	}

	private void bridgeUtil(Vertex<T> vertex, Set<Edge<T>> result, Map<Vertex<T>, Integer> discovery,
			Map<Vertex<T>, Integer> low, Map<Vertex<T>, Vertex<T>> parent, Map<Vertex<T>, Boolean> visited) {
		visited.put(vertex, true);

		// initialize very first time to 0
		discovery.put(vertex, time);
		low.put(vertex, time);

		time++;
		for (Vertex<T> child : vertex.getAdjacentVertexes()) {
			if (!visited.containsKey(child)) {
				parent.put(child, vertex);
				bridgeUtil(child, result, discovery, low, parent, visited);

				if (discovery.get(vertex) < low.get(child)) {
					result.add(new Edge<T>(vertex, child));
				}
				low.put(vertex, Math.min(discovery.get(vertex), low.get(child)));
			} else {
				if (!child.equals(parent.get(vertex))) {
					low.put(vertex, Math.min(discovery.get(vertex), low.get(child)));
				}
			}
		}
	}

	public static void main(String args[]) {

		Graph<Integer> graph = new Graph<Integer>(false);
		graph.addEdge(2, 1);
		graph.addEdge(3, 1);
		graph.addEdge(1, 4);
		graph.addEdge(4, 5);
		graph.addEdge(5, 1);
		Bridge<Integer> ap = new Bridge<Integer>();
		Set<Edge<Integer>> result = ap.getBridge(graph);
		System.out.print(result);
	}

}
