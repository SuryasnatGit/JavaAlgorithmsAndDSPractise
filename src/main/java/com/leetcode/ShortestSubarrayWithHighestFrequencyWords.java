package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Category : Medium
 */
public class ShortestSubarrayWithHighestFrequencyWords {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 2, 2, 1, 4, 1 };
		ShortestSubarrayWithHighestFrequencyWords testMain = new ShortestSubarrayWithHighestFrequencyWords();
		int[] res = testMain.findSubarray(arr);
		System.out.println(res[0] + "===" + res[1]);
	}

	/**
	 * For an array, return the shortest sub array with the most frequent repeated numbers. For example, array [1, 2, 3,
	 * 2, 2, 1, 4, 1] 1 and 2 appear 3 times, but you need to return [2,3,2,2] this subrange
	 */
	int[] findSubarray(int[] arr) {
		Map<Integer, Node> map = new HashMap<Integer, Node>();
		int maxCount = 0;

		for (int i = 0; i < arr.length; i++) {
			Node node = null;

			if (map.containsKey(arr[i])) {
				node = map.get(arr[i]);
				node.count++;
				node.end = i;
			} else {
				node = new Node(1, i, i);
				map.put(arr[i], node);
			}

			maxCount = Math.max(maxCount, node.count);
		}

		int minLength = Integer.MAX_VALUE;
		int[] res = { -1, -1 };
		for (Node node : map.values()) {
			if (node.count == maxCount) {
				if (node.end - node.start + 1 < minLength) {
					minLength = node.end - node.start + 1;
					res[0] = node.start;
					res[1] = node.end;
				}
			}
		}

		return res;
	}

	class Node {
		int count;
		int start;
		int end;

		public Node(int count, int start, int end) {
			super();
			this.count = count;
			this.start = start;
			this.end = end;
		}
	}

	class TreeNode {
		int val;
		List<TreeNode> children = new ArrayList<TreeNode>();
	}

}
