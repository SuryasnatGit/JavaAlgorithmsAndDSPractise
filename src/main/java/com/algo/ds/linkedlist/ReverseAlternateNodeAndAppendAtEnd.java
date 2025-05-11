package com.algo.ds.linkedlist;

/**
 * http://www.geeksforgeeks.org/given-linked-list-reverse-alternate-Nodes-append-end/
 * 
 * Test case:<br/>
 * Even and odd number of Nodes
 */
public class ReverseAlternateNodeAndAppendAtEnd {

	public void act(Node head) {

		Node result = null;
		SingleLinkedList ll = new SingleLinkedList();
		while (head != null && head.next != null) {
			Node temp = head.next;
			head.next = head.next.next;
			temp.next = null;
			ll.addNodeAtFront(temp.data);
			if (head.next == null) {
				break;
			}
			head = head.next;
		}
		head.next = result;
	}

	public static void main(String args[]) {
//		LinkList ll = new LinkList();
//		Node head = null;
//		head = ll.addNode(1, head);
//		head = ll.addNode(2, head);
//		head = ll.addNode(3, head);
//		head = ll.addNode(4, head);
//		head = ll.addNode(5, head);
//		head = ll.addNode(6, head);
//		ReverseAlternateNodeAndAppendAtEnd ran = new ReverseAlternateNodeAndAppendAtEnd();
//		ran.act(head);
//		ll.printList(head);
	}
}
