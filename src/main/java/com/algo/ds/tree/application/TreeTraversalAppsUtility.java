package com.algo.ds.tree.application;

import com.algo.ds.linkedlist.Position;
import com.algo.ds.tree.Tree;

public class TreeTraversalAppsUtility {

	/**
	 * prints table of content in an unintended way
	 * 
	 * @param t
	 */
	public void toc_unindented(Tree<Integer> t) {
		for (Position<Integer> p : t.preorder())
			System.out.println(p.getElement());
	}

	/**
	 * prints table of content in indented way. time complexity O(n) -- worst
	 * case
	 * 
	 * @param t
	 * @param p
	 * @param d
	 */
	public void toc_indented(Tree<Integer> t, Position<Integer> p, int d) {
		System.out.println(spaces(2 * d) + p.getElement());
		for (Position<Integer> child : t.children(p))
			toc_indented(t, child, d + 1);
	}

	public int diskSpaceUsage(Tree<Integer> t, Position<Integer> p) {
		int subTotal = p.getElement();// assume that elem represents the disk
										// usage
		for (Position<Integer> c : t.children(p))
			subTotal += diskSpaceUsage(t, c);
		return subTotal;
	}

	/**
	 * returns a string on 'n' spaces
	 * 
	 * @param n
	 */
	private String spaces(int n) {
		String space = "' '";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(space);
		}
		return sb.toString();
	}

	/**
	 * prints parenthesize representation of sub-tree of T rooted at p
	 */
	public void parenthesize(Tree<Integer> t, Position<Integer> p) {
		System.out.println(p.getElement());
		if (t.isInternal(p)) {
			boolean firstTime = true;
			for (Position<Integer> child : t.children(p)) {
				System.out.println(firstTime ? "(" : ",");
				firstTime = false;
				parenthesize(t, child);
			}
			System.out.println(")");
		}
	}

	public static void main(String[] args) {
		TreeTraversalAppsUtility util = new TreeTraversalAppsUtility();
		System.out.println(util.spaces(10));

	}
}
