package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

/**
 * Date 10/10/2016 Given a list, rotate the list to the right by k places, where k is non-negative.
 * 
 * Given 1->2->3->4->5->NULL and k = 2,
 * 
 * return 4->5->1->2->3->NULL.
 *
 * Time complexity O(min(n, k))
 *
 * https://leetcode.com/problems/rotate-list/
 */
public class RotateList {
	public ListNode rotateRight(ListNode head, int k) {
		if (head == null || k == 0) {
			return head;
		}

		ListNode slow = head;
		ListNode fast = head;
		int i = 0;
		while (i < k && fast != null) {
			fast = fast.next;
			i++;
		}

		if (fast == null) {
			return rotateRight(head, k % i);
		}

		while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}

		ListNode next = slow.next;
		slow.next = null;
		fast.next = head;

		return next;
	}

	/**
	 * To rotate the linked list counter clockwise, we need to 1. change next of kth ListNode to NULL, 2. next of last
	 * ListNode to previous head ListNode, 3. and finally change head to (k+1)th ListNode. So we need to get hold of
	 * three nodes: kth ListNode, (k+1)th ListNode and last ListNode. Traverse the list from beginning and stop at kth
	 * ListNode. Store pointer to kth ListNode. We can get (k+1)th ListNode using kthNode->next. Keep traversing till
	 * end and store pointer to last ListNode also. Finally, change pointers as stated above.
	 * 
	 * @return
	 */
	public ListNode rotateLeft(ListNode head, int k) {
		if (head == null || k == 0)
			return head;

		// navigate to kth ListNode
		int count = 1;
		ListNode current = head;
		while (count < k && current != null) {
			current = current.next;
			count++;
		}

		// if current == null, then it means k >= number of nodes in the linked list.
		// in this case don't do anything.
		if (current == null)
			return head;

		// store kth ListNode
		ListNode kthNode = current;

		// navigate to last ListNode
		while (current.next != null)
			current = current.next;

		// point last ListNode next to current head
		current.next = head;

		// make next of kth ListNode as new head
		head = kthNode.next;

		// point next of kth ListNode to null
		kthNode.next = null;

		return head;
	}

	public static void main(String[] args) {
		SingleLinkedList sll = new SingleLinkedList();

		sll.addNodeAtEnd(1);
		sll.addNodeAtEnd(2);
		sll.addNodeAtEnd(3);
		sll.addNodeAtEnd(4);
		sll.addNodeAtEnd(5);
		sll.addNodeAtEnd(6);

		sll.displayList(sll.getHead());
		RotateList rl = new RotateList();

		ListNode node = rl.rotateRight(sll.getHead(), 4);
		System.out.println();

		sll.displayList(node);

		System.out.println();

		SingleLinkedList sll1 = new SingleLinkedList();

		sll1.addNodeAtEnd(1);
		sll1.addNodeAtEnd(2);
		sll1.addNodeAtEnd(3);
		sll1.addNodeAtEnd(4);
		sll1.addNodeAtEnd(5);
		sll1.addNodeAtEnd(6);
		ListNode node1 = rl.rotateLeft(sll1.getHead(), 4);
		sll.displayList(node1);
	}
}
