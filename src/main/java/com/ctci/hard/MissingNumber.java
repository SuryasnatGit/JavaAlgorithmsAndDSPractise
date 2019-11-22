package com.ctci.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Missing Number: An array A contains all the integers from 0 to n, except for one number which is missing. In this
 * problem, we cannot access an entire integer in A with a single operation. The elements of A are represented in
 * binary, and the only operation we can use to access them is "fetch the jth bit of A[i]," which takes constant time.
 * Write code to find the missing integer. Can you do it in O(n) time?
 * 
 * CTCI - 17.4
 *
 */
public class MissingNumber {

	// Solution 1 - Given a list of numbers from 0 to n, with exactly
	// one number removed, find the missing number. This problem can be solved by simply adding the list of
	// numbers and comparing it to the actual sum of 0 through n, which is n * (n + 1)/2. The difference will be the
	// missing number. T - O(n^2)

	// Solution 2 - We could solve this by computing the value of each number, based on its binary representation, and
	// calculating the sum. The runtime of this solution is n * length(n), when length is the number of bits in n. Note
	// that length (n) = log2 (n) . So, the runtime is actually O( n log (n) ) . Not quite good enough!

	// Solution 3 - O(n)
	/* Create a random array of numbers from 0 to n, but skip 'missing' */
	public static List<BitInteger> initialize(int n, int missing) {
		BitInteger.INTEGER_SIZE = Integer.toBinaryString(n).length();
		List<BitInteger> array = new ArrayList<BitInteger>();

		for (int i = 1; i <= n; i++) {
			array.add(new BitInteger(i == missing ? 0 : i));
		}

		// Shuffle the array once.
		for (int i = 0; i < n; i++) {
			int rand = i + (int) (Math.random() * (n - i));
			array.get(i).swapValues(array.get(rand));
		}

		return array;
	}

	public static int findMissing(List<BitInteger> array) {
		return findMissing(array, BitInteger.INTEGER_SIZE - 1);
	}

	private static int findMissing(List<BitInteger> input, int column) {
		if (column < 0) { // Base case and error condition
			return 0;
		}
		List<BitInteger> oneBits = new ArrayList<BitInteger>(input.size() / 2);
		List<BitInteger> zeroBits = new ArrayList<BitInteger>(input.size() / 2);
		for (BitInteger t : input) {
			if (t.fetch(column) == 0) {
				zeroBits.add(t);
			} else {
				oneBits.add(t);
			}
		}
		if (zeroBits.size() <= oneBits.size()) {
			int v = findMissing(zeroBits, column - 1);
			System.out.print("0");
			return (v << 1) | 0;
		} else {
			int v = findMissing(oneBits, column - 1);
			System.out.print("1");
			return (v << 1) | 1;
		}
	}

	public static void main(String[] args) {
		Random rand = new Random();
		int n = rand.nextInt(300000) + 1;
		int missing = rand.nextInt(n + 1);
		List<BitInteger> array = initialize(n, missing);
		System.out.println("The array contains all numbers but one from 0 to " + n + ", except for " + missing);

		int result = findMissing(array);
		if (result != missing) {
			System.out.println("Error in the algorithm!\n" + "The missing number is " + missing
					+ ", but the algorithm reported " + result);
		} else {
			System.out.println("The missing number is " + result);
		}
	}
}
