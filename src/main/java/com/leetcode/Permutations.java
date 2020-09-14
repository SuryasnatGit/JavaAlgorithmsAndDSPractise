package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 * 
 * Example:
 * 
 * Input: [1,2,3]
 * 
 * 
 * Output: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
 *
 */
public class Permutations {

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		recurse(0, nums, result);
		return result;
	}

	private void recurse(int index, int[] nums, List<List<Integer>> result) {
		if (index == nums.length - 1) {
			List<Integer> res = new ArrayList<>();
			for (int num : nums) {
				res.add(num);
			}
			result.add(res);
			return;
		}

		for (int i = index; i < nums.length; i++) {
			swap(nums, i, index);
			recurse(index + 1, nums, result);
			swap(nums, i, index); // swap back
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		Permutations p = new Permutations();
		p.permute(new int[] { 1, 2, 3 }).forEach(a -> System.out.println(a));
	}
}
