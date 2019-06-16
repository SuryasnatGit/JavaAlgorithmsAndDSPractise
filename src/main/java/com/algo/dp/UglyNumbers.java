package com.algo.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Ugly numbers are numbers whose only prime factors are 2, 3 or 5. The sequence
 * 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, … shows the first 11 ugly numbers. By
 * convention, 1 is included.
 * 
 * Given a number n, the task is to find n’th Ugly number.
 * 
 * Examples:
 * 
 * Input : n = 7 Output : 8
 * 
 * Input : n = 10 Output : 12
 * 
 * Input : n = 15 Output : 24
 * 
 * Input : n = 150 Output : 5832
 * 
 * @author M_402201
 *
 */
public class UglyNumbers {

	/**
	 * Loop for all positive integers until ugly number count is smaller than n, if
	 * an integer is ugly than increment ugly number count. This method is not time
	 * efficient as it checks for all integers until ugly number count becomes n,
	 * but space complexity of this method is O(1)
	 * 
	 * time - O(n) space - O(1)
	 * 
	 * @param n
	 * @return
	 */
	public int findNthUglyNumber_simple(int n) {
		int nthUglyNum = 1;
		int uglyNumCount = 1;

		while (n > uglyNumCount) {
			nthUglyNum++;
			if (isUglyNum(nthUglyNum)) {
				uglyNumCount++;
			}
		}

		return nthUglyNum;
	}

	private boolean isUglyNum(int nthUglyNum) {
		nthUglyNum = greatestDivisible(nthUglyNum, 2);
		nthUglyNum = greatestDivisible(nthUglyNum, 3);
		nthUglyNum = greatestDivisible(nthUglyNum, 5);

		return nthUglyNum == 1 ? true : false;
	}

	/**
	 * continue dividing until divisible by divisor
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	private int greatestDivisible(int dividend, int divisor) {
		while (dividend % divisor == 0)
			dividend /= divisor;

		return dividend;
	}

	/**
	 * time - O(n) space - O(n)
	 * 
	 * @param n
	 * @return
	 */
	public int findNthUglyNumber_DP(int n) {
		// array to contain ugly numbers
		int[] uglyNums = new int[n];

		int index2 = 0, index3 = 0, index5 = 0; // indexes of 2,3,5 to go through the ugly num array
		int nextUglyNum = 1;
		int nextMultipleOf2 = 2, nextMultipleOf3 = 3, nextMultipleOf5 = 5;

		// first ugly num is always 1
		uglyNums[0] = 1;

		for (int i = 1; i < n; i++) {
			nextUglyNum = Math.min(nextMultipleOf2, Math.min(nextMultipleOf3, nextMultipleOf5));
			// assign to ugly num arr
			uglyNums[i] = nextUglyNum;
			if (nextUglyNum == nextMultipleOf2) {
				index2++;
				nextMultipleOf2 = uglyNums[index2] * 2;
			}
			if (nextUglyNum == nextMultipleOf3) {
				index3++;
				nextMultipleOf3 = uglyNums[index3] * 3;
			}
			if (nextUglyNum == nextMultipleOf5) {
				index5++;
				nextMultipleOf5 = uglyNums[index5] * 5;
			}
		}

		return nextUglyNum;
	}

	/**
	 * Super ugly numbers are positive numbers whose all prime factors are in the
	 * given prime list. Given a number n, the task is to find n’th Super Ugly
	 * number.
	 * 
	 * It may be assumed that given set of primes is sorted. Also, first Super Ugly
	 * number is 1 by convention.
	 * 
	 * Examples:
	 * 
	 * Input : primes[] = [2, 5] n = 5 Output : 8 Super Ugly numbers with given
	 * prime factors are 1, 2, 4, 5, 8, ... Fifth Super Ugly number is 8
	 * 
	 * Input : primes[] = [2, 3, 5] n = 50 Output : 243
	 * 
	 * Input : primes[] = [3, 5, 7, 11, 13] n = 9 Output: 21
	 * 
	 * @return
	 */
	public int superUglyNumbers(int n, int[] primes) {

		int k = primes.length;
		int[] nextMultiple = new int[k];
		System.arraycopy(primes, 0, nextMultiple, 0, k);

		int[] pointer = new int[k];

		int uglyIndex = 1;
		int[] uglyNums = new int[n];
		uglyNums[0] = 1;

		while (uglyIndex < n) {
			int nextUgly = findMin(nextMultiple);
			uglyNums[uglyIndex++] = nextUgly;

			for (int i = 0; i < k; i++) {
				if (nextUgly == nextMultiple[i])
					pointer[i]++;
			}

			for (int i = 0; i < k; i++) {
				nextMultiple[i] = primes[i] * uglyNums[pointer[i]];
			}
		}

		return uglyNums[n - 1];
	}

	private int findMin(int[] nextMultiple) {
		int min = nextMultiple[0];
		for (int i = 1; i < nextMultiple.length; i++) {
			min = Math.min(min, nextMultiple[i]);
		}
		return min;
	}

