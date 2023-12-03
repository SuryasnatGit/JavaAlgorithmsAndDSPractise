package com.algo.ds.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.algo.common.ListNode;

public class MergeKSortedLinkedLists {

	/**
	 * Using the dichotomous thinking in merge sort, divide the list containing k linked lists into two parts one after
	 * another, and then merge the two linked lists one after another, so that there are log(k) merge processes, each
	 * time using merge two sorted lists algorithm.
	 * 
	 * The time complexity is O(n log(k)) and no additional space is required, so the space complexity is O(1).
	 * 
	 * TODO: to check for accuracy
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKSortedLists(List<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}

		if (lists.size() == 1) {
			return lists.get(0);
		}

		int size = lists.size();

		ListNode base = lists.get(0);

		for (int i = 1; i < size; i++) {
			base = merge2SortedLists(base, lists.get(i));
		}
		return base;
	}

	private ListNode merge2SortedLists(ListNode left, ListNode right) {
		ListNode dummy = new ListNode(0);
		ListNode pointer = dummy;

		while (left != null && right != null) {
			if (left.data <= right.data) {
				pointer.next = left;
				left = left.next;
			} else {
				pointer.next = right;
				right = right.next;
			}
			pointer = pointer.next;
		}

		if (left != null) {
			pointer.next = left;
		} else {
			pointer.next = right;
		}

		return dummy.next;
	}

	/**
	 * Use Priority Queues. In this way, the nodes taken out each time are kept to be the current smallest, and new
	 * linked lists are added in order to obtain the merged result.
	 * 
	 * The time complexity is O(n log k), and the space complexity is O(k). It is the space of PriorityQueue.
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKSortedListsUsingPQ(List<ListNode> lists) {
		if (lists == null || lists.size() == 0) {
			return null;
		}

		Queue<Integer> queue = new PriorityQueue<>((a, b) -> a - b);

		for (ListNode listNode : lists) {
			ListNode head = listNode;
			while (head != null) {
				queue.offer(head.data);
				head = head.next;
			}
		}

		ListNode dummy = new ListNode(-1);
		ListNode head = dummy;
		while (!queue.isEmpty()) {
			head.next = new ListNode(queue.poll());
			head = head.next;
		}
		head.next = null;

		return dummy.next;
	}

	public static void main(String[] args) {
		MergeKSortedLinkedLists k = new MergeKSortedLinkedLists();
		List<ListNode> lists = new ArrayList<>();

		ListNode a = new ListNode(5);
		a.next = new ListNode(3);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(9);
		c.next = new ListNode(1);
		lists.add(a);
		lists.add(b);
		lists.add(c);
		LinkedListUtils.printList(k.mergeKSortedLists(lists));
		// LinkedListUtils.printList(k.mergeKSortedListsUsingPQ(lists));
	}

}
