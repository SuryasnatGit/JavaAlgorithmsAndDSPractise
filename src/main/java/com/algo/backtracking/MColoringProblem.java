package com.algo.backtracking;

/**
 * Given an undirected graph and a number m, determine if the graph can be colored with at most m
 * colors such that no two adjacent vertices of the graph are colored with same color. Here coloring
 * of a graph means assignment of colors to all vertices. Input: 1) A 2D array graph[V][V] where V
 * is the number of vertices in graph and graph[V][V] is adjacency matrix representation of the
 * graph. A value graph[i][j] is 1 if there is a direct edge from i to j, otherwise graph[i][j] is
 * 0. 2) An integer m which is maximum number of colors that can be used.
 * 
 * Output: An array color[V] that should have numbers from 1 to m. color[i] should represent the
 * color assigned to the ith vertex. The code should also return false if the graph cannot be
 * colored with m colors.
 * 
 * The idea is to assign colors one by one to different vertices, starting from the vertex 0. Before
 * assigning a color, we check for safety by considering already assigned colors to the adjacent
 * vertices. If we find a color assignment which is safe, we mark the color assignment as part of
 * solution. If we do not a find color due to clashes then we backtrack and return false.
 * 
 * 
 * @author surya
 *
 */
public class MColoringProblem {

	private int V = 4;// number of vertex in graph
	private int[] color = new int[V];

	/**
	 * 
	 * @param graph
	 *            adjacency matrix
	 * @param m
	 *            number of colors
	 * @return
	 */
	public boolean mColor(int[][] graph, int m) {
		// initialize color to default value
		for (int i = 0; i < V; i++)
			color[i] = 0;

		// start with first vertex
		if (!mColorUtil(graph, color, m, 0)) {
			System.out.println("same adjacent color node exist");
			return false;
		}
		printGraph(color);
		return true;
	}

	/**
	 * 
	 * @param graph
	 *            adjacency matrix
	 * @param color
	 *            color array of size V
	 * @param m
	 *            number of colors
	 * @param v
	 *            vertex number
	 * @return
	 */
	private boolean mColorUtil(int[][] graph, int[] color, int m, int v) {
		// base case. if all vertex have been covered
		if (v == V)
			return true;

		// consider this vertex v and try different colors
		for (int c = 1; c <= m; c++) {
			if (isSafe(graph, color, c, v)) {
				color[v] = c;

				// recur to assign colors to rest of the vertices
				if (mColorUtil(graph, color, m, v + 1))
					return true;

				// if assigning color c does not lead to a solution, then backtrack the color which was set earlier.
				color[v] = 0;
			}
		}
		// if no color can be assigned to this vertex then return false
		return false;
	}

	/**
	 * utility function to check if the current color assignment is safe for v
	 * 
	 * @param graph
	 *            adjacency matrix
	 * @param color
	 *            color array
	 * @param c
	 *            color id
	 * @param v
	 *            vertex id
	 * @return
	 */
	private boolean isSafe(int[][] graph, int[] color, int c, int v) {
		for (int i = 0; i < V; i++) {
			if (graph[v][i] == 1 && color[i] == c) // if color already exists
				return false;
		}
		return true;
	}

	private void printGraph(int[] sol) {
		for (int i = 0; i < 4; i++) {
			System.out.print(sol[i] + " ");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		MColoringProblem color = new MColoringProblem();
		int graph[][] = { { 0, 1, 1, 1 }, { 1, 0, 1, 0 }, { 1, 1, 0, 1 }, { 1, 0, 1, 0 }, };
		System.out.println(color.mColor(graph, 3));
	}
}
