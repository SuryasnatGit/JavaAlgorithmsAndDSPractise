package com.algo.ds.concurrent;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Lock free non blocking queue implementation. declaration of the head and tail references as AtomicReferences, which
 * ensures that any update on these references is an atomic operation. This data type in Java implements the necessary
 * compare-and-swap operation.
 * 
 *
 */
public class NonBlockingQueue<T> {

	private final AtomicReference<Node<T>> head, tail;
	private final AtomicInteger size;

	public NonBlockingQueue() {
		this.head = new AtomicReference<>(null);
		this.tail = new AtomicReference<>(null);
		this.size = new AtomicInteger();
		size.set(0);
	}

	// lock free add. We attempt to add the new node to the queue until the CAS operation succeeds to update the tail,
	// which must still be the same tail to which we appended the new node.
	public void add(T element) {
		if (element == null) {
			throw new NullPointerException();
		}

		Node<T> node = new Node<>(element);
		Node<T> currentTail;
		do {
			currentTail = tail.get();
			node.setPrevious(currentTail);
		} while (!tail.compareAndSet(currentTail, node));

		if (node.getPrevious() != null) {
			node.getPrevious().setNext(node);
		}

		head.compareAndSet(null, node); // for inserting the first element
		size.incrementAndGet();
	}

	// The CAS operation ensures that we move the current head only if no other node has been removed in the meantime.
	public T get() {
		if (head.get() == null) {
			throw new NoSuchElementException();
		}

		Node<T> currentHead;
		Node<T> nextNode;
		do {
			currentHead = head.get();
			nextNode = currentHead.getNext();
		} while (!head.compareAndSet(currentHead, nextNode));

		size.decrementAndGet();
		return currentHead.getValue();
	}
}

// declare the references to the previous and next node as volatile. This ensures that we update these references
// always in the main memory (thus are directly visible to all threads). The same for the actual node value.
class Node<T> {
	private volatile T value;
	private volatile Node<T> previous;
	private volatile Node<T> next;

	public Node(T val) {
		this.value = val;
		this.next = null;
	}

	public T getValue() {
		return value;
	}

	public Node<T> getPrevious() {
		return previous;
	}

	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
}
