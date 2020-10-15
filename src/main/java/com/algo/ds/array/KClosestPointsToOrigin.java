package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a list of points on the 2-D plane and an integer K. The task is to find K closest points to the origin and
 * print them.
 * 
 * Note: The distance between two points on a plane is the Euclidean distance.
 * 
 * Examples:
 * 
 * Input : point = [[3, 3], [5, -1], [-2, 4]], K = 2 Output : [[3, 3], [-2, 4]]
 * 
 * Input : point = [[1, 3], [-2, 2]], K = 1 Output : [[-2, 2]]
 * 
 * @author surya
 *
 */
public class KClosestPointsToOrigin {

	/**
	 * 1. Sort the points by distance using Euclidean distance formula. 2. Select first K points form the list 3. Print
	 * the points obtained in any order.
	 * 
	 * complexity - O(n log n)
	 * 
	 * @param array
	 */
	public void solution1_naive(List<Point> pointList, int k) {
		List<Point> result = new ArrayList<Point>();
		for (Point point : pointList) {
			double dist = Math.sqrt(point.x * point.x + point.y * point.y);
			point.dist = dist;
			result.add(point);
		}

		System.out.println(result);
		Collections.sort(result);
		System.out.println(result);

		for (int i = 0; i < k; i++) {
			System.out.println(result.get(i));
		}
	}

	public void solution2_optimal(List<Point> pointList, int k) {
		// using max heap
		PriorityQueue<Point> pq = new PriorityQueue<>((p1, p2) -> p1.dist > p2.dist ? -1 : 1);

		// compute the eucledian distance and add to a list.
		List<Point> result = new ArrayList<Point>();
		for (Point point : pointList) {
			double dist = Math.sqrt(point.x * point.x + point.y * point.y);
			point.dist = dist;
			result.add(point);
		}

		// insert first k points in max heap
		for (int i = 0; i < k; i++) {
			pq.add(result.get(i));
		}

		// go through the remaining points of the input array, if a point is closer to
		// the origin than the top point of the max-heap, remove the top point from
		// heap and add the point from the input array
		for (int i = k; i < result.size(); i++) {
			if (result.get(i).dist < pq.peek().dist) {
				pq.poll();
				pq.add(result.get(i));
			}
		}

		// the heap has k points closest to the origin, return them
		pq.forEach(p -> System.out.println(p));
	}

	public static void main(String[] args) {
		KClosestPointsToOrigin k = new KClosestPointsToOrigin();
		List<Point> list = new ArrayList<>();

		list.add(new Point(5, -1));
		list.add(new Point(3, 3));
		list.add(new Point(-2, 4));

		k.solution1_naive(list, 2);
		k.solution2_optimal(list, 2);
	}

}

class Point implements Comparable<Point> {
	int x;
	int y;
	double dist;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public double getDist() {
		return dist;
	}

	@Override
	public int compareTo(Point o) {
		return this.dist < o.dist ? -1 : 1;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}
