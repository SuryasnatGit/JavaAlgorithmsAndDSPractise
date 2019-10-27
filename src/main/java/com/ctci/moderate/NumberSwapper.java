package com.ctci.moderate;

/**
 * Write a function to swap a number in place (that is, without temporary variables).
 * 
 * CTCI : 16.1
 *
 */
public class NumberSwapper {

	public void swapNumbers(int a, int b) {
		System.out.println(a + " " + b);
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println(a + " " + b);
	}

	/**
	 * using XOR ( ^)
	 * 
	 * @param a
	 * @param b
	 */
	public void swapNumbersBinary(int a, int b) {
		System.out.println(a + " " + b);
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(a + " " + b);
	}

	public static void main(String[] args) {
		NumberSwapper ns = new NumberSwapper();
		ns.swapNumbers(5, 10);
		ns.swapNumbersBinary(7, 3);
		ns.swapNumbers(50, 20);
		ns.swapNumbers(Integer.MAX_VALUE, Integer.MAX_VALUE - 1);
	}

}
