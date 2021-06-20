package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

/**
 * http://www.geeksforgeeks.org/sum-of-two-linked-lists/<br/>
 * Test case.. <br/>
 * 1. Either of list is null.<br/>
 * 2. Size of list1 is greater, equal or smaller than list2.<br/>
 * 3. Add with carry in main. <br/>
 * 4. Add with carry in remaining.
 * 
 * <br/>
 * Steps: Following are the steps. <br/>
 * 1) Calculate sizes of given two linked lists. <br/>
 * 
 * 2) If sizes are same, then calculate sum using recursion. Hold all nodes in recursion call stack till the rightmost
 * node, calculate sum of rightmost nodes and forward carry to left side.<br/>
 * 
 * 3) If size is not same, then follow below steps: .<br/>
 * a) Calculate difference of sizes of two linked lists. Let the difference be diff .<br/>
 * 
 * b) Move diff nodes ahead in the bigger linked list. Now use step 2 to calculate sum of smaller list and right
 * sub-list (of same size) of larger list. Also, store the carry of this sum. .<br/>
 * 
 * c) Calculate sum of the carry (calculated in previous step) with the remaining left sub-list of larger list. Nodes of
 * this sum are added at the beginning of sum list obtained previous step.
 * 
 * <n/> Time Complexity: O(m+n) where m and n are the sizes of given two linked lists.
 * 
 * Category : Hard
 */
public class AddNumberRepresentedByLinkList {

	public ListNode addNumbers(ListNode head1, ListNode head2) {
		ReverseLinkedList rll = new ReverseLinkedList();
		head1 = rll.iterative(head1);
		head2 = rll.iterative(head2);
		ListNode result = add(head1, head2);
		return rll.iterative(result);
	}

	private ListNode add(ListNode head1, ListNode head2) {
		if (head1 == null && head2 == null) {
			return null;
		}

		ListNode dummy = new ListNode(-1);
		ListNode current = dummy;

		int carry = 0;
		while (head1 != null && head2 != null) {
			int sum = head1.data + head2.data + carry;
			carry = sum / 10;
			current.next = new ListNode(sum % 10);
			current = current.next;
			head1 = head1.next;
			head2 = head2.next;
		}

		while (head1 != null) {
			int sum = head1.data + carry;
			carry = sum / 10;
			current.next = new ListNode(sum % 10);
			current = current.next;
			head1 = head1.next;
		}

		while (head2 != null) {
			int sum = head2.data + carry;
			carry = sum / 10;
			current.next = new ListNode(sum % 10);
			current = current.next;
			head2 = head2.next;
		}

		if (carry != 0) {
			current.next = new ListNode(carry);
			current = current.next;
		}

		return dummy.next;
	}

	public static void main(String args[]) {
		AddNumberRepresentedByLinkList anr = new AddNumberRepresentedByLinkList();

		ListNode h1 = new ListNode(3);
		h1.next = new ListNode(4);
		h1.next.next = new ListNode(5);

		ListNode h2 = new ListNode(7);
		h2.next = new ListNode(4);
		h2.next.next = new ListNode(5);

		ListNode res = anr.addNumbers(h1, h2);
		MiscellaneousLinkedListProblems m = new MiscellaneousLinkedListProblems();
		m.displayList(res);
	}
}
