package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Count number of smaller elements on right side of an array for every element.
 * 
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property
 * where counts[i] is the number of smaller elements to the right of nums[i].
 * 
 * // Example: // // Given nums = [5, 2, 6, 1] // // To the right of 5 there are 2 smaller elements (2 and 1). // To the
 * right of 2 there is only 1 smaller element (1). // To the right of 6 there is 1 smaller element (1). // To the right
 * of 1 there is 0 smaller element. // Return the array [2, 1, 1, 0].
 *
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * 
 * Category : Hard
 */
public class CountSmallerOnRight {

	/**
	 * Approach 1 - Using 2 loops. time complexity - O(n^2).
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> countSmaller_simple(int[] nums) {
		int len = nums.length;
		int[] count = new int[len]; // new array to hold the counts
		for (int i = 0; i < len; i++) {
			int c = 0;
			for (int j = i + 1; j < len; j++) {
				if (nums[j] < nums[i])
					c++;
			}
			count[i] = c;
		}
		return Arrays.stream(count).boxed().collect(Collectors.toList());
	}

	/**
	 * Approach 2 - using binary search
	 * 
	 * T - O(n log n) S - O(n)
	 * 
	 */
	public List<Integer> countSmaller_binarySearch(int[] nums) {
		Integer[] ans = new Integer[nums.length];
		List<Integer> sorted = new ArrayList<Integer>();

		for (int i = nums.length - 1; i >= 0; i--) {
			int index = findIndex(sorted, nums[i]);
			ans[i] = index;
			sorted.add(index, nums[i]);
		}

		return Arrays.asList(ans);
	}

	private int findIndex(List<Integer> sorted, int target) {
		if (sorted.size() == 0) {
			return 0;
		}

		int start = 0;
		int end = sorted.size() - 1;

		if (sorted.get(end) < target) {
			return end + 1;
		}

		if (sorted.get(start) >= target) {
			return 0;
		}

		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (sorted.get(mid) < target) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}

		if (sorted.get(start) >= target) {
			return start;
		}

		return end;
	}

	/**
	 * Approach 3 - Using Binary Search Tree
	 * 
	 * As we don't care the exact position in sorted order, but the count of elements that are smaller (what this makes
	 * you think of? yes, BST), we could store the number of elements seen so far that are smaller for each node, which
	 * can be added as count directly when meeting a larger number. So, we can maintain a binary search tree, each node
	 * denotes to extra 2 fields, countOfLeft and frequency. The first property counts the number of elements that are
	 * smaller after the current node is created, the second one tracks the times we met the same number (see
	 * [1,3,2,2,2]). The algorithm is to create the BST node for each element in the array while updating the properties
	 * down through the BST. Note, the worst case for this approach is O(n^2) where the elements are sorted. (we have a
	 * flatten BST)
	 * 
	 * Time Complexity = O(n log n) Space Complexity = O(n)
	 */
	public List<Integer> countSmaller_usingBinarySearchTree(int[] nums) {
		LinkedList<Integer> result = new LinkedList<>();

		if (nums.length == 0)
			return result;

		result.add(0);

		TreeNode root = new TreeNode(nums[nums.length - 1]);
		for (int i = nums.length - 2; i >= 0; i--) {
			result.addFirst(insert(root, nums[i]));
		}

		return result;
	}

	private class TreeNode {
		int val, countOfLeftChildren;
		TreeNode left, right;

		public TreeNode(int v) {
			val = v;
			countOfLeftChildren = 0;
		}
	}

	private int insert(TreeNode root, int val) {
		int count = 0;
		while (true) {
			if (val <= root.val) {
				root.countOfLeftChildren++;
				if (root.left != null)
					root = root.left;
				else {
					root.left = new TreeNode(val);
					break;
				}
			} else {
				count += root.countOfLeftChildren + 1;
				if (root.right != null)
					root = root.right;
				else {
					root.right = new TreeNode(val);
					break;
				}
			}
		}
		return count;
	}

	public static void main(String args[]) {
		CountSmallerOnRight csr = new CountSmallerOnRight();
		int nums[] = { 5, 2, 6, 1, 0, 3 };
		System.out.println(csr.countSmaller_simple(nums));
		System.out.println(csr.countSmaller_binarySearch(nums));
		System.out.println(csr.countSmaller_usingBinarySearchTree(nums));
	}
}
