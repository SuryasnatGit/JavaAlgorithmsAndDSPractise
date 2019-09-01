package com.ctci.threadslocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * In the famous dining philosophers problem, a bunch of philosophers are sitting around a circular table with one
 * chopstick between each of them. A philosopher needs both chopsticks to eat, and always picks up the left chopstick
 * before the right one. A deadlock could potentially occur if all the philosophers reached for the left chopstick at
 * the same time. Using threads and locks, implement a simulation of the dining philosophers problem that prevents
 * deadlocks.
 * 
 * @author surya
 *
 */
public class DiningPhilosophers {

	// Running the below code may lead to a deadlock if all the philosophers have a left chopstick and are waiting
	// for the right one.
	class Philosopher extends Thread {

		private int eatableItems = 10;
		private Chopstick left, right;

		public Philosopher(Chopstick left, Chopstick right) {
			this.left = left;
			this.right = right;
		}

		// Alternatively, we can label the chopsticks with a number from e to N - 1. Each philosopher attempts to
		// pick up the lower numbered chopstick first. This essentially means that each philosopher goes for the left
		// chopstick before right one (assuming that's the way you labeled it), except for the last philosopher who
		// does this in reverse. This will break the cycle.
		private Chopstick lower, higher;
		private int index;

		public Philosopher(int i, Chopstick left, Chopstick right) {
			this.index = i;
			if (left.getNumber() < right.getNumber()) {
				this.lower = left;
				this.higher = right;
			} else {
				// reverse for last philosopher
				this.lower = right;
				this.higher = left;
			}
		}

		// public void eat() {
		// pickup();
		// chew();
		// putdown();
		// }

		// to prevent deadlock
		public void eat1() {
			if (pickup()) {
				chew();
				putdown();
			}
		}

		// public void pickup() {
		// left.pickup();
		// right.pickup();
		// }

		// to prevent deadlock
		public boolean pickup() {
			// attempt to pickup
			if (!left.pickup()) {
				return false;
			}
			if (!right.pickup()) {
				left.putdown();
				return false;
			}
			return true;
		}

		public void putdown() {
			right.putdown();
			left.putdown();
		}

		public void chew() {
			System.out.println("chew");
		}

		@Override
		public void run() {
			for (int i = 0; i < eatableItems; i++) {
				eat1();
			}
		}
	}

	class Chopstick {

		private Lock lock;

		private int number;

		public Chopstick() {
			lock = new ReentrantLock();
		}

		public Chopstick(int num) {
			lock = new ReentrantLock();
			this.number = num;
		}

		// public void pickup() {
		// System.out.println("picked up");
		// lock.lock();
		// }

		// to prevent deadlock use this instead of above
		public boolean pickup() {
			return lock.tryLock();
		}

		public void putdown() {
			System.out.println("put down");
			lock.unlock();
		}

		public int getNumber() {
			return number;
		}
	}

	public static void main(String[] args) {
		Chopstick left = new DiningPhilosophers().new Chopstick();
		Chopstick right = new DiningPhilosophers().new Chopstick();
		Philosopher philosopher = new DiningPhilosophers().new Philosopher(left, right);
		Philosopher philosopher1 = new DiningPhilosophers().new Philosopher(left, right);
		Philosopher philosopher2 = new DiningPhilosophers().new Philosopher(left, right);

		philosopher.start();
		philosopher1.start();
		philosopher2.start();
	}
}
