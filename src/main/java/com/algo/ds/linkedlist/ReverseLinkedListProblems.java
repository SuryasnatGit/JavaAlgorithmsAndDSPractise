package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

public class ReverseLinkedListProblems {

	/*
	 * Reverse linked list
	 * 
	 */
	public ListNode rev_iterative(ListNode head) {

		ListNode result = null;
		ListNode curr = head;

		while (curr != null) {
			ListNode next = curr.next;

			curr.next = result;
			result = curr;

			curr = next;
		}

		return result;
	}

	public ListNode rev_recursive(ListNode head) {
		return recursive(head, null, head);
	}

	private ListNode recursive(ListNode curr, ListNode prev, ListNode head) {
		// base case: end of the list reached
		if (curr == null) {
			// fix head pointer
			head = prev;
			return head;
		}

		// recur for next node and pass current node as previous node
		head = recursive(curr.next, curr, head);

		// fix current node(Nodes following it are already fixed)
		curr.next = prev;

		return head;
	}

	/*
	 * Reverse a linked list from position m _to _n. Do it in one-pass.
	 * 
	 * Input: 1->2->3->4->5->NULL, m = 2, n = 4 Output: 1->4->3->2->5->NULL
	 */
	public ListNode reverseListInPositionBetween(ListNode head, int m, int n) {
		if (head == null || head.next == null || m >= n) {
			return head;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;
		head = dummy;

		// move head to one node before m
		for (int i = 1; i < m; i++) {
			if (head == null) {
				return null;
			}
			head = head.next;
		}

		ListNode prev = head;
		ListNode curr = head.next;
		ListNode tail = head.next;
		prev.next = null;

		for (int i = m; i <= n; i++) {
			if (i == n) {
				tail.next = curr.next;
			}
			ListNode next = curr.next;
			curr.next = prev.next;
			prev.next = curr;
			curr = next;
		}

		return dummy.next;
	}

	public static void main(String[] args) {
		ReverseLinkedListProblems rev = new ReverseLinkedListProblems();

		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);

		// ListNode res = rev.iterative(head);
		// ListNode res = rev.rev_recursive(head);
		// new SingleLinkedList().displayList(res);

		ListNode res1 = rev.reverseListInPositionBetween(head, 2, 4);
		new SingleLinkedList().displayList(res1);
	}
}
