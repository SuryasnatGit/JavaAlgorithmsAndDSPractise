package com.algo.ds.array;

public class BitonicArray {

	/**
	 * Find the maximum value in a given Bitonic array. An array is considered bitonic if it is monotonically increasing
	 * and then monotonically decreasing. Monotonically increasing or decreasing means that for any index i in the array
	 * arr[i] != arr[i+1]. Example: Input: [1, 3, 8, 12, 4, 2], Output: 12
	 * 
	 * @param arr
	 * @return
	 */
	public int maxElement(int[] arr) {
		int start = 0, end = arr.length - 1;

		while (start < end) {
			int mid = start + (end - start) / 2;
			if (arr[mid] > arr[mid + 1]) {
				// we are in descending part of array. max is mid or before mid
				end = mid;
			} else {
				// we are in ascending part of array. max is mid + 1
				start = mid + 1;
			}
		}

		// at the end of the while loop start == end
		return arr[start];
	}

	public static void main(String[] args) {
		BitonicArray ba = new BitonicArray();
		System.out.println(ba.maxElement(new int[] { 1, 3, 8, 12, 4, 2 }));
		System.out.println(ba.maxElement(new int[] { 3, 8, 3, 1 }));
		System.out.println(ba.maxElement(new int[] { 1, 3, 8, 12 }));
		System.out.println(ba.maxElement(new int[] { 10, 9, 8 }));
	}
}
