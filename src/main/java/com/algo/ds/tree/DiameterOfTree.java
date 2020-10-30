package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * http://www.geeksforgeeks.org/diameter-of-a-binary-tree/
 * 
 * Note: The length of path between two nodes is represented by the number of edges between them.
 * 
 * 
 * Test cases: All left nodes. All right nodes
 */
public class DiameterOfTree {

	// T - O(n) S - O(n)
	private int diam = 0;

	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null)
			return 0;

		heightOfTree(root);

		return diam;
	}

	private int heightOfTree(TreeNode root) {
		if (root == null)
			return 0;

		// height of left subtree
		int leftTreeHeight = heightOfTree(root.left);
		// height of right subtree
		int rightTreeHeight = heightOfTree(root.right);

		// update the answer, because diameter of a
		// tree is nothing but maximum value of
		// (left_height + right_height) for each node. since its edges so don't add 1
		diam = Math.max(diam, leftTreeHeight + rightTreeHeight);

		return 1 + Math.max(leftTreeHeight, rightTreeHeight);
	}

	/**
	 * Diameter of n-ary tree.
	 * 
	 * The path can either start from one of the node and goes up to one of the LCAs of these nodes and again come down
	 * to the deepest node of some other subtree or can exist as a diameter of one of the child of the current node.
	 * 
	 * The solution will exist in any one of these:
	 * 
	 * I] Diameter of one of the children of the current node
	 * 
	 * II] Sum of Height of highest two subtree + 1
	 * 
	 * 
	 */

	/*
	 * public int diameterNaryTreeBFS() { int n = 5; List<List<Integer>> adjList = new ArrayList<>(); for (int i = 0; i
	 * <= n + 1; i++) { adjList.add(new ArrayList<>()); } adjList.get(1).add(2); adjList.get(1).add(3);
	 * adjList.get(2).add(1); adjList.get(2).add(4); adjList.get(2).add(5); adjList.get(3).add(1);
	 * adjList.get(4).add(2); adjList.get(5).add(2);
	 * 
	 * return findDiameter(adjList, n); }
	 * 
	 * private int findDiameter(List<List<Integer>> adjList, int n) { int init = bfs(1, adjList, n); int val = bfs(init,
	 * adjList, n); return maxCount; }
	 * 
	 * // method to do bfs traversal in iterative way. private int bfs(int nodeNumber, List<List<Integer>> adjList, int
	 * n) { Queue<Integer> queue = new LinkedList<>(); queue.add(nodeNumber);
	 * 
	 * boolean[] visited = new boolean[n + 1]; // initialize everything to false Arrays.fill(visited, false);
	 * 
	 * int count = 0;
	 * 
	 * // mark the traversed node visited visited[nodeNumber] = true; while (!queue.isEmpty()) { Integer top =
	 * queue.peek(); queue.poll(); List<Integer> list = adjList.get(top); for (int i = 0; i < list.size(); i++) { if
	 * (!visited[list.get(i)]) visited[list.get(i)] = true;
	 * 
	 * } } }
	 */

	public static void main(String args[]) {
		TreeNode head = new TreeNode(1);
		// head.left = new TreeNode(2);
		// head.right = new TreeNode(3);
		// head.left.left = new TreeNode(4);
		// head.left.right = new TreeNode(5);

		DiameterOfTree dt = new DiameterOfTree();
		System.out.println(dt.diameterOfBinaryTree(head));

	}
}
