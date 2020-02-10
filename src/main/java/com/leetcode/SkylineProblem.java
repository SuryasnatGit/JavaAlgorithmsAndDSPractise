package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a
 * distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo
 * (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 * 
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are
 * the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is
 * guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect
 * rectangles grounded on an absolutely flat surface at height 0.
 * 
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20
 * 10], [19 24 8] ] .
 * 
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ]
 * that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last
 * key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has
 * zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 * 
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8],
 * [24, 0] ].
 *
 * Category : Hard
 */
public class SkylineProblem {

	private PriorityQueue<Event> queue = new PriorityQueue<Event>((a, b) -> b.height - a.height);

	public List<List<Integer>> getSkyline(int[][] buildings) {
		int n = buildings.length;
		ArrayList<Event> events = new ArrayList<Event>();

		for (int i = 0; i < n; ++i) {
			int[] b = buildings[i];
			Event entering = new Event(b[0], b[2], 1);
			Event leaving = new Event(b[1], b[2], -1, entering);
			events.add(entering);
			events.add(leaving);
		}

		events.sort((e1, e2) -> {
			return e1.x == e2.x ? e2.height * e2.type - e1.height * e1.type : e1.x - e2.x;
		});

		List<List<Integer>> ans = new ArrayList<List<Integer>>();

		for (Event e : events) {
			if (e.type == 1) {
				if (this.queue.isEmpty() || e.height > this.queue.peek().height) {
					List<Integer> temp = new ArrayList<Integer>();
					temp.add(e.x);
					temp.add(e.height);
					ans.add(temp);
				}
				this.queue.offer(e);
			} else {
				this.queue.remove(e.enteringEvent);
				if (this.queue.isEmpty() || e.height > this.queue.peek().height) {
					List<Integer> temp = new ArrayList<Integer>();
					temp.add(e.x);
					if (this.queue.isEmpty()) {
						temp.add(0);
					} else {
						temp.add(this.queue.peek().height);
					}
					ans.add(temp);
				}
			}
		}
		return ans;

	}
}

class Event {
	public int x;
	public int height;
	public int type;
	public Event enteringEvent;

	public Event(int x, int h, int type) {
		this.x = x;
		this.height = h;
		this.type = type;
	}

	public Event(int x, int h, int type, Event event) {
		this(x, h, type);
		this.enteringEvent = event;
	}
}
