package com.algo.ds.queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 
 * Find median in stream of numbers
 * 
 * https://leetcode.com/problems/find-median-from-data-stream/
 */
public class MedianFinder {

	interface FindMedian {
		public void addNum(int num);

		public int findMedian();
	}

	/**
	 * Approach 1 - Using simple sort. Store the numbers in a resize-able container. Every time you need
	 * to output the median, sort the container and output the median. time complexity - O(N log N)
	 * Space complexity - O(N)
	 *
	 */
	class UsingSimpleSort implements FindMedian {

		List<Integer> list = new ArrayList<>();

		public void addNum(int num) {
			list.add(num);
		}

		public int findMedian() {
			Collections.sort(list); // O(N log N)
			int size = list.size();
			return (size % 2 == 0 ? (list.get(size / 2 - 1) + list.get(size / 2)) / 2 : list.get(size / 2));
		}
	}

	/**
	 * Approach 2 - Using insertion sort.
	 * 
	 * Which algorithm allows a number to be added to a sorted list of numbers and yet keeps the entire
	 * list sorted? Well, for one, insertion sort!
	 * 
	 * We assume that the current list is already sorted. When a new number comes, we have to add it to
	 * the list while maintaining the sorted nature of the list. This is achieved easily by finding the
	 * correct place to insert the incoming number, using a binary search (remember, the list is always
	 * sorted). Once the position is found, we need to shift all higher elements by one space to make
	 * room for the incoming number.
	 * 
	 * This method would work well when the amount of insertion queries is lesser or about the same as
	 * the amount of median finding queries.
	 * 
	 * time complexity - O(N) Space complexity - O(N)
	 *
	 */
	class UsingInsertionSort implements FindMedian {

		List<Integer> list = new ArrayList<>();

		@Override
		public void addNum(int num) {
			list.add(num);
			list.sort(null); // assume it is sorted in insertion sort
		}

		@Override
		public int findMedian() {
			int size = list.size();
			return (size % 2 == 0 ? (list.get(size / 2 - 1) + list.get(size / 2)) / 2 : list.get(size / 2));
		}

	}

	/**
	 * Approach 3 - Keeping the entire input sorted is not a requirement.
	 * 
	 * Heaps are a natural ingredient for this dish! Adding elements to them take logarithmic order of
	 * time. They also give direct access to the maximal/minimal elements in a group.
	 * 
	 * If we could maintain two heaps in the following way:
	 * 
	 * A max-heap to store the smaller half of the input numbers A min-heap to store the larger half of
	 * the input numbers This gives access to median values in the input: they comprise the top of the
	 * heaps.
	 * 
	 * If the following conditions are met:
	 * 
	 * Both the heaps are balanced (or nearly balanced) The max-heap contains all the smaller numbers
	 * while the min-heap contains all the larger numbers then we can say that:
	 * 
	 * All the numbers in the max-heap are smaller or equal to the top element of the max-heap (let's
	 * call it xx) All the numbers in the min-heap are larger or equal to the top element of the
	 * min-heap (let's call it yy) Then xx and/or yy are smaller than (or equal to) almost half of the
	 * elements and larger than (or equal to) the other half. That is the definition of median elements.
	 * 
	 * This leads us to a huge point of pain in this approach: balancing the two heaps!
	 * 
	 * Algorithm
	 * 
	 * Two priority queues:
	 * 
	 * A max-heap lo to store the smaller half of the numbers A min-heap hi to store the larger half of
	 * the numbers The max-heap lo is allowed to store, at worst, one more element more than the
	 * min-heap hi. Hence if we have processed kk elements:
	 * 
	 * If k = 2*n + 1 \quad (\forall \, n \in \mathbb{Z})k=2∗n+1(∀n∈Z), then lo is allowed to hold
	 * n+1n+1 elements, while hi can hold nn elements. If k = 2*n \quad (\forall \, n \in
	 * \mathbb{Z})k=2∗n(∀n∈Z), then both heaps are balanced and hold nn elements each. This gives us the
	 * nice property that when the heaps are perfectly balanced, the median can be derived from the tops
	 * of both heaps. Otherwise, the top of the max-heap lo holds the legitimate median.
	 * 
	 * Adding a number num:
	 * 
	 * Add num to max-heap lo. Since lo received a new element, we must do a balancing step for hi. So
	 * remove the largest element from lo and offer it to hi. The min-heap hi might end holding more
	 * elements than the max-heap lo, after the previous operation. We fix that by removing the smallest
	 * element from hi and offering it to lo. The above step ensures that we do not disturb the nice
	 * little size property we just mentioned.
	 * 
	 * time complexity - O(log N). space complexity - O(n)
	 * 
	 */
	class UsingPriorityQueue implements FindMedian {
		PriorityQueue<Integer> minPq = new PriorityQueue<>();
		PriorityQueue<Integer> maxPq = new PriorityQueue<>();

		public UsingPriorityQueue() {
			minPq = new PriorityQueue<>();
			maxPq = new PriorityQueue<>(20, Collections.reverseOrder());
		}

		// Adds a number into the data structure.
		public void addNum(int num) {
			if (maxPq.size() == 0) {
				maxPq.add(num);
				return;
			}
			if (maxPq.size() == minPq.size()) {
				if (minPq.peek() < num) {
					maxPq.offer(minPq.poll());
					minPq.offer(num);
				} else {
					maxPq.offer(num);
				}
			} else {
				int toBeOffered = 0;
				if (num >= maxPq.peek()) {
					toBeOffered = num;
				} else {
					toBeOffered = maxPq.poll();
					maxPq.offer(num);
				}
				minPq.offer(toBeOffered);
			}
		}

		// Returns the median of current data stream
		public int findMedian() {
			if (minPq.size() == maxPq.size()) {
				return (minPq.peek() + maxPq.peek()) / 2;
			} else {
				return maxPq.peek();
			}
		}
	}

	/**
	 * Approach 4 - Self-balancing Binary Search Trees (like an AVL Tree) have some very interesting
	 * properties. They maintain the tree's height to a logarithmic bound. Thus inserting a new element
	 * has reasonably good time performance. The median always winds up in the root of the tree and/or
	 * one of its children. Solving this problem using the same approach as Approach #3 but using a
	 * Self-balancing BST seems like a good choice. Except the fact that implementing such a tree is not
	 * trivial and prone to errors.
	 * 
	 * time complexity - O(N log N) space complexity - O(N)
	 * 
	 * @author surya
	 *
	 */
	class UsingSelfBalancedBST implements FindMedian {

		@Override
		public void addNum(int num) {
			// TODO Auto-generated method stub

		}

		@Override
		public int findMedian() {
			// TODO Auto-generated method stub
			return 0;
		}

	}

    public static void main(String args[]) {
        MedianFinder mf = new MedianFinder();

		MedianFinder.UsingPriorityQueue pq = mf.new UsingPriorityQueue();
		// MedianFinder.UsingSimpleSort pq = mf.new UsingSimpleSort();
		// MedianFinder.UsingInsertionSort pq = mf.new UsingInsertionSort();

		pq.addNum(4);
		System.out.println(pq.findMedian());
		pq.addNum(8);
		System.out.println(pq.findMedian());
		pq.addNum(2);
		System.out.println(pq.findMedian());
		pq.addNum(11);
		System.out.println(pq.findMedian());
		pq.addNum(13);
		System.out.println(pq.findMedian());
		pq.addNum(14);
		System.out.println(pq.findMedian());
		pq.addNum(-1);
		System.out.println(pq.findMedian());

    }
}
