package com.companyprep;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Give a string [] words, Find the shortest string [] containing the keyword inside.
 * 
 * example:
 * 
 * words: sky cloud google search sky work blue
 * 
 * keywords: sky blue
 * 
 * return: sky work blue
 *
 * Category : Hard
 */
public class ShortestSubstringContainingKeyword {

	public String[] shortestSS(String[] words, String keyword) {

		String[] keywords = keyword.split(" ");

		Map<String, Integer> map = new HashMap<>();
		for (String kw : keywords) {
			map.put(kw, -1);
		}

		int count = 0;
		int length = words.length;
		int minSubSlength = Integer.MAX_VALUE;
		int windowStart = 0, windowEnd = 0;

		for (int i = 0; i < length; i++) {
			if (map.containsKey(words[i])) {

				// if first occurrence
				int index = map.get(words[i]);
				if (index == -1) {
					count++;
				}

				// store latest index
				map.put(words[i], i);

				// if all words matched
				if (count == keywords.length) {

					// find smallest index
					int minIndex = Integer.MAX_VALUE;
					for (Map.Entry<String, Integer> entry : map.entrySet()) {
						minIndex = Math.min(minIndex, entry.getValue());
					}

					// if current length is smaller than length so far
					int subLength = i - minIndex;
					if (subLength < minSubSlength) {
						windowStart = minIndex;
						windowEnd = i;
						minSubSlength = subLength;
					}
				}
			}
		}

		return Arrays.copyOfRange(words, windowStart, windowEnd + 1); // to is exclusive
	}

	public static void main(String[] args) {
		ShortestSubstringContainingKeyword ss = new ShortestSubstringContainingKeyword();
		System.out.println(
				Arrays.toString(ss.shortestSS("sky cloud google search sky work blue".split(" "), "sky blue")));
	}
}
