package com.algo.ds.graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import com.algo.ds.queue.QueueAsArray;
import com.algo.ds.stack.ArrayStack;
import com.algo.ds.stack.Stack;
import com.algo.ds.stack.StackAsArray;

public class MyGraph<K> {

	private int MAX_VERTEX = 20;
	private Vertex[] vertexArray;
	private int[][] adjMatrix;
	private int numVer;
	private StackAsArray stack;
	private QueueAsArray queue;
	// array of linked lists for adjacency list
	private LinkedList<Integer>[] adjList;
	// linked list for adjacency list for Longest distance
	private LinkedList<AdjListNode>[] adjListLP;

	public MyGraph(int n) {
		vertexArray = new MyGraph.Vertex[MAX_VERTEX];
		adjMatrix = new int[MAX_VERTEX][MAX_VERTEX];
		numVer = n;
		for (int i = 0; i < MAX_VERTEX; i++)
			for (int j = 0; j < MAX_VERTEX; j++)
				adjMatrix[i][j] = 0; // initialize
		stack = new StackAsArray(MAX_VERTEX);
		queue = new QueueAsArray(MAX_VERTEX);
		// for adjacency list
		adjList = new LinkedList[numVer];
		for (int i = 0; i < numVer; i++)
			adjList[i] = new LinkedList<Integer>();

		// adj list for longest distance
		adjListLP = new LinkedList[numVer];
		for (int i = 0; i < numVer; i++)
			adjListLP[i] = new LinkedList<AdjListNode>();
	}

	public void addVertex(char l) {
		vertexArray[numVer++] = new Vertex(l);
	}

	public void addEdge(int start, int end) {
		adjMatrix[start][end] = 1;
		adjMatrix[end][start] = 1;
	}

	public void displayVertex(int j) {
		System.out.println(vertexArray[j].label);
	}

	/**
	 * Rule 1- If possible visit an adjacent unvisited vertex, mark it and push it onto the stack.
	 * 
	 * Rule 2 - If you can't follow Rule 1, because there are no adjacent unvisited vertex, then pop a vertex off the
	 * stack, if possible
	 * 
	 * Rule 3 - If you can't follow Rule 2, as the stack is empty then you are done.
	 * 
	 */
	public void dfs_adjacencyMatrix() {
		vertexArray[0].wasVisited = true; // start with the root
		displayVertex(0); // display
		stack.push((char) 0); // push it onto the stack

		while (!stack.isEmpty()) {// until stack empty
			// find the first unvisited adjacent vertex
			int d = findAdjUnvisitedVertex(stack.peek());
			if (d == -1)
				stack.pop();
			else {
				vertexArray[d].wasVisited = true;
				displayVertex(d);
				stack.push((char) d);
			}
		}

		// after all the vertex have been visited, reset them
		for (int i = 0; i < numVer; i++)
			vertexArray[i].wasVisited = false;

	}

	/**
	 * According to adjacency matrix, 1 means the vertex is adjacent. wasVisited = false, means the vertex is unvisited.
	 * 
	 * @param i
	 * @return
	 */
	private int findAdjUnvisitedVertex(int i) {
		for (int j = 0; j < numVer; j++) {
			if (adjMatrix[i][j] == 1 && vertexArray[j].wasVisited == false)
				return j;
		}
		return -1;
	}

	public void dfs_adjacencyList(int v, boolean[] visited) {
		// current node visited
		visited[v] = true;

		// recursion for all vertices adjacent to this vertex
		for (Iterator<Integer> iterator = adjList[v].listIterator(); iterator.hasNext();) {
			int n = iterator.next();
			if (!visited[n])
				dfs_adjacencyList(n, visited);
		}
	}

	/**
	 * Rule 1 - Visit the next unvisited adjacent vertex(if there is one) if possible, to the current vertex, mark it
	 * and insert it into the queue.
	 * 
	 * Rule 2 - If you cannot carry out Rule 1, because there is no more adjacent unvisited vertex, then remove an item
	 * from queue, if possible and make it current vertex.
	 * 
	 * Rule 3 - If you cannot carry out Rule 2, because queue is empty then you are done.
	 * 
	 */
	public void bfs_adjacencyMatrix() {
		vertexArray[0].wasVisited = true; // start with the top
		displayVertex(0);
		queue.insert(0);

		while (!queue.isEmpty()) { // until the queue is empty
			int v1 = queue.remove(); // remove vertex at head
			int v2 = findAdjUnvisitedVertex(v1);
			while (v2 != -1) {
				vertexArray[v2].wasVisited = true;
				displayVertex(v2);
				queue.insert(v2);
			}
			// reset
			for (int i = 0; i < numVer; i++)
				vertexArray[i].wasVisited = false;
		}
	}

