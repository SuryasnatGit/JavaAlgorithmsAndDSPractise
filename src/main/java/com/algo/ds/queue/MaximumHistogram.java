package com.algo.ds.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Video link https://youtu.be/ZmnqCZp9bBs
 * 
 * Given an array representing height of bar in bar graph, find max histogram area in the bar graph.
 * Max histogram will be max rectangular area in the graph.
 * 
 * References: http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 */
public class MaximumHistogram {

	/**
	 * https://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-set-1/
	 * 
	 * TODO:
	 * 
	 * @param input
	 * @return
	 */
	public int maxHistogram_usingSegmentTree(int[] input) {
		return 0;
	}

	/**
	 * Maintain a stack
	 * 
	 * If stack is empty or value at index of stack is less than or equal to value at current index,
	 * push this into stack. Otherwise keep removing values from stack till value at index at top of
	 * stack is less than value at current index. While removing value from stack calculate area if
	 * stack is empty it means that till this point value just removed has to be smallest element so
	 * area = input[top] * i if stack is not empty then this value at index top is less than or equal to
	 * everything from stack top + 1 till i. So area will area = input[top] * (i - stack.peek() - 1);
	 * Finally maxArea is area if area is greater than maxArea.
	 * 
	 * 
	 * Time complexity is O(n) Space complexity is O(n)
	 * 
	 * @param input
	 * @return
	 */
	public int maxHistogram(int input[]) {
		Deque<Integer> stack = new LinkedList<Integer>();
		int maxArea = 0;
		int area = 0;
		int i;
		for (i = 0; i < input.length;) {
			// If this bar is higher than the bar on top of stack, push it to stack
			if (stack.isEmpty() || input[stack.peekFirst()] <= input[i]) {
				stack.offerFirst(i++);
			} else {
				int top = stack.pollFirst();
				// if stack is empty means everything till i has to be
				// greater or equal to input[top] so get area by
				// input[top] * i;
				if (stack.isEmpty()) {
					area = input[top] * i;
				}
				// if stack is not empty then everything from i-1 to input.peek() + 1
				// has to be greater or equal to input[top]
				// so area = input[top]*(i - stack.peek() - 1);
				else {
					area = input[top] * (i - stack.peekFirst() - 1);
				}
				if (area > maxArea) {
					maxArea = area;
				}
			}
		}

		// Now pop the remaining bars from stack and calculate area with every
		// popped bar as the smallest bar
		while (!stack.isEmpty()) {
			int top = stack.pollFirst();
			// if stack is empty means everything till i has to be
			// greater or equal to input[top] so get area by
			// input[top] * i;
			if (stack.isEmpty()) {
				area = input[top] * i;
			}
			// if stack is not empty then everything from i-1 to input.peek() + 1
			// has to be greater or equal to input[top]
			// so area = input[top]*(i - stack.peek() - 1);
			else {
				area = input[top] * (i - stack.peekFirst() - 1);
			}
			if (area > maxArea) {
				maxArea = area;
			}
		}
		return maxArea;
	}

	public static void main(String args[]) {
		MaximumHistogram mh = new MaximumHistogram();
		int input[] = { 2, 2, 2, 6, 1, 5, 4, 2, 2, 2, 2 };
		int maxArea = mh.maxHistogram(input);
		System.out.println(maxArea);
		assert maxArea == 12;
	}
}
