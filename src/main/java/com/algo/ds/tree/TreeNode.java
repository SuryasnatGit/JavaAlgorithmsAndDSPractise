package com.algo.ds.tree;

public class TreeNode<K,V> {

	public int key;
	public int value;
	public TreeNode<K,V> left;
	public TreeNode<K,V> right;

	public TreeNode(int key, int val) {
		this.key = key;
		this.value = val;
	}
}
