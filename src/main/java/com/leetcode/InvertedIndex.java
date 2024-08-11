package com.leetcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Implement two methods, one is add document, document is a string. The other is search, which supports AND OR search.
 * Return eligible documents
 *
 * The concept of an inverted index is simple: use the words in the file as keywords, and then establish the mapping
 * between words and files. Of course, you can also add information such as the frequency of words in the file. Inverted
 * index is a very basic concept in search engines. Almost all search engines use inverted index
 *
 */
public class InvertedIndex {

	// Word, Line#/File#, Count
	Map<String, Map<Integer, Integer>> map = new HashMap<String, Map<Integer, Integer>>();

	void createIndex(String path) throws Exception {
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);

		String curLine = "";
		int lineNumber = 0; // Line number starting from 0
		while ((curLine = br.readLine()) != null) {
			String[] arr = curLine.split("\\s+");

			for (int i = 0; i < arr.length; i++) {
				String curWord = arr[i];

				if (map.containsKey(curWord)) {
					Map<Integer, Integer> curMap = map.get(curWord);
					curMap.put(lineNumber, curMap.getOrDefault(lineNumber, 0) + 1);
				} else {
					Map<Integer, Integer> curMap = new HashMap<Integer, Integer>();
					curMap.put(lineNumber, 1); // In the current line, occurs once
					map.put(curWord, curMap);
				}
			}

			lineNumber++;
		}

		br.close();
		fr.close();
	}

	void getOccurance(String[] arr) {
		for (String word : arr) {
			if (map.containsKey(word)) {
				Map<Integer, Integer> curMap = map.get(word);

				System.out.println(word + " occurs: ");
				for (Map.Entry<Integer, Integer> entry : curMap.entrySet()) {
					System.out.println("line# " + entry.getKey() + " with count# " + entry.getValue());
				}
				System.out.println();
			} else {
				System.out.println(word + " doesn't exist");
			}
		}
	}
}
