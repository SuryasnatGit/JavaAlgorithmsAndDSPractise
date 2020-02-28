package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

public class ReverseLinkedList {

	public ListNode iterative(ListNode head) {

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

	public ListNode recursive(ListNode head) {
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

	public static void main(String[] args) {
		ReverseLinkedList rev = new ReverseLinkedList();

		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(5);

		// ListNode res = rev.iterative(head);
		ListNode res = rev.recursive(head);
		System.out.println(res);
	}
}
