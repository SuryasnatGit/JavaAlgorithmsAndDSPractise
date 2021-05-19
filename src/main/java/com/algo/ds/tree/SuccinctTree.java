package com.algo.ds.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.algo.common.TreeNode;

/**
 * 
 * Encode and decode binary tree using succinct encoding technique
 *
 * References - http://www.geeksforgeeks.org/succinct-encoding-of-binary-tree/
 * https://en.wikipedia.org/wiki/Binary_tree#Succinct_encodings
 * 
 * TODO : need to understand
 * 
 * Category : Hard
 */
public class SuccinctTree {

	public Result encode(TreeNode root) {
		Result r = new Result();
		encode(root, r);
		return r;
	}

	private void encode(TreeNode root, Result r) {
		if (root == null) {
			r.binaryRep.add(0);
			return;
		}
		r.actualData.add(root.data);
		r.binaryRep.add(1);

		encode(root.left, r);
		encode(root.right, r);
	}

	public TreeNode decode(Result r) {
		AtomicInteger x = new AtomicInteger(0);
		AtomicInteger y = new AtomicInteger(0);
		return decode(r, x, y);
	}

	private TreeNode decode(Result r, AtomicInteger x, AtomicInteger y) {
		if (r.binaryRep.get(x.get()) == 0) {
			x.getAndIncrement();
			return null;
		}

		TreeNode root = new TreeNode();
		root.data = r.actualData.get(y.getAndIncrement());
		x.getAndIncrement();
		root.left = decode(r, x, y);
		root.right = decode(r, x, y);
		return root;
	}

	class Result {
		List<Integer> binaryRep = new ArrayList<>();
		List<Integer> actualData = new ArrayList<>();
	}

	public static void main(String args[]) {
		TreeNode head = new TreeNode(3);
		head.left = new TreeNode(3);
		head.right = new TreeNode(-6);
		head.left.left = new TreeNode(-7);
		head.left.right = new TreeNode(2);
		head.right.left = new TreeNode(9);
		head.right.right = new TreeNode(6);
		head.right.right.left = new TreeNode(11);
		head.right.right.right = new TreeNode(13);

		System.out.println("Before decoding");
		TreeTraversals tt = new TreeTraversals();
		tt.inOrder(head);
		System.out.println();
		tt.preOrder(head);
		System.out.println();
		SuccinctTree st = new SuccinctTree();
		Result r = st.encode(head);
		head = st.decode(r);
		System.out.println("After decoding");
		tt.inOrder(head);
		System.out.println();
		tt.preOrder(head);
	}

}
