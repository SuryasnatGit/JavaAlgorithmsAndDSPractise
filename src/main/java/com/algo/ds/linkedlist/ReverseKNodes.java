package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/
 * 
 * Given a linked list, write a function to reverse every k ListNodes (where k is an input to the
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
	public ListNode reverse(ListNode head, int k) {
		if (head == null) {
			return null;
		}
		ListNode previous = null;
		ListNode current = head;
		ListNode next = null;
		int count = 0;

		// reverse first k ListNodes of linked list
		while (current != null && count < k) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
			count++;
		}
		// next is now pointer to (k + 1)th ListNode. recursively call for the list starting from current and
		// make rest of list as next of first ListNode.
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
	public ListNode reverseUsingStack(ListNode head, int k) {
		Stack<ListNode> stack = new Stack<>();
		ListNode current = head;
		ListNode previous = null;

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

//	public static void main(String args[]) {
//		LinkList ll = new LinkList();
//		ListNode head = null;
//		head = ll.addListNode(1, head);
//		head = ll.addListNode(2, head);
//		head = ll.addListNode(3, head);
//		head = ll.addListNode(4, head);
//		head = ll.addListNode(5, head);
//		head = ll.addListNode(6, head);
//		head = ll.addListNode(7, head);
//		head = ll.addListNode(8, head);
//		ReverseKListNodes rn = new ReverseKListNodes();
//		head = rn.reverseUsingStack(head, 3);
//		ll.printList(head);
//	}
}
