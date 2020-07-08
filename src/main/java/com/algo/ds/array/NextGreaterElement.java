package com.algo.ds.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an array, print the Next Greater Element (NGE) for every element. The Next greater Element for an element x is
 * the first greater element on the right side of x in array. Elements for which no greater element exist, consider next
 * greater element as -1.
 * 
 * Examples:
 * 
 * For any array, rightmost element always has next greater element as -1.<br/>
 * For an array which is sorted in decreasing order, all elements have next greater element as -1.<br/>
 * For the input array [4, 5, 2, 25}, the next greater elements for each element are as follows.<br/>
 * 
 * Element NGE
 * 
 * 4 --> 5 5 --> 25 2 --> 25 25 --> -1
 * 
 * d) For the input array [13, 7, 6, 12}, the next greater elements for each element are as follows.
 * 
 * Element NGE 13 --> -1 7 --> 12 6 --> 12 12 --> -1
 * 
 * Priority : Medium
 *
 */
public class NextGreaterElement {

	public static void main(String[] args) {
		NextGreaterElement nge = new NextGreaterElement();
		nge.nextGreaterElementSimple(new int[] { 13, 7, 6, 12 });
		nge.nextGreaterElementOptimized(new int[] { 13, 7, 6, 12 });
		nge.nextGreaterElementCircularArray(new int[] { 1, 2, 1 });
	}

	// T - O(n ^ 2) S - O(1)
	public void nextGreaterElementSimple(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int next = -1;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] > arr[i]) {
					next = arr[j];
					break;
				}
			}
			System.out.println("NGE for " + arr[i] + " is " + next);
		}
	}

	// T - O(n) S - O(n)
	public void nextGreaterElementOptimized(int[] arr) {
		Stack<Integer> stack = new Stack<>();
		int[] result = new int[arr.length];

		for (int i = arr.length - 1; i >= 0; i--) {

			while (!stack.isEmpty() && stack.peek() <= arr[i]) {
				stack.pop();
			}

			if (stack.isEmpty()) {
				result[i] = -1;
			} else {
				result[i] = stack.peek();
			}

			stack.push(arr[i]);
		}

		System.out.println("Input :" + Arrays.toString(arr));
		System.out.println("Output :" + Arrays.toString(result));
	}

	/**
	 * Given a circular array (the next element of the last element is the first element of the array), print the Next
	 * Greater Number for every element. The Next Greater Number of a number x is the first greater number to its
	 * traversing-order next in the array, which means you could search circularly to find its next greater number. If
	 * it doesn't exist, output -1 for this number.
	 * 
	 * Example 1:
	 * 
	 * Input: [1,2,1]
	 * 
	 * Output: [2,-1,2]
	 * 
	 * Explanation: The first 1's next greater number is 2; The number 2 can't find next greater number; The second 1's
	 * next greater number needs to search circularly, which is also 2.
	 * 
	 * @param arr
	 */
	public void nextGreaterElementCircularArray(int[] nums) {
		int n = nums.length;
		Stack<Integer> stack = new Stack<>();
		for (int i = n - 1; i >= 0; i--) {
			stack.push(i);
		}

		int[] res = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			res[i] = -1;
			while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
				stack.pop();
			}

			if (!stack.isEmpty())
				res[i] = nums[stack.peek()];
			stack.push(i);
		}

		System.out.println("Input :" + Arrays.toString(nums));
		System.out.println("Output :" + Arrays.toString(res));
	}

}
