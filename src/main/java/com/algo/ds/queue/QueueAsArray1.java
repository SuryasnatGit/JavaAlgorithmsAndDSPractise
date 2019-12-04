package com.algo.ds.queue;

import java.util.Arrays;

/**
 * this does not use the number of items entity.
 * 
 * @author Suryasnat
 *
 */
public class QueueAsArray1 implements Queue<Integer> {

	private int capacity;
	private int[] arr;
	private int front;
	private int rear;

	public QueueAsArray1(int s) {
		capacity = s + 1;
		arr = new int[capacity];
		front = 0;
		rear = capacity - 1;
	}

	public boolean isEmpty() {
		return ((rear + 1 == front) || (front + capacity - 1 == rear));
	}

	public boolean isFull() {
		return ((rear + 2 == front) || (front + capacity - 2 == rear));
	}

	public int size() {
		if (rear >= front)
			return rear - front + 1;
		else
			return (capacity - front) + (rear + 1);
	}

	@Override
	public void enqueue(Integer data) {
		if (isFull())
			return;

		rear = (rear + 1) % capacity;
		arr[rear] = data;
	}

	@Override
	public Integer dequeue() {
		if (isEmpty())
			return Integer.MIN_VALUE;

		int d = arr[front];
		front = (front + 1) % capacity;
		return d;
	}

	@Override
	public Integer front() {
		if (isEmpty())
			return Integer.MIN_VALUE;

		return arr[front];
	}

	@Override
	public Integer rear() {
		if (isEmpty())
			return Integer.MIN_VALUE;

		return arr[rear];
	}

	@Override
	public void display() {
		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) {
		QueueAsArray1 qa = new QueueAsArray1(3);
		qa.enqueue(5);
		qa.enqueue(10);
		qa.enqueue(15);
		qa.enqueue(20);
		qa.display();
		System.out.println(qa.dequeue());
		System.out.println(qa.dequeue());
		qa.display();
		System.out.println(qa.dequeue());
		System.out.println(qa.dequeue());
	}
}