	/**
	 * Given an integer array arr[], the task is to sort only those elements which
	 * are ugly numbers at their relative positions in the array (positions of other
	 * elements must not be affected). Ugly numbers are numbers whose only prime
	 * factors are 2, 3 or 5. The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …..
	 * shows first few ugly numbers. By convention, 1 is included.
	 * 
	 * Examples:
	 * 
	 * Input: arr[] = {13, 9, 11, 3, 2} Output: 13 2 11 3 9 9, 3 and 2 are the only
	 * ugly numbers in the given array.
	 * 
	 * Input: arr[] = {1, 2, 3, 7, 12, 10} Output: 1 2 3 7 10 12
	 * 
	 * 
	 * @param n
	 */
	public void sortUglyNumbers(int[] n) {
		// List to contain ugly nums to sort
		List<Integer> uglyNums = new ArrayList<Integer>();

		int l = n.length;
		for (int i = 0; i < l; i++) {
			if (isUglyNum(n[i])) {
				// add to list
				uglyNums.add(n[i]);
				// set the array n position to -1
				n[i] = -1;
			}
		}

		// sort the ugly list
		Collections.sort(uglyNums);

		int j = 0;
		for (int i = 0; i < l; i++) {
			if (n[i] == -1) { // ugly num found
				System.out.print(uglyNums.get(j++) + " ");
			} else {
				System.out.print(n[i] + " ");
			}
		}
		System.out.println();
	}

	/**
	 * Given an array arr[] of N elements (0 ≤ arr[i] ≤ 1000). The task is to find
	 * the maximum length of the sub-array that contains only ugly numbers. Ugly
	 * numbers are numbers whose only prime factors are 2, 3 or 5. The sequence 1,
	 * 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, ….. shows first few ugly numbers. By
	 * convention, 1 is included.
	 * 
	 * Examples:
	 * 
	 * Input: arr[] = {1, 2, 7, 9, 120, 810, 374} Output: 3 Longest possible
	 * sub-array of ugly number sis {9, 120, 810}
	 * 
	 * Input: arr[] = {109, 480, 320, 142, 121, 1} Output: 2
	 * 
	 * @param n
	 * @return
	 */
	public int maxLengthSubArrayWithUglyNumbers(int[] n) {

		Set<Integer> uglyNumSet = new HashSet<Integer>();
		uglyNumSet.add(1); // by convention

		// add all ugly numbers between 1 to 1000 in the set
		for (int i = 2; i <= 1000; i++) {
			if (isUglyNum(i))
				uglyNumSet.add(i);
		}

		int currentMax = 0;
		int maxSoFar = 0;
		for (int i : n) {
			if (uglyNumSet.contains(i)) {
				currentMax++;
				maxSoFar = Math.max(currentMax, maxSoFar);
			} else {
				currentMax = 0;// reset
			}
		}
		return maxSoFar;
	}

	/**
	 * Given two arrays arr1[] and arr2[] of sizes N and M where 0 ≤ arr1[i],
	 * arr2[i] ≤ 1000 for all valid i, the task is to take one element from first
	 * array and one element from second array such that both of them are ugly
	 * numbers. We call it a pair (a, b). You have to find the count of all such
	 * distinct pairs. Note that (a, b) and (b, a) are not distinct. Ugly numbers
	 * are numbers whose only prime factors are 2, 3 or 5. The sequence 1, 2, 3, 4,
	 * 5, 6, 8, 9, 10, 12, 15, ….. shows first few ugly numbers. By convention, 1 is
	 * included.
	 * 
	 * Examples:
	 * 
	 * Input: arr1[] = {7, 2, 3, 14}, arr2[] = {2, 11, 10} Output: 4 All distinct
	 * pairs are (2, 2), (2, 10), (3, 2) and (3, 10)
	 * 
	 * Input: arr1[] = {1, 2, 3}, arr2[] = {1, 1} Output: 3 All distinct pairs are
	 * (1, 1), (1, 2) and (1, 3)
	 * 
	 * 
	 * @return
	 */
	public int totalDistinctPairOfUglyNumbers(int[] arr1, int[] arr2) {
		// get all ugly numbers between 1 and 1000
		Set<Integer> uglyNumSet = new HashSet<Integer>();
		for (int i = 1; i <= 1000; i++) {
			if (isUglyNum(i))
				uglyNumSet.add(i);
		}

		Set<Integer> uglyList1 = new HashSet<Integer>();
		for (int i : arr1) {
			if (uglyNumSet.contains(i))
				uglyList1.add(i);
		}

		Set<Integer> uglyList2 = new HashSet<Integer>();
		for (int i : arr2) {
			if (uglyNumSet.contains(i))
				uglyList2.add(i);
		}

		Set<Pair> set = new HashSet<UglyNumbers.Pair>();
		for (int i : uglyList1) {
			for (int j : uglyList2) {
				Pair p = new Pair(i, j);
				set.add(p);
			}
		}

		System.out.println(set);
		return set.size();
	}

	private class Pair {
		int i1;
		int i2;

		public Pair(int i1, int i2) {
			this.i1 = i1;
			this.i2 = i2;
		}

		@Override
		public String toString() {
			return "Pair [i1=" + i1 + ", i2=" + i2 + "]";
		}

		@Override
		public int hashCode() {
			return Math.abs(i1 - i2);
		}

		@Override
		public boolean equals(Object obj) {
			Pair o2 = (Pair) obj;
			if (this.i1 == o2.i1 && this.i2 == o2.i2)
				return true;
			if (this.i1 == o2.i2 && this.i2 == o2.i1)
				return true;
			return false;
		}
	}

	public static void main(String[] args) {
		UglyNumbers un = new UglyNumbers();
//		System.out.println(un.findNthUglyNumber_simple(50));
//		System.out.println(un.findNthUglyNumber_DP(8));
//		System.out.println(un.superUglyNumbers(50, new int[] { 2, 3, 5 }));
//		un.sortUglyNumbers(new int[] { 1, 2, 3, 7, 12, 10 });
//		System.out.println(un.maxLengthSubArrayWithUglyNumbers(new int[] { 1, 0, 6, 7, 320, 800, 100, 648 }));
		System.out.println(un.totalDistinctPairOfUglyNumbers(new int[] { 3, 7, 1 }, new int[] { 5, 1, 10, 4 }));
	}

}
