package com.algo.ds.array;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * You may assume nums1 and nums2 cannot be both empty.
 * 
 * Example 1:
 * 
 * nums1 = [1, 3] nums2 = [2]
 * 
 * The median is 2.0 Example 2:
 * 
 * nums1 = [1, 2] nums2 = [3, 4]
 * 
 * The median is (2 + 3)/2 = 2.5
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * 
 * Difficulty - Hard
 *
 */
public class MedianOfSortedArrays {

	public static void main(String[] args) {
		MedianOfSortedArrays me = new MedianOfSortedArrays();
		System.out.println(me.findMedianSortedArrays(new int[] { 1 }, new int[] { 2, 3 }));
		System.out.println(me.findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3, 4 }));
		System.out.println(me.findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3 }));
		System.out.println(me.findMedianSortedArrays(new int[] { 1, 2 }, new int[] {}));
		System.out.println(me.findMedianSortedArrays(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
				new int[] { 11, 12, 13, 14, 15, 16, 17, 18, 19 }));
	}

	public double findMedianSortedArrays(int[] A, int[] B) {
		// Based on isEven, find either mid or (mid - 1, mid)
		int len = A.length + B.length;
		boolean isEven = (len % 2 == 0);

		if (isEven) {
			return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
		} else {
			return findKth(A, 0, B, 0, len / 2 + 1);
		}
	}

	// Find kth element in 2 sorted array
	private int findKth(int[] A, int A_start, int[] B, int B_start, int k) {
		if (A_start >= A.length) {
			return B[B_start + k - 1];
		}
		if (B_start >= B.length) {
			return A[A_start + k - 1];
		}
		if (k == 1) {
			return Math.min(A[A_start], B[B_start]);
		}

		// Minus 1 because this is index
		int A_key = A_start + k / 2 - 1 < A.length ? A[A_start + k / 2 - 1] : Integer.MAX_VALUE;
		int B_key = B_start + k / 2 - 1 < B.length ? B[B_start + k / 2 - 1] : Integer.MAX_VALUE;

		if (A_key <= B_key) { // Cut half every time
			// The 2rd parameter will advance. last parameter will cut half
			return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
		} else {
			return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
		}
	}

}
