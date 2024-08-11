package com.algo.ds.hashtable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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
	 * starting point would never be on to side of a ticket. Once we find the starting point, we can simply traverse the
	 * given map to print itinerary in order.
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

	/**
	 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct
	 * the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin
	 * with JFK.
	 * 
	 * Note: If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical
	 * order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than
	 * ["JFK", "LGB"]. All airports are represented by three capital letters (IATA code). You may assume all tickets
	 * form at least one valid itinerary.
	 * 
	 * Example 1: tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]] Return ["JFK", "MUC",
	 * "LHR", "SFO", "SJC"].
	 * 
	 * Example 2: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]] Return
	 * ["JFK","ATL","JFK","SFO","ATL","SFO"].
	 * 
	 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
	 */

	// https://discuss.leetcode.com/topic/36383/share-my-solution
	Map<String, PriorityQueue<String>> flights = new HashMap<String, PriorityQueue<String>>(); // 从这个机场能到哪些机场，到达机场根据首字母排序
	LinkedList<String> res = new LinkedList<String>();

	public List<String> findItinerary(String[][] tickets) {
		for (String[] pair : tickets) {
			flights.putIfAbsent(pair[0], new PriorityQueue<String>());
			flights.get(pair[0]).add(pair[1]);
		}
		dfs("JFK");
		return res;
	}

	// Assumption: You may assume all tickets form at least one valid itinerary.
	// 如果没有solution，这个会return半个itinerary
	// 因为用了heap，这个solution会优先找到最优解
	void dfs(String departure) {
		// When to stop? we are polling from heap, so eventually the heap will be empty
		PriorityQueue<String> heap = flights.get(departure);
		while (heap != null && !heap.isEmpty()) { // why use while
			dfs(heap.poll()); // Since departure is fixed, let's try all the destinations
		}
		res.addFirst(departure);
	}

	/**
	 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=500584&highlight=snapchat My understanding:
	 * 给一堆飞行的schedule，打印出所有能到的城市来 input = [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]] output = ['JFK', 'NRT', 'JFK',
	 * 'KUL']
	 * 
	 * 
	 * input = [["JFK","SFO"],["JFK","ATL"],["ATL","NRT"],["ATL","SFO"]]
	 * 
	 * output = ['JFK', 'SFO', 'ATL', 'SFO', 'NRT']
	 * 
	 * 第一个输出了2遍JFK，因为NRT自己也能飞到JFK 第二个只输出了一遍JFK
	 */
	public List<String> findItinerary2(String[][] tickets) {
		for (String[] pair : tickets) {
			flights.putIfAbsent(pair[0], new PriorityQueue<String>());
			flights.get(pair[0]).add(pair[1]);
		}
		dfs2("JFK");
		return res;
	}

	void dfs2(String departure) {
		// When to stop? we are polling from heap, so eventually the heap will be empty
		PriorityQueue<String> heap = flights.get(departure);
		while (heap != null && !heap.isEmpty()) { // why use while
			dfs(heap.poll()); // Since departure is fixed, let's try all the destinations
		}
		res.addFirst(departure);
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
