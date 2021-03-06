package com.algo.ds.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Video link - https://youtu.be/ID00PMy0-vE
 * 
 * Category - Hard
 * 
 * <br/>
 * Usage - Kruskal algorithm for MST, finding cycle in undirected graph
 */
public class DisjointSet {

	/**
	 * Union Find Algorithm - Set 1. Note that the implementation of union() and find() is naive and
	 * takes O(n) time in worst case
	 * 
	 */
	public boolean isCycle(Graph<Integer> graph) {
		// initialize subsets to -1 making each element as individual subset
		int numVertex = graph.getAllVertex().size();
		int numEdge = graph.getAllEdges().size();
		int[] subsets = new int[numVertex];
		for (int i = 0; i < numVertex; i++)
			subsets[i] = -1;

		// scan through all edges of the graph. for each edge check if the source and destination vertex
		// belong to the same subset. if not then do a union. if yes then cycle is found
		for (int i = 0; i < numEdge; i++) {
			int source = find(subsets, graph.getAllEdges().get(i).getVertex1().id);
			int destination = find(subsets, graph.getAllEdges().get(i).getVertex2().id);
			if (source == destination)
				return true;
			union(subsets, source, destination);
		}
		return false;
	}

	/**
	 * function to find subset of element v
	 * 
	 * @param subsets
	 * @param v
	 * @return
	 */
	private int find(int[] subsets, int v) {
		if (subsets[v] == -1)
			return v;
		return find(subsets, subsets[v]);
	}

	/**
	 * function to do the union of 2 subsets
	 * 
	 * @param subsets
	 * @param source
	 * @param destination
	 */
	private void union(int[] subsets, int source, int destination) {
		int sx = find(subsets, source);
		int sy = find(subsets, destination);
		subsets[sx] = sy;
	}

	/**
	 * Union Find algorithm - Set 2.
	 * 
	 * Disjoint sets using path compression and union by rank Supports 3 operations<br/>
	 * 1) makeSet - creates a singleton set containing new element x and returns the position <br/>
	 * 2) union - merges the sets<br/>
	 * 3) findSet - returns an element of the set which is an element that identifies the set.
	 * 
	 * <br/>
	 * union by rank - make the node who has higher rank the parent and the node which has lower rank
	 * the child.
	 * 
	 * For m operations and total n elements time complexity is O(m*f(n)) where f(n) is very slowly
	 * growing function. For most cases f(n) <= 4 so effectively total time will be O(m). Proof in
	 * Coreman book.
	 */
	private Map<Long, Node> map = new HashMap<>();

	class Node {
		int rank; // depth of tree
		long data; // actual data
		Node parent; // parent node
	}

	/**
	 * Create a set with only one element.
	 */
	public void makeSet(long data) {
		Node node = new Node();
		node.data = data;
		node.parent = node;
		node.rank = 0;
		map.put(data, node);
	}

	/**
	 * Combines two sets together to one. Does union by rank
	 *
	 * @return true if data1 and data2 are in different set before union else false.
	 */
	public boolean union(long data1, long data2) {
		Node node1 = map.get(data1);
		Node node2 = map.get(data2);

		Node parent1 = findSet(node1);
		Node parent2 = findSet(node2);

		// if they are part of same set do nothing
		if (parent1.data == parent2.data) {
			return false;
		}

		// else whoever's rank is higher becomes parent of other
		if (parent1.rank >= parent2.rank) {
			// increment rank only if both sets have same rank
			parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
			parent2.parent = parent1;
		} else {
			parent1.parent = parent2;
		}
		return true;
	}

	/**
	 * Finds the representative of this set
	 */
	public long findSet(long data) {
		return findSet(map.get(data)).data;
	}

	/**
	 * Find the representative recursively and does path compression as well.
	 */
	private Node findSet(Node node) {
		Node parent = node.parent;
		if (parent == node) {
			return parent;
		}
		node.parent = findSet(node.parent);
		return node.parent;
	}

	public static void main(String args[]) {
		DisjointSet ds = new DisjointSet();
		ds.makeSet(1);
		ds.makeSet(2);
		ds.makeSet(3);
		ds.makeSet(4);
		ds.makeSet(5);
		ds.makeSet(6);
		ds.makeSet(7);

		ds.union(1, 2);
		ds.union(2, 3);
		ds.union(4, 5);
		ds.union(6, 7);
		ds.union(5, 6);
		ds.union(3, 7);

		System.out.println(ds.findSet(1));
		System.out.println(ds.findSet(2));
		System.out.println(ds.findSet(3));
		System.out.println(ds.findSet(4));
		System.out.println(ds.findSet(5));
		System.out.println(ds.findSet(6));
		System.out.println(ds.findSet(7));
	}
}