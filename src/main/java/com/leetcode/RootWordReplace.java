package com.leetcode;

/**
 * The question is to give a list of words. Each word is called a root word and there is an input string called a
 * sentence. The requirement is to put the word in the sentence if it has a root word, which is the prefix of the word.
 * Just replace this word with root word. Finally, the replaced sentence is returned. Example: root word ["abc", "car",
 * "race"] sentence "abcde cars ca bounse" return: "abc car ca bounse" In addition, there will not be a root word that
 * is the prefix of another root word. Use trie tree to solve it.
 * 
 * Category : Hard
 * 
 * TODO : to check
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

				TrieNode node = root.search(sub, 0);
				if (node == null) { // Nothing with this prefix
					break;
				} else if (node.hasWord) {
					// TODO : to check
//					arr[i] = node.word;
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
