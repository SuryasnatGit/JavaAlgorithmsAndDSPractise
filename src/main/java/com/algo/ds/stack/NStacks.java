package com.algo.ds.stack;

import java.util.Arrays;

/**
 * Question: Implement N > 0 stacks using a single array to store all stack data (you may use auxiliary arrays in your
 * stack object, but all of the objects in all of the stacks must be in the same array). No stack should be full unless
 * the entire array is full.
 * 
 * ● Eg.
 * 
 * N = 3; capacity = 10;
 * 
 * Stacks stacks = new Stacks(N, capacity);
 * 
 * stacks.put(0, 10); // put 10 in stack 0
 * 
 * stacks.put(2, 11); // put 11 in stack 2
 * 
 * stacks.pop(0) = 10; // pop from stack 0
 * 
 * stacks.pop(2) = 11; // pop from stack 2
 * 
 * @author surya
 *
 */
public class NStacks {

	private int[] topOfStack;
	private int[] nextIndex;
	private int[] stackData;

	private int nextAvailable;

	public NStacks(int numOfStacks, int capacity) {
		topOfStack = new int[numOfStacks];
		Arrays.fill(topOfStack, -1);

		nextIndex = new int[capacity];
		for (int i = 0; i < nextIndex.length - 1; i++) {
			nextIndex[i] = i + 1;
		}
		nextIndex[nextIndex.length - 1] = -1; // -1 is used to indicate end of free list.

		stackData = new int[capacity];

		nextAvailable = 0;
	}

	public void push(int stackNum, int value) {
		if (stackNum < 0 || stackNum >= topOfStack.length) {
			throw new ArrayIndexOutOfBoundsException();
		}

		int currentIndex = nextAvailable;
		nextAvailable = nextIndex[currentIndex];
		stackData[currentIndex] = value;
		nextIndex[currentIndex] = topOfStack[stackNum];
		topOfStack[stackNum] = currentIndex;
	}

	public int pop(int stackNum) {
		if (stackNum < 0 || stackNum >= topOfStack.length || topOfStack[stackNum] < 0) {
			throw new IndexOutOfBoundsException();
		}

		int currentIndex = topOfStack[stackNum];
		int value = stackData[currentIndex];
		topOfStack[stackNum] = nextIndex[currentIndex];
		nextIndex[currentIndex] = nextAvailable;
		nextAvailable = currentIndex;
		return value;
	}

	public static void main(String[] args) {
		NStacks ns = new NStacks(2, 6);
		ns.push(0, 10);
		ns.push(1, 20);

		System.out.println(ns.pop(1));
		System.out.println(ns.pop(0));
		System.out.println(ns.pop(2));
	}

}
