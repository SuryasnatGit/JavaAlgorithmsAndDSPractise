package com.algo.ds.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * Category : Hard
 *
 * @param <E>
 */
public class ArrayList<E> implements List<E> {

	private E[] data;
	private int size;
	private int capacity = 16;// default capacity

	public ArrayList() {
		data = (E[]) new Object[capacity];
		size = 0;
	}

	public ArrayList(int capacity) {
		data = (E[]) new Object[capacity];
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E get(int i) {
		checkRange(i, size);
		return data[i];
	}

	@Override
	public E set(int i, E element) {
		checkRange(i, size);
		E temp = data[i];
		data[i] = element;
		return temp;
	}

	@Override
	public boolean add(E element) {
		if (size == data.length) {
			// throw new IllegalStateException("Array full");
			// instead if throwing we can grow the array dynamically.
			resize(2 * data.length);
		}
		data[size++] = element;
		return true;
	}

	@Override
	public void add(int i, E element) {
		checkRange(i, size + 1);
		if (size == data.length) {
			// throw new IllegalStateException("Array full");
			// instead if throwing we can grow the array dynamically.
			resize(2 * data.length);
		}
		for (int k = size - 1; k >= i; k--) // shift elements to the right
			data[k + 1] = data[k];
		data[i] = element;
		size++;
	}

	@Override
	public E remove(int i) {
		checkRange(i, size);
		E temp = data[i];
		for (int j = i; j < size - 1; j++) // shift elements to fill hole
			data[j] = data[j + 1];
		data[size - 1] = null;// for GC
		size--;
		return temp;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (E e : data) {
			sb.append(e).append(",");
		}
		return sb.toString();
	}

	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}

	private void checkRange(int i, int size) {
		if (i < 0 || i >= size)
			throw new IndexOutOfBoundsException("index " + i + " out of range");
	}

	private void resize(int capacity) {
		E[] temp = (E[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}

	private class ArrayListIterator implements Iterator<E> {

		private int j;
		// checks if remove can be called at this time
		private boolean removable = false;

		@Override
		public boolean hasNext() {
			return j < size;
		}

		@Override
		public E next() {
			if (j == size)
				throw new NoSuchElementException("no next element");
			removable = true; // this can be removed
			return data[j++];
		}

		public void remove() {
			if (!removable)
				throw new NoSuchElementException("nothing to remove");
			ArrayList.this.remove(j - 1);
			j--;
			removable = false; // once removed set to false
		}

	}

	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 26; i++) {
			al.add(i);
		}
		System.out.println(al.toString());
		
		for (Iterator<Integer> iterator = al.iterator(); iterator.hasNext();) {
			Integer next = iterator.next();
			System.out.println(next);
		}
		
		System.out.println(al.remove(20));
		System.out.println(al.get(15));

	}
}
