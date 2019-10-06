package com.algo.ds.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class MiscellaneousLinkedListProblems {

	/**
	 * You are given two linked lists representing two non-negative numbers. <br/>
	 * The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and
	 * return it as a linked list.
	 * 
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) <br/>
	 * Output: 7 -> 0 -> 8
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public Link add(Link l1, Link l2) {
		Link sum = new Link(0); // initialize sum to 0
		Link p1 = l1, p2 = l2, p3 = sum;
		int carry = 0;
		while (p1 != null || p2 != null) {
			if (p1 != null) {
				carry += p1.getKey();
				p1 = p1.next;
			}
			if (p2 != null) {
				carry += p2.getKey();
				p2 = p2.next;
			}
			p3.next = new Link(carry % 10);
			p3 = p3.next;
			carry /= 10;
		}
		if (carry == 1)
			p3.next = new Link(1);

		System.out.println(sum.toString());
		return sum.next;
	}

	/**
	 * Given a singly linked list L: L0→L1→ ... →Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→...
	 * 
	 * For example, given {1,2,3,4}, reorder it to {1,4,2,3}. You must do this in-place without altering the nodes'
	 * values.
	 * 
	 * This problem can be solved by doing the following:
	 * 
	 * Break list in the middle to two lists (use fast & slow pointers) <br/>
	 * Reverse the order of the second list <br/>
	 * Merge two list back together
	 */
	public void reorderList(Link head) {
		// break in middle to form 2 lists
		if (head != null && head.next != null) {
			// fast runs twice the speed as slow, so if fast reaches the end that means slow is in the middle of the
			// list
			Link slow = head;
			Link fast = head;
			while (fast != null && fast.next != null && fast.next.next != null) {
				slow = slow.next;
				fast = fast.next.next;
			}
			Link second = slow.next;
			slow.next = null; // break the chain to separate the second list

			// now we have 2 lists, with fast and head
			System.out.println(head.toString());
			// reverse the second list
			second = reverseList(second);
			System.out.println(second.toString());
			// now merge the 2 lists
			Link p1 = head;
			Link p2 = second;
			while (p2 != null) {
				Link temp1 = p1.next;
				Link temp2 = p2.next;

				p1.next = p2;
				p2.next = temp1;

				p1 = temp1;
				p2 = temp2;
			}
			System.out.println(head.toString());
		}
	}

	private Link reverseList(Link head) {
		if (head == null || head.next == null)
			return head;

		Link previous = head;
		Link current = head.next;

		while (current != null) {
			Link temp = current.next;
			current.next = previous;
			previous = current;
			current = temp;
		}
		head.next = null;
		return previous;
	}

	/**
	 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
	 * 
	 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
	 * 
	 * You may not alter the values in the nodes, only nodes itself may be changed.
	 * 
	 * Only constant memory is allowed.
	 * 
	 * For example, Given this linked list: 1->2->3->4->5
	 * 
	 * For k = 2, you should return: 2->1->4->3->5
	 * 
	 * For k = 3, you should return: 3->2->1->4->5
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public Link reverseNodesInKGroup(Link head, int k) {
		if (head == null || k == 1)
			return head;

		Link temp = new Link(0);
		temp.next = head;

		Link pre = temp;
		Link p = head;
		int i = 0;
		while (p != null) {
			i++;
			if (i % k == 0) {
				pre = reverse(pre, p.next);
				p = pre.next;
			} else {
				p = p.next;
			}
		}
		return temp.next;
	}

	/**
	 * 0->1->2->3->4->5->6 | | pre next
	 *
	 * after calling pre = reverse(pre, next)
	 * 
	 * 0->3->2->1->4->5->6 | | pre next
	 **/
	private Link reverse(Link pre, Link next) {
		Link last = pre.next;
		Link curr = last.next;
		while (curr != next) {
			last.next = curr.next;
			curr.next = pre.next;
			pre.next = curr;
			curr = last.next;
		}
		return last;
	}

	/**
	 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
	 * 
	 * For example: given 1->2->3->4->5->NULL, m = 2 and n = 4, return 1->4->3->2->5->NULL.
	 * 
	 * 
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	public Link reverseListBetween(Link head, int m, int n) {
		if (head == null || m == n)
			return head;

		Link prev = null; // track (m-1)th node
		Link first = new Link(0);// track mth mode
		Link second = new Link(0);// track (n+1)th node

		int i = 0;
		Link p = head;
		while (p != null) {
			i++;

			if (i == m - 1)
				prev = p;

			if (i == m)
				first.next = p;

			if (i == n) {
				second.next = p.next;
				p.next = null;
			}

			p = p.next;
		}

		if (first.next == null)
			return head;

		Link p1 = first.next;
		Link p2 = p1.next;
		p1.next = second.next;

		while (p1 != null && p2 != null) {
			Link temp = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = temp;
		}

		if (prev != null)
			prev.next = p1;
		else
			return p1;

		return head;
	}

	/**
	 * A linked list is given such that each node contains an additional random pointer which could point to any node in
	 * the list or null.
	 * 
	 * Return a deep copy of the list. Approach 1 - using extra space <br/>
	 * complexity - O(n) time and O(n) space
	 * 
	 * @param head
	 * @return
	 */
	public RandomLink copyListWithRandomPointer(RandomLink head) {
		if (head == null)
			return head;

		// this hash map is the extra space
		Map<RandomLink, RandomLink> mapping = new HashMap<>();

		RandomLink copy = new RandomLink();
		RandomLink current = head, copycurrent = copy;

		// loop through the list and add the mapping of original link and copy link
		while (current.next != null) {
			mapping.put(current, copycurrent);
			copycurrent.next = new RandomLink();
			current = current.next;
			copycurrent = copycurrent.next;
		}
		// reset pointers to head positions of original and copy
		current = head;
		copycurrent = copy;
		// System.out.println(current.toString());
		// loop through the list and assign the random pointers for copy
		while (current != null) {
			copycurrent.random = mapping.get(current.random);
			current = current.next;
			copycurrent = copycurrent.next;
		}
		return copy;
	}

	public RandomLink copyListWithRandomPointer1(RandomLink head) {
		if (head == null)
			return head;

		// 1. copy next
		RandomLink current = head;
		while (current != null) {
			RandomLink temp = new RandomLink();
			temp.next = current.next;
			current.next = temp;
			current = current.next.next;
		}

		// 2. reset current and copy random
		current = head;
		while (current != null) {
			current.next.random = current.random.next;
			current = current.next.next;
		}

		// 3. reset head and split
		current = head;
		RandomLink copy = head.next;
		// separate the lists
		while (current.next != null) {
			RandomLink temp = current.next;
			current.next = current.next.next;
			current = temp;
		}
		return copy;
	}

	/**
	 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are
	 * talking about the node number and not the value in the nodes.
	 * 
	 * The program should run in O(1) space complexity and O(n) time complexity. where n is number of nodes
	 * 
	 * @param head
	 * @return
	 */
	public Link oddEvenList(Link head) {
		if (head == null)
			return head;

		Link result = head;
		Link p1 = head;
		Link p2 = head.next;
		Link connectLink = head.next;

		while (p1 != null && p2 != null) {
			if (p2.next == null)
				break;

			p1.next = p2.next;
			p1 = p1.next; // combines odds

			p2.next = p1.next;
			p2 = p2.next; // combines even
		}
		System.out.println(p1.toString());
		// System.out.println(p2.toString());
		p1.next = connectLink; // combines eveens to odds
		return result;
	}

	/**
	 * Given a sorted linked list, delete all duplicates such that each element appear only once.
	 * 
	 * For example,
	 * 
	 * Given 1->1->2, return 1->2. Given 1->1->2->3->3, return 1->2->3.
	 * 
	 * @param head
	 * @return
	 */
	public Link removeDuplicates(Link head) {
		if (head == null || head.next == null)
			return head;
		Link p = head;
		while (p != null && p.next != null) {
			if (p.getKey() == p.next.getKey()) {
				p.next = p.next.next;
			} else {
				p = p.next;
			}
		}
		return head;
	}

	/**
	 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the
	 * original list.
	 * 
	 * For example, given 1->1->1->2->3, return 2->3.
	 * 
	 * @param head
	 * @return
	 */
	public Link completelyRemoveDuplicates(Link head) {
		if (head == null || head.next == null)
			return head;
		// create a temp Link
		Link temp = new Link(0);
		temp.next = head;
		Link p = temp;
		while (p.next != null && p.next.next != null) {
			if (p.next.getKey() == p.next.next.getKey()) {
				int dup = p.next.getKey();
				while (p.next != null && p.next.getKey() == dup) {
					p.next = p.next.next;
				}
			} else {
				p = p.next;
			}
		}
		return temp.next == null ? new Link(0) : temp.next;

	}

	/**
	 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or
	 * equal to x.
	 * 
	 * You should preserve the original relative order of the nodes in each of the two partitions.
	 * 
	 * For example, given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public Link partitionListByValue(Link head, int x) {
		if (head == null || head.next == null)
			return head;

		// make 2 temp heads
		Link fakeHead1 = new Link(0);
		Link fakeHead2 = new Link(0);

		fakeHead1.next = head;
		Link ptr = head;
		Link previous = fakeHead1;
		Link ptr2 = fakeHead2;

		while (ptr != null) {
			if (ptr.getKey() < x) {
				ptr = ptr.next;
				previous = previous.next;
			} else {
				ptr2.next = ptr;
				previous.next = ptr.next;

				ptr = previous.next;
				ptr2 = ptr2.next;
			}
		}

		ptr2.next = null;// close it off

		previous.next = fakeHead2.next;

		return fakeHead1.next;
	}

	public Link getIntersectionNode(Link linkA, Link linkB) {
		if (linkA == null || linkB == null)
			return null;
		while (linkA != null && linkB != null) {
			if (linkA.getKey() == linkB.getKey())
				return linkA;
			else {
				linkA = linkA.next;
				linkB = linkB.next;
			}
		}
		return null;
	}

	/**
	 * Remove all elements from a linked list of integers that have value val.
	 * 
	 * Example
	 * 
	 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6 Return: 1 --> 2 --> 3 --> 4 --> 5
	 * 
	 * @param head
	 * @param val
	 * @return
	 */
	public Link removeAllNodesWithValue(Link head, int val) {
		Link temp = new Link(0);
		temp.next = head;
		Link p = temp; // new pointer points to temp
		while (p.next != null) {
			if (p.next.getKey() == val) {
				Link tempNext = p.next;
				p.next = tempNext.next;
			} else {
				p = p.next;
			}
		}
		return temp.next;
	}

	/**
	 * Given a linked list, swap every two adjacent nodes and return its head.
	 * 
	 * For example, given 1->2->3->4, you should return the list as 2->1->4->3.
	 * 
	 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can
	 * be changed.
	 * 
	 * @param head
	 * @return
	 */
	public Link swapNodesInPairs_1(Link head) {
		if (head == null || head.next == null)
			return head;
		Link temp = new Link(0);
		temp.next = head;
		Link p = temp;
		while (p.next != null && p.next.next != null) {
			Link temp1 = p; // pointer to track first node
			p = p.next;
			temp1.next = p.next;

			Link temp2 = p.next.next; // to track next node of the pair
			p.next.next = p;
			p.next = temp2;
		}
		return temp.next;
	}

	public Link swapNodesInPairs_2(Link head) {
		if (head == null || head.next == null)
			return head;
		Link temp = new Link(0);
		temp.next = head;

		Link p1 = head;
		Link p2 = head.next;
		Link pre = temp;
		while (p1 != null && p2 != null) {
			pre.next = p2;

			Link temp1 = p2.next;
			p2.next = p1;
			pre = p1;
			p1.next = temp1;

			p1 = p1.next;
			if (temp1 != null)
				p2 = temp1.next;
		}
		return temp.next;

	}

	public static void main(String[] args) {
		MiscellaneousLinkedListProblems prob = new MiscellaneousLinkedListProblems();

		/*
		 * Link l1 = new Link(2); Link l2 = new Link(4); Link l3 = new Link(3); l1.next = l2; l2.next = l3;
		 * System.out.println(l1.toString()); Link a1 = new Link(5); Link a2 = new Link(6); Link a3 = new Link(4);
		 * a1.next = a2; a2.next = a3; System.out.println(a1.toString()); System.out.println(prob.add(l1,
		 * a1).toString());
		 */

		// Link l1 = new Link(1);
		// Link l2 = new Link(2);
		// Link l3 = new Link(3);
		// Link l4 = new Link(4);
		// l1.next = l2;
		// l2.next = l3;
		// l3.next = l4;
		// // prob.reorderList(l1);
		//
		// // for random pointer testing
		// RandomLink r1 = new RandomLink();
		// RandomLink r2 = new RandomLink();
		// RandomLink r3 = new RandomLink();
		// r1.next = r2;
		// r1.random = r3;
		// r2.next = r3;
		// r2.random = r1;
		// // r3.next = null;
		// r3.random = r1;
		// System.out.println(r1.toString());
		// prob.copyListWithRandomPointer(r1);

		// Link l = new Link(0);
		// Link temp = l;
		// for (int i = 1; i <= 5; i++) {
		// // Link n =
		// temp.next = new Link(i);
		// temp = temp.next;
		// }
		// System.out.println(l.toString());
		// System.out.println(prob.oddEvenList(l.next).toString());
		// prob.testRemoveDuplicates();
		// prob.testCompletelyRemoveDuplicates();
		// prob.testPartitionListByValue();
		// prob.testRemoveAllNodesWithValue();
		// prob.testSwapNodesInPairs_1();
		prob.testReverseNodesInKGroup();
	}

	private void testReverseNodesInKGroup() {
		Link l1 = new Link(1);
		Link l2 = new Link(2);
		Link l3 = new Link(3);
		Link l4 = new Link(4);
		Link l5 = new Link(5);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		System.out.println(l1.toString());
		System.out.println(reverseNodesInKGroup(l1, 3).toString());
	}

	private void testSwapNodesInPairs_1() {
		Link l1 = new Link(1);
		Link l2 = new Link(2);
		Link l3 = new Link(3);
		Link l4 = new Link(4);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		System.out.println(l1.toString());
		System.out.println(swapNodesInPairs_2(l1).toString());
	}

	private void testRemoveAllNodesWithValue() {
		Link l1 = new Link(1);
		Link l2 = new Link(3);
		Link l3 = new Link(3);
		Link l4 = new Link(4);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		System.out.println(l1.toString());
		System.out.println(removeAllNodesWithValue(l1, 3).toString());
	}

	private void testRemoveDuplicates() {
		Link l1 = new Link(1);
		Link l2 = new Link(1);
		Link l3 = new Link(2);
		Link l4 = new Link(2);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		System.out.println(l1.toString());
		System.out.println(removeDuplicates(l1).toString());
	}

	private void testCompletelyRemoveDuplicates() {
		Link l1 = new Link(1);
		Link l2 = new Link(1);
		Link l3 = new Link(2);
		Link l4 = new Link(2);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		System.out.println(l1.toString());
		System.out.println(completelyRemoveDuplicates(l1).toString());
	}

	public void testPartitionListByValue() {
		Link l1 = new Link(1);
		Link l2 = new Link(4);
		l1.next = l2;
		Link l3 = new Link(3);
		l2.next = l3;
		Link l4 = new Link(2);
		l3.next = l4;
		Link l5 = new Link(5);
		l4.next = l5;
		Link l6 = new Link(2);
		l5.next = l6;
		System.out.println(l1.toString());
		System.out.println(partitionListByValue(l1, 3).toString());
	}
}
