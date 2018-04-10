package com.algo.ds.graph;

/**
 * Given a connected and undirected graph, a spanning tree of that graph is a subgraph that is a
 * tree and connects all the vertices together. A single graph can have many different spanning
 * trees. A minimum product spanning tree for a weighted, connected and undirected graph is a
 * spanning tree with weight product less than or equal to the weight product of every other
 * spanning tree. The weight product of a spanning tree is the product of weights corresponding to
 * each edge of the spanning tree. All weights of the given graph will be positive for simplicity.
 * 
 * This problem can be solved using standard minimum spanning tree algorithms like krushkal and
 * prim’s algorithm, but we need to modify our graph to use these algorithms. Minimum spanning tree
 * algorithms tries to minimize total sum of weights, here we need to minimize total product of
 * weights. We can use property of logarithms to overcome this problem. As we know, log(w1* w2 * w3
 * * …. * wN) = log(w1) + log(w2) + log(w3) ….. + log(wN) We can replace each weight of graph by its
 * log value, then we apply any minimum spanning tree algorithm which will try to minimize sum of
 * log(wi) which in-turn minimizes weight product. In below code first we have constructed the log
 * graph from given input graph, then that graph is given as input to prim’s MST algorithm, which
 * will minimize the total sum of weights of tree. Since weight of modified graph are logarithms of
 * actual input graph, we actually minimize the product of weights of spanning tree.
 * 
 * @author surya
 *
 */
public class MinimumProductSpanningTree {

	private static final int SIZE = 5;

	public void mst(int[][] graph) {
		double[][] logGraph = log(graph);
		PrimMST primMST = new PrimMST();
		// primMST.primMST_adjacentMatrix(logGraph); // commenting this as need to change the graph type to
		// int.
	}

	/**
	 * converts a normal graph to log graph
	 * 
	 * @param graph
	 * @return
	 */
	private double[][] log(int[][] graph) {
		double[][] logGraph = new double[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (graph[i][j] > 0)
					logGraph[i][j] = Math.log(graph[i][j]);
				else
					logGraph[i][j] = 0;
			}
		}
		return logGraph;
	}

}
