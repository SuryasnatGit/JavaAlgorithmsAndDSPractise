package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRoomProblems {

	/**
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
	 * When a room is taken, the room can not be used for anther meeting until the current meeting is over. As soon as
	 * the current meeting is finished, the room can be used for another meeting. We can sort the meetings by start
	 * timestamps and sequentially assign each meeting to a room. Each time when we assign a room for a meeting, we
	 * check if any meeting is finished so that the room can be reused
	 * 
	 * T - O(n log n) S - O(n)
	 */
	public int minMeetingRoomsRequired1(Interval[] intervals) {
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

	/**
	 * In order to efficiently track the earliest ending meeting, we can use a min heap. Whenever an old meeting ends
	 * before a new meeting starts, we reuse the room (i.e., do not add more room). Otherwise, we need an extra room
	 * (i.e., add a room)
	 * 
	 * T - O(n log n)
	 */
	public int minMeetingRoomsRequired(Interval[] intervals) {
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

	/**
	 * Regarding schedule meeting, get the time period when everyone is available. Assuming there are three people, A,
	 * B, and C, each has a different schedule. Find out the time period that can put them together. This is an example.
	 * The question is not so specific. You have to ask yourself a little bit about the boundaries and requirements.
	 */
	public List<Interval> getAvailableIntervals(List<Interval> list) {
		int start = Integer.MAX_VALUE;
		int end = Integer.MIN_VALUE;

		for (Interval in : list) {
			start = Math.min(start, in.start);
			end = Math.max(end, in.end);
		}

		List<Interval> res = new ArrayList<Interval>();
		boolean[] occupied = new boolean[end - start + 1];

		for (Interval in : list) {
			for (int i = in.start; i < in.end; i++) { // Not include end point
				occupied[i - start] = true;
			}
		}

		int left = -1;
		for (int i = start; i < end;) { // Not include end point
			if (!occupied[i - start]) { // 发现起点 就顺着找终点
				left = i;

				int right = left + 1;
				while (right <= end && !occupied[right - start]) {
					right++;
				}

				res.add(new Interval(left, right));

				i = right;
			} else {
				i++;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		MeetingRoomProblems mr = new MeetingRoomProblems();
		Interval[] intervals = new Interval[3];
		intervals[0] = new Interval(0, 5);
		intervals[1] = new Interval(5, 10);
		intervals[2] = new Interval(15, 20);
		System.out.println(mr.minMeetingRoomsRequired1(intervals));
		System.out.println(mr.minMeetingRoomsRequired(intervals));
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
