package com.algo.ds.linkedlist;

import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/
 * 
 * Given a linked list, write a function to reverse every k nodes (where k is an input to the
 * function). Example: Inputs: 1->2->3->4->5->6->7->8->NULL and k = 3 Output:
 * 3->2->1->6->5->4->8->7->NULL.
 * 
 * Inputs: 1->2->3->4->5->6->7->8->NULL and k = 5 Output: 5->4->3->2->1->8->7->6->NULL.
 * 
 */
public class ReverseKNodes {

	/**
	 * complexity - O(n)
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public Node reverse(Node head, int k) {
		if (head == null) {
			return null;
		}
		Node previous = null;
		Node current = head;
		Node next = null;
		int count = 0;

		// reverse first k nodes of linked list
		while (current != null && count < k) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
			count++;
		}
		// next is now pointer to (k + 1)th node. recursively call for the list starting from current and
		// make rest of list as next of first node.
		if (next != null)
			head.next = reverse(current, k);
		return previous;
	}

	/**
	 * time complexity - O(n). This algorithm uses O(k) extra space.
	 * 
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public Node reverseUsingStack(Node head, int k) {
		Stack<Node> stack = new Stack<>();
		Node current = head;
		Node previous = null;

		while (current != null) {
			// update stack with k number of elements
			int count = 0;
			while (current != null && count < k) {
				stack.push(current);
				current = current.next;
				count++;
			}
			while (!stack.isEmpty()) {
				if (previous == null) {
					previous = stack.peek();
					head = previous;
					stack.pop();
				} else {
					previous.next = stack.peek();
					previous = previous.next;
					stack.pop();
				}
			}
		}
		// next of last element will point to null
		previous.next = null;

		return head;
	}

	public static void main(String args[]) {
		LinkList ll = new LinkList();
		Node head = null;
		head = ll.addNode(1, head);
		head = ll.addNode(2, head);
		head = ll.addNode(3, head);
		head = ll.addNode(4, head);
		head = ll.addNode(5, head);
		head = ll.addNode(6, head);
		head = ll.addNode(7, head);
		head = ll.addNode(8, head);
		ReverseKNodes rn = new ReverseKNodes();
		head = rn.reverseUsingStack(head, 3);
		ll.printList(head);
	}
}
