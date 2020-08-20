package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algo.common.TreeNode;

/**
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined
 * as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is
 * the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any
 * order.
 * 
 * Examples 1
 * 
 * Input:
 * 
 * 5 / \ 2 -3
 * 
 * return [2, -3, 4], since all the values happen only once, return all of them in any order.
 * 
 * Examples 2
 * 
 * Input:
 * 
 * 5 / \ 2 -5
 * 
 * return [2], since 2 happens twice, however -5 only occur once.
 * 
 * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 *
 * Category : Medium
 */
public class MostFrequestSubtreeSum {

	private int maxCount = 0;

	public int[] findFrequentTreeSum(TreeNode root) {
		// base condition
		if (root == null) {
			return new int[] {};
		}

		// map of sum to frequency
		Map<Integer, Integer> map = new HashMap<>();
		helper(map, root);

		List<Integer>[] bucket = new ArrayList[maxCount + 1];

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int key = entry.getKey();
			int value = entry.getValue();

			if (bucket[value] == null) {
				bucket[value] = new ArrayList<Integer>();
			}
			bucket[value].add(key);
		}

		for (int i = bucket.length - 1; i >= 0; i--) {
			List<Integer> list = bucket[i];

			if (list == null) {
				continue;
			}

			int[] res = new int[list.size()];
			for (int j = 0; j < list.size(); j++) {
				res[j] = list.get(j);
			}

			return res;
		}

		return null;
	}

	private int helper(Map<Integer, Integer> map, TreeNode root) {
		if (root == null) {
			return 0;
		}

		int leftSum = helper(map, root.left);
		int rightSum = helper(map, root.right);
		int subTreeSum = leftSum + rightSum + root.data;

		if (!map.containsKey(subTreeSum)) {
			map.put(subTreeSum, 1);
		} else {
			map.put(subTreeSum, map.get(subTreeSum) + 1);
		}

		maxCount = Math.max(maxCount, map.get(subTreeSum));

		return subTreeSum;
	}

	public static void main(String[] args) {
		MostFrequestSubtreeSum most = new MostFrequestSubtreeSum();

		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(2);
		root.right = new TreeNode(-5);

		System.out.println(Arrays.toString(most.findFrequentTreeSum(root)));

		TreeNode root1 = new TreeNode(5);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(-3);

		System.out.println(Arrays.toString(most.findFrequentTreeSum(root1)));
	}
}
