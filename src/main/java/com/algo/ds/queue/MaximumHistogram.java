package com.algo.ds.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Video link https://youtu.be/ZmnqCZp9bBs
 * 
 * Given an array representing height of bar in bar graph, find max histogram area in the bar graph. Max histogram will
 * be max rectangular area in the graph.
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
	 * If stack is empty or value at index of stack is less than or equal to value at current index, push this into
	 * stack. Otherwise keep removing values from stack till value at index at top of stack is less than value at
	 * current index. While removing value from stack calculate area if stack is empty it means that till this point
	 * value just removed has to be smallest element so area = input[top] * i if stack is not empty then this value at
	 * index top is less than or equal to everything from stack top + 1 till i. So area will area = input[top] * (i -
	 * stack.peek() - 1); Finally maxArea is area if area is greater than maxArea.
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

	/**
	 * 给一个数组,代表一组人的身高。然后输出一个数组,表示在当前人之后的所有 比他高的人里,离他最近的人的身高。 比如输入是[3, 6, 7, 2, 3] 输出就是[6, 7, null, 3, null]。
	 * 我给出了俩解,都是O(n2)的。她希望得到一个O(n)的解
	 * 
	 * 第三题类似那个Leetcode里面的histgram吧
	 * 
	 * 用一个stack记录从右到左的身高 且保持身高递减的 比如例子里面[3,6,7,2,3], 从右往左扫:
	 * 
	 * 3, 因为是最右, 肯定是NULL, 然后把 3放到stack里; 2, 发现stack的top比2大, 于是就是3, 然后把2也放进stack里; 7, 发现2比7小, pop, 发现3 比7小, pop, stack空了
	 * 所以是NULL, 然后把7放进stack里; 6, 发现top=7>6, 所以就是7, 然后把6放进stack里; 3, 发现top=6>3, 所以就是3, 然后把3放进stack里;
	 * 
	 * 时间空间复杂度都是O(n)
	 */
	public int[] nextHigherMyStyle(int[] arr) {
		int len = arr.length;
		int[] res = new int[len];
		Stack<Integer> stack = new Stack<Integer>();

		res[len - 1] = -1;
		stack.push(arr[len - 1]);

		for (int i = arr.length - 2; i >= 0; i--) {
			int now = arr[i];

			while (!stack.isEmpty() && now >= stack.peek()) {
				stack.pop(); // Pop up all the shorter ones on the right
			}

			if (stack.isEmpty()) {
				res[i] = -1;
				stack.push(now);
			} else {
				res[i] = stack.peek();
				stack.push(now);
			}
		}

		return res;
	}

	public static void main(String args[]) {
		MaximumHistogram mh = new MaximumHistogram();
		int input[] = { 2, 2, 2, 6, 1, 5, 4, 2, 2, 2, 2 };
		int maxArea = mh.maxHistogram(input);
		System.out.println(maxArea);
		assert maxArea == 12;
	}
}
