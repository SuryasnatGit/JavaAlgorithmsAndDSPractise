package com.algo.ds.tree;

import java.util.LinkedList;

import com.algo.common.TreeNode;

/**
 * Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,
   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.

   2
    \
     3
    / 
   2    
  / 
 1

Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 */
public class LongestConsecutiveSequence {
	int max = 0;

	// DFS approach
	public int longestConsecutiveDFS(TreeNode root) {
		if (root == null) {
			return 0;
		}
		max = 0;
		longestConsecutiveUtil(root, root.data - 1, 0);
		return max;
	}

	private void longestConsecutiveUtil(TreeNode root, int prevData, int current) {
		if (root == null) {
			return;
		}

		if (root.data == prevData + 1) {
			current = current + 1;
		} else {
			current = 1;
		}
		max = Math.max(current, max);
		longestConsecutiveUtil(root.left, root.data, current);
		longestConsecutiveUtil(root.right, root.data, current);
	}
	
	// BFS approach
	public int longestConsecutiveBFS(TreeNode root) {
		if (root == null)
			return 0;

		LinkedList<TreeNode> nodeQueue = new LinkedList<TreeNode>();
		LinkedList<Integer> sizeQueue = new LinkedList<Integer>();

		nodeQueue.offer(root);
		sizeQueue.offer(1);
		int max = 1;

		while (!nodeQueue.isEmpty()) {
			TreeNode head = nodeQueue.poll();
			int size = sizeQueue.poll();

			if (head.left != null) {
				int leftSize = size;
				if (head.data == head.left.data - 1) {
					leftSize++;
					max = Math.max(max, leftSize);
				} else {
					leftSize = 1;
				}

				nodeQueue.offer(head.left);
				sizeQueue.offer(leftSize);
			}

			if (head.right != null) {
				int rightSize = size;
				if (head.data == head.right.data - 1) {
					rightSize++;
					max = Math.max(max, rightSize);
				} else {
					rightSize = 1;
				}

				nodeQueue.offer(head.right);
				sizeQueue.offer(rightSize);
			}

		}

		return max;
	}
}