	public void addEdge_adjList(int v, int w) {
		adjList[v].add(w);
	}

	public void bfs_adjacencyList(int n) {
		// checker for vertex visited or not. by default its valse
		boolean[] isVisited = new boolean[n];

		// create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<>();
		isVisited[n] = true; // its visited
		queue.add(n);

		while (!queue.isEmpty()) {
			// remove element from queue
			Integer elem = queue.poll();
			System.out.println(elem);
			// Get all adjacent vertices of the dequeued vertex elem
			// If a adjacent has not been visited, then mark it visited and enqueue it
			for (Iterator<Integer> iter = adjList[elem].iterator(); iter.hasNext();) {
				Integer next = iter.next();
				if (!isVisited[next]) {
					isVisited[next] = true;
					queue.add(next);
				}
			}
		}

	}

	public void minimumSpanningTree() {
		vertexArray[0].wasVisited = true; // start at 0, mark it and push it.
		stack.push((char) 0);

		while (!stack.isEmpty()) {
			int currentVertex = stack.peek(); // current vertex.
			int v2 = findAdjUnvisitedVertex(currentVertex);
			if (v2 == -1)
				stack.pop();
			else {
				vertexArray[v2].wasVisited = true;
				stack.push((char) v2);

				displayVertex(currentVertex);
				displayVertex(v2);
			}
		}
	}

	/**
	 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every
	 * directed edge uv, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible if the
	 * graph is not a DAG. Note that a vertex is pushed to stack only when all of its adjacent vertices (and their
	 * adjacent vertices and so on) are already in stack. <br>
	 * The above algorithm is simply DFS with an extra stack. So time complexity is same as DFS which is O(V+E).
	 */
	public void topologicalSort() {
		// maintain a stack for topological sort
		Stack<Integer> stack = new ArrayStack<>(numVer);
		// array of isVisited boolean
		boolean[] isVisited = new boolean[numVer];
		Arrays.fill(isVisited, false); // initialize it

		for (int i = 0; i < numVer; i++) {
			if (!isVisited[i]) { // if vertex is not visited
				topologicalSortUtil(i, stack, isVisited);
			}
		}

		// print contents of stack
		while (!stack.isEmpty()) {
			System.out.println(stack.pop() + " ");
		}
	}

	private void topologicalSortUtil(int i, Stack<Integer> stack, boolean[] isVisited) {
		// mark the current node as visited
		isVisited[i] = true;

		// loop through the adjacent vertexes for this vertex
		for (Iterator<Integer> iter = adjList[i].iterator(); iter.hasNext();) {
			Integer v = iter.next();
			if (!isVisited[v]) {
				// recursive operation
				topologicalSortUtil(v, stack, isVisited);
			}
		}
		// push current vertex to stack
		stack.push(i);
	}

	/**
	 * Given a directed graph, check whether the graph contains a cycle or not. Your function should return true if the
	 * given graph contains at least one cycle, else return false. There is a cycle in a graph only if there is a back
	 * edge present in the graph. To detect a back edge, we can keep track of vertices currently in recursion stack of
	 * function for DFS traversal. If we reach a vertex that is already in the recursion stack, then there is a cycle in
	 * the tree. The edge that connects current vertex to the vertex in the recursion stack is back edge. We have used
	 * inRecStack[] array to keep track of vertices in the recursion stack.
	 * 
	 * @return
	 */
	public boolean isCyclic() {
		// keep track of visited and part of recursion stack
		boolean[] isVisited = new boolean[numVer];
		boolean[] inRecStack = new boolean[numVer];
		Arrays.fill(isVisited, false);
		Arrays.fill(inRecStack, false);

		for (int i = 0; i < numVer; i++) {
			if (isCyclicUtil(i, isVisited, inRecStack))
				return true;
		}
		return false;
	}

