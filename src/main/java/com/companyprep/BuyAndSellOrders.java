package com.companyprep;

/**
 * Given a stream of incoming "buy" and "sell" orders (as lists of limit price, quantity, and side, like ["155", "3",
 * "buy"]), determine the total quantity (or number of "shares") executed.
 * 
 * A "buy" order can be executed if there is a corresponding "sell" order with a price that is less than or equal to the
 * price of the "buy" order. Similarly, a "sell" order can be executed if there is a corresponding "buy" order with a
 * price that is greater than or equal to the price of the "sell" order. It is possible that an order does not execute
 * immediately if it isn't paired to a counterparty. In that case, you should keep track of that order and execute it at
 * a later time when a pairing order is found. You should ensure that orders are filled immediately at the best possible
 * price. That is, an order should be executed when it is processed, if possible. Further, "buy" orders should execute
 * at the lowest possible price and "sell" orders at the highest possible price at the time the order is handled.
 * 
 * Note that orders can be partially executed.
 * 
 * --- Sample Input ---
 * 
 * orders = [ <br/>
 * ['150', '5', 'buy'], # Order A <br/>
 * ['190', '1', 'sell'], # Order B <br/>
 * ['200', '1', 'sell'], # Order C <br/>
 * ['100', '9', 'buy'], # Order D <br/>
 * ['140', '8', 'sell'], # Order E <br/>
 * ['210', '4', 'buy'], # Order F <br/>
 * ]
 * 
 * Sample Output 9
 * 
 * TODO: need analysis.
 * 
 * Ref:: https://www.geeksforgeeks.org/stock-buy-sell/
 *
 */
public class BuyAndSellOrders {

}
