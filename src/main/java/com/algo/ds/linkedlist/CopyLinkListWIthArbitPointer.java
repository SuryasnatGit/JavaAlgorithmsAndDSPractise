package com.algo.ds.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the
 * list or null. Return a deep copy of the list.
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyLinkListWIthArbitPointer {

	static class RandomNode {
		int label;
		RandomNode next, random;

		RandomNode(int x) {
			this.label = x;
		}

		@Override
		public String toString() {
			return "RandomNode [label=" + label + "]";
		}

	}

	/**
	 * Approach 1 - Time O(n) Space O(n)
	 * 
	 * @param head
	 * @return
	 */
	public RandomNode copyRandomList(RandomNode head) {
		Map<RandomNode, RandomNode> map = new HashMap<>();

		// create 2 pointers. 1 for head and 2 for dummy
		RandomNode p = head; // pointer to head
		RandomNode dummy = new RandomNode(0);
		RandomNode q = dummy; // pointer to dummy

		// traverse for copying next pointer
		while (p != null) {
			q.next = new RandomNode(p.label);
			map.put(p, q.next);
			q = q.next;
			p = p.next;
		}

		// reset pointers to head of both LLs
		p = head;
		q = dummy;

		// traverse for copying random pointer
		while (p != null) {
			q.next.random = map.get(p.random);
			p = p.next;
			q = q.next;
		}

		return dummy.next;
	}

	/**
	 * Approach 2 - Optimized. But this will modify original structure.
	 * 
	 * Time complexity is O(n) Space complexity is O(1)
	 * 
	 * @param head
	 * @return
	 */
	public RandomNode copyRandomList1(RandomNode head) {
		if (head == null) {
			return null;
		}

		RandomNode current = head;
		while (current != null) {
			RandomNode newNode = new RandomNode(current.label);
			newNode.next = current.next;
			newNode.random = current.random;
			current.next = newNode;
			current = newNode.next;
		}

		current = head;
		while (current != null) {
			RandomNode next = current.next;
			if (next.random != null) {
				next.random = next.random.next;
			}
			current = current.next.next;
		}

		current = head;
		RandomNode dummy = new RandomNode(0);
		RandomNode newCurrent = dummy;
		while (current != null) {
			newCurrent.next = current.next;
			newCurrent = newCurrent.next;
			current.next = current.next.next;
			current = current.next;
		}

		return dummy.next;
	}

	public void printList(RandomNode node) {
		System.out.println();
		while (node != null) {
			System.out.print(node.label + "->");
			node = node.next;
		}
		System.out.print("null");
	}

	public static void main(String args[]) {

		CopyLinkListWIthArbitPointer cll = new CopyLinkListWIthArbitPointer();

		RandomNode randomNode = new RandomNode(-1);
		RandomNode randomNode1 = new RandomNode(4);
		RandomNode randomNode2 = new RandomNode(8);
		RandomNode randomNode3 = new RandomNode(-3);
		RandomNode randomNode4 = new RandomNode(7);
		randomNode.next = randomNode1;
		randomNode1.next = randomNode2;
		randomNode2.next = randomNode3;
		randomNode3.next = randomNode4;

		randomNode.random = randomNode1;
		randomNode2.random = randomNode;
		randomNode1.random = randomNode3;
		randomNode3.random = randomNode2;

		cll.printList(randomNode);
		RandomNode copyRandomList1 = cll.copyRandomList(randomNode);
		cll.printList(copyRandomList1);
	}
}
