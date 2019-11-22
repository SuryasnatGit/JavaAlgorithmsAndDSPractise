package com.ctci.hard;

import java.util.Arrays;

/**
 * Random Set: Write a method to randomly generate a set of m integers from an array of size n. Each element must have
 * equal probability of being chosen.
 *
 * CTCI - 17.3
 */
public class RandomSet {

	public int[] set(int[] original, int m) {
		int[] ran = new int[m];
		// first copy m ints from original array
		for (int i = 0; i < m; i++) {
			ran[i] = original[i];
		}

		// next go for the rest of the array
		for (int i = m; i < original.length; i++) {
			int k = random(0, i);
			if (k < m) {
				ran[k] = original[i];
			}
		}

		return ran;
	}

	private int random(int start, int end) {
		return start + (int) Math.random() * (end - start + 1);
	}

	public static void main(String[] args) {
		RandomSet rs = new RandomSet();
		int[] res = rs.set(new int[] { 1, 5, 6, 2, 3, 7 }, 4);
		System.out.println(Arrays.toString(res));
	}

}
