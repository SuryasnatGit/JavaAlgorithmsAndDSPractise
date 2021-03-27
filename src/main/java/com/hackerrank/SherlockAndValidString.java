package com.hackerrank;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Sherlock considers a string to be valid if all characters of the string appear the same number of times. It is also
 * valid if he can remove just 1 character at index in the string, and the remaining characters will occur the same
 * number of times. Given a string , determine if it is valid. If so, return YES, otherwise return NO.
 * 
 * Example
 * 
 * s=abc . This is a valid string because frequencies are a=1,b=1,c=1.
 * 
 * s=abcc. This is a valid string because we can remove one and have of each character in the remaining string.
 * 
 * s=abccc. This string is not valid as we can only remove 1 occurrence of c. That leaves character frequencies of
 * a=1,b=1,c=2.
 * 
 * Function Description
 * 
 * Complete the isValid function in the editor below.
 * 
 * isValid has the following parameter(s):
 * 
 * string s: a string
 * 
 * Returns
 * 
 * string: either YES or NO
 * 
 * Sample Input 0
 * 
 * aabbcd
 * 
 * Sample Output 0
 * 
 * NO
 * 
 * Explanation 0
 * 
 * Given , we would need to remove two characters, both c and d aabb or a and b abcd, to make it valid. We are limited
 * to removing only one character, so is invalid.
 * 
 * Sample Input 1
 * 
 * aabbccddeefghi
 * 
 * Sample Output 1
 * 
 * NO
 * 
 * Explanation 1
 * 
 * Frequency counts for the letters are as follows:
 * 
 * {'a': 2, 'b': 2, 'c': 2, 'd': 2, 'e': 2, 'f': 1, 'g': 1, 'h': 1, 'i': 1}
 * 
 * There are two ways to make the valid string:
 * 
 * Remove characters with a frequency of : . Remove characters of frequency : . Neither of these is an option.
 * 
 * Sample Input 2
 * 
 * abcdefghhgfedecba Sample Output 2
 * 
 * YES Explanation 2
 * 
 * All characters occur twice except for e which occurs 3 times. We can delete one instance of to have a valid string
 * 
 *
 */
public class SherlockAndValidString {

	/**
	 * Approach:
	 * 
	 * If there are more than two different frequencies -> NO
	 * 
	 * if 1 frequency --> YES
	 * 
	 * if 2 frequency
	 * 
	 * if 1 occurs only once and frequency is 1 --> YES
	 * 
	 * else
	 * 
	 * if their difference 1 and one has frequency 1 --> YES
	 * 
	 * else
	 * 
	 * --> NO
	 * 
	 */
	public String isValid(String s) {

		Map<Character, Integer> frequencyMap = new HashMap<Character, Integer>();
		for (char ch : s.toCharArray()) {
			frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
		}

		// collect frequency values in set
		Set<Integer> set = new HashSet<Integer>(frequencyMap.values());

		if (set.size() > 2) {
			return "NO";
		}

		if (set.size() == 1) {
			return "YES";
		} else {
			int freq1 = 0;
			int freq2 = 0;
			int freq1Count = 0;
			int freq2Count = 0;

			Iterator<Integer> iterator = set.iterator();
			while (iterator.hasNext()) {
				freq1 = iterator.next();
				freq2 = iterator.next();
			}

			for (int value : frequencyMap.values()) {
				if (value == freq1) {
					freq1Count++;
				}
				if (value == freq2) {
					freq2Count++;
				}
			}

			if ((freq1 == 1 && freq1Count == 1) || (freq2 == 1 && freq2Count == 1)) {
				return "YES";
			} else if (Math.abs(freq1 - freq2) == 1 && (freq1Count == 1 || freq2Count == 1)) {
				return "YES";
			}
		}

		return "NO";

	}

	public static void main(String[] args) {
		SherlockAndValidString sh = new SherlockAndValidString();
		System.out.println(sh.isValid("aabb"));
		System.out.println(sh.isValid("aabbc"));
		System.out.println(sh.isValid("aabbcd"));
		System.out.println(sh.isValid("aaaabbbbcd"));
	}
}
