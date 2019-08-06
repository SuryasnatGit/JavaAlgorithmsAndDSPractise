package com.algo.ds.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/implement-lru-cache/
 * 
 * LRU cache stand for Least Recently Used Cache. which evict least recently used entry. As Cache
 * purpose is to provide fast and efficient way of retrieving data. it need to meet certain
 * requirement.
 * 
 * Some of the Requirement are
 * 
 * 1. Fixed Size: Cache needs to have some bounds to limit memory usages.
 * 
 * 2. Fast Access: Cache Insert and lookup operation should be fast , preferably O(1) time.
 * 
 * 3. Replacement of Entry in case , Memory Limit is reached: A cache should have efficient
 * algorithm to evict the entry when memory is full.
 * 
 * In case of LRU cache we evict least recently used entry so we have to keep track of recently used
 * entries, entries which have not been used from long time and which have been used recently. plus
 * lookup and insertion operation should be fast enough.
 * 
 * When we think about O(1) lookup , obvious data structure comes in our mind is HashMap. HashMap
 * provide O(1) insertion and lookup. but HashMap does not has mechanism of tracking which entry has
 * been queried recently and which not.
 * 
 * To track this we require another data-structure which provide fast insertion ,deletion and
 * updation , in case of LRU we use Doubly Linkedlist . Reason for choosing doubly LinkList is O(1)
 * deletion , updation and insertion if we have the address of Node on which this operation has to
 * perform. So our Implementation of LRU cache will have HashMap and Doubly LinkedList. In Which
 * HashMap will hold the keys and address of the Nodes of Doubly LinkedList . And Doubly LinkedList
 * will hold the values of keys.
 * 
 * Test cases: MAX_SIZE greater than 1 <br/>
 * Delete when empty <br/>
 * Delete when full <br/>
 * Enter data more than max <br/>
 * Delete till cache is empty
 */
public class LRUCache {

	private Node head;
	private Node tail;
	private Map<Integer, Node> map = new HashMap<Integer, Node>();
	private int MAX_SIZE = 5;
	private int size = 0;

	public LRUCache(int size) {
		MAX_SIZE = size;
	}

	public void used(int data) {
		if (containsInCache(data)) {
			Node node = map.get(data);
			if (node != head) {
				deleteFromCache(data);
				node.next = head;
				head.before = node;
				head = node;
				map.put(data, node);
			}
		} else {
			addIntoCache(data);
		}
	}

	public void addIntoCache(int data) {
		size++;
		if (head == null) {
			head = Node.newNode(data);
			tail = head;
			return;
		}
		if (size > MAX_SIZE) { // eviction
			tail = tail.before;
			Node next = tail.next;
			tail.next = null;
			next.before = null;
			map.remove(next.data);
		}
		Node newNode = Node.newNode(data);
		newNode.next = head;
		if (head != null) {
			head.before = newNode;
		}
		head = newNode;
		map.put(data, newNode);
		return;
	}

	public void printCache() {
		Node temp = head;
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
		Node node = map.get(data);
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
				head.before = null;
			}
			node.next = null;
		} else if (node == tail) {
			tail = tail.before;
			tail.next = null;
		} else {
			Node before = node.before;
			Node next = node.next;
			before.next = next;
			next.before = before;
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
