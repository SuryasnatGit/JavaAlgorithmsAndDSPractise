package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

/**
 * Given a linklist which has individual sorted componenets sort the entire linst e.g
 * 1-3-6-8-4-5-10-7-9 Here 1,2,6,8 are sorted, 4,5,10 are sorted and 7,9 are sorted Test case null
 * ListNode 1 ListNode 2 sorted ListNodes 2 reverse sorted ListNodes 3 reverse sorted ListNodes 4 ListNodes 2 each sorted
 * among themselves
 */
public class SortNearlySortedList {

	public ListNode sort(ListNode head) {
		ListNode result = null;
		ListNode start = head;
		while (head != null && head.next != null) {
			if (head.data < head.next.data) {
				head = head.next;
			} else {
				ListNode temp = head.next;
				head.next = null;
				result = mergeSort(result, start);
				head = temp;
				start = temp;
			}
		}
		result = mergeSort(result, start);
		return result;
	}

	private ListNode mergeSort(ListNode head1, ListNode head2) {
		if (head1 == null) {
			return head2;
		}
		if (head2 == null) {
			return head1;
		}
		if (head1.data <= head2.data) {
			head1.next = mergeSort(head1.next, head2);
			return head1;
		} else {
			head2.next = mergeSort(head1, head2.next);
			return head2;
		}
	}

//	public static void main(String args[]) {
//		LinkList ll = new LinkList();
//		ListNode head = null;
//		head = ll.addListNode(1, head);
//		head = ll.addListNode(2, head);
//		head = ll.addListNode(3, head);
//		head = ll.addListNode(7, head);
//		head = ll.addListNode(5, head);
//		head = ll.addListNode(6, head);
//		head = ll.addListNode(13, head);
//		head = ll.addListNode(11, head);
//		head = ll.addListNode(12, head);
//
//		SortNearlySortedList sns = new SortNearlySortedList();
//		head = sns.sort(head);
//		ll.printList(head);
//	}
}
