package com.algo.ds.queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * https://www.techiedelight.com/min-throws-required-to-win-snake-and-ladder-game/
 */
public class MinThrowsToWinSnakeLadderGame {

	/**
	 * 
	 * @param snakes
	 *            <sorce, dest>
	 * @param ladders
	 *            <source, dest>
	 * @param N
	 */
	public void numberOfThrows(Map<Integer, Integer> snakes, Map<Integer, Integer> ladders, int N) {

		List<Edge> edges = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			// for each node there can be possible 1 to 6 moves depending on the dice
			for (int j = 1; j <= 6 && i + j <= N; j++) {
				int source = i;

				int dest;

				int snakeDist = snakes.get(i + j) != null ? snakes.get(i + j) : 0;
				int ladderDist = ladders.get(i + j) != null ? ladders.get(i + j) : 0;

				if (snakeDist != 0 || ladderDist != 0)
					dest = snakeDist + ladderDist;
				else
					dest = i + j;

				// add edge from source to dest
				edges.add(new Edge(source, dest));
			}
		}

		// construct the graph
		Graph g = new Graph(edges, N);

		bfs(g, 0, N);
	}

	private void bfs(Graph g, int start, int size) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(start, 0));

		boolean[] visited = new boolean[size + 1];
		visited[start] = true;

		while (!queue.isEmpty()) {
			Node node = queue.poll();

			if (node.vertex == size) { // end reached, print dist
				System.out.println("min dist " + node.minDist);
				break;
			}

			for (int u : g.adjList.get(node.vertex)) {
				if (!visited[u]) {
					visited[u] = true;
					Node n = new Node(u, node.minDist + 1);
					queue.add(n);
				}
			}
		}
	}

	public static void main(String[] args) {
		MinThrowsToWinSnakeLadderGame min = new MinThrowsToWinSnakeLadderGame();

		// snakes and ladders are represented using a map.
		Map<Integer, Integer> ladder = new HashMap();
		Map<Integer, Integer> snake = new HashMap();

		// insert ladders into the map
		ladder.put(1, 38);
		ladder.put(4, 14);
		ladder.put(9, 31);
		ladder.put(21, 42);
		ladder.put(28, 84);
		ladder.put(51, 67);
		ladder.put(72, 91);
		ladder.put(80, 99);

		// insert snakes into the map
		snake.put(17, 7);
		snake.put(54, 34);
		snake.put(62, 19);
		snake.put(64, 60);
		snake.put(87, 36);
		snake.put(93, 73);
		snake.put(95, 75);
		snake.put(98, 79);

		min.numberOfThrows(snake, ladder, 10 * 10);
	}
}

class Edge {
	int source;
	int dest;

	public Edge(int src, int dest) {
		this.source = src;
		this.dest = dest;
	}
}

class Node {
	int vertex;
	int minDist;

	public Node(int vert, int dist) {
		this.vertex = vert;
		this.minDist = dist;
	}
}

class Graph {
	List<List<Integer>> adjList = null;

	public Graph(List<Edge> edges, int N) {

		adjList = new ArrayList<>(N);

		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		for (int i = 0; i < edges.size(); i++) {
			int src = edges.get(i).source;
			int dest = edges.get(i).dest;

			adjList.get(src).add(dest);// directed
		}
	}
}
