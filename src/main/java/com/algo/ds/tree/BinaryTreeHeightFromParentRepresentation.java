package com.algo.ds.tree;

/**
 * A given array represents a tree in such a way that the array value gives the parent node of that particular index. The value of the root node index would always be -1. Find the height of the tree.
Height of a Binary Tree is number of nodes on the path from root to the deepest leaf node, the number includes both root and leaf.

Input: parent[] = {1 5 5 2 2 -1 3}
Output: 4
The given array represents following Binary Tree 
         5
        /  \
       1    2
      /    / \
     0    3   4
         /
        6 


Input: parent[] = {-1, 0, 0, 1, 1, 3, 5};
Output: 5
The given array represents following Binary Tree 
         0
       /   \
      1     2
     / \
    3   4
   /
  5 
 /
6

Note that the time complexity of this programs seems more than O(n). 
If we take a closer look, we can observe that depth of every node is evaluated only once.



 * @author surya
 *
 */
public class BinaryTreeHeightFromParentRepresentation {

	/**
	 * An efficient solution can solve the above problem in O(n) time. The idea is to first calculate
	 * depth of every node and store in an array depth[]. Once we have depths of all nodes, we return
	 * maximum of all depths. 1) Find depth of all nodes and fill in an auxiliary array depth[]. 2)
	 * Return maximum value in depth[].
	 * 
	 * @param parent
	 * @return
	 */
	public int heightFromParentArray(int[] parent) {
		int n = parent.length;
		// make a temp array of size n which will contain the depth of each node
		int[] depth = new int[n];
		// initialize depth
		for (int i = 0; i < n; i++) {
			depth[i] = 0;
		}
		for (int i = 0; i < n; i++) {
			fillDepth(parent, i, depth);
		}

		int maxHt = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (depth[i] > maxHt)
				maxHt = depth[i];
		}
		return maxHt;
	}

	private void fillDepth(int[] parent, int i, int[] depth) {
		// if depth at i is already filled
		if (depth[i] != 0)
			return;

		// if node at i is root
		if (parent[i] == -1) {
			depth[i] = 1; // root starts at depth 1
			return;
		}

		// if parent's depth is not measured, then evaluate parent's depth first
		if (depth[parent[i]] == 0) {
			fillDepth(parent, parent[i], depth);
		}
		depth[i] = depth[parent[i]] + 1;
	}

}
