package com.algo.ds.linkedlist;

public class ReverseLinkedListProblems {

	/*
	 * Reverse linked list
	 * 
	 */
	public Node rev_iterative(Node head) {

		Node result = null;
		Node curr = head;

		while (curr != null) {
			Node next = curr.next;

			curr.next = result;
			result = curr;

			curr = next;
		}

		return result;
	}

	public Node rev_recursive(Node head) {
		return recursive(head, null, head);
	}

	private Node recursive(Node curr, Node prev, Node head) {
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
	public Node reverseListInPositionBetween(Node head, int m, int n) {
		if (head == null || head.next == null || m >= n) {
			return head;
		}

		Node dummy = new Node(0);
		dummy.next = head;
		head = dummy;

		// move head to one node before m
		for (int i = 1; i < m; i++) {
			if (head == null) {
				return null;
			}
			head = head.next;
		}

		Node prev = head;
		Node curr = head.next;
		Node tail = head.next;
		prev.next = null;

		for (int i = m; i <= n; i++) {
			if (i == n) {
				tail.next = curr.next;
			}
			Node next = curr.next;
			curr.next = prev.next;
			prev.next = curr;
			curr = next;
		}

		return dummy.next;
	}

	public static void main(String[] args) {
		ReverseLinkedListProblems rev = new ReverseLinkedListProblems();

		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);

		// Node res = rev.iterative(head);
		// Node res = rev.rev_recursive(head);
		// new SingleLinkedList().displayList(res);

		Node res1 = rev.reverseListInPositionBetween(head, 2, 4);
		new SingleLinkedList().displayList(res1);
	}
}
