package com.algo.ds.array;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/check-if-array-elements-are-consecutive/
 * 
 * Given an unsorted array of numbers, write a function that returns true if array consists of consecutive numbers.
 * 
 * Examples: a) If array is {5, 2, 3, 1, 4}, then the function should return true because the array has consecutive
 * numbers from 1 to 5.
 * 
 * b) If array is {83, 78, 80, 81, 79, 82}, then the function should return true because the array has consecutive
 * numbers from 78 to 83.
 * 
 * c) If the array is {34, 23, 52, 12, 3 }, then the function should return false because the elements are not
 * consecutive.
 * 
 * d) If the array is {7, 6, 5, 5, 3, 4}, then the function should return false because 5 and 5 are not consecutive.
 * 
 * 
 */
public class CheckIfArrayElementsAreConsecutive {

	/**
	 * Time Complexity: O(nLogn)
	 * 
	 * @param input
	 * @return
	 */
	public boolean areConsequetive_usingSorting(int[] input) {
		Arrays.sort(input); // O(n log n)
		for (int i = 0; i < input.length - 1; i++) {
			if (input[i + 1] - input[i] != 1)
				return false;
		}
		return true;
	}

	/**
	 * Time Complexity: O(n) Extra Space: O(n)
	 * 
	 * @param input
	 * @param n
	 * @return
	 */
	public boolean areConsequetive_usingVisitedArray(int[] input, int n) {
		if (n < 1)
			return false;

		int min = findMin(input, n);

		int max = findMax(input, n);

		if (max - min + 1 == n) { // then only check for all elements
			// have a visited array of size n
			boolean[] arr = new boolean[n];
			for (int i = 0; i < n; i++) {
				if (arr[input[i] - min] != false) // this means the element is already visited
					return false;
				arr[input[i] - min] = true;
			}
			return true;
		}
		return false;
	}

	private int findMin(int[] input, int n) {
		int min = input[0];
		for (int i = 1; i < n; i++) {
			if (input[i] < min)
				min = input[i];
		}
		return min;
	}

	private int findMax(int[] input, int n) {
		int max = input[0];
		for (int i = 1; i < n; i++) {
			if (input[i] > max)
				max = input[i];
		}
		return max;
	}

	/**
	 * This method is O(n) time complexity and O(1) extra space, but it changes the original array and it works only if
	 * all numbers are positive. We can get the original array by adding an extra step though. It is an extension of
	 * method 2 and it has the same two steps.
	 * 
	 * Time Complexity: O(n) Extra Space: O(1)
	 * 
	 * @param input
	 * @return
	 */
	public boolean areConsecutive(int input[]) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < input.length; i++) {
			if (input[i] < min) {
				min = input[i];
			}
		}
		for (int i = 0; i < input.length; i++) {
			if (Math.abs(input[i]) - min >= input.length) {
				return false;
			}
			if (input[Math.abs(input[i]) - min] < 0) {
				return false;
			}
			input[Math.abs(input[i]) - min] = -input[Math.abs(input[i]) - min];
		}
		for (int i = 0; i < input.length; i++) {
			input[i] = Math.abs(input[i]);
		}
		return true;
	}

	public static void main(String args[]) {
		int input[] = { 76, 78, 75, 77, 71, 74 };
		CheckIfArrayElementsAreConsecutive cia = new CheckIfArrayElementsAreConsecutive();
		System.out.println(cia.areConsecutive(input));
		System.out.println(cia.areConsequetive_usingSorting(input));
		System.out.println(cia.areConsequetive_usingVisitedArray(input, input.length));
	}
}
