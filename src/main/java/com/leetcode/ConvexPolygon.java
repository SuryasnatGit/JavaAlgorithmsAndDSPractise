package com.leetcode;

import java.util.List;

/**
 * Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon
 * definition).
 * 
 * Note:
 * 
 * There are at least 3 and at most 10,000 points. Coordinates are in the range -10,000 to 10,000. You may assume the
 * polygon formed by given points is always a simple polygon (Simple polygon definition). In other words, we ensure that
 * exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other. Example 1:
 * 
 * [[0,0],[0,1],[1,1],[1,0]]
 * 
 * Answer: True
 * 
 * Explanation: Example 2:
 * 
 * [[0,0],[0,10],[10,10],[10,0],[5,5]]
 * 
 * Answer: False
 * 
 * Explanation:
 * 
 * For a convex polygon, all interior angles must be less than or equal to 180 degrees. How to find this angle? Use
 * cross product
 * 
 * The key observation for convexity is that vector pi+1-pi always turns to the same direction to pi+2-pi formed by any
 * 3 sequentially adjacent vertices, i.e., cross product (pi+1-pi) x (pi+2-pi) does not change sign when traversing
 * sequentially along polygon vertices.
 * 
 * @author surya
 *
 */
public class ConvexPolygon {

	public boolean isConvex(List<List<Integer>> points) {
		boolean positive = false;
		boolean negative = false;

		for (int A = 0; A < points.size(); A++) {
			int B = (A + 1) % points.size(); // next point
			int C = (B + 1) % points.size(); // next point

			List<Integer> p1 = points.get(A);
			List<Integer> p2 = points.get(B);
			List<Integer> p3 = points.get(C);

			int crossProduct = crossProduct(p1.get(0), p1.get(1), p2.get(0), p2.get(1), p3.get(0), p3.get(1));
			// The vector product must always be positive or always negative
			if (crossProduct < 0) {
				negative = true;
			} else if (crossProduct > 0) {
				positive = true;
			}

			// The idea used here is the same as determining whether a sequence is monotonic
			if (positive && negative) {
				return false;
			}

		}

		return true;
	}

	int crossProduct(int Ax, int Ay, int Bx, int By, int Cx, int Cy) {
		int BAx = Bx - Ax;
		int BAy = By - Ay;
		int BCx = Bx - Cx;
		int BCy = By - Cy;

		return BAx * BCy - BAy * BCx;
	}
}
