package com.algo.ds.linkedlist;

/**
 * checks if a linked list is a palindrome
 * 
 * @author Suryasnat
 *
 */
public class PalindromeLinkedList {

	/**
	 * Create a new reversed list and compare each node
	 * 
	 * time and space complexity - O(N)
	 * 
	 * @param head
	 * @return
	 */
	public boolean isPalindrome_1(Link head) {
		if (head == null)
			return false;

		Link p = head;
		Link prev = new Link(head.getKey());
		while (p.next != null) {
			Link temp = new Link(p.next.getKey());
			temp.next = prev;
			prev = temp;
			p = p.next;
		}

		Link p1 = head;
		Link p2 = prev;
		while (p1 != null) {
			if (p1.getKey() != p2.getKey())
				return false;
			p1 = p1.next;
			p2 = p2.next;
		}
		return true;
	}

	/**
	 * break and reverse second half. using fast and slow pointers.<br/>
	 * time complexity - O(N) <br/>
	 * space complexity - O(1)
	 * 
	 * @param head
	 * @return
	 */
	public boolean isPalindrome_2(Link head) {
		if (head == null || head.next == null)
			return false;

		Link fast = head;
		Link slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		Link secondHead = slow.next;// head pointer to second list
		slow.next = null; // split the list here

		// reverse the second list
		Link p1 = secondHead;
		Link p2 = p1.next;
		while (p1 != null && p2 != null) {
			Link temp = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = temp;
		}

		secondHead.next = null;

		// compare both the lists
		Link p = p1;
		Link q = head;
		while (p != null) {
			if (p.getKey() != q.getKey())
				return false;

			p = p.next;
			q = q.next;
		}
		return true;
	}

	Link left;

	/**
	 * recursive approach.<br/>
	 * Time is O(n) and space is O(1).
	 * 
	 * @param head
	 * @return
	 */
	public boolean isPalindrome_3(Link head) {
		left = head;
		return check(head);
	}

	private boolean check(Link right) {
		// stop recursion
		if (right == null)
			return true;

		// if sub list is not palindrome return false
		boolean x = check(right.next);
		if (!x)
			return false;

		// current left and right
		boolean y = left.getKey() == right.getKey();

		// move left to next;
		left = left.next;

		return y;
	}

	public static void main(String[] args) {
		Link l1 = new Link(1);
		Link l2 = new Link(2);
		Link l3 = new Link(3);
		Link l4 = new Link(2);
		// Link l5 = new Link(1);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		// l4.next = l5;
		PalindromeLinkedList list = new PalindromeLinkedList();
		// System.out.println(list.isPalindrome_1(l1));
		// System.out.println(list.isPalindrome_2(l1));
		System.out.println(list.isPalindrome_3(l1));
	}
}
