package com.hackerrank;

import java.util.HashSet;
import java.util.Set;

public class StringManipulation {

	/**
	 * Problem 1 :
	 * 
	 * A string is said to be a special string if either of two conditions is met:
	 * 
	 * All of the characters are the same, e.g. aaa.
	 * 
	 * All characters except the middle one are the same, e.g. aadaa.
	 * 
	 * A special substring is any substring of a string which meets one of those criteria. Given a string, determine how
	 * many special substrings can be formed from it.
	 * 
	 * For example, given the string s=mnonopoo, we have the following special substrings:
	 * m,n,o,n,o,p,o,o,non,ono,opo,oo.
	 * 
	 * Function Description
	 * 
	 * Complete the substrCount function in the editor below. It should return an integer representing the number of
	 * special substrings that can be formed from the given string.
	 * 
	 * substrCount has the following parameter(s):
	 * 
	 * n: an integer, the length of string s
	 * 
	 * s: a string
	 * 
	 * Input Format
	 * 
	 * The first line contains an integer, n, the length of s. The second line contains the string s.
	 * 
	 * Output Format
	 * 
	 * Print a single line containing the count of total special substrings.
	 * 
	 * Sample Input 0
	 * 
	 * 5 asasd
	 * 
	 * Sample Output 0
	 * 
	 * 7
	 * 
	 * Explanation 0
	 * 
	 * The special palindromic substrings of s=asasd are a,s,a,s,d,asa,sas
	 * 
	 * Sample Input 1
	 * 
	 * 7 abcbaba
	 * 
	 * Sample Output 1
	 * 
	 * 10
	 * 
	 * Explanation 1
	 * 
	 * The special palindromic substrings of s=abcbaba are a,b,c,b,a,b,a,bab,aba,bcb
	 * 
	 * Sample Input 2
	 * 
	 * 4 aaaa
	 * 
	 * Sample Output 2
	 * 
	 * 10
	 * 
	 * Explanation 2
	 * 
	 * The special palindromic substrings of s=aaaa are a,a,a,a,aa,aa,aa,aaa,aaa,aaaa
	 * 
	 * 
	 */
	public long specialPalindromicSubstringsCount(int n, String s) {

		if (s == null || s.length() == 0) {
			return 0;
		}

		int count = 0;

		for (int i = 0; i < n; i++) {
			count += countPalindromicSubstring(s, i, i);
			count += countPalindromicSubstring(s, i, i + 1);
		}

		return count;
	}

	private int countPalindromicSubstring(String s, int i, int j) {
		int count = 0;
		Set<Character> set = new HashSet<Character>();

		while (i >= 0 && j < s.length()) {
			if (s.charAt(i) == s.charAt(j)) { // expand outwards
				set.add(s.charAt(i));
				set.add(s.charAt(j));
				if (set.size() <= 2) {
					count++;
				}
				i--;
				j++;
			} else {
				break;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		StringManipulation sm = new StringManipulation();
		System.out.println(sm.specialPalindromicSubstringsCount(5, "asasd")); // 7
		System.out.println(sm.specialPalindromicSubstringsCount(7, "abcbaba")); // 10
		System.out.println(sm.specialPalindromicSubstringsCount(8, "mnonopoo")); // 12
	}
}
