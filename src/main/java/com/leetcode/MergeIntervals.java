package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 
 * Category : Hard
 * 
 */
public class MergeIntervals {

	/**
	 * Problem 1 : Given a collection of intervals, merge all overlapping intervals.
	 * 
	 * Example 1:
	 * 
	 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]] Explanation: Since intervals [1,3]
	 * and [2,6] overlaps, merge them into [1,6].
	 * 
	 * Example 2:
	 * 
	 * Input: intervals = [[1,4],[4,5]] Output: [[1,5]] Explanation: Intervals [1,4] and [4,5] are considered
	 * overlapping.
	 * 
	 */
	public int[][] merge(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return new int[0][0];
		}

		// sort the input intervals
		List<Interval> intervalList = new ArrayList<>();
		for (int[] interval : intervals) {
			Interval i = new Interval(interval[0], interval[1]);
			intervalList.add(i);
		}

		Collections.sort(intervalList, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});

		Stack<Interval> stack = new Stack<>();
		// put first interval in stack
		stack.push(intervalList.get(0));

		// start from next interval
		for (int i = 1; i < intervalList.size(); i++) {
			Interval top = stack.peek();
			Interval curr = intervalList.get(i);
			if (top.end < curr.start) {
				stack.push(curr);
			} else if (top.end < curr.end) {
				top.end = curr.end;
				stack.pop();
				stack.push(top);
			}
		}

		// dump contents of stack to result
		int[][] result = new int[stack.size()][2];
		int c = 0;
		while (!stack.isEmpty()) {
			Interval i = stack.pop();
			int[] interval = new int[2];
			interval[0] = i.start;
			interval[1] = i.end;
			result[c++] = interval;
		}

		return result;
	}

	public List<Interval> merge(List<Interval> intervals) {

		List<Interval> res = new ArrayList<Interval>();
		if (intervals.size() == 0) {
			return res;
		}

		PriorityQueue<Interval> heap = new PriorityQueue<Interval>(new IntervalComparator());
		for (int i = 0; i < intervals.size(); i++) {
			heap.offer(intervals.get(i));
		}

		Interval cur = heap.poll();
		while (!heap.isEmpty()) {
			Interval next = heap.poll();
			if (next.start <= cur.end) { // Overlap
				// No need to offer back
				cur = new Interval(cur.start, Math.max(cur.end, next.end));
			} else { // Gap
				res.add(cur);
				cur = next;
			}
		}

		res.add(cur);

		return res;
	}

	// TODO: Question: How do you add intervals and merge them for a large stream of intervals? (Facebook Follow-up
	// Question) . INterval tree

	// https://leetcode.com/problems/merge-intervals/solution/

	// https://leetcode.com/problems/merge-intervals/discuss/21452/Share-my-interval-tree-solution-no-sorting

	class Interval {
		int start;
		int end;

		public Interval(int s, int e) {
			this.start = s;
			this.end = e;
		}
	}

	class IntervalComparator implements Comparator<Interval> {

		public int compare(Interval i1, Interval i2) {
			if (i1.start == i2.start) {
				return i1.end - i2.end;
			}
			return i1.start - i2.start;
		}
	}

	/**
	 * 
	 * Interval List A: <1,3> <5,7> B: <4,6>. Output intersection <5,6> Output Union <1,7> The requirement is to merge
	 * if the end of the previous interval is the start-1 of the latter
	 * 
	 * Maintain double pointers, but the most important principle is to compare two pointers but only move one pointer
	 * at a time
	 * 
	 * New: a variant of merge interval, merge the overlap interval in the two lists
	 * 
	 */
	// You can use the bucket sort idea First merge the list by yourself to ensure that there is no overlap
	public List<Interval> intersection(List<Interval> A, List<Interval> B) {
		List<Interval> res = new ArrayList<Interval>();
		int pos1 = 0;
		int pos2 = 0;

		while (pos1 < A.size() && pos2 < B.size()) {
			Interval in1 = A.get(pos1);
			Interval in2 = B.get(pos2);

			if (in1.end < in2.start) {
				pos1++;
			} else if (in2.end < in1.start) {
				pos2++;
			} else {
				Interval in = new Interval(Math.max(in1.start, in2.start), Math.min(in1.end, in2.end));
				res.add(in);

				// Move the one which ends earlier. Great!!! There is still a problem
				if (in1.end < in2.end) {
					pos1++;
				} else {
					pos2++;
				}
			}
		}

		for (Interval in : res) {
			System.out.println(in.start + "==" + in.end);
		}

		return res;
	}

	public List<Interval> union(List<Interval> A, List<Interval> B) {
		List<Interval> res = new ArrayList<Interval>();
		int pos1 = 1;
		int pos2 = 0;

		res.add(A.get(0));
		while (pos1 < A.size() && pos2 < B.size()) {
			Interval in1 = A.get(pos1);
			Interval in2 = B.get(pos2);
			Interval prev = res.get(res.size() - 1);

			// Use only 1 each time
			if (in1.start < in2.start) {
				if (prev.end + 1 < in1.start) {
					res.add(in1);
					pos1++;
				} else {
					prev.end = Math.max(prev.end, in1.end);
					pos1++;
				}
			} else {
				if (prev.end + 1 < in2.start) {
					res.add(in2);
					pos2++;
				} else {
					prev.end = Math.max(prev.end, in2.end);
					pos2++;
				}
			}
		}

		// Merge res and whichever left, A or B
		while (pos1 < A.size()) {
			Interval prev = res.get(res.size() - 1);
			Interval now = A.get(pos1);

			if (prev.end + 1 >= now.start) {
				prev.end = now.end;
			} else {
				res.add(now);
			}
			pos1++;
		}

		while (pos2 < B.size()) {
			Interval prev = res.get(res.size() - 1);
			Interval now = B.get(pos2);

			if (prev.end + 1 >= now.start) {
				prev.end = now.end;
			} else {
				res.add(now);
			}
			pos2++;
		}

		for (Interval in : res) {
			System.out.println(in.start + "==" + in.end);
		}

		return res;
	}

	/**
	 * Problem 2 :
	 * 
	 * Merge Intervals wrote two functions addInterval, getTotalBusyTime. Write two different implementations to analyze
	 * trade off (basically it is determined based on the frequency of calling the two functions)
	 * 
	 * (1) LinkedList is inserted so that start remains in order after each insertion and keeps all nodes disconnected
	 * while calculating total busy. O(N) add time and O(1) get time
	 * 
	 * (2) Binary search tree. That is map, treemap. Keep the order after insertion, O(logN) add O(N) get time
	 * 
	 * TODO: follow up If you need to remove interval, which way?
	 */

	// Solution 1 : time complexity: call O(n), get O(1)
	private Node head = new Node(0, 0);
	private int totalLength = 0;

	public void addInterval(int start, int end) {
		if (start >= end) {
			return;
		}
		Node mover = head.next;
		Node prev = head;
		while (mover != null) {
			if (end < mover.start) {
				Node node = new Node(start, end);
				mover.pre.next = node;
				node.pre = mover.pre;
				node.next = mover;
				mover.pre = node;
				totalLength += end - start;
				break;
			} else if (start > mover.end) {
				prev = mover;
				mover = mover.next;
			} else {
				totalLength -= mover.end - mover.start;
				start = Math.min(start, mover.start);
				end = Math.max(end, mover.end);
				Node next = mover.next;
				mover.pre.next = mover.next;
				if (mover.next != null) {
					mover.next.pre = mover.pre;
				}
				mover.next = null;
				mover.pre = null;
				mover = next;
			}
		}
		if (mover == null) {
			totalLength += end - start;
			prev.next = new Node(start, end);
			prev.next.pre = prev;
		}
	}

	public int getTotalBusyTime() {
		return totalLength;
	}

	class Node {
		Node pre = null;
		Node next = null;
		int start;
		int end;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	// Solution 2 : time complexity: call O(1), get O(nlgn)
	private List<Interval> intervals = new ArrayList<>();

	public void add1(int start, int end) {
		if (start >= end) {
			return;
		}
		intervals.add(new Interval(start, end));
	}

	public int getTotalLength1() {
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval inter1, Interval inter2) {
				return inter1.start - inter2.start;
			}
		});
		int start = intervals.get(0).start;
		int end = intervals.get(0).end;
		int totalLen = 0;
		for (Interval inter : intervals) {
			if (end >= inter.start) {
				end = Math.max(inter.end, end);
			} else {
				totalLen += end - start;
				end = inter.end;
				start = inter.start;
			}
		}
		totalLen += end - start;
		return totalLen;
	}

	public static void main(String[] args) {
		MergeIntervals mi = new MergeIntervals();
		int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
		Arrays.stream(mi.merge(intervals)).forEach(a -> System.out.println(Arrays.toString(a)));
	}

}
