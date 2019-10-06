package com.ctci.hard;

/**
 * Write a function that adds two numbers. You should not use + or any arithmetic operators
 * 
 * CTCI : 17.1
 */
public class AddWithoutPlus {

	/**
	 * 
	 * Using half-adder logic..
	 * 
	 * Can we make this a little easier? Yes! Imagine I decided to split apart the "addition" and
	 * "carry" steps. That is, I do the following:
	 * 
	 * 1. Add 759 + 674, but "forget" to carry. I then get 323.
	 * 
	 * 2. Add 759 + 674 but only do the carrying, rather than the addition of each digit.I then get
	 * 1110.
	 * 
	 * 3. Add the result of the first two operations (recursively, using the same process described in
	 * step 1 and 2): 1110 + 323 = 1433.
	 * 
	 * Now, how would we do this in binary?
	 * 
	 * 1. If I add two binary numbers together, but forget to carry, the i th bit in the sum will be e
	 * only if a and b have the same ith bit (both e or both 1). This is essentially an XOR.
	 * 
	 * 2. If I add two numbers together but only carry, I will have a 1 in the ith bit of the sum only
	 * if bits i - 10f a and b are both is. This is an AND, shifted.
	 * 
	 * 3. Now, recurse until there's nothing to carry.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int add(int x, int y) {
		while (y != 0) {
			int sum = x ^ y; // XOR for sum only and not carry
			int carry = (x & y) << 1; // AND and left shift for only carry
			return add(sum, carry);
		}
		return x;
	}

	/**
	 * Like addition, the idea is to use subtractor logic
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int subtract(int x, int y) {

		// Iterate till there
		// is no carry
		while (y != 0) {
			// Subtraction of bits of x
			// and y where at least one
			// of the bits is not set
			int sum = x ^ y;

			// Borrow is shifted by one
			// so that subtracting it from
			// x gives the required sum
			int carry = ((~x) & y) << 1;

			return subtract(sum, carry);
		}

		return x;
	}

	public static void main(String[] args) {
		AddWithoutPlus ap = new AddWithoutPlus();
		int a = 8;
		int b = 38;
		int xor = a ^ b;
		System.out.println(xor);
		System.out.println(Integer.toBinaryString(xor));
		int t = a & b;
		System.out.println(t);
		System.out.println(Integer.toBinaryString(t));
		int c = t << 1;
		System.out.println(c);
		System.out.println(Integer.toBinaryString(c));

		System.out.println(Integer.toBinaryString(0 << 1));

		System.out.println("Result :" + ap.add(19, 27));
		System.out.println("Result :" + ap.subtract(95, 195));
	}
}
