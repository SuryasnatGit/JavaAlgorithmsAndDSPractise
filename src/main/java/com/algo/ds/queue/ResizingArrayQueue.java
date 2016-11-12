package com.algo.ds.queue;

import java.util.NoSuchElementException;

public class ResizingArrayQueue<E> {

    private E[] data;
    private int n; // num of elements
    private int first; // index of first element in the queue
    private int last; // index of next available slot

    public ResizingArrayQueue() {
        data = (E[]) new Object[2]; // initialize with size 2
        n = 0;
        first = 0;
        last = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void enqueue(E element) {
        if (n == data.length)
            resize(2 * data.length);
        data[last++] = element;
        n++;
    }

    public E dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("queue underflow");
        E temp = data[first];
        data[first] = null; // for GC
        first++;
        n--;
        // check if array needs resizing..
        if (n > 0 && n == data.length / 4)
            resize(data.length / 2);
        return temp;
    }

    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException("queue underflow");
        return data[first];
    }

    private void resize(int capacity) {
        assert capacity >= n;
        E[] temp = (E[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = data[(first + i) % data.length];
        }
        data = temp;
        first = 0;
        last = n;
    }
}
