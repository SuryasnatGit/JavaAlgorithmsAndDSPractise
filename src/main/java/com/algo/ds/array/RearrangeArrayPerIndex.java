package com.algo.ds.array;

import java.util.Arrays;

/**
 * Given an array of size n where elements are in range 0 to n-1. Rearrange elements of array such
 * that if arr[i] = j then arr[j] becomes i.
 *
 * http://www.geeksforgeeks.org/rearrange-array-arrj-becomes-arri-j/.
 * 
 * Example 1: Input: arr[] = {1, 3, 0, 2}; Output: arr[] = {2, 0, 3, 1}; Explanation for the above
 * output. Since arr[0] is 1, arr[1] is changed to 0 Since arr[1] is 3, arr[3] is changed to 1 Since
 * arr[2] is 0, arr[0] is changed to 2 Since arr[3] is 2, arr[2] is changed to 3
 * 
 * 
 */
public class RearrangeArrayPerIndex {

	/**
	 * A Simple Solution is to create a temporary array and one by one copy i to temp[arr[i]] where
	 * i varies from 0 to n-1.
	 *
	 * time - O(n), Space - O(n)
	 * 
	 * @param input
	 */
	public void rearrange_simple(int[] input) {
		int[] temp = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			temp[input[i]] = i;
		}
		for (int i = 0; i < input.length; i++) {
			input[i] = temp[i];
		}
	}

	/**
	 * Difficult to understand solution. Time complexity O(n) Space complexity O(1)
	 * 
	 * @param input
	 */
	public void rearrange(int input[]) {

		for (int i = 0; i < input.length; i++) {
			input[i]++;
		}

		for (int i = 0; i < input.length; i++) {
			if (input[i] > 0) {
				rearrangeUtil(input, i);
			}
		}

		for (int i = 0; i < input.length; i++) {
			input[i] = -input[i] - 1;
		}
	}

	private void rearrangeUtil(int input[], int start) {
		int i = start + 1;
		int v = input[start];
		while (v > 0) {
			int t = input[v - 1];
			input[v - 1] = -i;
			i = v;
			v = t;
		}
	}

	/**
	 * The idea is to store each elements new and old value as quotient and remainder of n,
	 * respectively (n being the size of the array). For example, Suppose an elements new value is 2,
	 * the old value is 1 and n is 3, then the elements value is stored as 1 + 2*3 = 7. We can retrieve
	 * its old value by 7%3 = 1 and its new value by 7/3 = 2.
	 * 
	 * Time - O(n) Space - O(1)
	 * 
	 * @param input
	 */
	public void rearrange_another_simple(int[] input) {
		int n = input.length;
		for (int i = 0; i < n; i++) {
			input[input[i] % n] += i * n;
		}
		for (int i = 0; i < n; i++) {
			input[i] /= n;
		}
	}

	public static void main(String args[]) {
		RearrangeArrayPerIndex rai = new RearrangeArrayPerIndex();
		int input[] = { 1, 2, 0, 5, 3, 4 };
		// rai.rearrange(input);
		// rai.rearrange_simple(input);
		rai.rearrange_another_simple(input);
		Arrays.stream(input).forEach(i -> System.out.print(i + " "));
	}

}
