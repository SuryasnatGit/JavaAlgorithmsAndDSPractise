package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

/**
 * Swap two ListNodes in double link list. If it swaps first ListNode its callers responsibility to fix the head.
 * 
 * Given a linked list and two keys in it, swap ListNodes for two given keys. ListNodes should be swapped by changing links.
 * Swapping data of ListNodes may be expensive in many situations when data contains many fields. It may be assumed that all
 * keys in linked list are distinct.
 * 
 * Examples:
 * 
 * Input: 10->15->12->13->20->14, x = 12, y = 20 Output: 10->15->20->13->12->14
 * 
 * Input: 10->15->12->13->20->14, x = 10, y = 20 Output: 20->15->12->13->10->14
 * 
 * Input: 10->15->12->13->20->14, x = 12, y = 13 Output: 10->15->13->12->20->14
 * 
 * 
 * Test cases:<br/>
 * A right neighbor of B.<br/>
 * B right neighbor of A.<br/>
 * A and B not neighbors of each other.<br/>
 * A or B are start or end ListNodes.<br/>
 */
public class SwapTwoListNodesInDoubleLL {

	public void swap(ListNode ListNodeA, ListNode ListNodeB) {
		if (ListNodeA == null || ListNodeB == null) {
			throw new IllegalArgumentException();
		}
		// if B is right neighbor of A
		if (ListNodeA.next == ListNodeB) {
			if (ListNodeA.previous != null) {
				ListNodeA.previous.next = ListNodeB;
				ListNodeB.previous = ListNodeA.previous;
			} else {
				ListNodeB.previous = null;
			}
			if (ListNodeB.next != null) {
				ListNodeB.next.previous = ListNodeA;
				ListNodeA.next = ListNodeB.next;
			} else {
				ListNodeA.next = null;
			}
			ListNodeB.next = ListNodeA;
			ListNodeA.previous = ListNodeB;
		} // else if A is right neighbor of B
		else if (ListNodeB.next == ListNodeA) {
			if (ListNodeB.previous != null) {
				ListNodeB.previous.next = ListNodeA;
				ListNodeA.previous = ListNodeB.previous;
			} else {
				ListNodeA.previous = null;
			}
			if (ListNodeA.next != null) {
				ListNodeA.next.previous = ListNodeB;
				ListNodeB.next = ListNodeA.next;
			} else {
				ListNodeB.next = null;
			}
			ListNodeA.next = ListNodeB;
			ListNodeB.previous = ListNodeA;
		} // if A and B are not neighbors of each other
		else {
			if (ListNodeA.previous != null) {
				ListNodeA.previous.next = ListNodeB;
			}
			if (ListNodeA.next != null) {
				ListNodeA.next.previous = ListNodeB;
			}
			ListNode next = ListNodeB.next;
			ListNode previous = ListNodeB.previous;
			ListNodeB.previous = ListNodeA.previous;
			ListNodeB.next = ListNodeA.next;

			if (next != null) {
				next.previous = ListNodeA;
			}
			if (previous != null) {
				previous.next = ListNodeA;
			}
			ListNodeA.previous = previous;
			ListNodeA.next = next;
		}
	}

	public static void main(String args[]) {
		DoublyLinkedList dll = new DoublyLinkedList();
		dll.insertLast(1);
		dll.insertLast(2);
		dll.insertLast(3);
		dll.insertLast(4);
		dll.insertLast(5);

		SwapTwoListNodesInDoubleLL snt = new SwapTwoListNodesInDoubleLL();
		ListNode ListNodeA = new ListNode(3);
		ListNode ListNodeB = new ListNode(5);
		snt.swap(ListNodeA, ListNodeB);

	}
}
