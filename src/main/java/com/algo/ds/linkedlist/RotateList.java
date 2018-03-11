package com.algo.ds.linkedlist;

/**
 * Date 10/10/2016 Given a list, rotate the list to the right by k places, where
 * k is non-negative.
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
	public Node rotateRight(Node head, int k) {
		if (head == null || k == 0) {
			return head;
		}
		Node slow = head;
		Node fast = head;
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
		Node next = slow.next;
		slow.next = null;
		fast.next = head;
		return next;
	}

	/**
	 * To rotate the linked list counter clockwise, we need to 1. change next of kth
	 * node to NULL, 2. next of last node to previous head node, 3. and finally
	 * change head to (k+1)th node. So we need to get hold of three nodes: kth node,
	 * (k+1)th node and last node. Traverse the list from beginning and stop at kth
	 * node. Store pointer to kth node. We can get (k+1)th node using kthNode->next.
	 * Keep traversing till end and store pointer to last node also. Finally, change
	 * pointers as stated above.
	 * 
	 * @return
	 */
	public Node rorateLeft(Node head, int k) {
		if (head == null || k == 0)
			return head;

		// navigate to kth node
		int count = 1;
		Node current = head;
		while (count < k && current != null) {
			current = current.next;
			count++;
		}

		// if current == null, then it means k >= number of nodes in the linked list.
		// in this case don't do anything.
		if (current == null)
			return head;

		// store kth node
		Node kthNode = current;

		// navigate to last node
		while (current.next != null)
			current = current.next;

		// point last node next to current head
		current.next = head;

		// make next of kth node as new head
		head = kthNode.next;

		// point next of kth node to null
		kthNode.next = null;

		return head;
	}
	
	public static void main(String[] args) {
		LinkList ll = new LinkList();
		Node head = null;
		head = ll.addNode(1, head);
		head = ll.addNode(2, head);
		head = ll.addNode(3, head);
		head = ll.addNode(4, head);
		head = ll.addNode(5, head);
		head = ll.addNode(6, head);
		ll.printList(head);
		RotateList rl = new RotateList();
		head = rl.rorateLeft(head, 4);
		ll.printList(head);
	}
}
