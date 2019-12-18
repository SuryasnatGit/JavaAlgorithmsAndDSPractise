package com.ctci.treegraph;

import java.util.HashMap;

import com.algo.common.TreeNode;

/**
 * CTCI - 4.12
 * 
 * Paths with Sum: You are given a binary tree in which each node contains an integer value (which might be positive or
 * negative). Design an algorithm to count the number of paths that sum to a given value. The path does not need to
 * start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * 
 * @author surya
 *
 */
public class PathsWithSum {

	// Approach 1 - Brute force - Recursive way
	/**
	 * In the brute force approach, we just look at all possible paths. To do this, we traverse to each node. At each
	 * node, we recursively try all paths downwards, tracking the sum as we go. As soon as we hit our target sum, we
	 * increment the total.
	 * 
	 * Runtime: For balanced BT - O( N log N). For unbalanced tree - O(N^2)
	 * 
	 * @param root
	 * @param targetSum
	 * @return
	 */
	public int countPathsWithSum(TreeNode root, int targetSum) {
		if (root == null)
			return 0;

		// get paths with sum starting from this root
		int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);

		// navigate through left and right sub-trees
		int pathsFromLeft = countPathsWithSum(root.left, targetSum);
		int pathsFromRight = countPathsWithSum(root.right, targetSum);

		return pathsFromRoot + pathsFromLeft + pathsFromRight;
	}

	private int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum) {
		if (node == null)
			return 0;

		int totalPaths = 0;
		if (currentSum == targetSum) { // found path from root
			totalPaths++;
		}

		totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum);
		totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum);

		return totalPaths;
	}

	// Approach 2 - Optimized way

	/**
	 * 1. Track its runningSum. We'll take this in as a parameter and immediately increment it by node. value.
	 * 
	 * 2. Look up runningSum - targetSum in the hash table. The value there indicates the total number. Set totalPaths
	 * to this value.
	 * 
	 * 3. If runningSum == targetSum, then there's one additional path that starts at the root. Increment totalPaths.
	 * 
	 * 4. Add runningSum to the hash table (incrementing the value if it's already there).
	 * 
	 * 5. Recurse left and right, counting the number of paths with sum targetSum.
	 * 
	 * 6. After we're done recursing left and right, decrement the value of runningSum in the hash table. This is
	 * essentially backing out of our work; it reverses the changes to the hash table so that other nodes don't use it
	 * (since we're now done with node).
	 * 
	 * The runtime for this algorithm is 0 (N), where N is the number of nodes in the tree. We know it is 0 (N) because
	 * we travel to each node just once, doing 0 (1) work each time. In a balanced tree, the space complexity is 0 (log
	 * N) due to the hash table. The space complexity can grow to O( n) in an unbalanced tree.
	 * 
	 * @param root
	 * @param targetSum
	 * @return
	 */
	public int countPathsWithSum1(TreeNode root, int targetSum) {
		return countPathsWithSum1(root, targetSum, 0, new HashMap<Integer, Integer>());
	}

	private int countPathsWithSum1(TreeNode node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount) {
		// base case
		if (node == null)
			return 0;

		// count paths with sum ending at current node
		runningSum += node.data;
		int sum = targetSum - runningSum;
		int totalPaths = pathCount.getOrDefault(sum, 0);

		// if running sum == target sum then one additional path starts at root
		if (runningSum == targetSum)
			totalPaths++;

		// increment pathCount, recurse , then decrement pathCount
		incrementHashTable(pathCount, runningSum, 1); // increment
		totalPaths += countPathsWithSum1(node.left, targetSum, runningSum, pathCount);
		totalPaths += countPathsWithSum1(node.right, targetSum, runningSum, pathCount);
		incrementHashTable(pathCount, runningSum, -1); // decrement

		return totalPaths;
	}

	private void incrementHashTable(HashMap<Integer, Integer> pathCount, int key, int delta) {
		int newCount = pathCount.getOrDefault(key, 0);
		if (newCount == 0)
			pathCount.remove(key);
		else
			pathCount.put(key, newCount);

	}

}
