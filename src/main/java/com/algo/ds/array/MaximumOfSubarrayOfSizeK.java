package com.algo.ds.array;

/**
 * http://www.geeksforgeeks.org/maximum-of-all-subarrays-of-size-k
 * 
 * Given an array and an integer k, find the maximum for each and every contiguous subarray of size k.
Examples :

Input :
arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}
k = 3
Output :
3 3 4 5 5 5 6

Input :
arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}
k = 4
Output :
10 10 10 15 15 90 90


 * 
 * Test cases:
 * 
 * input containg neg and pos values.
 * val of k is neg 0 or pos.
 * val of k is larger than size of input.
 * val of k is same as size of input.
 */
import java.util.Deque;
import java.util.LinkedList;

public class MaximumOfSubarrayOfSizeK {

	/**
	 * Time Complexity : The outer loop runs n-k+1 times and the inner loop runs k times for every
	 * iteration of outer loop. So time complexity is O((n-k+1)*k) which can also be written as O(nk).
	 * 
	 * @param input
	 * @param k
	 */
	public void maxSubArray_simple(int[] input, int k) {
		int n = input.length;
		for (int i = 0; i <= n - k; i++) {
			int max = input[i];
			for (int j = 1; j < k; j++) {
				if (input[i + j] > max)
					max = input[i + j];
			}
			System.out.println(max);
		}
	}

	public int[] maxSubArray(int input[], int k) {
		Deque<Integer> queue = new LinkedList<Integer>();
		int max[] = new int[input.length - k + 1];
		int maxVal = Integer.MIN_VALUE;
		// first find max of first k values and make it 0th element of max array
		for (int i = 0; i < k; i++) {
			if (maxVal < input[i]) {
				maxVal = input[i];
			}
			if (queue.isEmpty()) {
				queue.offerLast(i);
			} else {
				while (!queue.isEmpty() && input[queue.peekLast()] <= input[i]) {
					queue.pollLast();
				}
				queue.offerLast(i);
			}

		}
		max[0] = maxVal;
		int index = 1;
		// continue from k till end of the input array
		for (int i = k; i < input.length; i++) {
			// if index of peek is k distance from i then its no value to us.
			// throw it away
			if (i - k + 1 > queue.peekFirst()) {
				queue.pollFirst();
			}
			while (!queue.isEmpty() && input[queue.peekLast()] <= input[i]) {
				queue.pollLast();
			}
			queue.offerLast(i);
			// Only reason first element survived was because it was biggest element.
			// make it the max value for this k
			max[index] = input[queue.peekFirst()];
			index++;
		}
		return max;
	}

	public static void main(String args[]) {
		int input[] = { 8, 5, 10, 7, 9, 4, 15, 12, 90, 13 };
		MaximumOfSubarrayOfSizeK msa = new MaximumOfSubarrayOfSizeK();
		// int max[] = msa.maxSubArray(input, 4);
		// for (int i : max) {
		// System.out.print(i + " ");
		// }
		msa.maxSubArray_simple(input, 4);
	}
}
