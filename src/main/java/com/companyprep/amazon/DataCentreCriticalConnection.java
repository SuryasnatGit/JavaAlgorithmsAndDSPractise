package com.companyprep.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a data center with n servers from 1 to n. To make the data center running, all servers must be connected, that
 * means there exists at least one path between any pair of servers. Now we know there could be some critical
 * connections broken which brings down the whole data center. You need to write a program to find out all these broken
 * critical connections. A server connection is a critical connection which when removed will make the whole data center
 * disconnected. Write a method to output all critical connections.
 * 
 * Input: serversNum, the number of servers in the data center.
 * 
 * connectionsNum, the number of connections between the servers.
 * 
 * connections, a list of pairs representing the connections between two severs.
 * 
 * Output:
 * 
 * Return a list of integer pairs representing the critical connections. Output an empty array if there are no critical
 * connections.
 * 
 * Example :
 * 
 * Input: serversNum = 4 connectionsNum = 4 connections = [[1, 2], [1, 3], [3, 2], [3, 4]]
 * 
 * Output: [[3,4]]
 * 
 * Explanation: There are one critical connections: 1. Between server 3 and 4 If the connection [3, 4] breaks, then the
 * network will be disconnected since servers 3 and 4 cannot communicate with the rest of the network. Remaining three
 * connections are not critical.
 *
 */
public class DataCentreCriticalConnection {

	List<List<Integer>> result = new ArrayList<>();
	int rank = 0;

	private List<Integer>[] buildGraph(int n, List<List<Integer>> connections) {
		List<Integer>[] graph = new ArrayList[n];
		for (List<Integer> connection : connections) {
			int u = connection.get(0);
			int v = connection.get(1);
			if (graph[u] == null) {
				graph[u] = new ArrayList<>();
			}
			if (graph[v] == null) {
				graph[v] = new ArrayList<>();
			}
			graph[u].add(v);
			graph[v].add(u);
		}
		return graph;
	}

	private void dfs(int u, int parent, int[] low, int[] discover, List<Integer>[] graph) {
		if (discover[u] != -1)
			return;

		discover[u] = low[u] = rank++;
		for (Integer neighbour : graph[u]) {
			if (neighbour == parent)
				continue;
			dfs(neighbour, u, low, discover, graph);

			if (discover[u] < low[neighbour]) {
				result.add(Arrays.asList(u, neighbour));
			} else {
				low[u] = Math.min(low[u], low[neighbour]);
			}
		}
	}

	// All the connections within a CYCLE is NOT critical path.
	// Apply Tarjan's algorithm
	// Time: O(n + e) - vertices and edges
	// Space: O(n)
	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		List<Integer>[] graph = buildGraph(n, connections);
		int[] low = new int[n];
		int[] discover = new int[n];
		Arrays.fill(low, -1);
		Arrays.fill(discover, -1);

		dfs(0, -1, low, discover, graph);
		return result;
	}

	// Solution 2
	int timer;

	List<List<Integer>> g;

	boolean[] visited;
	/*
	 * This map stores the time when the current node is visited
	 */
	int[] tin;
	int[] low;

	void dfs(int u, int parent, List<List<Integer>> res) {
		visited[u] = true;

		// Put the current timer.
		tin[u] = timer;
		// Low is the time of entry to start with
		low[u] = timer;

		timer++;

		/*
		 * Go through all the neighbors
		 */
		for (int to : g.get(u)) {
			// If it is parent, nothing to be done
			if (to == parent)
				continue;

			/*
			 * If the neighbor was already visited get the minimum of the neighbor entry time or the current low of the
			 * node.
			 */
			if (visited[to]) {
				low[u] = Math.min(low[u], tin[to]);
			} else {
				// Else do the DFS
				dfs(to, u, res);
				/*
				 * Normal edge scenario, take the minimum of low of the parent and the child.
				 */
				low[u] = Math.min(low[u], low[to]);

				/*
				 * If low of the child node is less than time of entry of current node, then there is a bridge.
				 */
				if (low[to] > tin[u]) {
					// List<Integer> l = new ArrayList<>();
					List<Integer> l = Arrays.asList(new Integer[] { u, to });
					res.add(l);
				}
			}
		}

	}

	public void findBridges(List<List<Integer>> res) {
		timer = 0;
		for (int i = 0; i < g.size(); i++) {
			if (!visited[i])
				dfs(i, -1, res);
		}
	}

	public List<List<Integer>> criticalConnections1(int n, List<List<Integer>> connections) {

		g = new ArrayList<>();

		visited = new boolean[n];
		/*
		 * This map stores the time when the current node is visited
		 */
		tin = new int[n];
		low = new int[n];

		Arrays.fill(visited, false);
		Arrays.fill(tin, n);
		Arrays.fill(low, n);

		for (int i = 0; i < n; i++) {
			g.add(new ArrayList<>());
		}
		for (List<Integer> l : connections) {
			g.get(l.get(0)).add(l.get(1));
			g.get(l.get(1)).add(l.get(0));
		}

		List<List<Integer>> res = new ArrayList<>();
		findBridges(res);

		return res;

	}
}
