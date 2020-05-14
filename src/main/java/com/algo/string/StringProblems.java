package com.algo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class StringProblems {

	public static void main(String[] args) {
		StringProblems sp = new StringProblems();
		// sp.stringPermutations("BOLD");
		// System.out.println(p.isUniqueString("surya"));
		// System.out.println(p.isUniqueString("suryasnat"));
		// System.out.println(p.isUniqueString("tableta"));

		// System.out.println(p.isUniqueString1("surya"));
		// System.out.println(p.isUniqueString3("a"));
		// System.out.println(p.isUniqueString1("table"));

		// p.findStringPermutation1("table", "blate");
		// p.findStringPermutation1("man", "nam");
		// System.out.println(p.findStringPermutation2("table", "blate"));
		// System.out.println(p.findStringPermutation2("man", "nan"));
		// System.out.println(p.findStringPermutation2("life", "file"));
		// System.out.println(p.isIsomorphicStrings("att", "pee"));
		// System.out.println(p.isIsomorphicStrings("att", "bad"));
		char[] arr = { 'm', 'y', ' ', 't', ' ' };

		// System.out.println(sp.urlifyString(new String("my name is "), 10));
		// System.out.println(sp.asciiToInt("-2147483"));
		// // System.out.println(sp.urlifyString("Mr John Smith ", 20));
		// System.out.println(sp.oneEdit("abba", "bbc"));
		// System.out.println(sp.oneEdit("abba", "bba"));

		// System.out.println(sp.oneEdit_1("apple", "appl"));
		// System.out.println(sp.oneEdit_1("apple", "applm"));
		// System.out.println(sp.oneEdit_1("appl", "apple"));
		// System.out.println(sp.oneEdit_1("apple", "appless"));
		// System.out.println(sp.compressedString("apple"));
		// System.out.println(sp.compressedString("aabcccccaaa"));
		// System.out.println(sp.compressedString("ABCDEFFFFFFFF"));
		// System.out.println(sp.compressedString("ABCDEF"));
		// System.out.println(sp.leftRotation("surya", 2));
		// System.out.println(sp.leftRotation("apple", 3));
		// System.out.println(sp.rightRotation("surya", 2));
		// System.out.println(sp.isUniqueString_usingBits("abcdzz"));
		// System.out.println(sp.isUniqueString_sort("abc"));
		// System.out.println(sp.isUniqueString_sort("abcc"));
		// System.out.println(sp.stringRotation("bat", "abt"));
		//
		// System.out.println(sp.compressedString("apple"));
		// System.out.println(sp.compressedString("aabcccccaaa"));
		// System.out.println(sp.compressedString("ABCDEFFFFFFFF"));
		// System.out.println(sp.compressedString("ABCDEF"));
		// System.out.println(sp.leftRotation("surya", 2));
		// System.out.println(sp.leftRotation("apple", 3));
		// System.out.println(sp.rightRotation("surya", 2));
		// System.out.println(sp.palindromeIndex("bobj"));
		// System.out.println(sp.palindromeIndex("bob"));
		// System.out.println(sp.palindromeIndex("abob"));
		// System.out.println(sp.palindromeIndex("blob"));

		// System.out.println(sp.isNumber("+123"));
		// System.out.println(sp.isNumber("123-"));
		// System.out.println(sp.isNumber("+123.0"));
		// System.out.println(sp.isNumber("+123.01"));
		// System.out.println(sp.isNumber("+123.Ea"));
		//
		// System.out.println(sp.missingCharsToMakeStringPanagram("suryasnat"));
		String s = "stop";
		// sp.stringPermutationsBT(s.toCharArray(), 0, s.length());
		sp.stringPermutations("cat").forEach(a -> System.out.println(a));
	}

	/**
	 * Steve has a string, , consisting of lowercase English alphabetic letters. In one operation, he can delete any
	 * pair of adjacent letters with same value. For example, string "aabcc" would become either "aab" or "bcc" after
	 * operation. Steve wants to reduce as much as possible. To do this, he will repeat the above operation as many
	 * times as it can be performed. Help Steve out by finding and printing 's non-reducible form!
	 * 
	 * @param s
	 */
	public void reducedString(String s) {
		System.out.println("input string -->" + s);
		StringBuilder sb = new StringBuilder(s);
		int i = 1; // initial counter
		while (i < sb.length()) {
			if (sb.charAt(i - 1) == sb.charAt(i)) {
				sb.delete(i - 1, i + 1); // alternate would be s = s.substring(0, i - 1) + s.substring(i + 1);
				if (i > 1) {
					i--;
				}
			} else {
				i++;
			}
		}
		if (sb.length() == 0) {
			System.out.println("empty string");
		} else {
			System.out.println("output string -->" + sb.toString());
		}
	}

	public void starcase(int n) {

		StringBuilder sb = new StringBuilder();
		String s = "";
		String sp = "";
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < n - i; j++) {
				sp += " ";
			}
			for (int j = 0; j < i; j++) {
				s = s + "#";

			}
			sb.append(sp).append(s).append("\n");
			s = "";
			sp = "";
		}
		System.out.println(sb.toString());
	}

	/**
	 * Alice wrote a sequence of words in CamelCase as a string of letters, , having the following properties: It is a
	 * concatenation of one or more words consisting of English letters. All letters in the first word are lowercase.
	 * For each of the subsequent words, the first letter is uppercase and rest of the letters are lowercase. Given ,
	 * print the number of words in on a new line.
	 * 
	 * @param input
	 */
	public void camelCase(String input) {
		int wsi = 0;// word start index
		int wei = 0;// word end index
		int count = 0; // count number of words in string
		for (int i = 0; i < input.length(); i++) {
			if (Character.isUpperCase(input.charAt(i))) {
				wei = i - 1;
				System.out.println("word :" + input.substring(wsi, wei + 1));
				count++;
				wsi = i;
			}
		}
		System.out.println("word :" + input.substring(wsi));
		if (!input.isEmpty())
			System.out.println(++count);
	}

	public void marsExploration(String input) {
		String sos = "SOS";
		int c = 0;
		for (int j = 0; j < input.length(); j++) {
			for (int i = 0; i < sos.length(); i++) {
				if (sos.charAt(i) != input.charAt(j))
					c++;
			}
		}
		System.out.println(c);
	}

	private Set<Integer> weightedUniformString(String input) {
		Set<Integer> set = new HashSet<>();
		int weight = 0;
		char prev = ' ';
		for (int i = 0; i < input.length(); i++) {
			char curr = input.charAt(i);
			if (curr != prev)
				weight = 0;
			weight += curr - 'a' + 1;
			set.add(weight);
			prev = curr;
		}
		return set;
	}

	private int separateTheNumbers(String input) {
		if (input.charAt(0) == '0')
			return -1;

		for (int length = 1; length * 2 <= input.length(); length++) {
			int firstNumber = Integer.parseInt(input.substring(0, length));
			StringBuilder sb = new StringBuilder();
			int number = firstNumber;
			while (sb.length() < input.length()) {
				sb.append(number);
				number++;
			}
			if (sb.toString().equals(input))
				return firstNumber;
		}
		return -1;
	}

	/**
	 * Since we are limited to the alphabet which we know to be constant we can come up with a better solution We can
	 * iterate through every character pair which is (26 * 25) / 2 = 325 pairs for each of those iterations we will run
	 * through the string checking if it fits the pattern and return the largest.<br/>
	 * Time Complexity: O(n) <br/>
	 * Space Complexity: O(1)
	 * 
	 * @param input
	 */
	private void twoCharacters(String input) {
		int maxPattern = 0; // keep a running max
		if (input.length() == 0) {
			System.out.println(maxPattern);
			System.exit(0);
		}

		// An unlabeled break statement terminates the innermost switch, for, while, or
		// do-while statement, but a
		// labeled break terminates an outer statement. The following program,
		// BreakWithLabelDemo, is similar to the
		// previous program, but uses nested for loops to search for a value in a
		// two-dimensional array. When the value
		// is found, a labeled break terminates the outer for loop (labeled "search"):

		for (int i = 0; i < 26; i++) {
			nextLetter: // hmm interesting. java label :-)
			for (int j = i + 1; j < 26; j++) {
				char one = (char) ('a' + i);
				char two = (char) ('a' + j);
				char lastSeen = '\u0000'; // null character
				int patternSize = 0;
				for (char letter : input.toCharArray()) {
					if (letter == one || letter == two) {
						if (letter == lastSeen) { // if duplicate found
							continue nextLetter;
						}
						// if not a duplicate
						patternSize++;
						lastSeen = letter;
					}
				} // end of loop for string input
				maxPattern = (patternSize > maxPattern) ? patternSize : maxPattern;
			}
		}
		System.out.println("maxPattern :" + maxPattern);
	}

	/**
	 * You have two strings, and . Find a string, , such that:
	 * 
	 * can be expressed as where is a non-empty substring of and is a non-empty substring of . is a palindromic string.
	 * The length of is as long as possible. For each of the pairs of strings ( and ) received as input, find and print
	 * string on a new line. If you're able to form more than one valid string , print whichever one comes first
	 * alphabetically. If there is no valid answer, print instead.<br/>
	 * https://www.hackerrank.com/challenges/challenging-palindromes/problem
	 * 
	 * <br/>
	 * brute force solution. but this fails for very long strings.. WIP
	 * 
	 * @param a
	 * @param b
	 */
	private void challengingPalindromes(String a, String b) {
		int la = a.length();
		List<String> ssa = new ArrayList<>(); // to contain all substrings of a
		int lb = b.length();
		List<String> ssb = new ArrayList<>(); // to contain all substrings of b
		// find each substring of a. O(A^2)
		int index = 0;
		for (int i = 0; i < la; i++) {
			for (int j = i + 1; j <= la; j++) {
				ssa.add(index++, a.substring(i, j));
			}
		}
		index = 0;
		// System.out.println(ssa);
		// find each substring of b. O(B^2)
		for (int i = 0; i < lb; i++) {
			for (int j = i + 1; j <= lb; j++) {
				ssb.add(index++, b.substring(i, j));
			}
		}
		// System.out.println(ssb);
		// combine both lists to find the largest palindrome string
		int maxLength = 0;
		List<String> res = new ArrayList<>();
		for (String sa : ssa) {
			for (String sb : ssb) {
				String s = sa + sb;
				// System.out.println(s);
				if (isPalindrome(s) && s.length() >= maxLength) {
					maxLength = s.length();
					res.add(s);
				}
			}
		}
		// System.out.println(res);
		res.sort(new Comparator<String>() {
			// reverse comparison to keep the longest palindrome string in the first element
			// in the list
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() > o2.length())
					return -1;
				else if (o1.length() < o2.length())
					return 1;
				else
					return o1.compareTo(o2);
			}
		});
		// System.out.println(res);
		System.out.println((!res.isEmpty() && res.get(0) != null) ? res.get(0) : -1);
	}

	private boolean isPalindrome(String s) {
		int l = s.length();
		// empty string considered as palindrome
		if (l == 0)
			return true;

		return isPalindromeRec(s, 0, l - 1);
	}

	private boolean isPalindromeRec(String s, int start, int end) {
		// if there is only 1 char
		if (start == end)
			return true;

		// if first and last char do not match
		if (s.charAt(start) != s.charAt(end))
			return false;

		// if there are more then 2 characters check if middle substring is also
		// palindrome or not.
		if (start < end + 1)
			return isPalindromeRec(s, start + 1, end - 1);

		return true;
	}

	/**
	 * Given a string S, Check if characters of the given string can be rearranged to form a palindrome. For example
	 * characters of geeksogeeks can be rearranged to form a palindrome geeksoskeeg, but characters of geeksforgeeks
	 * cannot be rearranged to form a palindrome.
	 * 
	 * @param s
	 * @return
	 */
	public boolean isAnagramPalindrome(String s) {
		Set<Character> set = new HashSet<>();
		for (char c : s.toCharArray()) {
			if (set.contains(c))
				set.remove(c);
			else
				set.add(c);
		}

		return set.size() > 1 ? false : true;
	}

	// public void numberSeparator(String input){
	// for(char c : input.toCharArray()){
	// Integer.parseInt(c);
	// }
	// int length = input.length();
	// for(int i=0;i<length;i++){
	// input.substring(i, endIndex)
	// }
	// }
	//

	private int count = 0;

	/**
	 * Analyzing the Time Complexity :
	 * 
	 * 1. How many times does function perm get called in its base case? As we can understand from the recursion
	 * explained above that for a string of length 3 it is printing 6 permutations which is actually 3!. This is because
	 * if it needs to generate permutation, it is needed to pick characters for each slot. If there are 3 characters in
	 * our string, in the first slot, there are 3 choices, 2 choices for the next slot (for each of 3 choices earlier,
	 * i.e multiplication and not addition) and so on. This tells that there are n! permutations being printed in the
	 * base case which is what is shown in the image.
	 * 
	 * 2. How many times does function perm get called before its base case? Consider that lines 9 through 12 are hit n
	 * number of times. Therefore, there will be no more than (n * n!) function calls.
	 * 
	 * 3. How long does each function call take? Since, each character of string prefix needs to be printed, thus
	 * executing line 7 will take O(n) time. Line 10 and line 11 will also take O(n) time combined due to string
	 * concatenation, as sum of rem, prefix and str.charAt(i) will always be n. Each function call therefore corresponds
	 * to O(n) work.
	 * 
	 * 4. What is the total runtime? Calling perm O(n * n!) times (as an upper bound) and each call takes O(n) time, the
	 * total runtime will not exceed O(n^2 * n!).
	 * 
	 * @param str
	 */
	public List<String> stringPermutations(String str) {
		List<String> res = new ArrayList<>();
		permutations(str, "", res);
		return res;
	}

	private void permutations(String remainder, String prefix, List<String> res) {
		if (remainder.length() == 0) {
			res.add(prefix);
			return;
		} else {
			for (int i = 0; i < remainder.length(); i++) {
				permutations(remainder.substring(0, i) + remainder.substring(i + 1, remainder.length()),
						prefix + remainder.charAt(i), res);
			}
		}
	}

	/**
	 * <<<<<<< HEAD String permutation using back tracking
	 * 
	 * @param s
	 *            input string
	 * @param i
	 *            count
	 * @param n
	 *            length of string
	 */
	public void stringPermutationsBT(char[] arr, int i, int n) {
		// base case. if count reached the end of string
		if (i == n - 1) {
			System.out.println(arr);
			return;
		}

		for (int j = i; j < n; j++) {
			swap(arr, i, j);
			stringPermutationsBT(arr, i + 1, n);
			// backtrack to restore original position
			swap(arr, i, j);
		}
	}

	private void swap(char[] ar, int i, int j) {
		char t = ar[i];
		ar[i] = ar[j];
		ar[j] = t;
	}

	/**
	 * ======= >>>>>>> 2c6c487b34697f264b17a923751408a3627d9918 Algorithm to find if a string has unique characters.
	 * Time complexity - O(N) as hashset operations is constant time O(1). space complexity - O(N)
	 * 
	 * @param s
	 * @return
	 */
	public boolean isUniqueString(String s) {
		Set<Character> charSet = new HashSet<>();
		char[] charArray = s.toCharArray();
		for (char ch : charArray) {
			if (charSet.contains(ch))
				return false;
			else
				charSet.add(ch);
		}
		return true;
	}

	/**
	 * Algorithm to find if a string has unique characters using a temp array. Time complexity is O(N^2) for worst case
	 * , where N is the length of the string. space complexity - O(N)
	 * 
	 * @param s
	 * @return
	 */
	public boolean isUniqueString1(String s) {
		int l = s.length();
		// temp array with length l as length of string
		char[] tempArr = new char[l];
		for (int i = 0; i < l; i++) {
			char charAt = s.charAt(i);
			// for each iteration check if char is present in the temp array.
			// if so return false
			for (int j = 0; j < l; j++) {
				System.out.println("charAt -->" + charAt + " . tempArr -->" + Arrays.toString(tempArr));
				if (charAt == tempArr[j]) {
					return false;
				}
			}
			// if not then add char to temp arr
			tempArr[i] = charAt;
		}
		// when we reach this point we know that string is unique
		return true;
	}

	/**
	 * Time complexity - O(N^2) as for each iteration of N, the indexOf check runs N times. If extra space is not
	 * allowed.
	 * 
	 * @param s
	 * @return
	 */
	public boolean isUniqueString2(String s) {
		boolean isUnique = false;
		for (char c : s.toCharArray()) {
			System.out.println("s.indexOf(c) -->" + s.indexOf(c) + " . s.lastIndexOf(c)-->" + s.lastIndexOf(c));
			if (s.indexOf(c) == s.lastIndexOf(c)) {
				isUnique = true;
			} else {
				isUnique = false;
				break;
			}
		}
		return isUnique;
	}

	/**
	 * Time complexity - O(N). space complexity - O(C) where C is a constant - 256
	 * 
	 * @param s
	 * @return
	 */
	public boolean isUniqueString3(String s) {
		// boolean array representing each character in set
		boolean[] arr = new boolean[256];
		for (int i = 0; i < s.length(); i++) {
			// store the char as int to determine the ASCII value
			int pos = s.charAt(i);
			// if the boolean array element at the pos index returns true then there is a duplicate.
			if (arr[pos])
				return false;
			// else set the array element at pos index to true
			arr[pos] = true;
		}
		return true;
	}

	/**
	 * time complexity - O(N) if extra space is not allowed
	 * 
	 * @param s
	 * @return
	 */
	public boolean isUniqueString_usingBits(String s) {
		int checker = 0;
		for (int i = 0; i < s.length(); i++) {
			int val = s.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0)
				return false;
			checker |= (1 << val); // inclusive OR (|)
		}
		return true;
	}

	/**
	 * If modification is allowed. O(n log n) + O(n)
	 * 
	 * @return
	 */
	public boolean isUniqueString_sort(String s) {
		char[] charArray = s.toCharArray();
		Arrays.sort(charArray); // O(n log n)
		for (int i = 0; i < charArray.length - 1; i++) { // O(n)
			if (charArray[i] == charArray[i + 1])
				return false;
		}
		return true;
	}

	/**
	 * two strings, write a method to decide if one is a permutation of the other.
	 * 
	 * CTCI - 1.2 sort the strings and compare it. complexity - O(NlogN)
	 * 
	 * @param input1
	 * @param input2
	 */
	public boolean findStringPermutation1(String input1, String input2) {
		if (input1.length() != input2.length())
			return false;
		char[] inputchar1 = input1.toCharArray();
		char[] inputchar2 = input2.toCharArray();
		Arrays.sort(inputchar1);
		Arrays.sort(inputchar2);
		String sortInput1 = new String(inputchar1);
		String sortInput2 = new String(inputchar2);
		return sortInput1.equals(sortInput2);
	}

	/**
	 * Using hashtable. complexity - O(N). space complexity - O(N)
	 * 
	 * @param input1
	 * @param input2
	 */
	public boolean findStringPermutation2(String input1, String input2) {
		if (input1.length() != input2.length())
			return false;
		Map<Character, Integer> map = new HashMap<>();
		for (char ch : input1.toCharArray()) {
			if (map.containsKey(ch)) {
				Integer count = map.get(ch) + 1;
				map.put(ch, count);
			} else {
				map.put(ch, 1);
			}
		}

		for (char ch : input2.toCharArray()) {
			if (map.containsKey(ch)) {
				Integer count = map.get(ch) - 1;
				map.put(ch, count);
			} else {
				return false;
			}
		}

		for (Character ch : map.keySet()) {
			if (map.get(ch) != 0)
				return false;
		}
		return true;
	}

	/**
	 * Solution #2: Check if the two strings have identical character counts. We can also use the definition of a
	 * permutation-two words with the same character counts-to implement this algorithm. We simply iterate through this
	 * code, counting how many times each character appears. Then, afterwards, we compare the two arrays.
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean checkPermutation(String s, String t) {
		if (s.length() != t.length())
			return false;

		// assume character set is ascii
		int[] count = new int[128];

		for (char c : s.toCharArray()) {
			count[c]++;
		}
		for (char c : t.toCharArray()) {
			count[c]--;
			if (count[c] < 0)
				return false;
		}
		return true;
	}

	/**
	 * Given two strings s and t, determine if they are isomorphic. Two strings are isomorphic if the characters in s
	 * can be replaced to get t.
	 * 
	 * For example,"egg" and "add" are isomorphic, "foo" and "bar" are not. complexity - O(n)
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean isIsomorphicStrings(String s1, String s2) {
		if (s1 == null || s2 == null)
			return false;
		if (s1.length() != s2.length())
			return false;
		Map<Character, Character> charMap = new HashMap<>();
		for (int i = 0; i < s1.length(); i++) {
			char ch1 = s1.charAt(i);
			char ch2 = s2.charAt(i);
			if (charMap.containsKey(ch1)) {
				if (!charMap.get(ch1).equals(ch2))
					return false;
			} else {
				if (charMap.containsValue(ch2))
					return false;
				charMap.put(ch1, ch2);
			}
		}
		return true;
	}

	/**
	 * CTCI - 1.3
	 * 
	 * Write a method to replace all spaces in a string with '%20': You may assume that the string has sufficient space
	 * at the end to hold the additional characters, and that you are given the "true" length of the string. (Note: if
	 * implementing in Java, please use a character array so that you can perform this operation in place.)
	 * 
	 * Examples:
	 * 
	 * Input : "Mr John Smith", 13 Output : Mr%20John%20Smith
	 * 
	 * @param input
	 * @param trueLength
	 * @return
	 */
	public String urlifyString(String input, int trueLength) {
		char[] charArray = input.toCharArray();
		int spaceLength = 0;

		// calculate space length
		for (int i = 0; i < trueLength; i++) {
			if (charArray[i] == ' ')
				spaceLength++;
		}

		int newLength = trueLength + 2 * spaceLength;

		for (int i = trueLength - 1, j = newLength - 1; i >= 0; i--) {
			char c = charArray[i];
			if (c == ' ') {
				charArray[j--] = '0';
				charArray[j--] = '2';
				charArray[j--] = '%';
			} else {
				charArray[j--] = c;
			}
		}

		return new String(charArray);
	}

	/**
	 * https://leetcode.com/problems/string-to-integer-atoi/
	 * 
	 * Implement atoi to convert a string to an integer.
	 * 
	 * Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask
	 * yourself what are the possible input cases.
	 * 
	 * Analysis
	 * 
	 * The following cases should be considered for this problem:
	 * 
	 * 1. null or empty string 2. white spaces 3. +/- sign 4. calculate real value 5. handle min & max
	 * 
	 * @param input
	 * @return
	 */
	public int asciiToInt(String input) {
		System.out.println("input :" + input);
		if (input == null || input.length() < 1)
			return 0;

		// trim white space
		input = input.trim();

		// check negative or positive
		char flag = '+';
		int i = 0;
		if (input.charAt(0) == '-') {
			flag = '-';
			i++;
		} else if (input.charAt(0) == '+') {
			i++;
		}

		// calculate value
		double res = 0;
		while (input.length() > i && input.charAt(i) >= '0' && input.charAt(i) <= '9') {
			res = res * 10 + (input.charAt(i) - '0');
			i++;
		}

		// check sign
		if (flag == '-')
			res = -res;

		// check int limits
		if (res > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		else if (res < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;

		return (int) res;
	}

	/**
	 * We are playing the Guess Game. The game is as follows: I pick a number from 1 to n. You have to guess which
	 * number I picked. Every time you guess wrong, I'll tell you whether the number is higher or lower. You call a
	 * pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0): <br>
	 * -1 : My number is lower <br>
	 * 1 : My number is higher <br>
	 * 0 : Congrats! You got it! <br>
	 * Binary search problem
	 * 
	 * @param n
	 * @return
	 */
	public int guessNumber(int n) {
		int low = 1;
		int high = n;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			int result = guess(mid);
			if (result == 0)
				return mid;
			else if (result == 1)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}

	/**
	 * Validate if a given string is numeric. Some examples: "0" -> true "0.1" -> true "abc" -> false.
	 * 
	 * A string could be divided into these four substrings in the order from left to right: s1. Leading whitespaces
	 * (optional). s2. Plus (+) or minus (â€“) sign (optional). s3. Number. s4. Optional trailing whitespaces
	 * (optional). We ignore s1, s2, s4 and evaluate whether s3 is a valid number. We realize that a number could either
	 * be a whole number or a decimal number. For a whole number, it is easy: We evaluate whether s3 contains only
	 * digits and we are done.
	 * 
	 * On the other hand, a decimal number could be further divided into three parts: a. Integer part b. Decimal point
	 * c. Fractional part The integer and fractional parts contain only digits. For example, the number â€œ3.64â€ has
	 * integer part (3) and fractional part (64). Both of them are optional, but at least one of them must present. For
	 * example, a single dot â€˜.â€™ is not a valid number, but â€œ1.â€, â€œ.1â€, and â€œ1.0â€ are all valid. Please
	 * note that â€œ1.â€ is valid because it implies â€œ1.0â€.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isNumber(String input) {
		int i = 0;
		int n = input.length();

		// 1. leading whitespace check
		while (i < n && Character.isWhitespace(input.charAt(i)))
			i++;
		// 2. sign check
		if (i < n && (input.charAt(i) == '+' || input.charAt(i) == '-'))
			i++;
		boolean isNumeric = false;
		// 3. number check
		while (i < n && Character.isDigit(input.charAt(i))) {
			i++;
			isNumeric = true;
		}
		// 4. decimal check
		if (i < n && input.charAt(i) == '.') {
			i++;
			while (i < n && Character.isDigit(input.charAt(i))) {
				i++;
				isNumeric = true;
			}
		}

		// if we want to extend this further by adding exponential part check, then add
		// this code.
		if (isNumeric && i < n && input.charAt(i) == 'e') {
			i++;
			isNumeric = false;
			if (i < n && (input.charAt(i) == '+' || input.charAt(i) == '-'))
				i++;
			while (i < n && Character.isDigit(input.charAt(i))) {
				i++;
				isNumeric = true;
			}
		}

		// 5. trailing whitespace check
		while (i < n && Character.isWhitespace(input.charAt(i)))
			i++;

		return (i == n && isNumeric);
	}

	private int guess(int mid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object firstNonRepeatingCharInString(String input) {
		Hashtable hashChar = new Hashtable();

		int j, strLength;
		Character chr;
		Object oneTime = new Object();
		Object twoTimes = new Object();

		strLength = input.length();

		for (j = 0; j < strLength; j++) {
			chr = new Character(input.charAt(j));
			Object o = hashChar.get(chr);

			/*
			 * if there is no entry for that particular character, then insert the oneTime flag:
			 */
			if (o == null) {
				hashChar.put(chr, oneTime);
			}
			/*
			
			*/
			else if (o == oneTime) {
				hashChar.put(chr, twoTimes);
			}
		}

		/*
		 * go through hashtable and search for the first nonrepeated char:
		 */

		for (j = 0; j < strLength; j++) {
			chr = new Character(input.charAt(j));
			if (hashChar.get(chr) == oneTime)
				return chr;
		}
		/*
		 * this only returns null if the loop above doesn't find a nonrepeated character in the hashtable
		 */
		return null;
	}

	boolean stringsRearrangement(String[] inputArray) {
		System.out.println("before rearrangemnt -- " + Arrays.asList(inputArray));
		Arrays.sort(inputArray);
		System.out.println("after rearrangemnt -- " + Arrays.asList(inputArray));
		boolean res = false;
		for (int i = 0; i < inputArray.length; i++) {
			int diff = 0;
			if (i + 1 == inputArray.length) {
				res = false;
			}
			String s1 = inputArray[i];
			String s2 = inputArray[i + 1];
			for (int j = 0; j < s1.length(); j++) {
				if (s1.charAt(j) != s2.charAt(j)) {
					diff++;
					if (diff > 1)
						res = false;
				}
			}
			res = diff == 1 ? true : false;
		}
		return res;
	}

	/**
	 * reverse words in a string while keeping the space between the words consistent
	 * 
	 * @param input
	 * @return
	 */
	public String reverseWordByWord(String input) {
		Pattern ws = Pattern.compile("\\b");
		String[] wssplit = ws.split(input);
		String s = "";
		for (int i = wssplit.length - 1; i >= 0; i--) {
			s += wssplit[i];
		}
		return s;
	}

	/**
	 * Given a string check if it is Pangram or not. A pangram is a sentence containing every letter in the English
	 * Alphabet.
	 * 
	 * @param input
	 */
	public void pangram(String input) {

		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution.
		 */
		String s = "abcdefghijklmnopqrstuvwxyz";
		List<Character> list = new ArrayList<>();
		for (char c : s.toCharArray()) {
			list.add(c);
		}
		Set<Character> set = new TreeSet<>();
		Scanner in = new Scanner(System.in);
		String inputStr = in.nextLine();
		for (char ch : inputStr.toCharArray()) {
			if (ch != ' ') {
				Character valueOf = Character.valueOf(Character.toLowerCase(ch));
				set.add(valueOf);
			}
		}
		if (set.containsAll(list)) {
			System.out.println("pangram");
		} else {
			System.out.println("not pangram");
		}
	}

	public void pangram1(String input) {
		boolean[] init = new boolean[26];
		for (int i = 0; i < 26; i++) {
			init[i] = false;
		}
		int index = 0;// for index in init array
		char[] charArray = input.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if ('A' <= charArray[i] && charArray[i] <= 'Z')
				index = charArray[i] - 'A';
			if ('a' <= charArray[i] && charArray[i] <= 'z')
				index = charArray[i] - 'a';
			init[index] = true;
		}
		String result = "is pangram";
		for (boolean res : init) {
			if (!res) {
				result = "not pangram";
				break;
			}
		}
		System.out.println(result);
	}

	// time - O(n) SPace - O(1)
	public List<Character> missingCharsToMakeStringPanagram(String input) {
		boolean[] bool = new boolean[26];

		char[] chArr = input.toCharArray();
		for (char ch : chArr) {
			int index = ch - 'a';
			bool[index] = true;
		}

		List<Character> result = new ArrayList<Character>();
		for (int i = 0; i < bool.length; i++) {
			if (!bool[i]) {
				int ch = 'a' + i;
				result.add((char) ch);
			}
		}

		return result;
	}

	/**
	 * There are three types of edits that can be performed on strings: insert a character, remove a character, or
	 * replace a character. Given two strings, write a function to check if they are one edit (or zero edits) away. time
	 * complexity - O(M + N).
	 * 
	 * EXAMPLE pale, ple -) true . pales, pale -) true . pale, bale -) true pale, bae -) false.
	 * 
	 * CTCI - 1.5
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean oneEdit(String s1, String s2) {
		int l1 = s1.length();
		int l2 = s2.length();

		if (Math.abs(l1 - l2) > 1)
			return false;

		int count = 0; // count of edits
		int i = 0, j = 0;

		while (i < l1 && j < l2) {
			// if current characters do not match
			if (s1.charAt(i) != s2.charAt(j)) {
				if (count == 1)
					return false;

				if (l1 > l2)
					i++;
				else if (l1 < l2)
					j++;
				else {
					// if both lengths are same
					i++;
					j++;
				}
				count++;
			} else {
				// if current characters match
				i++;
				j++;
			}
		}
		// if last characters is extra in any string
		if (i < l1 || j < l2)
			count++;

		System.out.println(count);
		return count == 1;
	}

	/**
	 * This is one of those problems where it's helpful to think about the "meaning" of each of these operations. What
	 * does it mean for two strings to be one insertion, replacement, or removal away from each other? Replacement:
	 * Consider two strings, such as bale and pale, that are one replacement away. Yes, that does mean that you could
	 * replace a character in bale to make pale. But more precisely, it means that they are different only in one place.
	 * Insertion: The strings apple and aple are one insertion away. This means that if you compared the strings, they
	 * would be identical-except for a shift at some point in the strings. Removal: The strings apple and aple are also
	 * one removal away, since removal is just the inverse of insertion.
	 * 
	 * complexity - 0 (n) time, where n is the length of the shorter string.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean oneEdit_1(String s1, String s2) {
		if (s1.length() == s2.length())
			return oneEditReplace(s1, s2);
		else if (s1.length() + 1 == s2.length())
			return oneEditInsert(s1, s2);
		else if (s1.length() - 1 == s2.length())
			return oneEditInsert(s2, s1);
		return false;
	}

	private boolean oneEditReplace(String s1, String s2) {
		boolean foundDiff = false;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				if (foundDiff)
					return false; // if subsequent diff occurs then this is not a one edit replace
				foundDiff = true; // set the boolean to true for first occurance
			}
		}
		return true;
	}

	/**
	 * 
	 * @param shortStr
	 *            is the shorter string
	 * @param longStr
	 *            is the longer string
	 * @return
	 */
	private boolean oneEditInsert(String shortStr, String longStr) {
		int index1 = 0;
		int index2 = 0;
		while (index1 < shortStr.length() && index2 < longStr.length()) {
			if (shortStr.charAt(index1) != longStr.charAt(index2)) {
				if (index1 != index2)
					return false;
				index2++;
			} else {
				index1++;
				index2++;
			}
		}
		return true;
	}

	/**
	 * The runtime is O(p + k^2), where p is the size of the original string and k is the number of character sequences.
	 * For example, if the string is aabccdeeaa, then there are six character sequences. It 's slow because string
	 * concatenation operates in O(n^2). Instead of this concatenation we can use StringBuilder
	 * 
	 * CTCI - 1.6
	 * 
	 * @param input
	 * @return
	 */
	public String compressedString_bad(String input) {
		int conseqetiveCount = 0;
		String compressedString = "";
		for (int i = 0; i < input.length(); i++) {
			conseqetiveCount++;
			if (i + 1 >= input.length() || input.charAt(i) != input.charAt(i + 1)) {
				compressedString += input.charAt(i) + conseqetiveCount;
				conseqetiveCount = 0;// reset it
			}
		}
		return (compressedString.length() < input.length()) ? compressedString : input;
	}

	/**
	 * Implement a method to perform basic string compression using the counts of repeated characters. For example, the
	 * string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than the original
	 * string, your method should return the original string. You can assume the string has only uppercase and lowercase
	 * letters (a - z). complexity - O(N)
	 * 
	 * @param input
	 * @return
	 */
	public String compressedString(String input) {
		int count = 1;
		MyStringBuilder sb = new MyStringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			if (i + 1 == input.length()) {
				sb.append(Character.toString(ch)).append(Integer.toString(count));
				break;
			}
			if (ch == input.charAt(i + 1))
				count++;
			else {
				// from leetcode(https://leetcode.com/problems/string-compression/description/) if you want to have
				// this condition - Every element of the array should be a character (not int) of length 1.
				// then..
				if (count == 1) {
					sb.append(Character.toString(ch));
				} else {
					sb.append(Character.toString(ch)).append(Integer.toString(count));
				}
				count = 1;
			}
		}

		return sb.toString().length() > input.length() ? input : sb.toString();
	}

	/**
	 * Rotating to the left by n is the same as rotating to the right by length-n. time and space complexity - O(N)
	 * 
	 * @param input
	 * @param d
	 * @return
	 */
	public String leftRotation(String input, int d) {
		int n = input.length();
		if (d > n)
			d %= n;
		char[] ch = new char[n];
		for (int i = 0; i < n; i++) {
			ch[(i + (n - d)) % n] = input.charAt(i);
		}
		return new String(ch);
	}

	public String rightRotation(String input, int d) {
		int n = input.length();
		if (d > n)
			d %= n;
		char[] ch = new char[n];
		for (int i = 0; i < n; i++) {
			ch[(i + d) % n] = input.charAt(i);
		}
		return new String(ch);
	}

	/**
	 * Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings, s1
	 * and s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring (e.g., waterbottle is a
	 * rotation of erbottlewat ).
	 * 
	 * We ask what the rotation point is. If a string is broken down into x and y, so thst xy = s1 and yx = s2, then yx
	 * is a substring of xyxy. i.e s2 is always a substring of s1s1.
	 * 
	 * The runtime of this varies based on the runtime of isSubString. But if you assume that isSubstring runs in O(A+B)
	 * time (on strings of length A and B), then the runtime of isRotation is O( N) .
	 * 
	 * CTCI - 1.9
	 */
	public boolean stringRotation(String s1, String s2) {
		int len1 = s1.length();
		if (len1 == s2.length() && len1 > 0) {
			String s1s1 = s1 + s1;
			return isSubString(s1s1, s2);
		}
		return false;
	}

	private boolean isSubString(String s1, String s2) {
		return s1.contains(s2);
	}

	/**
	 * Given a string of lowercase letters in the range ascii[a-z], determine a character that can be removed to make
	 * the string a palindrome. There may be more than one solution, but any will do. For example, if your string is
	 * "bcbc", you can either remove 'b' at index or 'c' at index . If the word is already a palindrome or there is no
	 * solution, return -1. Otherwise, return the index of a character to remove.
	 * 
	 * @param input
	 * @return
	 */
	public int palindromeIndex(String input) {
		for (int i = 0, j = input.length() - 1; i < j; i++, j--) {
			if (input.charAt(i) != input.charAt(j)) {
				return isPalindrome(input, i + 1, j) ? i : j;
			}
		}
		return -1;
	}

	private boolean isPalindrome(String input, int beginIndex, int endIndex) {
		while (beginIndex <= endIndex) {
			if (input.charAt(beginIndex++) != input.charAt(endIndex--))
				return false;
		}
		return true;
	}

	public void convertNumToExcelColName(int num) {
		StringBuilder sb = new StringBuilder();

		while (num > 0) {
			int rem = num % 26;

			if (rem == 0) {
				sb.append("Z");
				num = (num / 26) - 1;
			} else {
				sb.append((char) ((rem - 1) + 'A'));
				num = num / 26;
			}
		}

		System.out.println(sb.reverse());
	}

	// replace %20 with " "
	public String replaceSpace(String s) {
		int posOld = 0, posNew = 0;
		char[] res = s.toCharArray();

		while (posOld < s.length()) {
			if (posOld < s.length() - 2 && res[posOld] == '%' && res[posOld + 1] == '2' && res[posOld + 2] == '0') {
				while (posOld < s.length() - 2 && res[posOld] == '%' && res[posOld + 1] == '2'
						&& res[posOld + 2] == '0') {
					posOld += 3;
				} //

				res[posNew++] = ' ';
			} else {
				res[posNew++] = res[posOld++];
			}
		}

		return new String(res).substring(0, posNew);
	}

}
