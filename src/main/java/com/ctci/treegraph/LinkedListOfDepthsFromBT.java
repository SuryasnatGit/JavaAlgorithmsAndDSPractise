package com.ctci.treegraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.algo.common.ListNode;
import com.algo.common.Node;

/**
 * Given a binary tree, design an algorithm which creates a linked list of all the nodes at each
 * depth (e.g., if you have a tree with depth 0, you'll have 0 linked lists). Time Complexity : O(N)
 * 
 * @author surya
 *
 */
public class LinkedListOfDepthsFromBT {

	public void printLists(Node root) {
		List<ListNode> lists = new ArrayList<>(); // collector

		if (root == null)
			return;
		// queue is needed for level or breadth first search
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		int numLevelNodes = 0;
		while (!queue.isEmpty()) {
			numLevelNodes = queue.size();
			ListNode head = null; // pointer to head in LL
			ListNode curr = null; // pointer to current in LL
			while (numLevelNodes > 0) {
				Node node = queue.remove();
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
		Node root = new Node(5);
		root.left = new Node(10);
		root.right = new Node(15);
		root.left.left = new Node(20);
		root.left.right = new Node(25);
		root.right.left = new Node(30);
		root.right.right = new Node(35);
		LinkedListOfDepthsFromBT l = new LinkedListOfDepthsFromBT();
		l.printLists(root);

	}

}
