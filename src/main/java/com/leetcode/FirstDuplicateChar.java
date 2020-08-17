package com.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstDuplicateChar {

	public char firstDuplicate(String s) {
		LinkedHashMap<Character, Integer> charCount = new LinkedHashMap<>();

		for (char c : s.toCharArray()) {
			charCount.put(c, charCount.get(c) != null ? charCount.get(c) + 1 : 1);
		}

		for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
			if (entry.getValue() > 1) {
				return entry.getKey();
			}
		}

		return ' ';
	}

	public static void main(String[] args) {
		FirstDuplicateChar fir = new FirstDuplicateChar();
		System.out.println(fir.firstDuplicate("abccfegbaed"));
		System.out.println(fir.firstDuplicate("abccfeged"));
	}
}
