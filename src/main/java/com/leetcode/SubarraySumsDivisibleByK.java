package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * 
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 * 
 * Example 1:
 * 
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * 
 * Output: 7
 * 
 * Explanation: There are 7 subarrays with a sum divisible by K = 5: [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3],
 * [0], [0, -2, -3], [-2, -3]
 * 
 * Category : Medium
 */
public class SubarraySumsDivisibleByK {

	public static void main(String[] args) {
		SubarraySumsDivisibleByK kSub = new SubarraySumsDivisibleByK();

		int k = 3;
		int[] arr = { 1, 2, 3, 4, 1 };

		System.out.println(kSub.countKSubsequence2(k, arr));
		System.out.println(kSub.countKSubsequence(k, arr));
	}

	int countKSubsequence2(int k, int[] arr) {
		int count = 0;

		for (int i = 0; i < arr.length; i++) { // Start Point
			int sum = 0;
			for (int j = i; j < arr.length; j++) { // End Point
				sum += arr[j];

				if (sum % k == 0) {
					count++;
				}
			}
		}

		return count;
	}

	List<Integer> countKSubsequence(int k, int[] arr) {
		List<Integer> res = new ArrayList<Integer>();
		LinkedList<Integer> list = new LinkedList<Integer>();
		int len = arr.length;

		if (k > len) {
			return res;
		}

		int count = 0;
		int i = 0;
		int sum = 0;
		// Add first k elements to list
		while (i < k) {
			list.addLast(arr[i]);
			sum += arr[i];
			i++;
		}

		while (i < len) {
			if (sum % k == 0) {
				count++;
				res.add(i - k);
			}
			int toRemove = list.getFirst();
			sum -= toRemove;
			list.removeFirst();

			int toAdd = arr[i];
			sum += toAdd;
			list.addLast(toAdd);

			i++;
		}

		System.out.println(count);
		return res;
	}
}
