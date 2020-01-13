package com.algo.ds.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.techiedelight.com/find-distinct-combinations-of-given-length/
 * 
 * https://www.techiedelight.com/find-distinct-combinations-given-length-repetition-allowed/
 * 
 */
public class DistinctCombinationOfGivenLength {

	public void findDistinctCombination(int[] arr, int k) {
		combinationRecursive(arr, "", 0, arr.length, k);
	}

	private void combinationRecursive(int[] arr, String out, int i, int length, int k) {
		// if k is > length of string
		if (k > length)
			return;

		// if k is 0 then print result
		if (k == 0) {
			System.out.println(out);
			return;
		}

		for (int j = i; j < length; j++) {
			// add current element arr[j] to solution and recur for next index(j+1) with one less element(k-1)
			combinationRecursive(arr, out + " " + arr[j], j + 1, length, k - 1);

			// uncomment below code to handle duplicates
			while (j < length - 1 && arr[j] == arr[j + 1]) {
				j++;
			}
		}
	}

	// Function to print all distinct combinations of length k where
	// repetition of elements is allowed
	public void recur(int[] A, List<Integer> out, int k, int i, int n) {
		// base case: if combination size is k, print it
		if (out.size() == k) {
			System.out.println(out);
			return;
		}

		// start from previous element in the current combination
		// till last element
		for (int j = i; j < n; j++) {
			// add current element A[j] to the solution and recur with
			// same index j (as repeated elements are allowed in combinations)
			out.add(A[j]);
			recur(A, out, k, j, n);

			// backtrack - remove current element from solution
			out.remove(out.size() - 1);

			// code to handle duplicates - skip adjacent duplicate elements
			while (j < n - 1 && A[j] == A[j + 1]) {
				j++;
			}
		}
	}

	public static void main(String[] args) {
		DistinctCombinationOfGivenLength dis = new DistinctCombinationOfGivenLength();
		int[] arr = { 1, 1, 3 };
		int k = 2;
		// dis.findDistinctCombination(arr, k);
		dis.recur(arr, new ArrayList<>(), 2, 0, arr.length);
	}
}
