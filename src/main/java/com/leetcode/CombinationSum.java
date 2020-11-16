package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Category : Medium, Hard
 * 
 * Tags : DFS, Recursion, Backtracking
 *
 */
public class CombinationSum {

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
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {

	}

	// TODO : combination sum 2, 3, 4
	public static void main(String[] args) {
		CombinationSum cs = new CombinationSum();
		List<List<Integer>> csum = cs.combinationSum(new int[] { 2, 2, 4, 6, 8 }, 8);
		csum.forEach(s -> System.out.println(s));
	}

}
