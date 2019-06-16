package com.algo.ds.hashtable;

/**
 * Given a limited range array contains both positive and non-positive numbers, i.e., elements are in the range from
 * -MAX to +MAX. Our task is to search if some number is present in the array or not in O(1) time.
 * 
 * Since range is limited, we can use index mapping (or trivial hashing). We use values as index in a big array.
 * Therefore we can search and insert elements in O(1) time.
 * 
 * How to handle negative numbers? The idea is to use a 2D array of size hash[MAX+1][2]
 * 
 * @author surya
 *
 */
public class TrivialHashingWithNegativesAllowed {

	private int MAX = 100;
	private boolean[][] has = new boolean[MAX + 1][2];

	public void insert(int[] arr) {
		int length = arr.length;
		for (int i = 0; i < length; i++) {
			if (arr[i] >= 0) {
				has[arr[i]][0] = true;
			} else {
				has[Math.abs(arr[i])][1] = true;
			}
		}
	}

	public boolean search(int x) {
		if (x >= 0) {
			if (has[x][0])
				return true;
			else
				return false;
		} else {
			x = Math.abs(x);
			if (has[x][1])
				return true;
			else
				return false;
		}
	}

}
