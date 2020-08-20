
package com.algo.ds.array;

/**
 *
 * Given an unsorted array find maximum gap between consecutive element in sorted array.
 *
 * Return 0 if the array contains less than 2 elements.
 * 
 * Example 1:
 * 
 * Input: [3,6,9,1]
 * 
 * Output: 3
 * 
 * Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
 * 
 * Example 2:
 * 
 * Input: [10]
 * 
 * Output: 0
 * 
 * Explanation: The array contains less than 2 elements, therefore return 0.
 * 
 * Time complexity O(n) Space complexity O(n)
 *
 * Reference https://leetcode.com/problems/maximum-gap/
 * 
 * Category : Hard
 */
public class MaximumGap {

	class Bucket {
		int low;
		int high;
		boolean isSet = false;

		void update(int val) {
			if (!isSet) {
				low = val;
				high = val;
				isSet = true;
			} else {
				low = Math.min(low, val);
				high = Math.max(high, val);
			}
		}
	}

	public int maximumGap(int[] input) {
		if (input == null || input.length < 2) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < input.length; i++) {
			min = Math.min(min, input[i]);
			max = Math.max(max, input[i]);
		}

		int gap = (int) Math.ceil((double) (max - min) / (input.length - 1));

		Bucket[] buckets = new Bucket[input.length - 1];

		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new Bucket();
		}

		for (int i = 0; i < input.length; i++) {
			if (input[i] == max || input[i] == min) {
				continue;
			}
			buckets[(input[i] - min) / gap].update(input[i]);
		}

		int prev = min;
		int maxGap = 0;
		for (int i = 0; i < buckets.length; i++) {
			if (!buckets[i].isSet) {
				continue;
			}
			maxGap = Math.max(maxGap, buckets[i].low - prev);
			prev = buckets[i].high;
		}

		return Math.max(maxGap, max - prev);
	}

	public static void main(String args[]) {
		int[] input = { 4, 3, 13, 2, 9, 7 };
		MaximumGap mg = new MaximumGap();
		System.out.println(mg.maximumGap(input));
	}
}
