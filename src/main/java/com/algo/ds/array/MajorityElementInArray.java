package com.algo.ds.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers containing duplicates, return the majority element in an array if present. A majority
 * element appears more than n/2 times where n is the size of the array.
 * 
 * For example, the majority element is 2 in the array {2, 8, 7, 2, 2, 5, 2, 3, 1, 2, 2}
 *
 */
public class MajorityElementInArray {

	// T - O(n) S - O(n)
	public int findMajorityHashing(int[] arr) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : arr) {
			if (map.containsKey(i)) {
				map.put(i, map.get(i) + 1);
			} else {
				map.put(i, 1);
			}
			if (map.get(i) > arr.length / 2) {
				return i;
			}
		}
		return -1;
	}

	// Function to return majority element present in given array. T - O(n) S - O(1)
	public int findMajorityElementOptimized(int[] A) {
		// m stores majority element if present
		int m = -1;

		// initialize counter i with 0
		int i = 0;

		// do for each element A[j] of the array
		for (int j = 0; j < A.length; j++) {
			// if the counter i becomes 0
			if (i == 0) {
				// set the current candidate to A[j]
				m = A[j];

				// reset the counter to 1
				i = 1;
			}

			// else increment the counter if A[j] is current candidate
			else if (m == A[j]) {
				i++;
			}
			// else decrement the counter if A[j] is not current candidate
			else {
				i--;
			}
		}

		return m;
	}

	public static void main(String[] args) {
		MajorityElementInArray maj = new MajorityElementInArray();
		int[] arr = { 2, 8, 7, 2, 2, 5, 2, 3, 1, 2, 2 };
		System.out.println(maj.findMajorityHashing(arr));
		System.out.println(maj.findMajorityElementOptimized(arr));
	}

}
