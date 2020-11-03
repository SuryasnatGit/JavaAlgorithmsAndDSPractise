package com.algo.ds.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Priority : Medium
 *
 */
public class NextGreaterElement {

	public static void main(String[] args) {
		NextGreaterElement nge = new NextGreaterElement();
		nge.nextGreaterElementSimple(new int[] { 13, 7, 6, 12 });
		nge.nextGreaterElementOptimized(new int[] { 13, 7, 6, 12 });
		nge.nextGreaterElementCircularArray(new int[] { 1, 2, 1 });

		System.out.println(Arrays.toString(nge.nextGreaterElement1(new int[] { 4, 1, 2 }, new int[] { 1, 3, 4, 2 })));
		System.out.println(Arrays.toString(nge.nextGreaterElement1(new int[] { 2, 4 }, new int[] { 1, 2, 3, 4 })));
		System.out.println(Arrays
				.toString(nge.nextGreaterElement1(new int[] { 1, 3, 5, 2, 4 }, new int[] { 6, 5, 4, 3, 2, 1, 7 })));
	}

	/**
	 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find
	 * all the next greater numbers for nums1's elements in the corresponding places of nums2.
	 * 
	 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not
	 * exist, output -1 for this number.
	 * 
	 * Example 1:
	 * 
	 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
	 * 
	 * Output: [-1,3,-1]
	 * 
	 * Explanation: For number 4 in the first array, you cannot find the next greater number for it in the second array,
	 * so output -1. For number 1 in the first array, the next greater number for it in the second array is 3. For
	 * number 2 in the first array, there is no next greater number for it in the second array, so output -1.
	 * 
	 * Example 2:
	 * 
	 * Input: nums1 = [2,4], nums2 = [1,2,3,4].
	 * 
	 * Output: [3,-1]
	 * 
	 * Explanation: For number 2 in the first array, the next greater number for it in the second array is 3. For number
	 * 4 in the first array, there is no next greater number for it in the second array, so output -1.
	 * 
	 * T - O(m * n) S - O(m)
	 */
	public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
		int[] result = new int[nums1.length];
		Arrays.fill(result, -1);

		for (int i = 0; i < nums1.length; i++) {
			int currElemIndex = -1;
			int ngeIndex = -1;
			for (int j = nums2.length - 1; j >= 0; j--) {
				if (nums2[j] > nums1[i]) {
					ngeIndex = j;
				}
				if (nums2[j] == nums1[i]) {
					currElemIndex = j;
					break;
				}
			}

			if (currElemIndex != -1 && ngeIndex > currElemIndex) {
				result[i] = nums2[ngeIndex];
			}
		}

		return result;
	}

	// T - O(m + n) S - O(m + n)
	public int[] nextGreaterElement1Optimized(int[] findNums, int[] nums) {
		Stack<Integer> stack = new Stack<>();
		Map<Integer, Integer> map = new HashMap<>();
		int[] res = new int[findNums.length];

		for (int i = 0; i < nums.length; i++) {
			while (!stack.empty() && nums[i] > stack.peek()) {
				map.put(stack.pop(), nums[i]);
			}
			stack.push(nums[i]);
		}

		while (!stack.empty()) {
			map.put(stack.pop(), -1);
		}

		for (int i = 0; i < findNums.length; i++) {
			res[i] = map.get(findNums[i]);
		}

		return res;
	}

	/**
	 * Given an array, print the Next Greater Element (NGE) for every element. The Next greater Element for an element x
	 * is the first greater element on the right side of x in array. Elements for which no greater element exist,
	 * consider next greater element as -1.
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
	 */
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
