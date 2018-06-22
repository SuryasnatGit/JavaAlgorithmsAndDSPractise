package com.ctci.stacknqueue;

import java.util.EmptyStackException;

/**
 * Use an array to implement 3 stacks
 * 
 * @author surya
 *
 */
public class ThreeStackArray {

	private int stackSize = 100;
	private int[] array = new int[stackSize * 3];
	private int[] stackPointer = { -1, -1, -1 };// pointer to keep track of top elements

	public void push(int stackNum, int value) throws Exception {
		// check size
		if (stackPointer[stackNum] + 1 > stackSize)
			throw new Exception("out of bounds");
		// increment stack pinter
		stackPointer[stackNum]++;
		// update top value
		array[absTop(stackNum)] = value;
	}

	int pop(int stackNum) throws Exception {
		if (isEmpty(stackNum)) {
			throw new EmptyStackException();
		}
		int value = array[absTop(stackNum)]; // get top
		array[absTop(stackNum)] = 0; // clear index
		stackPointer[stackNum]--; // decrement pointer
		return value;
	}

	int peek(int stackNum) {
		if (isEmpty(stackNum)) {
			throw new EmptyStackException();
		}
		int index = absTop(stackNum);
		return array[index];
	}

	boolean isEmpty(int stackNum) {
		return stackPointer[stackNum] == -1;
	}

	// returns abs top of stack
	private int absTop(int stackNum) {
		return stackNum * stackSize + stackPointer[stackNum];
	}
}
