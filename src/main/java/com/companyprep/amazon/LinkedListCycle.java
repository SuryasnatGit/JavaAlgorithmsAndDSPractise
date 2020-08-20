package com.companyprep.amazon;

import com.algo.common.ListNode;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * 
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the
 * linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 * 
 * Note: Do not modify the linked list.
 * 
 * Example 1:
 * 
 * Input: head = [3,2,0,-4], pos = 1
 * 
 * Output: tail connects to node index 1
 * 
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 * Example 2:
 * 
 * Input: head = [1,2], pos = 0
 * 
 * Output: tail connects to node index 0
 * 
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 * 
 * Example 3:
 * 
 * Input: head = [1], pos = -1
 * 
 * Output: no cycle
 * 
 * Explanation: There is no cycle in the linked list.
 *
 * TODO: not working.
 */
public class LinkedListCycle {

	public ListNode detectCycle(ListNode head) {

		ListNode meet = findMeetingPoint(head);

		if (meet == null) {
			return null;
		}

		ListNode walk1 = head;
		ListNode walk2 = meet;

		while (walk1 != walk2) {
			walk1 = walk1.next;
			walk2 = walk2.next;
		}

		return walk1;
	}

	private ListNode findMeetingPoint(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (fast == slow)
				return slow;
		}
		return null;
	}

	public static void main(String[] args) {
		LinkedListCycle ll = new LinkedListCycle();

		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n3;

		System.out.println(ll.detectCycle(n1));
	}
}
