package com.algo.ds.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Given input array of 0s and 1s and number of flips allowed from 0 to 1, what is maximum consecutive 1s we can have in
 * array
 *
 * Time complexity - O(n) Space complexity - O(1)
 *
 * http://www.geeksforgeeks.org/find-zeroes-to-be-flipped-so-that-number-of-consecutive-1s-is-maximized/
 * 
 * e.g. input: arr={1 1 0 1 1 0 0 1 1 1 } m=1 output={1 1 1 1 1 0 0 1 1 1} position=2
 * 
 * arr={1 1 0 1 1 0 0 1 1 1 } m=2 output={1 1 0 1 1 1 1 1 1 1} position=5,6
 * 
 * @author Tushar Roy
 */
public class Flip0sMaximum1s {

	public int flip0sToMaximizeConsecutive1s(int input[], int flipsAllowed) {

		int windowStart = 0;
		int countZero = 0;
		int result = 0;
		for (int i = 0; i < input.length; i++) {
			if (input[i] == 1) {
				result = Math.max(result, i - windowStart + 1);
			} else {
				if (countZero < flipsAllowed) {
					countZero++;
					result = Math.max(result, i - windowStart + 1);
				} else {
					while (true) {
						if (input[windowStart] == 0) {
							windowStart++;
							break;
						}
						windowStart++;
					}
				}
			}
		}
		return result;
	}

	/**
	 * You are given with an array of 1s and 0s. And you are given with an integer m, which signifies number of flips
	 * allowed.
	 * 
	 * find the position of zeros which when flipped will produce maximum continuous series of 1s.
	 * 
	 * e.g. input: arr={1 1 0 1 1 0 0 1 1 1 } m=1 output={1 1 1 1 1 0 0 1 1 1} position=2
	 * 
	 * arr={1 1 0 1 1 0 0 1 1 1 } m=2 output={1 1 0 1 1 1 1 1 1 1} position=5,6
	 * 
	 * This is similar to the max subarray problem and can be solved in O(n) time using something similar to Kadane's
	 * algorithm. The main idea is maintaining a sliding window that contains a continuous sequence of 1's as we iterate
	 * over the array. We extend the right side of the window if we encounter a 1 or the number of flips is less than m;
	 * otherwise we shrink the left side to one position to the right of the first zero. The last part forces us to
	 * maintain a list of the positions of the zeros. Thus O(m) space. Here a solution in Java.
	 * 
	 * @param arr
	 * @param m
	 * @return
	 */
	public List<Integer> maximumContinuousOnes(int[] arr, int m) {
		// initialize variables
		int maxBegin = 0;
		int maxEnd = 0;
		int maxLength = 0;
		int begin = 0;

		// holder for 0 elements
		ArrayDeque<Integer> deque = new ArrayDeque<>();

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				if (deque.size() < m) {
					deque.addFirst(i);
				} else {
					int length = i - begin;
					if (length > maxLength) {
						maxLength = length;
						maxBegin = begin;
						maxEnd = i;
					}
					begin = deque.removeLast() + 1;
					deque.addFirst(i);
				}
			}
		}

		int length = arr.length - begin;
		if (length > maxLength) {
			maxBegin = begin;
			maxEnd = arr.length;
		}

		List<Integer> list = new ArrayList<>();
		for (int i = maxBegin; i < maxEnd; i++) {
			if (arr[i] == 0)
				list.add(i);
		}

		return list;
	}

	public static void main(String args[]) {
		int input[] = { 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1 };
		Flip0sMaximum1s fm = new Flip0sMaximum1s();
		System.out.println(fm.flip0sToMaximizeConsecutive1s(input, 1));

		int[] input1 = { 1, 1, 0, 1, 1, 0, 0, 1, 1, 1 };
		System.out.println(fm.maximumContinuousOnes(input1, 1));
		System.out.println(fm.maximumContinuousOnes(input1, 2));
	}
}
