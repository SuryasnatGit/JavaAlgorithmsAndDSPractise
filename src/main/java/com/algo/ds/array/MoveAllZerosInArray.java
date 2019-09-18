
package com.algo.ds.array;

import java.util.Arrays;

/**
 * {0,0,1,2,0,5,6,7,0} becomes 125670000
 * 
 * @author ctsuser1
 */
public class MoveAllZerosInArray {

	/**
	 * time - O(n)
	 * 
	 * @param arr
	 */
	public void moveZerosToEnd(int arr[]) {
		int slow = 0;
		int fast = 0;
		// accumulates all non 0s in the beginning of the array
		while (fast < arr.length) {
			if (arr[fast] == 0) {
				fast++;
				continue; // moves to the next iteration.
			}
			arr[slow] = arr[fast];
			slow++;
			fast++;
		}
		// accumulates all 0s at the end of the array
		while (slow < arr.length) {
			arr[slow++] = 0;
		}

		System.out.println(Arrays.toString(arr));
	}

	/**
	 * time - O(n)
	 * 
	 * @param arr
	 */
	public void moveZerosToBeginning(int[] arr) {
		int slow = arr.length - 1;
		int fast = arr.length - 1;
		while (fast >= 0) {
			if (arr[fast] == 0) {
				fast--;
				continue;
			}
			arr[slow--] = arr[fast--];
		}

		// append 0s at beginning
		while (slow >= 0) {
			arr[slow--] = 0;
		}

		System.out.println(Arrays.toString(arr));
	}

	public static void main(String args[]) {
		MoveAllZerosInArray maz = new MoveAllZerosInArray();
		maz.moveZerosToEnd(new int[] { 0, 0, 1, 2, 0, 5, 6, 7, 0 });
		maz.moveZerosToBeginning(new int[] { 0, 0, 1, 2, 0, 5, 6, 7, 0 });
	}
}
