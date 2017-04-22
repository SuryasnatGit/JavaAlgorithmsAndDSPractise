package com.codefights;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class StringsProblems {
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

	public static void main(String[] args) {
		StringsProblems sp = new StringsProblems();
		// String input = " The dog is black ";
		// System.out.println(input);
		// System.out.println(sp.reverseWordByWord(input));
		// sp.pangram("We promptly judged antique ivory buckles for the next prize");
		// sp.pangram("The quick brown fox jumps over the lazy dog");
		sp.pangram1("We promptly judged antique ivory buckles for the next prize");

	}

	/**
	 * Given a string check if it is Pangram or not. A pangram is a sentence containing every letter in the English
	 * Alphabet.
	 * 
	 * @param input
	 */
	public void pangram(String input) {

		/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
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
}
