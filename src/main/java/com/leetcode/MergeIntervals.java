package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * Example 1:
 * 
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]] Explanation: Since intervals [1,3] and
 * [2,6] overlaps, merge them into [1,6].
 * 
 * Example 2:
 * 
 * Input: intervals = [[1,4],[4,5]] Output: [[1,5]] Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * Category : Medium
 * 
 */
public class MergeIntervals {

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

	class Interval {
		private int start;
		private int end;

		public Interval(int s, int e) {
			this.start = s;
			this.end = e;
		}
	}

	public static void main(String[] args) {
		MergeIntervals mi = new MergeIntervals();
		int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
		Arrays.stream(mi.merge(intervals)).forEach(a -> System.out.println(Arrays.toString(a)));
	}

}
