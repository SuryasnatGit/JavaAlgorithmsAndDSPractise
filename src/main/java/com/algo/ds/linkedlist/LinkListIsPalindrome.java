package com.algo.ds.linkedlist;

import com.algo.ds.stack.LinkedListStack;
import com.algo.ds.stack.Stack;

/**
 * http://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/ Test cases: <br/>
 * odd number of nodes <br/>
 * even number of nodes <br/>
 * 0 1 or more nodes <br/>
 * palindrome list <br/>
 * non palindrom list
 */
public class LinkListIsPalindrome {

	/**
	 * Time complexity of above method is O(n), but it requires O(n) extra space.
	 * 
	 * @param headRef
	 * @param head
	 * @return
	 */
	public boolean isPalindrome_usingStack(NodeRef headRef, Node head) {
		Stack<Integer> stack = new LinkedListStack<>();
		Node temp = head;
		// 1st pass
		while (temp != null) {
			stack.push(temp.data);
			temp = temp.next;
		}
		// 2nd pass
		temp = head;
		while (temp != null) {
			if (!stack.pop().equals(temp.data))
				return false;
			temp = temp.next;
		}
		return true;
	}

	// declare pointers to head, slow pointer, fast pointer and second half of list for using this method
	Node slow, fast, secondHalf;

	/**
	 * Time Complexity O(n) Auxiliary Space: O(1)
	 * 
	 * @param head
	 * @return
	 */
	public boolean isPalindrome_usingReverseMethod(Node head) {
		// initially slow and fast point to head
		slow = head;
		fast = head;
		Node prevToSlow = head; // points to a node previous to slow pointer
		Node midNode = null; // pointer for middle node(this will be null for even number of nodes and not null for odd
								// number of nodes)
		boolean result = true;
		if (head != null && head.next != null) {
			// move slow and fast pointers
			while (fast != null && fast.next != null) {
				fast = fast.next.next;
				// move prevToSlow
				prevToSlow = slow;
				slow = slow.next;
			}

			// for odd number of nodes fast will be not null. for even number of nodes fast will be null.
			// skip the middle node for odd case and store it so that it can be used later for restoring original list
			if (fast != null) {
				midNode = slow;
				slow = slow.next;
			}

			// now split the lists into 2
			secondHalf = slow;
			// terminate the first half
			prevToSlow.next = null;
			// reverse second half
			reverse();
			// compare first and second halfs
			result = compare(head, secondHalf);
			// restore the original list by again reversing the second half
			reverse();

			// check for midnode nullability
			if (midNode != null) {
				prevToSlow.next = midNode;
				midNode.next = secondHalf;
			} else
				prevToSlow.next = secondHalf;
		}
		return result;
	}

	private boolean compare(Node head1, Node head2) {
		Node temp1 = head1;
		Node temp2 = head2;

		while (temp1 != null && temp2 != null) {
			if (temp1.data == temp2.data) {
				temp1 = temp1.next;
				temp2 = temp2.next;
			} else
				return false;
		}
		if (temp1 == null && temp2 == null)
			return true;

		// will reach here if one is null and other is not null
		return false;
	}

	private void reverse() {
		Node prev = null;
		Node current = secondHalf;
		Node next;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		secondHalf = prev;
	}

	/**
	 * Time Complexity: O(n) Auxiliary Space: O(n) if Function Call Stack size is considered, otherwise O(1).
	 * 
	 * @param head
	 * @param end
	 * @return
	 */
	public boolean isPalindrome(NodeRef head, Node end) {
		if (end == null) {
			return true;
		}
		boolean r = isPalindrome(head, end.next);
		r = r && head.node.data == end.data;
		head.next();
		return r;
	}

	public static void main(String args[]) {
		LinkList ll = new LinkList();
		Node head = null;
		head = ll.addNode(1, head);
		head = ll.addNode(2, head);
		head = ll.addNode(3, head);
		head = ll.addNode(4, head);
		head = ll.addNode(3, head);
		head = ll.addNode(2, head);
		head = ll.addNode(1, head);
		NodeRef nodeRef = new NodeRef();
		nodeRef.node = head;
		LinkListIsPalindrome llp = new LinkListIsPalindrome();
		System.out.println(llp.isPalindrome(nodeRef, head));
		System.out.println(llp.isPalindrome_usingStack(nodeRef, head));
		System.out.println(llp.isPalindrome_usingReverseMethod(head));
	}
}
