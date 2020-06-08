package com.hackerrank;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {

	// private ReentrantLock mutex = new ReentrantLock();

	private Object producerObject = new Object();
	private Object consumerObject = new Object();

	private Queue<Integer> queue;

	public ProducerConsumer() {
		queue = new LinkedList<Integer>();
	}

	class Producer implements Runnable {

		private int count;

		public Producer() {
			count = 1;
		}

		@Override
		public void run() {
			while (true) {
				int item = generateItem();
				System.out.println("producer thread :" + Thread.currentThread().getName());
				System.out.println("Item produced :" + item);

				// mutex.lock();

				synchronized (producerObject) {
					queue.offer(item);

					System.out.println("producer notify all.");
					// producerObject.notify();
					producerObject.notifyAll();
				}

				// notifyAll();
				// mutex.unlock();

				// Getting Exception in thread "Thread-0" java.lang.IllegalMonitorStateException after first run and
				// customer wating..
				// System.out.println("producer notify.");
				// notifyAll();
			}
		}

		public int generateItem() {
			return count++;
		}
	}

	class Consumer implements Runnable {

		@Override
		public void run() {
			while (true) {

				// mutex.lock();
				synchronized (consumerObject) {
					if (queue.isEmpty()) {
						try {
							System.out.println("consumer waiting..");
							consumerObject.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					int item = queue.poll();
					processItem(item);
				}

				// mutex.unlock();

				// System.out.println("consumer notify..");
				// notify();
			}
		}

		public void processItem(int item) {
			System.out.println("consumer thread :" + Thread.currentThread().getName());
			System.out.println("Item consumed :" + item);
		}

	}

	public static void main(String[] args) {
		ProducerConsumer pc = new ProducerConsumer();

		Thread pt = new Thread(pc.new Producer());
		pt.start();

		ExecutorService executor = Executors.newFixedThreadPool(5);

		for (int i = 0; i < 500; i++) {
			executor.submit(pc.new Consumer());
		}

		executor.shutdown();

		try {
			pt.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
