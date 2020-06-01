package com.algo.ds.array;

/**
 * Given a sorted array arr[] and a number x, write a function that counts the occurrences of x in arr[]. Expected time
 * complexity is O(Logn)
 * 
 * Examples:
 * 
 * Input: arr[] = {1, 1, 2, 2, 2, 2, 3,}, x = 2
 * 
 * Output: 4 // x (or 2) occurs 4 times in arr[]
 * 
 * Input: arr[] = {1, 1, 2, 2, 2, 2, 3,}, x = 3
 * 
 * Output: 1
 * 
 * Input: arr[] = {1, 1, 2, 2, 2, 2, 3,}, x = 1
 * 
 * Output: 2
 * 
 * Input: arr[] = {1, 1, 2, 2, 2, 2, 3,}, x = 4
 * 
 * Output: -1 // 4 doesn't occur in arr[]
 *
 */
public class FrequencyOfElementInSortedArray {

	public int count(int[] array, int target) {
		int left = 0, right = array.length - 1;
		int index = -1;

		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				index = mid;
				break;
			}
			if (array[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}

		if (array[left] == target) {
			index = left;
		} else if (array[right] == target) {
			index = right;
		}

		if (index == -1) {
			return -1;
		}

		int count = 1;
		left = index - 1;
		while (left >= 0 && array[left] == target) {
			count++;
			left--;
		}

		right = index + 1;
		while (right <= array.length - 1 && array[right] == target) {
			count++;
			right++;
		}

		return count;
	}

	public static void main(String[] args) {
		FrequencyOfElementInSortedArray fre = new FrequencyOfElementInSortedArray();
		System.out.println(fre.count(new int[] { 1, 1, 2, 2, 2, 2, 3, }, 1));
		System.out.println(fre.count(new int[] { 1, 1, 2, 2, 2, 2, 3, }, 2));
		System.out.println(fre.count(new int[] { 1, 1, 2, 2, 2, 2, 3, }, 3));
		System.out.println(fre.count(new int[] { 1, 1, 2, 2, 2, 2, 3, }, 4));
	}
}
