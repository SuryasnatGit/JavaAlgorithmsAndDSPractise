package com.algo.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Given a list of strings, write a function to get the kth most frequently occurring string.
 * 
 * kthMostFrequent({"a","b","c","a","b","a"}, 0) = "a"
 * 
 * kthMostFrequent({"a","b","c","a","b","a"}, 1) = "b"
 * 
 * kthMostFrequent({"a","b","c","a","b","a"}, 2) = "c"
 * 
 * kthMostFrequent({"a","b","c","a","b","a"}, 3) = null
 * 
 * Category : Hard
 *
 */
public class KthMostFrequentString {

	public String mostFrequentString(String[] array, int k) {
		Map<String, Integer> freqMap = new HashMap<String, Integer>();

		for (String str : array) {
			if (freqMap.containsKey(str)) {
				freqMap.put(str, freqMap.get(str) + 1);
			} else {
				freqMap.put(str, 1);
			}
		}

		List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(freqMap.entrySet());

		Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		return entryList.size() > 0 ? entryList.get(k).getKey() : null;
	}

	public static void main(String[] args) {
		KthMostFrequentString k = new KthMostFrequentString();
		System.out.println(k.mostFrequentString(new String[] { "a", "b", "c", "a", "b", "a" }, 0));
		System.out.println(k.mostFrequentString(new String[] { "a", "b", "c", "a", "b", "a" }, 1));
		System.out.println(k.mostFrequentString(new String[] { "a", "b", "c", "a", "b", "a" }, 2));
	}

	// Another version - find the K most frequent strings from a large data that can't fit into the
	// memory in one go. Lets say there is a data of 200GB but available memory is 1 GB only.

}
