package com.algo.ds.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms {

	/**
	 * Problem 1 - https://www.programcreek.com/2014/07/leetcode-meeting-rooms-java/
	 * 
	 * Given an array of meeting time intervals consisting of start and end times [s1, e1], [s2, e2], ... , determine if
	 * a person could attend all meetings.
	 * 
	 * For example, Given [ [0, 30], [5, 10], [15, 20] ], return false.
	 */
	public boolean canAttendAllMeetings(Interval[] intervals) {
		// first sort in increasing order of start time
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});

		// check if ith interval's end time is more than (i+1)th interval start time
		for (int i = 0; i < intervals.length - 1; i++) {
			if (intervals[i].end > intervals[i + 1].start)
				return false;
		}

		return true;
	}

	/**
	 * Problem 2 - Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si
	 * < ei), find the minimum number of conference rooms required.
	 * 
	 * https://leetcode.com/problems/meeting-rooms-ii/
	 * 
	 * T - O(n log n) S - O(n)
	 */
	public int minMeetingRooms1(Interval[] intervals) {
		int[] start = new int[intervals.length];
		int[] end = new int[intervals.length];

		for (int i = 0; i < intervals.length; i++) {
			start[i] = intervals[i].start;
			end[i] = intervals[i].end;
		}

		Arrays.sort(start);
		Arrays.sort(end);

		int j = 0;
		int rooms = 0;
		for (int i = 0; i < start.length; i++) {
			if (start[i] < end[j]) {
				rooms++;
			} else {
				j++;
			}
		}
		return rooms;
	}

	public int minMeetingRooms(Interval[] intervals) {
		if (intervals.length == 0) {
			return 0;
		}

		Arrays.sort(intervals, (a, b) -> a.start - b.start); // sorts in ascending order of start time

		PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.end - b.end);
		pq.offer(intervals[0]);
		int rooms = 1;
		for (int i = 1; i < intervals.length; i++) {
			Interval it = pq.poll();
			if (it.end <= intervals[i].start) {
				it = new Interval(it.start, intervals[i].end);
			} else {
				rooms++;
				pq.offer(intervals[i]);
			}
			pq.offer(it);
		}
		return rooms;
	}

	public static void main(String[] args) {
		MeetingRooms mr = new MeetingRooms();
		Interval[] intervals = new Interval[3];
		intervals[0] = new Interval(0, 5);
		intervals[1] = new Interval(5, 10);
		intervals[2] = new Interval(15, 20);
		System.out.println(mr.minMeetingRooms1(intervals));
		System.out.println(mr.minMeetingRooms(intervals));
		System.out.println(mr.canAttendAllMeetings(intervals));
	}
}

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}
