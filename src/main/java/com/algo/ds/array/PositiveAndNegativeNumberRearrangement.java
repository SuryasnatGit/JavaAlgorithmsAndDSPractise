package com.algo.ds.array;

import java.util.Arrays;

/**
 * Given an array of positive and negative numbers, arrange them such that all negative integers
 * appear before all the positive integers in the array.The order of appearance should be
 * maintained.
 * 
 */
public class PositiveAndNegativeNumberRearrangement {

	/**
	 * Time - O(n), space -O(n)
	 * 
	 * @param input
	 * @return
	 */
	public int[] rearrangeWithExtraSpace(int[] input) {
		int n = input.length;
		int[] temp = new int[n];
		int c = 0;
		for (int i = 0; i < n; i++) {
			if (input[i] >= 0)
				temp[c++] = input[i];
		}
		for (int i = 0; i < n; i++) {
			if (input[i] < 0)
				temp[c++] = input[i];
		}
		System.arraycopy(temp, 0, input, 0, n);
		return input;
	}

	/**
	 * Using modified insertion sort.
	 * 
	 * We can modify insertion sort to solve this problem.
	 * 
	 * Algorithm –
	 * 
	 * Loop from i = 1 to n - 1. a) If the current element is positive, do nothing. b) If the current
	 * element arr[i] is negative, we insert it into sequence arr[0..i-1] such that all positive
	 * elements in arr[0..i-1] are shifted one position to their right and arr[i] is inserted at index
	 * of first positive element.
	 * 
	 * time - O(n^2), space - O(1)
	 * 
	 * @param input
	 * @return
	 */
	public int[] rearrangeWithoutExtraSpace1(int[] input) {
		int n = input.length;
		for (int i = 1; i < n; i++) {
			int key = input[i];
			// if current element is positive just continue
			if (key > 0)
				continue;
			int j = i - 1;
			// if current element is negative then shift all positive (0..i-1) elements to one position to right
			while (j >= 0 && input[j] > 0) {
				input[j + 1] = input[j];
				j--;
			}
			input[j + 1] = key;
		}
		return input;
	}

	/**
	 * Using modified merge sort.
	 * 
	 * Let Ln and Lp denotes the negative part and positive part of left sub-array respectively.
	 * Similarly, Rn and Rp denotes the negative and positive part of right sub-array respectively.
	 * Below are the steps to convert [Ln Lp Rn Rp] to [Ln Rn Lp Rp] without using extra space.
	 * 
	 * 1. Reverse Lp and Rn. We get [Lp] -> [Lp'] and [Rn] -> [Rn'] [Ln Lp Rn Rp] -> [Ln Lp’ Rn’ Rp]
	 * 
	 * 2. Reverse [Lp’ Rn’]. We get [Rn Lp]. [Ln Lp’ Rn’ Rp] -> [Ln Rn Lp Rp]
	 * 
	 * Time complexity of above solution is O(n log n), O(Log n) space for recursive calls, and no
	 * additional data structure.
	 * 
	 * 
	 * @param input
	 * @return
	 */
	public int[] rearrangeWithoutExtraSpace2(int[] input) {
		int n = input.length;
		int l = 0;
		int r = n - 1;
		rearrangeWithoutExtraSpace2(input, l, r);
		return input;
	}

	private void rearrangeWithoutExtraSpace2(int[] input, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2; // to prevent overflow
			rearrangeWithoutExtraSpace2(input, left, mid);
			rearrangeWithoutExtraSpace2(input, mid + 1, right);

			// finally merge
			merge(input, left, mid, right);
		}
	}

	private void merge(int[] input, int left, int mid, int right) {
		int i = left; // starting point of left half
		int j = mid + 1; // starting point of right half

		// increment i for all negative numbers in left half
		while (i <= mid && input[i] < 0)
			i++;
		// that means input[i .. m] is positive

		// increment j for all negative numbers in right half
		while (j <= right && input[j] < 0)
			j++;
		// that means input[j .. right] is positive

		// reverse positive part of left sub-array(input[i..m])
		reverse(input, i, mid);

		// reverse negative part of right sub-array(input[m+1..j-1])
		reverse(input, mid + 1, j - 1);

		// reverse input[i..j-1]
		reverse(input, i, j - 1);
	}

	/**
	 * reversing array in O(n) time and O(1) space
	 * 
	 * @param input
	 * @param left
	 * @param right
	 */
	private void reverse(int[] input, int left, int right) {
		if (left < right) {
			swap(input, left, right);
			reverse(input, ++left, --right);
		}
	}

	private void swap(int[] input, int left, int right) {
		int temp = input[left];
		input[left] = input[right];
		input[right] = temp;
	}

	public static void main(String[] args) {
		PositiveAndNegativeNumberRearrangement po = new PositiveAndNegativeNumberRearrangement();
		int[] input = new int[] { -1, 2, -3, 4, -5, 6, 7, -8, -9 };
		System.out.println(Arrays.toString(input));
		System.out.println(Arrays.toString(po.rearrangeWithoutExtraSpace2(input)));
	}
}
