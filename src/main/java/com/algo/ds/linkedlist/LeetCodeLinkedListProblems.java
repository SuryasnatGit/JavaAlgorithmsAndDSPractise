package com.algo.ds.linkedlist;

import java.util.HashMap;
import java.util.Map;

import com.algo.common.ListNode;

public class LeetCodeLinkedListProblems {

	/**
	 * Given a linked list, determine if it has a cycle in it.
	 * 
	 * If we have 2 pointers - fast and slow. It is guaranteed that the fast one will meet the slow one if there exists
	 * a circle.
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
	 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the
	 * nodes of the first two lists.
	 * 
	 * The key to solve the problem is defining a fake head. Then compare the first elements from each list. Add the
	 * smaller one to the merged list. Finally, when one of them is empty, simply append it to the merged list, since it
	 * is already sorted.
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

	/**
	 * Use 2 nested for loops. Outer loop will be for each node of the 1st list and inner loop will be for 2nd list. In
	 * the inner loop, check if any of nodes of 2nd list is same as the current node of first linked list. Time
	 * complexity of this method will be O(mn) where m and n are the number of nodes in two lists.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int intersectionOfLinkedLists_1(Link a, Link b) {
		Link pa = a;
		while (pa != null) {
			Link pb = b;
			while (pb != null) {
				if (pa.getKey() == pb.getKey())
					return pa.getKey();
				pb = pb.next;
			}
			pa = pa.next;
		}
		return 0;
	}

	/**
	 * Time Complexity: O(m+n) Auxiliary Space: O(1)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int intersectionOfLinkedLists_2(Link a, Link b) {
		int ca = getCount(a);
		int cb = getCount(b);
		if (ca > cb) {
			int d = ca - cb;
			return intersectingNode(d, a, b);
		} else {
			int d = cb - ca;
			return intersectingNode(d, b, a);
		}
	}

	private int intersectingNode(int d, Link l1, Link l2) {
		Link curr1 = l1;
		Link curr2 = l2;
		for (int i = 0; i < d; i++) {
			curr1 = curr1.next;
		}
		// now both curr1 and curr2 are at the same position
		while (curr1 != null && curr2 != null) {
			if (curr1.getKey() == curr2.getKey())
				return curr1.getKey();
			curr1 = curr1.next;
			curr2 = curr2.next;
		}
		return 0;
	}

	private int getCount(Link l) {
		int count = 0;
		Link curr = l;
		while (curr != null) {
			count++;
			curr = curr.next;
		}
		return count;
	}

	public Link unionListOfLists(Link a, Link b) {
		Link head = new Link();
		Link pa = a;
		Link pb = b;
		while (pa != null) {
			head = pushToList(pa.getKey(), head);
			pa = pa.next;
		}
		while (pb != null) {
			if (!isPresent(pb.getKey(), head))
				head = pushToList(pb.getKey(), head);
			pb = pb.next;
		}
		return head;
	}

	private boolean isPresent(int key, Link head) {
		Link cur = head;
		while (cur != null) {
			if (cur.getKey() == key)
				return true;
			cur = cur.next;
		}
		return false;
	}

	private Link pushToList(int key, Link head) {
		Link temp = new Link(key);
		temp.next = head;
		head = temp;
		return head;
	}

	public Link intersectionListOfLists(Link a, Link b) {
		Link result = new Link();
		Link pa = a;
		Link pb = b;
		while (pb != null) {
			if (isPresent(pb.getKey(), pa))
				result = pushToList(pb.getKey(), result);
			pb = pb.next;
		}
		return result;
	}

	/**
	 * Given a linked list, remove the nth node from the end of list and return its head.
	 * 
	 * For example, given linked list 1->2->3->4->5 and n = 2, the result is 1->2->3->5.
	 * 
	 * 2 pass solution - Calculate the length first, and then remove the nth from the beginning.
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public Link removeNthNodeFromEnd_1(Link head, int n) {
		if (head == null)
			return head;

		// calculate the length
		Link p = head;
		int i = 0;
		while (p != null) {
			i++;
			p = p.next;
		}

		// if 1st node is being removed.
		int fromStart = i - n + 1;
		if (fromStart == 1)
			return head.next;

		// if node to be removed is not first one
		p = head;
		i = 0;
		while (p != null) {
			i++;
			if (i == fromStart - 1) {
				p.next = p.next.next;
			}
			p = p.next;
		}
		return head;
	}

	/**
	 * One pass solution - Use fast and slow pointers. The fast pointer is n steps ahead of the slow pointer. When the
	 * fast reaches the end, the slow pointer points at the previous element of the target element.
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public Link removeNthNodeFromEnd_2(Link head, int n) {
		if (head == null)
			return head;

		Link fast = head;
		Link slow = head;

		// advance fast pointer by n steps
		for (int i = 0; i < n; i++) {
			fast = fast.next;
		}

		// check if 1st node is being removed
		if (fast == null)
			return head.next;

		while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}

		slow.next = slow.next.next;

		return head;
	}

	/**
	 * https://www.techiedelight.com/move-even-nodes-to-end-of-list-in-reverse-order/
	 * 
	 * @param head
	 */
	public void moveEvenNodesToEndOfListInReverseOrder(ListNode head) {
		if (head == null)
			return;

		ListNode odd = head;
		ListNode even = null, prev = null;
		while (odd != null && odd.next != null) {
			if (odd.next != null) {
				ListNode newnode = odd.next;
				odd.next = odd.next.next;

				newnode.next = even;
				even = newnode;
			}

			prev = odd;
			odd = odd.next;
		}

		if (odd != null) {
			odd.next = even;
		} else {
			prev.next = even;
		}
	}

	public static void main(String[] args) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(10, 10);
		map.put(20, 20);
		// System.out.println(map.get(5));
		LeetCodeLinkedListProblems prob = new LeetCodeLinkedListProblems();
		// Link a1 = new Link(10);
		// // Link a2 = new Link(20);
		// // a1.next = a2;
		// System.out.println(a1.toString());
		// // Link a3 = new Link(30);
		// // a2.next = a3;
		// // System.out.println(a1.toString());
		// // System.out.println(prob.hasCycle(a1));
		// // a3.next = a1;
		// // // System.out.println(a1.toString());
		// // System.out.println(prob.hasCycle(a1));
		//
		// Link b1 = new Link(5);
		// Link b2 = new Link(15);
		// b1.next = b2;
		// System.out.println(b1.toString());
		// System.out.println(prob.merge2SortedLists(a1, b1).toString());

		Link a1 = new Link(15);
		Link a2 = new Link(20);
		a1.next = a2;
		Link a3 = new Link(30);
		a2.next = a3;

		Link b1 = new Link(5);
		Link b2 = new Link(20);
		b1.next = b2;
		Link b3 = new Link(30);
		b2.next = b3;
		System.out.println(prob.intersectionOfLinkedLists_2(a1, b1));
		System.out.println(prob.unionListOfLists(a1, b1));
		System.out.println(prob.intersectionListOfLists(a1, b1));
	}

}
