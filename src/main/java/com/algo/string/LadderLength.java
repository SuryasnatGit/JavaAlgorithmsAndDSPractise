package com.algo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LadderLength {

	public static void main(String[] args) {
		LadderLength ll = new LadderLength();
		Set<String> dict = new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
		// int length = ll.ladderLength1("hit", "cog", dict);
		// System.out.println(length);
		System.out.println(ll.findAllLadders("hit", "cog", dict));
	}

	/**
	 * Given two words (start and end), and a dictionary, find the length of
	 * shortest transformation sequence from start to end, such that only one
	 * letter can be changed at a time and each intermediate word must exist in
	 * the dictionary. For example, given:
	 * 
	 * start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"] One
	 * shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", the
	 * program should return its length 5.
	 * 
	 * So we quickly realize that this is a search problem, and breath-first
	 * search guarantees the optimal solution.
	 * 
	 * @param beginWord
	 * @param endWord
	 * @param dict
	 * @return
	 */
	public int ladderLength1(String beginWord, String endWord, Set<String> dict){
		List<WordNode> queue = new ArrayList<>();
		queue.add(new WordNode(beginWord, 1)); // initialize
		
		dict.add(endWord);
		
		while(!queue.isEmpty()){
			WordNode wordNode = queue.remove(0);
			String word = wordNode.getWord();

			if (word.equals(endWord))
				return wordNode.getCount();

			char[] charArray = word.toCharArray();
			for (int i = 0; i < charArray.length; i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					char temp = charArray[i];
					if (charArray[i] != c)
						charArray[i] = c; // try with this char combination to check if its present in set

					String newWord = new String(charArray);
					if (dict.contains(newWord)) {
						queue.add(new WordNode(newWord, wordNode.getCount() + 1));
						dict.remove(newWord);
					}
					// replace temp in original position
					charArray[i] = temp;
				}
			}
		}
		return 0;
	}

	/**
	 * Given two words (start and end), and a dictionary, find all shortest
	 * transformation sequence(s) from start to end, such that: 1) Only one
	 * letter can be changed at a time, 2) Each intermediate word must exist in
	 * the dictionary.
	 * 
	 * For example, given: start = "hit", end = "cog", and dict =
	 * ["hot","dot","dog","lot","log"], return:
	 * 
	 * [ ["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"] ]
	 * 
	 * The idea is the same. To track the actual ladder, we need to add a
	 * pointer that points to the previous node in the WordNode class.
	 * 
	 * In addition, the used word can not directly removed from the dictionary.
	 * The used word is only removed when steps change.
	 * 
	 * @param beginWord
	 * @param endWord
	 * @param dict
	 * @return
	 */
	public List<List<String>> findAllLadders(String beginWord, String endWord, Set<String> dict) {

		List<List<String>> result = new ArrayList<>(); // contains result
		List<WordNode> queue = new ArrayList<>();

		Set<String> visited = new HashSet<>();
		Set<String> unVisited = new HashSet<>();

		dict.add(endWord); // add the endWord to dict
		unVisited.addAll(dict); // unvisited contains all of dictionary words
		queue.add(new WordNode(beginWord, 1, null));

		int prevNumSteps = 0;

		while (!queue.isEmpty()) {
			WordNode top = queue.remove(0);
			String word = top.getWord();
			int currNumSteps = top.getCount();

			// result achieved. navigate back to top of the chain.
			if (endWord.equals(word)) {
				List<String> l = new ArrayList<>();
				l.add(word);
				while (top.previous != null) {
					l.add(top.previous.word);
					top = top.previous;
				}
				result.add(l);
				continue;
			}

			if (prevNumSteps < currNumSteps)
				unVisited.removeAll(visited);

			prevNumSteps = currNumSteps;

			char[] charArray = word.toCharArray();
			for (int i = 0; i < charArray.length; i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					char temp = charArray[i];
					if (charArray[i] != c)
						charArray[i] = c;

					String newWord = new String(charArray);
					// the bifurcation happens here when multiple wordnodes get
					// added here within a single iteration
					if (unVisited.contains(newWord)) {
						queue.add(new WordNode(newWord, top.count + 1, top));
						visited.add(newWord);
					}
					charArray[i] = temp;
				}
			}
		}

		return result;
	}

	class WordNode {
		private String word;
		private int count;
		private WordNode previous;

		/**
		 * constructor for calculating the mnimum ladder length
		 * 
		 * @param word
		 * @param c
		 */
		public WordNode(String word, int c) {
			this.word = word;
			this.count = c;
		}

		/**
		 * constructor for finding all the ladders
		 * 
		 * @param word
		 * @param c
		 * @param prev
		 */
		public WordNode(String word, int c, WordNode prev) {
			this.word = word;
			this.count = c;
			this.previous = prev;
		}

		public int getCount() {
			return count;
		}

		public String getWord() {
			return word;
		}

		@Override
		public String toString() {
			return "WordNode [word=" + word + ", count=" + count + ", previous=" + previous + "]";
		}
	}

}
