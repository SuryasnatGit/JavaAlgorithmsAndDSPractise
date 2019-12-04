package com.algo.ds.queue;

public interface Queue<E> {

	/**
	 * Adds element to rear of queue
	 * 
	 * @param data
	 */
	void enqueue(E data);

	/**
	 * removes element from front of queue
	 * 
	 * @return
	 */
	E dequeue();

	/**
	 * retrieves but does not remove the front of the queue
	 * 
	 * @return
	 */
	E front();

	/**
	 * retrieves but does not remove the rear of the queue
	 * 
	 * @return
	 */
	E rear();

	boolean isEmpty();

	int size();

	void display();
}
