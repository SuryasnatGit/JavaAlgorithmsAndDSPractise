package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

/**
 * <p>
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * 3 main steps :
 * 
 * 1. find the midpoint of the LL (using fast and slow pointers) 2. merging 2 ordered LL 3. divide and conquer recursion
 * </p>
 */
public class SortLinkedList {

	private ListNode midpoint(ListNode head) {
		ListNode slow = head;
		ListNode fast = head.next;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		return slow;
	}

	private ListNode merge(ListNode left, ListNode right) {
		ListNode dummy = new ListNode(0);
		ListNode pointer = dummy;

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

	public ListNode sort(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode mid = midpoint(head);
		ListNode right = sort(mid.next);
		mid.next = null;
		ListNode left = sort(head);

		return merge(left, right);
	}

	public static void main(String[] args) {
		SortLinkedList sll = new SortLinkedList();
		ListNode head = new ListNode(7);
		head.next = new ListNode(5);
		head.next.next = new ListNode(9);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(6);
		ListNode sorted = sll.sort(head);
		LinkedListUtils.printList(sorted);
	}
}
