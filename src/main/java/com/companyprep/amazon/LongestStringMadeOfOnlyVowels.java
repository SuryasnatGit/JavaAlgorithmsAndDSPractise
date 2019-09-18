package com.companyprep.amazon;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given with a string . Your task is to remove atmost two substrings of any length from the given string such
 * that the remaining string contains vowels('a','e','i','o','u') only. Your aim is the maximise the length of the
 * remaining string. Output the length of remaining string after removal of atmost two substrings. NOTE: The answer may
 * be 0, i.e. removing the entire string.
 * 
 * Sample Input :
 * 
 * earthproblem
 * 
 * letsgosomewhere
 * 
 * Sample Output
 * 
 * 3
 * 
 * 2 Category : Hard
 *
 */
public class LongestStringMadeOfOnlyVowels {

	public static int longestVowelString(String s) {
		Set<Character> map = new HashSet<>();
		map.add('a');
		map.add('e');
		map.add('i');
		map.add('o');
		map.add('u');
		int s1 = 0, s2 = 0, s3 = 0;
		int i = 0, len = s.length();
		while (i < len && map.contains(s.charAt(i)))
			i++;
		s1 = i > 0 ? i : s1;
		if (s1 == len)
			return s1;
		int j = len - 1;
		while (j >= 0 && map.contains(s.charAt(j)))
			j--;
		s2 = j < (len - 1) ? (len - j - 1) : s2;
		for (; i <= j;) {
			int lenT = 0;
			while (i <= j && !map.contains(s.charAt(i)))
				i++;
			while (i <= j && map.contains(s.charAt(i))) {
				lenT++;
				i++;
			}
			s3 = lenT > s3 ? lenT : s3;
		}
		s1 = s1 > s2 ? (s1 + (s2 > s3 ? s2 : s3)) : (s2 + (s1 > s3 ? s1 : s3));
		return s1;
	}
}
