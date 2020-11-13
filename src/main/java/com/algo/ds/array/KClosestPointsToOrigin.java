package com.algo.ds.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	 * Solution 1: T - O(N log K)
	 */
	public void findKClosestPointsPQ(List<Point> pointList, int k) {
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

	/**
	 * Solution 2 : Another O(N) approach is using bucket sort
	 * 
	 */
	public List<Point> findKClosestPointsBucketSort(Point p0, int k, Point[] points) {
		Map<Point, Integer> map = new HashMap<Point, Integer>();

		int longest = 0;
		for (Point p : points) {
			map.put(p, getDistance(p, p0));
			longest = Math.max(longest, map.get(p));
		}

		List<Point>[] bucket = new List[longest + 1];

		for (Map.Entry<Point, Integer> entry : map.entrySet()) {
			Point p = entry.getKey();
			int distance = entry.getValue();

			if (bucket[distance] == null) {
				bucket[distance] = new ArrayList<Point>();
			}

			bucket[distance].add(p);
		}

		List<Point> res = new ArrayList<Point>();
		for (int i = 0; i < bucket.length && res.size() < k; i++) {
			if (bucket[i] != null) {
				res.addAll(bucket[i]); // It may exceed K. To output all the elements with frequency greater than or
										// equal to top k, or just take top k
			}
		}

		return res;
	}

	/**
	 * Solution 3 : Quickselect uses the same overall approach as quicksort, choosing one element as a pivot and
	 * partitioning the data in two based on the pivot, accordingly as less than or greater than the pivot. However,
	 * instead of recursing into both sides, as in quicksort, quickselect only recurses into one side – the side with
	 * the element it is searching for. This reduces the average complexity from O(n log n) to O(n), with a worst case
	 * of O(n ^ 2). Time complexity of quick sort, worst case is O(n ^ 2). Merge sort worst case is O(n log n)
	 */
	public List<Point> findKClosestPointsQuickSelect(Point[] points, Point p, int k) {
		quickSelect(points, p, k);

		List<Point> res = new ArrayList<Point>();
		for (int i = 0; i < k; i++) {
			res.add(points[i]);
		}

		return res;
	}

	private void quickSelect(Point[] points, Point p, int k) {
		int left = 0, right = points.length - 1;

		while (left < right) {
			int pos = partition(points, p, left, right);

			if (pos == k) { // When it is k, all the ones on the left are smaller than points[k]
				return;
			} else if (pos < k) {
				left = pos + 1;
			} else {
				right = pos - 1;
			}
		}
	}

	private int partition(Point[] points, Point p, int left, int right) {
		int pivot = left;

		while (left < right) {
			while (left < right && getDistance(points[left], p) <= getDistance(points[pivot], p)) {
				left++;
			}

			while (left < right && getDistance(points[right], p) > getDistance(points[pivot], p)) {
				right--;
			}

			swap(points, left, right);
		}

		if (getDistance(points[left], p) <= getDistance(points[pivot], p)) {
			swap(points, left, pivot);
			return left;
		} else {
			swap(points, pivot, left - 1);
			return left - 1;
		}
	}

	private void swap(Point[] points, int left, int right) {
		Point tmp = points[left];
		points[left] = points[right];
		points[right] = tmp;
	}

	private int getDistance(Point p1, Point p2) {
		return (int) (Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

	public static void main(String[] args) {
		KClosestPointsToOrigin k = new KClosestPointsToOrigin();
		List<Point> list = new ArrayList<>();

		list.add(new Point(5, -1));
		list.add(new Point(3, 3));
		list.add(new Point(-2, 4));

		k.findKClosestPointsPQ(list, 2);
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
