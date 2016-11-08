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

}
