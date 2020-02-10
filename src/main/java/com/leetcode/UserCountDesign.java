package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * TODO: check further. ready on interval and segment tree
 * 
 * A period of time where users login and logout, given a sets of login and logout time pairs, write a function that can
 * show the number of users online at any given time.
 * 
 * Given input which is vector of log entries of some online system each entry is something like (user_name, login_time,
 * logout_time), come up with an algorithm with outputs number of users logged in the system at each time slot in the
 * input, output should contain only the time slot which are in the input.
 * 
 * INput : [ # ("Jane", 1.2, 4.5), # ("Jin", 3.1, 6.7), # ("June", 8.9, 10.3) # ]
 * 
 * # =>
 * 
 * # [(1.2, 1), (3.1, 2), (4.5, 1), (6.7, 0), (8.9, 1), (10.3, 0)]
 */
public class UserCountDesign {

	static class Interval implements Comparable<Interval> {
		double start;
		double end;

		Interval(double s, double e) {
			start = s;
			end = e;
		}

		@Override
		public String toString() {
			return "Start " + start + " End " + end;
		}

		@Override
		public int compareTo(Interval o1) {
			return (int) (this.start - o1.start);
		}
	}

	public static void main(String[] a) {
		Map<Double, Integer> map = new HashMap();
		List<Interval> intervalList = new ArrayList();
		Interval intr = new Interval(1.2, 4.5);
		intervalList.add(intr);
		intr = new Interval(3.1, 6.7);
		intervalList.add(intr);
		intr = new Interval(8.9, 10.3);
		intervalList.add(intr);

		Collections.sort(intervalList);
		for (Interval i : intervalList) {
			map.put(i.start, 0);
			map.put(i.end, 0);
		}
		Interval prev = intervalList.get(0);
		map.put(prev.start, 1);
		for (int i = 1; i < intervalList.size(); i++) {
			Interval curr = intervalList.get(i);
			map.put(curr.start, 1);
			if (curr.start > prev.start && curr.start < prev.end) {
				map.put(curr.start, map.get(prev.start) + 1);
				if (curr.end > prev.start && curr.end < prev.end) {
					map.put(curr.end, map.get(prev.end) + 1);
				} else {
					map.put(curr.end, map.get(prev.end));
					map.put(prev.end, map.get(prev.end) + 1);
				}
			}
			prev = curr;
		}
		System.out.println(map);
	}

	// Solution 1 - segment tree / interval tree

	private TreeMap<Integer, Integer> timeCount = new TreeMap<Integer, Integer>();
	private Map<Integer, List<Interval>> userInterval = new HashMap<Integer, List<Interval>>();

	public List<Interval> insert(int userId, double start, int end) {
		List<Interval> res = new ArrayList<Interval>();

		if (!userInterval.containsKey(userId)) {
			Interval in = new Interval(start, end);
			res.add(in);
			return res;
		} else {
			List<Interval> existing = userInterval.get(userId);

			int pos = 0;
			while (pos < existing.size() && existing.get(pos).end < start) {
				pos++;
			}

			// completely overwrite the new interval
			if (existing.get(pos).start <= start && existing.get(pos).end >= end) {
				return res;
			}

			if (existing.get(pos).start >= end) { // completely independent
				res.add(new Interval(start, end));
				return res;
			}

			while (pos < existing.size() && existing.get(pos).start < end) {
				Interval now = existing.get(pos);

				// Not sure if this will work.....
				if (start < now.start) {
					res.add(new Interval(start, now.start));
				}

				if (end > now.end) {
					start = now.end;
				}
				pos++;
			}
		}

		return res;
	}

}

class Interval {
	int start;
	int end;

	public Interval(int st, int e) {
		this.start = st;
		this.end = e;
	}
}