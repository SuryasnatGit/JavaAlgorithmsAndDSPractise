package com.leetcode;

/**
 * You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
 * 
 * For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine to one
 * of its adjacent washing machines at the same time .
 * 
 * Given an integer array representing the number of dresses in each washing machine from left to right on the line, you
 * should find the minimum number of moves to make all the washing machines have the same number of dresses. If it is
 * not possible to do it, return -1.
 * 
 * Example1
 * 
 * Input: [1,0,5]
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * 1st move: 1 0 <-- 5 => 1 1 4<br/>
 * 2nd move: 1 <-- 1 <-- 4 => 2 1 3<br/>
 * 3rd move: 2 1 <-- 3 => 2 2 2<br/>
 * 
 * Example2
 * 
 * Input: [0,3,0]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * 1st move: 0 <-- 3 0 => 1 2 0<br/>
 * 2nd move: 1 2 --> 0 => 1 1 1<br/>
 * 
 * Example3
 * 
 * Input: [0,2,0]
 * 
 * Output: -1
 * 
 * Category : Medium
 * 
 */
public class SuperWashingMachines {

	// https://discuss.leetcode.com/topic/79938/super-short-easy-java-o-n-solution
	public int findMinMoves(int[] machines) {
		int sum = 0;
		int n = machines.length;

		for (int m : machines) {
			sum += m;
		}

		if (sum % n != 0) {
			return -1;
		}

		int target = sum / n;
		int res = 0;
		int transfer = 0;

		for (int load : machines) {
			transfer += load - target;
			res = Math.max(res, Math.max(Math.abs(transfer), load - target));
		}

		return res;
	}
}
