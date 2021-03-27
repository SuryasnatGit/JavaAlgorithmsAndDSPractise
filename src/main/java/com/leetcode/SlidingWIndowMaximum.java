package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very
 * right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return
 * the max sliding window.
 * 
 * Follow up:
 * 
 * Could you solve it in linear time?
 * 
 * Example:
 * 
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * 
 * Output: [3,3,5,5,6,7]
 * 
 * Explanation:
 * 
 * Window position Max --------------- ----- <br/>
 * [1 3 -1] -3 5 3 6 7 ==> 3 <br/>
 * 1 [3 -1 -3] 5 3 6 7 ==> 3 <br/>
 * 1 3 [-1 -3 5] 3 6 7 ==> 5 <br/>
 * 1 3 -1 [-3 5 3] 6 7 ==> 5 <br/>
 * 1 3 -1 -3 [5 3 6] 7 ==> 6 <br/>
 * 1 3 -1 -3 5 [3 6 7] ==> 7
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 10^5
 * 
 * -10^4 <= nums[i] <= 10^4
 * 
 * 1 <= k <= nums.length
 * 
 * Category : Hard
 * 
 * Hint:
 * 
 * How about using a data structure such as deque (double-ended queue)?
 * 
 * The queue size need not be the same as the window’s size.
 * 
 * Remove redundant elements and the queue should store only elements that need to be considered.
 *
 */
public class SlidingWIndowMaximum {

	public static void main(String[] args) {
		SlidingWIndowMaximum sl = new SlidingWIndowMaximum();
		System.out.println(Arrays.toString(sl.maxSlidingWindowHeapCleaner(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3)));
	}

	public int[] maxSlidingWindowHeapCleaner(int[] nums, int k) {
		if (nums == null || nums.length == 0 || nums.length < k) {
			return new int[] {};
		}

		int[] res = new int[nums.length - k + 1];

		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
			public int compare(Integer val1, Integer val2) {
				return val2 - val1;
			}
		});

		for (int i = 0; i < nums.length; i++) { // N * (K + log(K))
			if (i - k >= 0) { // from k
				heap.remove(nums[i - k]);// because it will slide to right so remove first element in sliding window
			}
			heap.offer(nums[i]);

			if (i - k + 1 >= 0) { // from k - 1
				res[i - k + 1] = heap.peek();
			}
		}

		return res;
	}

	// O(N) method is very good, you have to remember
	// O(N) is a bit like histogram, trapping rain water maintains a monotonically decreasing sequence
	public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0 || nums.length < k) {
			return new int[] {};
		}

		int len = nums.length;
		int[] res = new int[len - k + 1];
		LinkedList<Integer> list = new LinkedList<Integer>(); // 需要两头同时操作，所以LinkedList 实际上是一个双向链表

		for (int i = 0; i < nums.length; i++) { // List存index
			if (!list.isEmpty() && list.peekFirst() < i - k + 1) { // 最左边的过时了
				list.pollFirst();
			}

			// 我们只关心在一个section中的max, 如果左边的值小于新加入的值，左边的小值就没价值了，直接扔掉，这样保证List最左边就总是下一个max
			while (!list.isEmpty() && nums[i] > nums[list.peekLast()]) { // 只留下大数，小数不管用 最终是个递减数列
				list.pollLast();
			}

			list.addLast(i);

			if (i - k + 1 >= 0) {
				res[i - k + 1] = nums[list.peekFirst()];
			}
		}

		return res;
	}
}
