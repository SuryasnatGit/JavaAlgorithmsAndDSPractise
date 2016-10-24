package com.algo.ds.queue;

public class QueueAsArray {

	private int max;
	private int[] arr;
	private int front;
	private int rear;
	private int N;// number of items
	
	public QueueAsArray(int n) {
		max = n;
		arr = new int[max];
		front = 0;
		rear = -1;
		N = 0;
	}
	
	public void insert(int num){
		if(rear == max-1)
			rear = -1;
		arr[++rear] = num;
		N++;
	}
	
	public int remove(){
		int n = arr[front++];
		if(front == max)
			front = 0;
		N--;
		return n;
	}
	
	public int peekFront(){
		return arr[front];
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public boolean isFull(){
		return N == max;
	}
}
