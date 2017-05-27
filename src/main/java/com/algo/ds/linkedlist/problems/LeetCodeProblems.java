package com.algo.ds.linkedlist.problems;

import com.algo.ds.linkedlist.Link;

public class LeetCodeProblems {

	/**
	 * Given a linked list, determine if it has a cycle in it.
	 * 
	 * If we have 2 pointers - fast and slow. It is guaranteed that the fast one
	 * will meet the slow one if there exists a circle.
	 * 
	 * @param head
	 * @return
	 */
	public boolean hasCycle(Link head) {
		Link slow = head;
		Link fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (fast == slow)
				return true;
		}
		return false;
	}

	/**
	 * Merge two sorted linked lists and return it as a new list. The new list
	 * should be made by splicing together the nodes of the first two lists.
	 * 
	 * The key to solve the problem is defining a fake head. Then compare the
	 * first elements from each list. Add the smaller one to the merged list.
	 * Finally, when one of them is empty, simply append it to the merged list,
	 * since it is already sorted.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public Link merge2SortedLists(Link a, Link b) {
		Link tempHead = new Link(0);
		Link headPtr = tempHead;
		Link aPtr = a;
		Link bPtr = b;
		while (aPtr != null && bPtr != null) {
			if (aPtr.getKey() < bPtr.getKey()) {
				headPtr.next = aPtr;
				aPtr = aPtr.next;
			} else {
				headPtr.next = bPtr;
				bPtr = bPtr.next;
			}
			headPtr = headPtr.next;
		}
		if (aPtr != null)
			headPtr.next = aPtr;
		if (bPtr != null)
			headPtr.next = bPtr;
		return tempHead.next;
	}

	public static void main(String[] args) {
		LeetCodeProblems prob = new LeetCodeProblems();
		Link a1 = new Link(10);
//		Link a2 = new Link(20);
//		a1.next = a2;
		System.out.println(a1.toString());
//		Link a3 = new Link(30);
//		a2.next = a3;
//		System.out.println(a1.toString());
//		System.out.println(prob.hasCycle(a1));
//		a3.next = a1;
//		// System.out.println(a1.toString());
//		System.out.println(prob.hasCycle(a1));
		
		
		Link b1 = new Link(5);
		Link b2 = new Link(15);
		b1.next = b2;
		System.out.println(b1.toString());
		System.out.println(prob.merge2SortedLists(a1, b1).toString());
	}

}
