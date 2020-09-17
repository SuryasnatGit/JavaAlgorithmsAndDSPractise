package com.algo.ds.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * Category : Medium
 * 
 * Tags : BFS, DFS
 */
public class CourseSchedule {

	/*
	 * Problem 1 : There are a total of n courses you have to take, labeled from 0 to n - 1. Some courses may have
	 * prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
	 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should
	 * take to finish all courses. There may be multiple correct orders, you just need to return one of them. If it is
	 * impossible to finish all courses, return an empty array.
	 * 
	 * For example:
	 * 
	 * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the
	 * correct course order is [0,1]
	 * 
	 * 4, [[1,0],[2,0],[3,1],[3,2]] There are a total of 4 courses to take. To take course 3 you should have finished
	 * both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course
	 * order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
	 * 
	 * Note: The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about
	 * how a graph is represented.
	 * 
	 * Hints: This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no
	 * topological ordering exists and therefore it will be impossible to take all courses. Topological sort could also
	 * be done via BFS.
	 * 
	 * Time complexity O(n) Space complexity O(n)
	 *
	 */

	/**
	 * The first node in the topological ordering will be the node that doesn't have any incoming edges. Essentially,
	 * any node that has an in-degree of 0 can start the topologically sorted order. If there are multiple such nodes,
	 * their relative order doesn't matter and they can appear in any order.
	 * 
	 * Our current algorithm is based on this idea. We first process all the nodes/course with 0 in-degree implying no
	 * prerequisite courses required. If we remove all these courses from the graph, along with their outgoing edges, we
	 * can find out the courses/nodes that should be processed next. These would again be the nodes with 0 in-degree. We
	 * can continuously do this until all the courses have been accounted for.
	 */
	public int[] findOrderBFS(int N, int[][] P) {
		Map<Integer, Set<Integer>> courses = new HashMap<Integer, Set<Integer>>();
		Map<Integer, Integer> degrees = new HashMap<Integer, Integer>();
		int[] res = new int[N];
		int index = 0;

		for (int i = 0; i < P.length; i++) {
			int first = P[i][0];
			int second = P[i][1]; // first relies on second, second is the prerequisite

			if (!courses.containsKey(second)) {
				courses.put(second, new HashSet<Integer>());
			}
			courses.get(second).add(first);
			degrees.put(first, degrees.getOrDefault(first, 0) + 1);
		}

		Queue<Integer> queue = new LinkedList<Integer>();
		// Adding all vertices with 0 in-degree to the queue.
		for (int i = 0; i < N; i++) {
			if (!degrees.containsKey(i)) {
				queue.offer(i);
			}
		}

		// process until queue is empty
		while (!queue.isEmpty()) {
			int now = queue.poll();
			res[index++] = now;

			// reduce in-degree of each neighbor by 1
			if (courses.containsKey(now)) {
				for (int next : courses.get(now)) {
					degrees.put(next, degrees.get(next) - 1);

					// if in-degree of a neighbor becomes 0 add it to the queue
					if (degrees.get(next) == 0) {
						queue.offer(next);
					}
				}
			}
		}

		if (index == N) {
			return res;
		}
		return new int[] {}; // No solution!
	}

	public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
		boolean[] used = new boolean[numCourses];
		Neighbors[] graph = new Neighbors[numCourses];

		for (int i = 0; i < graph.length; i++) {
			graph[i] = new Neighbors();
		}

		for (int[] tuple : prerequisites) {
			graph[tuple[1]].neighbor.add(tuple[0]);
		}
		Stack<Integer> stack = new Stack<>();
		boolean[] dfs = new boolean[numCourses];

		for (int i = 0; i < numCourses; i++) {
			if (topSort(i, graph, used, stack, dfs)) {
				return new int[0];
			}
		}

		int[] output = new int[numCourses];
		int index = 0;
		while (!stack.isEmpty()) {
			output[index++] = stack.pop();
		}

