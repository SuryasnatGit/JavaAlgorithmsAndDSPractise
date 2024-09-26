package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.techiedelight.com/find-distinct-combinations-of-given-length/
 * 
 * https://www.techiedelight.com/find-distinct-combinations-given-length-repetition-allowed/
 * 
 * Category : Medium
 * 
 */
public class DistinctCombinationOfGivenLength {

	/*
	 * Given an integer array, find all distinct combinations of a given length k.
	 * 
	 * For example,
	 * 
	 * Input: {2, 3, 4}, k = 2 Output: {2, 3}, {2, 4}, {3, 4}
	 * 
	 * Input: {1, 2, 1}, k = 2 Output: {1, 2}, {1, 1}, {2, 1}
	 * 
	 * The program should print all the distinct combinations, while preserving the relative order of elements as they
	 * appear in the array.
	 */
	public void findDistinctCombinationWhereRepetetionNotAllowed(int[] arr, int k) {
		System.out.println("Input :" + Arrays.toString(arr));
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

	public void findDistinctCombinationWhereRepetetionAllowed(int[] arr, int k) {
		System.out.println("Input :" + Arrays.toString(arr));
		recur(arr, new ArrayList<>(), k, 0, arr.length);
	}

	// Function to print all distinct combinations of length k where
	// repetition of elements is allowed
	public void recur(int[] A, List<Integer> out, int k, int start, int length) {
		// base case: if combination size is k, print it
		if (out.size() == k) {
			System.out.println(out);
			return;
		}

		// start from previous element in the current combination
		// till last element
		for (int j = start; j < length; j++) {
			// add current element A[j] to the solution and recur with
			// same index j (as repeated elements are allowed in combinations)
			out.add(A[j]);
			recur(A, out, k, j, length);

			// backtrack - remove current element from solution
			out.remove(out.size() - 1);

			// code to handle duplicates - skip adjacent duplicate elements
			while (j < length - 1 && A[j] == A[j + 1]) {
				j++;
			}
		}
	}

	public static void main(String[] args) {
		DistinctCombinationOfGivenLength dis = new DistinctCombinationOfGivenLength();
		System.out.println("findDistinctCombinationWhereRepetetionNotAllowed");
		dis.findDistinctCombinationWhereRepetetionNotAllowed(new int[] { 1, 2, 3 }, 2);
		System.out.println();
		// to print distinct element combination in case of repeat elements in input, sort the input array

		int[] arr = { 1, 3, 1 };
		Arrays.sort(arr);

		dis.findDistinctCombinationWhereRepetetionNotAllowed(arr, 2);

		System.out.println("findDistinctCombinationWhereRepetetionAllowed");

		dis.findDistinctCombinationWhereRepetetionAllowed(new int[] { 1, 2, 3 }, 2);
		System.out.println();
		int[] arr1 = { 1, 3, 1 };
		Arrays.sort(arr1);

		dis.findDistinctCombinationWhereRepetetionAllowed(arr1, 2);
	}
}
