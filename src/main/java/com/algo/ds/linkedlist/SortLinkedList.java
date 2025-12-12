package com.algo.ds.linkedlist;

/**
 * <p>
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * 3 main steps :
 * 
 * 1. find the midpoint of the LL (using fast and slow pointers) 2. merging 2 ordered LL 3. divide and conquer recursion
 * </p>
 * 
 * Priority : Medium
 */
public class SortLinkedList {

	public Node sort(Node head) {
		if (head == null || head.next == null) {
			return head;
		}

		Node mid = midpoint(head);
		Node right = sort(mid.next);
		mid.next = null;
		Node left = sort(head);

		return merge(left, right);
	}

	private Node midpoint(Node head) {
		Node slow = head;
		Node fast = head.next;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		return slow;
	}

	private Node merge(Node left, Node right) {
		Node dummy = new Node(0);
		Node pointer = dummy;

		while (left != null && right != null) {
			if (left.data <= right.data) {
				pointer.next = left;
				left = left.next;
			} else {
				pointer.next = right;
				right = right.next;
			}
			pointer = pointer.next;
		}

		if (left != null) {
			pointer.next = left;
		} else {
			pointer.next = right;
		}

		return dummy.next;
	}

	public static void main(String[] args) {
		SortLinkedList sll = new SortLinkedList();
		Node head = new Node(7);
		head.next = new Node(5);
		head.next.next = new Node(9);
		head.next.next.next = new Node(3);
		head.next.next.next.next = new Node(6);
		Node sorted = sll.sort(head);
		LinkedListUtils.printList(sorted);
	}
}
