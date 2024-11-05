package com.companyprep;

import java.util.HashSet;
import java.util.Set;

import com.algo.common.ListNode;

/**
 * Category : Easy
 *
 */
public class LinkedListCycle {

	/**
	 * Given a linked list, determine if it has a cycle in it.
	 * 
	 * Follow up: Can you solve it without using extra space?
	 * 
	 * T - O(n) S - O(1)
	 */
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}

		ListNode slow = head;
		ListNode fast = head.next;

		while (slow != fast) {
			if (fast == null || fast.next == null) {
				return false;
			}

			slow = slow.next;
			fast = fast.next.next;
		}

		return true;
	}

	/**
	 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
	 * 
	 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in
	 * the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
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
	 */
	/**
	 * T - O(n) S - O(n)
	 */
	public ListNode detectCycle(ListNode head) {
		Set<ListNode> set = new HashSet<ListNode>();

		ListNode node = head;
		while (node != null) {
			if (set.contains(node)) {
				return node;
			}
			set.add(node);
			node = node.next;
		}
		return null;
	}

	/**
	 * T - O(n) S - O(1)
	 * 
	 * @param head
	 * @return
	 */
	public ListNode detectCycle1(ListNode head) {
		if (head == null) {
			return null;
		}

		ListNode slow = head, fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				break;
			}
		}

		if (fast == null) {
			return null;
		}

		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow;
	}

	public static void main(String[] args) {
		LinkedListCycle ll = new LinkedListCycle();

		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n3;

		System.out.println(ll.hasCycle(n1));
		System.out.println(ll.detectCycle(n1));
		System.out.println(ll.detectCycle1(n1));
	}
}
