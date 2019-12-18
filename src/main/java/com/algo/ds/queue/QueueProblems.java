package com.algo.ds.queue;

import java.util.LinkedList;
import java.util.Queue;

public class QueueProblems {

	public void binaryNumbers(int n) {
		Queue<String> queue = new LinkedList<>();
		queue.add("1");

		int i = 1;
		while (i++ <= n) {
			queue.add(queue.peek() + "0");
			queue.add(queue.peek() + "1");

			System.out.println(queue.poll());
		}
	}

	public static void main(String[] args) {
		QueueProblems qp = new QueueProblems();
		qp.binaryNumbers(20);
	}

}
