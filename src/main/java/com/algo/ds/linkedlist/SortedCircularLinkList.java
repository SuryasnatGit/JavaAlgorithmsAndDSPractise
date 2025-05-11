package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

/**
 * http://www.geeksforgeeks.org/sorted-insert-for-circular-linked-list/
 * 
 * Test cases: Insert 2nd element smaller than head.<br/>
 * Insert 2nd element larger than head.<br/>
 * Insert element larger than tail.<br/>
 * Insert element just before tail.<br/>
 * Insert element somewhere between head and tail.<br/>
 */
public class SortedCircularLinkList {

	public ListNode add(ListNode head, int data) {
		if (head == null) {
			head = new ListNode(data);
			head.next = head;
			return head;
		}
		ListNode ListNode = new ListNode(data);
		ListNode tail = getTail(head);
		if (ListNode.data < head.data) {
			ListNode.next = head;
			tail.next = ListNode;
			return ListNode;
		}
		ListNode current = head;
		ListNode pre = null;
		while (current != tail && ListNode.data >= current.data) {
			pre = current;
			current = current.next;
		}
		if (ListNode.data < current.data) {
			ListNode.next = current;
			pre.next = ListNode;
		} else {
			ListNode.next = tail.next;
			tail.next = ListNode;
		}
		return head;
	}

	private ListNode getTail(ListNode head) {
		ListNode temp = head;
		while (temp.next != head) {
			temp = temp.next;
		}
		return temp;
	}

	public void printList(ListNode head) {
		if (head == null) {
			return;
		}
		ListNode current = head.next;
		System.out.println(head.data);
		while (current != head) {
			System.out.println(current.data);
			current = current.next;
		}
	}

//	public static void main(String args[]) {
//		SortedCircularLinkList scll = new SortedCircularLinkList();
//		ListNode head = null;
//		head = scll.addInterval(head, 10);
//		head = scll.addInterval(head, 12);
//		head = scll.addInterval(head, -1);
//		head = scll.addInterval(head, -5);
//		head = scll.addInterval(head, 11);
//		head = scll.addInterval(head, 7);
//
//		scll.printList(head);
//	}
}
