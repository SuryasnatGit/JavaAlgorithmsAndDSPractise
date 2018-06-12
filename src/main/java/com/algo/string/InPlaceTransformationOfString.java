package com.algo.string;

/**
 * http://www.geeksforgeeks.org/an-in-place-algorithm-for-string-transformation/.
 * 
 * Given a string, move all even positioned elements to end of string. While
 * moving elements, keep the relative order of all even positioned and odd
 * positioned elements same. For example, if the given string is
 * “a1b2c3d4e5f6g7h8i9j1k2l3m4”, convert it to “abcdefghijklm1234567891234”
 * in-place and in O(n) time complexity.
 */
public class InPlaceTransformationOfString {

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

	public void inPlaceTransformationImproved(char str[]) {
		int low = 0;
		int size = str.length;
		while (size > 0) {
			int end = get3PowerK1(size);
			size = size - end;
			CycleLeaderIteration cli = new CycleLeaderIteration();
			cli.iterate(str, low, end + low - 1);
			low = low + end;
		}
		size = str.length;
		low = 0;
		int end = get3PowerK1(size);
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
