package com.ctci.treegraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * CTCI - 4.7
 * 
 * Build Order: You are given a list of projects and a list of dependencies (which is a list of
 * pairs of projects, where the second project is dependent on the first project). All of a
 * project's dependencies must be built before the project is. Find a build order that will allow
 * the projects to be built. If there is no valid build order, return an error.
 * 
 * EXAMPLE Input: projects: a, b, c, d, e, f dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
 * Output: f, e, a, b, d, c
 * 
 * @author surya
 *
 */
public class BuildOrder {

	// Approach 1 - more OO approach
	public Project[] buildOrder(String[] projects, String[][] dependencies) {
		Graph graph = buildGraph(projects, dependencies);
		return orderProjects(graph.getNodes());
	}

	private Graph buildGraph(String[] projects, String[][] dependencies) {
		Graph graph = new Graph();
		for (String project : projects)
			graph.getOrCreateNode(project);
		for (String[] dependency : dependencies)
			graph.addEdge(dependency[0], dependency[1]);
		return graph;
	}

	private Project[] orderProjects(ArrayList<Project> projects) {
		Project[] order = new Project[projects.size()];
		int endOfList = addNonDependent(order, projects, 0);
		int toBeProcessed = 0;
		while (toBeProcessed < order.length) {
			Project current = order[toBeProcessed];

			if (current == null)
				return null; // circular dependency, no remaining projects w/ 0 dependencies

			ArrayList<Project> children = current.getChildren(); // remove myself as dependency
			for (Project child : children)
				child.decrementDependencies();

			endOfList = addNonDependent(order, children, endOfList); // add children that have no one depending on them
			++toBeProcessed;
		}
		return order;
	}

	/* insert projects w/ 0 dependencies into the order array */
	private int addNonDependent(Project[] order, ArrayList<Project> projects, int offset) {
		for (Project project : projects)
			if (project.getNumberDependencies() == 0)
				order[offset] = project;
		++offset;
		return offset;
	}

	public class Graph {
		private ArrayList<Project> nodes = new ArrayList<Project>();
		private HashMap<String, Project> map = new HashMap<String, Project>();

		public Project getOrCreateNode(String name) {
			if (!map.containsKey(name)) {
				Project node = new Project(name);
				nodes.add(node);
				map.put(name, node);
			}
			return map.get(name);
		}

		public void addEdge(String startName, String endName) {
			Project start = getOrCreateNode(startName);
			Project end = getOrCreateNode(endName);
			start.addNeighbor(end);
		}

		public ArrayList<Project> getNodes() {
			return nodes;
		}
	}

	public class Project {
		private ArrayList<Project> children = new ArrayList<Project>();
		private Map<String, Project> map = new HashMap<String, Project>();
		private String name;
		private int dependencies = 0;

		public Project(String n) {
			name = n;
		}

		public void addNeighbor(Project node) {
			if (!map.containsKey(node.getName())) {
				children.add(node);
				node.incrementDependencies();
			}
		}

		public void incrementDependencies() {
			++dependencies;
		}

		public void decrementDependencies() {
			--dependencies;
		}

		public String getName() {
			return name;
		}

		public ArrayList<Project> getChildren() {
			return children;
		}

		public int getNumberDependencies() {
			return dependencies;
		}

	}

	// Approach 2
	/**
	 * Perform topological sort. Input is a list of dependencies where the index is the process number
	 * and the value is the numbers the processes it depends on.
	 * 
	 * @param projects
	 * @return
	 */
	public List<Integer> buildOrder(int[][] projects) {
		Set<Integer> tempMark = new HashSet<>();
		Set<Integer> permMark = new HashSet<>();
		List<Integer> result = new ArrayList<>();

		// Recursively search for any unmarked node
		for (int i = 0; i < projects.length; i++) {
			if (!permMark.contains(i)) {
				visit(i, projects, tempMark, permMark, result);
			}
		}

		return result;
	}

	private void visit(int i, int[][] projects, Set<Integer> tempMark, Set<Integer> permMark, List<Integer> result) {
		// throw error if we find a cycle
		if (tempMark.contains(i)) {
			throw new RuntimeException("Graph is not acyclic");
		}

		// if we haven't visited a node recursively search from there
		if (!permMark.contains(i)) {
			// add to temp mark
			tempMark.add(i);

			// perform recursive search from children
			for (int count : projects[i]) {
				visit(count, projects, tempMark, permMark, result);
			}

			// mark visited node as permanent
			permMark.add(i);
			// remove from temp node
			tempMark.remove(i);
			// add to result
			result.add(i);
		}
	}

	// Sample test cases
	public static void main(String[] args) {
		BuildOrder bo = new BuildOrder();

		assert compareResults(bo.buildOrder(new int[][] { {}, { 0 }, { 1 }, { 2 }, { 3 } }),
				new int[] { 0, 1, 2, 3, 4 }) : "Simple sorted order";
		assert compareResults(bo.buildOrder(new int[][] { {}, { 0 }, { 0 }, { 1, 2 }, { 1, 2, 3 } }),
				new int[] { 0, 1, 2, 3, 4 }) : "Complex sorted order";
		assert compareResults(bo.buildOrder(new int[][] { { 3 }, { 0 }, { 4 }, {}, { 1 } }),
				new int[] { 3, 0, 1, 4, 2 }) : "Simple unsorted order";
		assert compareResults(bo.buildOrder(new int[][] { { 3 }, { 0, 3 }, { 0, 1, 3 }, {}, { 1, 2, 3 } }),
				new int[] { 3, 0, 1, 2, 4 }) : "Complex unsorted order";
		try {
			bo.buildOrder(new int[][] { { 1 }, { 0 } });
			assert false : "Throw error on cycle";
		} catch (Exception e) {
		}
		System.out.println("Passed all test cases");
	}

	// Helper method for tests. Checks if lists have equal values
	private static boolean compareResults(List<Integer> a, int[] b) {
		if (a.size() != b.length)
			return false;
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) != b[i])
				return false;
		}

		return true;
	}
}
