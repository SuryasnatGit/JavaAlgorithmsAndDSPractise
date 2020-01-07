package com.algo.ds.array;

//https://www.techiedelight.com/find-index-0-replaced-get-maximum-length-sequence-of-continuous-ones/
public class MaxLengthSeqOfContinuous1s {

	public int maxLength(int[] arr) {
		int count = 0;
		int maxIndex = -1;
		int prev0Index = -1;
		int maxCount = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1)
				count++;
			else {
				count = i - prev0Index;
				prev0Index = i;
			}

			if (count > maxCount) {
				maxCount = count;
				maxIndex = prev0Index;
			}
		}

		return maxIndex;
	}

	// Find index of 0 to replaced with 1 to get maximum sequence
	// of continuous 1's using sliding window technique
	public int findIndexofZero(int[] A) {
		int left = 0; // left represents window's starting index
		int count = 0; // stores number of zeros in current window
		int max_count = 0; // stores maximum number of 1's (including 0)

		int max_index = -1; // stores index of 0 to be replaced
		int prev_zero_index = -1; // stores index of previous zero

		// maintain a window [left..i] containing at-most one zero
		for (int i = 0; i < A.length; i++) {
			// if current element is 0, update prev_zero_index and
			// increase count of zeros in current window by 1
			if (A[i] == 0) {
				prev_zero_index = i;
				count++;
			}

			// window becomes unstable if number of zeros in it becomes 2
			if (count == 2) {
				// remove elements from the window's left side till
				// we found a zero
				while (A[left] != 0) {
					left++;
				}

				// remove leftmost 0 so that window becomes stable again
				left++;

				// decrement count as 0 is removed
				count = 1;
			}

			// when we reach here, the window [left..i] contains only
			// at-most one zero we update maximum count and index of 0
			// to be replaced if required
			if (i - left + 1 > max_count) {
				max_count = i - left + 1;
				max_index = prev_zero_index;
			}
		}

		// return index of 0 to be replaced or -1 if array contains all 1's
		return max_index;
	}

	public static void main(String[] args) {
		MaxLengthSeqOfContinuous1s m = new MaxLengthSeqOfContinuous1s();
		System.out.println(m.maxLength(new int[] { 0, 0, 1, 0, 1, 1, 1, 0, 1, 1 }));
		System.out.println(m.findIndexofZero(new int[] { 0, 0, 1, 0, 1, 1, 1, 0, 1, 1 }));
	}
}
