package com.algo.ds.tree;

/**
 * Write class for interval tree :
 * 
 * Consider a situation where we have a set of intervals and we need following operations to be
 * implemented efficiently. 1) Add an interval 2) Remove an interval 3) Given an interval x, find if
 * x overlaps with any of the existing intervals.
 * 
 * Interval Tree: The idea is to augment a self-balancing Binary Search Tree (BST) like Red Black
 * Tree, AVL Tree, etc to maintain set of intervals so that all operations can be done in O(Logn)
 * time.
 * 
 * Every node of Interval Tree stores following information. a) i: An interval which is represented
 * as a pair [low, high] b) max: Maximum high value in subtree rooted with this node.
 * 
 * The low value of an interval is used as key to maintain order in BST. The insert and delete
 * operations are same as insert and delete in self-balancing BST used.
 * 
 * 
 * 
 * Keep a max at every node which is max of that subtree. This max is used to decide which direction
 * to move when checking for overlap. When an interval's high is less than max of left side go in
 * that direciton otherwise go in other direction.
 */
class InternalNode {
	int low;
	int high;
	int max;
	InternalNode left;
	InternalNode right;

	@Override
	public String toString() {
		return "InternalNode{" + "max=" + max + ", low=" + low + ", high=" + high + '}';
	}
}

public class IntervalTree {

	public InternalNode insert(InternalNode root, int low, int high) {
		if (root == null) {
			InternalNode node = new InternalNode();
			node.low = low;
			node.high = high;
			node.max = high;
			return node;
		}

		if (low < root.low) {
			root.left = insert(root.left, low, high);
		} else {
			root.right = insert(root.right, low, high);
		}

		root.max = Math.max(root.high, high);
		return root;
	}

	public InternalNode isOverlap(InternalNode root, int low, int high) {
		if (root == null) {
			return null;
		}

		if (root.high >= low && root.low <= high) {
			return root;
		}

		if (root.left != null && root.left.max > low) {
			return isOverlap(root.left, low, high);
		} else {
			return isOverlap(root.right, low, high);
		}
	}

	public static void main(String args[]) {
		IntervalTree it = new IntervalTree();
		InternalNode root = null;
		root = it.insert(root, 10, 15);
		root = it.insert(root, 11, 13);
		root = it.insert(root, 18, 21);
		root = it.insert(root, 20, 25);
		root = it.insert(root, 0, 7);

		System.out.println(it.isOverlap(root, 8, 9));
		System.out.println(it.isOverlap(root, 17, 17));
		System.out.println(it.isOverlap(root, 21, 22));
		System.out.println(it.isOverlap(root, 21, 22));
		System.out.println(it.isOverlap(root, 12, 18));
		System.out.println(it.isOverlap(root, 24, 26));
	}

}
