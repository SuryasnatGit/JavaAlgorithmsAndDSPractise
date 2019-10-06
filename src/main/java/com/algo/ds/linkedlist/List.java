package com.algo.ds.linkedlist;

public interface List<E> {

	public int size();

	public boolean isEmpty();

	public E get(int i);

	public E set(int i, E element);

	public boolean add(E element);

	public void add(int i, E element);

	public E remove(int i);
}
