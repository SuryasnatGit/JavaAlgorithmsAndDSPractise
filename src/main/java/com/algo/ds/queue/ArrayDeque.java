package com.algo.ds.queue;

/**
 * Array impl of deque using circular array
 * 
 * @author ctsuser1
 *
 * @param <E>
 */
public class ArrayDeque<E> implements Dequeue<E> {

    private E[] data;
    private int front, back;
    private int size, capacity;

    public ArrayDeque(int capacity) {
        data = (E[]) new Object[capacity];
        front = 1;
        back = 0;
        size = 0;
        this.capacity = capacity - 1;
    }

    @Override
    public void insertFront(E element) throws Exception {
        if (isFull())
            throw new Exception("queue is full");
        data[front] = element;
        front = (front + 1) % data.length;
        size++;
    }

    @Override
    public void insertBack(E element) throws Exception {
        if (isFull())
            throw new Exception("queue is full");
        data[back] = element;
        back = (back - 1) % data.length;
        size++;
    }

    @Override
    public E removeFront() throws Exception {
        if (isEmpty())
            throw new Exception("queue is empty");
        E temp = data[front];
        data[front] = null; // for GC
        front = (front - 1) % data.length;
        size--;
        return temp;
    }

    @Override
    public E removeBack() throws Exception {
        if (isEmpty())
            throw new Exception("queue is empty");
        E temp = data[back];
        data[back] = null;
        back = (back + 1) % data.length;
        size--;
        return temp;
    }

    @Override
    public E front() throws Exception {
        if (isEmpty())
            throw new Exception("queue is empty");
        return data[(front - 1) % data.length];
    }

    @Override
    public E back() throws Exception {
        if (isEmpty())
            throw new Exception("queue is empty");
        return data[(back + 1) % data.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

}
