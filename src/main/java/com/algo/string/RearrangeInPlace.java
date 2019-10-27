package com.algo.string;

import java.util.Arrays;

/**
 * * a[i] = a[a[i]] in-place Write a program to modify the array such that arr[I] = arr[arr[I]]. Do this in place i.e.
 * with out using additional memory.
 * 
 * input : {2,3,1,0} output : {1,0,3,2}
 * 
 * https://www.geeksforgeeks.org/rearrange-given-array-place/
 * 
 * 1) Increase every array element arr[i] by (arr[arr[i]] % n)*n.
 * 
 * 2) Divide every element by n.
 * 
 * Let us understand the above steps by an example array {3, 2, 0, 1} In first step, every value is incremented by
 * (arr[arr[i]] % n)*n After first step array becomes {7, 2, 12, 9}. The important thing is, after the increment
 * operation of first step, every element holds both old values and new values. Old value can be obtained by arr[i]%n
 * and new value can be obtained by arr[i]/n.
 * 
 * In second step, all elements are updated to new or output values by doing arr[i] = arr[i]/n. After second step, array
 * becomes {1, 0, 3, 2}
 * 
 * time - O(n) space - O(1)
 * 
 * Category : Hard
 *
 */
public class RearrangeInPlace {

	public void rearrange(int[] arr) {
		System.out.println(Arrays.toString(arr));
		int n = arr.length;
		// 1st step
		for (int i = 0; i < n; i++) {
			arr[i] += (arr[arr[i]] % n) * n;
		}

		// 2nd step
		for (int i = 0; i < n; i++) {
			arr[i] /= n;
		}

		System.out.println(Arrays.toString(arr));
	}

	/**
	 * Given an array of size n where all elements are distinct and in range from 0 to n-1, change contents of arr[] so
	 * that arr[i] = j is changed to arr[j] = i.
	 * 
	 * Examples:
	 * 
	 * Example 1:
	 * 
	 * Input: arr[] = {1, 3, 0, 2};
	 * 
	 * Output: arr[] = {2, 0, 3, 1};
	 * 
	 * Explanation for the above output.
	 * 
	 * Since arr[0] is 1, arr[1] is changed to 0<br/>
	 * Since arr[1] is 3, arr[3] is changed to 1<br/>
	 * Since arr[2] is 0, arr[0] is changed to 2<br/>
	 * Since arr[3] is 2, arr[2] is changed to 3<br/>
	 * 
	 * Time - O(n) Space - O(n)
	 */
	public void rearrange1(int[] in) {
		System.out.println(Arrays.toString(in));
		int l = in.length;
		int[] temp = new int[l];

		for (int i = 0; i < l; i++) {
			temp[in[i]] = i;
		}

		for (int i = 0; i < l; i++) {
			in[i] = temp[i];
		}
		System.out.println(Arrays.toString(in));
	}

	/**
	 * Time - O(n) Space - O(1)
	 * 
	 * @param in
	 */
	public void rearrange1_optimized(int[] in) {
		System.out.println(Arrays.toString(in));
		int l = in.length;
		for (int i = 0; i < l; i++) {
			in[in[i] % l] += i * l;
		}

		for (int i = 0; i < l; i++) {
			in[i] /= l;
		}
		System.out.println(Arrays.toString(in));
	}

	public static void main(String[] args) {
		RearrangeInPlace rp = new RearrangeInPlace();
		rp.rearrange(new int[] { 2, 3, 1, 0 });

		rp.rearrange1(new int[] { 1, 3, 0, 2 });

		rp.rearrange1_optimized(new int[] { 1, 3, 0, 2 });
	}

}
