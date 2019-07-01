package com.algo.ds.array;

/**
 * Find the contiguous subarray within an array (containing at least one number) that has the largest sum. For example,
 * given the array [2, 1, -3, 4, -1, 2, 1, -5, 4], The contiguous array [4, -1, 2, 1] has the largest sum = 6.
 * 
 * @author M_402201
 *
 */
public class MaxSumSubarray {

	/**
	 * Approach 1 - divide and conquor.
	 * 
	 * Assume we partition the array A into two smaller arrays S and T at the middle index, M. Then, S = A1 ... AM-1,
	 * and T = AM+1 ... AN.
	 * 
	 * The contiguous subarray that has the largest sum could either:
	 * 
	 * i. Contain the middle element: a. The largest sum is the maximum suffix sum of S + A[M] + the maximum prefix sum
	 * of T.
	 * 
	 * ii. Does not contain the middle element: a. The largest sum is in S, which we could apply the same algorithm to
	 * S. b. The largest sum is in T, which we could apply the same algorithm to T.
	 * 
	 * time complexity - O(n log n), Space - O(log n) stack space.
	 * 
	 * @param a
	 * @return
	 */
	public int maxSumSubArray1(int[] a) {
		return maxSumSubArray(a, 0, a.length - 1);
	}

	private int maxSumSubArray(int[] a, int left, int right) {
		if (left > right)
			return Integer.MIN_VALUE;

		int mid = left + (right - left) / 2;
		int leftAns = maxSumSubArray(a, left, mid - 1);
		int rightAns = maxSumSubArray(a, mid + 1, right);

		int leftSum = 0, maxLeftSum = 0;
		for (int i = mid - 1; i >= left; i--) {
			leftSum += a[i];
			maxLeftSum = Math.max(maxLeftSum, leftSum);
		}

		int rightSum = 0, maxRightSum = 0;
		for (int i = mid + 1; i <= right; i++) {
			rightSum += a[i];
			maxRightSum = Math.max(maxRightSum, rightSum);
		}

		return Math.max(maxLeftSum + a[mid] + maxRightSum, Math.max(leftAns, rightAns));
	}

	/**
	 * Approach 2 - dynamic programming. Time - O(n), Space - O(1)
	 * 
	 * @param a
	 * @return
	 */
	public int maxSumSubArray2(int[] a) {
		int maxEndingHere = 0, maxSoFar = 0;
		for (int i = 0; i < a.length; i++) {
			maxEndingHere = Math.max(maxEndingHere + a[i], a[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		return maxSoFar;
	}

	public static void main(String[] args) {
		MaxSumSubarray max = new MaxSumSubarray();
		System.out.println(max.maxSumSubArray1(new int[] { 2, 1, -3, 4, -1, 2, 1, -5, 4 }));
		System.out.println(max.maxSumSubArray2(new int[] { 2, 1, -3, 4, -1, 2, 1, -5, 4 }));
	}

}
