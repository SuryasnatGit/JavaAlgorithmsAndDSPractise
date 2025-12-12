package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * You must do this in-place without altering the nodes' values.
 * 
 * For example, Given {1,2,3,4}, reorder it to {1,4,2,3}. Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 *
 * 
 * https://leetcode.com/problems/reorder-list/
 * 
 * Category : Hard
 */
public class ReorderList {

	public void reorderList(ListNode head) {
		ListNode back = frontBackSplit(head);
		back = reverse(back);
		alternateMerge(head, back);
	}

	private ListNode alternateMerge(ListNode head1, ListNode head2) {
		ListNode dummyHead = new ListNode(-1);
		ListNode current = dummyHead;
		while (head1 != null && head2 != null) {
			current.next = head1;
			head1 = head1.next;
			current = current.next;
			current.next = head2;
			head2 = head2.next;
			current = current.next;
		}
		return dummyHead.next;
	}

	/**
	 * 4-5>-6> will return 6->5->4
	 * 
	 * @param head
	 * @return
	 */
	private ListNode reverse(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode front = null;
		ListNode mid = head;
		ListNode next = null;
		while (mid != null) {
			next = mid.next;
			mid.next = front;
			front = mid;
			mid = next;
		}
		return front;
	}

	/**
	 * out of 1->2->3->4->5->6, this will return 4->5->6 after split
	 * 
	 * @param head
	 * @return
	 */
	private ListNode frontBackSplit(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode slow = head;
		head = head.next;
		while (head != null && head.next != null) {
			slow = slow.next;
			head = head.next.next;
		}
		ListNode next = slow.next;
		slow.next = null;
		return next;
	}

	public static void main(String[] args) {
//		ReorderList rl = new ReorderList();
//		LinkList ll = new LinkList();
//		ListNode head1 = null;
//		head1 = ll.addNode(1, head1);
//		head1 = ll.addNode(2, head1);
//		head1 = ll.addNode(3, head1);
//		head1 = ll.addNode(4, head1);
//		head1 = ll.addNode(5, head1);
//		head1 = ll.addNode(6, head1);
//		ll.printList(head1);
//
//		rl.reorderList(head1);
//		ll.printList(head1);
	}
}
