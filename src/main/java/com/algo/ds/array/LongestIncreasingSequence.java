package com.algo.ds.array;

import java.util.Arrays;

public class LongestIncreasingSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestIncreasingSequence lis = new LongestIncreasingSequence();
		int[] array = new int[] { 4, 3, 2, 1, 9, 8, 10, 12, 11, 7 };
		System.out.println(lis.findLongestIncreasingSeqLength(array));

	}

	// O(n log n)
	public int findLongestIncreasingSeqLength(int[] array) {
		Arrays.sort(array);

		int count = 1;
		int longestCount = 0;
		int start = array[0];

		for (int i = 1; i < array.length - 1; i++) {
			start++;
			if (array[i] == start) {
				count++;
			} else {
				if (count > longestCount)
					longestCount = count;
				// reset count and start
				count = 0;
				start = array[i];
			}
		}
		if (longestCount > 0)
			return longestCount;
		else
			return 1;
	}

}
