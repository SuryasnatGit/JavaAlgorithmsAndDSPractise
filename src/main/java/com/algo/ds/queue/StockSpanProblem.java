package com.algo.ds.queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * http://www.geeksforgeeks.org/the-stock-span-problem Question: The stock span problem is a
 * financial problem where we have a series of n daily price quotes for a stock and we need to
 * calculate span of stock's price for all n days. The span Si of the stock's price on a given day i
 * is defined as the maximum number of consecutive days just before the given day, for which the
 * price of the stock on the current day is less than or equal to its price on the given day. For
 * example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, then the span
 * values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
 */
public class StockSpanProblem {

	/**
	 * Traverse the input price array. For every element being visited, traverse elements on left of it
	 * and increment the span value of it while elements on the left side are smaller..
	 * 
	 * Complexity - O(n^2)
	 * 
	 * @param prices
	 * @return
	 */
	public int[] stockSpan_bruteForce(int[] prices) {
		int l = prices.length;
		int[] res = new int[l];
		res[0] = 1; // first element span is always 1
		for (int i = 1; i < l; i++) { // traverse through rest of array. O(n)
			res[i] = 1; // initialize span value
			for (int j = i - 1; j >= 0 && prices[i] > prices[j]; j--) { // O(n)
				res[i]++;
			}
		}
		return res;
	}

	/**
	 * We see that S[i] on day i can be easily computed if we know the closest day preceding i, such
	 * that the price is greater than on that day than the price on day i. If such a day exists, let's
	 * call it h(i), otherwise, we define h(i) = -1. The span is now computed as S[i] = i - h(i). See
	 * the following diagram.
	 * 
	 * time complexity - O(n). Space complexity - O(n)
	 * 
	 * @param prices
	 * @return
	 */
	public int[] stockSpan(int[] prices) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] stockSpan = new int[prices.length];
        stockSpan[0] = 1;
        stack.offerFirst(0);
		for (int i = 1; i < prices.length; i++) { // O(n)
            while (!stack.isEmpty() && prices[stack.peekFirst()] < prices[i]) {
				stack.pollFirst(); // O(1)
            }
            if (stack.isEmpty()) {
                stockSpan[i] = i + 1;
            } else {
				stockSpan[i] = i - stack.peekFirst(); // O(1)
            }
			stack.offerFirst(i); // O(1)
        }
        return stockSpan;
    }

    public static void main(String[] args) {
		StockSpanProblem stock = new StockSpanProblem();
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
		int[] result = stock.stockSpan(prices);
		System.out.println(Arrays.toString(result));
		System.out.println(Arrays.toString(stock.stockSpan_bruteForce(prices)));
    }
}
