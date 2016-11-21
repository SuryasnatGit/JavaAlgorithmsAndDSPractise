package com.algo.ds.array;

import java.io.File;

public class Recursion {

	/**
	 * time - O(n)
	 * 
	 * @param num
	 * @return
	 */
	public int factorial(int num) {
		if (num < 0)
			throw new IllegalArgumentException();
		if (num == 0)
			return 1;
		else
			return num * factorial(num - 1);
	}

	/**
	 * runs in O(log n) for sorted elements
	 * 
	 * @param data
	 * @param target
	 * @param low
	 * @param high
	 * @return
	 */
	public boolean binarySearch(int[] data, int target, int low, int high) {
		if (low > high)
			return false;
		int mid = (low + high) / 2;
		if (target == data[mid])
			return true;
		if (target < data[mid])
			return binarySearch(data, target, low, mid - 1);
		else
			return binarySearch(data, target, mid + 1, high);
	}

	public long diskUsage(File root) {
		long length = root.length();
		if (root.isDirectory()) {
			File[] files = root.listFiles();
			for (File file : files) {
				length += diskUsage(file);
			}
		}
		System.out.println("size :" + length + ",file:" + root);
		return length;
	}
}
