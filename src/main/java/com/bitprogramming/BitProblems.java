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
		System.out.println(bp.bitSetInNum(20, 4));
		bp.numberOfSubsetsOfASet("train".toCharArray());
		bp.largestPower(150);
	}

	public void flipAllBits(int n) {
		System.out.println(Integer.toBinaryString(n));
		// if n is a 32 bit integer
		n = n ^ ((1 << 32) - 1);
		System.out.println(Integer.toBinaryString(n));
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
	 * find the largest power of 2 <= N(i.e MSB of N in binary form) If N has all bits as 1, then N = 2^i - 1, where i =
	 * number of bits in N. trick is to convert all right side bits of MSB to 1
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

}
