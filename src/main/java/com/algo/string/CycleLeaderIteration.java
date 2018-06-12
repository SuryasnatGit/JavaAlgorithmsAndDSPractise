package com.algo.string;

/**
 * a[i] = a[a[i]] in-place Write a program to modify the array such that arr[I]
 * = arr[arr[I]]. Do this in place i.e. with out using additional memory.
 * 
 * input : {2,3,1,0} output : {1,0,3,2}
 * 
 * This is a simple application of cycle leader iteration algorithm.
 * 
 * Things to note : size of input array will always be of the form 3^k+1 ie,2,
 * 4, 10, 28, 82..
 * 
 * time complexity - O(n). Each item in a cycle is shifted at most once. Thus
 * time complexity of the cycle leader algorithm is O(n). The time complexity of
 * the reverse operation is O(n).
 * 
 * @author Suryasnat
 *
 */
public class CycleLeaderIteration {

	// assumption that size is going to be 3^k +1 from start to end
	public void iterate(char str[], int start, int end) {
		int len = end - start + 1;
		int power = 1;
		while (power < len) {
			int index = power;
			int newIndex = -1;
			char temp = str[start + index];
			char temp1;
			while (newIndex != power) {
				if (index % 2 == 0) { // if old index is even
					newIndex = index / 2;
				} else { // else if old index is odd
					newIndex = len / 2 + index / 2;
				}
				temp1 = str[start + newIndex];
				str[start + newIndex] = temp;
				temp = temp1;
				index = newIndex;
			}
			power = power * 3;
		}
	}

	public static void main(String args[]) {
		String str = "1a2b3c4d5e6f7g8h9iAjBkClDmEn";
		char[] str1 = str.toCharArray();
		CycleLeaderIteration cli = new CycleLeaderIteration();
		cli.iterate(str1, 0, str1.length);
		for (char ch : str1) {
			System.out.print(ch + " ");
		}
	}

}
