package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * Category : Hard
 * 
 * Tags : DFS, Recursion, Backtracking
 *
 */
public class CombinationSumProblems {

	/**
	 * Given an array of integers A and a sum B, find all unique combinations in A where the sum is equal to B. The same
	 * repeated number may be chosen from A unlimited number of times.
	 * 
	 * Note:
	 * 
	 * All numbers will be positive integers. Elements in a combination (a1, a2, … , ak) must be in non-descending
	 * order. (ie, a1 ≤ a2 ≤ … ≤ ak). The combinations themselves must be sorted in ascending order. If there is no
	 * combination possible the print "Empty" (without qoutes).
	 * 
	 * Example, Given A = 2,4,6,8 and B(sum) = 8, A solution set is:
	 * 
	 * [2, 2, 2, 2]
	 * 
	 * [2, 2, 4]
	 * 
	 * [2, 6]
	 * 
	 * [4, 4]
	 * 
	 * [8]
	 * 
	 * The first impression of this problem should be depth-first search(DFS). To solve DFS problem, recursion is a
	 * normal implementation
	 */
	public List<List<Integer>> findAllUniqueCombinationsWithRepetition(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();

		if (candidates == null || candidates.length == 0)
			return res;

		Arrays.sort(candidates);
		List<Integer> item = new ArrayList<Integer>();
		helper(candidates, 0, target, item, res);

		return res;
	}

	public void helper(int[] candidates, int index, int target, List<Integer> item, List<List<Integer>> res) {
		// base terminating case
		if (target == 0) {
			List<Integer> temp = new ArrayList<Integer>(item);
			res.add(temp);
			return;
		}

		for (int i = index; i < candidates.length; i++) {
			if (i > 0 && candidates[i] == candidates[i - 1]) // to remove duplicates
				continue;

			if (target - candidates[i] < 0)
				return;

			// try current candidate
			item.add(candidates[i]);
			// recursion to next level
			helper(candidates, i, target - candidates[i], item, res);
			// backtrack to current level
			item.remove(item.size() - 1);
		}
	}

	/**
	 * Problem 2 - Given a collection of candidate numbers (candidates) and a target number (target), find all unique
	 * combinations in candidates where the candidate numbers sums to target.
	 * 
	 * Each number in candidates may only be used once in the combination.
	 * 
	 * Note:
	 * 
	 * All numbers (including target) will be positive integers. The solution set must not contain duplicate
	 * combinations.
	 * 
	 * Example 1:
	 * 
	 * Input: candidates = [10,1,2,7,6,1,5], target = 8, A solution set is: [ [1, 7], [1, 2, 5], [2, 6], [1, 1, 6] ]
	 * 
	 * Example 2:
	 * 
	 * Input: candidates = [2,5,2,1,2], target = 5, A solution set is: [ [1,2,2], [5] ]
	 * 
	 * @param args
	 */
	public List<List<Integer>> findAllUniqueCombinationsWithoutRepetition(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();

		Arrays.sort(candidates);
		Set<Integer> visited = new HashSet<Integer>();
		helper2(res, list, candidates, target, 0, visited);
		return res;
	}

	private void helper2(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int pos,
			Set<Integer> visited) {
		if (target == 0) {
			for (int val : list) {
				System.out.print(val + "-");
			}
			System.out.println();
			res.add(new ArrayList<Integer>(list));
			return;
		}

		if (target < 0) {
			return;
		}

		for (int i = pos; i < candidates.length; i++) { // 其实不需要这个visited, haha!
			if (i != pos && candidates[i] == candidates[i - 1] && !visited.contains(i - 1)) {
				continue;
			}
			list.add(candidates[i]);
			visited.add(i);
			helper2(res, list, candidates, target - candidates[i], i + 1, visited);
			list.remove(list.size() - 1);
			visited.remove(i);
		}
	}

	/**
	 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be
	 * used and each combination should be a unique set of numbers. Example 1:
	 * 
	 * Input: k = 3, n = 7
	 * 
	 * Output:
	 * 
	 * [[1,2,4]]
	 * 
	 * Example 2:
	 * 
	 * Input: k = 3, n = 9
	 * 
	 * Output:
	 * 
	 * [[1,2,6], [1,3,5], [2,3,4]]
	 */
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();

		helper3(res, list, k, n, 1);
		return res;
	}

	private void helper3(List<List<Integer>> res, List<Integer> list, int k, int n, int pos) {
		if (list.size() == k) {
			if (n == 0) {
				for (int val : list) {
					System.out.print(val + "-");
				}
				System.out.println();
				res.add(new ArrayList<Integer>(list));
			}
			return;
		}

		if (n < 0) {
			return;
		}

		for (int i = pos; i <= 9; i++) {
			list.add(i);
			helper3(res, list, k, n - i, i + 1);
			list.remove(list.size() - 1);
		}
	}

	/**
	 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that
	 * add up to a positive integer target. Example:
	 * 
	 * nums = [1, 2, 3] target = 4
	 * 
	 * The possible combination ways are: (1, 1, 1, 1) (1, 1, 2) (1, 2, 1) (1, 3) (2, 1, 1) (2, 2) (3, 1)
	 * 
	 * Note that different sequences are counted as different combinations.
	 * 
	 * Therefore the output is 7. Follow up: What if negative numbers are allowed in the given array? How does it change
	 * the problem? What limitation we need to add to the question to allow negative numbers?
	 */
	public int combinationSum4(int[] nums, int target) {
		int[] hash = new int[target + 1]; // (1, 2, 1) (2, 1, 1) 算两个，一维数组
		hash[0] = 1;

		for (int t = 1; t <= target; t++) {
			hash[t] = 0;
			for (int i = 0; i < nums.length; i++) {
				if (t - nums[i] >= 0) {
					hash[t] += hash[t - nums[i]];
				}
			}
		}

		System.out.println(hash[target]);
		return hash[target];
	}

	// 每个元素只能用一次，求装满书包的可能性. 这个可以有重复元素
	public int combinationSum4_5(int[] nums, int target) {
		int[][] hash = new int[nums.length + 1][target + 1];
		hash[0][0] = 1;

		// 前i个物品中取 书包体积为0
		for (int i = 1; i < hash.length; i++) {
			hash[i][0] = 1;
		}

		// 前0个物品中取 书包体积为i
		for (int i = 1; i < hash[0].length; i++) {
			hash[0][i] = 0;
		}

		for (int i = 1; i <= nums.length; i++) {
			for (int t = 1; t <= target; t++) {
				hash[i][t] = hash[i - 1][t];
				if (t - nums[i - 1] >= 0) {
					hash[i][t] += hash[i - 1][t - nums[i - 1]]; // 又多一种可能
				}
			}
		}

		System.out.println(hash[nums.length][target]);
		return hash[nums.length][target];
	}

	// What if negative numbers exist
	public int combinationSum5(int[] nums, int target) {
		Set<List<Integer>> res = new HashSet<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		helper5(res, list, nums, target);

		System.out.println(res.size());
		return res.size();
	}

	// Stack over flow. Doesnt work
	private void helper5(Set<List<Integer>> res, List<Integer> list, int[] nums, int target) {
		if (target == 0) {
			res.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			list.add(nums[i]);
			helper5(res, list, nums, target - nums[i]);
			list.remove(list.size() - 1);
		}
	}

	public static void main(String[] args) {
		CombinationSumProblems cs = new CombinationSumProblems();
		List<List<Integer>> csum = cs.findAllUniqueCombinationsWithRepetition(new int[] { 2, 2, 4, 6, 8 }, 8);
		csum.forEach(s -> System.out.println(s));
	}

}
