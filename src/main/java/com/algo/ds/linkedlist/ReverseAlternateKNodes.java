package com.algo.ds.linkedlist;

/**
 * http://www.geeksforgeeks.org/reverse-alternate-k-nodes-in-a-singly-linked-list/.
 * 
 * Given a linked list, write a function to reverse every alternate k nodes (where k is an input to
 * the function) in an efficient way. Give the complexity of your algorithm. Example: Inputs:
 * 1->2->3->4->5->6->7->8->9->NULL and k = 3 Output: 3->2->1->4->5->6->9->8->7->NULL.
 * 
 * 
 * Test case:<br/>
 * k is even or odd.<br/>
 * number of nodes are even or odd.<br/>
 * k is less than or equal to 1.
 */
public class ReverseAlternateKNodes {

	public Node reverse(Node head, int k, boolean reverse) {
		if (k <= 1) {
			return head;
		}
		if (head == null) {
			return null;
		}
		if (reverse) {
			int count = 0;
			Node previous = null;
			Node current = head;
			Node next = null;
			while (current != null && count < k) {
				next = current.next;
				current.next = previous;
				previous = current;
				current = next;
				count++;
			}
			head.next = reverse(current, k, !reverse);
			head = previous;
		} else {
			int count = 0;
			Node temp = head;
			// skip k nodes
			while (count < k - 1 && head != null) {
				head = head.next;
				count++;
			}
			if (head != null) {
				head.next = reverse(head.next, k, !reverse);
			}
			head = temp;
		}
		return head;
	}

	public static void main(String args[]) {
		SingleLinkedList ll = new SingleLinkedList();
		Node head = null;
		ll.addNode(1);
		ll.addNode(2);
		ll.addNode(3);
		ll.addNode(4);
		ll.addNode(5);
		ll.addNode(6);
		ll.addNode(7);
		ll.addNode(8);

		ReverseAlternateKNodes ra = new ReverseAlternateKNodes();
		head = ra.reverse(head, 3, true);
		ll.displayList(head);
	}
}
