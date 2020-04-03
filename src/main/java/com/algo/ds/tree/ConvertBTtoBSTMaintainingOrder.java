package com.algo.ds.tree;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.algo.common.TreeNode;

/**
 * https://www.techiedelight.com/convert-binary-tree-to-bst-maintaining-original-structure/
 */
public class ConvertBTtoBSTMaintainingOrder {

	public void extractSet(TreeNode root, Set<Integer> set) {
		if (root == null)
			return;

		extractSet(root.left, set);
		set.add(root.data);
		extractSet(root.right, set);
	}

	private void convertToBst(TreeNode root, Iterator<Integer> iterator) {
		if (root == null)
			return;

		convertToBst(root.left, iterator);
		root.data = iterator.next();
		convertToBst(root.right, iterator);
	}

	public static void main(String[] args) {
		ConvertBTtoBSTMaintainingOrder bt = new ConvertBTtoBSTMaintainingOrder();

		TreeNode root = new TreeNode(8);
		root.left = new TreeNode(3);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(10);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(6);

		Set<Integer> set = new TreeSet<>();
		bt.extractSet(root, set);

		Iterator<Integer> iterator = set.iterator();
		bt.convertToBst(root, iterator);

		bt.printTree(root);
	}

	private void printTree(TreeNode root) {
		if (root == null)
			return;

		printTree(root.left);
		System.out.println(root.data + " ");
		printTree(root.right);
	}

}
