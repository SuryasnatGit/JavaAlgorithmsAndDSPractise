package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

/**
 * http://www.geeksforgeeks.org/given-a-linked-list-which-is-sorted-how-will-you-insert-in-sorted-way/ Test cases: 0
 * nodes 1 nodes 2 or more nodes already sorted reverse sorted negative positive numbers
 * 
 * time complexity - O(n)
 */
public class InsertionSortLinkList {

	public ListNode sort(ListNode head, ListNode newnode) {
		// specual case for head node
		if (head == null || head.data >= newnode.data) {
			newnode.next = head;
			head = newnode;
			return newnode;
		}

		ListNode curr = head;
		while (curr.next != null && curr.next.data < newnode.data) {
			curr = curr.next;
		}

		newnode.next = curr.next;
		curr.next = newnode;

		return head;
	}

	private void print(ListNode head) {
		ListNode curr = head;
		while (curr != null) {
			System.out.print(curr.data + "->");
			curr = curr.next;
		}
		System.out.print("null");
		System.out.println();
	}

	private ListNode addNode(int data, ListNode head) {
		ListNode temp = head;
		ListNode node = new ListNode(data);
		if (head == null)
			return node;

		while (head.next != null)
			head = head.next;

		head.next = node;

		return temp;
	}

	public static void main(String args[]) {
		InsertionSortLinkList isll = new InsertionSortLinkList();

		ListNode head = null;

		int[] data = { 5, 7, 9 };
		for (int d : data) {
			head = isll.addNode(d, head);
		}

		isll.print(head);

		head = isll.sort(head, new ListNode(8));

		isll.print(head);
	}
}
