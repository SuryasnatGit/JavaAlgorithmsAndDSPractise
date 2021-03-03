package com.leetcode;

import java.util.Stack;

import com.algo.common.ListNode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * You may not modify the values in the list's nodes. Only nodes itself may be changed.
 * 
 * Input: head = [1,2,3,4] Output: [2,1,4,3]
 * 
 * Example 2:
 * 
 * Input: head = [] Output: []
 * 
 * Example 3:
 * 
 * Input: head = [1] Output: [1]
 *
 */
public class SwapPairsInLinkedList {

	// Good
	public ListNode swapPairs(ListNode node) {
		ListNode dummy = new ListNode(-1);
		ListNode prev = dummy;

		while (node != null && node.next != null) {
			ListNode first = node;
			ListNode second = node.next;
			ListNode next = node.next.next;

			first.next = null;
			prev.next = second;
			second.next = first;

			prev = first;
			node = next;
		}

		if (node != null) {
			prev.next = node;
		}

		return dummy.next;
	}

	public ListNode swapStack(ListNode node, int k) {
		ListNode dummy = new ListNode(-1);
		ListNode prev = dummy;
		Stack<ListNode> stack = new Stack<ListNode>(); // Use LinkedList instead

		while (node != null) {
			while (!stack.isEmpty()) {
				ListNode now = stack.pop();
				now.next = null;
				prev.next = now;
				prev = prev.next;
			}

			for (int i = 0; i < k && node != null; i++) {
				stack.push(node);
				node = node.next;
			}
		}

		while (!stack.isEmpty()) { // If we dont want to reverse the last section, reverse the stack here
			ListNode now = stack.pop();
			now.next = null;
			prev.next = now;
			prev = prev.next;
		}

		return dummy.next;
	}
}
