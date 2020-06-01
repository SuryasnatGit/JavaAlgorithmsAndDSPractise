package com.ctci.moderate;

/**
 * Write an algorithm which computes the number of traling zeros in n factorial
 *
 * CTCI : 16.5
 */
public class FactorialZeros {

	public int factorial0s(int num) {
		int count = 0;
		for (int i = 2; i <= num; i++) {
			count += numberOfTimes5PresentInNum(i);
		}
		return count;
	}

	private int numberOfTimes5PresentInNum(int i) {
		int count = 0;
		while (i % 5 == 0) {
			count++;
			i /= 5;
		}
		return count;
	}

	public static void main(String[] args) {
		FactorialZeros fz = new FactorialZeros();
		System.out.println(fz.factorial0s(19));
		System.out.println(fz.factorial0s(125));
	}
}
