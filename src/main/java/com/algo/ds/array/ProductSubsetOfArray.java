package com.algo.ds.array;

/**
 * Greedy Algorithm
 * 
 * @author surya
 *
 */
public class ProductSubsetOfArray {

	/**
	 * Given an array a, we have to find minimum product possible with the subset of elements present in
	 * the array. The minimum product can be single element also.
	 * 
	 * Examples:
	 * 
	 * Input : a[] = { -1, -1, -2, 4, 3 } Output : -24 Explanation : Minimum product will be ( -2 * -1 *
	 * -1 * 4 * 3 ) = -24
	 * 
	 * Input : a[] = { -1, 0 } Output : -1 Explanation : -1(single element) is minimum product possible
	 * 
	 * Input : a[] = { 0, 0, 0 } Output : 0
	 * 
	 * Cases:
	 * 
	 * a. If there are even number of negative numbers and no zeros, the result is the product of all
	 * except the largest valued negative number.
	 * 
	 * b. If there are an odd number of negative numbers and no zeros, the result is simply the product
	 * of all.
	 * 
	 * c. If there are zeros and positive, no negative, the result is 0. The exceptional case is when
	 * there is no negative number and all other elements positive then our result should be the first
	 * minimum positive number.
	 * 
	 * Time Complexity : O(n) Auxiliary Space : O(1)
	 * 
	 * 
	 * 
	 * @return
	 */
	public int minProductSubset(int[] arr) {
		int n = arr.length;
		if (n == 1)
			return arr[0];

		// initialize variables
		int countZero = 0;
		int countNegative = 0;
		int negativeMax = Integer.MIN_VALUE;
		int positiveMin = Integer.MAX_VALUE;
		int product = 1;

		for (int i = 0; i < n; i++) {
			if (arr[i] == 0) {
				countZero++;
				continue;
			}
			if (arr[i] < 0) {
				countNegative++;
				// find max negative number
				negativeMax = Math.max(negativeMax, arr[i]);
			}
			// find the maximum positive number
			if (arr[i] > 0 && arr[i] < positiveMin) {
				positiveMin = arr[i];
			}
			product *= arr[i];
		}

		// if there are all zeros and no negative numbers
		if (countZero == n || (countZero > 0 && countNegative == 0))
			return 0;

		// if there are all positives
		if (countNegative == 0)
			return positiveMin;

		// if there are even number of negative numbers
		if (countNegative > 0 && countNegative % 2 == 0)
			product = product / negativeMax;

		return product;
	}

	/**
	 * Given an array a, we have to find maximum product possible with the subset of elements present in
	 * the array. The maximum product can be single element also.
	 * 
	 * Examples:
	 * 
	 * Input : a[] = { -1, -1, -2, 4, 3 } Output : 24 Explanation : Maximum product will be ( -2 * -1 *
	 * 4 * 3 ) = 24
	 * 
	 * Input : a[] = { -1, 0 } Output : 0 Explanation : 0(single element) is maximum product possible
	 * 
	 * Input : a[] = { 0, 0, 0 } Output : 0
	 * 
	 * Cases:
	 * 
	 * 1. If there are even number of negative numbers and no zeros, result is simply product of all.
	 * 
	 * 2. If there are odd number of negative numbers and no zeros, result is product of all except the
	 * largest valued negative number.
	 * 
	 * 3. If there are zeros, result is product of all except these zeros with one exceptional case. The
	 * exceptional case is when there is one negative number and all other elements are 0. In this case,
	 * result is 0.
	 * 
	 * @return
	 */
	public int maxProductSubset(int[] arr) {
		int n = arr.length;

		if (n == 1)
			return arr[0];

		int countZero = 0;
		int countNegative = 0;
		int maximumNegative = Integer.MIN_VALUE;
		int product = 1;
		for (int i = 0; i < n; i++) {
			// count zero elements
			if (arr[i] == 0) {
				countZero++;
				continue;
			}
			// count negative
			if (arr[i] < 0) {
				countNegative++;
				maximumNegative = Math.max(maximumNegative, arr[i]);
			}

			product = product * arr[i];
		}
		// if all zeros
		if (countZero == n)
			return 0;

		// if negative count is odd
		if (countNegative % 2 == 1) {
			// special case
			if (countNegative == 1 && countZero > 0 && (countNegative + countZero == n))
				return 0;
			product = product / maximumNegative;
		}

		return product;
	}

	public static void main(String[] args) {
		ProductSubsetOfArray ps = new ProductSubsetOfArray();
		int[] arr = { -1, -1, -2, 4, 3 };
		System.out.println(ps.minProductSubset(arr));
		System.out.println(ps.maxProductSubset(arr));
	}
}
