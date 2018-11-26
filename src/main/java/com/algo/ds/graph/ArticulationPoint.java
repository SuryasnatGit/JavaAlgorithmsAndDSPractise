package com.algo.ds.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Find articulation points in connected undirected graph. Articulation points are vertices such
 * that removing any one of them disconnects the graph.
 *
 * We need to do DFS of this graph and keep visitedTime and lowTime for each vertex. lowTime keeps
 * track of back edges.
 *
 * If any one of following condition meets then vertex is articulation point.
 *
 * 1) If vertex is root of DFS and has at least 2 independent children.(By independent it means they
 * are not connected to each other except via this vertex). This condition is needed because if we
 * started from corner vertex it will meet condition 2 but still is not an articulation point. To
 * filter out those vertices we need this condition.
 *
 * 2) It is not root of DFS and if visitedTime of vertex <= lowTime of any adjacent vertex then its
 * articulation point.
 *
 * Time complexity is O(E + V) Space complexity is O(V)
 * 
 * We do DFS traversal of given graph with additional code to find out Articulation Points (APs). In
 * DFS traversal, we maintain a parent[] array where parent[u] stores parent of vertex u. Among the
 * above mentioned two cases, the first case is simple to detect. For every vertex, count children.
 * If currently visited vertex u is root (parent[u] is NIL) and has more than two children, print
 * it. How to handle second case? The second case is trickier. We maintain an array disc[] to store
 * discovery time of vertices. For every node u, we need to find out the earliest visited vertex
 * (the vertex with minimum discovery time) that can be reached from subtree rooted with u. So we
 * maintain an additional array low[] which is defined as follows.
 * 
 * low[u] = min(disc[u], disc[w]) where w is an ancestor of u and there is a back edge from some
 * descendant of u to w.
 *
 * 
 * References: https://en.wikipedia.org/wiki/Biconnected_component
 * http://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
 */
public class ArticulationPoint<T> {

	private int time;

	public Set<Vertex<T>> findarticulationPoints(Graph<T> graph) {
		time = 0;
		Set<Vertex<T>> visited = new HashSet<>();
		Set<Vertex<T>> articulationPoints = new HashSet<>();
		Vertex<T> startVertex = graph.getAllVertex().iterator().next();

		Map<Vertex<T>, Integer> visitedTime = new HashMap<>();
		Map<Vertex<T>, Integer> lowTime = new HashMap<>();
		Map<Vertex<T>, Vertex<T>> parent = new HashMap<>();

		DFS(visited, articulationPoints, startVertex, visitedTime, lowTime, parent);
		return articulationPoints;
	}

	/**
	 * 
	 * @param visited
	 *            - keeps track of visited vertices
	 * @param articulationPoints
	 *            - stores articulation points
	 * @param vertex
	 *            - the vertex to be visited next
	 * @param visitedTime
	 *            - discovery time of vertex
	 * @param lowTime
	 *            - earliest visited vertex time
	 * @param parent
	 *            - mapping of v to u(where v is parent of u)
	 */
	private void DFS(Set<Vertex<T>> visited, Set<Vertex<T>> articulationPoints, Vertex<T> vertex,
			Map<Vertex<T>, Integer> visitedTime, Map<Vertex<T>, Integer> lowTime, Map<Vertex<T>, Vertex<T>> parent) {
		visited.add(vertex);

		// initialize visited time and low time to 0 the very first time
		visitedTime.put(vertex, time);
		lowTime.put(vertex, time);

		time++;
		int childCount = 0;
		boolean isArticulationPoint = false;
		for (Vertex<T> adj : vertex.getAdjacentVertexes()) {
			// if adj is same as parent then just ignore this vertex.
			if (adj.equals(parent.get(vertex))) {
				continue;
			}
			// if adj has not been visited then visit it.
			if (!visited.contains(adj)) {
				parent.put(adj, vertex);
				childCount++;
				DFS(visited, articulationPoints, adj, visitedTime, lowTime, parent);

				if (visitedTime.get(vertex) <= lowTime.get(adj)) {
					isArticulationPoint = true;
				} else {
					// below operation basically does lowTime[vertex] = min(lowTime[vertex], lowTime[adj]);
					lowTime.compute(vertex, (currentVertex, time) -> Math.min(time, lowTime.get(adj)));
				}

			} else { // if adj is already visited see if you can get better low time.
				// below operation basically does lowTime[vertex] = min(lowTime[vertex], visitedTime[adj]);
				lowTime.compute(vertex, (currentVertex, time) -> Math.min(time, visitedTime.get(adj)));
			}
		}

		// checks if either condition 1 or condition 2 meets). If yes then it is articulation point.
		if ((parent.get(vertex) == null && childCount >= 2) || parent.get(vertex) != null && isArticulationPoint) {
			articulationPoints.add(vertex);
		}

	}

	public static void main(String args[]) {
		Graph<Integer> graph = new Graph<>(false);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(4, 5);
		graph.addEdge(5, 6);
		graph.addEdge(6, 7);
		graph.addEdge(7, 5);
		graph.addEdge(6, 8);

		// bigger example
		/*
		 * graph.addEdge(0, 1); graph.addEdge(0, 2); graph.addEdge(0, 3); graph.addEdge(0, 4);
		 * graph.addEdge(4, 2); graph.addEdge(3, 5); graph.addEdge(4, 6); graph.addEdge(6, 3);
		 * graph.addEdge(6, 7); graph.addEdge(6, 8); graph.addEdge(7, 9); graph.addEdge(9, 10);
		 * graph.addEdge(8, 10);
		 */

		ArticulationPoint<Integer> ap = new ArticulationPoint<Integer>();
		Set<Vertex<Integer>> aPoints = ap.findarticulationPoints(graph);
		aPoints.forEach(point -> System.out.println(point));
	}

}
