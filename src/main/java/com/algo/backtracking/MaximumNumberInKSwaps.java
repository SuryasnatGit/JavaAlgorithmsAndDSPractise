package com.algo.backtracking;

/**
 * Given a positive integer, find maximum integer possible by doing at-most K swap operations on its
 * digits.
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
 * @author surya
 *
 */
public class MaximumNumberInKSwaps {

	int max;

	public MaximumNumberInKSwaps() {
		this.max = Integer.MIN_VALUE;
	}

	public void findMin(char ch[], int k) {
		if (k == 0) {
			return;
		}
		for (int i = 0; i < ch.length; i++) {
			for (int j = i + 1; j < ch.length; j++) {
				swap(ch, i, j);
				int temp = Integer.parseInt(String.valueOf(ch));
				if (max < temp) {
					max = temp;
				}
				findMin(ch, k - 1);
				swap(ch, j, i);
			}
		}
	}

	public void swap(char ch[], int i, int j) {
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
	}

	public static void main(String args[]) {
		String str = "243";
		int n = 2;
		MaximumNumberInKSwaps ks = new MaximumNumberInKSwaps();
		ks.findMin(str.toCharArray(), n);
		System.out.println(ks.max + "");
	}
}
