package com.algo.ds.array;

import java.util.Arrays;

public class Sorting {

	/**
	 * sorting in ascending order
	 * 
	 * @param data
	 */
	public void insertionSort(char[] data) {
		int l = data.length;
		for (int i = 1; i < l; i++) {
			char cur = data[i];
			int j = i;
			while (j > 0 && data[j - 1] > cur) {
				data[j] = data[j - 1];
				j--;
			}
			data[j] = cur;
		}
		System.out.println(Arrays.toString(data));

	}
}
