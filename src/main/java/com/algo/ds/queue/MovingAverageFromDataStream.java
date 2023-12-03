package com.algo.ds.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * 
 * Example:
 * 
 * MovingAverage m = new MovingAverage(3); m.next(1) = 1 m.next(10) = (1 + 10) / 2 m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 * 
 * T - O(1) S - O(k)
 */
public class MovingAverageFromDataStream {

	/**
	 * Using Queue as DS
	 */
	class MovingAverageUsingQueueDS {
		private int size;
		private Queue<Integer> queue;
		private int sum;

		public MovingAverageUsingQueueDS(int size) {
			this.size = size;
			this.queue = new LinkedList<>();
			this.sum = 0;
		}

		public double getAverage(int num) {
			if (queue.size() == size) {
				sum -= queue.poll();
			}
			queue.offer(num);
			sum += num;
			return (double) sum / queue.size();
		}
	}

	/**
	 * Using Fixed sized array as Circular Queue
	 */
	class MovingAverageUsingArray {
		private int capacity;
		private int[] window;;
		private int sum;
		private int insertIndex;
		private int count;

		public MovingAverageUsingArray(int size) {
			this.capacity = size;
			this.window = new int[size];
			this.sum = 0;
			this.insertIndex = 0;
			this.count = 0;
		}

		public double getAverage(int num) {
			if (count < capacity) {
				count++;
			} else {
				sum -= window[insertIndex];
			}
			window[insertIndex] = num;
			sum += num;
			insertIndex = (insertIndex + 1) % capacity;
			return (double) sum / count;
		}

	}

	public static void main(String[] args) {
		MovingAverageUsingQueueDS mov = new MovingAverageFromDataStream().new MovingAverageUsingQueueDS(3);
		System.out.println(mov.getAverage(5));
		System.out.println(mov.getAverage(10));
		System.out.println(mov.getAverage(15));
		System.out.println(mov.getAverage(20));

		MovingAverageUsingArray mov1 = new MovingAverageFromDataStream().new MovingAverageUsingArray(3);
		System.out.println(mov1.getAverage(5));
		System.out.println(mov1.getAverage(10));
		System.out.println(mov1.getAverage(15));
		System.out.println(mov1.getAverage(20));
	}
}
