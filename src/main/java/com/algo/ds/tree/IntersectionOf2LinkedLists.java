package com.algo.ds.tree;

import com.algo.common.ListNode;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * 
 * For example, the following two linked lists:
 * 
 * begin to intersect at node c1.
 * 
 * Example 1:
 * 
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3 Output: Reference of the
 * node with value = 8 Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two
 * lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There
 * are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 * 
 * Example 2:
 * 
 * Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1 Output: Reference of the node
 * with value = 2 Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists
 * intersect). From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes
 * before the intersected node in A; There are 1 node before the intersected node in B.
 * 
 * Example 3:
 * 
 * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2 Output: null Input Explanation: From
 * the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect,
 * intersectVal must be 0, while skipA and skipB can be arbitrary values. Explanation: The two lists do not intersect,
 * so return null.
 * 
 * @author surya
 *
 */
public class IntersectionOf2LinkedLists {

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		int numA = countOfNodes(headA);
		int numB = countOfNodes(headB);

		int diff = Math.abs(numA - numB);

		if (numA > numB) {
			ListNode currA = headA;
			ListNode currB = headB;

			while (diff-- != 0) {
				currA = currA.next;
			}

			while (currA != null && currB != null) {
				if (currA.data == currB.data) {
					return currA;
				}
				currA = currA.next;
				currB = currB.next;
			}
		} else {
			ListNode currA = headA;
			ListNode currB = headB;

			while (diff-- != 0) {
				currB = currB.next;
			}

			while (currA != null && currB != null) {
				if (currA.data == currB.data) {
					return currA;
				}
				currA = currA.next;
				currB = currB.next;
			}
		}

		return null;
	}

	private int countOfNodes(ListNode head) {
		ListNode curr = head;

		int count = 0;
		while (curr != null) {
			count++;
			curr = curr.next;
		}

		return count;
	}

	public static void main(String[] args) {
		IntersectionOf2LinkedLists in = new IntersectionOf2LinkedLists();

		ListNode headA = new ListNode(1);
		headA.next = new ListNode(9);
		headA.next.next = new ListNode(1);
		headA.next.next.next = new ListNode(2);
		headA.next.next.next.next = new ListNode(4);

		ListNode headB = new ListNode(3);
		headB.next = new ListNode(2);
		headB.next.next = new ListNode(4);

		System.out.println(in.getIntersectionNode(headA, headB));

		ListNode headA1 = new ListNode(2);
		headA1.next = new ListNode(6);
		headA1.next.next = new ListNode(4);

		ListNode headB1 = new ListNode(4);
		headB1.next = new ListNode(5);
		headB1.next.next = new ListNode(2);

		System.out.println(in.getIntersectionNode(headA1, headB1));
	}
}
