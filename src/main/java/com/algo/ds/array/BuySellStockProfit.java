package com.algo.ds.array;

/**
 * Date 03/04/2016
 * 
 * 
 *
 * Best time to buy and sell stocks. 1) Only 1 transaction is allowed 2)
 * Infinite number transactions are allowed
 *
 * Time complexity O(n) Space complexity O(1)
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * 
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum
 * profit.
 * 
 * Example 1: Input: [7, 1, 5, 3, 6, 4] Output: 5
 * 
 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger
 * than buying price) Example 2: Input: [7, 6, 4, 3, 1] Output: 0
 * 
 * In this case, no transaction is done, i.e. max profit = 0.
 */
public class BuySellStockProfit {

	public int oneProfit(int arr[]) {
		int minPrice = arr[0];
		int maxProfit = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] - minPrice > maxProfit) {
				maxProfit = arr[i] - minPrice;
			}
			if (arr[i] < minPrice) {
				minPrice = arr[i];
			}
		}
		return maxProfit;
	}

	public int allTimeProfit(int arr[]) {
		int profit = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i - 1] < arr[i]) {
				profit += arr[i] - arr[i - 1];
			}
		}
		return profit;
	}

	public static void main(String args[]) {
		int arr[] = { 7, 10, 15, 5, 11, 2, 7, 9, 3 };
		int arr1[] = { 6, 4, 1, 3, 5, 7, 3, 1, 3, 4, 7, 9, 2, 5, 6, 0, 1, 2 };
		BuySellStockProfit bss = new BuySellStockProfit();
		System.out.println(bss.oneProfit(arr));
		System.out.print(bss.allTimeProfit(arr1));

	}
}
