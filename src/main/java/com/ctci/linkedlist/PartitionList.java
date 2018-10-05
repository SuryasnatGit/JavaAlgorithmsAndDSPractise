package com.ctci.linkedlist;

import com.algo.ds.linkedlist.Link;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come first, then
 * all nodes with value equal to x and finally nodes with value greater than or equal to x. The
 * original relative order of the nodes in each of the three partitions should be preserved. The
 * partition must work in-place.
 * 
 * Examples:
 * 
 * Input : 1->4->3->2->5->2->3, x = 3 Output: 1->2->2->3->3->4->5
 * 
 * Input : 1->4->2->10 x = 3 Output: 1->2->4->10
 * 
 * Input : 10->4->20->10->3 x = 3 Output: 3->10->4->20->10
 * 
 * @author surya
 *
 */
public class PartitionList {

	public Link partitionList_maintainOrder(Link head, int x) {
		// initialize 3 lists
		Link headLess = null, tailLess = null;
		Link headEqual = null, tailEqual = null;
		Link headMore = null, tailMore = null;
		while (head != null) {
			// if less
			if (head.getKey() < x) {
				if (headLess == null) {
					headLess = tailLess = head;
				} else {
					tailLess.next = head;
					tailLess = tailLess.next;
				}
			}

			// if equals
			if (head.getKey() == x) {
				if (headEqual == null) {
					headEqual = tailEqual = null;
				} else {
					tailEqual.next = head;
					tailEqual = tailEqual.next;
				}
			}

			// if more
			if (head.getKey() > x) {
				if (headMore == null) {
					headMore = tailMore = null;
				} else {
					tailMore.next = head;
					tailMore = tailMore.next;
				}
			}

			head = head.next;
		}

		// end of more list to null
		if (tailMore != null)
			tailMore.next = null;

		// connect 3 lists
		// if smaller list of empty
		if (headLess == null) {
			if (headEqual == null) {
				return headMore;
			}
			tailEqual.next = headMore;
			return headEqual;
		}

		// if smaller list is not empty and equal is empty
		if (headEqual == null) {
			tailLess.next = headMore;
			return headLess;
		}

		// if both smaller and equal list are non-empty
		tailLess.next = headEqual;
		tailEqual.next = headMore;
		return headLess;
	}
}
