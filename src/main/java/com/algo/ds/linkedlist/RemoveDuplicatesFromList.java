
package com.algo.ds.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesFromList {

	/**
	 * 
	 * http://www.geeksforgeeks.org/remove-duplicates-from-a-sorted-linked-list/ <br/>
	 * 
	 * For example if the linked list is 11->11->11->21->43->43->60 then removeDuplicates() should convert the list to
	 * 11->21->43->60.
	 * 
	 * Test cases : <br/>
	 * All duplicates <br/>
	 * No duplicates <br/>
	 * Duplicates only in starting <br/>
	 * Duplicates only at the end <br/>
	 * 0 1 or more nodes in the list
	 * 
	 * time complexity - O(n), where n is the number of nodes in the list
	 * 
	 * @param head
	 */
	public void removeDuplicatesFromSortedList(Node head) {
		if (head == null) {
			return;
		}
		Node current = head;
		while (current != null && current.next != null) {
			if (current.data == current.next.data) {
				current.next = current.next.next;
			} else {
				current = current.next;
			}
		}
	}

	/**
	 * For example if the linked list is 12->11->12->21->41->43->21 then removeDuplicates() should convert the list to
	 * 12->11->21->41->43. Outer loop is used to pick the elements one by one and inner loop compares the picked element
	 * with rest of the elements. time complexity - O(n^2)
	 * 
	 * 
	 * @param head
	 */
	public void removeDuplicatesFromUnsortedList_using2Loops(Node head) {
		if (head == null)
			return;
		Node pointer1, pointer2, duplicate = null;
		// initially set ptr1 to head
		pointer1 = head;
		while (pointer1 != null && pointer1.next != null) { // outer loop
			pointer2 = pointer1;
			// inner loop
			while (pointer2 != null && pointer2.next != null) {
				if (pointer1.data == pointer2.next.data) {
					duplicate = pointer2.next;
					pointer2.next = pointer2.next.next;
					System.gc();// this removes the duplicate Node
				} else {
					pointer2 = pointer2.next;
				}
			}
			// move ptr1
			pointer1 = pointer1.next;
		}

	}

	/**
	 * Time Complexity: O(n) on average (assuming that hash table access time is O(1) on average).
	 * 
	 * @param head
	 */
	public void removeDuplicatesFromUnsortedList_usingHashing(Node head) {
		if (head == null)
			return;
		Set<Integer> set = new HashSet<>();
		Node current = head;
		Node previous = null;
		while (current != null) {
			int value = current.data;
			if (set.contains(value)) {
				previous.next = current.next;
			} else {
				set.add(value);
				previous = current;
			}
			current = current.next;
		}
	}

	/**
	 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the
	 * original list.
	 * 
	 * Example 1:
	 * 
	 * Input: 1->2->3->3->4->4->5 Output: 1->2->5
	 * 
	 * Example 2:
	 * 
	 * Input: 1->1->1->2->3 Output: 2->3
	 * 
	 * @param head
	 */
	public Node removeAllDuplicates_recursive(Node head) {
		if (head == null || head.next == null)
			return head;
		if (head.data == head.next.data) {
			while (head.next != null && head.data == head.next.data) {
				head = head.next;
			}
			removeAllDuplicates_recursive(head.next);
		} else {
			head.next = removeAllDuplicates_recursive(head.next);
		}
		return head;
	}

	/**
	 * complexity - O(n)
	 * 
	 * @param head
	 * @return
	 */
	public Node removeAllDuplicates_2pointer(Node head) {
		if (head == null || head.next == null)
			return head;

		// dummy Node
		Node dummy = new Node(0);
		dummy.next = head;

		Node prev = dummy;
		Node curr = head;
		Node next;
		while (curr != null && curr.next != null) {
			next = curr.next;
			while (next != null && curr.data == next.data) {
				next = next.next;
			}

			if (next != curr.next) {
				prev.next = next;
			} else {
				prev = curr;
			}
			curr = next;
		}
		return dummy.next;
	}

	public static void main(String args[]) {
		SingleLinkedList ll = new SingleLinkedList();

		ll.addNode(1);
		ll.addNode(1);
		ll.addNode(1);
		ll.addNode(4);
		ll.addNode(4);
		ll.addNode(5);
		ll.addNode(6);
		ll.addNode(6);
		ll.addNode(6);

		RemoveDuplicatesFromList rds = new RemoveDuplicatesFromList();
		rds.removeDuplicatesFromSortedList(ll.getHead());
		ll.displayList();

		ll.addNode(11);
		ll.addNode(21);
		ll.addNode(21);
		ll.addNode(31);
		ll.addNode(41);
		// rds.removeAllDuplicates_2pointer(head1); // sol 1
		// rds.removeAllDuplicates(head1); // sol 2
		rds.removeAllDuplicates_recursive(ll.getHead()); // sol 3
		ll.displayList();

		ll.addNode(12);
		ll.addNode(42);
		ll.addNode(22);
		ll.addNode(42);
		ll.addNode(32);

		rds.removeDuplicatesFromUnsortedList_using2Loops(ll.getHead()); // sol 1
		// rds.removeDuplicatesFromUnsortedList_usingHashing(head2); // sol 2
		ll.displayList();
	}
}
