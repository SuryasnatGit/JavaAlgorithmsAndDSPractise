package com.ctci.linkedlist;


import com.algo.common.ListNode;

/**
 * Implement an algorithm to find the kth to last element of a singly ListNodeed list.
 * 
 * @author surya
 *
 */
public class FindKthToLastElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(2);
		l1.next.next = new ListNode(3);
		l1.next.next.next = new ListNode(4);
		l1.next.next.next.next = new ListNode(5);
		FindKthToLastElement kth = new FindKthToLastElement();
		kth.printList(l1);
		ListNode l = kth.findKthElement(l1, 2);
		System.out.println("kth element is :" + l.getData());
		kth.printKthToLastElement(l1, 2);
	}

	public void printList(ListNode head) {
		while (head != null) {
			System.out.print(head.getData() + "->");
			head = head.next;
		}
		System.out.println("NULL");
	}

	public ListNode findKthElement(ListNode head, int k) {
		ListNode curr = head;
		int c = 1;
		while (curr.next != null && c++ != k) {
			curr = curr.next;
		}
		return k > c ? new ListNode(0) : curr;
	}

	/**
	 * Approach 1 - using recursion
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public int printKthToLastElement(ListNode head, int k) {
		if (head == null)
			return 0;
		int index = printKthToLastElement(head.next, k) + 1;
		if (index == k)
			System.out.println("kth to last element is :" + head.getData());
		return index;
	}

	/**
	 * Approach 2 - using a wrapper class. complexity - O(n)
	 * 
	 * @author surya
	 *
	 */
	class Index {
		public int value = 0;
	}

	public ListNode getKthToLastElement(ListNode head, int k) {
		Index idx = new Index();
		return getKthToLastElement(head, k, idx);
	}

	public ListNode getKthToLastElement(ListNode head, int k, Index index) {
		if (head == null)
			return null;
		ListNode node = getKthToLastElement(head.next, k, index);
		index.value = index.value + 1;
		if (index.value == k)
			return head;
		return node;
	}

	/**
	 * Approach 3 - Iterative approach. Using 2 pointers. O(n) time and O(1) space.
	 */
	public ListNode getKthToLastElement_using2Pointers(ListNode head, int k) {
		ListNode p1 = head;
		ListNode p2 = head;

		// move p1 k nodes into the list
		for (int i = 0; i < k; i++) {
			if (p1 == null)
				return null;
			p1 = p1.next;
		}
		// now move both pointers simultaneously. if p1 reaches the end , then p2 will be at the kth node
		// from end.
		while (p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}
}
