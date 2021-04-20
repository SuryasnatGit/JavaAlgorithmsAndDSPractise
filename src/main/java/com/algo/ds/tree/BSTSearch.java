package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * 
 * 
 * Youtube link - https://youtu.be/zm83jPHZ-jA
 * 
 * Given a binary search tree and a key, return node which has data as this key or return null if no node has data as
 * key.
 * 
 * Solution Since its BST for every node check if root.data is key and if not go either left or right depending on if
 * root.data is greater or less than key
 * 
 * Time complexity is O(n) for non balanced BST Time complexity is O(logn) for balanced BST
 * 
 * Test cases: 1) null tree 2) Tree with one node and key is that node 3) Tree with many nodes and key does not exist 4)
 * Tree with many nodes and key exists
 */
public class BSTSearch {

	public TreeNode search(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		if (root.data == key) {
			return root;
		} else if (root.data < key) {
			return search(root.right, key);
		} else {
			return search(root.left, key);
		}
	}

	public static void main(String args[]) {
		BinarySearchTree bt = new BinarySearchTree();

		bt.insert(10);
		bt.insert(20);
		bt.insert(-10);
		bt.insert(15);
		bt.insert(0);
		bt.insert(21);
		bt.insert(-1);

		BSTSearch bstSearch = new BSTSearch();

		TreeNode result = bstSearch.search(bt.getRoot(), 21);
		System.out.println(result.data);

		result = bstSearch.search(bt.getRoot(), -1);
		System.out.println(result.data);

		result = bstSearch.search(bt.getRoot(), 11);
		System.out.println(result != null ? result.data : null);
	}
}
