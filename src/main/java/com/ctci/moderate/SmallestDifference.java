package com.ctci.moderate;

import java.util.Arrays;

/**
 * Given two arrays of integers, compute the pair of values (one value in each array) with the smallest (non-negative)
 * difference. Return the difference.
 * 
 * EXAMPLE
 * 
 * Input {1,3,5,11,2} and {23,127,235,19,8}
 * 
 * Output 3. That is, the pair (11, 8).
 * 
 * This algorithm takes O(A log A + B log B) time to sort and O(A + B) time to find the minimum difference. Therefore,
 * the overall runtime is 0 (A log A + B log B).
 *
 * CTCI: 16.6
 */
public class SmallestDifference {

	public int smallestDiff(int[] a1, int[] a2) {
		Arrays.sort(a1);
		Arrays.sort(a2);

		int diff = Integer.MAX_VALUE;
		int i = 0, j = 0;
		int[] res = new int[2];

		while (i < a1.length && j < a2.length) {
			if (Math.abs(a1[i] - a2[j]) < diff) {
				diff = Math.abs(a1[i] - a2[j]);
				res[0] = a1[i];
				res[1] = a2[j];
			}

			// move the smaller one
			if (a1[i] < a2[j]) {
				i++;
			} else {
				j++;
			}
		}

		System.out.println(Arrays.toString(res));
		return diff;
	}

	/**
	 * a中的每个元素都得用上，b的长度大于等于a，a中每个元素到b里面选一个对应的元素（必须按顺序取）使的差值的之和为最小。
	 * 我是举例子，math.abs(a1-b3)＋math.abs(a2-b9)+math.abs(a3-b17)为最小，不能sort是因为如果sort了，a和b的顺序就被打乱了。比如上面的例子，不能取a1-b3,
	 * a2-b1这种是不合法的。
	 * 
	 * // array[i][j] mean when we reach a[i] and we try to chose b[j] // thus array[i][j] = min(array[i - 1][j - 1] +
	 * abs(a[i] - b[j]), array[i - 1][j]) // thus array[a.length][b.length] will be the result
	 */
	public int minDifferenceSum(int[] a, int[] b) {
		int[][] hash = new int[a.length + 1][b.length + 1];

		for (int i = 1; i <= a.length; i++) {
			hash[i][0] = Integer.MAX_VALUE; // Need to initialize this because we need hash[i][j - 1]
		}

		for (int i = 1; i <= a.length; i++) {
			for (int j = 1; j <= b.length - (a.length - i); j++) { // 保证在b上留下空间 to match a
				hash[i][j] = Math.min(hash[i - 1][j - 1] + Math.abs(a[i - 1] - b[j - 1]), hash[i][j - 1]);
			}
		}

		return hash[a.length][b.length];
	}

	// What if B is super long
	// This solution has better space efficiency
	public int minDifferenceSumLong(int[] a, int[] b) {
		int[] hash = new int[a.length + 1];

		for (int i = 1; i <= a.length; i++) {
			hash[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < b.length; i++) { // Start from index i in b, b is super long
			int[] temp = new int[a.length + 1];
			for (int j = 1; j <= a.length; j++) {
				if (b.length - i - 1 < a.length - j) {
					continue;
				}
				temp[j] = Math.min(hash[j], hash[j - 1] + Math.abs(b[i] - a[j - 1]));
			}

			hash = temp;
		}

		return hash[a.length];
	}

	public static void main(String[] args) {
		SmallestDifference sd = new SmallestDifference();
		System.out.println(sd.smallestDiff(new int[] { 1, 3, 5, 11, 2 }, new int[] { 23, 127, 235, 19, 8 }));
	}
}
