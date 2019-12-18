package com.ctci.treegraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.algo.common.ListNode;
import com.algo.common.TreeNode;

/**
 * CTCI - 4.3
 * 
 * Given a binary tree, design an algorithm which creates a linked list of all the nodes at each
 * depth (e.g., if you have a tree with depth D, you'll have D linked lists). Time Complexity : O(N)
 * 
 * @author surya
 *
 */
public class LinkedListOfDepthsFromBT {

	public void printLists(TreeNode root) {
		List<ListNode> lists = new ArrayList<>(); // collector

		if (root == null)
			return;
		// queue is needed for level or breadth first search
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int numLevelNodes = 0;
		while (!queue.isEmpty()) {
			numLevelNodes = queue.size();
			ListNode head = null; // pointer to head in LL
			ListNode curr = null; // pointer to current in LL
			while (numLevelNodes > 0) {
				TreeNode node = queue.remove();
				ListNode newListNode = new ListNode(node.getData());
				if (head == null) {
					head = newListNode;
					curr = newListNode;
				} else {
					curr.next = newListNode;
					curr = curr.next;
				}
				if (node.getLeft() != null)
					queue.add(node.getLeft());
				if (node.getRight() != null)
					queue.add(node.getRight());

				numLevelNodes--;
			}
			lists.add(head);
		}

		// display
		lists.forEach(ln -> System.out.println(ln));
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(10);
		root.right = new TreeNode(15);
		root.left.left = new TreeNode(20);
		root.left.right = new TreeNode(25);
		root.right.left = new TreeNode(30);
		root.right.right = new TreeNode(35);
		LinkedListOfDepthsFromBT l = new LinkedListOfDepthsFromBT();
		l.printLists(root);

	}

}
