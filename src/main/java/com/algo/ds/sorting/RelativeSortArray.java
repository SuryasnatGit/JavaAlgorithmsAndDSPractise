package com.algo.ds.sorting;

import java.util.Arrays;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 * 
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. Elements that
 * don't appear in arr2 should be placed at the end of arr1 in ascending order.
 * 
 * Example 1:
 * 
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6] Output: [2,2,2,1,4,3,3,9,6,7,19]
 *
 */
public class RelativeSortArray {

	public int[] relativeSortArray(int[] arr1, int[] arr2) {

		SortedMap<Integer, Integer> map = new TreeMap<>();

		for (int i = 0; i < arr1.length; i++) {
			map.put(arr1[i], map.getOrDefault(arr1[i], 0) + 1);
		}

		int[] result = new int[arr1.length];
		int index = 0;
		for (int i = 0; i < arr2.length; i++) {
			if (map.containsKey(arr2[i])) {
				int count = map.get(arr2[i]);
				while (count-- > 0) {
					result[index++] = arr2[i];
				}
				// remove key from map
				map.remove(arr2[i]);
			}
		}

		// add rest of keys which are present in map
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int num = entry.getKey();
			int count = entry.getValue();
			while (count-- > 0) {
				result[index++] = num;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		RelativeSortArray rs = new RelativeSortArray();
		System.out.println(Arrays.toString(
				rs.relativeSortArray(new int[] { 2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19 }, new int[] { 2, 1, 4, 3, 9, 6 })));
		System.out.println(
				Arrays.toString(rs.relativeSortArray(new int[] { 28, 6, 22, 8, 44, 17 }, new int[] { 22, 28, 8, 6 })));
	}
}
