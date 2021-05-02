package com.companyprep;

/**
 * Given a string S of lowercase letters.
 * 
 * You need to find as many substrings from the S that meets the following conditions.
 * 
 * no overlap among substrings.<br/>
 * 
 * one letter can only exist in one substring. For every letter C in a substring, all occurrences of the letter C also
 * need to be in that substring.<br/>
 * 
 * you want to find as many as substrings possible.<br/>
 * 
 * If there are two solutions with the same number of substrings, return the one with a smaller total length.<br/>
 * 
 * Input : The input consists of one argument:
 * 
 * S: a string of lowercase letters
 * 
 * Output : Return substrings as a list.
 * 
 * Examples
 * 
 * Example 1:
 * 
 * Input:
 * 
 * S = "baddacxb"
 * 
 * Output: ["bb", "a", "c", "x"]
 * 
 * Example 2:
 * 
 * Input:
 * 
 * S = "bbeadcxede"
 * 
 * Output: ["dd", "c", "x"]
 * 
 * Explanation:
 * 
 * Here ["adda", "c", "x"] is not considered a correct answer. Because the total length of ["dd","c", "x"] is 4, which
 * is smaller than the total length of ["adda", "c", "x"], which is 6.
 *
 * TODO : check further
 */
public class FindSubstrings {

}
