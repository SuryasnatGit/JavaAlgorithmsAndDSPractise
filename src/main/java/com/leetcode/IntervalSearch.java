package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Give a two-dimensional array of Intervals, each of which represents a receiver, and each receiver has a set of
 * intervals. Then give an integer and ask to find all receivers whose interval contains this integer
 *
 * A wants to receive 1 to 4, 7 to 9, 12 to 15 B wants to receive 2 to 8, 10 to 12 C wants to receive 5 to 6 If you give
 * a number 8, it should return A and B If you give a number 5, it should return B and C
 * 
 * Everyone’s intervals are sorted, so binary search can be used, which is better than brute force
 *
 */
public class IntervalSearch {

	List<Integer> match(List<List<Interval>> source, int point) {
		List<Integer> res = new ArrayList<Integer>();

		for (int i = 0; i < source.size(); i++) {
			List<Interval> list = source.get(i);

			int left = 0, right = list.size() - 1;
			while (left <= right) {
				int mid = (left + right) / 2;
				Interval in = list.get(mid);

				if (point >= in.start && point <= in.end) {
					res.add(i);
					break;
				} else if (point < in.start) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
		}

		return res;
	}
}
