package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	public List<List<Integer>> getPermutation(int[] arr) {
		Set<List<Integer>> resSet = new HashSet<List<Integer>>();
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();

		Arrays.sort(arr); // If using Set, no need to sort
		helperWithDuplicates(res, list, arr, visited);

		for (List<Integer> aList : res) {
			for (int val : aList) {
				System.out.print(val + "--");
			}
			System.out.println();
		}

		return res;
	}

	// What if there is duplicate?
	void helperWithDuplicates(Set<List<Integer>> res, List<Integer> list, int[] arr, Set<Integer> visited) {
		if (list.size() == arr.length) {
			res.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (visited.contains(i)) {
				continue;
			}
			visited.add(i);
			list.add(arr[i]);
			helperWithDuplicates(res, list, arr, visited);
			list.remove(list.size() - 1);
			visited.remove(i);
		}
	}

	// What if Set is not allowed. You need to figure out the duplicates
	void helperWithDuplicates(List<List<Integer>> res, List<Integer> list, int[] arr, Set<Integer> visited) {
		if (list.size() == arr.length) {
			res.add(new ArrayList<Integer>(list));
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (visited.contains(i) || (i != 0 && arr[i] == arr[i - 1] && !visited.contains(i - 1))) {
				continue;
			}
			visited.add(i);
			list.add(arr[i]);
			helperWithDuplicates(res, list, arr, visited);
			list.remove(list.size() - 1);
			visited.remove(i);
		}
	}

	public static void main(String[] args) {
		Permutations p = new Permutations();
		p.permute(new int[] { 1, 2, 3 }).forEach(a -> System.out.println(a));
	}
}
