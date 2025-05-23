package com.algo.ds.linkedlist;

/**
 * Find middle element in linklist. Use two pointer approach. Test cases 0,1,2,3,4 and so on nodes
 * 
 * @author tusroy
 *
 */
public class MiddleElementOfLinkList {

	public int middle(Node head) {
		if (head == null || head.next == null) {
			return head.data;
		}

		Node slow = head;
		Node fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow.data;
	}

	public static void main(String args[]) {
		MiddleElementOfLinkList mle = new MiddleElementOfLinkList();
		SingleLinkedList ll = new SingleLinkedList();
		Node head = null;
		ll.addNodeAtEnd(1);
		System.out.println(mle.middle(head));
		ll.addNode(2);
		System.out.println(mle.middle(head));
//		head = ll.addNode(3, head);
//		System.out.println(mle.middle(head));
//		head = ll.addNode(4, head);
//		System.out.println(mle.middle(head));
//		head = ll.addNode(5, head);
//		System.out.println(mle.middle(head));
	}
}
