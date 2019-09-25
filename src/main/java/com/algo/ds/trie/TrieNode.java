package com.algo.ds.trie;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TrieNode {

	private char c;
	// each node stores a map of its child nodes
	private Map<Character, TrieNode> children;
	// true when node is a leaf node
	private boolean isLeaf;
	// collection to store complete list of words in leaf node
	Set<String> words = new HashSet<>();

	public TrieNode() {
		children = new HashMap<>();
	}

	public TrieNode(char ch) {
		this.c = ch;
	}

	public char getChar() {
		return c;
	}

	public Map<Character, TrieNode> getChildren() {
		return children;
	}

	public Set<String> getWords() {
		return words;
	}

	/**
	 * Find the child node of this node that has the char argument as its data.
	 * 
	 * @param ch
	 * @return
	 */
	public TrieNode getChild(char ch) {
		return children.get(ch);
	}

	/**
	 * Add the word to this Trie and recursively create child nodes
	 * 
	 * @param word
	 */
	public void addWord(String word) {
		if (word == null || word.isEmpty())
			return;

		char firstChar = word.charAt(0);
		TrieNode child = getChild(firstChar);
		if (child == null) {
			child = new TrieNode(firstChar);
			children.put(firstChar, child);
		}

		if (word.length() > 1) {
			child.addWord(word.substring(1));
		} else {
			child.setLeaf(true);
		}
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	@Override
	public String toString() {
		return "TrieNode [char=" + c + ", children=" + children + ", isLeaf=" + isLeaf + ", words=" + words + "]";
	}
}
