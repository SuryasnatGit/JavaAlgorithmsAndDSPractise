package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * 
 * For example,
 * 
 * Given linked list: 1->2->3->4->5, and n = 2.
 * 
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * 
 * Note: Given n will always be valid. Try to do this in one pass.
 *
 */
public class RemoveNthNode {

	public ListNode removeNthFromEnd(ListNode head, int n) {
		// Head may change, so must use dummy node
		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode prevN = findPreviousNode(dummy, n);

		if (prevN.next != null) { // Not the last element in linked list
			prevN.next = prevN.next.next;
		}

		return dummy.next;
	}

	// This is easier to understand
	ListNode findPreviousNode(ListNode preHead, int n) {
		ListNode slow = preHead;
		ListNode fast = preHead;

		for (int i = 1; i <= n; i++) { // How many steps to go. Fast is N steps ahead
			fast = fast.next;
		}

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow;
	}

	ListNode findPreviousNode2(ListNode preHead, int n) {
		ListNode slow = preHead;
		ListNode fast = preHead.next;

		for (int i = 1; i < n; i++) { // How many steps to go
			fast = fast.next;
		}

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow;
	}

	// By using length
	public ListNode removeNthFromEnd2(ListNode head, int n) {
		int len = 0;
		ListNode cur = head;
		while (cur != null) {
			len++;
			cur = cur.next;
		}

		int steps = len - n;
		ListNode dummy = new ListNode(-1);
		ListNode pre = dummy;

		for (int i = 0; i < steps; i++) {
			pre.next = head;
			pre = pre.next;
			head = head.next;
		}
		// Skip the one to remove

		pre.next = head == null ? null : head.next;

		return dummy.next;
	}
}
