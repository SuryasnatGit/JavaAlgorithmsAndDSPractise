package com.leetcode;

import java.util.List;

public class MaximumSubarraySum {

	/**
	 * Problem 1 :
	 * 
	 * Given an array of integers, find a contiguous subarray which has the largest sum.
	 * 
	 * Example
	 * 
	 * Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6.
	 */

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
	public int maximumSumSubArray(int[] a) {
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
	 * Kadane's algorighm: maxSubArray(A, i) = maxSubArray(A, i - 1) > 0 ? maxSubArray(A, i - 1) : 0 + A[i];
	 * 
	 * Approach 2 - dynamic programming. Time - O(n), Space - O(1)
	 * 
	 * @param a
	 * @return
	 */
	public int maximumSumSubArray1(int[] nums) {
		int maximum = nums[0];
		int current = nums[0];
		for (int i = 1; i < nums.length; i++) {
			current = Math.max(current + nums[i], nums[i]);
			maximum = Math.max(maximum, current);
		}
		return maximum;
	}

	/**
	 * Problem 2 :
	 * 
	 * Given an array of integers, find two non-overlapping subarrays which have the largest sum. The number in each
	 * subarray should be contiguous. Return the largest sum.
	 * 
	 * The subarray should contain at least one number
	 * 
	 * Example
	 * 
	 * For given[1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2], they both
	 * have the largest sum 7
	 * 
	 * Kadane's Algorithm - O(n) space, O(n) time
	 * 
	 * Analysis
	 * 
	 * Maximum Subarray II has several difficulties compared with Maximum Subarray I:
	 * 
	 * The sum of two non-adjacent subarrays, the original local variable is insufficient, additional variables are
	 * needed to save the subarray sum, and they are required to be non-adjacent; definition left[], right[]the two
	 * arrays store the left-to-right and right-to-left subarrays respectively. the result of sum
	 * 
	 * It is not enough to save the local optimal solution of the sub-problem, but also need to save the global optimal
	 * solution. Therefore left[], right[]what is stored in is actually the global optimal of the current subarray sum;
	 * for example, left[i]it is [0, ..., i]the maximum subarray sum that can be obtained for this subarray,
	 * right[i]which means [n - 1, ..., i]the maximum subarray that can be obtained. sum, this optimal solution does not
	 * necessarily include ithe position as the end.
	 * 
	 * Finally, a global query left[]is right[]performed on the sum to obtain the final result.
	 * 
	 * It is not enough to update the local variable and then return the global optimal. It is necessary to save the
	 * global optimal for each sub-problem (which may not end with the current position) for subsequent global queries
	 * on the left and right sides.
	 * 
	 * Compared with Prefix Sum, I personally prefer Kadane's Algorithm, but when using Kadane's Algorithm , you must
	 * pay attention to the initialization issue (when looping from beginning to end, you must nums[0]initialize with
	 * and index = 1start searching from ; or when looping from end to beginning, use nums[n-1]initialization and index
	 * = n - 2start from ).
	 * 
	 * 
	 * 
	 * TODO : understand properly
	 * 
	 * @param args
	 */
	public int maxTwoSubArrays(List<Integer> nums) {
		if (nums == null || nums.size() == 0)
			return 0;

		int n = nums.size();

		// left[] stores the global max subarray sum for [0, ..., i]
		int[] left = new int[n];

		// right[] stores the global max subarray sum for [i, ..., n - 1]
		int[] right = new int[n];

		// Initialize left[] array
		left[0] = nums.get(0);
		int prevMax = left[0];
		int max = left[0];
		// Loop from left to right
		for (int i = 1; i < n; i++) {
			prevMax = Math.max(prevMax + nums.get(i), nums.get(i));
			max = Math.max(max, prevMax);
			left[i] = max;
		}

		// Initialize right[] array
		right[n - 1] = nums.get(n - 1);
		prevMax = right[n - 1];
		max = right[n - 1];
		// Loop from right to left
		for (int i = n - 2; i >= 0; i--) {
			prevMax = Math.max(prevMax + nums.get(i), nums.get(i));
			max = Math.max(max, prevMax);
			right[i] = max;
		}

		// Find max sum of left and right subarrays
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < n - 1; i++) {
			result = Math.max(result, left[i] + right[i + 1]);
		}

		return result;
	}

