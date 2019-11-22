package com.algo.backtracking;

import java.util.Arrays;

/**
 * 
 * Given a positive number N, find all combinations of 2*N elements such that every element from 1 to N appears exactly
 * twice and distance between its two appearances is exactly equal to value of the element.
 * 
 * for example, N = 3
 * 
 * 3 1 2 1 3 2
 * 
 * 2 3 1 2 1 3
 * 
 * @author surya
 *
 */
public class ElementCombinationWithConstraints {

	/**
	 * 
	 * @param arr
	 *            input array
	 * @param elem
	 *            particular element in the array
	 * @param n
	 *            length
	 */
	public void combination(int[] arr, int elem, int n) {
		// base case
		if (elem > n) {
			// print the array
			System.out.println(Arrays.toString(arr));
			return;
		}

		for (int i = 0; i < 2 * n; i++) {
			// check if both element positions in the array are vacant(-1) and i + k + 1 < 2n. this order of condition
			// is important as we need to check the length before checking the -1
			if (arr[i] == -1 && (i + elem + 1) < 2 * n && arr[i + elem + 1] == -1) {
				// place elem in both positions
				arr[i] = elem;
				arr[i + elem + 1] = elem;

				// recur for next element
				combination(arr, elem + 1, n);

				// back track
				arr[i] = -1;
				arr[i + elem + 1] = -1;
			}
		}
	}

	public static void main(String[] args) {
		ElementCombinationWithConstraints com = new ElementCombinationWithConstraints();
		int n = 3;
		int[] arr = new int[2 * n];
		Arrays.fill(arr, -1);

		com.combination(arr, 1, n);
	}
}
