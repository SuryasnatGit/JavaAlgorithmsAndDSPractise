package com.algo.ds.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://www.techiedelight.com/kruskals-algorithm-for-finding-minimum-spanning-tree/
 * <p>
 * Find minimum spanning tree usinig Kruskals algorithm
 * <p>
 * Time Complexity: O(ElogE) or O(ElogV). Sorting of edges takes O(ELogE) time. After sorting, we iterate through all
 * edges and apply find-union algorithm. The find and union operations can take atmost O(LogV) time. So overall
 * complexity is O(ELogE + ELogV) time. The value of E can be atmost O(V2), so O(LogV) are O(LogE) same. Therefore,
 * overall time complexity is O(ElogE) or O(ElogV)
 * 
 * Space complexity - O(E + V)
 * <p>
 * References https://en.wikipedia.org/wiki/Kruskal%27s_algorithm
 * 
 * Category - Hard
 */

public class KruskalMST {
	/**
	 * Comparator to sort edges by weight in non decreasing order
	 */
	public class EdgeComparator implements Comparator<Edge<Integer>> {
		@Override
		public int compare(Edge<Integer> edge1, Edge<Integer> edge2) {
			if (edge1.getWeight() <= edge2.getWeight()) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	public List<Edge<Integer>> getMST(Graph<Integer> graph) {
		List<Edge<Integer>> allEdges = graph.getAllEdges();
		EdgeComparator edgeComparator = new EdgeComparator();

		// sort all edges in non decreasing order. complexity - O(E log E)
		Collections.sort(allEdges, edgeComparator);
		DisjointSet disjointSet = new DisjointSet();

		// create as many disjoint sets as the total vertices
		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			disjointSet.makeSet(vertex.getId());
		}

		List<Edge<Integer>> resultEdge = new ArrayList<Edge<Integer>>();

		// time complexity - O(E)
		for (Edge<Integer> edge : allEdges) {
			// get the sets of two vertices of the edge
			long root1 = disjointSet.findSet(edge.getVertex1().getId());
			long root2 = disjointSet.findSet(edge.getVertex2().getId());

			// check if the vertices are in same set or different set
			// if verties are in same set then ignore the edge
			if (root1 == root2) {
				continue;
			} else {
				// if vertices are in different set then add the edge to result and union these two sets into one
				resultEdge.add(edge);
				disjointSet.union(edge.getVertex1().getId(), edge.getVertex2().getId());
			}

		}
		return resultEdge;
	}

	public static void main(String args[]) {
		Graph<Integer> graph = new Graph<Integer>(false);
		graph.addEdge(1, 2, 4);
		graph.addEdge(1, 3, 1);
		graph.addEdge(2, 5, 1);
		graph.addEdge(2, 6, 3);
		graph.addEdge(2, 4, 2);
		graph.addEdge(6, 5, 2);
		graph.addEdge(6, 4, 3);
		graph.addEdge(4, 7, 2);
		graph.addEdge(3, 4, 5);
		graph.addEdge(3, 7, 8);
		KruskalMST mst = new KruskalMST();
		List<Edge<Integer>> result = mst.getMST(graph);
		for (Edge<Integer> edge : result) {
			System.out.println(edge.getVertex1() + " " + edge.getVertex2());
		}
	}
}