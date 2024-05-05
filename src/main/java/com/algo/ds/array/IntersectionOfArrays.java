package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IntersectionOfArrays {

	public static void main(String[] args) {
		IntersectionOfArrays inter = new IntersectionOfArrays();
		System.out.println(Arrays.toString(inter.intersectionOf2Arrays(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 })));
		System.out.println(
				Arrays.toString(inter.intersectionOf2Arrays(new int[] { 4, 9, 5 }, new int[] { 9, 4, 9, 8, 4 })));
		System.out.println(Arrays.toString(inter.intersectionOf2Arrays(new int[] { 1, 2, 3 }, new int[] { 3, 2, 1 })));

		System.out
				.println(Arrays.toString(inter.intersectionWithStreams(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 })));
		System.out.println(
				Arrays.toString(inter.intersectionWithStreams(new int[] { 4, 9, 5 }, new int[] { 9, 4, 9, 8, 4 })));
		System.out
				.println(Arrays.toString(inter.intersectionWithStreams(new int[] { 1, 2, 3 }, new int[] { 3, 2, 1 })));
		System.out.println(Arrays.toString(inter.intersectionOf2Arrays2(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 })));
		System.out.println(
				Arrays.toString(inter.intersectionOf2Arrays2(new int[] { 4, 9, 5 }, new int[] { 9, 4, 9, 5, 4 })));
		System.out.println(
				Arrays.toString(inter.intersectionOf2Arrays2(new int[] { 1, 2, 3 }, new int[] { 3, 2, 1, 1 })));
	}

	/**
	 * Problem 1 : Given two arrays, write a function to compute their intersection.
	 * 
	 * Each element in the result must be unique.
	 * 
	 * The result can be in any order.
	 * 
	 */

	/*
	 * T - O(n log n) S - O(k) where k is the distinct number of elements which are same in both arrays
	 */
	public int[] intersectionOf2Arrays(int[] arr1, int[] arr2) {
		Set<Integer> set = new HashSet<Integer>();

		Arrays.sort(arr1);
		Arrays.sort(arr2);

		int i = 0, j = 0;
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				i++;
			} else if (arr2[j] < arr1[i]) {
				j++;
			} else {
				set.add(arr1[i]);
				i++;
				j++;
			}
		}

		int[] result = new int[set.size()];
		int k = 0;
		for (int num : set) {
			result[k++] = num;
		}

		return result;
	}

	public int[] intersectionWithStreams(int[] arr1, int[] arr2) {
		Set<Integer> set = Arrays.stream(arr1).boxed().collect(Collectors.toSet());
		return Arrays.stream(arr2).distinct().filter(e -> set.contains(e)).toArray();
	}

	/**
	 * Problem 2 : Note:
	 * 
	 * Each element in the result should appear as many times as it shows in both arrays.
	 * 
	 * The result can be in any order.
	 * 
	 * Follow up:
	 * 
	 * What if the given array is already sorted? How would you optimize your algorithm?
	 * 
	 * >> If both arrays are sorted, we could use two pointers to iterate, which is similar to the merge two sorted
	 * array process. Use two pointers. At start two pointers point to the first element of each array. If it is equal,
	 * push it to the answer then move both pointers to next position. Otherwise, move the one with less value to the
	 * next position.
	 * 
	 * Using two pointers will have O(M + N) time complexity
	 * 
	 * What if nums1 's size is small compared to nums2 's size? Which algorithm is better?
	 * 
	 * >> If using HashMap solution, the time complexity would be O(M + N), while space complexity is O(N), we can use
	 * nums1 to build the hash map, in order to reduce space complexity.
	 * 
	 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements
	 * into the memory at once?
	 * 
	 * >> If nums1 is small enough to store in memory, build a hashmap out of it, and read chunk by chunk from nums2,
	 * and check if it's in hashmap.
	 * 
	 * If both nums1 and nums2 are too large for in memory storage, do external sorting for both first, and read chunk
	 * by chunk for both sorted array, and then check intersection.
	 */

	public int[] intersectionOf2Arrays2(int[] arr1, int[] arr2) {
		List<Integer> set = new ArrayList<Integer>();

		Arrays.sort(arr1);
		Arrays.sort(arr2);

		int i = 0, j = 0;
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				i++;
			} else if (arr2[j] < arr1[i]) {
				j++;
			} else {
				set.add(arr1[i]);
				i++;
				j++;
			}
		}

		int[] result = new int[set.size()];
		int k = 0;
		for (int num : set) {
			result[k++] = num;
		}

		return result;
	}

}
