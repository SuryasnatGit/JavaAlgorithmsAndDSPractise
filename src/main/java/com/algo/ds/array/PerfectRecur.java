package com.algo.ds.array;

public class PerfectRecur {
	public static void main(String[] args) {
		PerfectRecur r = new PerfectRecur();
		int query = 6;
		int sum = r.sumFactorsTo(query, query - 1);
		if (sum == query) {
			System.out.println(query + " is perfect");
		} else {
			System.out.println(query + " isn't perfect: The sum is " + sum);
		}
	}

	public int sumFactorsTo(int num, int max) {
		if (max == 0) {
			return 0;
		} else {
			int sub = sumFactorsTo(num, max - 1);
			if (num % max == 0) {
				sub += max;
			}
			return sub;
		}
	}
}