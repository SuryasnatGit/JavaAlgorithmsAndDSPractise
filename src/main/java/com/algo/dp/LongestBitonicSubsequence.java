package com.algo.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.techiedelight.com/longest-bitonic-subsequence/
 * 
 */
public class LongestBitonicSubsequence {

	/**
	 * T - O(n^2) S - O(1)
	 * 
	 * @param nums
	 * @return
	 */
	public int lbs(int[] nums) {

		int[] I = new int[nums.length];
		int[] D = new int[nums.length];

		int n = nums.length - 1;

		I[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i] && I[j] > I[i]) {
					I[i] = I[j];
				}
			}
			I[i]++;
		}

		D[n] = 1;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = n; j > i; j--) {
				if (nums[j] < nums[i] && D[j] > D[i]) {
					D[i] = D[j];
				}
			}
			D[i]++;
		}

		int lbs = 1;
		for (int i = 0; i <= n; i++) {
			lbs = Math.max(lbs, I[i] + D[i] - 1);
		}

		return lbs;
	}

	/**
	 * Function to find Longest Bitonic Subsequence in an array
	 * 
	 * @param arr
	 * @param n
	 */
	public void printLBS(int[] arr, int n) {
		// I.get(i) stores the longest increasing subsequence ending with arr.get(i)
		List<List<Integer>> I = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			I.add(i, new ArrayList<>());
		}

		I.get(0).add(arr[0]);

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				if (I.get(i).size() < I.get(j).size() && arr[i] > arr[j]) {
					I.set(i, new ArrayList<>(I.get(j)));
				}
			}
			I.get(i).add(arr[i]);
		}

		// D.get(i) stores the longest decreasing subsequence starting from arr.get(i)
		List<List<Integer>> D = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			D.add(i, new ArrayList<>());
		}
		D.get(n).add(0, arr[n]);

		for (int i = n - 1; i >= 0; i--) {
			for (int j = n; j > i; j--) {
				if (D.get(i).size() < D.get(j).size() && arr[i] > arr[j])
					D.set(i, new ArrayList<>(D.get(j)));
			}
			D.get(i).add(0, arr[i]);
		}

		// Uncomment below lines to print contents of list LIS & LDS
		/*
		 * for (int i = 0; i <= n; i++) { System.out.println("LIS[" + i + "] - " + I.get(i)); }
		 * 
		 * for (int i = 0; i <= n; i++) { System.out.println("LDS[" + i + "] - " + D.get(i)); }
		 */

		// find peak element index
		int peak = 0;
		for (int i = 1; i <= n; i++) {
			if (I.get(i).size() + D.get(i).size() > I.get(peak).size() + D.get(peak).size()) {
				peak = i;
			}
		}

		System.out.print("Longest Bitonic Subsequence is: ");

		// print longest increasing subsequence ending at peak element
		System.out.print(I.get(peak));

		// pop front element of LDS as it points to same element as rear of LIS
		D.get(peak).remove(0);

		// print longest decreasing subsequence starting from peak element
		System.out.println(D.get(peak));
	}

	public static void main(String[] args) {
		LongestBitonicSubsequence lbs = new LongestBitonicSubsequence();
		int[] A = { 4, 2, 5, 9, 7, 6, 10, 3, 1 };

		System.out.println("Length of Longest Bitonic Subsequence is " + lbs.lbs(A));
		lbs.printLBS(A, A.length - 1);
	}
}
