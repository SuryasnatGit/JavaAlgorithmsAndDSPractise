package com.algo.string;

/**
 * http://www.geeksforgeeks.org/an-in-place-algorithm-for-string-transformation/.
 * 
 * Given a string, move all even positioned elements to end of string. While moving elements, keep the relative order of
 * all even positioned and odd positioned elements same. For example, if the given string is a1b2c3d4e5f6g7h8i9j1k2l3m4,
 * convert it to abcdefghijklm1234567891234 in-place and in O(n) time complexity.
 * 
 * Below are the steps:
 * 
 * 1. Cut out the largest prefix sub-string of size of the form 3^k + 1. In this step, we find the largest non-negative
 * integer k such that 3^k+1 is smaller than or equal to n (length of string).
 * 
 * 2. Apply cycle leader iteration algorithm ( it has been discussed below ), starting with index 1, 3, 9…… to this
 * sub-string. Cycle leader iteration algorithm moves all the items of this sub-string to their correct positions, i.e.
 * all the alphabets are shifted to the left half of the sub-string and all the digits are shifted to the right half of
 * this sub-string.
 * 
 * 3. Process the remaining sub-string recursively using steps#1 and #2.
 * 
 * 4. Now, we only need to join the processed sub-strings together. Start from any end ( say from left ), pick two
 * sub-strings and apply the below steps:
 * 
 * 4.1 Reverse the second half of first sub-string. <br/>
 * 4.2 Reverse the first half of second sub-string.<br/>
 * 4.3 Reverse the second half of first sub-string and first half of second sub-string together.
 * 
 * 5. Repeat step#4 until all sub-strings are joined. It is similar to k-way merging where first sub-string is joined
 * with second. The resultant is merged with third and so on.
 * 
 * Category : Hard
 */
public class InPlaceTransformationOfString {

	public void inPlaceTransformationImproved(char str[]) {
		int low = 0;
		int size = str.length;
		// do actual CLI
		while (size > 0) {
			int end = get3PowerK1(size);
			size = size - end;
			CycleLeaderIteration cli = new CycleLeaderIteration();
			cli.iterate(str, low, end + low - 1);
			low = low + end;
		}

		// calculate size and end for merge operations
		size = str.length;
		int end = get3PowerK1(size);
		// find the size chunks of CLI lengths
		while (end < str.length) {
			int nextEnd = get3PowerK1(str.length - end);
			reverse(str, end / 2, end - 1);
			reverse(str, end / 2, end + nextEnd / 2 - 1);
			reverse(str, end / 2, end / 2 + nextEnd / 2 - 1);
			end = end + nextEnd;
		}
	}

	private int get3PowerK1(int size) {
		int power = 1;
		while ((power * 3 + 1) <= size) {
			power = power * 3;
		}
		return power + 1;
	}

	private void reverse(char[] str, int low, int high) {
		while (low < high) {
			swap(str, low, high);
			low++;
			high--;
		}
	}

	private void swap(char str[], int index1, int index2) {
		char temp = str[index1];
		str[index1] = str[index2];
		str[index2] = temp;
	}

	public static void main(String args[]) {
		char str[] = { 'a', '1', 'b', '2', 'c', '3', 'd', '4', 'e', '5', 'f', '6', 'g', '7', 'h', '8', 'i', '9', 'j',
				'A', 'k', 'B', 'l', 'C', 'm', 'D' };
		InPlaceTransformationOfString ip = new InPlaceTransformationOfString();
		ip.inPlaceTransformationImproved(str);
		for (int i = 0; i < str.length; i++) {
			System.out.print(str[i]);
		}
	}

}
