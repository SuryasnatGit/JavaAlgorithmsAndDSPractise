package com.leetcode;

import com.algo.common.TreeNode;

/**
 * Problem Statement: Given a BST and a number X, find the Floor of X. Note: Floor(X) is a number that is either equal
 * to X or is immediately lesser than X.
 * 
 * If Floor could not be found, return -1.
 * 
 * Time Complexity: O(log(N)) {Similar to Binary Search, at a given time we’re searching one half of the tree, so the
 * time taken would be of the order log(N) where N are the total nodes in the BST and log(N) is the height of the tree.}
 * 
 * Space Complexity: O(1) {As no extra space is being used, we’re just traversing the BST.}
 */
public class FloorOfANumberInBST {

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(2);
		TreeNode n5 = new TreeNode(9);
		TreeNode n4 = new TreeNode(5);
		TreeNode n3 = new TreeNode(3);
		TreeNode n1 = new TreeNode(1);

		n3.left = n1;
		n1.right = n2;
		n3.right = n4;
		n4.right = n5;

		FloorOfANumberInBST f = new FloorOfANumberInBST();
		System.out.println(f.floor(n3, 8));
		System.out.println(f.floor(n3, 10));
		System.out.println(f.floor(n3, 7));
		System.out.println(f.floor(n3, 4));
		System.out.println(f.floor(n3, 0));
	}

	/**
	 * This Problem can be easily solved by using a similar approach as we use to Binary Search a linear array and find
	 * the number which is just lesser or equal to the given target input value.
	 * 
	 * @param root
	 * @param num
	 * @return
	 */
	public int floor(TreeNode root, int num) {
		int floor = -1;
		while (root != null) {
			if (root.data == num) {
				return num;
			} else if (root.data < num) {
				floor = root.data;
				root = root.right;
			} else {
				root = root.left;
			}
		}
		return floor;
	}
}
