package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PrimeSet {

	public void primeSet(int[] nums) {
		List<Integer> set = new ArrayList<>();

		dfs(nums, set, 0, 1);

		set.forEach(a -> System.out.println(a));
	}

	private void dfs(int[] nums, List<Integer> set, int index, int num) {
		if (num != 1)
			set.add(num);

		for (int i = index; i < nums.length; i++) {
			dfs(nums, set, i + 1, num * nums[i]);
		}
	}

	public static void main(String[] args) {
		PrimeSet ps = new PrimeSet();
		ps.primeSet(new int[] { 2, 3, 5 });
	}
}
