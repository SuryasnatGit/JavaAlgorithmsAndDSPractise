package com.algo.ds.queue;

/**
 * this does not use the number of items entity.
 * @author Suryasnat
 *
 */
public class QueueAsArray1 {
	
	private int maxSize;
	private int[] arr;
	private int front;
	private int rear;
	
	public QueueAsArray1(int s) {
		maxSize = s+1;
		arr = new int[maxSize];
		front = 0;
		rear = -1;
	}
	
	public void insert(int n){
		if(rear == maxSize-1)
			rear = -1;
		arr[++rear] = n;
	}
	
	public int remove(){
		int n = arr[front++];
		if(front == maxSize)
			front = 0;
		return n;
	}
	
	public int peek(){
		return arr[front];
	}
	
	public boolean isEmpty(){
		return ((rear + 1 == front) || (front + maxSize - 1 == rear));
	}
	
	public boolean isFull(){
		return ((rear + 2 == front) || (front + maxSize -2 == rear));
	}
	
	public int size(){
		if(rear >= front)
			return rear - front + 1;
		else
			return (maxSize - front) + (rear + 1);
	}
}
