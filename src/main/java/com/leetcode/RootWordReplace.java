package com.leetcode;

/**
 * 题目是给一个list of word， 每个word 叫做root word 然后还有个input string 是sentence， 要求是把sentence 中的单词如果有root word 是单词的prefix，
 * 就把这个单词替换成root word。最后返回替换后的sentence 例子： root word ["abc", "car", "race"] sentence "abcde cars ca bounse" return: "abc
 * car ca bounse" 另外不会有某个root word 是另外一个root word的prefix 用trie tree 解了。
 *
 */
public class RootWordReplace {
	public static void main(String[] args) {
		RootWordReplace rr = new RootWordReplace();
		rr.replace();
	}

	String[] rootWords = { "abc", "car", "race" };
	String sentence = "abcde cars ca bounse";
	TrieNode root = new TrieNode();

	void replace() {
		for (String word : rootWords) {
			root.insert(word, 0);
		}

		String[] arr = sentence.split("\\s+");
		for (int i = 0; i < arr.length; i++) {
			String s = arr[i];

			for (int len = 1; len <= s.length(); len++) {
				String sub = s.substring(0, len);

				TrieBreakingBad.TrieNode node = root.search(sub, 0);
				if (node == null) { // Nothing with this prefix
					break;
				} else if (node.hasWord) {
					arr[i] = node.word;
					break;
				} else {
					// has this prefix, continue
				}
			}
		}

		for (String s : arr) {
			System.out.print(s + " ");
		}
	}
}
