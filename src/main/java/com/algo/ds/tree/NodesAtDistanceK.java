package com.algo.ds.tree;

/**
 * http://www.geeksforgeeks.org/print-nodes-distance-k-given-node-binary-tree/
 * 
 * Given a binary tree, a target node in the binary tree, and an integer value k, print all the nodes that are at
 * distance k from the given target node. No parent pointers are available.
 * 
 * Test case. k should not be negative, k could be very big number which means nothing was found, dest might/might not
 * exists in the tree, root could be null
 * 
 * Category : Hard
 * 
 */
public class NodesAtDistanceK {

	Node root;
	/*
	 * Recursive function to print all the nodes at distance k in tree (or subtree) rooted with given root.
	 */

	void printkdistanceNodeDown(Node node, int k) {
		// Base Case
		if (node == null || k < 0)
			return;

		// If we reach a k distant node, print it
		if (k == 0) {
			System.out.print(node.data);
			System.out.println("");
			return;
		}

		// Recur for left and right subtrees
		printkdistanceNodeDown(node.left, k - 1);
		printkdistanceNodeDown(node.right, k - 1);
	}

	// Prints all nodes at distance k from a given target node.
	// The k distant nodes may be upward or downward.This function
	// Returns distance of root from target node, it returns -1
	// if target node is not present in tree rooted with root.
	int printkdistanceNode(Node node, Node target, int k) {
		// Base Case 1: If tree is empty, return -1
		if (node == null)
			return -1;

		// If target is same as root. Use the downward function
		// to print all nodes at distance k in subtree rooted with
		// target or root
		if (node == target) {
			printkdistanceNodeDown(node, k);
			return 0;
		}

		// Recur for left subtree
		int dl = printkdistanceNode(node.left, target, k);

		// Check if target node was found in left subtree
		if (dl != -1) {

			// If root is at distance k from target, print root
			// Note that dl is Distance of root's left child from
			// target
			if (dl + 1 == k) {
				System.out.print(node.data);
				System.out.println("");
			}

			// Else go to right subtree and print all k-dl-2 distant nodes
			// Note that the right child is 2 edges away from left child
			else
				printkdistanceNodeDown(node.right, k - dl - 2);

			// Add 1 to the distance and return value for parent calls
			return 1 + dl;
		}

		// MIRROR OF ABOVE CODE FOR RIGHT SUBTREE
		// Note that we reach here only when node was not found in left
		// subtree
		int dr = printkdistanceNode(node.right, target, k);
		if (dr != -1) {
			if (dr + 1 == k) {
				System.out.print(node.data);
				System.out.println("");
			} else
				printkdistanceNodeDown(node.left, k - dr - 2);
			return 1 + dr;
		}

		// If target was neither present in left nor in right subtree
		return -1;
	}

	public static void main(String args[]) {
		NodesAtDistanceK nad = new NodesAtDistanceK();
		Node root = null;
		BinaryTree bt = new BinaryTree();
		root = bt.addNode(10, root);
		root = bt.addNode(3, root);
		root = bt.addNode(-1, root);
		root = bt.addNode(8, root);
		root = bt.addNode(-6, root);
		root = bt.addNode(-11, root);
		root = bt.addNode(18, root);
		root = bt.addNode(11, root);
		root = bt.addNode(13, root);
		root = bt.addNode(26, root);
		root = bt.addNode(27, root);
		nad.printkdistanceNode(root, root.left.right, 2);
	}
}
