package com.designpatterns;

import java.util.concurrent.locks.ReentrantLock;

public class Singleton {

	private static Singleton instance = null;
	private static ReentrantLock lock = new ReentrantLock();

	private Singleton() {

	}

	public static Singleton getInstance1() {
		if (instance == null) {
			synchronized (Object.class) {
				if (instance == null) {
					System.out.println("instance 1 created");
					instance = new Singleton();
				}
			}
		}
		System.out.println("instance 1 returned");
		return instance;
	}

	public static Singleton getInstance2() {
		if (instance == null) {
			lock.lock();
			if (instance == null) {
				System.out.println("instance 2 created");
				instance = new Singleton();
			}
			lock.unlock();
		}
		System.out.println("instance 2 returned");
		return instance;
	}

	public static void main(String[] args) {
		Singleton.getInstance1();
		Singleton.getInstance2();
	}

}
