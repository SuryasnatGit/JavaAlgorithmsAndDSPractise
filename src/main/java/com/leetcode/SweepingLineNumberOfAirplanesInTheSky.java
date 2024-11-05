package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?
 * 
 * Notice
 * 
 * If landing and flying happens at the same time, we consider landing should happen at first.
 * 
 * Have you met this question in a real interview? Yes Example For interval list
 * 
 * [ [1,10], [2,3], [5,8], [4,7] ] Return 3
 * 
 * 1.2 find the point which have the maximum overlap of intervals
 * 
 * Category : Hard
 */
public class SweepingLineNumberOfAirplanesInTheSky {

	public static void main(String[] args) {
		SweepingLineNumberOfAirplanesInTheSky sw = new SweepingLineNumberOfAirplanesInTheSky();
		List<Interval> list = new ArrayList<Interval>();
		list.add(new Interval(1, 10));
		list.add(new Interval(2, 3));
		list.add(new Interval(5, 8));
		list.add(new Interval(4, 7));

		System.out.println(sw.countOfAirplanes(list));
	}

	// O(M * N)
	public int countOfAirplanes(List<Interval> airplanes) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int res = 0;

		for (Interval in : airplanes) {
			for (int i = in.start; i < in.end; i++) {
				map.put(i, map.getOrDefault(i, 0) + 1);
				res = Math.max(res, map.get(i));
			}
		}

		return res;
	}

	// O(Nlog(N) + 2 * N)
	public int countOfAirplanesSweepingLine(List<Interval> airplanes) {
		List<Point> list = new ArrayList<Point>();

		for (Interval in : airplanes) {
			Point start = new Point(in.start, 1);
			Point end = new Point(in.end, 0);

			list.add(start);
			list.add(end);
		}

		Collections.sort(list);

		int count = 0;
		int max = 0;
		for (Point p : list) {
			if (p.isStart == 1) { // start
				count++;
			} else { // end
				count--;
			}

			max = Math.max(max, count);
		}

		return max;
	}

	class Point implements Comparable<Point> {
		int time;
		int isStart; // Easier to compare
		// boolean isStart;

		Point(int time, int isStart) {
			this.time = time;
			this.isStart = isStart;
		}

		public int compareTo(Point that) {
			if (this.time != that.time) {
				return this.time - that.time;
			}

			return this.isStart - that.isStart; // The plane lands first and then takes off
		}
	}

	static class Interval {
		int start;
		int end;

		public Interval(int s, int e) {
			this.start = s;
			this.end = e;
		}
	}
}
