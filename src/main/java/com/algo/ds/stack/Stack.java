package com.algo.ds.stack;


public interface Stack<E> {

    public void push(E e);

    public E pop();

    public E top();

    public boolean isEmpty();

    public int size();
}
