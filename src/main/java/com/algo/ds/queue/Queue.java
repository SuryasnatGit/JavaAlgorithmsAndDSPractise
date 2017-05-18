package com.algo.ds.queue;

public interface Queue<E> {

	E peek();

	void enqueue(E data);

	E dequeue();

	boolean isEmpty();

	int size();

	void display();
}
