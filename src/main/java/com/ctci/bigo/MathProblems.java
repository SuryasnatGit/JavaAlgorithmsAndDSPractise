package com.ctci.bigo;

public class MathProblems {

	/**
	 * O(B)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int intProduct(int a, int b) {
		int sum = 0;
		for (int i = 0; i < b; i++) {
			sum += a;
		}
		return sum;
	}

	/**
	 * O(B)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int apowerb(int a, int b) {
		if (b < 0)
			return -1;
		else if (b == 0)
			return 1;
		else
			return a * apowerb(a, b - 1);
	}

	/**
	 * O(1)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int modulo(int a, int b) {
		if (b <= 0)
			return -1;
		int div = a / b;
		return a - div * b;
	}

	/**
	 * O( a/b ) . The variable count will eventually equal ~. The while loop iterates count times.
	 * Therefore, it iterates a/b times.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int division(int a, int b) {
		int count = 0;
		int sum = b;
		while (sum <= a) {
			sum += b;
			count++;
		}
		return count;
	}

	/**
	 * this essentially does a binary search, so O(log N)
	 * 
	 * @param n
	 * @return
	 */
	public int sqrt(int n) {
		return sqrt(n, 1, n);
	}

	private int sqrt(int num, int min, int max) {
		if (max < min)
			return -1;// error

		int guess = (min + max) / 2;
		int prod = guess * guess;
		if (prod == num)
			return guess;
		else if (prod < num) {
			// too low
			return sqrt(num, guess + 1, max);
		} else {
			// too high
			return sqrt(num, min, guess - 1);
		}
	}

	/**
	 * complexity - O(log N). A number of d digits can be 10^d. so d = Log N
	 * 
	 * @param num
	 * @return
	 */
	public int sumOfDigits(int num) {
		int sum = 0;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		return sum;
	}

	/**
	 * write a function to check if the value of a binary number (passed as a string) equals the
	 * hexadecimal representation of a string.
	 */
	public boolean compareBinaryToHex(String binary, String hex) {
		int n1 = convertFromBase(binary, 2);
		int n2 = convertFromBase(hex, 16);
		return (n1 < 0 || n2 < 0) ? false : n1 == n2;
	}

	private int convertFromBase(String number, int base) {
		// check for base
		if (base < 2 || (base > 10 && base != 16))
			return -1;
		int value = 0;
		for (int i = number.length() - 1; i >= 0; i--) {
			int digit = Character.getNumericValue(number.charAt(i));
			if (digit < 0 && digit >= base)
				return -1;
			int exp = number.length() - 1 - i;
			value += digit * Math.pow(base, exp);
		}
		return value;
	}

	public static void main(String[] args) {
		MathProblems p = new MathProblems();
		// System.out.println(p.intProduct(4, 5));
		// System.out.println(p.apowerb(2, 4));
		// System.out.println(p.modulo(10, 3));
		// System.out.println(p.division(20, 3));
		// System.out.println(p.sqrt(60));
		// System.out.println(p.sumOfDigits(586));
		//
		// System.out.println(Integer.toBinaryString(200));
		// System.out.println(Integer.toOctalString(200));
		// System.out.println(Integer.toHexString(200));
		//
		// // System.out.println(Integer.parseUnsignedInt("310", 2));
		// System.out.println(Integer.parseUnsignedInt("310", 8));

		System.out.println(p.compareBinaryToHex("11001000", "c8"));
	}

}
