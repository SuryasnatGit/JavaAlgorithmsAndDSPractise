package com.algo.ds.array;

import java.util.HashMap;
import java.util.Map;

// https://www.techiedelight.com/find-frequency-element-sorted-array-containing-duplicates/
public class FrequencyOfEachElementInSortedArray {

	public void findFrequencyRecursive(int[] arr) {
		Map<Integer, Integer> count = new HashMap<Integer, Integer>();
		util(arr, 0, arr.length - 1, count);

		System.out.println(count);
	}

	private void util(int[] arr, int start, int end, Map<Integer, Integer> count) {
		if (start > end)
			return;

		if (arr[start] == arr[end]) {
			Integer freq = count.get(arr[start]);
			if (freq == null)
				freq = 0;
			count.put(arr[start], freq + (end - start + 1));
			return;
		}

		int mid = (start + end) / 2;

		util(arr, start, mid, count);
		util(arr, mid + 1, end, count);
	}

	public void findFrequencyIterative(int[] arr) {
		Map<Integer, Integer> count = new HashMap<>();

		int left = 0, right = arr.length - 1;
		while (left <= right) {
			if (arr[left] == arr[right]) {
				if (count.get(arr[left]) == null) {
					count.put(arr[left], 0);
				}
				count.put(arr[left], count.get(arr[left]) + (right - left + 1));

				left = right + 1;
				right = arr.length - 1; // reset to original
			} else {
				right = (left + right) / 2; // binary search approach, split by half
			}
		}
		System.out.println(count);
	}

	public void findFrequency(int[] arr) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}

		System.out.println(map);
	}

	/**
	 * Count frequency of each element in large list
	 * 
	 * @param arr
	 * @return
	 */
	public void findFrequencyBinarySearch(int[] arr) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < arr.length;) {
			int curVal = arr[i];

			int radix = 0;
			int pos = (int) (i + Math.pow(2, radix));
			while (pos < arr.length && arr[pos] == curVal) { // Jump, O(log(N))
				radix++;
				pos = (int) (i + Math.pow(2, radix));
			}

			int left = i;
			int right = (pos < arr.length) ? pos : arr.length - 1;

			// Binary search to find the right border of curVal
			int rightBorder = binarySearch(arr, left, right, curVal);

			map.put(curVal, rightBorder - left + 1);
			i = rightBorder + 1;
		}

		System.out.println(map);
	}

	private int binarySearch(int[] arr, int left, int right, int curVal) {
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;

			if (arr[mid] == curVal) {
				left = mid;
			} else {
				right = mid;
			}
		}

		if (arr[right] == curVal) {
			return right;
		}
		return left;
	}

	public static void main(String[] args) {
		FrequencyOfEachElementInSortedArray fr = new FrequencyOfEachElementInSortedArray();
		int[] arr = { 2, 2, 2, 4, 4, 4, 5, 5, 6, 8, 8, 9 };
		fr.findFrequencyRecursive(arr);
		fr.findFrequencyIterative(arr);
		fr.findFrequencyBinarySearch(arr);
		fr.findFrequency(arr);
	}
}
