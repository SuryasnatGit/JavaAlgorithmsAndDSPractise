package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algo.common.TreeNode;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * 
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * 
 * Example:
 * 
 * Given nums = [2, 7, 11, 15], target = 9,
 * 
 * Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 * 
 * @author surya
 *
 */
public class TwoSum {

	public boolean sumOfTwo(int[] a, int[] b, int v) {
		Arrays.sort(b);

		for (int aNum : a) {
			if (Arrays.binarySearch(b, v - aNum) >= 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * O(n2) runtime, O(1) space Brute force: The brute force approach is simple. Loop through each element x and find
	 * if there is another value that equals to target x. As finding another value requires looping through the rest of
	 * array, its runtime complexity is O(n2).
	 * 
	 * @param arr
	 * @param n
	 * @return
	 */
	public int[] twoSum_bruteForce(int[] arr, int n) {
		int[] res = new int[2];
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] + arr[j] == n) {
					res[0] = i;
					res[1] = j;
				}
			}
		}
		return res;
	}

	/**
	 * O(n) runtime, O(n) space Hash table: We could reduce the runtime complexity of looking up a value to O(1) using a
	 * hash map that maps a value to its index.
	 * 
	 * @param arr
	 * @param n
	 * @return
	 */
	public int[] twoSum_hashTable(int[] arr, int n) {
		int[] res = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			int x = arr[i];
			if (map.containsKey(n - x)) {
				res[0] = map.get(n - x);
				res[1] = i;
			}
			map.put(x, i);
		}
		return res;
	}

	/**
	 * Of course we could still apply the [Hash table] approach, but it costs us O(n) extra space, plus it does not make
	 * use of the fact that the input is already sorted.
	 * 
	 * <br/>
	 * O(n log n) runtime, O(1) space Binary search: For each element x, we could look up if target x exists in O(log n)
	 * time by applying binary search over the sorted array. Total runtime complexity is O(n log n).
	 * 
	 * @param arr
	 * @param n
	 * @return
	 */
	public int[] twoSum_binarySearch(int[] arr, int n) {
		for (int i = 0; i < arr.length; i++) { // n times log n
			int j = binarySearch(arr, n - arr[i], i + 1);
			if (j != -1) {
				return new int[] { i, j };
			}
		}
		throw new IllegalArgumentException("no 2 sum solution");
	}

	private int binarySearch(int[] arr, int key, int start) {
		int left = start;
		int right = arr.length - 1;
		while (left < right) {
			int mid = (left + right) / 2;
			if (arr[mid] < key) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return (left == right && arr[left] == key) ? left : -1;
	}

	/**
	 * O(n) runtime, O(1) space
	 * 
	 * Two pointers: Array needs to be sorted. Lets assume we have two indices pointing to the ith and jth elements, Ai
	 * and Aj respectively. The sum of Ai and Aj could only fall into one of these three possibilities: i. Ai + Aj >
	 * target. Increasing i isnt going to help us, as it makes the sum even bigger. Therefore we should decrement j. ii.
	 * Ai + Aj < target. Decreasing j isnt going to help us, as it makes the sum even smaller. Therefore we should
	 * increment i. iii. Ai + Aj == target. We have found the answer.
	 * 
	 * @param arr
	 * @param n
	 * @return
	 */
	public int[] twoSum_2pointers(int[] arr, int n) {
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			int sum = arr[left] + arr[right];
			if (sum < n) {
				left++;
			} else if (sum > n) {
				right--;
			} else {
				return new int[] { left, right };
			}
		}
		throw new IllegalArgumentException("no 2 sum solution");
	}

	/**
	 * Given an unsorted array of integers, find all the pairs that they add up to a specific target number. The array
	 * may contain duplicated elements. The output should not contain duplicated pairs, and each pair needs to be in
	 * ascending order, e.g., [1, 2] instead of [2, 1].
	 * 
	 * Hash Table: Time ~ O(N), Space ~ O(N)
	 * 
	 * Use Hash Table to store key - num[i], value - count.
	 * 
	 * @param arr
	 * @param target
	 * @return
	 */
	public List<List<Integer>> twoSumContainingDuplicates(int[] arr, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			int num1 = arr[i];
			int num2 = target - arr[i];
			// if map contains a number and its count > 0
			if (map.containsKey(num1) && map.get(num1) > 0) {
				List<Integer> list = new ArrayList<>();
				if (num1 < num2) {
					list = Arrays.asList(num1, num2);
				} else {
					list = Arrays.asList(num2, num1);
				}

				if (!result.contains(list)) {
					result.add(list);
				}

			} else {
				if (!map.containsKey(num2)) {
					map.put(num2, 1);
				}
				map.put(num2, map.get(num2) + 1);
			}
		}

		return result;
	}

	/**
	 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that
	 * their sum is equal to the given target.
	 * 
	 * Example 1: Input: 5 / \ 3 6 / \ \ 2 4 7
	 * 
	 * Target = 9
	 * 
	 * Output: True Example 2: Input: 5 / \ 3 6 / \ \ 2 4 7
	 * 
	 * Target = 28
	 * 
	 * Output: False
	 */
	public boolean findTarget(TreeNode root, int k) {
		TreeNode cur = root;
		return helper(root, cur, k);
	}

	boolean helper(TreeNode root, TreeNode cur, int k) {
		if (cur == null) {
			return false;
		}

		int toFind = k - cur.data;

		if (isInBST(root, toFind, cur)) {
			return true;
		} else {
			return helper(root, cur.left, k) || helper(root, cur.right, k);
		}
	}

	boolean isInBST(TreeNode root, int target, TreeNode source) {
		TreeNode cur = root;

		while (cur != null) {
			if (cur.data == target && source != cur) {
				return true;
			}

			if (cur.data < target) {
				cur = cur.right;
			} else {
				cur = cur.left;
			}
		}

		return false;
	}

	/**
	 * Given an array nums of n integers, find two integers in nums such that the sum is closest to a given number,
	 * target. Return the difference between the sum of the two integers and the target.
	 * 
	 * Example
	 * 
	 * Given array nums = [-1, 2, 1, -4], and target = 4.
	 * 
	 * The minimum difference is 1. (4 - (2 + 1) = 1).
	 * 
	 * T - O(n log n) + O(n) ==> O(n log n)
	 */
	public int twoSumClosest(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		if (nums.length == 2) {
			return target - nums[0] - nums[1];
		}

		Arrays.sort(nums);

		int left = 0, right = nums.length - 1, minDiff = Integer.MAX_VALUE;

		while (left < right) {
			int sum = nums[left] + nums[right];
			int diff = Math.abs(sum - target);

			if (diff == target) {
				return 0;
			}

			minDiff = Math.min(diff, minDiff);

			if (sum > target) {
				right--;
			} else {
				left++;
			}
		}

		return minDiff;
	}

	public static void main(String[] args) {
		int[] a = new int[] { 2, 7, 11, 15 };
		TwoSum two = new TwoSum();
		Arrays.stream(two.twoSum_bruteForce(a, 17)).forEach(i -> System.out.print(i + " "));
		System.out.println();
		Arrays.stream(two.twoSum_hashTable(a, 17)).forEach(i -> System.out.print(i + " "));
		System.out.println();
		Arrays.stream(two.twoSum_binarySearch(a, 17)).forEach(i -> System.out.print(i + " "));
		System.out.println();
		Arrays.stream(two.twoSum_2pointers(a, 17)).forEach(i -> System.out.print(i + " "));
		System.out.println();
		two.twoSumContainingDuplicates(new int[] { 1, 5, 2, 4, 2, 7, 4 }, 6).forEach(l -> System.out.println(l));

		System.out.println(two.sumOfTwo(new int[] { 1, 2, 3 }, new int[] { 10, 20, 30, 40 }, 50));

		System.out.println(two.twoSumClosest(new int[] { -1, 2, 1, -4 }, 4));
	}

}
