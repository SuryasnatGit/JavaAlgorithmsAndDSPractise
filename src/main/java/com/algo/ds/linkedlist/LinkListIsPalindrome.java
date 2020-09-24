package com.algo.ds.linkedlist;

import java.util.Stack;

import com.algo.common.ListNode;

/**
 * http://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 * 
 * Test cases: <br/>
 * odd number of nodes <br/>
 * even number of nodes <br/>
 * 0 1 or more nodes <br/>
 * palindrome list <br/>
 * non palindrom list
 * 
 * Category : Hard
 */
public class LinkListIsPalindrome {

	/**
	 * Time complexity of above method is O(n), but it requires O(n) extra space.
	 * 
	 * @param headRef
	 * @param head
	 * @return
	 */
	public boolean isPalindrome_usingStack(ListNode head) {
		Stack<Integer> stack = new Stack<>();
		ListNode temp = head;
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

	/**
	 * Time Complexity O(n) Auxiliary Space: O(1)
	 * 
	 * @param head
	 * @return
	 */
	private ListNode secondHalf;

	public boolean isPalindrome_usingReverseMethod(ListNode head) {
		// initially slow and fast point to head
		ListNode slow = head;
		ListNode fast = head;
		ListNode prevToSlow = head; // points to a node previous to slow pointer
		ListNode midNode = null; // pointer for middle node(this will be null for even number of nodes and not null for
									// odd
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

	private boolean compare(ListNode head1, ListNode head2) {
		ListNode temp1 = head1;
		ListNode temp2 = head2;

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
		ListNode prev = null;
		ListNode current = secondHalf;
		ListNode next;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		secondHalf = prev;
	}

	public static void main(String args[]) {
		SingleLinkedList ll = new SingleLinkedList();
		ll.addNodeAtFront(1);
		ll.addNodeAtEnd(0);
		ll.addNodeAtEnd(0);

		LinkListIsPalindrome llp = new LinkListIsPalindrome();
		System.out.println(llp.isPalindrome_usingStack(ll.getHead()));
		System.out.println(llp.isPalindrome_usingReverseMethod(ll.getHead()));
	}
}
