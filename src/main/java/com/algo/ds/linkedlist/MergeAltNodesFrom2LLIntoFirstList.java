package com.algo.ds.linkedlist;

/**
 * https://www.techiedelight.com/merge-alternate-nodes-two-linked-lists-first-list/
 * 
 */
public class MergeAltNodesFrom2LLIntoFirstList {

	// Function to construct a linked list by merging alternate nodes of
	// two given linked lists using dummy node
	public Node[] merge(Node a, Node b) {
		Node dummy = new Node();
		Node tail = dummy;

		while (true) {
			// empty list cases
			if (a == null) {
				tail.next = null; // Note
				break;
			} else if (b == null) {
				tail.next = a;
				break;
			}
			// common case: move two nodes to tail
			else {
				tail.next = a;
				tail = a;
				a = a.next;

				tail.next = b;
				tail = b;
				b = b.next;
			}
		}

		a = dummy.next;
		return new Node[] { a, b };
	}
}
