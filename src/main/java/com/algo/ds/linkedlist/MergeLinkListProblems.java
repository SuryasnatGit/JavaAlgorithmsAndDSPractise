package com.algo.ds.linkedlist;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.algo.common.ListNode;

/**
 * http://www.geeksforgeeks.org/merge-sort-for-linked-list/.
 * 
 * complexity - O(n log n) Test cases 0 ListNodes 1 ListNodes 2 ListNodes 3 ListNodes fully sorted reverse sorted
 */
public class MergeLinkListProblems {

	/**
	 * @param head:
	 *            The head of linked list.
	 * @return: You should return the head of the sorted linked list, using constant space complexity.
	 */
	public ListNode mergeSortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode mid = findMiddle(head);
		ListNode right = mergeSortList(mid.next);
		mid.next = null;
		ListNode left = mergeSortList(head);

		return merge2SortedLists(left, right);
	}

	private ListNode findMiddle(ListNode head) {
		ListNode slow = head;
		ListNode fast = head.next;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

	public ListNode merge2SortedLists(ListNode head1, ListNode head2) {
		ListNode dummy = new ListNode(0);
		ListNode pointer = dummy;

		while (head1 != null && head2 != null) {
			if (head1.data < head2.data) {
				pointer.next = head1;
				head1 = head1.next;
			} else {
				pointer.next = head2;
				head2 = head2.next;
			}
			pointer = pointer.next;
		}

		if (head1 != null) {
			pointer.next = head1;
		} else {
			pointer.next = head2;
		}

		return dummy.next;
	}

	/**
	 * Divide and Conquer approach.
	 * 
	 * T - O(n log k) S - O(1)
	 */
	public ListNode mergeKListsNaive(List<ListNode> lists) {
		if (lists.size() == 0) {
			return null;
		}
		return mergeHelper(lists, 0, lists.size() - 1);
	}

	private ListNode mergeHelper(List<ListNode> lists, int start, int end) {
		if (start == end) {
			return lists.get(start);
		}

		int mid = start + (end - start) / 2;
		ListNode left = mergeHelper(lists, start, mid);
		ListNode right = mergeHelper(lists, mid + 1, end);
		return merge2SortedLists(left, right);
	}

	/**
	 * Using priority queue.
	 * 
	 * T - O(n log k) S - O(1)
	 */
	public ListNode mergeKListsHeap(ArrayList<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}

		Comparator<ListNode> listNodeComparator = new Comparator<ListNode>() {
			public int compare(ListNode left, ListNode right) {
				if (left == null) {
					return 1;
				} else if (right == null) {
					return -1;
				}
				return left.data - right.data;
			}
		};

		Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), listNodeComparator);
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i) != null) {
				heap.add(lists.get(i));
			}
		}

		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;
		while (!heap.isEmpty()) {
			ListNode head = heap.poll();
			tail.next = head;
			tail = head;
			if (head.next != null) {
				heap.add(head.next);
			}
		}
		return dummy.next;
	}

	public static void main(String args[]) {
		MergeLinkListProblems msll = new MergeLinkListProblems();

		ListNode head1 = new ListNode(1);
		head1.next = new ListNode(5);
		head1.next.next = new ListNode(3);

		ListNode head2 = new ListNode(2);
		head2.next = new ListNode(4);
		head2.next.next = new ListNode(6);

		MiscellaneousLinkedListProblems mis = new MiscellaneousLinkedListProblems();

		ListNode head = msll.mergeSortList(head1);
		mis.displayList(head);

		ListNode mergedNode = msll.merge2SortedLists(head1, head2);
		mis.displayList(mergedNode);
	}
}
