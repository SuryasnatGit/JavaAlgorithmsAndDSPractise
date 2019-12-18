package com.algo.ds.array;

import java.util.Arrays;
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

Category : Hard

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
	 * Solution 1 - simple.
	 * 
	 * Time Complexity : The outer loop runs n-k+1 times and the inner loop runs k times for every iteration of outer
	 * loop. So time complexity is O((n-k+1)*k) which can also be written as O(nk).
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

	/**
	 * Method 2 - Use Self-Balancing BST
	 * 
	 * 1. Pick first k elements and create a Self-Balancing Binary Search Tree (BST) of size k.
	 * 
	 * 2. Run a loop for i = 0 to n  k
	 * 
	 * - Get the maximum element from the BST, and print it.
	 * 
	 * - Search for arr[i] in the BST and delete it from the BST.
	 * 
	 * - Insert arr[i+k] into the BST.
	 * 
	 * Time complexity - Time Complexity of step 1 is O(K * Log k). Time Complexity of steps 2(a), 2(b) and 2(c) is
	 * O(Logk). Since steps 2(a), 2(b) and 2(c) are in a loop that runs n-k+1 times, time complexity of the complete
	 * algorithm is O(kLogk + (n-k+1)*Logk) which can also be written as O(N * Log k).
	 * 
	 */

	/**
	 * Method 3 -
	 * 
	 * Time Complexity: O(n). It seems more than O(n) at first look. If we take a closer look, we can observe that every
	 * element of array is added and removed at most once. So there are total 2n operations. Auxiliary Space: O(k)
	 * 
	 * @param input
	 * @param k
	 * @return
	 */
	public int[] maxSubArray(int input[], int k) {
		Deque<Integer> queue = new LinkedList<Integer>();
		int max[] = new int[input.length - k + 1];
		int maxVal = Integer.MIN_VALUE;

		// first find max of first k values and make it 0th element of max array
		// maximum element index kept in rear of deque
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
			max[index++] = input[queue.peekFirst()];
		}
		return max;
	}

	/**
	 * Method 4 - (Use Max-Heap)
	 * 
	 * 1. Pick first k elements and create a max heap of size k.<br/>
	 * 2. Perform heapify and print the root element. <br/>
	 * 3. Store the next and last element from the array <br/>
	 * 4. Run a loop from k  1 to n. <br/>
	 * - Replace the value of element which is got out of the window with new element which came inside the window.<br/>
	 * - Perform heapify. <br/>
	 * - Print the root of the Heap. <br/>
	 * 
	 * Time Complexity: Time Complexity of steps 4(a) is O(k), 4(b) is O(Log(k)) and it is in a loop that runs (n  k +
	 * 1) times. Hence, the time complexity of the complete algorithm is O((k + Log(k)) * n) i.e. O(n * k).
	 * 
	 * @param args
	 */

	/**
	 * Solution 5 - Using stack.
	 * 
	 * https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k-using-stack-in-on-time/
	 * 
	 * 
	 * @param args
	 */

	public static void main(String args[]) {
		int input[] = { 8, 5, 10, 7, 9, 4, 15, 12, 90, 13 };
		MaximumOfSubarrayOfSizeK msa = new MaximumOfSubarrayOfSizeK();
		int max[] = msa.maxSubArray(input, 4);
		System.out.println(Arrays.toString(max));
		// for (int i : max) {
		// System.out.print(i + " ");
		// }
		// msa.maxSubArray_simple(input, 4);
	}
}
