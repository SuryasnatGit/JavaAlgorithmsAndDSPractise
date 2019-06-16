
package com.algo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Remove consecutive duplicate characters e.g AABBCDDAAB -> ABCDAB ABBBCCD -> ABCD Test cases Empty string all unique
 * all duplicates duplicates at certain different places
 */
public class RemoveConsecutiveDuplicate {

	/**
	 * worst case complexity - O(n^2).
	 * 
	 * @param input
	 * @return
	 */
	// public void remDup(char[] input){
	// if(input == null || input.length == 0) return;
	// int c = 0;
	// removeDuplicates_recursive(input, c);
	// }
	//
	// public void removeDuplicates_recursive(char[] input, int c){
	//
	// // if adjacent chars are same
	// if(input[0] == input[1]){
	// // shift char by 1 left
	// int i = 0;
	// while(input[i] != ' '){
	// input[i] = input[i+1];
	// i++;
	// }
	//
	// removeDuplicates_recursive(input,);
	// }
	//
	// // if adjacent chars are not same, check from next char
	// removeDuplicates_recursive(new String(input).substring(beginIndex))
	// }

	/**
	 * Time Complexity : O(n) Auxiliary Space : O(1)
	 * 
	 * @param input
	 * @return
	 */
	public List<Character> removeDuplicates_iterative(char input[]) {
		int slow = 0;
		int fast = 0;
		List<Character> result = new ArrayList<>();
		while (fast < input.length) {
			while (fast < input.length && input[slow] == input[fast]) {
				fast++;
			}
			result.add(input[slow]);
			slow = fast;
		}
		return result;
	}

	public static void main(String args[]) {
		String str = "AAABBCCDDDEFGHAABB";
		char input[] = str.toCharArray();
		RemoveConsecutiveDuplicate rcd = new RemoveConsecutiveDuplicate();
		System.out.println(rcd.removeDuplicates_iterative(input));
	}
}
