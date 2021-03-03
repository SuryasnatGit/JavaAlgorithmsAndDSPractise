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
 * 凸多边形，所有内角必须都小于等于180度，如何求这个角度，利用cross product
 * 
 * The key observation for convexity is that vector pi+1-pi always turns to the same direction to pi+2-pi formed by any
 * 3 sequentially adjacent vertices, i.e., cross product (pi+1-pi) x (pi+2-pi) does not change sign when traversing
 * sequentially along polygon vertices.
 * 
 * @author surya
 *
 */
public class ConvexPolygon {

	// http://www.cnblogs.com/EdwardLiu/p/6210805.html
	public boolean isConvex(List<List<Integer>> points) {
		boolean positive = false;
		boolean negative = false;

		for (int A = 0; A < points.size(); A++) {
			int B = (A + 1) % points.size(); // 下一个点
			int C = (B + 1) % points.size(); // 下下个点

			List<Integer> p1 = points.get(A);
			List<Integer> p2 = points.get(B);
			List<Integer> p3 = points.get(C);

			int crossProduct = crossProduct(p1.get(0), p1.get(1), p2.get(0), p2.get(1), p3.get(0), p3.get(1));
			// 向量积必须一直正或者一直负
			if (crossProduct < 0) {
				negative = true;
			} else if (crossProduct > 0) {
				positive = true;
			}

			if (positive && negative) { // 这里使用的思想跟判断数列是否单调是一样的
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
