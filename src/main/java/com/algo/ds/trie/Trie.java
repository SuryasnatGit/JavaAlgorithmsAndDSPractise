package com.algo.ds.trie;

import java.util.Map;

/**
 * A trie is a data structure used for efficient retrieval of data associated with keys. If key is of length n, then
 * using trie worst case time complexity for searching the record associated with this key is O(n). Insertion of (key,
 * record) pair also takes O(n) time in worst case.
 * 
 * Trie's retrieval/insertion time in worst case is better than hashTable and binary search tree both of which take
 * worst case time of O(n) for retrieval/insertion. The trie structure though in theory has same worst case space
 * complexity as hashTable or a binary tree, memory needed to store pointers causes it to be less space efficient during
 * implementations.
 * 
 * @author Suryasnat
 *
 */
public class Trie {

	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	/**
	 * inserts words into trie. </br>
	 * worst case - O(n) where n is the length of key/word
	 * 
	 * @param word
	 */
	public void insert(String word) {
		Map<Character, TrieNode> children = root.getChildren();
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode trieNode;
			if (children.containsKey(ch)) {
				trieNode = children.get(ch);
			} else {
				trieNode = new TrieNode(ch);
				children.put(ch, trieNode);
			}
			children = trieNode.getChildren(); // progress children
			if (i == word.length() - 1)
				trieNode.setLeaf(true);
		}
	}

	/**
	 * returns true if complete word is in the trie. <br/>
	 * worst case - O(n)
	 * 
	 * @param word
	 * @return
	 */
	public boolean search(String word) {
		TrieNode node = searchNode(word);
		return (node != null && node.isLeaf()) ? true : false;
	}

	/**
	 * returns true if there is a word in trie which starts with given prefix
	 * 
	 * @param prefix
	 * @return
	 */
	public boolean searchPrefix(String prefix) {
		return searchNode(prefix) != null ? true : false;
	}

	private TrieNode searchNode(String str) {
		Map<Character, TrieNode> children = root.getChildren();
		TrieNode trieNode = null;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (children.containsKey(ch)) {
				trieNode = children.get(ch);
				children = trieNode.getChildren();
			} else {
				return null;
			}
		}
		return trieNode;
	}

	public boolean search1(String word) {
		return dfsSearch(root.getChildren(), word, 0);
	}

	/**
	 * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one
	 * letter.
	 * 
	 * @param children
	 * @param word
	 * @param start
	 * @return
	 */
	public boolean dfsSearch(Map<Character, TrieNode> children, String word, int start) {
		if (start == word.length()) {
			return (children.size() == 0) ? true : false;
		}
		char ch = word.charAt(start);
		if (children.containsKey(ch)) {
			return (start == word.length() - 1 && children.get(ch).isLeaf()) ? true
					: dfsSearch(children.get(ch).getChildren(), word, start + 1);
		} else if (ch == '.') {
			boolean result = false;
			for (Map.Entry<Character, TrieNode> childEntry : children.entrySet()) {
				if (start == word.length() - 1 && childEntry.getValue().isLeaf())
					return true;
				if (dfsSearch(childEntry.getValue().getChildren(), word, start + 1))
					result = true;
			}
			return result;
		} else
			return false;
	}

	public static void main(String[] args) {
		Trie t = new Trie();
		t.insert("sun");
		t.insert("moon");
		System.out.println(t.root);
		System.out.println(t.search("su"));
		System.out.println(t.search("sun"));
		System.out.println(t.search("suns"));
		System.out.println(t.searchPrefix("su"));
		System.out.println(t.searchPrefix("sun"));
		System.out.println(t.searchPrefix("suns"));
	}
}
