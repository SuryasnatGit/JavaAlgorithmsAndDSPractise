package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.algo.common.TreeNode;

/**
 * 
 * Category : Hard
 */
public class IdenticalTrees {

	// Problem 1 - http://www.geeksforgeeks.org/check-for-identical-bsts-without-building-the-trees/
	/**
	 * According to BST property, elements of the left subtree must be smaller and elements of right subtree must be
	 * greater than root. Two arrays represent the same BST if, for every element x, the elements in left and right
	 * subtrees of x appear after it in both arrays. And same is true for roots of left and right subtrees. The idea is
	 * to check of if next smaller and greater elements are same in both arrays. Same properties are recursively checked
	 * for left and right subtrees. The idea looks simple, but implementation requires checking all conditions for all
	 * elements.
	 * 
	 * Test cases : Both array should be same size and have exact same elements and first element should be same
	 * 
	 * Little simpler version of above implementation. Uses more space but atleast its easy to understand. All it does
	 * is makes 2 list one of larger and one of smaller element than root and then passes them into left and right sides
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */

	public boolean sameBST1(int arr1[], int arr2[]) {
		List<Integer> list1 = new ArrayList<>();
		for (int i : arr1) {
			list1.add(i);
		}
		List<Integer> list2 = new ArrayList<>();
		for (int i : arr2) {
			list2.add(i);
		}

		return sameBST1(list1, list2);
	}

	/**
	 * It might not work for duplicate elements in array. For that you have to store both index and actual value to
	 * differentiate somehow.
	 */
	private boolean sameBST1(List<Integer> arr1, List<Integer> arr2) {
		if (arr1.size() == 0 && arr2.size() == 0) {
			return true;
		}
		if (arr1.size() == 0 || arr2.size() == 0) {
			return false;
		}

		if (arr1.get(0) != arr2.get(0)) {
			return false;
		}

		List<Integer> smaller1 = new ArrayList<Integer>();
		List<Integer> larger1 = new ArrayList<Integer>();
		boolean first = true;
		for (Integer i : arr1) {
			if (first) {
				first = false;
				continue;
			}
			if (i <= arr1.get(0)) {
				smaller1.add(i);
			} else {
				larger1.add(i);
			}
		}

		first = true;
		List<Integer> smaller2 = new ArrayList<Integer>();
		List<Integer> larger2 = new ArrayList<Integer>();
		for (Integer i : arr2) {
			if (first) {
				first = false;
				continue;
			}
			if (i <= arr2.get(0)) {
				smaller2.add(i);
			} else {
				larger2.add(i);
			}
		}

		boolean r = compare(smaller1, smaller2) && compare(larger1, larger2);

		if (!r) {
			return false;
		}

		// for subtrees
		return sameBST1(smaller1, smaller2) && sameBST1(larger1, larger2);
	}

	private boolean compare(List<Integer> l1, List<Integer> l2) {
		Set<Integer> s = new HashSet<Integer>();
		for (Integer i : l1) {
			s.add(i);
		}
		for (Integer i : l2) {
			if (!s.contains(i)) {
				return false;
			}
		}
		return true;
	}

	// Problem 2 - https://www.techiedelight.com/check-if-two-binary-trees-are-identical-not-iterative-recursive/
	// T - O(n)
	/**
	 * The idea is to traverse both trees and compare value at their root node. If the value matches, we recursively
	 * check if left subtree of first tree is identical to left subtree of second tree and right subtree of first tree
	 * is identical to right subtree of second tree. If the value at their root node differs, the trees violates data
	 * property. If at any point in the recursion, the first tree is empty & second tree is non-empty or second tree is
	 * empty & first tree is non-empty, the trees violates structural property and they cannot be identical.
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 */
	public boolean isIdenticalBSTs_recursive(TreeNode n1, TreeNode n2) {
		if (n1 == null && n2 == null)
			return true;

		return (n1 != null && n2 != null) && (n1.data == n2.data) && isIdenticalBSTs_recursive(n1.left, n2.left)
				&& isIdenticalBSTs_recursive(n1.right, n2.right);
	}

	public boolean isIdenticalBSTs_iterative(TreeNode n1, TreeNode n2) {
		if (n1 == null && n2 == null)
			return true;

		if (n1 == null)
			return false;

		if (n2 == null)
			return false;

		Queue<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(n1, n2));

		while (!queue.isEmpty()) {
			Pair pair = queue.poll();

			TreeNode node1 = pair.n1;
			TreeNode node2 = pair.n2;

			if (node1.data != node2.data)
				return false;

			if (node1.left != null && node2.left != null && node1.left == node2.left) {
				queue.add(new Pair(node1.left, node2.left));
			} else {
				if (node1.left == null || node2.left == null)
					return false;
			}

			if (node1.right != null && node2.right != null && node1.right == node2.right) {
				queue.add(new Pair(node1.right, node2.right));
			} else {
				if (node1.right == null || node2.right == null)
					return false;
			}
		}

		return true;
	}

	private class Pair {
		TreeNode n1;
		TreeNode n2;

		public Pair(TreeNode n1, TreeNode n2) {
			this.n1 = n1;
			this.n2 = n2;
		}
	}

	public static void main(String args[]) {
		int tree1[] = { 3, -6, -2, 11, 9, -10, -1, 15, 10 };
		int tree2[] = { 3, 11, 9, 15, -6, 10, -2, -1, -10 };
		IdenticalTrees it = new IdenticalTrees();
		System.out.println(it.sameBST1(tree1, tree2));

		TreeNode x = new TreeNode(15);
		x.left = new TreeNode(10);
		x.right = new TreeNode(20);
		x.left.left = new TreeNode(8);
		x.left.right = new TreeNode(12);
		x.right.left = new TreeNode(16);
		x.right.right = new TreeNode(25);

		// construct second tree
		TreeNode y = new TreeNode(15);
		y.left = new TreeNode(10);
		y.right = new TreeNode(20);
		y.left.left = new TreeNode(8);
		y.left.right = new TreeNode(12);
		y.right.left = new TreeNode(16);
		y.right.right = new TreeNode(25);

		System.out.println(it.isIdenticalBSTs_recursive(x, y));
		System.out.println(it.isIdenticalBSTs_iterative(x, y));
	}
}
