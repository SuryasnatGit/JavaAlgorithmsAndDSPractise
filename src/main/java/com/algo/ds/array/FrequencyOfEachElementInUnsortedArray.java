package com.algo.ds.array;

import java.util.Arrays;

/**
 * Given an unsorted array of n integers which can contain integers from 1 to n. Some elements can be repeated multiple
 * times and some other elements can be absent from the array. Count frequency of all elements that are present and
 * print the missing elements.
 * 
 * Category : Hard
 *
 */
public class FrequencyOfEachElementInUnsortedArray {

	// T - O(n) S - O(n)
	public void findCounts1(int[] arr) {
		int len = arr.length;
		int[] hash = new int[len];

		for (int i = 0; i < len; i++) {
			hash[arr[i] - 1]++;
		}

		for (int i = 0; i < len; i++) {
			System.out.print(i + 1 + " -> " + hash[i]);
			System.out.println();
		}
	}

	/**
	 * idea is to traverse the given array, use elements as an index and store their counts at the index. T - O(n) S -
	 * O(1)
	 */
	public void findCounts2(int[] arr) {
		System.out.println("Original array :" + Arrays.toString(arr));
		int n = arr.length;

		// decrement each element by 1, so array elements are now in range of 0 to n-1
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
