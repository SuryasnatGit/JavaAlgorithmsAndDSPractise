package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * It is known that there is a function that inputs a user ID and can return all the friends (degree 1 friends) of the
 * user, sorted by the friend ID from small to large. It is required to implement a function to output and return the
 * friends of all friends of a user (degree 2 friends), and degree 3 friends
 *
 * Give you a function that can return the current friends with the specified ID, and then use this function to find out
 * which IDs have the most mutual friends other than the two IDs (input) given to you (two levels apart). This is mine.
 * ID and friends' Friend has the most friends in common, because friends are two-way, so just judge how many of my
 * friends are also friends of people on the third level, that is, in-degree, but it is also possible, A-B-C. D is not a
 * friend of A, but D is a friend of C. This is also indegree.
 *
 * My idea: bfs then calculates the in-degree of each layer in the second layer. The one with the most in-degree is the
 * one with the most mutual friends.
 * 
 * https://discuss.leetcode.com/topic/54969/fb-08-2016-phone-interview-find-2nd-degree-connections/3
 * 
 * Find 2nd degree connections ( friends’ friends), output these 2nd degree connections ranked by number of common
 * friends (i.e 1st degree connections) with you, (example: if 2nd degree connection A has 10 common friends (1st degree
 * connections) with you but 2nd degree connection B has 8 common friends (1st degree connections)with you, then A
 * should be ranked first) Input is your connection graph represented by undirected graph nodes, output is list of 2nd
 * degree connections represented by graph nodes
 * 
 * public List<UndirectedGraphNode> findSecDegreeConnections(UndirectedGraphNode myself){
 * 
 * }
 * 
 * you can just apply BFS for two layers and for the node of second layer(2nd degree friends), count the number of 1st
 * degree friends who expand to it. Then just sort the 2nd layer.
 * 
 * Category : Hard
 * 
 * TODO : to check further and understand
 */
public class FriendsFriends {
	public static void main(String[] args) {

	}

	public void findSecDegreeConnections(UndirectedGraphNode myself) {
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
		queue.offer(myself);
		visited.add(myself);

		Set<UndirectedGraphNode> myFriends = new HashSet<UndirectedGraphNode>();
		for (UndirectedGraphNode next : myself.friends) {
			myFriends.add(next);
		}

		int level = 0;

		while (!queue.isEmpty() && level < 2) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				UndirectedGraphNode now = queue.poll();
				for (UndirectedGraphNode next : now.friends) {
					if (!visited.contains(next)) {
						queue.offer(next);
						visited.add(next);
					}
				}
			}

			level++;
		}

		Map<UndirectedGraphNode, Integer> map = new HashMap<UndirectedGraphNode, Integer>();
		for (UndirectedGraphNode fof : queue) {
			int count = 0;
			for (UndirectedGraphNode fofof : fof.friends) {
				if (myFriends.contains(fofof)) {
					count++;
				}
			}
			map.put(fof, count); // This person has how many common friend with me
		}

		Map<Integer, Set<UndirectedGraphNode>> res = new HashMap<Integer, Set<UndirectedGraphNode>>();
		for (Map.Entry<UndirectedGraphNode, Integer> entry : map.entrySet()) {
			UndirectedGraphNode node = entry.getKey();
			int count = entry.getValue();

			if (!res.containsKey(count)) {
				res.put(count, new HashSet<UndirectedGraphNode>());
			}
			res.get(count).add(node);
		}

		// Sort res and find the persons which share most common friends
	}

	class UndirectedGraphNode {
		String name;
		List<UndirectedGraphNode> friends;

		UndirectedGraphNode(String x) {
			name = x;
			friends = new ArrayList<UndirectedGraphNode>();
		}
	}
}
