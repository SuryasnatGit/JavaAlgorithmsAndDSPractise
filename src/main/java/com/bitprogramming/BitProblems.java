package com.bitprogramming;

public class BitProblems {

	public static void main(String[] args) {
		BitProblems bp = new BitProblems();
		// System.out.println(bp.isPowerOf2(8));
		// System.out.println(bp.isPowerOf2(19));
		// System.out.println(bp.isPowerOf2(40));
		// System.out.println(bp.isPowerOf2(256));
		// System.out.println(bp.isPowerOf2_bit(10));
		// System.out.println(bp.isPowerOf2_bit(32));
		// System.out.println(bp.countOnesInBinaryForm(24));
		// System.out.println(bp.bitSetInNum(20, 4));
		// bp.numberOfSubsetsOfASet("train".toCharArray());
		// bp.largestPower(150);
		int[] arr = new int[] { 1, 3, 5 };
		System.out.println(bp.sumOfBitDiffAmongAllPairs_simple(arr));
		System.out.println(bp.sumOfBitDiffAmongAllPairs_efficient(arr));
	}

	public void flipAllBits(int n) {
		System.out.println(Integer.toBinaryString(n));
		// if n is a 32 bit integer
		n = n ^ ((1 << 32) - 1);
		System.out.println(Integer.toBinaryString(n));

	}

	/**
	 * 1. Left shift given number 1 by k-1 to create a number that has only set bit as k-th bit.<br/>
	 * 2. Return bitwise XOR of temp and n. Since temp has only k-th bit set, doing XOR would toggle
	 * only this bit
	 * 
	 * @param num
	 * @param k
	 * @return
	 */
	public int toggleKthBitNumber(int num, int k) {
		int temp = 1 << (k - 1);
		return num ^ temp;
	}

	/**
	 * check if n is a power of 2. complexity - O(log N)
	 * 
	 * @param n
	 * @return
	 */
	public boolean isPowerOf2(int n) {
		if (n == 0)
			return false;
		else {
			while (n % 2 == 0)
				n /= 2;
			return (n == 1);
		}
	}

	/**
	 * if x is a power of 2 then x & x-1 is 0. complexity - O(1)
	 * 
	 * @param x
	 * @return
	 */
	public boolean isPowerOf2_bit(int x) {
		int andoper = x & (x - 1);
		return (andoper == 0 ? true : false);
	}

