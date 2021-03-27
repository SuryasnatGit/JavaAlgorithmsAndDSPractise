package com.ctci.moderate;

/**
 * Write methods to implement the multiply, subtract, and divide operations for integers. The results of all of these
 * are integers. Use only the add operator.
 * 
 * TODO : understand multiply and divide operations better
 * 
 * CTCI : 16.9
 *
 */
public class MathOperations {

	// T - O(k)
	private int negate(int a) {
		int res = 0;
		int negSign = a > 0 ? -1 : 1;
		while (a != 0) {
			res += negSign;
			a += negSign;
		}
		return res;
	}

	/**
	 * we can try to get a to zero faster. (For this explanation, we'll assume that a is positive.) To do this, we can
	 * first reduce a by 1, then 2, then 4, then 8, and so on. We'll call this value delta. We want a to reach exactly
	 * zero. When reducing a by the next delta would change the sign of a, we reset delta back to 1 and repeat the
	 * process. See CTCI Pg 492/712 for time complexity analysis.
	 * 
	 * T(n) - O((log a)^2)
	 * 
	 * @param a
	 * @return
	 */
	private int negate_opt(int a) {
		int res = 0;
		int newSign = a > 0 ? -1 : 1;
		int delta = newSign;
		// repeat until a == 0
		while (a != 0) {
			boolean diffSigns = (a + delta > 0) != (a > 0);
			if (a + delta != 0 && diffSigns) { // if delta is too big then resst it
				delta = newSign;
			}
			res += delta;
			a += delta;
			delta += delta; // double delta
		}
		return res;
	}

	public int subtract(int a, int b) {
		return a + negate_opt(b);
	}

	/* Multiply a by b by adding a to itself b times */
	public int multiply(int a, int b) {
		if (a < b) {
			return multiply(b, a); // algorithm is faster if b < a
		}
		int sum = 0;
		for (int i = abs(b); i > 0; i = subtract(i, 1)) {
			sum += a;
		}
		if (b < 0) {
			sum = negate(sum);
		}
		return sum;
	}

	// Return absolute value
	private int abs(int a) {
		if (a < 0) {
			return negate(a);
		} else {
			return a;
		}
	}

	public int divide(int a, int b) throws java.lang.ArithmeticException {
		if (b == a) {
			throw new java.lang.ArithmeticException("ERROR");
		}
		int absa = abs(a);
		int absb = abs(b);

		int product = 0;
		int x = 0;
		while (product + absb <= absa) { /* don't go past a */
			product += absb;
			x++;
		}
		if ((a < 0 && b < 0) || (a > 0 && b > 0)) {
			return x;
		} else {
			return negate_opt(x);
		}
	}

	public static void main(String[] args) {
		MathOperations mo = new MathOperations();
		System.out.println(mo.negate(50));
		System.out.println(mo.negate_opt(30));
		System.out.println(mo.subtract(5, 10));
		System.out.println(mo.subtract(20, 10));
		System.out.println(mo.multiply(5, 16));
		System.out.println(mo.divide(34, 17));
	}
}
