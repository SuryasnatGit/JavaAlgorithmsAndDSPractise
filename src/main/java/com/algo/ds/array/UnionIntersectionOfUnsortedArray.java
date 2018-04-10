package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UnionIntersectionOfUnsortedArray {

	/**
	 * time complexity - O(m*n) where m = length of arr1, n = length of arr2 Space complexity - O(m + n)
	 * 
	 * @param arr1
	 * @param arr2
	 */
	public void union_bruteForce(int[] arr1, int[] arr2) {
		int len1 = arr1.length;
		int len2 = arr2.length;
		List<Integer> temp = new ArrayList<>();
		// copy all elements of arr1 into temp
		for (int i = 0; i < len1; i++)
			temp.add(arr1[i]);
		// loop through all elements of arr2. if any element is not present in temp then add to temp
		for (int j = 0; j < len2; j++) {
			if (!temp.contains(arr2[j]))
				temp.add(arr2[j]);
		}
		System.out.println(temp);
	}

	/**
	 * time complexity - O(m*n) where m = length of arr1, n = length of arr2 Space complexity - O(m + n)
	 * 
	 * @param arr1
	 * @param arr2
	 */
	public void intersection_bruteForce(int[] arr1, int[] arr2) {
		int len1 = arr1.length;
		int len2 = arr2.length;
		List<Integer> temp = new ArrayList<>();
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < len1; i++)
			temp.add(arr1[i]);
		for (int j = 0; j < len2; j++)
			if (temp.contains(arr2[j]))
				res.add(arr2[j]);
		System.out.println(res);
	}

	public static void main(String[] args) {
		int[] b = { 1, 3, 5, 2, 4, 9 };
		int[] a = { 3, 2, 10 };
		UnionIntersectionOfUnsortedArray unsorted = new UnionIntersectionOfUnsortedArray();
		unsorted.union_bruteForce(a, b);
		unsorted.intersection_bruteForce(a, b);
		unsorted.union_1(a, b);
		unsorted.union_hashing(a, b);
		unsorted.intersection_hashing(a, b);
	}

	/**
	 * Method 2 - sorting and searching. time complexity - O(min((m+n)logm + (m+n)logn)). Space
	 * complexity - O(m + n)
	 * 
	 * @param arr1
	 * @param arr2
	 */
	public void union_1(int[] arr1, int[] arr2) {
		int len1 = arr1.length;
		int len2 = arr2.length;
		List<Integer> res = new ArrayList<>();
		if (len1 < len2) {
			Arrays.sort(arr1); // O(n log n)
			res.addAll(Arrays.stream(arr1).boxed().collect(Collectors.toList())); // java 8 approach
			for (int i = 0; i < len2; i++) {
				if (!res.contains(arr2[i]))
					res.add(arr2[i]);
			}
			System.out.println(res);
		}
		else if (len2 < len1) {
			Arrays.sort(arr2);
			res.addAll(Arrays.stream(arr2).boxed().collect(Collectors.toList())); // java 8 approach
			for (int i = 0; i < len1; i++) {
				if (!res.contains(arr1[i]))
					res.add(arr1[i]);
			}
			System.out.println(res);
		}
	}

	public void intersection_1(int[] arr1, int[] arr2) {
		int len1 = arr1.length;
		int len2 = arr2.length;
		List<Integer> res = new ArrayList<>();
		if (len1 < len2) {
			Arrays.sort(arr1);
			// for each element in arr2, binary search for it in arr1. if present copy to res.
		} else {
			Arrays.sort(arr2);
			// for each element in arr1, binary search for it in arr2. if present copy to res.
		}
	}

	/**
	 * Time complexity of this method is O(m+n) under the assumption that hash table search and insert
	 * operations take Θ(1) time.
	 * 
	 * @param arr1
	 * @param arr2
	 */
	public void union_hashing(int[] arr1, int[] arr2) {
		Set<Integer> res = new HashSet<>();
		res.addAll(Arrays.stream(arr1).boxed().collect(Collectors.toList()));
		res.addAll(Arrays.stream(arr2).boxed().collect(Collectors.toList()));
		System.out.println(res);
	}

	/**
	 * Time complexity of this method is O(m+n) under the assumption that hash table search and insert
	 * operations take Θ(1) time.
	 * 
	 * @param arr1
	 * @param arr2
	 */
	public void intersection_hashing(int[] arr1, int[] arr2) {
		Set<Integer> res = new HashSet<>();
		res.addAll(Arrays.stream(arr1).boxed().collect(Collectors.toList()));
		for (int i = 0; i < arr2.length; i++) {
			if (res.contains(arr2[i]))
				System.out.print(arr2[i] + " ");
		}
	}

}
