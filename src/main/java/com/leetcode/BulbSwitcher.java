package com.leetcode;

/**
 * Category : Medium
 *
 */
public class BulbSwitcher {

	/**
	 * There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb.
	 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the i-th
	 * round, you toggle every i bulb. For the n-th round, you only toggle the last bulb. Find how many bulbs are on
	 * after n rounds.
	 * 
	 * Example:
	 * 
	 * Input: 3
	 * 
	 * Output: 1
	 * 
	 * Explanation:
	 * 
	 * At first, the three bulbs are [off, off, off]. After first round, the three bulbs are [on, on, on]. After second
	 * round, the three bulbs are [on, off, on]. After third round, the three bulbs are [on, off, off].
	 * 
	 * So you should return 1, because there is only one bulb is on.
	 */
	public int bulbSwitch(int n) {
		int res = (int) Math.sqrt(n);
		return res;
	}

	/**
	 * There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m
	 * unknown operations towards buttons, you need to return how many different kinds of status of the n lights could
	 * be.
	 * 
	 * Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:
	 * 
	 * 1. Flip all the lights.<br/>
	 * 2. Flip lights with even numbers.<br/>
	 * 3. Flip lights with odd numbers.<br/>
	 * 4. Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
	 * 
	 * Example 1:
	 * 
	 * Input: n = 1, m = 1.
	 * 
	 * Output: 2
	 * 
	 * Explanation: Status can be: [on], [off]
	 * 
	 * Example 2:
	 * 
	 * Input: n = 2, m = 1.
	 * 
	 * Output: 3
	 * 
	 * Explanation: Status can be: [on, off], [off, on], [off, off]
	 * 
	 * Example 3:
	 * 
	 * Input: n = 3, m = 1.
	 * 
	 * Output: 4
	 * 
	 * Explanation: Status can be: [off, on, off], [on, off, on], [off, off, off], [off, on, on].
	 * 
	 * Note: n and m both fit in range [0, 1000].
	 * 
	 */
	public int flipLights(int n, int m) {

	}

	public static void main(String[] args) {
		BulbSwitcher bs = new BulbSwitcher();
		System.out.println(bs.bulbSwitch(2));
		System.out.println(bs.bulbSwitch(4));
		System.out.println(bs.bulbSwitch(6));
		System.out.println(bs.bulbSwitch(7));
		System.out.println(bs.bulbSwitch(9));
		System.out.println(bs.bulbSwitch(10));
	}
}
