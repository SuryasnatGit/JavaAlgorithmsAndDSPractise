package com.algo.ds.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Give two arrays of same size consisting of 0s and 1s find span (i, j) such that sum of input1[i..j] = sum of
 * input2[i..j]
 *
 * Time complexity O(n) Space complexity O(n)
 *
 * http://www.geeksforgeeks.org/longest-span-sum-two-binary-arrays/.
 * 
 * Examples :
 * 
 * Input: arr1[] = {0, 1, 0, 0, 0, 0}; arr2[] = {1, 0, 1, 0, 0, 1}; Output: 4 The longest span with same sum is from
 * index 1 to 4.
 * 
 * Input: arr1[] = {0, 1, 0, 1, 1, 1, 1}; arr2[] = {1, 1, 1, 1, 1, 0, 1}; Output: 6 The longest span with same sum is
 * from index 1 to 6.
 * 
 * Input: arr1[] = {0, 0, 0}; arr2[] = {1, 1, 1}; Output: 0
 * 
 * Input: arr1[] = {0, 0, 1, 0}; arr2[] = {1, 1, 1, 1}; Output: 1
 * 
 * Category : Medium
 */
public class LongestSameSumSpan {

	/**
	 * Time Complexity : O(n2) Auxiliary Space : O(1)
	 * 
	 * @param input1
	 * @param input2
	 * @return
	 */
	public int longestSpan_brute(int input1[], int input2[]) {
		if (input1.length != input2.length) {
			throw new IllegalArgumentException("Not same length input");
		}

		int maxLen = 0;
		for (int i = 0; i < input1.length; i++) {
			int sum1 = 0, sum2 = 0;
			for (int j = i; j < input2.length; j++) {
				sum1 += input1[j];
				sum2 += input2[j];
				if (sum1 == sum2) {
					int len = j - i + 1;
					maxLen = Math.max(maxLen, len);
				}
			}
		}
		return maxLen;
	}

	/**
	 * Time Complexity: Θ(n) Auxiliary Space: Θ(n)
	 * 
	 * @param input1
	 * @param input2
	 * @return
	 */
	public int longestSpan(int input1[], int input2[]) {
		if (input1.length != input2.length) {
			throw new IllegalArgumentException("Not same length input");
		}
		Map<Integer, Integer> diff = new HashMap<>();
		int prefix1 = 0, prefix2 = 0;
		int maxSpan = 0;
		diff.put(0, -1);
		for (int i = 0; i < input1.length; i++) {
			prefix1 += input1[i];
			prefix2 += input2[i];
			int currDiff = prefix1 - prefix2;
			if (diff.containsKey(currDiff)) {
				maxSpan = Math.max(maxSpan, i - diff.get(currDiff));
			} else {
				diff.put(currDiff, i);
			}
		}
		return maxSpan;
	}

	public static void main(String args[]) {
		int input1[] = { 1, 0, 0, 1, 1, 0 };
		int input2[] = { 0, 1, 1, 0, 1, 1 };
		LongestSameSumSpan lsss = new LongestSameSumSpan();
		System.out.println(lsss.longestSpan(input1, input2));
		System.out.println(lsss.longestSpan_brute(input1, input2));
	}

}
