package com.algo.puzzle;

public class Puzzles {

	// https://www.techiedelight.com/angle-between-hour-minute-hand/
	public int findAngle(int hour, int min) {
		int hourdeg = (360 / 12) * hour + (360 * min) / (12 * 60);
		int mindeg = (360 / 60) * min;

		int diff = Math.abs(hourdeg - mindeg);
		if (diff > 180)
			diff = 360 - diff;

		return diff;
	}

	// https://www.techiedelight.com/implement-power-function-without-using-multiplication-division-operators/
	public int power(int a, int b) {
		if (b == 0)
			return 1;

		int res = 0;
		int pow = power(a, b - 1);

		for (int i = 0; i < a; i++) {
			res += pow;
		}
		return res;
	}

	// https://www.techiedelight.com/print-numbers-1-n-without-using-semicolon/
	public void print1ToN(int n) {
		int i = 0;
		while (i++ < n) {
			System.out.print(i + " ");
		}
	}

	public static void main(String[] args) {
		Puzzles p = new Puzzles();
		System.out.println(p.findAngle(5, 30));
		System.out.println(p.findAngle(5, 25));
		System.out.println(p.findAngle(9, 0));
		System.out.println(p.power(2, 4));
		p.print1ToN(3);
	}

}
