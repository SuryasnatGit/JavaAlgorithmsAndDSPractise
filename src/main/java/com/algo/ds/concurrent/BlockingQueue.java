package com.algo.ds.concurrent;

import java.util.LinkedList;
import java.util.List;

/**
 * A blocking queue is a queue that blocks when you try to dequeue from it and the queue is empty,
 * or if you try to enqueue items to it and the queue is already full. A thread trying to dequeue
 * from an empty queue is blocked until some other thread inserts an item into the queue. A thread
 * trying to enqueue an item in a full queue is blocked until some other thread makes space in the
 * queue, either by dequeuing one or more items or clearing the queue completely.
 * 
 * Notice how notifyAll() is only called from enqueue() and dequeue() if the queue size is equal to
 * the size bounds (0 or limit). If the queue size is not equal to either bound when enqueue() or
 * dequeue() is called, there can be no threads waiting to either enqueue or dequeue items.
 *
 */
public class BlockingQueue {

	private List<String> queue = new LinkedList<String>();
	private int limit;

	public BlockingQueue(int limit) {
		this.limit = limit;
	}

	public synchronized void enqueue(String input) throws InterruptedException {
		while (queue.size() == limit) {
			wait();
		}

		if (queue.size() == 0)
			notifyAll();

		queue.add(input);
	}

	public synchronized String dequeue() throws InterruptedException {
		while (queue.size() == 0) {
			wait();
		}

		if (queue.size() == limit)
			notifyAll();

		return queue.remove(0);
	}

}
