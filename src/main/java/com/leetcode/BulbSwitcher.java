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
