package com.ctci.moderate;

/**
 * Given two straight line segments (represented as a start point and an end point)' compute the point of intersection,
 * if any.
 * 
 * @author surya
 *
 */
public class LinesIntersection {

}

class Line {

	double slope, yintercept;

	public Line(Point start, Point end) {
		double deltay = end.y - start.y;
		double deltax = end.x - start.x;
		slope = deltay / deltax;
		yintercept = end.y - slope * end.x;
	}
}

class Point {

	double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}