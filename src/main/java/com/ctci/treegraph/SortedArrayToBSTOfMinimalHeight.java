package com.ctci.treegraph;

import com.algo.common.Node;

/**
 * Objective: Given a sorted array with unique elements, Create a binary search tree with minimal
 * height.
 * 
 * Why minimal height is important :
 * 
 * We can do the linear scan to the array and make the first element as root and insert all other
 * elements into the tree but in that case tree will be skewed , which means all the nodes of the
 * tree will be on the one side of the root so the height of the tree will be equal to the number of
 * elements in the array. So here our objective is to keep the tree balanced as much as possible.
 * 
 * What is balanced Tree: A balanced tree is a tree in which difference between heights of sub-trees
 * of any node in the tree is not greater than one. To read more about balanced tree, click here
 * 
 * Input: A one dimensional array
 * 
 * Output: Binary Search Tree of Minimal Height.
 * 
 * Approach:
 * 
 * Recursion:
 * 
 * Get the middle of the array make it as root. (By doing this we will ensure that half of the
 * elements of array will be on the left side of the root and half on the right side.) Take the left
 * half of the array, call recursively and add it to root.left. Take the right half of the array,
 * call recursively and add it to root.right. return root.
 * 
 * @author surya
 *
 */
public class SortedArrayToBSTOfMinimalHeight {

	public Node toBST(int[] arr) {
		return toBST(arr, 0, arr.length - 1);
	}

	private Node toBST(int[] arr, int start, int end) {
		if (start > end)
			return null;

		int mid = (start + end) / 2;
		Node root = new Node(arr[mid]);
		root.setLeft(toBST(arr, start, mid - 1));
		root.setRight(toBST(arr, mid + 1, end));
		return root;
	}

	public void displayTree(Node root) {
		if (root != null) {
			displayTree(root.getLeft());
			System.out.print(" " + root.getData());
			displayTree(root.getRight());
		}
	}

	public static void main(String[] args) {
		SortedArrayToBSTOfMinimalHeight arr = new SortedArrayToBSTOfMinimalHeight();
		int[] intarr = { 2, 3, 6, 7, 8, 9, 12, 15, 16, 18, 20 };
		Node root = arr.toBST(intarr);
		arr.displayTree(root);
	}
}
