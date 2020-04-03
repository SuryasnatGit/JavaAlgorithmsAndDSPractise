package com.algo.string;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;

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

	public static void main(String[] args) throws FileNotFoundException {
		KthMostFrequentString k = new KthMostFrequentString();
		System.out.println(k.mostFrequentString(new String[] { "a", "b", "c", "a", "b", "a" }, 0));
		System.out.println(k.mostFrequentString(new String[] { "a", "b", "c", "a", "b", "a" }, 1));
		System.out.println(k.mostFrequentString(new String[] { "a", "b", "c", "a", "b", "a" }, 2));

		PriorityQueue<MinHeapNode> minHeap = new PriorityQueue<>();
		Trie root = new Trie();

		Scanner sc = new Scanner(new File(
				"C:\\Developer\\GitRepo\\JavaAlgorithmsAndDSPractise\\src\\main\\java\\com\\algo\\string\\file.txt"));
		while (sc.hasNext()) {
			k.insertAndUpdateHeap(root, sc.next(), minHeap, 5);
		}
		sc.close();

		int count = 2;
		while (!minHeap.isEmpty() && count-- > 0) {
			System.out.println(minHeap.poll());
		}

		// System.out.println(minHeap);
	}

	// Solution 1 - simple
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

	// Solution 2 :

	// Another version - find the K most frequent strings from a large data that can't fit into the
	// memory in one go. Lets say there is a data of 200GB but available memory is 1 GB only.

	// insert key into trie, if prefix mark end of key as leaf, update heap
	private void insertAndUpdateHeap(Trie root, String key, PriorityQueue<MinHeapNode> minHeap, int k) {
		Trie cur = root;
		for (int i = 0; i < key.length(); i++) {

			int index = Character.toLowerCase(key.charAt(i)) - 'a';
			if (cur.child[index] == null) {
				cur.child[index] = new Trie();
			}

			cur = cur.child[index];
		}

		// mark cur as leaf
		cur.isEndOfWord = true;
		cur.frequency = cur.frequency + 1;

		// Case 1, word is already present in minHeap
		if (cur.heapNode != null) {

			// Unless removed heap will not sort
			minHeap.remove(cur.heapNode);
			cur.heapNode.frequency = cur.frequency;
			minHeap.add(cur.heapNode);

			// Case 2, word is not present and heap is not full
		} else if (cur.heapNode == null && minHeap.size() < k) {
			cur.heapNode = new MinHeapNode(key, cur.frequency);
			cur.heapNode.trieNode = cur;
			minHeap.add(cur.heapNode);

			// Case 3, word is not present and heap is full, and frequency is more than top of minHeap, replace it
		} else if (cur.heapNode == null && cur.frequency > minHeap.peek().frequency) {
			MinHeapNode minHeapRoot = minHeap.poll();
			minHeapRoot.trieNode = cur;
			minHeapRoot.frequency = cur.frequency;
			minHeapRoot.word = key;
			cur.heapNode = minHeapRoot;
			minHeap.add(minHeapRoot);
		}
	}

	// Solution 3 :
	// https://stackoverflow.com/questions/21565880/find-the-n-most-repeating-words-strings-in-a-huge-file-that-does-not-fit-in-me

}

class Trie {
	boolean isEndOfWord = false;
	int frequency = 0;
	Trie[] child = new Trie[26];
	MinHeapNode heapNode = null;
}

class MinHeapNode implements Comparable<MinHeapNode> {
	String word;
	int frequency;
	Trie trieNode;

	public MinHeapNode(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}

	public int compareTo(MinHeapNode n) {
		return n.frequency - this.frequency;
	}

	public String toString() {
		return word + ": " + frequency;
	}
}