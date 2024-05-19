package com.companyprep;

import java.util.Arrays;

/**
 * Category : Hard
 * 
 * TODO : to understand more thoroughly
 */
public class BigIntegerOperations {

	public int[] bigIntMultiple(int[] num1, int[] num2) {
		int m = num1.length;
		int n = num2.length;

		int[] res = new int[m + n];

		if ((m == 1 && num1[0] == 0) || (n == 1 && num2[0] == 0)) {
			return new int[] { 0 };
		}

		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				int mul = num1[i] * num2[j];

				int p1 = i + j + 1;
				int p2 = i + j;

				mul += res[p1];
				res[p2] += mul / 10;
				res[p1] = mul % 10;
			}
		}

		return res;
	}

	public int divide2Integers(int val1, int val2) {
		int flag = ((val1 < 0 && val2 > 0) || (val1 > 0 && val2 < 0)) ? -1 : 1;
		boolean flag2 = (val1 < 0) ^ (val2 < 0);

		long num1 = Math.abs(Long.valueOf(val1));
		long num2 = Math.abs(Long.valueOf(val2));

		if (num2 == 0) {
			return Integer.MAX_VALUE;
		}
		if (num1 < num2) {
			return 0;
		}

		long left = 1, right = num1;
		while (true) {
			long mid = left + (right - left) / 2;

			if (mid > num1 / num2) {
				right = mid - 1;
			} else {
				if ((mid + 1) > num1 / num2) {
					if (flag == 1 && mid > Integer.MAX_VALUE) {
						return Integer.MAX_VALUE;
					} else if (flag == -1 && flag * mid < Integer.MIN_VALUE) {
						return Integer.MIN_VALUE;
					}
					return (int) (flag * mid);
				}
				left = mid + 1;
			}
		}
	}

	public static void main(String[] args) {
		BigIntegerOperations bio = new BigIntegerOperations();
		System.out.println(Arrays.toString(bio.bigIntMultiple(new int[] { 7 }, new int[] { 8 })));
		System.out.println(Arrays.toString(bio.bigIntMultiple(new int[] { 7, 8, 9 }, new int[] { 8, 9, 7 })));
		System.out.println(Arrays.toString(bio.bigIntMultiple(new int[] { 2 }, new int[] { 3 }))); // this does not
																									// work.. refer
																									// leetcode solution
		System.out.println(Arrays.toString(bio.bigIntMultiple(new int[] { 0 }, new int[] { 0 })));
		System.out.println(bio.divide2Integers(10, 2));
		System.out.println(bio.divide2Integers(500000000, 2));
	}

}
