package com.ctci.treegraph;

import com.algo.common.Node;

/**
 * CTCI - 4.10
 * 
 * Check Subtree: Tl and T2 are two very large binary trees, with Tl much bigger than T2. Create an
 * algorithm to determine if T2 is a subtree of Tl. A tree T2 is a subtree ofTi if there exists a
 * node n in Ti such that the subtree of n is identical to T2. That is, if you cut off the tree at
 * node n, the two trees would be identical.
 * 
 * @author surya
 *
 */
public class CheckSubtree {

	// Approach 1 - Using recursion (not recommended for very large tree)

	/**
	 * Time Complexity: Time worst case complexity of the solution is O(mn) where m and n are number of
	 * nodes in given two trees.
	 * 
	 * Checks if S is a subtree of T
	 * 
	 * @param T
	 * @param S
	 * @return
	 */
	public boolean isSubTree(Node T, Node S) {
		// base case
		if (S == null)
			return true;
		if (T == null)
			return false;

		if (areIdenticalTrees(T, S))
			return true;

		// check for both left and right subtrees
		return isSubTree(T.left, S) || isSubTree(T.right, S);
	}

	private boolean areIdenticalTrees(Node T, Node S) {
		// base case
		if (T == null && S == null)
			return true;
		if (T == null || S == null)
			return false;

		// check if data of both nodes are same and left and right sub trees are identical
		return (T.data == S.data) && areIdenticalTrees(T.left, S.left) && areIdenticalTrees(T.right, S.right);
	}

	// Approach 2 - Using inorder and preorder traversal
	// time complexity - O(n)
	class Passing {

		int i;
		int m = 0;
		int n = 0;
	}

	class BinaryTree {

		Node root;
		Passing p = new Passing();

		// A utility function to store inorder traversal of tree rooted
		// with root in an array arr[]. Note that i is passed as reference
		void storeInorder(Node node, int arr[], Passing i) {
			if (node == null) {
				arr[i.i++] = '$';
				return;
			}
			storeInorder(node.left, arr, i);
			arr[i.i++] = node.data;
			storeInorder(node.right, arr, i);
		}

		// A utility function to store preorder traversal of tree rooted
		// with root in an array arr[]. Note that i is passed as reference
		void storePreOrder(Node node, int arr[], Passing i) {
			if (node == null) {
				arr[i.i++] = '$';
				return;
			}
			arr[i.i++] = node.data;
			storePreOrder(node.left, arr, i);
			storePreOrder(node.right, arr, i);
		}

		/* This function returns true if S is a subtree of T, otherwise false */
		boolean isSubtree(Node T, Node S) {
			/* base cases */
			if (S == null) {
				return true;
			}
			if (T == null) {
				return false;
			}

			// Store Inorder traversals of T and S in inT[0..m-1]
			// and inS[0..n-1] respectively
			int inT[] = new int[100];
			String op1 = String.valueOf(inT);
			int inS[] = new int[100];
			String op2 = String.valueOf(inS);
			storeInorder(T, inT, p);
			storeInorder(S, inS, p);
			inT[p.m] = '\0';
			inS[p.m] = '\0';

			// If inS[] is not a substring of preS[], return false
			if (!op1.contains(op2)) {
				return false;
			}

			// Store Preorder traversals of T and S in inT[0..m-1]
			// and inS[0..n-1] respectively
			p.m = 0;
			p.n = 0;
			int preT[] = new int[100];
			int preS[] = new int[100];
			String op3 = String.valueOf(preT);
			String op4 = String.valueOf(preS);
			storePreOrder(T, preT, p);
			storePreOrder(S, preS, p);
			preT[p.m] = '\0';
			preS[p.n] = '\0';

			// If inS[] is not a substring of preS[], return false
			// Else return true
			return (op3.contains(op4));
		}
	}

	public static void main(String[] args) {
		CheckSubtree cs = new CheckSubtree();

		Node r1 = new Node(1);
		r1.left = new Node(2);
		r1.right = new Node(3);
		r1.right.left = new Node(4);
		r1.right.right = new Node(5);

		Node r2 = new Node(3);
		r2.left = new Node(4);
		// r2.right = new Node(5);
		r2.right = new Node(6);

		System.out.println(cs.isSubTree(r1, r2));
	}
}
