package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of
 * points that lie on the same straight line.
 * 
 * https://leetcode.com/problems/max-points-on-a-line/
 * 
 * TODO : understand
 * 
 * Category : Hard
 *
 */
public class MaxPointsInALine {

	public int maxPoints(Point[] points) {

		if (points.length <= 2) {
			return points.length;
		}

		int res = Integer.MIN_VALUE;

		for (int i = 0; i < points.length; i++) {

			Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();

			int overlap = 0, max = 0;

			for (int j = i + 1; j < points.length; j++) {

				int x = points[i].x - points[j].x;
				int y = points[i].y - points[j].y;

				if (x == 0 && y == 0) {
					overlap++;
					continue;
				}

				// Calculate the greatest common factor and divide X and Y by the greatest common factor. In fact, the
				// only fraction representing the slope is found. The decimal is not accurate. If the fraction is not
				// reduced, it cannot be represented unique
				int gcd = getGreatestCommonDivisor(x, y);

				// gcd can't be 0
				x = x / gcd;
				y = y / gcd;

				// Check map history
				if (map.containsKey(x)) {
					Map<Integer, Integer> yMap = map.get(x);
					yMap.put(y, yMap.getOrDefault(y, 0) + 1);
					map.put(x, yMap);
				} else {
					Map<Integer, Integer> yMap = new HashMap<Integer, Integer>();
					yMap.put(y, 1);
					map.put(x, yMap);
				}

				max = Math.max(max, map.get(x).get(y));
			}

			// overlap must be used outside of the inner loop
			res = Math.max(res, max + overlap + 1);
		}

		return res;
	}

	private int getGreatestCommonDivisor(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return getGreatestCommonDivisor(b, a % b);
		}
	}
}

class Point {
	int x;
	int y;
}
