package com.algo.ds.stack;

/**
 * space complexity - O(N) where N is the # of elements in stack. time complexity - O(1)
 * 
 */
public class ArrayStack<E> implements Stack<E> {

    private static int capacity = 1000; // default capacity
    private int index = -1;
    private E[] data;

    public ArrayStack() {
        this(capacity);
    }

    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public void push(E e) {
        if (index == data.length)
            throw new IllegalStateException("stack is full");
        data[++index] = e;
    }

    @Override
    public E pop() {
        if (isEmpty())
            return null;
        E e = data[index];
        data[index] = null; // dereference for GC
        index--;
        return e;
    }

    @Override
    public E top() {
        if (isEmpty())
            return null;
        return data[index];
    }

    @Override
    public boolean isEmpty() {
        return index == -1;
    }

    @Override
    public int size() {
        return (index + 1);
    }

	/**
	 * sort a stack in ascending order (with biggest items on top). you may use at most one additional
	 * stack to hold items, but you may not copy the elements into any other data strcuture (such as an
	 * array). The stack supports the following operations: push, pop, peek, and isEmpty
	 */
	public ArrayStack<Integer> sort(ArrayStack<Integer> inputStack) {
		ArrayStack<Integer> resStack = new ArrayStack<>();
		while (!inputStack.isEmpty()) {
			int elem = inputStack.pop();
			while (!resStack.isEmpty() && (Integer) resStack.top() > elem) {// for smallest item on top change to <
				inputStack.push((Integer) resStack.pop());
			}
			resStack.push(elem);
		}
		return resStack;
	}

	public static void main(String[] args) {
		ArrayStack<Integer> stack = new ArrayStack<>();
		stack.push(5);
		stack.push(1);
		stack.push(900);
		stack.push(10);

		ArrayStack<Integer> res = stack.sort(stack);
		while (!res.isEmpty()) {
			System.out.println(res.pop());
		}
	}
}
