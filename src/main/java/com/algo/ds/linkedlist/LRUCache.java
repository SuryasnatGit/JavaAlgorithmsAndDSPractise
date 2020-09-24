package com.algo.ds.linkedlist;

import java.util.HashMap;
import java.util.Map;

import com.algo.common.ListNode;

/**
 * http://www.geeksforgeeks.org/implement-lru-cache/
 * 
 * LRU cache stand for Least Recently Used Cache. which evict least recently used entry. As Cache purpose is to provide
 * fast and efficient way of retrieving data. it need to meet certain requirement.
 * 
 * Some of the Requirement are
 * 
 * 1. Fixed Size: Cache needs to have some bounds to limit memory usages.
 * 
 * 2. Fast Access: Cache Insert and lookup operation should be fast , preferably O(1) time.
 * 
 * 3. Replacement of Entry in case , Memory Limit is reached: A cache should have efficient algorithm to evict the entry
 * when memory is full.
 * 
 * In case of LRU cache we evict least recently used entry so we have to keep track of recently used entries, entries
 * which have not been used from long time and which have been used recently. plus lookup and insertion operation should
 * be fast enough.
 * 
 * When we think about O(1) lookup , obvious data structure comes in our mind is HashMap. HashMap provide O(1) insertion
 * and lookup. but HashMap does not has mechanism of tracking which entry has been queried recently and which not.
 * 
 * To track this we require another data-structure which provide fast insertion ,deletion and updation , in case of LRU
 * we use Doubly Linkedlist . Reason for choosing doubly LinkList is O(1) deletion , updation and insertion if we have
 * the address of ListNode on which this operation has to perform. So our Implementation of LRU cache will have HashMap
 * and Doubly LinkedList. In Which HashMap will hold the keys and address of the Nodes of Doubly LinkedList . And Doubly
 * LinkedList will hold the values of keys.
 * 
 * Test cases: MAX_SIZE greater than 1 <br/>
 * Delete when empty <br/>
 * Delete when full <br/>
 * Enter data more than max <br/>
 * Delete till cache is empty
 */
public class LRUCache {

	private ListNode head; // points to most recently used item
	private ListNode tail; // points to least recently used item
	private Map<Integer, ListNode> map = new HashMap<Integer, ListNode>(); // map item to linked list node
	private int MAX_SIZE = 5;
	private int size = 0;

	public LRUCache(int size) {
		MAX_SIZE = size;
	}

	public void used(int data) {
		if (containsInCache(data)) { // cache hit
			ListNode node = map.get(data);
			// if any other node other than head is accessed then it has to be moved to the head position as its the
			// most recently accessed
			if (node != head) {
				deleteFromCache(data);
				node.next = head;
				head.previous = node;
				head = node;
				map.put(data, node);
			}
		} else { // cache miss
			addIntoCache(data);
		}
	}

	public int get(int key) {
		if (containsInCache(key)) {
			ListNode node = map.get(key);
			if (node != head) {
				// remove from cache
				deleteFromCache(key);
				addToFront(node, key);
			}
			return node.data;
		}
		return -1;
	}

	private void addToFront(ListNode node, int key) {
		node.next = head;
		head.previous = node;
		head = node;
		map.put(key, node);
	}

	public void addIntoCache(int data) {
		size++;
		if (head == null) {
			head = new ListNode(data);
			tail = head;
			return;
		}
		if (size > MAX_SIZE) { // eviction
			tail = tail.previous;
			ListNode next = tail.next;
			tail.next = null;
			next.previous = null;
			map.remove(next.data);
		}
		ListNode newNode = new ListNode(data);
		newNode.next = head;
		if (head != null) {
			head.previous = newNode;
		}
		head = newNode;
		map.put(data, newNode);
		return;
	}

	public void printCache() {
		ListNode temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}
		System.out.println();
	}

	public boolean containsInCache(int data) {
		return map.containsKey(data);
	}

	public void deleteFromCache(int data) {
		ListNode node = map.get(data);
		if (node == null) {
			return;
		}
		map.remove(data);
		if (size == 1) {
			head = null;
			tail = null;
		} else if (node == head) {
			head = head.next;
			if (head != null) {
				head.previous = null;
			}
			node.next = null;
		} else if (node == tail) {
			tail = tail.previous;
			tail.next = null;
		} else {
			ListNode before = node.previous;
			ListNode next = node.next;
			before.next = next;
			next.previous = before;
		}
	}

	public static void main(String args[]) {
		LRUCache lruCache = new LRUCache(5);
		lruCache.used(4);

		lruCache.used(5);
		lruCache.printCache();
		lruCache.used(6);
		lruCache.printCache();
		lruCache.used(5);
		lruCache.printCache();
		lruCache.used(9);
		lruCache.printCache();
		lruCache.used(10);
		lruCache.printCache();
		lruCache.used(11);
		lruCache.printCache();
		lruCache.used(16);
		lruCache.printCache();
		lruCache.used(10);
		lruCache.printCache();
		lruCache.deleteFromCache(10);
		lruCache.printCache();
		lruCache.deleteFromCache(9);
		lruCache.printCache();
	}
}
