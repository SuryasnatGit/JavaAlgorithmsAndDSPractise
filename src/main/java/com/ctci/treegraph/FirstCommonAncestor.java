package com.ctci.treegraph;

import com.algo.common.TreeNode;

/**
 * CTCI - 4.8
 * 
 * First Common Ancestor: Design an algorithm and write code to find the first common ancestor of two nodes in a binary
 * tree. Avoid storing additional nodes in a data structure. NOTE: This is not necessarily a binary search tree.
 * 
 * @author surya
 *
 */
public class FirstCommonAncestor<K, V> {

	// Approach 1
	/**
	 * worst case O((logN)^2) balanced tree.
	 * 
	 * worst case O(N^2) unbalanced tree.
	 * 
	 * Trace p's path upwards. At each node on this path, check to see if node is on the path from q to the root. The
	 * first node will be the first common ancestor
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode commonAncestor(TreeNode p, TreeNode q) {
		if (p == q)
			return null;

		TreeNode ancestor = p;
		while (ancestor != null) {
			if (isOnPath(ancestor, q))
				return ancestor;
			ancestor = ancestor.parent;
		}
		return null;
	}

	/**
	 * O(d) where d is depth
	 * 
	 * @param ancestor
	 * @param node
	 * @return
	 */
	private boolean isOnPath(TreeNode ancestor, TreeNode node) {
		while (node != ancestor && node != null)
			node = node.parent;
		return node == ancestor;
	}

	// Approach 2
	/**
	 * O(t) time - where t is size of subtree for 1st common ancestor.
	 * 
	 * Worst case - O(n) - # nodes in tree. Each node in subtree is searched once
	 * 
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (!covers(root, p) || !covers(root, q))
			return null;
		else if (covers(p, q))
			return p;
		else if (covers(q, p))
			return q;

		/* Traverse upwards until you find a node that covers p */
		TreeNode sibling = getSibling(p);
		TreeNode parent = p.parent;
		while (!covers(sibling, q)) {
			sibling = getSibling(parent);
			parent = parent.parent;
		}
		return parent;
	}

	boolean covers(TreeNode root, TreeNode p) {
		if (root == null)
			return false;
		if (root == p)
			return true;
		return covers(root.left, p) || covers(root.right, p);
	}

	TreeNode getSibling(TreeNode node) {
		if (node == null || node.parent == null)
			return null;
		TreeNode parent = node.parent;
		return parent.left == node ? parent.right : parent.left;
	}

	// Approach 3 - Solution Without Links to Parents
	/**
	 * O(n) time on balanced tree. covers() is called on 2n nodes in the first call. n nodes on left side, n nodes for
	 * right side. algorithm branches left or right 2n/2 nodes then 2n/4 ... O(n). Follow a chain in which p and q are
	 * on the same side. If p and q are both on the left of the node, branch left to look for common ancestor if they
	 * are both on the right, branch right to look for the common ancestor. When p and q are no longer on the same side,
	 * you have fond the first common ancestor
	 * 
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	TreeNode commonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
		if (!covers(root, p) || !covers(root, q))
			return null;
		return ancestorHelper(root, p, q);
	}

	TreeNode ancestorHelper(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null)
			return null;
		else if (root == p)
			return p;
		else if (root == q)
			return q;

		boolean pIsOnLeft = covers1(root.left, p);
		boolean qIsOnLeft = covers1(root.left, q);

		if (pIsOnLeft != qIsOnLeft)
			return root;
		TreeNode childSide = pIsOnLeft ? root.left : root.right;
		return ancestorHelper(childSide, p, q);
	}

	boolean covers1(TreeNode root, TreeNode p) {
		if (root == null)
			return false;
		if (root == p)
			return true;
		return covers(root.left, p) || covers(root.right, p);
	}

}
