package com.algo.ds.array;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/count-frequencies-elements-array-o1-extra-space-time/
 * 
 * Category : Hard
 *
 */
public class FrequencyOfEachElementInUnsortedArray {

	// T - O(n) S - O(1)
	public void findCounts1(int[] arr) {
		System.out.println("Original array :" + Arrays.toString(arr));

		int i = 0;
		int n = arr.length;

		while (i < n) {
			// if value is < 0 then continue
			if (arr[i] <= 0) {
				i++;
				continue;
			}

			// for example, index of 5 is 5-1 = 4
			int elemIndex = arr[i] - 1;

			if (arr[elemIndex] > 0) {
				arr[i] = arr[elemIndex];
				arr[elemIndex] = -1;
			} else {
				// decrement further
				arr[elemIndex]--;
				arr[i] = 0;
				i++;
			}
		}

		System.out.println("After conversion :" + Arrays.toString(arr));
		for (int c = 0; c < n; c++) {
			System.out.println("Element " + (c + 1) + " occurs " + Math.abs(arr[c]) + " times");
		}
	}

	public void findCounts2(int[] arr) {
		System.out.println("Original array :" + Arrays.toString(arr));
		int n = arr.length;

		// decrement each element by 1
		for (int i = 0; i < n; i++) {
			arr[i] = arr[i] - 1;
		}

		for (int i = 0; i < n; i++) {
			arr[arr[i] % n] = arr[arr[i] % n] + n;
		}

		// retrieve counts
		for (int i = 0; i < n; i++) {
			arr[i] = arr[i] / n;
		}
		System.out.println("After conversion :" + Arrays.toString(arr));
		for (int c = 0; c < n; c++) {
			System.out.println("Element " + (c + 1) + " occurs " + arr[c] + " times");
		}
	}

	public static void main(String[] args) {
		FrequencyOfEachElementInUnsortedArray fr = new FrequencyOfEachElementInUnsortedArray();
		fr.findCounts1(new int[] { 2, 3, 3, 2, 5 });
		fr.findCounts2(new int[] { 2, 3, 3, 2, 5 });
	}
}
