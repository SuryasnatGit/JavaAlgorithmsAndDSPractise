package com.ctci.treegraph;

import java.util.LinkedList;
import java.util.ListIterator;

import com.algo.ds.graph.Graph;

/**
 * CTCI - 4.1
 * 
 * Given a directed graph, design an algorithm to find out whether there is a route between two
 * nodes.
 * 
 * @author surya
 *
 */
public class RouteBetweenNodes {

	private int v;// number of vertices
	private LinkedList<Integer>[] adjList;

	public RouteBetweenNodes(int size) {
		this.v = size;
		adjList = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			adjList[i] = new LinkedList<>();
		}
	}

	/**
	 * Using BFS
	 * 
	 * @return
	 */
	public boolean isRouteExist(int source, int dest) {

		// mark all vertices as not visited. false by default
		boolean[] visited = new boolean[v];

		// queue for BFS
		LinkedList<Integer> queue = new LinkedList<>();

		// mark current node as visited and enqueue it.
		visited[source] = true;
		queue.add(source);

		while (!queue.isEmpty()) {
			// dequeue a vertex
			Integer vertex = queue.poll();

			// get all adjacent vertex of dequeued vertex. // If a adjacent has not been visited, then mark it
			// visited and enqueue it
			ListIterator<Integer> listIterator = adjList[vertex].listIterator();
			while (listIterator.hasNext()) {
				Integer next = listIterator.next();
				// If this adjacent node is the destination node, then return true
				if (next == dest) {
					return true;
				}

				// Else, continue to do BFS
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
				}
			}
		}
		return false;
	}

	private enum State {
		unvisited, visited, visiting;
	}

	public boolean isRouteExists_DFS(Graph<Integer> g, int source, int dest) {

		// TODO : implement
		return false;
	}

}
