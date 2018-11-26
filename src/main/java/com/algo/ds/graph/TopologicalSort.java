package com.algo.ds.graph;

import java.util.Collections;
import java.util.Map;
import java.util.Stack;

/**
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that
 * for every directed edge uv, vertex u comes before v in the ordering. Topological Sorting for a
 * graph is not possible if the graph is not a DAG.. Given a directed acyclic graph, do a
 * topological sort on this graph. A topological ordering is possible if and only if the graph has
 * no directed cycles, that is, if it is a directed acyclic graph (DAG).
 *
 * Do DFS by keeping visited. Put the vertex which are completely explored into a stack. Pop from
 * stack to get sorted order.
 *
 * Space and time complexity is O(n).
 */
public class TopologicalSort<T> {

	// A recursive function used by topologicalSort
	private void topologicalSortUtil(Vertex<T> vertex, Map<Vertex<T>, Boolean> visited, Stack<Vertex<T>> stack,
			Graph<T> graph) {
		// Mark the current node as visited.
		visited.put(vertex, true);

		// Recur for all the vertices adjacent to this
		// vertex
		for (Vertex<T> adjVertex : vertex.getAdjacentVertexes()) {
			if (!visited.get(adjVertex))
				topologicalSortUtil(adjVertex, visited, stack, graph);
		}

		// Push current vertex to stack which stores result
		stack.push(vertex);
	}

	// The function to do Topological Sort. It uses
	// recursive topologicalSortUtil()
	public void topologicalSort(Graph<T> graph) {
		Stack<Vertex<T>> stack = new Stack<>();

		// Mark all the vertices as not visited
		Map<Vertex<T>, Boolean> visited = Collections.emptyMap();
		for (Map.Entry<Vertex<T>, Boolean> set : visited.entrySet()) {
			set.setValue(false);
		}
		// Collections.e

		// Call the recursive helper function to store
		// Topological Sort starting from all vertices
		// one by one
		for (Vertex<T> vertex : graph.getAllVertex()) {
			if (!visited.get(vertex))
				topologicalSortUtil(vertex, visited, stack, graph);
		}

		// Print contents of stack
		while (stack.empty() == false)
			System.out.print(stack.pop() + " ");
	}

	public static void main(String args[]) {
		Graph<Integer> graph = new Graph<>(true);
		graph.addEdge(1, 3);
		graph.addEdge(1, 2);
		graph.addEdge(3, 4);
		graph.addEdge(5, 6);
		graph.addEdge(6, 3);
		graph.addEdge(3, 8);
		graph.addEdge(8, 11);

		TopologicalSort<Integer> sort = new TopologicalSort<Integer>();
		sort.topologicalSort(graph);
		// while (!result.isEmpty()) {
		// System.out.println(result.poll());
		// }
	}
}
