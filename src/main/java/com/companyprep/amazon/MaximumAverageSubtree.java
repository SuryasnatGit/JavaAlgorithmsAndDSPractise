package com.companyprep.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an N-ary tree, find the subtree with maximum average. Return the root of the subtree. A subtree of a tree is
 * any node of that tree plus all its descendants. The average value of a subtree is the sum of its values, divided by
 * the number of nodes.
 * 
 * Example 1: Input:
 * 
 * Output: 13 Explanation: For the node with value = 13 we have an avarage of (13 + 4 + -2) / 3 = 5 which is the
 * maximum.
 * 
 * Example 2:
 * 
 * Input:
 * 
 * 
 * Output: 21
 * 
 * Explanation:
 * 
 * For the node with value = 1 we have an avarage of (- 5 + 21 + 5 - 1) / 5 = 4.
 * 
 * For the node with value = -5 we have an avarage of (-5 / 1) = -5.
 * 
 * For the node with value = 21 we have an avarage of (21 / 1) = 21.
 * 
 * For the node with value = 5 we have an avarage of (5 / 1) = 5.
 * 
 * For the node with value = -1 we have an avarage of (-1 / 1) = -1. So the subtree of 21 is the maximum.
 * 
 * Category : Hard
 *
 */
public class MaximumAverageSubtree {

	// Solution 1 - recursive way

	double max = Integer.MIN_VALUE;
	TreeNode maxNode = null;

	public TreeNode maximumAverageSubtree(TreeNode root) {
		if (root == null)
			return null;
		helper(root);
		return maxNode;
	}

	private double[] helper(TreeNode root) {
		if (root == null)
			return new double[] { 0, 0 };

		double curTotal = root.val;
		double count = 1;
		for (TreeNode child : root.childs) {
			double[] cur = helper(child);
			curTotal += cur[0];
			count += cur[1];
		}
		double avg = curTotal / count;
		if (count > 1 && avg > max) { // taking "at least 1 child" into account
			max = avg;
			maxNode = root;
		}
		return new double[] { curTotal, count };
	}

	// Solution 2 - Using DFS

	private double max_avg = 0.0;
	private TreeNode root_of_max_avg_subtree = null;

	public double[] dfs(TreeNode cur) {
		// if the current node has no child, that it is a leaf, not a sub-tree
		// table format: { sum, count }
		if (cur.childs.size() == 0)
			return new double[] { cur.val, 1 };

		int sum = 0, cnt = 0;
		double avg = 0;
		for (TreeNode child : cur.childs) {
			double[] res_c = dfs(child);
			sum += res_c[0];
			cnt += res_c[1];
		}
		sum += cur.val;
		cnt++;
		avg = (sum / cnt);

		if (avg > max_avg) {
			max_avg = avg;
			root_of_max_avg_subtree = cur;
		}

		return new double[] { sum, cnt };
	}

	private TreeNode buildTree() {
		TreeNode root = new TreeNode(20);

		TreeNode sub1 = new TreeNode(12);
		for (int i : Arrays.asList(11, 2, 3))
			sub1.childs.add(new TreeNode(i));
		TreeNode sub2 = new TreeNode(18);
		for (int i : Arrays.asList(15, 8))
			sub2.childs.add(new TreeNode(i));

		root.childs.addAll(Arrays.asList(sub1, sub2));

		return root;
	}

	private class TreeNode {
		int val;
		List<TreeNode> childs;

		public TreeNode(int v) {
			this.val = v;
			this.childs = new ArrayList<TreeNode>();
		}

		public void levelOrder(TreeNode cur) {
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			q.offer(cur);

			while (!q.isEmpty()) {
				int size = q.size();
				for (int i = 0; i < size; i++) {
					TreeNode n = q.remove();
					if (n.childs.size() != 0)
						for (TreeNode child : n.childs)
							q.offer(child);
					System.out.printf("%d ", n.val);
				}
				System.out.println();
			}
		}
	}

}
