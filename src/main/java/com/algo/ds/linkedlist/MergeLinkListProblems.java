package com.algo.ds.linkedlist;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * http://www.geeksforgeeks.org/merge-sort-for-linked-list/.
 * 
 * complexity - O(n log n) Test cases 0 Nodes 1 Nodes 2 Nodes 3 Nodes fully sorted reverse sorted
 * 
 * Category : Medium
 */
public class MergeLinkListProblems {

	/**
	 * @param head:
	 *            The head of linked list.
	 * @return: You should return the head of the sorted linked list, using constant space complexity.
	 */
	public Node mergeSortList(Node head) {
		if (head == null || head.next == null) {
			return head;
		}
		Node mid = findMiddle(head);
		Node right = mergeSortList(mid.next);
		mid.next = null;
		Node left = mergeSortList(head);

		return merge2SortedLists(left, right);
	}

	private Node findMiddle(Node head) {
		Node slow = head;
		Node fast = head.next;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	public Node merge2SortedLists(Node head1, Node head2) {
		Node dummy = new Node(0);
		Node pointer = dummy;

		while (head1 != null && head2 != null) {
			if (head1.data < head2.data) {
				pointer.next = head1;
				head1 = head1.next;
			} else {
				pointer.next = head2;
				head2 = head2.next;
			}
			pointer = pointer.next;
		}

		if (head1 != null) {
			pointer.next = head1;
		} else {
			pointer.next = head2;
		}

		return dummy.next;
	}

	/**
	 * Divide and Conquer approach.
	 * 
	 * T - O(n log k) S - O(1)
	 */
	public Node mergeKListsNaive(List<Node> lists) {
		if (lists.size() == 0) {
			return null;
		}
		return mergeHelper(lists, 0, lists.size() - 1);
	}

	private Node mergeHelper(List<Node> lists, int start, int end) {
		if (start == end) {
			return lists.get(start);
		}

		int mid = start + (end - start) / 2;
		Node left = mergeHelper(lists, start, mid);
		Node right = mergeHelper(lists, mid + 1, end);
		return merge2SortedLists(left, right);
	}

	/**
	 * Using priority queue.
	 * 
	 * T - O(n log k) S - O(k)
	 */
	public Node mergeKListsHeap(ArrayList<Node> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}

		Comparator<Node> NodeComparator = new Comparator<Node>() {
			public int compare(Node left, Node right) {
				if (left == null) {
					return 1;
				} else if (right == null) {
					return -1;
				}
				return left.data - right.data;
			}
		};

		Queue<Node> heap = new PriorityQueue<Node>(lists.size(), NodeComparator);
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i) != null) {
				heap.add(lists.get(i));
			}
		}

		Node dummy = new Node(0);
		Node tail = dummy;
		while (!heap.isEmpty()) {
			Node head = heap.poll();
			tail.next = head;
			tail = head;
			if (head.next != null) {
				heap.add(head.next);
			}
		}
		return dummy.next;
	}

	public static void main(String args[]) {
		MergeLinkListProblems msll = new MergeLinkListProblems();

		Node head1 = new Node(1);
		head1.next = new Node(5);
		head1.next.next = new Node(3);

		Node head2 = new Node(2);
		head2.next = new Node(4);
		head2.next.next = new Node(6);

		MiscellaneousLinkedListProblems mis = new MiscellaneousLinkedListProblems();

		Node head = msll.mergeSortList(head1);
		mis.displayList(head);

		Node mergedNode = msll.merge2SortedLists(head1, head2);
		mis.displayList(mergedNode);
	}
}
