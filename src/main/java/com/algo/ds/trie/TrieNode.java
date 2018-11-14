package com.algo.ds.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

	private char c;
	private Map<Character, TrieNode> children = new HashMap<>();
	private boolean isLeaf;

	public TrieNode() {
		// TODO Auto-generated constructor stub
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

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	@Override
	public String toString() {
		return "TrieNode [c=" + c + ", children=" + children + ", isLeaf=" + isLeaf + "]";
	}
}
