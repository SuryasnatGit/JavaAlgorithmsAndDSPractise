package com.algo.ds.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * http://www.geeksforgeeks.org/merge-two-bsts-with-limited-extra-space/
 * 
 * Given two Binary Search Trees(BST), print the elements of both BSTs in sorted form. The expected
 * time complexity is O(m+n) where m is the number of nodes in first tree and n is the number of
 * nodes in second tree. Maximum allowed auxiliary space is O(height of the first tree + height of
 * the second tree).
 * 
 * A similar question has been discussed earlier. Let us first discuss already discussed methods of
 * the previous post which was for Balanced BSTs. The method 1 can be applied here also, but the
 * time complexity will be O(n^2) in worst case. The method 2 can also be applied here, but the
 * extra space required will be O(n) which violates the constraint given in this question. Method 3
 * can be applied here but the step 3 of method 3 cant be done in O(n) for an unbalanced BST.
 * 
 * Thanks to Kumar for suggesting the following solution.
 * 
 * The idea is to use iterative inorder traversal. We use two auxiliary stacks for two BSTs. Since
 * we need to print the elements in sorted form, whenever we get a smaller element from any of the
 * trees, we print it. If the element is greater, then we push it back to stack for the next
 * iteration.
 * 
 * Time Complexity: O(m+n) Auxiliary Space: O(height of the first tree + height of the second tree)
 * 
 * Test cases: <br/>
 * 1. Both tree are null<br/>
 * 2. One of the tree is null<br/>
 * 3. All elements of one tree occur before other tree<br/>
 * 4. All elements of one tree occur after other tree<br/>
 * 5. Elements are mixed<br/>
 * 6. All same elements<br/>
 */
public class PrintTwoBSTInSortedForm {

	public void print(Node root1, Node root2) {
		Deque<Node> s1 = new LinkedList<Node>();
		Deque<Node> s2 = new LinkedList<Node>();

		while (true) {
			if (root1 != null) {
				s1.addFirst(root1);
				root1 = root1.left;
				continue;
			}
			if (root2 != null) {
				s2.addFirst(root2);
				root2 = root2.left;
				continue;
			}
			if (!s1.isEmpty()) {
				root1 = s1.peekFirst();
			}
			if (!s2.isEmpty()) {
				root2 = s2.peekFirst();
			}
			if (root1 != null && root2 != null) {
				if (root1.data <= root2.data) {
					System.out.println(root1.data);
					root1 = s1.pollFirst();
					root1 = root1.right;
					root2 = null;
				} else {
					System.out.println(root2.data);
					root2 = s2.pollFirst();
					root2 = root2.right;
					root1 = null;
				}
			} else if (root1 != null) {
				System.out.println(root1.data);
				root1 = s1.pollFirst();
				root1 = root1.right;

			} else if (root2 != null) {
				System.out.println(root2.data);
				root2 = s2.pollFirst();
				root2 = root2.right;
			}
			if (root1 == null && root2 == null && s1.isEmpty() && s2.isEmpty()) {
				break;
			}
		}
	}

	public static void main(String args[]) {
		PrintTwoBSTInSortedForm ptb = new PrintTwoBSTInSortedForm();
		BinaryTree bt = new BinaryTree();
		Node head = null;
		head = bt.addNode(10, head);
		head = bt.addNode(15, head);
		head = bt.addNode(5, head);
		head = bt.addNode(7, head);
		head = bt.addNode(19, head);
		head = bt.addNode(20, head);
		head = bt.addNode(-1, head);

		Node head1 = null;
		head1 = bt.addNode(-4, head1);
		head1 = bt.addNode(-3, head1);
		head1 = bt.addNode(6, head1);
		head1 = bt.addNode(11, head1);
		head1 = bt.addNode(22, head1);
		head1 = bt.addNode(26, head1);

		ptb.print(head, head1);
	}
}
