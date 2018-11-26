package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/diameter-of-a-binary-tree/
 * 
 * Test cases: All left nodes. All right nodes
 */
public class DiameterOfTree {

	/**
	 * Time Complexity: O(n^2)
	 * 
	 * @param n
	 * @return
	 */
	public int diameter(Node n) {
		// base case if tree is empty
		if (n == null)
			return 0;

		// calculate height of left and right sub tree
		int lheight = height(n.left);
		int rheight = height(n.right);

		// calculate diameter of left and right sub tree
		int ldiameter = diameter(n.left);
		int rdiameter = diameter(n.right);

		/*
		 * Return max of following three 1) Diameter of left subtree 2) Diameter of right subtree 3) Height
		 * of left subtree + height of right subtree + 1
		 */
		return Math.max(Math.max(ldiameter, rdiameter), (1 + lheight + rheight));
	}

	/*
	 * The function Compute the "height" of a tree. Height is the number of nodes along the longest path
	 * from the root node down to the farthest leaf node.
	 */
	private int height(Node n) {
		if (n == null)
			return 0;

		return (1 + Math.max(height(n.left), height(n.right)));
	}

	// Optimized implementation: The above implementation can be optimized by calculating the height in
	// the same recursion rather than calling a height() separately. This optimization reduces time
	// complexity to O(n).

	class Height {
		int h;
	}

	public int diameterMod(Node root) {
		Height height = new Height();
		return diameterMod(root, height);
	}

	private int diameterMod(Node root, Height height) {
		Height leftHeight = new Height();
		Height rightHeight = new Height();

		if (root == null) {
			height.h = 0;
			return 0;// diameter is also 0
		}

		/*
		 * Get the heights of left and right subtrees in lh and rh And store the returned values in
		 * ldiameter and ldiameter
		 */
		int ldiameter = diameterMod(root.left, leftHeight);
		int rdiameter = diameterMod(root.right, rightHeight);

		/*
		 * Height of current node is max of heights of left and right subtrees plus 1
		 */
		height.h = Math.max(leftHeight.h, rightHeight.h) + 1;

		return Math.max(Math.max(ldiameter, rdiameter), (1 + leftHeight.h + rightHeight.h));
	}

	public int diameterTreeNew(Node root) {
		if (root == null)
			return 0;
		int diam = Integer.MIN_VALUE;
		int height = heightTree(root, diam);
		return diam;
	}

	private int heightTree(Node root, int diam) {
		if (root == null)
			return 0;

		// height of left subtree
		int leftTreeHeight = heightTree(root.left, diam);
		// height of right subtree
		int rightTreeHeight = heightTree(root.right, diam);

		// update the answer, because diameter of a
		// tree is nothing but maximum value of
		// (left_height + right_height + 1) for each node
		diam = Math.max(diam, 1 + leftTreeHeight + rightTreeHeight);

		return 1 + Math.max(leftTreeHeight, rightTreeHeight);
	}

	/**
	 * Diameter of tree using DFS.
	 * 
	 * In this post a different DFS based solution is discussed. After observing above tree we can see
	 * that the longest path will always occur between two leaf nodes. We start DFS from a random node
	 * and then see which node is farthest from it. Let the node farthest be X. It is clear that X will
	 * always be a leaf node and a corner of DFS. Now if we start DFS from X and check the farthest node
	 * from it, we will get the diameter of the tree.
	 * 
	 */
	private int x;
	private int maxCount;// maximum distance from node

	public int diameterTreeDFS() {
		int n = 5;
		List<List<Integer>> adjList = new ArrayList<>();
		for (int i = 0; i <= n + 1; i++) {
			adjList.add(new ArrayList<>());
		}
		adjList.get(1).add(2);
		adjList.get(1).add(3);
		adjList.get(2).add(1);
		adjList.get(2).add(4);
		adjList.get(2).add(5);
		adjList.get(3).add(1);
		adjList.get(4).add(2);
		adjList.get(5).add(2);

		return diameterDfs(adjList, n);
	}

