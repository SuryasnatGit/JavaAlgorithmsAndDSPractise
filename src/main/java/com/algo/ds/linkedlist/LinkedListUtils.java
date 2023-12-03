package com.algo.ds.linkedlist;

import com.algo.common.ListNode;

public class LinkedListUtils {

	public static void printList(ListNode head) {
		StringBuilder sb = new StringBuilder();
		while (head != null) {
			sb.append(head.data).append(" ");
			head = head.next;
		}
		System.out.println(sb.toString());
	}
}
