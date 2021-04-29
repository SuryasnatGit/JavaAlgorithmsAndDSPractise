package com.companyprep;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A shopkeeper has a sale to complete and has arranged the items being sold in a list. Starting from the left, the shop
 * keeper rings up each item at its full price less the price of the first lower or equally priced item to its right. If
 * there is no item to the right that costs less than or equal to the current item's price the current item is sold at
 * full price.
 * 
 * For example, assume there are items priced [2, 3, 1, 2, 4, 2]. The first and second items would each be discounted by
 * 1 unit, the first equal or lower price to the right. The item priced 1 unit would sell at a full price. The next
 * item, at 2 units, would be discounted 2 units as would the 4 unit item. The sixth and final item must be purchased at
 * full price. The total cost is 1+2+1+0+2+2 = 8 units.
 * 
 * Print total cost of all items on the first line. On the second line print a space separated list of integers
 * representing the indexes of the non- discounted items in ascending index order.
 * 
 * 1 <= size(prices) <= 100000
 * 
 * 1 <= prices <= 1000000
 * 
 * Output:
 * 
 * Input 2: [5,1,3,4,6,2]
 * 
 * Output:
 * 
 * 14
 * 
 * 1 5
 * 
 * Input 3: [1,3,3,2,5]
 * 
 * Output:
 * 
 * 9
 * 
 * 0 3 4
 * 
 * Category : Medium
 *
 */
public class ShopkeeperSale {

	public void printTotalCost(int[] prices) {
		int total = 0;
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < prices.length; i++) {
			while (!s.isEmpty() && prices[s.peek()] >= prices[i]) {
				total += prices[s.pop()] - prices[i];
			}
			s.push(i);
		}
		List<Integer> res = new ArrayList<>();
		while (!s.isEmpty()) {
			int idx = s.pop();
			total += prices[idx];
			res.add(0, idx);
		}
		System.out.println(total);
		System.out.println(res);
	}

	public static void main(String[] args) {
		ShopkeeperSale ss = new ShopkeeperSale();
		ss.printTotalCost(new int[] { 2, 3, 1, 2, 4, 2 });
	}
}
