package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * design a data structure which support
 * 
 * insert(key, value).
 * 
 * delete(key)
 * 
 * update(key, value)
 * 
 * max() // Take out the largest element in this data structure...
 * 
 * All operations are in O(1).
 * 
 * TODO : to analyse and complete.
 *
 */
public class AllO1TimeDataStructure {

	public static void main(String[] args) {

	}

	// Doesn't work
	Map<Integer, Node> map1 = new HashMap<Integer, Node>();
	Map<Integer, Node> map2 = new HashMap<Integer, Node>();
	Node dummy1 = new Node(-1, Integer.MIN_VALUE);
	Node top1 = dummy1;
	Node dummy2 = new Node(-1, Integer.MIN_VALUE);
	Node top2 = dummy2;

	void insert(int key, int value) {
		if (map1.containsKey(key)) {
			return; // No reInsert
		}

		Node newNode1 = new Node(key, value);
		top1.next = newNode1;
		newNode1.prev = top1;
		top1 = top1.next;
		map1.put(key, newNode1);

		Node newNode2 = top2.value > newNode1.value ? new Node(top2.value) : new Node(newNode1.value);
		top2.next = newNode2;
		newNode2.prev = top2;
		top2 = top2.next;
		map2.put(key, newNode2);
	}

	void delete(int key) {
		if (!map1.containsKey(key)) {
			return;
		}

		Node node1 = map1.get(key);
		Node node2 = map2.get(key);

		map1.remove(key); // No, you can’t just delete himself, the maximum value of other nodes may depend on him
		map2.remove(key);
	}

	class Node {
		int key;
		int value;
		Node next;
		Node prev;

		Node(int key, int value) {
			this.key = key;
			this.value = value;
		}

		Node(int value) {
			this.value = value;
		}
	}

	/*
	 * Design a hashHeap. Forget the specific scenario, it seems to be a bunch of tasks, each task has a priority, when
	 * you need to update the priority of a task, use hashHeap To access a certain element, and then re-siftUp or
	 * siftDown, to maintain the characteristics of the entire heap.
	 */
}
