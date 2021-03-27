package com.leetcode;

/**
 * 211. Design a data structure that supports the following two operations:
 * 
 * void addWord(word) bool search(word) search(word) can search a literal word or a regular expression string containing
 * only letters a-z or .. A . means it can represent any one letter.
 * 
 * For example:
 * 
 * addWord("bad")<br/>
 * addWord("dad") <br/>
 * addWord("mad")<br/>
 * search("pad") -> false<br/>
 * search("bad") -> true<br/>
 * search(".ad") -> true<br/>
 * search("b..") -> true
 * 
 * Design a class, add(word): add word, then isMember(word): if word was added before, return True The difficulty is:
 * add(Apple) -> isMember(A*pl*) To return true, isMember(*****) must also return true.
 * 
 * given a list of words, find whether a given target word exists. Should support “.” which matches any character.
 * Follow up: support “*” which matches 0 or more characters
 * 
 * Added support for'*'. The key is that when the search word encounters'*', all possible consecutive'*'s should be
 * skipped and continue to search for the next non-'*' char. In the Trie of the dictionary data structure, find all the
 * corresponding descendants of the current node (if it is'.', find all descendants). Then it loops recursively for each
 * descendant node. An edge case means that if the word ends with a continuous'*', it will directly return true, because
 * the prefix before the'*' has been found in the dictionary
 ** 
 * No, it should be the case. There are two cases. Because it can match 0 characters, you can directly use the character
 * after * to search in the current layer. If it fails, because * can match any number of characters, then the current
 * layer CHILDREN that is not NULL is recursive, and then the character after * is searched in the next layer
 * 
 */
public class TrieMatch {

	public static void main(String[] args) {
		String[] dict = { "hello", "world", "word", "hellp" };
		TrieNode root = new TrieNode();

		for (String s : dict) {
			root.insert(s, 0);
		}

		TrieNode node = root.search("wor*d", 0);
		System.out.println(node == null);
		if (node != null) {
			System.out.println(node.hasWord);
		}
	}

}

class TrieNode {
	TrieNode[] children = null;
	boolean hasWord = false;

	TrieNode() {
		children = new TrieNode[26];
	}

	public void insert(String word, int pos) {
		if (pos == word.length()) {
			this.hasWord = true;
			return;
		}

		if (this.children[word.charAt(pos) - 'a'] == null) {
			this.children[word.charAt(pos) - 'a'] = new TrieNode();
		}
		this.children[word.charAt(pos) - 'a'].insert(word, pos + 1);
	}

	public TrieNode search(String word, int pos) {
		if (pos == word.length()) {
			return this;
		}

		if (word.charAt(pos) == '*') {
			for (TrieNode child : this.children) {
				if (child != null) {
					TrieNode node = child.search(word, pos + 1);
					if (node != null && node.hasWord) {
						return node;
					}
				}
			}
		} else {
			TrieNode child = this.children[word.charAt(pos) - 'a'];
			if (child == null) {
				return null;
			} else {
				return child.search(word, pos + 1);
			}
		}

		return null;
	}
}
