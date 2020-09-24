package com.leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that
 * support the peek() operation -- it essentially peek() at the element that will be returned by the next call to
 * next().
 * 
 * Example:
 * 
 * Assume that the iterator is initialized to the beginning of the list: [1,2,3].
 * 
 * Call next() gets you 1, the first element in the list. Now you call peek() and it returns 2, the next element.
 * Calling next() after that still return 2. You call next() the final time and it returns 3, the last element. Calling
 * hasNext() after that should return false. Follow up: How would you extend your design to be generic and work with all
 * types, not just integer?
 * 
 * Priority : Medium
 * 
 * @param <V>
 */
public class PeekingIterator<V> implements Iterator<V> {

	private Iterator<V> iter;
	private V nextInt;

	public PeekingIterator(Iterator<V> iterator) {
		this.iter = iterator;
	}

	// Returns the next element in the iteration without advancing the iterator.
	public V peek() {
		if (!hasNext()) {
			return null;
		}

		if (nextInt != null) {
			return nextInt;
		}

		nextInt = iter.next();
		return nextInt;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public V next() {
		if (!hasNext()) {
			return null;
		}

		if (nextInt != null) {
			V val = nextInt;
			nextInt = null;
			return val;
		}

		return iter.next();
	}

	@Override
	public boolean hasNext() {
		return nextInt != null || iter.hasNext();
	}

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3);
		PeekingIterator<Integer> pk = new PeekingIterator<Integer>(list.iterator());
		System.out.println(pk.peek());
		System.out.println(pk.peek());
		System.out.println(pk.next());
		System.out.println(pk.peek());
		System.out.println(pk.next());
		System.out.println(pk.next());
		System.out.println(pk.peek());

		List<String> list1 = Arrays.asList("one", "two");
		PeekingIterator<String> pk1 = new PeekingIterator<String>(list1.iterator());
		System.out.println(pk1.peek());
		System.out.println(pk1.peek());
		System.out.println(pk1.next());
		System.out.println(pk1.peek());
		System.out.println(pk1.next());
		System.out.println(pk1.next());
		System.out.println(pk1.peek());
	}
}
