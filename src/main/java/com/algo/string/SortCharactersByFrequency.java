package com.algo.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortCharactersByFrequency {

	public String frequencySort(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}

		Map<Character, Integer> map = new HashMap<>();
		for (char ch : s.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		Comparator<Map.Entry<Character, Integer>> comp = new Comparator<Map.Entry<Character, Integer>>() {
			@Override
			public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		};

		List<Map.Entry<Character, Integer>> list = new ArrayList<>();
		list.addAll(map.entrySet());
		Collections.sort(list, comp);

		char[] charr = new char[s.length()];
		int count = 0;
		for (Map.Entry<Character, Integer> entry : list) {
			char c = entry.getKey();
			int val = entry.getValue();
			for (int i = 0; i < val; i++) {
				charr[count++] = c;
			}
		}

		return new String(charr);
	}

	public static void main(String[] args) {
		SortCharactersByFrequency sort = new SortCharactersByFrequency();
		System.out.println(sort.frequencySort("apple"));
		// System.out.println(sort.frequencySort1("apple"));
		System.out.println(sort.frequencySort("tree"));
		// System.out.println(sort.frequencySort1("tree"));
	}
}
