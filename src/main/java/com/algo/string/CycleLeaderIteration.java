package com.algo.string;

/**
 * 
 * 
 * The cycle leader iteration algorithm is an algorithm for shuffling an array by moving all even-numbered entries to
 * the front and all odd-numbered entries to the back while preserving their relative order. For example, given this
 * input:
 * 
 * Things to note : size of input array will always be of the form 3^k+1 ie,2, 4, 10, 28, 82..
 * 
 * time complexity - O(n). Each item in a cycle is shifted at most once. Thus time complexity of the cycle leader
 * algorithm is O(n). The time complexity of the reverse operation is O(n).
 * 
 * Explanation : https://www.geeksforgeeks.org/an-in-place-algorithm-for-string-transformation/
 * 
 * Category : Hard
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
			while (newIndex != power) {
				if (index % 2 == 0) { // if old index is even
					newIndex = index / 2;
				} else { // else if old index is odd
					newIndex = len / 2 + index / 2;
				}
				char temp1 = str[start + newIndex];
				str[start + newIndex] = temp;
				temp = temp1;
				index = newIndex;
			}
			power = power * 3;
		}
	}

	public static void main(String args[]) {
		String str = "1A2B3C4D5E";
		char[] str1 = str.toCharArray();
		CycleLeaderIteration cli = new CycleLeaderIteration();
		cli.iterate(str1, 0, str1.length);
		System.out.println(str1);
	}

}
