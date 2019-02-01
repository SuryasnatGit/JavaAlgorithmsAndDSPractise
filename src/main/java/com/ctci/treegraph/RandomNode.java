package com.ctci.treegraph;

import java.util.Random;

/**
 * CTCI - 4.11
 * 
 * Random Node: You are implementing a binary tree class from scratch which, in addition to insert,
 * find, and delete, has a method getRandomNode() which returns a random node from the tree. All
 * nodes should be equally likely to be chosen. Design and implement an algorithm for getRandomNode,
 * and explain how you would implement the rest of the methods.
 * 
 *
 */
public class RandomNode {

	// Approach 1 - Slow and working
	// Copy the nodes to an array and return random elements from the array. O(N) time and O(N) space.
	// But probably we need to look into internal of the DS as we need to implement the tree from
	// scratch

	// Approach 2
	// Label each node from 1 to N. label them in BST order(inorder). On call of getRandomNode we
	// generate a random index and use BST search to find this index.
	// But issue is on addition of new node, reindexing needs to happen which will take O(N) time.

	// Approach 3
	// In a balanced tree, this algorithm will be a (log N), where N is the number of nodes.
	private int data;
	public RandomNode left;
	public RandomNode right;
	private int size = 0;

	public RandomNode(int d) {
		data = d;
		size = 1;
	}

	public RandomNode getRandomNode() {
		int leftSize = left == null ? 0 : left.size();
		Random random = new Random();
		int index = random.nextInt(size);
		if (index < leftSize) {
			return left.getRandomNode();
		} else if (index == leftSize) {
			return this;
		} else {
			return right.getRandomNode();
		}
	}

	public void insertlnOrder(int d) {
		if (d <= data) {
			if (left == null) {
				left = new RandomNode(d);
			} else {
				left.insertlnOrder(d);
			}
		} else {
			if (right == null) {
				right = new RandomNode(d);
			} else {
				right.insertlnOrder(d);
			}
		}
		size++;
	}

	public int size() {
		return size;
	}

	public int data() {
		return data;
	}

	public RandomNode find(int d) {
		if (d == data) {
			return this;
		} else if (d <= data) {
			return left != null ? left.find(d) : null;
		} else if (d > data) {
			return right != null ? right.find(d) : null;
		}
		return null;
	}

}