	/**
	 * recursive way. Time Complexity: O(n) Space Complexity: O(1) Algorithmic Paradigm: Divide and
	 * conquer.
	 * 
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int getPowerOfN(int x, int y) {
		if (y == 0)
			return 1;
		else if (y % 2 == 0) {
			return getPowerOfN(x, y / 2) * getPowerOfN(x, y / 2);
		} else {
			return x * getPowerOfN(x, y / 2) * getPowerOfN(x, y / 2);
		}
	}

	/**
	 * Time Complexity of optimized solution: O(logn)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int getPowerOfN_optimized(int x, int y) {
		if (y == 0)
			return 1;
		int temp = getPowerOfN_optimized(x, y % 2);
		if (temp == 0) {
			return temp * temp;
		} else {
			return x * temp * temp;
		}
	}

	/**
	 * complexity - O(K) where K is the number of ones present in binary form
	 * 
	 * @param n
	 * @return
	 */
	public int countOnesInBinaryForm(int n) {
		System.out.println(Integer.toBinaryString(n));
		int count = 0;
		while (n != 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}

	/**
	 * check if ith bit is set (as 1) in the given number n. and n with 2^i , 2^i = 1 << i
	 * 
	 * @param n
	 * @param i
	 * @return
	 */
	public boolean bitSetInNum(int n, int i) {
		int s = n & 1 << i;
		System.out.println(s);
		return (s > 0 ? true : false);
	}

	public void numberOfSubsetsOfASet(char[] chararr) {
		int n = chararr.length;
		for (int i = 0; i < (1 << n); i++) { // loops from 0 to 2^n
			for (int j = 0; j < n; j++) {// loops from 0 to n
				int s = i & 1 << j;
				// System.out.print(s);
				System.out.print(s > 0 ? chararr[j] : "");
			}
			System.out.println();
		}
	}

	/**
	 * find the largest power of 2 <= N(i.e MSB of N in binary form) If N has all bits as 1, then N =
	 * 2^i - 1, where i = number of bits in N. trick is to convert all right side bits of MSB to 1
	 * 
	 * @param n
	 */
	public void largestPower(int n) {
		// for 16 bit integer
		n = n | n >> 1;
		n = n | n >> 2;
		n = n | n >> 4;
		n = n | n >> 8;
		// for 32 bit and 64 bit integers/long it can be extended further
		// at this point all bits on right side of MSB is 1
		int result = (n + 1) >> 1;
		System.out.println(result);
	}

	/**
	 * You are given two 32-bit numbers, N and M, and two bit positions, i and j. Write a method to
	 * insert M into N such that M starts at bit j and ends at bit i. You can assume that the bits j
	 * through i have enough space to fit all of M. That is, if M = 10011, you can assume that there are
	 * at least 5 bits between j and i. You would not, for example, have j = 3 and i = 2, because M
	 * could not fully fit between bit 3 and bit 2. EXAMPLE Input: N = l0000000000 , M = 10011, i= 2, j=
	 * 6 Output: N = 10001001100.
	 * 
	 * This problem can be approached in three key steps: 1. Clear the bits j through i in N 2. Shift M
	 * so that it lines up with bits j through i 3. Merge M and N.
	 * 
	 * @param m
	 * @param n
	 * @param i
	 * @param j
	 * @return
	 */
	public int insert(int m, int n, int i, int j) {
		int allOnes = ~0; // all 1s
		// 1s before position j then 0
		int left = allOnes << (j + 1);
		// 1s after position i
		int right = (1 << i) - 1;
		// all 1s except for position between i and j
		int mask = left | right;
		// clear bits j through i and then put m in there
		int n_cleared = n & mask;// clear bits j through i
		int m_shifted = m << i;// shift m in correct position
		return n_cleared | m_shifted; // OR them
	}

	/**
	 * Given an integer array of n integers, find sum of bit differences in all pairs that can be formed
	 * from array elements. Bit difference of a pair (x, y) is count of different bits at same positions
	 * in binary representations of x and y. For example, bit difference for 2 and 7 is 2. Binary
	 * representation of 2 is 010 and 7 is 111 ( first and last bits differ in two numbers).
	 * 
	 * Examples :
	 * 
	 * Input: arr[] = {1, 2} Output: 4 All pairs in array are (1, 1), (1, 2) (2, 1), (2, 2) Sum of bit
	 * differences = 0 + 2 + 2 + 0 = 4
	 * 
	 * Input: arr[] = {1, 3, 5} Output: 8 All pairs in array are (1, 1), (1, 3), (1, 5) (3, 1), (3, 3)
	 * (3, 5), (5, 1), (5, 3), (5, 5) Sum of bit differences = 0 + 1 + 1 + 1 + 0 + 2 + 1 + 2 + 0 = 8 .
	 * complexity - O(n^2)
	 * 
	 * @param arr
	 * @return
	 */
	public int sumOfBitDiffAmongAllPairs_simple(int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				int x = arr[i];
				int y = arr[j];
				if (x != y) {
					int diffBits = x ^ y;
					// count 1s in binary form
					count += countOnesInBinaryForm(diffBits);
				}
			}
		}
		return count;
	}

	/**
	 * An Efficient Solution can solve this problem in O(n) time using the fact that all numbers are
	 * represented using 32 bits (or some fixed number of bits). The idea is to count differences at
	 * individual bit positions. We traverse from 0 to 31 and count numbers with ith bit set. Let this
	 * count be count. There would be n-count numbers with ith bit not set. So count of differences
	 * at ith bit would be count * (n-count) * 2.
	 * 
	 * 
	 * @param arr
	 * @return
	 */
	public int sumOfBitDiffAmongAllPairs_efficient(int[] arr) {
		int ans = 0; // Initialize result
		int n = arr.length;

		// traverse over all bits
		for (int i = 0; i < 32; i++) {

			// count number of elements
			// with i'th bit set
			int count = 0;

			for (int j = 0; j < n; j++)
				if ((arr[j] & (1 << i)) == 0)
					count++;

			// Add "count * (n - count) * 2"
			// to the answer
			ans += (count * (n - count) * 2);
		}

		return ans;

	}

}
