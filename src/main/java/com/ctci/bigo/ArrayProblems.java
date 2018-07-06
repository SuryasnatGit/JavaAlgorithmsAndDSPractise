package com.ctci.bigo;

import java.util.HashMap;
import java.util.Map;

public class ArrayProblems {

	/**
	 * Given two sorted arrays, find the number of elements in common. The arrays are the same length
	 * and each has all distinct elements. complexity - O(N^2)
	 * 
	 * @return
	 */
	public int numberOfCommonElementsIn2SortedArray(int[] a, int[] b) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				if (a[i] == b[j])
					count++;
			}
		}
		return count;
	}

	public int numberOfCommonElementsIn2SortedArray_hashTable(int[] a, int[] b) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < a.length; i++) { // O(N)
			map.put(a[i], a[i]);// O(1)
		}
		for (int i = 0; i < b.length; i++) {// O(N)
			if (map.containsKey(b[i]))
				count++;
		}
		return count;
	}

	public static void main(String[] args) {
		ArrayProblems ap = new ArrayProblems();

		int[] a = { 13, 17, 39, 40, 55, 59, 78 };
		int[] b = { 15, 19, 39, 43, 55, 60, 78 };
		System.out.println(ap.numberOfCommonElementsIn2SortedArray(a, b));
	}

}
