package com.algo.ds.stack;


public interface MyStack<E> {

    public void push(E e);

    public E pop();

    public E top();

    public boolean isEmpty();

    public int size();
}
