package com.algo.ds.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Depth First Traversal can be used to detect cycle in a Graph. DFS for a connected graph produces a tree. There is a
 * cycle in a graph only if there is a back edge present in the graph. A back edge is an edge that is from a node to
 * itself (self loop) or one of its ancestor in the tree produced by DFS.
 * 
 * For a disconnected graph, we get the DFS forest as output. To detect cycle, we can check for cycle in individual
 * trees by checking back edges.
 * 
 * Time Complexity of this method is same as time complexity of DFS traversal which is O(V+E).
 */
public class CycleInDirectedGraph {

	/**
	 * https://www.geeksforgeeks.org/detect-cycle-direct-graph-using-colors/
	 * 
	 * In this post a different solution is discussed. The solution is from CLRS book. The idea is to do DFS of given
	 * graph and while doing traversal, assign one of the below three colors to every vertex.
	 * 
	 * WHITE : Vertex is not processed yet. Initially all vertices are WHITE.
	 * 
	 * GRAY : Vertex is being processed (DFS for this vertex has started, but not finished which means that all
	 * descendants (ind DFS tree) of this vertex are not processed yet (or this vertex is in function call stack)
	 * 
	 * BLACK : Vertex and all its descendants are processed.
	 * 
	 * While doing DFS, if we encounter an edge from current vertex to a GRAY vertex, then this edge is back edge and
	 * hence there is a cycle.
	 * 
	 * @param graph
	 * @return
	 */
	public boolean hasCycle(Graph<Integer> graph) {
		Set<Vertex<Integer>> whiteSet = new HashSet<>();
		Set<Vertex<Integer>> graySet = new HashSet<>();
		Set<Vertex<Integer>> blackSet = new HashSet<>();

		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			whiteSet.add(vertex);
		}

		while (whiteSet.size() > 0) {
			Vertex<Integer> current = whiteSet.iterator().next();
			if (dfs(current, whiteSet, graySet, blackSet)) {
				return true;
			}
		}
		return false;
	}

	private boolean dfs(Vertex<Integer> current, Set<Vertex<Integer>> whiteSet, Set<Vertex<Integer>> graySet,
			Set<Vertex<Integer>> blackSet) {
		// move current to gray set from white set and then explore it.
		moveVertex(current, whiteSet, graySet);
		for (Vertex<Integer> neighbor : current.getAdjacentVertexes()) {
			// if in black set means already explored so continue.
			if (blackSet.contains(neighbor)) {
				continue;
			}
			// if in gray set then cycle found.
			if (graySet.contains(neighbor)) {
				return true;
			}
			if (dfs(neighbor, whiteSet, graySet, blackSet)) {
				return true;
			}
		}
		// move vertex from gray set to black set when done exploring.
		moveVertex(current, graySet, blackSet);
		return false;
	}

	private void moveVertex(Vertex<Integer> vertex, Set<Vertex<Integer>> sourceSet,
			Set<Vertex<Integer>> destinationSet) {
		sourceSet.remove(vertex);
		destinationSet.add(vertex);
	}

	/*
	 * Solution 2 - https://www.geeksforgeeks.org/detect-cycle-in-a-directed-graph-using-bfs/
	 * 
	 * TODO
	 */

	public static void main(String args[]) {
		Graph<Integer> graph = new Graph<>(true);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 3);
		graph.addEdge(4, 1);
		graph.addEdge(4, 5);
		graph.addEdge(5, 6);
		graph.addEdge(6, 4);
		CycleInDirectedGraph cdg = new CycleInDirectedGraph();
		System.out.println(cdg.hasCycle(graph));
	}
}
