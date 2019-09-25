package com.companyprep.amazon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Minimum Spanning Tree II
 * 
 * There's an undirected connected graph with n nodes labeled 1..n. But some of the edges has been broken disconnecting
 * the graph. Find the minimum cost to repair the edges so that all the nodes are once again accessible from each other.
 * 
 * Input:
 * 
 * n, an int representing the total number of nodes.
 * 
 * edges, a list of integer pair representing the nodes connected by an edge.
 * 
 * edgesToRepair, a list where each element is a triplet representing the pair of nodes between which an edge is
 * currently broken and the cost of repearing that edge, respectively (e.g. [1, 2, 12] means to repear an edge between
 * nodes 1 and 2, the cost would be 12).
 * 
 * Example 1:
 * 
 * Input:
 * 
 * n = 5, edges = [[1, 2], [2, 3], [3, 4], [4, 5], [1, 5]], edgesToRepair = [[1, 2, 12], [3, 4, 30], [1, 5, 8]]
 * 
 * Output: 20
 * 
 * Explanation:
 * 
 * There are 3 connected components due to broken edges: [1], [2, 3] and [4, 5]. We can connect these components into a
 * single component by repearing the edges between nodes 1 and 2, and nodes 1 and 5 at a minimum cost 12 + 8 = 20.
 * 
 * Example 2:
 * 
 * Input:
 * 
 * n = 6, edges = [[1, 2], [2, 3], [4, 5], [3, 5], [1, 6], [2, 4]], edgesToRepair = [[1, 6, 410], [2, 4, 800]]
 * 
 * Output: 410
 * 
 * Example 3:
 * 
 * Input:
 * 
 * n = 6, edges = [[1, 2], [2, 3], [4, 5], [5, 6], [1, 5], [2, 4], [3, 4]], edgesToRepair = [[1, 5, 110], [2, 4, 84],
 * [3, 4, 79]]
 * 
 * Output: 79
 * 
 * Category : Hard
 */
public class MinCostToRepairEdges {

	int[] parents;

	/**
	 * SOlution 1 - Using union find. Time Complexity = O(V+E).
	 * 
	 * @param n
	 * @param edges
	 * @param edgesToRepair
	 * @return
	 */
	public int minCostToConnect(int n, int[][] edges, int[][] edgesToRepair) {
		int connect = n, totalCost = 0;
		parents = new int[n + 1];
		Set<String> set = new HashSet<>();

		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}

		for (int[] edge : edgesToRepair) {
			StringBuilder sb = new StringBuilder();
			sb.append(edge[0]).append("#").append(edge[1]);
			set.add(sb.toString());
		}

		for (int[] edge : edges) {
			StringBuilder sb = new StringBuilder();
			sb.append(edge[0]).append("#").append(edge[1]);
			if (!set.contains(sb.toString())) {
				this.union(edge[0], edge[1]);
				connect--;
			}
		}

		Arrays.sort(edgesToRepair, new Comparator<int[]>() {
			@Override
			public int compare(int[] arr1, int[] arr2) {
				return arr1[2] - arr2[2];
			}
		});

		for (int[] edge : edgesToRepair) {
			if (this.union(edge[0], edge[1])) {
				totalCost += edge[2];
				connect--;
			}
			if (connect == 1) {
				return totalCost;
			}
		}

		return connect == 1 ? totalCost : -1;
	}

	private boolean union(int x, int y) {
		int setX = find(x);
		int setY = find(y);
		if (setX != setY) {
			parents[setY] = setX;
			return true;
		}
		return false;
	}

	private int find(int num) {
		if (parents[num] != num) {
			parents[num] = find(parents[num]);
		}
		return parents[num];
	}

	// Solution 2 - Using Prim MST
	public int minCost(int n, int[][] edges, int[][] newEdges) {
		String to = "->";
		int res = 0;
		Map<String, Integer> graph = new HashMap<>();
		for (int[] edge : edges) {
			graph.put(edge[0] + to + edge[1], 0);
			graph.put(edge[1] + to + edge[0], 0);
		}
		for (int[] edge : newEdges) {
			graph.put(edge[0] + to + edge[1], edge[2]);
			graph.put(edge[1] + to + edge[0], edge[2]);
		}
		int[] dist = new int[n + 1];
		boolean[] visited = new boolean[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		visited[1] = true;
		for (int i = 1; i < dist.length; i++) {
			if (graph.containsKey(1 + to + i)) {
				dist[i] = Math.min(dist[i], graph.get(1 + to + i));
			}
			if (graph.containsKey(i + to + 1)) {
				dist[i] = Math.min(dist[i], graph.get(i + to + 1));
			}
		}
		for (int cnt = 0; cnt < n - 1; cnt++) {
			int[] next = getMin(dist, visited);
			int p = next[0];
			int v = next[1];
			if (p != -1) {
				visited[p] = true;
				dist[p] = v;
				for (int i = 1; i < dist.length; i++) {
					String key = p + to + i;
					if (!visited[i] && graph.containsKey(key) && graph.get(key) < dist[i]) {
						dist[i] = graph.get(key);
					}
				}
			}
		}
		for (int i = 1; i < dist.length; i++) {
			if (dist[i] == Integer.MAX_VALUE)
				return -1;
			res += dist[i];
		}
		return res;
	}

	private int minCostAllStartNodes(int n, int[][] edges, int[][] newEdges) {
		String to = "->";
		int res = 0;
		Map<String, Integer> graph = new HashMap<>();
		for (int[] edge : edges) {
			graph.put(edge[0] + to + edge[1], 0);
			graph.put(edge[1] + to + edge[0], 0);
		}
		for (int[] edge : newEdges) {
			graph.put(edge[0] + to + edge[1], edge[2]);
			graph.put(edge[1] + to + edge[0], edge[2]);
		}
		int[] dist = new int[n + 1];
		boolean[] visited = new boolean[n + 1];
		for (int j = 1; j <= n; j++) {
			dist = new int[n + 1];
			visited = new boolean[n + 1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[j] = 0;
			visited[j] = true;
			for (int i = 1; i < dist.length; i++) {
				if (graph.containsKey(j + to + i)) {
					dist[i] = Math.min(dist[i], graph.get(j + to + i));
				}
				if (graph.containsKey(i + to + j)) {
					dist[i] = Math.min(dist[i], graph.get(i + to + j));
				}
			}
			for (int cnt = 0; cnt < n - 1; cnt++) {
				int[] next = getMin(dist, visited);
				int p = next[0];
				int v = next[1];
				if (p != -1) {
					visited[p] = true;
					dist[p] = v;
					for (int i = 1; i < dist.length; i++) {
						String key = p + to + i;
						if (!visited[i] && graph.containsKey(key) && graph.get(key) < dist[i]) {
							dist[i] = graph.get(key);
						}
					}
				}
			}
			System.out.println("Dist Array with Start Node: " + j + ", " + Arrays.toString(dist));
		}
		for (int i = 1; i <= n; i++) {
			if (dist[i] == Integer.MAX_VALUE)
				return -1;
			res += dist[i];
		}
		return res;
	}

	private int[] getMin(int[] dist, boolean[] visited) {
		int[] res = new int[2];
		res[0] = -1;
		res[1] = Integer.MAX_VALUE;
		for (int i = 1; i < dist.length; i++) {
			if (!visited[i]) {
				if (dist[i] < res[1]) {
					res[0] = i;
					res[1] = dist[i];
				}
			}
		}
		return res;
	}

}
