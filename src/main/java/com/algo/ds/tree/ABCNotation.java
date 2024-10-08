package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * An ABC notation in a tree is defined as follows:
 * 
 * 1. "0" means travel left
 * 
 * 2. "1" means travel right
 * 
 * 3. "Undefined" means hit the root
 * 
 * 4. "Not Found" means not present in tree
 * 
 * Given a BST insertion order, {5,2,8,3,6,9,1} find the ABC notation for 6, 1, 10, 2 which is "10","00","NotFound", "0"
 * 
 * Category : Medium
 *
 */
public class ABCNotation {

	public String bstSearch(TreeNode node, int num) {
		if (node == null) {
			return "NotFound";
		}
		if (node.data == num) {
			return "Undefined";
		}
		String str = bst(node, num);
		return str;
	}

	private String bst(TreeNode node, int num) {
		if (node == null) {
			return "NotFound";
		}
		if (node.data == num) {
			return "found";
		}
		if (num < node.data) {
			String str1 = bst(node.left, num);
			if (str1.equals("NotFound")) {
				return str1;
			} else if (str1.equals("found")) {
				return "0";
			} else {
				return "0" + str1;
			}
		}
		if (num > node.data) {
			String str1 = bst(node.right, num);
			if (str1.equals("NotFound")) {
				return str1;
			} else if (str1.equals("found")) {
				return "1";
			} else {
				return "1" + str1;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		ABCNotation abc = new ABCNotation();

		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(5);
		bst.insert(2);
		bst.insert(8);
		bst.insert(3);
		bst.insert(6);
		bst.insert(9);
		bst.insert(1);

		bst.inorder(bst.getRoot());
		System.out.println();

		System.out.println(abc.bstSearch(bst.getRoot(), 6));
		System.out.println(abc.bstSearch(bst.getRoot(), 1));
		System.out.println(abc.bstSearch(bst.getRoot(), 10));
		System.out.println(abc.bstSearch(bst.getRoot(), 2));

	}
}
