package com.algo.ds.linkedlist;

import java.util.HashMap;
import java.util.Map;

import com.algo.common.ListNode;

/**
 * http://www.geeksforgeeks.org/implement-lru-cache/
 * 
 * CTCI : 16.25
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

	private ListNode head = new ListNode(-1, -1); // points to most recently used item
	private ListNode tail = new ListNode(-1, -1); // points to least recently used item
	private Map<Integer, ListNode> map = new HashMap<>(); // map item to linked list node
	private int MAX_SIZE = 5;

	public LRUCache(int capacity) {
		this.MAX_SIZE = capacity;
		this.head.next = tail;
		this.tail.previous = head;
	}

	public void put(int key, int value) {
		if (get(key) != -1) { // valid value exists. will be overwritten
			map.get(key).value = value;
			return;
		}
		if (map.size() == MAX_SIZE) {
			// evict from head (LRU)
			map.remove(head.next.data);
			head.next = head.next.next;
			head.next.previous = head;
		}
		ListNode node = new ListNode(key, value);
		map.put(key, node);
		moveNodeToTail(node);
	}

	// work with only tail node
	private void moveNodeToTail(ListNode current) {
		current.next = tail;
		tail.previous.next = current;
		current.previous = tail.previous;
		tail.previous = current;
	}

	public int get(int key) {
		if (!map.containsKey(key)) {
			return -1;
		}
		ListNode current = map.get(key);
		// detach current so that it can be moved to tail of list
		current.previous.next = current.next;
		current.next.previous = current.previous;
		moveNodeToTail(current);

		return current.data;
	}

	public void printCache() {
		ListNode node = head.next;
		while (node != null && node.data != -1) {
			System.out.println(node.data);
			node = node.next;
		}
	}


	public static void main(String args[]) {
		LRUCache lruCache = new LRUCache(2);
		lruCache.put(1, 1);
		lruCache.put(2, 2);
		lruCache.printCache();
		lruCache.put(3, 3);
		lruCache.printCache();
		System.out.println(lruCache.get(3));
		lruCache.printCache();
		System.out.println(lruCache.get(2));
		lruCache.printCache();
	}
}
