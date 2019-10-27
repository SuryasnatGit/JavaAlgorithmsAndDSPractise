package com.ctci.moderate;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a method to find the frequency of occurrences of any given word in a book. What if we were running this
 * algorithm mUltiple times?
 * 
 * CTCI : 16.2
 *
 */
public class WordFrequencies {

	public int count(String[] arr, String word) {
		int count = 0;
		for (String w : arr) {
			if (w.equalsIgnoreCase(word))
				count++;
		}
		return count;
	}

	// check for all error conditions. also check for case sensitiveness and convert to lower case
	public int count1(String[] arr, String word) {
		if (word == null || word.trim() == "") {
			return -1;
		}

		Map<String, Integer> freqMap = new HashMap<>();

		// populate the hashmap
		for (String w : arr) {
			if (w != null && w.trim() != "") {
				if (!freqMap.containsKey(w)) {
					freqMap.put(w, 1);
				} else {
					freqMap.put(w, freqMap.get(w) + 1);
				}
			}
		}

		if (freqMap.containsKey(word))
			return freqMap.get(word);

		return 0;
	}

	public static void main(String[] args) {
		WordFrequencies wf = new WordFrequencies();

		System.out.println(wf.count1(new String[] {}, "my"));
		System.out.println(wf.count1(new String[] { "my", null }, "my"));
		System.out.println(wf.count1(new String[] { "  ", "hi" }, "my  "));
		System.out.println(wf.count1(new String[] { "my", "my " }, "my"));
		System.out.println(wf.count1(new String[] { "my" }, null));
	}

}
