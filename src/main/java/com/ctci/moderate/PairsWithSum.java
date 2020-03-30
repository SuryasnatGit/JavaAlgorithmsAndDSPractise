package com.ctci.moderate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pairs with Sum: Design an algorithm to find all pairs of integers within an array which sum to a specified value.
 * 
 * CTCI : 16.24
 *
 */
public class PairsWithSum {

	class Pair {
		int num1;
		int num2;

		public Pair(int n1, int n2) {
			this.num1 = n1;
			this.num2 = n2;
		}

		@Override
		public String toString() {
			return "Pair [num1=" + num1 + ", num2=" + num2 + "]";
		}
	}

	// T - O(n^2)
	public List<Pair> findSumPairs1(int[] arr, int sum) {
		List<Pair> result = new ArrayList<>();

		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] + arr[j] == sum) {
					Pair p = new Pair(arr[i], arr[j]);
					result.add(p);
				}
			}
		}

		return result;
	}

	// Optimized. T - O(n log n) + O(n) ~= O(n log n)
	public List<Pair> findSumPairs2(int[] arr, int sum) {
		List<Pair> result = new ArrayList<>();

		// sort
		Arrays.sort(arr);

		// binary search
		int left = 0;
		int right = arr.length - 1;

		while (left < right) {
			int s = arr[left] + arr[right];
			if (s == sum) {
				Pair p = new Pair(arr[left], arr[right]);
				result.add(p);
				left++;
				right--;
			} else if (s < sum) {
				left++;
			} else {
				right--;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		PairsWithSum ps = new PairsWithSum();
		int[] arr = { 3, 1, 5, 2, 6 };
		System.out.println(ps.findSumPairs1(arr, 8));
		System.out.println(ps.findSumPairs2(arr, 8));
	}
}
