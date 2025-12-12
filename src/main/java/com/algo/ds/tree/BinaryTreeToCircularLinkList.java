package com.algo.ds.tree;

import com.algo.common.TreeNode;

/**
 * http://cslibrary.stanford.edu/109/TreeListRecursion.html<br/>
 * Given a Binary Tree, convert it to a Circular Doubly Linked List (In-Place).
 * 
 * 1. The left and right pointers in nodes are to be used as previous and next pointers respectively in converted
 * Circular Linked List. <br/>
 * 2. The order of nodes in List must be same as Inorder of the given Binary Tree. <br/>
 * 3. The first node of Inorder traversal must be head node of the Circular List.
 * 
 */
public class BinaryTreeToCircularLinkList {

	public TreeNode convert(TreeNode root) {
		if (root == null) {
			return null;
		}

		// making the root circular if only root is there
		if (root.left == null && root.right == null) {
			root.left = root;
			root.right = root;
			return root;
		}

		TreeNode left = convert(root.left);
		TreeNode right = convert(root.right);

		root.left = root;
		root.right = root;

		left = join(left, root);
		left = join(left, right);
		return left;
	}

	/**
	 * concatenate both the lists and returns the head of the List.<br/>
	 * Get the last node of the left list. Retrieving the last node is an O(1) operation, since the prev pointer of the
	 * head points to the last node of the list. Connect it with the first node of the right list Get the last node of
	 * the second list Connect it with the head of the list.
	 * 
	 * @param r1
	 * @param r2
	 * @return
	 */
	private TreeNode join(TreeNode r1, TreeNode r2) {
		// If either of the list is empty, then
		// return the other list
		if (r1 == null) {
			return r2;
		}
		if (r2 == null) {
			return r1;
		}
		TreeNode t1 = r2.left;

		r1.left.right = r2;
		r2.left = r1.left;
		r1.left = t1;
		t1.right = r1;
		return r1;
	}

	private void print(TreeNode root) {
		TreeNode temp = root;
		do {
			System.out.println(temp.data);
			temp = temp.right;
		} while (temp != root);

		System.out.println();
		do {
			System.out.println(temp.data);
			temp = temp.left;
		} while (temp != root);
	}

	public static void main(String args[]) {
//		BinaryTreeToCircularLinkList btc = new BinaryTreeToCircularLinkList();
//		BinaryTree bt = new BinaryTree();
//		TreeNode root = null;
//		root = bt.addNode(10, root);
//		root = bt.addNode(3, root);
//		root = bt.addNode(-1, root);
//		root = bt.addNode(8, root);
//		root = bt.addNode(-6, root);
//		root = bt.addNode(13, root);
//		root = btc.convert(root);
//		btc.print(root);
	}
}
