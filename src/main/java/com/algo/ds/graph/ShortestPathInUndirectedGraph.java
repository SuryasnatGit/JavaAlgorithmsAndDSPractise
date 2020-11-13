package com.algo.ds.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ShortestPathInUndirectedGraph {

	int shortestPathBFS(Map<Node, Set<Node>> map, Node start, Node end) {
		if (start == end) {
			return 0;
		}

		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(start);

		int level = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				Node node = queue.poll();

				if (node == end) {
					return level + 1;
				}

				if (map.containsKey(node)) {
					for (Node child : map.get(node)) {
						map.get(child).remove(node);
						queue.offer(child);
					}

					map.remove(node);
				}
			}

			level++;
		}

		return -1;
	}

	int res = Integer.MAX_VALUE;

	int shortestPathDFS(Map<Node, Set<Node>> map, Node start, Node end) {
		if (start == end) {
			return 0;
		}

		Set<Node> visited = new HashSet<Node>();
		helper(map, start, end, visited, 1);

		return res;
	}

	void helper(Map<Node, Set<Node>> map, Node start, Node end, Set<Node> visited, int level) {
		if (start == end) {
			res = Math.min(res, level);
			return;
		}

		if (map.containsKey(start)) {
			for (Node child : map.get(start)) {
				if (!visited.contains(child)) {
					visited.add(child);
					helper(map, child, end, visited, level + 1);
					visited.remove(child);
				}
			}
		}
	}
}

class Node {

}
