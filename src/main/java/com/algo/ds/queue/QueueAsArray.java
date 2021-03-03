package com.algo.ds.queue;

import java.util.Arrays;

/**
 * For implementing queue, we need to keep track of two indices, front and rear. We enqueue an item at the rear and
 * dequeue an item from front. If we simply increment front and rear indices, then there may be problems, front may
 * reach end of the array. The solution to this problem is to increase front and rear in circular manner
 * 
 * All operations have O(1) time
 *
 */
public class QueueAsArray implements MyQueue<Integer> {

	private int capacity;
	private int[] arr;
	private int front;
	private int rear;
	private int size;// number of items

	public QueueAsArray(int n) {
		this.capacity = n;
		arr = new int[capacity];
		front = 0;
		rear = capacity - 1;
		size = 0;
	}

	public void enqueue(Integer num) {
		if (isFull())
			return;

		rear = (rear + 1) % capacity;
		arr[rear] = num;
		size++;
	}

	public Integer dequeue() {
		if (isEmpty())
			return Integer.MIN_VALUE;

		int n = arr[front];
		front = (front + 1) % capacity;
		size--;
		return n;
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

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == capacity;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void display() {
		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) {
		QueueAsArray qa = new QueueAsArray(3);
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
