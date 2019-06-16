package com.algo.ds.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null. Return a deep copy
 * of the list.
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyLinkListWIthArbitPointer {

	static class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	}

	/**
	 * Approach 1 - Time O(n) Space O(n)
	 * 
	 * @param head
	 * @return
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
		Map<RandomListNode, RandomListNode> map = new HashMap<>();

		// create 2 pointers. 1 for head and 2 for dummy
		RandomListNode p = head; // pointer to head
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode q = dummy; // pointer to dummy

		// traverse for copying next pointer
		while (head != null) {
			q.next = new RandomListNode(p.label);
			map.put(p, q.next);
			q = q.next;
			p = p.next;
		}

		// reset pointers to head of both LLs
		p = head;
		q = dummy;

		// traverse for copying random pointer
		while (head != null) {
			q.next.random = map.get(p.random);
			p = p.next;
			q = q.next;
		}

		return dummy.next;
	}

	/**
	 * Approach 2 - Optimized. But this will modify original structure. Time
	 * complexity is O(n) Space complexity is O(1)
	 * 
	 * @param head
	 * @return
	 */
	public RandomListNode copyRandomList1(RandomListNode head) {
		if (head == null) {
			return null;
		}

		RandomListNode current = head;
		while (current != null) {
			RandomListNode newNode = new RandomListNode(current.label);
			newNode.next = current.next;
			newNode.random = current.random;
			current.next = newNode;
			current = newNode.next;
		}

		current = head;
		while (current != null) {
			RandomListNode next = current.next;
			if (next.random != null) {
				next.random = next.random.next;
			}
			current = current.next.next;
		}

		current = head;
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode newCurrent = dummy;
		while (current != null) {
			newCurrent.next = current.next;
			newCurrent = newCurrent.next;
			current.next = current.next.next;
			current = current.next;
		}

		return dummy.next;
	}

	public static void main(String args[]) {

		CopyLinkListWIthArbitPointer cll = new CopyLinkListWIthArbitPointer();

		RandomListNode randomListNode = new RandomListNode(-1);
		RandomListNode randomListNode1 = new RandomListNode(4);
		RandomListNode randomListNode2 = new RandomListNode(8);
		RandomListNode randomListNode3 = new RandomListNode(-3);
		RandomListNode randomListNode4 = new RandomListNode(7);
		randomListNode.next = randomListNode1;
		randomListNode1.next = randomListNode2;
		randomListNode2.next = randomListNode3;
		randomListNode3.next = randomListNode4;

		randomListNode.random = randomListNode1;
		randomListNode2.random = randomListNode3;
		randomListNode1.random = randomListNode;
		cll.copyRandomList(randomListNode);
	}
}
