package com.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * Shuffle a set of numbers without duplicates.
 * 
 * Example:
 * 
 * // Init an array with set 1, 2, and 3. int[] nums = {1,2,3}; Solution solution = new Solution(nums);
 * 
 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 * solution.shuffle();
 * 
 * // Resets the array back to its original configuration [1,2,3]. solution.reset();
 * 
 * // Returns the random shuffling of array [1,2,3]. solution.shuffle();
 * 
 * Priority : Medium
 *
 */
public class ShuffleArray {

	private int[] array;
	private int[] original;

	public ShuffleArray(int[] nums) {
		this.array = nums;
		this.original = nums.clone();
	}

	public int[] reset() {
		original = original.clone();
		return original;
	}

	public int[] shuffle() {
		Random ran = new Random();

		for (int i = 0; i < array.length; i++) {
			int next = ran.nextInt(array.length - i) + i;
			swap(array, i, next);
		}
		return array;
	}

	private void swap(int[] arr, int i, int index) {
		int tmp = arr[i];
		arr[i] = arr[index];
		arr[index] = tmp;
	}

	public static void main(String[] args) {
		int[] arr = { 0, 1, 2, 3, 4, 5 };
		ShuffleArray sa = new ShuffleArray(arr);

		System.out.println(Arrays.toString(sa.shuffle()));
		System.out.println(Arrays.toString(sa.reset()));
		System.out.println(Arrays.toString(sa.shuffle()));

	}
}
