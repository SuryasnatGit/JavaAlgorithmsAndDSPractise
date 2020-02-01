package com.algo.ds.tree;

/**
 * https://www.techiedelight.com/combinations-of-words-formed-replacing-given-numbers-corresponding-english-alphabet/
 *
 * T - exponential
 */
public class CombinationOfWordsFormed {

	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public void combination(int[] num, int len, String str) {
		if (len == num.length) {
			System.out.println(str);
			return;
		}

		int sum = 0;

		for (int j = len; j < num.length; j++) {
			sum = (sum * 10) + num[j];
			if (sum > 0 && sum <= 26) {
				combination(num, j + 1, str + alphabet.charAt(sum - 1));
			}
		}
	}

	// Function to construct a binary tree where each leaf node contains
	// one unique combination of words formed
	public void construct(Node root, int[] digit, int i) {
		// Base case: empty tree
		if (root == null || i == digit.length) {
			return;
		}

		// check if digit[i+1] exists
		if (i + 1 < digit.length) {
			// process current and next digit
			int sum = 10 * digit[i] + digit[i + 1];

			// if a valid character can be formed by both digits
			// create left child from it
			if (sum <= 26) {
				root.left = new Node(root.key + alphabet.charAt(sum - 1));
			}

			// construct left subtree by remaining digits
			construct(root.left, digit, i + 2);
		}

		// process current digit and create right child from it
		root.right = new Node(root.key + alphabet.charAt(digit[i] - 1));

		// construct right subtree by remaining digits
		construct(root.right, digit, i + 1);
	}

	class Node {
		String key;
		Node left, right;

		// Constructor
		Node(String key) {
			this.key = key;
			left = right = null;
		}
	}

	public static void main(String[] args) {
		CombinationOfWordsFormed com = new CombinationOfWordsFormed();
		com.combination(new int[] { 1, 2, 2 }, 0, "");
		System.out.println();
		com.combination(new int[] { 1, 2, 3 }, 0, "");
	}
}
