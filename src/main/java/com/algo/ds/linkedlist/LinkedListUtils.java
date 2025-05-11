package com.algo.ds.linkedlist;

public class LinkedListUtils {

	public static void printList(Node head) {
		StringBuilder sb = new StringBuilder();
		while (head != null) {
			sb.append(head.data).append(" ");
			head = head.next;
		}
		System.out.println(sb.toString());
	}
}
