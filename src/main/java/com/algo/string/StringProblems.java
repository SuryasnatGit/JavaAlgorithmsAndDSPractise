package com.algo.string;

import java.util.ArrayList;
import java.util.Arrays;
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
		System.out.println(sp.compressedString("apple"));
		System.out.println(sp.compressedString("aabcccccaaa"));
		System.out.println(sp.compressedString("ABCDEFFFFFFFF"));
		System.out.println(sp.compressedString("ABCDEF"));
	}

	private int count = 0;

	/**
	 * Analyzing the Time Complexity :
	 * 
	 * 1. How many times does function perm get called in its base case? As we can understand from the
	 * recursion explained above that for a string of length 3 it is printing 6 permutations which is
	 * actually 3!. This is because if it needs to generate permutation, it is needed to pick characters
	 * for each slot. If there are 3 characters in our string, in the first slot, there are 3 choices, 2
	 * choices for the next slot (for each of 3 choices earlier, i.e multiplication and not addition)
	 * and so on. This tells that there are n! permutations being printed in the base case which is what
	 * is shown in the image.
	 * 
	 * 2. How many times does function perm get called before its base case? Consider that lines 9
	 * through 12 are hit n number of times. Therefore, there will be no more than (n * n!) function
	 * calls.
	 * 
	 * 3. How long does each function call take? Since, each character of string prefix needs to be
	 * printed, thus executing line 7 will take O(n) time. Line 10 and line 11 will also take O(n) time
	 * combined due to string concatenation, as sum of rem, prefix and str.charAt(i) will always be n.
	 * Each function call therefore corresponds to O(n) work.
	 * 
	 * 4. What is the total runtime? Calling perm O(n * n!) times (as an upper bound) and each call
	 * takes O(n) time, the total runtime will not exceed O(n^2 * n!).
	 * 
	 * @param str
	 */
    public void stringPermutations(String str) {
		permutations(str, "");
	}

	private void permutations(String string, String prefix) {
		if (string.length() == 0) {
			System.out.println(prefix);
			System.out.println(++count);
		}
		else {
			for (int i = 0; i < string.length(); i++) {
				String rem = string.substring(0, i) + string.substring(i + 1);
				permutations(rem, prefix + string.charAt(i));
			}
		}
	}

	/**
	 * Algorithm to find if a string has unique characters. Time complexity - O(N) as hashset operations
	 * is constant time O(1). space complexity - O(N)
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
	 * Algorithm to find if a string has unique characters using a temp array. Time complexity is O(N^2)
	 * for worst case , where N is the length of the string. space complexity - O(N)
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
	 * Time complexity - O(N^2) as for each iteration of N, the indexOf check runs N times.
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
	 * sort the strings and compare it. complexity - O(NlogN)
	 * 
	 * @param input1
	 * @param input2
	 */
	public void findStringPermutation1(String input1, String input2) {
		char[] inputchar1 = input1.toCharArray();
		char[] inputchar2 = input2.toCharArray();
		Arrays.sort(inputchar1);
		Arrays.sort(inputchar2);
		String sortInput1 = new String(inputchar1);
		String sortInput2 = new String(inputchar2);
		System.out.println(sortInput1.equals(sortInput2) ? "is a permutation " : "is not a permutation");
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
	 * Write a method to replace all the spaces in a string with �%20�. You may assume that the string has sufficient
	 * space at the end to hold the additional characters, and that you are given the �true� length of the string.
	 * 
	 * Examples:
	 * 
	 * Input : "Mr John Smith", 13 Output : Mr%20John%20Smith
	 * 
	 * Input : "Mr John Smith ", 13 Output : Mr%20John%20Smith
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
	 * Given a string check if it is Pangram or not. A pangram is a sentence containing every letter in
	 * the English Alphabet.
	 * 
	 * @param input
	 */
	public void pangram(String input) {

		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named
		 * Solution.
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

	/**
	 * There are three types of edits that can be performed on strings: insert a character, remove a
	 * character, or replace a character. Given two strings, write a function to check if they are one
	 * edit (or zero edits) away. time complexity - O(M + N)
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
	 * Implement a method to perform basic string compression using the counts of repeated characters.
	 * For example, the string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not
	 * become smaller than the original string, your method should return the original string. You can
	 * assume the string has only uppercase and lowercase letters (a - z). complexity - O(N)
	 * 
	 * @param input
	 * @return
	 */
	public String compressedString(String input) {
		int count = 1;
		StringBuilder sb = new StringBuilder();
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
}
