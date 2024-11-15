package com.algo.ds.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, find largest sub-array formed by consecutive integers. The sub-array should contain all
 * distinct values.
 * 
 * The idea is to consider every sub-array and keep track of largest subarray found so far which is formed by
 * consecutive integers. In order for an sub-array to contain consecutive integers,
 * 
 * The difference between maximum and minimum element in it should be exactly equal to length of the subarray minus one.
 * 
 * All elements in the array should be distinct (we can check this by inserting the elements in set or using a visited
 * array).
 * 
 * for example, input = {2,0,2,1,4,3,1,0} output = {0,2,1,4,3}
 * 
 * Category : Medium
 * 
 * TODO : to practice
 *
 */
public class LargestSubarrayFormedByConsequetiveIntegers {

	public void largestSubarray(int[] arr) {
		int min = 0, max = 0, left = 0, right = 0, length = 1;

		for (int i = 0; i < arr.length - 1; i++) {
			min = arr[i];
			max = arr[i];

			for (int j = i + 1; j < arr.length; j++) {
				min = Math.min(min, arr[j]);
				max = Math.max(max, arr[j]);

				if (isConsequetive(arr, i, j, min, max)) {
					if (length < max - min + 1) {
						length = max - min + 1;
						left = i;
						right = j;
					}
				}
			}
		}

		System.out.println("max subarray lies between index " + left + " and " + right);
	}

	private boolean isConsequetive(int[] arr, int i, int j, int min, int max) {

		if (max - min != j - i)
			return false;

		Set<Integer> set = new HashSet<Integer>();
		for (int c = i; c <= j; c++) {
			if (set.contains(arr[c]))
				return false;

			set.add(arr[c]);
		}

		return true;
	}

	public static void main(String[] args) {
		LargestSubarrayFormedByConsequetiveIntegers l = new LargestSubarrayFormedByConsequetiveIntegers();
		l.largestSubarray(new int[] { 2, 0, 2, 1, 4, 3, 1, 0 });
	}
}