	/**
	 * Problem 3 :
	 * 
	 * Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.
	 * 
	 * The number in each subarray should be contiguous.
	 * 
	 * Return the largest sum.
	 * 
	 * The subarray should contain at least one number
	 * 
	 * Example
	 * 
	 * Given[-1,4,-2,3,-2,3],k=2, return 8
	 * 
	 * Analysis :
	 * 
	 * Maintain a matrix of int globalMax [ k+1 ] [ len+1 ]. globalMax [ i ] [ j ] represents the maximum sum obtained
	 * by taking i subarray among the first j numbers. Note that the jth number here is not necessarily included. .
	 * 
	 * An int type localMax, used every time when writing the i-th row and j-th column of globalMax, records the maximum
	 * sum obtained by taking i subarrays among the first j numbers, but including the j-th number
	 * 
	 */
	public int maxKSubArrays(int[] nums, int k) {
		// write your code here
		if (nums == null || nums.length == 0)
			return 0;

		int n = nums.length;

		int[][] localMax = new int[n + 1][k + 1];
		int[][] globalMax = new int[n + 1][k + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k && j <= i; j++) {
				localMax[j - 1][j] = Integer.MIN_VALUE;
				localMax[i][j] = Math.max(localMax[i - 1][j], globalMax[i - 1][j - 1]) + nums[i - 1];
				if (i == j) {
					globalMax[i][j] = localMax[i][j];
				} else {
					globalMax[i][j] = Math.max(localMax[i][j], globalMax[i - 1][j]);
				}
			}
		}

		return globalMax[n][k];
	}

	/**
	 * Problem 4 :
	 * 
	 * Given an integer arrays, find a contiguous subarray which has the largest sum and length should be greater or
	 * equal to given length k. Return the largest sum, return 0 if there are fewer than k elements in the array.
	 * 
	 * Ensure that the result is an integer type.
	 * 
	 * Example
	 * 
	 * Given the array [-2,2,-3,4,-1,2,1,-5,3]and k = 5, the contiguous subarray [2,-3,4,-1,2,1]has the largest sum = 5.
	 * 
	 * Analysis
	 * 
	 * This question is somewhat different from the previous Maximum subarray sum. Kadane Algorithm cannot be applied
	 * very well here.
	 * 
	 * The reason is that when solving the interval sum, the length of the interval is required to be greater than or
	 * equal to k.
	 * 
	 * The basis of maxSum is the sum of the first k elements; the minimum value of the prefix sum minPrefix needs to be
	 * compared when used, which sum[i - k]ensures that the interval length of maxSum is at least k (sum[i] - sum[i -
	 * k], i - (i - k) = k).
	 * 
	 * 
	 */
	public int maximumSubArrayWithAtleastKNumbers(int[] nums, int k) {
		int n = nums.length;
		if (n < k)
			return 0;

		int maxSum = 0;
		for (int i = 0; i < k; i++) {
			maxSum += nums[i];
		}

		int[] sum = new int[n + 1];
		sum[0] = 0;

		int minPrefix = 0;
		for (int i = 1; i <= n; i++) {
			sum[i] = sum[i - 1] + nums[i - 1];
			if (i >= k) {
				minPrefix = Math.min(minPrefix, sum[i - k]);
				maxSum = Math.max(maxSum, sum[i] - minPrefix);
			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		MaximumSubarraySum max = new MaximumSubarraySum();
		System.out.println(max.maximumSumSubArray(new int[] { -2, 2, -3, 4, -1, 2, 1, -5, 3 }));
		System.out.println(max.maximumSumSubArray1(new int[] { -2, 2, -3, 4, -1, 2, 1, -5, 3 }));
	}
}
