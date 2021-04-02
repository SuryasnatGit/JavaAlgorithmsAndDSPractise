package com.companyprep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Find the k post offices located closest to you, given your location and a list of locations of all post offices
 * available. Locations are given in 2D coordinates in [X, Y], where X and Y are integers. Euclidean distance is applied
 * to find the distance between you and a post office. Assume your location is [m, n] and the location of a post office
 * is [p, q], the Euclidean distance between the office and you is SquareRoot((m - p) * (m - p) + (n - q) * (n - q)). K
 * is a positive integer much smaller than the given number of post offices. from aonecode.com
 * 
 * e.g. Input you: [0, 0] post_offices: [[-16, 5], [-1, 2], [4, 3], [10, -2], [0, 3], [-5, -9]] k = 3
 * 
 * Output from aonecode.com [[-1, 2], [0, 3], [4, 3]]
 * 
 */
public class KNearestPostOffices {

	public List<Location> kNearestPostOffices(List<Location> poLocations, int k, Location yourLocation) {
		PriorityQueue<Location> pq = new PriorityQueue<Location>(k, new LocationComparator());

		for (Location loc : poLocations) {
			int xDist = yourLocation.x - loc.x;
			int yDist = yourLocation.y - loc.y;
			loc.euclidDist = Math.sqrt(xDist * xDist + yDist * yDist);
			pq.add(loc);
		}

		List<Location> result = new ArrayList<Location>();
		while (!pq.isEmpty() && k-- > 0) {
			result.add(pq.poll());
		}

		return result;
	}

	class LocationComparator implements Comparator<Location> {

		@Override
		public int compare(Location o1, Location o2) {
			if (o1.euclidDist < o2.euclidDist) {
				return -1;
			} else if (o1.euclidDist > o2.euclidDist) {
				return 1;
			}
			return 0;
		}

	}

	public static void main(String[] args) {
		KNearestPostOffices kpo = new KNearestPostOffices();
		Location your = new Location(0, 0);

		List<Location> locs = Arrays.asList(new Location(-16, 5), new Location(-1, 2), new Location(4, 3),
				new Location(10, -2), new Location(0, 3), new Location(-5, -9));

		kpo.kNearestPostOffices(locs, 3, your).forEach(a -> System.out.println(a));

	}
}

class Location {
	int x;
	int y;
	double euclidDist;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Location [x=" + x + ", y=" + y + ", euclidDist=" + euclidDist + "]";
	}
}
