package com.algo.ds.linkedlist;

import java.util.HashMap;
import java.util.Map;

import com.algo.ds.linkedlist.CopyLinkListWIthArbitPointer.RandomNode;

public class MiscellaneousLinkedListProblems {

	/**
	 * Given a Nodeed list, determine if it has a cycle in it.
	 * 
	 * If we have 2 pointers - fast and slow. It is guaranteed that the fast one will meet the slow one if there exists
	 * a circle.
	 * 
	 * @param head
	 * @return
	 */
	public boolean hasCycle(Node head) {
		Node slow = head;
		Node fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (fast == slow)
				return true;
		}
		return false;
	}

	/**
	 * Use 2 nested for loops. Outer loop will be for each node of the 1st list and inner loop will be for 2nd list. In
	 * the inner loop, check if any of nodes of 2nd list is same as the current node of first Nodeed list. Time
	 * complexity of this method will be O(mn) where m and n are the number of nodes in two lists.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int intersectionOfNodeedLists_1(Node a, Node b) {
		Node pa = a;
		while (pa != null) {
			Node pb = b;
			while (pb != null) {
				if (pa.data == pb.data)
					return pa.data;
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
	public int intersectionOfNodeedLists_2(Node a, Node b) {
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

	private int intersectingNode(int d, Node l1, Node l2) {
		Node curr1 = l1;
		Node curr2 = l2;
		for (int i = 0; i < d; i++) {
			curr1 = curr1.next;
		}
		// now both curr1 and curr2 are at the same position
		while (curr1 != null && curr2 != null) {
			if (curr1.data == curr2.data)
				return curr1.data;
			curr1 = curr1.next;
			curr2 = curr2.next;
		}
		return 0;
	}

	private int getCount(Node l) {
		int count = 0;
		Node curr = l;
		while (curr != null) {
			count++;
			curr = curr.next;
		}
		return count;
	}

	public Node unionListOfLists(Node a, Node b) {
		Node head = new Node(0);
		Node pa = a;
		Node pb = b;
		while (pa != null) {
			head = pushToList(pa.data, head);
			pa = pa.next;
		}
		while (pb != null) {
			if (!isPresent(pb.data, head))
				head = pushToList(pb.data, head);
			pb = pb.next;
		}
		return head;
	}

	private boolean isPresent(int key, Node head) {
		Node cur = head;
		while (cur != null) {
			if (cur.data == key)
				return true;
			cur = cur.next;
		}
		return false;
	}

	private Node pushToList(int key, Node head) {
		Node temp = new Node(key);
		temp.next = head;
		head = temp;
		return head;
	}

	public Node intersectionListOfLists(Node a, Node b) {
		Node result = new Node(0);
		Node pa = a;
		Node pb = b;
		while (pb != null) {
			if (isPresent(pb.data, pa))
				result = pushToList(pb.data, result);
			pb = pb.next;
		}
		return result;
	}

	/**
	 * Given a Nodeed list, remove the nth node from the end of list and return its head.
	 * 
	 * For example, given Nodeed list 1->2->3->4->5 and n = 2, the result is 1->2->3->5.
	 * 
	 * 2 pass solution - Calculate the length first, and then remove the nth from the beginning.
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public Node removeNthNodeFromEnd_1(Node head, int n) {
		if (head == null)
			return head;

		// calculate the length
		Node p = head;
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
	 * https://www.techiedelight.com/rearrange-Nodeed-list-alternating-high-low-values/
	 * 
	 * @return
	 */
	public Node rearrange(Node head) {
		// empty list
		if (head == null)
			return null;

		Node prev = head;
		Node curr = head.next;

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
	 * One pass solution - Use fast and slow pointers. The fast pointer is n steps ahead of the slow pointer. When the
	 * fast reaches the end, the slow pointer points at the previous element of the target element.
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public Node removeNthNodeFromEnd_2(Node head, int n) {
		if (head == null)
			return head;

		Node fast = head;
		Node slow = head;

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
	public void moveEvenNodesToEndOfListInReverseOrder(Node head) {
		if (head == null)
			return;

		Node odd = head;
		Node even = null, prev = null;
		while (odd != null && odd.next != null) {
			if (odd.next != null) {
				Node newnode = odd.next;
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
	 * You are given two Nodeed lists representing two non-negative numbers. <br/>
	 * The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and
	 * return it as a Nodeed list.
	 * 
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) <br/>
	 * Output: 7 -> 0 -> 8
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public Node add(Node l1, Node l2) {
		Node sum = new Node(0); // initialize sum to 0
		Node p1 = l1, p2 = l2, p3 = sum;
		int carry = 0;
		while (p1 != null || p2 != null) {
			if (p1 != null) {
				carry += p1.data;
				p1 = p1.next;
			}
			if (p2 != null) {
				carry += p2.data;
				p2 = p2.next;
			}
			p3.next = new Node(carry % 10);
			p3 = p3.next;
			carry /= 10;
		}
		if (carry == 1)
			p3.next = new Node(1);

		System.out.println(sum.toString());
		return sum.next;
	}

	/**
	 * Given a Nodeed list, reverse the nodes of a Nodeed list k at a time and return its modified list.
	 * 
	 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
	 * 
	 * You may not alter the values in the nodes, only nodes itself may be changed.
	 * 
	 * Only constant memory is allowed.
	 * 
	 * For example, Given this Nodeed list: 1->2->3->4->5
	 * 
	 * For k = 2, you should return: 2->1->4->3->5
	 * 
	 * For k = 3, you should return: 3->2->1->4->5
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public Node reverseNodesInKGroup(Node head, int k) {
		if (head == null || k == 1)
			return head;

		Node temp = new Node(0);
		temp.next = head;

		Node pre = temp;
		Node p = head;
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
	private Node reverse(Node pre, Node next) {
		Node last = pre.next;
		Node curr = last.next;
		while (curr != next) {
			last.next = curr.next;
			curr.next = pre.next;
			pre.next = curr;
			curr = last.next;
		}
		return last;
	}

	/**
	 * Reverse a Nodeed list from position m to n. Do it in-place and in one-pass.
	 * 
	 * For example: given 1->2->3->4->5->NULL, m = 2 and n = 4, return 1->4->3->2->5->NULL.
	 * 
	 * 
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	public Node reverseListBetween(Node head, int m, int n) {
		if (head == null || m == n)
			return head;

		Node prev = null; // track (m-1)th node
		Node first = new Node(0);// track mth mode
		Node second = new Node(0);// track (n+1)th node

		int i = 0;
		Node p = head;
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

		Node p1 = first.next;
		Node p2 = p1.next;
		p1.next = second.next;

		while (p1 != null && p2 != null) {
			Node temp = p2.next;
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
	 * A Nodeed list is given such that each node contains an additional random pointer which could point to any
	 * node in the list or null.
	 * 
	 * Return a deep copy of the list. Approach 1 - using extra space <br/>
	 * complexity - O(n) time and O(n) space
	 * 
	 * @param head
	 * @return
	 */
	public RandomNode copyListWithRandomPointer(RandomNode head) {
		if (head == null)
			return head;

		// this hash map is the extra space
		Map<RandomNode, RandomNode> mapping = new HashMap<>();

		RandomNode copy = new RandomNode(0);
		RandomNode current = head, copycurrent = copy;

		// loop through the list and add the mapping of original Node and copy Node
		while (current.next != null) {
			mapping.put(current, copycurrent);
			copycurrent.next = new RandomNode(0);
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

	public RandomNode copyListWithRandomPointer1(RandomNode head) {
		if (head == null)
			return head;

		// 1. copy next
		RandomNode current = head;
		while (current != null) {
			RandomNode temp = new RandomNode(0);
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
		RandomNode copy = head.next;
		// separate the lists
		while (current.next != null) {
			RandomNode temp = current.next;
			current.next = current.next.next;
			current = temp;
		}
		return copy;
	}

	/**
	 * Given a sorted Nodeed list, delete all duplicates such that each element appear only once.
	 * 
	 * For example,
	 * 
	 * Given 1->1->2, return 1->2. Given 1->1->2->3->3, return 1->2->3.
	 * 
	 * @param head
	 * @return
	 */
	public Node removeDuplicates(Node head) {
		if (head == null || head.next == null)
			return head;
		Node p = head;
		while (p != null && p.next != null) {
			if (p.data == p.next.data) {
				p.next = p.next.next;
			} else {
				p = p.next;
			}
		}
		return head;
	}

	/**
	 * Given a sorted Nodeed list, delete all nodes that have duplicate numbers, leaving only distinct numbers from
	 * the original list.
	 * 
	 * For example, given 1->1->1->2->3, return 2->3.
	 * 
	 * @param head
	 * @return
	 */
	public Node completelyRemoveDuplicates(Node head) {
		if (head == null || head.next == null)
			return head;
		// create a temp Node
		Node temp = new Node(0);
		temp.next = head;
		Node p = temp;
		while (p.next != null && p.next.next != null) {
			if (p.next.data == p.next.next.data) {
				int dup = p.next.data;
				while (p.next != null && p.next.data == dup) {
					p.next = p.next.next;
				}
			} else {
				p = p.next;
			}
		}
		return temp.next == null ? new Node(0) : temp.next;

	}

	/**
	 * Given a Nodeed list and a value x, partition it such that all nodes less than x come before nodes greater
	 * than or equal to x.
	 * 
	 * You should preserve the original relative order of the nodes in each of the two partitions.
	 * 
	 * For example, given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public Node partitionListByValue(Node head, int x) {
		if (head == null || head.next == null)
			return head;

		// make 2 temp heads
		Node fakeHead1 = new Node(0);
		Node fakeHead2 = new Node(0);

		fakeHead1.next = head;
		Node ptr = head;
		Node previous = fakeHead1;
		Node ptr2 = fakeHead2;

		while (ptr != null) {
			if (ptr.data < x) {
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

	public Node getIntersectionNode(Node NodeA, Node NodeB) {
		if (NodeA == null || NodeB == null)
			return null;
		while (NodeA != null && NodeB != null) {
			if (NodeA.data == NodeB.data)
				return NodeA;
			else {
				NodeA = NodeA.next;
				NodeB = NodeB.next;
			}
		}
		return null;
	}

	/**
	 * Remove all elements from a list of integers that have value val.
	 * 
	 * Example
	 * 
	 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6 Return: 1 --> 2 --> 3 --> 4 --> 5
	 * 
	 * @param head
	 * @param val
	 * @return
	 */
	public Node removeAllNodesWithValue(Node head, int val) {
		Node temp = new Node(0);
		temp.next = head;
		Node curr = temp; // new pointer points to temp
		while (curr.next != null) {
			if (curr.next.data == val) {
				Node tempNext = curr.next;
				curr.next = tempNext.next;
			} else {
				curr = curr.next;
			}
		}
		return temp.next;
	}

	/**
	 * Given a Nodeed list, swap every two adjacent nodes and return its head.
	 * 
	 * For example, given 1->2->3->4, you should return the list as 2->1->4->3.
	 * 
	 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can
	 * be changed.
	 * 
	 * @param head
	 * @return
	 */
	public Node swapNodesInPairs_1(Node head) {
		if (head == null || head.next == null)
			return head;
		Node temp = new Node(0);
		temp.next = head;
		Node p = temp;
		while (p.next != null && p.next.next != null) {
			Node temp1 = p; // pointer to track first node
			p = p.next;
			temp1.next = p.next;

			Node temp2 = p.next.next; // to track next node of the pair
			p.next.next = p;
			p.next = temp2;
		}
		return temp.next;
	}

	public Node swapNodesInPairs_2(Node head) {
		if (head == null || head.next == null)
			return head;
		Node temp = new Node(0);
		temp.next = head;

		Node p1 = head;
		Node p2 = head.next;
		Node pre = temp;
		while (p1 != null && p2 != null) {
			pre.next = p2;

			Node temp1 = p2.next;
			p2.next = p1;
			pre = p1;
			p1.next = temp1;

			p1 = p1.next;
			if (temp1 != null)
				p2 = temp1.next;
		}
		return temp.next;

	}

	// https://www.techiedelight.com/split-Nodeed-list-into-two-lists-list-containing-alternating-elements/
	public Node[] splitLLInto2ListsContainingAltElements(Node head) {
		Node first = head;
		Node second = head.next;
		alternateSplit(first, second);

		return new Node[] { first, second };
	}

	private void alternateSplit(Node first, Node second) {
		if (first == null || second == null)
			return;

		if (first.next != null)
			first.next = first.next.next;

		if (second.next != null)
			second.next = second.next.next;

		alternateSplit(first.next, second.next);
	}

	// https://www.techiedelight.com/intersection-two-given-sorted-Nodeed-lists/
	public Node sortedIntersectNode(Node a, Node b) {
		Node res = null;
		Node tail = res;

		while (a != null && b != null) {
			if (a.data == b.data) {
				if (res == null) {
					res = tail = new Node(a.data);
				} else {
					tail.next = new Node(a.data);
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

	public int compareLists(Node a, Node b) {
		while (a != null && b != null) {
			if (a.data != b.data)
				return 0;
			a = a.next;
			b = b.next;
		}
		return a == null && b == null ? 1 : 0;
	}

	private Node formSortedList(Node tempNode, Node head) {
		Node current = head;
		Node temp = new Node(tempNode.data);
		if (head == null) {
			head = temp;
		} else {
			current.next = temp;
			current = current.next;
		}
		return head;
	}

	public void displayList(Node head) {
		StringBuilder sb = new StringBuilder();
		while (head != null) {
			sb.append(head.data + " ");
			head = head.next;
		}
		System.out.println(sb.toString());
	}

	/**
	 *
	 */
	public Node deleteNodeInMiddle(Node head, int x) {
		if (head == null)
			return head;
		Node current = head;
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

		// unNode the deleted node from the list
		current.next = current.next.next;

		return head;
	}

	/**
	 * Delete Middle Node: Implement an algorithm to delete a node in the middle (Le., any node but the fi rst and last
	 * node, not necessarily the exact middle) of a singly Nodeed list, given only access to that node. EXAMPLE
	 * Input: the node c from the Nodeed list a - >b- >c - >d - >e- >f Result: nothing is returned, but the new
	 * Nodeed list looks like a->b->d->e->f
	 * 
	 * @param n
	 * @return
	 */
	public boolean deleteMiddleNode(Node n) {
		if (n == null || n.next == null)
			return false;
		Node next = n.next;
		n.data = next.data;
		n.next = next.next;
		return true;
	}

	public int getNthNodeFromEnd(Node head, int n) {
		Node temp = head;
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
				return temp.data;
			temp = temp.next;
		}
		return 0;
	}

	/**
	 * Given a Nodeed list, swap every two adjacent nodes and return its head. For example, Given 1->2->3->4, you
	 * should return the list as 2->1->4->3. Your algorithm should use only constant space. You may not modify the
	 * values in the list, only nodes itself can be changed.
	 * 
	 * @param head
	 * @return
	 */
	public Node swapPairs(Node head) {
		Node dummy = new Node(-1);

		dummy.next = head;
		Node p = dummy;
		while (p.next != null && p.next.next != null) {
			Node next = p.next;
			Node next2 = p.next.next;
			p.next = next2;
			next.next = next2.next;
			next2.next = next;

			p = next;
		}

		return dummy.next;
	}

	public Node cloneList(Node head) {
		if (head == null)
			return null;

		Node newHead = null;
		Node tail = null;
		Node current = head;

		while (current != null) {
			if (newHead == null) {
				newHead = new Node(current.data);
				tail = newHead;
			} else {
				Node temp = new Node(current.data);
				tail.next = temp;
				tail = tail.next;
				tail.next = null;
			}
			current = current.next;
		}
		return newHead;
	}

	public Node moveLastNodeToFrontOfLL(Node head) {
		if (head == null || head.next == null)
			return head;

		Node curr = head;

		// move curr to last but end node of ll
		while (curr.next.next != null)
			curr = curr.next;

		curr.next.next = head;

		head = curr.next;

		curr.next = null;

		return head;
	}

	public Node deleteOddNodes(Node head) {
		if (head == null || head.next == null)
			return head;

		Node dummy = new Node(-1);
		Node curr = dummy;

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
		 * Node l1 = new Node(2); Node l2 = new Node(4); Node l3 = new Node(3); l1.next = l2;
		 * l2.next = l3; System.out.println(l1.toString()); Node a1 = new Node(5); Node a2 = new
		 * Node(6); Node a3 = new Node(4); a1.next = a2; a2.next = a3; System.out.println(a1.toString());
		 * System.out.println(prob.add(l1, a1).toString());
		 */

		// Node l1 = new Node(1);
		// Node l2 = new Node(2);
		// Node l3 = new Node(3);
		// Node l4 = new Node(4);
		// l1.next = l2;
		// l2.next = l3;
		// l3.next = l4;
		// // prob.reorderList(l1);
		//
		// // for random pointer testing
		// RandomNode r1 = new RandomNode();
		// RandomNode r2 = new RandomNode();
		// RandomNode r3 = new RandomNode();
		// r1.next = r2;
		// r1.random = r3;
		// r2.next = r3;
		// r2.random = r1;
		// // r3.next = null;
		// r3.random = r1;
		// System.out.println(r1.toString());
		// prob.copyListWithRandomPointer(r1);

		// Node l = new Node(0);
		// Node temp = l;
		// for (int i = 1; i <= 5; i++) {
		// // Node n =
		// temp.next = new Node(i);
		// temp = temp.next;
		// }
		// System.out.println(l.toString());
		// System.out.println(prob.oddEvenList(l.next).toString());
		// prob.testRemoveDuplicates();
		// prob.testCompletelyRemoveDuplicates();
		// prob.testPartitionListByValue();
		prob.testRemoveAllNodesWithValue();
		// prob.testSwapNodesInPairs_1();
		// prob.testReverseNodesInKGroup();

		// Node a = new Node(1);
		// a.next = new Node(2);
		// a.next.next = new Node(3);
		// a.next.next.next = new Node(4);
		// a.next.next.next.next = new Node(6);
		//
		// System.out.println(a);
		// Node h = prob.rearrangeOddEvenListByPosition(a);
		// System.out.println(h);
		// Node v = prob.rearrangeOddEvenListByValue(a);
		// System.out.println(v);
		//
		// Node b = new Node(1);
		// b.next = new Node(3);
		// b.next.next = new Node(5);

		// prob.testDeleteOddNodes();
		// System.out.println(b);
		//
		// Node n = prob.sortedIntersectNode(a, b);
		// System.out.println(n);
	}

	private void testDeleteOddNodes() {
		Node a = new Node(1);
		a.next = new Node(2);
		a.next.next = new Node(3);
		a.next.next.next = new Node(4);
		a.next.next.next.next = new Node(6);

		System.out.println(a);
		deleteOddNodes(a);
		System.out.println(a);
	}

	private void testReverseNodesInKGroup() {
		Node l1 = new Node(1);
		Node l2 = new Node(2);
		Node l3 = new Node(3);
		Node l4 = new Node(4);
		Node l5 = new Node(5);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		System.out.println(l1.toString());
		System.out.println(reverseNodesInKGroup(l1, 3).toString());
	}

	private void testSwapNodesInPairs_1() {
		Node l1 = new Node(1);
		Node l2 = new Node(2);
		Node l3 = new Node(3);
		Node l4 = new Node(4);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		System.out.println(l1.toString());
		System.out.println(swapNodesInPairs_2(l1).toString());
	}

	private void testRemoveAllNodesWithValue() {
		Node l1 = new Node(1);
		Node l2 = new Node(3);
		Node l3 = new Node(3);
		Node l4 = new Node(4);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		new SingleLinkedList().displayList(l1);
		Node res = removeAllNodesWithValue(l1, 3);
		System.out.println();
		new SingleLinkedList().displayList(res);
	}

	private void testRemoveDuplicates() {
		Node l1 = new Node(1);
		Node l2 = new Node(1);
		Node l3 = new Node(2);
		Node l4 = new Node(2);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		System.out.println(l1.toString());
		System.out.println(removeDuplicates(l1).toString());
	}

	private void testCompletelyRemoveDuplicates() {
		Node l1 = new Node(1);
		Node l2 = new Node(1);
		Node l3 = new Node(2);
		Node l4 = new Node(2);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		System.out.println(l1.toString());
		System.out.println(completelyRemoveDuplicates(l1).toString());
	}

	public void testPartitionListByValue() {
		Node l1 = new Node(1);
		Node l2 = new Node(4);
		l1.next = l2;
		Node l3 = new Node(3);
		l2.next = l3;
		Node l4 = new Node(2);
		l3.next = l4;
		Node l5 = new Node(5);
		l4.next = l5;
		Node l6 = new Node(2);
		l5.next = l6;
		System.out.println(l1.toString());
		System.out.println(partitionListByValue(l1, 3).toString());
	}
}
