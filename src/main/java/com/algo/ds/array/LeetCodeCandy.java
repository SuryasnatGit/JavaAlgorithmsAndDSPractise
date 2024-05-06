package com.algo.ds.array;

import java.util.Arrays;

/**
 * References https://leetcode.com/problems/candy/.
 * 
 * There are N children standing in a line. Each child is assigned a rating value.
 * 
 * You are giving candies to these children subjected to the following requirements:
 * 
 * Each child must have at least one candy. Children with a higher rating get more candies than their neighbors. What is
 * the minimum candies you must give?
 * 
 * Example 1:
 * 
 * Input: [1,0,2] Output: 5 Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies
 * respectively. Example 2:
 * 
 * Input: [1,2,2] Output: 4 Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies
 * respectively. The third child gets 1 candy because it satisfies the above two conditions.
 * 
 * Category : Hard
 */
public class LeetCodeCandy {

	/**
	 * Time complexity : O(n). The array candies[] of size n is traversed twice.
	 * 
	 * Space complexity : O(n). Two arrays leftScan[] and rightScan[] of size n are used.
	 * 
	 * @param arr
	 * @return
	 */
	public int candy_approach1_2array_3passes(int[] arr) {
		int sum = 0;
		int n = arr.length;
		int[] left = new int[n];
		int[] right = new int[n];
		Arrays.fill(left, 1);
		Arrays.fill(right, 1);

		// first pass left to right
		for (int i = 1; i < n; i++) {
			if (arr[i] > arr[i - 1]) {
				left[i] = left[i - 1] + 1;
			}
		}

		// second pass right to left
		for (int i = n - 2; i >= 0; i--) {
			if (arr[i] > arr[i + 1]) {
				right[i] = right[i + 1] + 1;
			}
		}

		// third pass left to right
		for (int i = 0; i < n; i++) {
			sum += Math.max(left[i], right[i]);
		}

		return sum;
	}

	/**
	 * Time complexity : O(n). The array candies[] of size n is traversed twice.
	 * 
	 * Space complexity : O(n). An array candies[] of size n is used.
	 */
	public int candy_approach2_1array_2passes(int[] ratings) {
		int length = ratings.length;
		int[] candies = new int[length];
		Arrays.fill(candies, 1);

		// first pass
		for (int i = 1; i < length; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candies[i] = candies[i - 1] + 1;
			}
		}

		int sum = candies[length - 1];
		// second pass
		for (int i = length - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1]) {
				candies[i] = Math.max(candies[i], candies[i + 1] + 1);
			}
			sum += candies[i];
		}

		return sum;
	}

	/**
	 * TODO : understand properly.
	 * 
	 * T - O(n) S - O(1)
	 * 
	 */
	public int candyConstantSpace(int[] ratings) {
		int pointOfChange = 0;
		int totalCandies = 1;
		int currentCandy = 1;
		boolean isIndependent = true;
		int maxHeight = 0;
		int diff = 0;

		for (int i = 1; i < ratings.length; i++) {
			diff = 0;
			if (ratings[i] > ratings[i - 1]) {
				currentCandy += 1;
			} else if (ratings[i] == ratings[i - 1]) {
				isIndependent = true;
				pointOfChange = i;
				currentCandy = 1;
			} else {
				if (currentCandy == 1) {
					if (!isIndependent) {
						if (i - pointOfChange == maxHeight - 1) {
							pointOfChange--;
						}
					}
				} else {
					maxHeight = currentCandy;
					currentCandy = 1;
					isIndependent = false;
					pointOfChange = i;
				}
				diff = i - pointOfChange;
			}
			totalCandies += (diff + currentCandy);
		}
		return totalCandies;
	}

	public static void main(String[] args) {
		LeetCodeCandy candy = new LeetCodeCandy();
		System.out.println(candy.candy_approach2_1array_2passes(new int[] { 1, 2, 2 }));
		System.out.println(candy.candyConstantSpace(new int[] { 1, 2, 2 }));
		System.out.println(candy.candy_approach2_1array_2passes(new int[] { 1, 0, 2 }));
		System.out.println(candy.candyConstantSpace(new int[] { 1, 0, 2 }));
		System.out.println(candy.candyConstantSpace(new int[] { 3, 1, 2, 2, 2, 1, 4 }));
		System.out.println(candy.candyConstantSpace(new int[] { 3, 1, 2, 2, 1, 4 }));
		System.out.println(candy.candyConstantSpace(new int[] { 3, 1, 2, 1, 4 }));
		System.out.println(candy.candy_approach1_2array_3passes(new int[] { 1, 0, 2 }));
		System.out.println(candy.candy_approach1_2array_3passes(new int[] { 1, 2, 2 }));
	}
}
