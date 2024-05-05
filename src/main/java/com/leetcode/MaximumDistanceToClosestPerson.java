package com.leetcode;

/**
 * 
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty. There is at
 * least one empty seat, and at least one person sitting. Alex wants to sit in the seat such that the distance between
 * him and the closest person to him is maximized. Return that maximum distance to closest person.
 * 
 * Example 1:
 * 
 * Input: [1,0,0,0,1,0,1]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * If Alex sits in the second open seat (seats[2]), then the closest person has distance 2. If Alex sits in any other
 * open seat, the closest person has distance 1. Thus, the maximum distance to the closest person is 2.
 * 
 * Example : 2
 * 
 * Input: [1,0,0,0]
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * If Alex sits in the last seat, the closest person is 3 seats away. This is the maximum distance possible, so the
 * answer is 3.
 * 
 * Category : Medium
 */
public class MaximumDistanceToClosestPerson {

	public int maxDistance(int[] seats) {
		int left = -1, maxDistance = 0;

		for (int i = 0; i < seats.length; i++) {
			// if current seat is empty
			if (seats[i] == 0) {
				continue;
			}

			// left boundry
			if (left < 0) {
				maxDistance = Math.max(maxDistance, i);
			} else {
				maxDistance = Math.max(maxDistance, (i - left) / 2);
			}
			left = i;
		}

		if (seats[seats.length - 1] == 0) {
			maxDistance = Math.max(maxDistance, seats.length - 1 - left);
		}

		return maxDistance;
	}

	public static void main(String[] args) {
		MaximumDistanceToClosestPerson max = new MaximumDistanceToClosestPerson();
		System.out.println(max.maxDistance(new int[] { 1, 0, 0, 0, 1, 0, 1 }));
		System.out.println(max.maxDistance(new int[] { 1, 0, 0, 0 }));
		System.out.println(max.maxDistance(new int[] { 0, 0, 0, 1 }));
		System.out.println(max.maxDistance(new int[] { 0, 0, 0, 0 }));
		System.out.println(max.maxDistance(new int[] { 1, 1, 1 }));
	}

}