	private boolean isCyclicUtil(int i, boolean[] isVisited, boolean[] inRecStack) {
		if (!isVisited[i]) {
			// set isVisited
			isVisited[i] = true;
			inRecStack[i] = true;

			// recur for all vertices adjacent to this vertex
			for (Iterator<Integer> iter = adjList[i].iterator(); iter.hasNext();) {
				Integer v = iter.next();
				if (!isVisited[v] && isCyclicUtil(v, isVisited, inRecStack))
					return true;
				else if (inRecStack[v])
					return true;
			}
		}
		// remove vertex from recursion stack
		inRecStack[i] = false;
		return false;
	}

	/**
	 * For largest distance for DAG
	 * 
	 * @author Suryasnat
	 *
	 */
	class AdjListNode {
		private int vertex;
		private int weight;

		public AdjListNode(int v, int w) {
			this.vertex = v;
			this.weight = w;
		}

		public int getVertex() {
			return vertex;
		}

		public int getWeight() {
			return weight;
		}
	}

	/**
	 * to add edge in graph using adjacency list for longest path
	 * 
	 * @param v
	 * @param w
	 * @param weight
	 */
	public void addEdge_adjList_LD(int v, int w, int weight) {
		AdjListNode e = new AdjListNode(w, weight);
		adjListLP[v].add(e);
	}

	/**
	 * Time Complexity: Time complexity of topological sorting is O(V+E). After finding topological order, the algorithm
	 * process all vertices and for every vertex, it runs a loop for all adjacent vertices. Total adjacent vertices in a
	 * graph is O(E). So the inner loop runs O(V+E) times. Therefore, overall time complexity of this algorithm is
	 * O(V+E).
	 * 
	 * @param sourceVertex
	 */
	public void longestPathInDAG(int sourceVertex) {
		// maintain a stack for topological sort
		Stack<Integer> stack = new ArrayStack<>(numVer);
		int[] dist = new int[numVer];

		// array of isVisited boolean
		boolean[] isVisited = new boolean[numVer];
		Arrays.fill(isVisited, false); // initialize it

		// Call the recursive helper function to store Topological Sort
		// starting from all vertices one by one
		for (int i = 0; i < numVer; i++) {
			if (!isVisited[i]) { // if vertex is not visited
				topologicalSortUtil(i, stack, isVisited);
			}
		}

		// Initialize distances to all vertices as infinite and distance
		// to source as 0
		for (int i = 0; i < numVer; i++) {
			dist[i] = Integer.MIN_VALUE;
		}
		dist[sourceVertex] = 0;

		// process vertices in topological order
		while (!stack.isEmpty()) {
			int v = stack.pop();

			// update distances of all adjacent vertices
			if (dist[v] != Integer.MIN_VALUE) {
				for (Iterator<AdjListNode> iterator = adjListLP[v].iterator(); iterator.hasNext();) {
					AdjListNode n = iterator.next();
					if (dist[n.getVertex()] < dist[v] + n.getWeight()) {
						dist[n.getVertex()] = dist[v] + n.getWeight();
					}
				}
			}
		}

		for (int i = 0; i < numVer; i++) {
			System.out.println(dist[i] + " ");
		}
	}

	/**
	 * A mother vertex in a graph G = (V,E) is a vertex v such that all other vertices in G can be reached by a path
	 * from v. complexity - O(V + E)
	 */
	public void findMotherVertex() {
		// mark all vertices as not visited. complexity - O(n)
		boolean[] isVisited = new boolean[numVer];
		Arrays.fill(isVisited, false);

		// the vertex that finishes last should be mother node. DFS complexity - O(V + E)
		int v = -1;
		for (int i = 0; i < numVer; i++) {
			if (!isVisited[i]) {
				dfs_adjacencyList(i, isVisited);
				v = i;
			}
		}

		// reset the visited array.complexity - O(n)
		Arrays.fill(isVisited, false);

		// If there exist mother vertex (or vetices) in given
		// graph, then v must be one (or one of them)

		// Now check if v is actually a mother vertex (or graph
		// has a mother vertex). We basically check if every vertex
		// is reachable from v or not.

		// Reset all values in visited[] as false and do
		// DFS beginning from v to check if all vertices are
		// reachable from it or not.. DFS complexity - O(V + E)
		dfs_adjacencyList(v, isVisited);
		for (int i = 0; i < numVer; i++) {
			if (!isVisited[i]) {
				System.out.println("not a mother vertex");
				return;
			}
		}

		System.out.println("mother vertex is " + v);
	}

	class Vertex {
		public char[] label;
		int num;
		boolean wasVisited;

		Vertex(int n) {
			num = n;
		}
	}

}
