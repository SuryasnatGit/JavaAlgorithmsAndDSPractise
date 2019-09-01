package com.algo.search;

/**
 * Given an integer x, find square root of it. If x is not a perfect square,
 * then return floor(√x).
 * 
 * Examples :
 * 
 * Input: x = 4 Output: 2
 * 
 * Input: x = 11 Output: 3
 * 
 * @author M_402201
 *
 */
public class SquareRoot {

	/**
	 * Time complexity of the above solution is O(√ n)
	 * 
	 * @param num
	 * @return
	 */
	public int floorSqrt(int num) {
		if (num == 0 || num == 1)
			return num;

		int i = 1, result = 1;
		while (result <= num) {
			i++;
			result = i * i;
		}
		return i - 1;
	}

	/**
	 * time complexity - O(log n). can be further optimized by start = 0 and end =
	 * num/2 as floor of sq rt of num cannot be more than num / 2 when num > 1
	 * 
	 * @param num
	 * @return
	 */
	public int floorSqrtBinarySearch(int num) {
		// base case
		if (num == 0 || num == 1)
			return num;

		int start = 1, end = num, result = 0;
		while (start <= end) {
			int mid = (start + end) / 2;

			if (mid * mid == num)
				return mid;
			else if (mid * mid < num) {
				start = mid + 1;
				result = mid;
			} else {
				end = mid - 1;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		SquareRoot sr = new SquareRoot();
		System.out.println(sr.floorSqrt(9));
		System.out.println(sr.floorSqrt(11));
		System.out.println(sr.floorSqrt(28));
		System.out.println(sr.floorSqrtBinarySearch(28));
	}
}
