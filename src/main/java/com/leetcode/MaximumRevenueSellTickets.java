package com.leetcode;

import java.util.PriorityQueue;

/**
 * The railway ticket office has n ticket windows, and each window has a[i] tickets. The price of each ticket is equal
 * to the number of tickets left at that time. When there are already m tickets sold, how much money can the ticket
 * office make at most?
 *
 */
public class MaximumRevenueSellTickets {

	public int maxRevenue(int[] windows, int m) {
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

		for (int window : windows) {
			pq.offer(window);
		}

		int revenue = 0;
		while (m-- > 0) {
			int ticketPrice = pq.poll();
			revenue += ticketPrice;
			pq.offer(--ticketPrice);
		}

		return revenue;
	}

	public static void main(String[] args) {
		MaximumRevenueSellTickets max = new MaximumRevenueSellTickets();
		System.out.println(max.maxRevenue(new int[] { 5, 1, 7, 10, 11, 9 }, 4));
	}
}
