package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * 
 * Note:
 * 
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * 
 * Example 1:
 * 
 * Input: [2,2,1] Output: 1 Example 2:
 * 
 * Input: [4,1,2,1,2] Output: 4
 *
 */
public class SingleNumber {

	// T - O(n) S - O(n)
	public int singleNumber(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (set.contains(num)) {
				set.remove(num);
			} else {
				set.add(num);
			}
		}
		return set.iterator().next();
	}

	/**
	 * T - O(n) S - O(1)
	 * 
	 * Concept
	 * 
	 * If we take XOR of zero and some bit, it will return that bit. a XOR 0 = a
	 * 
	 * If we take XOR of two same bits, it will return 0. a XOR a = 0
	 * 
	 * a XOR b XOR a = (a XOR a) XOR b = 0 XOR b = b
	 * 
	 * So we can XOR all bits together to find the unique number.
	 * 
	 * @return
	 */
	public int singleNumber1(int[] nums) {
		int res = 0;
		for (int num : nums) {
			res ^= num; // bitwise XOR operation
		}
		return res;
	}

	public static void main(String[] args) {
		SingleNumber sn = new SingleNumber();
		System.out.println(sn.singleNumber(new int[] { 2, 2, 1 }));
		System.out.println(sn.singleNumber(new int[] { 4, 1, 2, 1, 2 }));
		System.out.println();
		System.out.println(sn.singleNumber1(new int[] { 2, 2, 1 }));
		System.out.println(sn.singleNumber1(new int[] { 4, 1, 2, 1, 2 }));
	}
}