		return output;
	}

	class Neighbors {
		List<Integer> neighbor = new ArrayList<>();
	}

	private boolean topSort(int course, Neighbors[] graph, boolean[] used, Stack<Integer> stack, boolean[] dfs) {
		if (used[course]) {
			return false;
		}
		if (dfs[course]) {
			return true;
		}
		dfs[course] = true;
		for (int adj : graph[course].neighbor) {
			if (topSort(adj, graph, used, stack, dfs)) {
				return true;
			}
		}
		dfs[course] = false;
		used[course] = true;
		stack.push(course);
		return false;
	}

	/*
	 * There are a total of n courses you have to take, labeled from 0 to n - 1. Some courses may have prerequisites,
	 * for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1] Given the total
	 * number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
	 * 
	 * For example:
	 * 
	 * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is
	 * possible.
	 * 
	 * 2, [[1,0],[0,1]] There are a total of 2 courses to take. To take course 1 you should have finished course 0, and
	 * to take course 0 you should also have finished course 1. So it is impossible.
	 * 
	 * Note: The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about
	 * how a graph is represented.
	 * 
	 * Hints: This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no
	 * topological ordering exists and therefore it will be impossible to take all courses. Topological Sort via DFS - A
	 * great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort. Topological sort
	 * could also be done via BFS.
	 */
	// Very Similar to Alien Dictionary
	public boolean canFinishGood(int N, int[][] P) {
		Map<Integer, Set<Integer>> courses = new HashMap<Integer, Set<Integer>>();
		Map<Integer, Integer> degrees = new HashMap<Integer, Integer>();

		for (int i = 0; i < P.length; i++) {
			int first = P[i][0];
			int second = P[i][1]; // first relies on second, second is the prerequisite

			if (!courses.containsKey(second)) {
				courses.put(second, new HashSet<Integer>());
			}
			courses.get(second).add(first);
			degrees.put(first, degrees.getOrDefault(first, 0) + 1);
		}

		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < N; i++) {
			if (!degrees.containsKey(i)) {
				queue.offer(i);
			}
		}

		int count = 0;

		while (!queue.isEmpty()) {
			int now = queue.poll();
			count++;

			if (courses.containsKey(now)) {
				for (int next : courses.get(now)) {
					degrees.put(next, degrees.get(next) - 1);
					if (degrees.get(next) == 0) {
						queue.offer(next);
					}
				}
			}
		}

		return count == N;
	}

	public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
		ArrayList[] graph = new ArrayList[numCourses];
		int[] degree = new int[numCourses];
		Queue<Integer> queue = new LinkedList<Integer>();
		int count = 0;

		for (int i = 0; i < numCourses; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < prerequisites.length; i++) {
			degree[prerequisites[i][1]]++;
			graph[prerequisites[i][0]].add(prerequisites[i][1]);
		}

		for (int i = 0; i < degree.length; i++) {
			if (degree[i] == 0) {
				queue.offer(i);
				count++;
			}
		}

		// BFS
		while (!queue.isEmpty()) {
			int course = queue.poll();
			for (int i = 0; i < graph[course].size(); i++) {
				int pre = (int) graph[course].get(i);
				degree[pre]--;
				if (degree[pre] == 0) {
					queue.offer(pre);
					count++;
				}
			}
		}

		if (count == numCourses) {
			return true;
		}
		return false;
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		ArrayList[] graph = new ArrayList[numCourses];
		boolean[] visited = new boolean[numCourses];

		for (int i = 0; i < numCourses; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < prerequisites.length; i++) {
			graph[prerequisites[i][1]].add(prerequisites[i][0]);// Key is prerequisite
		}

		for (int i = 0; i < numCourses; i++) {
			if (!dfs(graph, visited, i)) {
				return false;
			}
		}

		return true;
	}

	boolean dfs(ArrayList[] graph, boolean[] visited, int course) {
		if (visited[course]) { // There is circle
			return false;
		}

		visited[course] = true;

		for (int i = 0; i < graph[course].size(); i++) {
			if (!dfs(graph, visited, (int) graph[course].get(i))) {
				return false;
			}
		}

		visited[course] = false;

		return true;
	}

	public static void main(String[] args) {
		CourseSchedule cs = new CourseSchedule();
		int[] res = cs.findOrderDFS(2, new int[][] { { 1, 0 } });
		System.out.println(Arrays.toString(res));
		int[] res1 = cs.findOrderDFS(4, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } });
		System.out.println(Arrays.toString(res1));

		System.out.println(Arrays.toString(cs.findOrderBFS(4, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } })));
	}
}
