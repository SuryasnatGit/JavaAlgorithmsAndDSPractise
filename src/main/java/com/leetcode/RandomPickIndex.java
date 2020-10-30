package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can
 * assume that the given target number must exist in the array.
 * 
 * Note: The array size can be very large. Solution that uses too much extra space will not pass the judge.
 * 
 * Example:
 * 
 * int[] nums = new int[] {1,2,3,3,3}; Solution solution = new Solution(nums);
 * 
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 * 
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1. solution.pick(1);
 * 
 * Category : Medium
 */
public class RandomPickIndex {

	// For Approach 1 - using map. T - O(N) S - O(N)
	private Map<Integer, List<Integer>> map;
	private Random random;

	// For approach 2
	private int[] nums;

	public RandomPickIndex(int[] nums) {
		this.map = new HashMap<>();
		this.random = new Random();

		for (int i = 0; i < nums.length; i++) {
			if (!map.containsKey(nums[i])) {
				map.put(nums[i], new ArrayList<Integer>());
			}
			map.get(nums[i]).add(i);
		}

		this.nums = nums;
	}

	public int pick1(int target) {
		List<Integer> list = map.get(target);
		int randomIndex = random.nextInt(list.size());
		return list.get(randomIndex);
	}

	// Approach 2
	public int pick2(int target) {
		int res = -1;
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target) {
				if (random.nextInt(++count) == 0) { // The probability of 0 is 1/N
					res = i;
				}
			}
		}

		return res;
	}

	public static void main(String[] args) {
		RandomPickIndex rpi = new RandomPickIndex(new int[] { 1, 2, 3, 3, 3 });
		System.out.println(rpi.pick1(1));
		System.out.println(rpi.pick1(1));
		System.out.println(rpi.pick1(3));
		System.out.println(rpi.pick1(3));
		System.out.println(rpi.pick1(3));
		System.out.println(rpi.pick1(3));
		System.out.println(rpi.pick1(3));
		System.out.println("****************");
		System.out.println(rpi.pick2(3));
		System.out.println(rpi.pick2(3));
		System.out.println(rpi.pick2(3));
		System.out.println(rpi.pick2(3));
		System.out.println(rpi.pick2(3));
	}
}
