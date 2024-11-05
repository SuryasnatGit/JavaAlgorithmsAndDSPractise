package com.leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So
 * the median is the mean of the two middle value.
 * 
 * Examples: [2,3,4] , the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very
 * right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your
 * job is to output the median array for each window in the original array.
 * 
 * For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * 
 * Window position Median <br/>
 * --------------- ----- <br/>
 * [1 3 -1] -3 5 3 6 7 1 <br/>
 * 1 [3 -1 -3] 5 3 6 7 -1 <br/>
 * 1 3 [-1 -3 5] 3 6 7 -1 <br/>
 * 1 3 -1 [-3 5 3] 6 7 3 <br/>
 * 1 3 -1 -3 [5 3 6] 7 5 <br/>
 * 1 3 -1 -3 5 [3 6 7] 6 <br/>
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 * 
 * Note: You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 * 
 * Category : Hard
 *
 */
public class SlidingWindowMediam {
	public static void main(String[] args) {

	}

	public double[] medianSlidingWindow(int[] nums, int k) {
		MedianFinder medianHeap = new MedianFinder();

		int len = nums.length;
		double[] res = new double[len - k + 1];

		for (int i = 0; i < k && i < len; i++) {
			medianHeap.addNum(nums[i]);
		}

		int index = 0;
		res[index] = medianHeap.findMedian();

		for (int i = k; i < len; i++) {
			int removeIndex = i - k;
			int addIndex = i;

			medianHeap.removeNum(nums[removeIndex]);
			medianHeap.addNum(nums[addIndex]);

			res[i - k + 1] = medianHeap.findMedian();
		}

		return res;
	}
}

// 这实际上是Find median from data stream, 只不过加了一个remove()
class MedianFinder {

	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10, Collections.reverseOrder());

	/** initialize your data structure here. */
	public MedianFinder() {

	}

	public void addNum(int num) {
		maxHeap.offer(num);
		minHeap.offer(maxHeap.poll());

		if (maxHeap.size() < minHeap.size()) {
			maxHeap.offer(minHeap.poll());
		}
	}

	public void removeNum(int num) {
		if (maxHeap.contains(num)) {
			maxHeap.remove(num);
		} else {
			minHeap.remove(num);
		}

		if (maxHeap.size() < minHeap.size()) {
			maxHeap.offer(minHeap.poll());
		}
	}

	public double findMedian() {
		if (maxHeap.size() > minHeap.size()) {
			return maxHeap.peek();
		} else {
			return (minHeap.peek() + maxHeap.peek()) / 2.0;
		}
	}
}
