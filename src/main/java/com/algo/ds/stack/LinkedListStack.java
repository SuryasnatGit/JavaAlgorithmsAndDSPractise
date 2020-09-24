package com.algo.ds.stack;

import com.algo.ds.linkedlist.SingleLinkedList;

// uses adaptor pattern
public class LinkedListStack<E> implements MyStack<E> {

    private SingleLinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new SingleLinkedList<>();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E top() {
        return linkedList.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public int size() {
        return linkedList.size();
    }

}
