package com.ctci.bigo;

import java.util.Random;

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
	 * O( a/b ) . The variable count will eventually equal ~. The while loop iterates count times. Therefore, it
	 * iterates a/b times.
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

	// divide and conquer is fastest
	public int numberOfDigits(int num) {
		if (num == 0)
			return 1;

		num = Math.abs(num);

		int l;
		for (l = 0; num > 0; l++) {
			num /= 10;
		}
		return l;
	}

	/**
	 * write a function to check if the value of a binary number (passed as a string) equals the hexadecimal
	 * representation of a string.
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

	public int reverseInteger(int input) {
		int ret = 0;
		while (input != 0) {
			ret = ret * 10 + input % 10;
			if (ret > Integer.MAX_VALUE)
				return 0;
			input /= 10;
		}
		return ret;
	}

	public boolean isPalindrome(int num) {
		if (num < 0)
			return false;

		int div = 1;
		while (num / div >= 10)
			div *= 10;

		while (num != 0) {
			int left = num / div;
			int right = num % 10;
			if (left != right)
				return false;

			num = (num % div) / 10;
			div /= 100; // as 2 places are discarded in every loop
		}
		return true;
	}

	public double calculatePI() {
		Random ran = new Random();

		int N = 1000000;
		int inside = 0;

		for (int i = 0; i < N; i++) {
			double x = ran.nextDouble(); // 0.0 - 1.0, not include 1
			double y = ran.nextDouble();

			if ((x * x + y * y) <= 1) {
				inside++;
			}
		}

		double res = (double) ((4.0 * inside) / N);
		return res;
	}

	public void printMultiplicationTable(int num) {
		for (int i = 1; i <= num; i++) {
			for (int j = 1; j <= num; j++) {
				int mul = i * j;
				int len = Integer.toString(mul).length(); // for proper alignment
				int space = 4 - len;// as max is 100(3 digits)
				while (space-- > 0) {
					System.out.print(" ");
				}
				System.out.print(mul);
			}
			System.out.println();
		}
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

		// System.out.println(p.compareBinaryToHex("11001000", "c8"));
		// System.out.println(p.reverseInteger(123));
		// System.out.println(p.reverseInteger(-476));
		// System.out.println(p.isPalindrome(12341));
		// System.out.println(p.isPalindrome(12321));
		// System.out.println(p.numberOfDigits(1938723765));
		p.printMultiplicationTable(10);
	}

}