	private int diameterDfs(List<List<Integer>> adjList, int n) {
		// do dfs from random node and find farthest node X from it
		dfs(1, n, adjList); // here 1 is the node number

		// do dfs from X node and find farthest node from it.
		dfs(x, n, adjList);

		return maxCount;
	}

	private void dfs(int nodeNumber, int n, List<List<Integer>> adjList) {
		boolean[] visited = new boolean[n + 1];
		// initialize everything to false
		Arrays.fill(visited, false);

		int count = 0;

		// increment count by 1 for visited node
		dfsUtil(nodeNumber, count + 1, visited, adjList);
	}

	private void dfsUtil(int nodeNumber, int count, boolean[] visited, List<List<Integer>> adjList) {
		// set visited node to true
		visited[nodeNumber] = true;
		count++;

		List<Integer> list = adjList.get(nodeNumber);
		for (Integer i : list) {
			if (!visited[i]) {
				if (count >= maxCount) {
					maxCount = count;
					x = i;
				}
				dfsUtil(i, count, visited, adjList);
			}
		}
	}

	/**
	 * Diameter of n-ary tree.
	 * 
	 * The path can either start from one of the node and goes up to one of the LCAs of these nodes and
	 * again come down to the deepest node of some other subtree or can exist as a diameter of one of
	 * the child of the current node.
	 * 
	 * The solution will exist in any one of these:
	 * 
	 * I] Diameter of one of the children of the current node
	 * 
	 * II] Sum of Height of highest two subtree + 1
	 * 
	 * 
	 */

	/*public int diameterNaryTreeBFS() {
		int n = 5;
		List<List<Integer>> adjList = new ArrayList<>();
		for (int i = 0; i <= n + 1; i++) {
			adjList.add(new ArrayList<>());
		}
		adjList.get(1).add(2);
		adjList.get(1).add(3);
		adjList.get(2).add(1);
		adjList.get(2).add(4);
		adjList.get(2).add(5);
		adjList.get(3).add(1);
		adjList.get(4).add(2);
		adjList.get(5).add(2);

		return findDiameter(adjList, n);
	}

	private int findDiameter(List<List<Integer>> adjList, int n) {
		int init = bfs(1, adjList, n);
		int val = bfs(init, adjList, n);
		return maxCount;
	}

	// method to do bfs traversal in iterative way.
	private int bfs(int nodeNumber, List<List<Integer>> adjList, int n) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(nodeNumber);

		boolean[] visited = new boolean[n + 1];
		// initialize everything to false
		Arrays.fill(visited, false);

		int count = 0;

		// mark the traversed node visited
		visited[nodeNumber] = true;
		while (!queue.isEmpty()) {
			Integer top = queue.peek();
			queue.poll();
			List<Integer> list = adjList.get(top);
			for (int i = 0; i < list.size(); i++) {
				if (!visited[list.get(i)])
					visited[list.get(i)] = true;

			}
		}
	}*/

	public static void main(String args[]) {
		BinaryTree bt = new BinaryTree();
		Node head = null;
		head = bt.addNode(10, head);
		head = bt.addNode(15, head);
		head = bt.addNode(5, head);
		head = bt.addNode(7, head);
		head = bt.addNode(19, head);
		head = bt.addNode(20, head);
		head = bt.addNode(-1, head);
		head = bt.addNode(21, head);
		head = bt.addNode(11, head);
		head = bt.addNode(12, head);
		head = bt.addNode(13, head);
		head = bt.addNode(14, head);
		DiameterOfTree dt = new DiameterOfTree();
		System.out.println(dt.diameter(head));
		System.out.println(dt.diameterMod(head));
		System.out.println(dt.diameterTreeNew(head));
		System.out.println(dt.diameterTreeDFS());
	}
}
