package com.algo.ds.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of tickets, find itinerary in order using the given list.
 * 
 * Example:
 * 
 * Input: "Chennai" -> "Banglore" "Bombay" -> "Delhi" "Goa" -> "Chennai" "Delhi" -> "Goa"
 * 
 * Output: Bombay->Delhi, Delhi->Goa, Goa->Chennai, Chennai->Banglore, It may be assumed that the input list of tickets
 * is not cyclic and there is one ticket from every city except final destination.
 * 
 * @author surya
 *
 */
public class ItineraryFromListOfTickets {

	// solution 1 - build a graph and do topological sorting of the graph. time complexity - O(n)

	/**
	 * Solution 2 - We can also use hashing to avoid building a graph. The idea is to first find the starting point. A
	 * starting point would never be on ‘to’ side of a ticket. Once we find the starting point, we can simply traverse
	 * the given map to print itinerary in order.
	 * 
	 * All of the above steps require O(n) time so overall time complexity is O(n).
	 * 
	 * @param dataSet
	 *            key is from and value is to
	 */
	public void printItinerary(Map<String, String> dataSet) {
		// make reverse data set map. key is to and value is from
		Map<String, String> reverse = new HashMap<>();
		for (Map.Entry<String, String> entry : dataSet.entrySet()) {
			reverse.put(entry.getValue(), entry.getKey());
		}

		String start = null;
		for (Map.Entry<String, String> entry : dataSet.entrySet()) {
			if (!reverse.containsKey(entry.getKey())) {
				// got the starting point
				start = entry.getKey();
				break;
			}
		}

		// if starting point is not found something wrong with input
		if (start == null) {
			System.out.println("input wrong");
			return;
		}

		String to = dataSet.get(start);
		while (to != null) {
			System.out.println(start + " -> " + to);
			start = to;
			to = dataSet.get(start);
		}
	}

	public static void main(String[] args) {
		ItineraryFromListOfTickets i = new ItineraryFromListOfTickets();

		Map<String, String> dataSet = new HashMap<String, String>();
		dataSet.put("Chennai", "Banglore");
		dataSet.put("Bombay", "Delhi");
		dataSet.put("Goa", "Chennai");
		dataSet.put("Delhi", "Goa");

		i.printItinerary(dataSet);
	}

}
