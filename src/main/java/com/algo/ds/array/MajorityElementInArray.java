package com.algo.ds.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.techiedelight.com/find-majority-element-in-an-array-boyer-moore-majority-vote-algorithm/
 *
 */
public class MajorityElementInArray {

	// T - O(n^2)
	public int findMajority1(int[] arr) {
		int n = arr.length;
		for (int i = 0; i <= n / 2; i++) {
			int count = 1;

			for (int j = i + 1; j < n; j++) {
				if (arr[j] == arr[i])
					count++;
			}

			if (count > n / 2)
				return arr[i];
		}
		return -1;
	}

	// T - O(n) S - O(n)
	public int findMajority2(int[] arr) {
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

	// Function to return majority element present in given array. T - O(n)
	public int majorityElement(int[] A) {
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
		System.out.println(maj.findMajority1(arr));
		System.out.println(maj.findMajority2(arr));
		System.out.println(maj.majorityElement(arr));
	}

}
