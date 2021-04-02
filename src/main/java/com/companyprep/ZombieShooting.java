package com.companyprep;

import com.algo.common.ListNode;

/**
 * 
 * It is a circle of zombies, giving you a starting point, and then you shoot a zombie every k steps, and then go down
 * until you have a zombie, Let you output the last remaining zombie. I do it with linkedlist, and then while loop
 * shoots a zombie every time, num-1 deletes that listnode, and finally num becomes 1 to jump out of the loop.
 *
 */
public class ZombieShooting {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node1;

		ZombieShooting rd = new ZombieShooting();
		ListNode res = rd.shotZombies(node1, 2);
		System.out.println(res.data);
	}

	ListNode shotZombies(ListNode node, int k) {
		while (node.next != node) { // 1 node left
			ListNode preK = find(node, k);
			preK.next = preK.next.next;
			node = preK.next;
		}
		return node;
	}

	ListNode find(ListNode node, int k) {
		while (k > 1) {
			node = node.next;
			k--;
		}
		return node;
	}
}
