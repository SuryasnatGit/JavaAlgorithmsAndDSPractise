package com.algo.ds.linkedlist;

import java.util.HashMap;
import java.util.Map;

import com.algo.common.ListNode;

import sun.awt.image.ImageWatched.Link;

public class MiscellaneousLinkedListProblems {

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
	 * https://www.techiedelight.com/rearrange-linked-list-alternating-high-low-values/
	 * 
	 * @return
	 */
	public ListNode rearrange(ListNode head) {
		// empty list
		if (head == null)
			return null;

		ListNode prev = head;
		ListNode curr = head.next;

		// start from second node
		while (curr != null) {
			// If the prev node is greater than current node,
			// swap their values
			if (prev.data > curr.data) {
				int temp = prev.data;
				prev.data = curr.data;
				curr.data = temp;
			}

			// if next node is greater than current node,
			// swap their values
			if (curr.next != null && curr.next.data > curr.data) {
				int temp = curr.next.data;
				curr.next.data = curr.data;
				curr.data = temp;
			}

			// update prev and curr node
			prev = curr.next;

			if (curr.next == null) {
				break;
			}

			curr = curr.next.next;
		}

		return head;
	}

	/**
	 * https://www.techiedelight.com/sort-linked-list-containing-0s-1s-2s/
	 * 
	 * @return
	 */
	public ListNode sortList(ListNode head) {
		// base case
		if (head == null || head.next == null) {
			return head;
		}

		// maintain three dummy_ nodes
		ListNode dummy_0 = new ListNode(-1), dummy_1 = new ListNode(-1), dummy_2 = new ListNode(-1);

		// maintain three references
		ListNode zero = dummy_0, one = dummy_1, two = dummy_2;
		ListNode curr = head;

		// traverse the list
		while (curr != null) {
			if (curr.data == 0) {
				zero.next = curr;
				zero = zero.next;
			} else if (curr.data == 1) {
				one.next = curr;
				one = one.next;
			} else {
				two.next = curr;
				two = two.next;
			}
			curr = curr.next;
		}

		// combine lists containing 0's, 1's and 2's
		zero.next = (dummy_1.next != null) ? (dummy_1.next) : (dummy_2.next);
		one.next = dummy_2.next;
		two.next = null;

		// change head
		return dummy_0.next;
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

	// https://www.techiedelight.com/split-linked-list-into-two-lists-list-containing-alternating-elements/
	public ListNode[] splitLLInto2ListsContainingAltElements(ListNode head) {
		ListNode first = head;
		ListNode second = head.next;
		alternateSplit(first, second);

		return new ListNode[] { first, second };
	}

	private void alternateSplit(ListNode first, ListNode second) {
		if (first == null || second == null)
			return;

		if (first.next != null)
			first.next = first.next.next;

		if (second.next != null)
			second.next = second.next.next;

		alternateSplit(first.next, second.next);
	}

	// https://www.techiedelight.com/intersection-two-given-sorted-linked-lists/
	public ListNode sortedIntersectNode(ListNode a, ListNode b) {
		ListNode res = null;
		ListNode tail = res;

		while (a != null && b != null) {
			if (a.data == b.data) {
				if (res == null) {
					res = tail = new ListNode(a.data);
				} else {
					tail.next = new ListNode(a.data);
					tail = tail.next;
				}

				a = a.next;
				b = b.next;
			} else if (a.data < b.data) {
				a = a.next;
			} else {
				b = b.next;
			}
		}

		return res;
	}

	public int compareLists(Link a, Link b) {
		while (a != null && b != null) {
			if (a.getKey() != b.getKey())
				return 0;
			a = a.next;
			b = b.next;
		}
		return a == null && b == null ? 1 : 0;
	}

	public Link mergeSortedListsRecursive(Link a, Link b) {
		if (a == null)
			return b;
		if (b == null)
			return a;
		if (a.getKey() < b.getKey()) {
			a.next = mergeSortedListsRecursive(a.next, b);
			return a;
		} else {
			b.next = mergeSortedListsRecursive(a, b.next);
			return b;
		}
	}

	public Link mergeSortedListsNonRecursive(Link a, Link b) {
		Link temp = null;
		while (a != null && b != null) {
			if (a.getKey() < b.getKey()) {
				temp = formSortedList(a, temp);
				a = a.next;
			} else {
				temp = formSortedList(b, temp);
				b = b.next;
			}
		}
		while (a != null) {
			temp = formSortedList(a, temp);
			a = a.next;
		}
		while (b != null) {
			temp = formSortedList(b, temp);
			b = b.next;
		}
		return temp;
	}

	private Link formSortedList(Link tempLink, Link head) {
		Link current = head;
		Link temp = new Link(tempLink.getKey());
		if (head == null) {
			head = temp;
		} else {
			current.next = temp;
			current = current.next;
		}
		return head;
	}

	private void displayList(Link head) {
		while (head != null) {
			System.out.print(head.getKey() + "->");
			head = head.next;
		}
		System.out.println();
	}

	/**
	 *
	 */
	public Link deleteNodeInMiddle(Link head, int x) {
		if (head == null)
			return head;
		Link current = head;
		if (x == 0) {
			head = current.next;
			return head;
		}

		// find previous node of the node to be deleted
		for (int i = 0; current != null && i < x - 1; i++) {
			current = current.next;
		}

		// if x is more than number of nodes
		if (current == null || current.next == null)
			return head;

		// unlink the deleted node from the list
		current.next = current.next.next;

		return head;
	}

	/**
	 * Delete Middle Node: Implement an algorithm to delete a node in the middle (Le., any node but the fi rst and last
	 * node, not necessarily the exact middle) of a singly linked list, given only access to that node. EXAMPLE Input:
	 * the node c from the linked list a - >b- >c - >d - >e- >f Result: nothing is returned, but the new linked list
	 * looks like a->b->d->e->f
	 * 
	 * @param n
	 * @return
	 */
	public boolean deleteMiddleNode(Link n) {
		if (n == null || n.next == null)
			return false;
		Link next = n.next;
		n.data = next.data;
		n.next = next.next;
		return true;
	}

	public int getNthNodeFromEnd(Link head, int n) {
		Link temp = head;
		int c = 0;
		while (temp != null) {
			c++;
			temp = temp.next;
		}
		temp = head;
		int c1 = 0;
		while (temp != null) {
			c1++;
			if (c1 == c - n)
				return temp.getKey();
			temp = temp.next;
		}
		return 0;
	}

	/**
	 * Given a linked list, swap every two adjacent nodes and return its head. For example, Given 1->2->3->4, you should
	 * return the list as 2->1->4->3. Your algorithm should use only constant space. You may not modify the values in
	 * the list, only nodes itself can be changed.
	 * 
	 * @param head
	 * @return
	 */
	public Link swapPairs(Link head) {
		Link dummy = new Link(-1);

		dummy.next = head;
		Link p = dummy;
		while (p.next != null && p.next.next != null) {
			Link next = p.next;
			Link next2 = p.next.next;
			p.next = next2;
			next.next = next2.next;
			next2.next = next;

			p = next;
		}

		return dummy.next;
	}

	public Link cloneList(Link head) {
		if (head == null)
			return null;

		Link newHead = null;
		Link tail = null;
		Link current = head;

		while (current != null) {
			if (newHead == null) {
				newHead = new Link(current.data);
				tail = newHead;
			} else {
				Link temp = new Link(current.data);
				tail.next = temp;
				tail = tail.next;
				tail.next = null;
			}
			current = current.next;
		}
		return newHead;
	}

	public ListNode moveLastNodeToFrontOfLL(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode curr = head;

		// move curr to last but end node of ll
		while (curr.next.next != null)
			curr = curr.next;

		curr.next.next = head;

		head = curr.next;

		curr.next = null;

		return head;
	}

	public ListNode deleteOddNodes(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode dummy = new ListNode(-1);
		ListNode curr = dummy;

		int i = 0;
		while (head != null) {
			if (i % 2 == 0) {
				curr.next = head;
				curr = head;
			}
			head = head.next;
			i++;
		}

		return dummy.next;
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
		// prob.testReverseNodesInKGroup();

		// ListNode a = new ListNode(1);
		// a.next = new ListNode(2);
		// a.next.next = new ListNode(3);
		// a.next.next.next = new ListNode(4);
		// a.next.next.next.next = new ListNode(6);
		//
		// System.out.println(a);
		// ListNode h = prob.rearrangeOddEvenListByPosition(a);
		// System.out.println(h);
		// ListNode v = prob.rearrangeOddEvenListByValue(a);
		// System.out.println(v);
		//
		// ListNode b = new ListNode(1);
		// b.next = new ListNode(3);
		// b.next.next = new ListNode(5);

		prob.testDeleteOddNodes();
		// System.out.println(b);
		//
		// ListNode n = prob.sortedIntersectNode(a, b);
		// System.out.println(n);
	}

	private void testDeleteOddNodes() {
		ListNode a = new ListNode(1);
		a.next = new ListNode(2);
		a.next.next = new ListNode(3);
		a.next.next.next = new ListNode(4);
		a.next.next.next.next = new ListNode(6);

		System.out.println(a);
		deleteOddNodes(a);
		System.out.println(a);
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
