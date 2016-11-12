package com.algo.ds.queue;

import java.util.NoSuchElementException;

public class CircularArrayQueue<E> {

    private E[] data;
    private int read, write, count;
    private int defaultCapacity = 10;

    public CircularArrayQueue() {
        data = (E[]) new Object[defaultCapacity];
        read = 0;
        write = 0;
        count = 0;
    }

    public CircularArrayQueue(int initialCapacity) {
        data = (E[]) new Object[initialCapacity];
        read = 0;
        write = 0;
        count = 0;
    }

    public void enqueue(E element) {
        if (isFull())
            throw new NoSuchElementException("queue overflow");
        data[write] = element;
        write = (write + 1) % data.length;
        count++;
    }

    public E dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("queue empty");
        E temp = data[read];
        data[read] = null; // for GC
        read = (read + 1) % data.length;
        count--;
        return temp;
    }

    public boolean isFull() {
        return read == (write + 1) % data.length;
    }

    public boolean isEmpty() {
        return read == write;
    }

}
