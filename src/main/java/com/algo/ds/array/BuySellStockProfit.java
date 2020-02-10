package com.algo.ds.array;

/**
 * https://www.techiedelight.com/maximum-profit-earned-buying-and-selling-shares/
 * 
 * There can be several variations to the above problem.
 */
public class BuySellStockProfit {

	/**
	 * If allowed to stock only once then find max diff between 2 elements in the array where smaller element appears
	 * before the larger element.
	 * 
	 * https://www.techiedelight.com/find-maximum-difference-between-two-elements-array/
	 * 
	 * T - O(n) S - O(1)
	 */
	public int maxProfitStocksOneTransaction(int arr[]) {
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

	/**
	 * If allowed to stock shares at most twice.
	 * 
	 * https://www.techiedelight.com/find-maximum-profit-earned-from-at-most-two-stock-transactions/
	 * 
	 * T - O(n) S - O(n)
	 */
	public int maxProfitStocksTwoTransaction(int[] price) {
		int n = price.length;

		// create an auxiliary array of size n
		int[] profit = new int[n];

		// initialize last element of the auxiliary array to 0
		profit[n - 1] = 0;

		// to keep track of maximum stock price on the right of current stock price
		int max_so_far = price[n - 1];

		// traverse the array from right to left
		for (int i = n - 2; i >= 0; i--) {
			// update profit[i] to the maximum profit earned by a single stock
			// transaction from day i to day n-1
			profit[i] = Math.max(profit[i + 1], max_so_far - price[i]);

			// update maximum stock price seen so far
			max_so_far = Math.max(max_so_far, price[i]);
		}

		// to keep track of minimum stock price to the left of current stock price
		int min_so_far = price[0];

		// traverse the array from left to right
		for (int i = 1; i < n; i++) {
			// update profit[i] by taking the maximum of:
			// 1. profit[i-1] which represents the maximum profit calculated so far
			// 2. total profit of obtained by closing the first transaction on day i
			// and performing another transaction from day i to day n-1.

			profit[i] = Math.max(profit[i - 1], (price[i] - min_so_far) + profit[i]);

			// update minimum stock price seen so far
			min_so_far = Math.min(min_so_far, price[i]);
		}

		// the last element of profit[] stores the result
		return profit[n - 1];
	}

	/**
	 * https://www.techiedelight.com/find-maximum-profit-earned-at-most-k-stock-transactions/
	 * 
	 * T - O(n^2 * k) S - O(nk)
	 */
	public int maxProfitStocksKTransaction(int[] price, int k) {
		// get number of days n
		int n = price.length;

		// profit[i][j] stores the maximum profit gained by doing
		// at most i transactions till j'th day
		int[][] profit = new int[k + 1][n];

		// fill profit[][] matrix in bottom-up fashion
		for (int i = 0; i <= k; i++) {
			for (int j = 0; j < n; j++) {
				// profit is 0 when:
				// i = 0 i.e. for 0'th day
				// j = 0 i.e. no transaction is being performed

				if (i == 0 || j == 0) {
					profit[i][j] = 0;
				} else {
					int max_so_far = 0;
					for (int x = 0; x < j; x++) {
						int curr_price = price[j] - price[x] + profit[i - 1][x];
						if (max_so_far < curr_price) {
							max_so_far = curr_price;
						}
					}

					profit[i][j] = Math.max(profit[i][j - 1], max_so_far);
				}
			}
		}

		return profit[k][n - 1];
	}

	/**
	 * T - O(nk) S - O(nk)
	 */
	public int maxProfitStocksKTransactionOptimized(int[] price, int k) {
		// get number of days n
		int n = price.length;

		// profit[i][j] stores the maximum profit gained by doing
		// at most i transactions till j'th day
		int[][] profit = new int[k + 1][n + 1];

		// fill profit[][] matrix in bottom-up fashion
		for (int i = 0; i <= k; i++) {
			// initialize prev diff to minus infinity
			int prev_diff = Integer.MIN_VALUE;

			for (int j = 0; j < n; j++) {
				// profit is 0 when:
				// i = 0 i.e. for 0'th day
				// j = 0 i.e. no transaction is being performed
				if (i == 0 || j == 0) {
					profit[i][j] = 0;
				} else {
					prev_diff = Math.max(prev_diff, profit[i - 1][j - 1] - price[j - 1]);
					profit[i][j] = Math.max(profit[i][j - 1], price[j] + prev_diff);
				}
			}
		}

		return profit[k][n - 1];
	}

	/**
	 * https://www.techiedelight.com/maximum-profit-earned-buying-and-selling-shares/
	 * 
	 * T - O(n) S - O(1)
	 */
	public int maxProfitStocksAnyTransaction(int[] price) {
		// store maximum profit gained
		int profit = 0;

		// initialize local minimum to first element's index
		int j = 0;

		// start from second element
		for (int i = 1; i < price.length; i++) {
			// update local minimum if decreasing sequence is found
			if (price[i - 1] > price[i]) {
				j = i;
			}

			// sell shares if current element is peak
			// i.e. (previous <= current > next)
			if (price[i - 1] <= price[i] && (i + 1 == price.length || price[i] > price[i + 1])) {
				profit += (price[i] - price[j]);
				System.out.printf("Buy on day %d and sell on day %d\n", j + 1, i + 1);
			}
		}

		return profit;
	}

	/**
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
	 * 
	 */
	public int maxProfitStocksWithTransactionFee(int[] prices, int fee) {
		int cash = 0, hold = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			cash = Math.max(cash, hold + prices[i] - fee);
			hold = Math.max(hold, cash - prices[i]);
		}
		return cash;
	}

	/**
	 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
	 * 
	 */
	public int maxProfitStocksWithCooldown(int[] prices) {
		if (prices.length == 0)
			return 0;
		int hold = -prices[0], cash = 0, cool = 0;
		for (int i = 1; i < prices.length; i++) {
			int hold2 = Math.max(hold, cool - prices[i]);
			int cash2 = Math.max(cash, hold + prices[i]);
			cool = cash;
			hold = hold2;
			cash = cash2;
		}
		return cash;
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
		BuySellStockProfit bss = new BuySellStockProfit();

		System.out.println(bss.maxProfitStocksOneTransaction(new int[] { 2, 7, 9, 5, 1, 3, 5 }));

		System.out.println(bss.maxProfitStocksTwoTransaction(new int[] { 2, 7, 9, 5, 1, 3, 5 }));

		System.out.println(bss.maxProfitStocksKTransaction(new int[] { 2, 7, 9, 5, 1, 3, 6 }, 3));

		System.out.println(bss.maxProfitStocksKTransactionOptimized(new int[] { 2, 7, 9, 5, 1, 3, 6 }, 3));

		System.out.println(bss.maxProfitStocksAnyTransaction(new int[] { 2, 7, 9, 5, 1, 3, 6 }));

		System.out.println(bss.maxProfitStocksWithTransactionFee(new int[] { 1, 3, 2, 8, 4, 9 }, 2));

		System.out.println(bss.maxProfitStocksWithCooldown(new int[] { 2, 7, 9, 5, 1, 3, 5 }));

	}
}
