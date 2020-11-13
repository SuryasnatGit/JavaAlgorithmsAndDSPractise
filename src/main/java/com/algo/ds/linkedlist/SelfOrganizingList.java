package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

/**
 * https://opendsa-server.cs.vt.edu/ODSA/Books/Everything/html/SelfOrg.html
 *
 */
public class SelfOrganizingList {

	public int length = 0; // The length of the linked list is not important. It is not used in the following methods,
							// but it is maintained
	public ListNode head = null; // Pointer to the chain header node
	public ListNode tail = null; // Pointer to the node at the end of the list

	public SelfOrganizingList() {

	}

	int isEmpty() { // Judge whether the chain list is empty or not. If the head pointer is 0, it means empty
		return head == null ? 1 : 0;
	}

	// Add data to chain header
	void addToHead(int aValue) {
		head = new ListNode(aValue); // Add node
		if (tail == null)
			tail = head; // If the tail pointer is null, it means that the list itself is null and the tail pointer is
							// updated
		length++;
	}

	// Add data to the end of the list
	void addToTail(int aValue) {
		// If the tail pointer is null, it means that the list itself is null, and the tail pointer and the head pointer
		// are updated
		if (tail == null)
			head = tail = new ListNode(aValue);
		// Otherwise, only the tail pointer will be updated
		else
			tail = tail.next = new ListNode(aValue);
		length++;
	}

	// Delete data from chain header
	int deleteFromHead() {
		// If the list is empty and cannot be deleted, return - 1
		if (head == null)
			return -1;
		// Record deleted value
		int deletedValue = head.data;
		if (head == tail) // If there is only one node in the list, both the head and tail are empty after deletion
			head = tail = null;
		else
			head = head.next;
		length--;
		return deletedValue; // Return deleted value
	}

	int deleteFromTail() {
		// If the list is empty and cannot be deleted, return - 1
		if (head == null)
			return -1;
		// Record deleted value
		int deletedValue = tail.data;
		if (head == tail) // If there is only one node in the list, both the head and tail are empty after deletion
			head = tail = null;
		else {
			ListNode now = null;
			for (now = head; now.next != tail; now = now.next)
				; // Traverse the linked list to find the penultimate node
			now.next = null;
			tail = now;
		}
		length--;
		return deletedValue; // Return deleted value
	}

	int deleteNode(int index) {
		if (index <= 0)
			return -1;
		// If the list is empty and cannot be deleted, return - 1
		if (head == null)
			return -1;
		ListNode now = null;
		int cnt = 1; // Counter
		/*
		 * The following cyclic actions: Case 1: normally, find the previous node of the node to be deleted; Case 2: if
		 * the number of nodes to be deleted is greater than the number of summary points, the found value will stop at
		 * NULL
		 */
		for (now = head; cnt + 1 < index && now != null; cnt++, now = now.next)
			;

		if (now == null)
			return -1; // Situation 2 occurs, direct return
		// Record deleted node pointer and value
		ListNode deletedNode = now.next;
		int deletedValue = deletedNode.data;

		// Case 1: delete this node
		if (cnt == index - 1)
			now.next = now.next.next;
		length--;
		// If the end node is deleted, update the tail
		if (deletedNode.next == null) {
			tail = now;
		}
		return deletedValue;
	}

	// Inserts a node to the specified ordinal
	void addNode(int value, int index) { // index starts from 1
		if (index <= 0)
			return;
		// Deal with the case that the link list is empty to insert a node
		if (head == null && index == 1) {
			head = tail = new ListNode(value);
			length++;
			return;
		}
		// Handle inserting data in the chain header
		if (index == 1) {
			head = new ListNode(value);
			length++;
			return;
		}
		ListNode aheadOfAdd = null;
		int cnt = 1;
		// Loop to find the node before the node to insert
		for (aheadOfAdd = head, cnt = 1; aheadOfAdd != null && cnt + 1 < index; cnt++, aheadOfAdd = aheadOfAdd.next)
			;
		// If the above loop is terminated because the condition "CNT + 1 < index" is not tenable, then the number of
		// nodes to be inserted exceeds the total size of the linked list
		if (index != cnt + 1)
			return;
		if (aheadOfAdd == null)
			return;

		aheadOfAdd.next = new ListNode(value);
		length++;
		// If a node is inserted into the tail, update the tail
		if (aheadOfAdd.next.next == null)
			tail = aheadOfAdd.next;
	}

	void printSelf() { // Print linked list content
		ListNode nodeToPrint = this.head;
		System.out.println("Content of LinkedList:");
		while (nodeToPrint != null) {
			System.out.print(String.format("%d ", nodeToPrint.data));
			if (nodeToPrint == tail)
				System.out.print("tail is reached!\n");
			nodeToPrint = nodeToPrint.next;
		}
		System.out.print("\n");
	}

	// Determine whether the linked list contains a value
	ListNode contains(int value) {
		for (ListNode now = head; now != null; now = now.next)
			if (now.data == value)
				return now;
		return null;
	}

	int nodeIndex(int value) {
		int cnt = 0;
		ListNode now = null;
		if (head == null)
			return -1;
		for (cnt = 1, now = head; now != null && now.data != value; now = now.next, cnt++)
			;
		return cnt;
	}

	ListNode search(int value) {
		ListNode now = contains(value);
		if (now == null)
			return null;
		deleteNode(nodeIndex(value));
		addToHead(value);
		return head;
	}

}
