package com.algo.ds.array;

/**
 * https://www.techiedelight.com/find-minimum-sum-subarray-given-size-k/
 *
 */
public class MinSumSubarrayOfSizeK {

	public void minSubSubarray(int[] arr, int k) {
		int minWindowSum = Integer.MAX_VALUE;
		int minSum = 0;

		int end = 0;
		for (int i = 0; i < arr.length; i++) {
			minSum += arr[i];

			if (i + 1 >= k) {
				if (minWindowSum > minSum) {
					minWindowSum = minSum;
					end = i;
				}

				// remove left most element from window
				minSum -= arr[i + 1 - k];
			}
		}

		System.out.println("min sum : " + minWindowSum + " occurs from " + (end - k + 1) + " to " + end);

	}

	public static void main(String[] args) {
		MinSumSubarrayOfSizeK minsum = new MinSumSubarrayOfSizeK();
		minsum.minSubSubarray(new int[] { 10, 4, 2, 5, 6, 3, 8, 1 }, 3);
	}

}
