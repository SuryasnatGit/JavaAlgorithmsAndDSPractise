package com.algo.ds.array;

import java.util.Arrays;

public class Merge2ArraysWithConstraints {

	/**
	 * Given 2 sorted arrays x[] and y[] of size m and n each where m >= n and x[] has exactly n vacant cells, merge
	 * elements of y[] in their correct position in array x[] i.e merge (x,y) by keeping the sorted order.
	 *
	 */
	public void merge(int[] a, int[] b) {

		// move non zero elements in a to the beginning
		int k = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != 0) {
				a[k++] = a[i];
			}
		}

		merge(a, b, k - 1, b.length - 1);
	}

	/**
	 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
	 * 
	 * Note:
	 * 
	 * The number of elements initialized in nums1 and nums2 are m and n respectively. You may assume that nums1 has
	 * enough space (size that is equal to m + n) to hold additional elements from nums2. Example:
	 * 
	 * Input: nums1 = [1,2,3,0,0,0], m = 3 nums2 = [2,5,6], n = 3
	 * 
	 * Output: [1,2,2,3,5,6]
	 * 
	 */
	public void merge(int[] a, int[] b, int m, int n) {
		int k = m + n + 1;

		while (m >= 0 && n >= 0) {
			if (a[m] > b[n]) {
				a[k--] = a[m--];
			} else {
				a[k--] = b[n--];
			}
		}

		while (n >= 0) {
			a[k--] = b[n--];
		}
	}

	/**
	 * Give two lists, each with the same elements, merge the two lists, and require the elements of the short list to
	 * separate the elements of the long list as evenly as possible. For example, if the list is AAAAA and BBBBBB,
	 * return BABABABABAB; for example, AAAAAA and BBBB, return AABABABABA.
	 */
	public char[] merge2Strings(char[] s1, char[] s2) {
		int length1 = s1.length;
		int length2 = s2.length;

		// make 1st smaller and 2nd larger
		if (length1 > length2) {
			return merge2Strings(s2, s1);
		}

		char[] result = new char[length1 + length2];
		int i = 0, j = 0;
		boolean isSecond = false;

		while (i < length1 && j < length2) {
			if (isSecond) {
				result[i + j] = s2[j++];
			} else {
				result[i + j] = s1[i++];
			}

			isSecond = !isSecond;
		}

		// smaller(1st) must be done already. only 2nd left
		while (j < length2) {
			result[i + j] = s2[j++];
		}

		return result;
	}

	public static void main(String[] args) {
		Merge2ArraysWithConstraints m = new Merge2ArraysWithConstraints();
		int[] a = { 0, 2, 0, 3, 0, 5, 6, 0, 0 };
		int[] b = { 1, 8, 9, 10, 15 };
		m.merge(a, b);

		System.out.println(Arrays.toString(a));

		System.out.println(m.merge2Strings("AAAAA".toCharArray(), "BBBBBB".toCharArray()));
		System.out.println(m.merge2Strings("AAAAAA".toCharArray(), "BBBB".toCharArray()));
		System.out.println(m.merge2Strings("AAA".toCharArray(), "BBBB".toCharArray()));

		int[] arr1 = { 1, 2, 3, 0, 0, 0 };
		int[] arr2 = { 2, 5, 6 };
		m.merge(arr1, arr2, 2, 2);
		System.out.println(Arrays.toString(arr1));
	}

}
