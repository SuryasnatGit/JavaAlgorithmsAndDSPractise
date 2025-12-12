package com.algo.ds.linkedlist;

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
	 * To rotate the linked list counter clockwise, we need to 1. change next of kth Node to NULL, 2. next of last
	 * Node to previous head Node, 3. and finally change head to (k+1)th Node. So we need to get hold of
	 * three nodes: kth Node, (k+1)th Node and last Node. Traverse the list from beginning and stop at kth
	 * Node. Store pointer to kth Node. We can get (k+1)th Node using kthNode->next. Keep traversing till
	 * end and store pointer to last Node also. Finally, change pointers as stated above.
	 * 
	 * @return
	 */
	public Node rotateLeft(Node head, int k) {
		if (head == null || k == 0)
			return head;

		// navigate to kth Node
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

		// store kth Node
		Node kthNode = current;

		// navigate to last Node
		while (current.next != null)
			current = current.next;

		// point last Node next to current head
		current.next = head;

		// make next of kth Node as new head
		head = kthNode.next;

		// point next of kth Node to null
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

		Node node = rl.rotateRight(sll.getHead(), 4);
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
		Node node1 = rl.rotateLeft(sll1.getHead(), 4);
		sll.displayList(node1);
	}
}
