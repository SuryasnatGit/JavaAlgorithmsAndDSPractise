package com.algo.backtracking;

/**
 * Given a positive integer, find maximum integer possible by doing at-most K swap operations on its digits.
 * 
 * Examples:
 * 
 * Input: M = 254, K = 1 Output: 524
 * 
 * Input: M = 254, K = 2 Output: 542
 * 
 * Input: M = 68543, K = 1 Output: 86543
 * 
 * Input: M = 7599, K = 2 Output: 9975
 * 
 * Input: M = 76543, K = 1 Output: 76543
 * 
 * Input: M = 129814999, K = 4 Output: 999984211
 * 
 * Time Complexity: O(N^3), where N is the total number of digits in the input number. For each pair of digits, we spend
 * up to O(N) time to compare the final sequences.
 * 
 * Space Complexity: O(N), we keep each digit in the array.
 *
 */
public class MaximumNumberInKSwaps {

	private int max = Integer.MIN_VALUE;

	public int maximumSwap(int num) {

		char[] ch = Integer.toString(num).toCharArray();
		if (ch.length == 1) {
			return num;
		}

		maximumSwap(ch, 1);

		return Math.max(num, max);
	}

	private void maximumSwap(char[] ch, int k) {
		if (k == 0) {
			return;
		}

		for (int i = 0; i < ch.length; i++) {
			for (int j = i + 1; j < ch.length; j++) {
				swap(ch, i, j);

				int temp = Integer.parseInt(String.valueOf(ch));
				max = Math.max(temp, max);

				maximumSwap(ch, k - 1);
				// back track
				swap(ch, j, i);
			}
		}
	}

	private void swap(char[] ch, int i, int j) {
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
	}

	public static void main(String args[]) {
		MaximumNumberInKSwaps ks = new MaximumNumberInKSwaps();
		System.out.println(ks.maximumSwap(0));
		System.out.println(ks.maximumSwap(1));
		System.out.println(ks.maximumSwap(10));
		System.out.println(ks.maximumSwap(2736));
		System.out.println(ks.maximumSwap(9973));
	}
}
