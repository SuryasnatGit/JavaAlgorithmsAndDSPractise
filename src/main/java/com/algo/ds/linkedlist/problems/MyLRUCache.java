package com.algo.ds.linkedlist.problems;

import java.util.HashMap;
import java.util.Map;

public class MyLRUCache {

	private class Node {
		private int key;
		private int value;
		private Node previous;
		private Node next;
	}

	private int capacity;
	private Node head;
	private Node tail;
	private Map<Integer, Node> data;

	public MyLRUCache(int capacity) {
		this.capacity = capacity;
		data = new HashMap<>();
	}

	public void add(Node n) {
		// reset position
		head = null;
		tail = null;

		// first element
		if (head == null) {
			head.next = n;
			head = head.next;
		}

		// existing element
		head.previous = n;
		n.next = head;
		head = n;
	}

	public void remove(Node n) {
		// if no node exists
		if (head == null || n == null)
			return;

		// if only 1 element present
		if (head == tail && head == n) {
			head = null;
			tail = null;
		}

		// if head matches the element to be removed
		if (head == n) {
			head.next.previous = null;
			head = head.next;
		}

		// if tail matches the element to be removed
		if (tail == n) {
			tail.previous.next = null;
			tail = tail.previous;
		}

		// if element is in the middle
		n.previous.next = n.next;
		n.next.previous = n.previous;
	}

	/**
	 * remove from DLL and add it in beginning
	 * 
	 * @param n
	 */
	public void moveFirst(Node n) {
		remove(n);
		add(n);
	}

	public void removeLast() {
		remove(tail);
	}

	/**
	 * With every access the accessed node needs to move to the front of the queue/DLL
	 * 
	 * @param key
	 * @return
	 */
	public int get(int key) {
		if (data.containsKey(key)) {
			Node node = data.get(key);
			moveFirst(node);
			return node.value;
		} else
			return -1;
	}

	public void set(int key, int value) {
		// existing key
		if (data.containsKey(key)) {
			Node node = data.get(key);
			moveFirst(node);
			node.value = value;
		}

		// check capacity. if exceeds remove the oldest slot
		if (data.size() >= capacity) {
			int key2 = tail.key;
			removeLast();
			data.remove(key2);
		}

		// new slot
		Node n = new Node();
		n.key = key;
		n.value = value;
		add(n);
		data.put(key, n);
	}
}
