package com.algo.ds.array;

/**
 * http://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/.
 * 
 * Suppose there is a circle. There are n petrol pumps on that circle. You are given two sets of data. 1. The amount of
 * petrol that every petrol pump has. 2. Distance from that petrol pump to the next petrol pump.
 * 
 * Calculate the first point from where a truck will be able to complete the circle (The truck will stop at each petrol
 * pump and it has infinite capacity). Expected time complexity is O(n). Assume for 1 litre petrol, the truck can go 1
 * unit of distance.
 * 
 * For example, let there be 4 petrol pumps with amount of petrol and distance to next petrol pump value pairs as {4,
 * 6}, {6, 5}, {7, 3} and {4, 5}. The first point from where truck can make a circular tour is 2nd petrol pump. Output
 * should be start = 1 (index of 2nd petrol pump).
 * 
 */
public class GasStationCircle {

	/**
	 * Time - O(n), Space - O(1)
	 * 
	 * @param gasAvailable
	 * @param gasRequired
	 * @return
	 */
	public int startTour(int gasAvailable[], int gasRequired[]) {
		int start = -1;
		int end = 0;
		int currentGas = 0;
		boolean visitedOnce = false;
		while (start != end) {
			currentGas += gasAvailable[end] - gasRequired[end];
			if (start == -1) {
				start = end;
			}
			if (end == gasAvailable.length - 1 && visitedOnce == false) {
				visitedOnce = true;
			} else if (end == gasAvailable.length - 1 && visitedOnce == true) {
				return -1;
			}
			if (currentGas < 0) {
				start = -1;
				currentGas = 0;
			}
			end = (end + 1) % gasAvailable.length;
		}

		return end;
	}

	public static void main(String args[]) {
		GasStationCircle gsc = new GasStationCircle();
		int[] gasAvailable = { 4, 4, 6 };
		int[] gasRequired = { 5, 6, 1 };
		System.out.println(gsc.startTour(gasAvailable, gasRequired));
	}
}
