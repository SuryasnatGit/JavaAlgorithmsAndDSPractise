package com.algo.ds.array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * http://www.geeksforgeeks.org/next-greater-element/
 * 
 * Given an array, print the Next Greater Element (NGE) for every element. The Next greater Element
 * for an element x is the first greater element on the right side of x in array. Elements for which
 * no greater element exist, consider next greater element as -1.
 * 
 * Examples: a) For any array, rightmost element always has next greater element as -1. b) For an
 * array which is sorted in decreasing order, all elements have next greater element as -1. c) For
 * the input array [4, 5, 2, 25}, the next greater elements for each element are as follows.
 * 
 * Element NGE 4 --> 5 5 --> 25 2 --> 25 25 --> -1 d) For the input array [13, 7, 6, 12}, the next
 * greater elements for each element are as follows.
 * 
 * Element NGE 13 --> -1 7 --> 12 6 --> 12 12 --> -1
 * 
 * Find next larger element on right side of a number in array for all positions in array. This is
 * different than finding largest element on right side which can be done by keeping max while
 * iterating from right. It is also different from find next higher number on right side which can
 * be found by keeping AVL tree and finding ceiling.
 */
public class LargerElementOnRight {

	/**
	 * time - O(n^2). The worst case occurs when all elements are sorted in decreasing order.
	 * 
	 * @param input
	 */
	public void larger_bruteforce(int[] input) {
		for (int i = 0; i < input.length; i++) {
			int next = -1;
			for (int j = i + 1; j < input.length; j++) {
				if (input[i] < input[j]) {
					next = input[j];
					break;
				}
			}
			System.out.print(next + " ");
		}
	}

	/**
	 * Time Complexity: O(n). The worst case occurs when all elements are sorted in decreasing order. If
	 * elements are sorted in decreasing order, then every element is processed at most 4 times.
	 * 
	 * @param input
	 * @return
	 */
	public int[] larger(int input[]) {
		Deque<Integer> stack = new LinkedList<Integer>();
		int result[] = new int[input.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = -1; // holder for correct index
		}

		stack.offerFirst(0);
		for (int i = 1; i < input.length; i++) {
			while (stack.size() > 0) {
				int t = stack.peekFirst();// t is the index
				if (input[t] < input[i]) {
					result[t] = i;
					stack.pollFirst();
				} else {
					break;
				}
			}
			stack.offerFirst(i);
		}
		return result;
	}

	public static void main(String args[]) {
		LargerElementOnRight leo = new LargerElementOnRight();
		int input[] = { 4, 2, -8, 6, 0, -3, -1, 1, 9 };
		int result[] = leo.larger(input);
		leo.larger_bruteforce(input);
		System.out.println();
		for (int i = 0; i < result.length; i++) {
			if (result[i] != -1) {
				System.out.print(input[result[i]] + " ");
			} else {
				System.out.print("-1");
			}
		}
	}
}
