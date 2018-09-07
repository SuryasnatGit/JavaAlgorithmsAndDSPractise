package com.algo.ds.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Write a function that determines whether a array contains duplicate characters within k indices
 * of each other.
 * 
 * Input: k = 3, arr[] = {1, 2, 3, 4, 1, 2, 3, 4} Output: false All duplicates are more than k
 * distance away.
 * 
 * Input: k = 3, arr[] = {1, 2, 3, 1, 4, 5} Output: true 1 is repeated at distance 3.
 * 
 * Input: k = 3, arr[] = {1, 2, 3, 4, 5} Output: false
 * 
 * Input: k = 3, arr[] = {1, 2, 3, 4, 4} Output: true
 */
public class DuplicateWithinkIndices {

	/**
	 * complexity - O(nk)
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public boolean duplicate_simple(int[] arr, int k) {
		if (k >= arr.length)
			return false;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 1; j < k; j++) {
				if (arr[i] == arr[j]) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean duplicate(int arr[], int k) {
		if (k >= arr.length)
			return false;
		Set<Integer> visited = new HashSet<Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (visited.contains(arr[i])) {
				return true;
			}
			if (i >= k) {
				visited.remove(arr[i - k]);
			}
			visited.add(arr[i]);
		}
		return false;
	}

	public static void main(String args[]) {
		int arr[] = { 1, 2, 3, 11, 2, 5, 6 };
		DuplicateWithinkIndices dk = new DuplicateWithinkIndices();
		System.out.println(dk.duplicate(arr, 3));
		System.out.println(dk.duplicate_simple(arr, 3));
	}
}
