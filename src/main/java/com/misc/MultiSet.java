package com.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* A class representing a Mutable Multiset */
class MultiSet<E> {

	/* List to store distinct values */
	private List<E> values;

	/* List to store counts of distinct values */
	private List<Integer> frequency;

	private final String ERROR_MSG = "Count cannot be negative: ";

	/* Constructor */
	public MultiSet() {
		values = new ArrayList<>();
		frequency = new ArrayList<>();
	}

	/**
	 * Adds an element to this multiset specified number of times
	 *
	 * @param element
	 *            The element to be added
	 * @param count
	 *            The number of times
	 * @return The previous count of the element
	 */
	public int add(E element, int count) {
		if (count < 0) {
			throw new IllegalArgumentException(ERROR_MSG + count);
		}

		int index = values.indexOf(element);
		int prevCount = 0;

		if (index != -1) {
			prevCount = frequency.get(index);
			frequency.set(index, prevCount + count);
		} else if (count != 0) {
			values.add(element);
			frequency.add(count);
		}

		return prevCount;
	}

	/**
	 * Adds specified element to this multiset
	 *
	 * @param element
	 *            The element to be added
	 * @return true always
	 */
	public boolean add(E element) {
		return add(element, 1) >= 0;
	}

	/**
	 * Adds all elements in the specified collection to this multiset
	 *
	 * @param c
	 *            Collection containing elements to be added
	 * @return true if all elements are added to this multiset
	 */
	boolean addAll(Collection<? extends E> c) {
		for (E element : c)
			add(element, 1);

		return true;
	}

	/**
	 * Adds all elements in the specified array to this multiset
	 *
	 * @param arr
	 *            An array containing elements to be added
	 */
	public void addAll(E... arr) {

		for (E element : arr)
			add(element, 1);
	}

	/**
	 * Performs the given action for each element of the Iterable, including duplicates
	 *
	 * @param action
	 *            The action to be performed for each element
	 */
	public void forEach(Consumer<? super E> action) {
		List<E> all = new ArrayList<>();

		for (int i = 0; i < values.size(); i++)
			for (int j = 0; j < frequency.get(i); j++)
				all.add(values.get(i));

		all.forEach(action);
	}

	/**
	 * Removes a single occurrence of specified element from this multiset
	 *
	 * @param element
	 *            The element to removed
	 * @return true if an occurrence was found and removed
	 */
	public boolean remove(Object element) {
		return remove(element, 1) > 0;
	}

	/**
	 * Removes a specified number of occurrences of the specified element from this multiset
	 *
	 * @param element
	 *            The element to removed
	 * @param count
	 *            The number of occurrences to be removed
	 * @return The previous count
	 */
	public int remove(Object element, int count) {
		if (count < 0) {
			throw new IllegalArgumentException(ERROR_MSG + count);
		}

		int index = values.indexOf(element);
		if (index == -1)
			return 0;

		int prevCount = frequency.get(index);

		if (prevCount > count) {
			frequency.set(index, prevCount - count);
		} else {
			values.remove(index);
			frequency.remove(index);
		}

		return prevCount;
	}

	/**
	 * Check if this multiset contains at least one occurrence of the specified element
	 *
	 * @param element
	 *            The element to be checked
	 * @return true if this multiset contains at least one occurrence of the element
	 */
	public boolean contains(Object element) {
		return values.contains(element);
	}

	/**
	 * Check if this multiset contains at least one occurrence of each element in the specified collection
	 *
	 * @param c
	 *            The collection of elements to be checked
	 * @return true if this multiset contains at least one occurrence of each element
	 */
	public boolean containsAll(Collection<?> c) {
		return values.containsAll(c);
	}

	/**
	 * Update the frequency of an element to the specified count or add element to this multiset if not present
	 *
	 * @param element
	 *            The element to be updated
	 * @param count
	 *            The new count
	 * @return The previous count
	 */
	public int setCount(E element, int count) {
		if (count < 0) {
			throw new IllegalArgumentException(ERROR_MSG + count);
		}

		if (count == 0)
			remove(element);

		int index = values.indexOf(element);
		if (index == -1)
			return add(element, count);

		int prevCount = frequency.get(index);
		frequency.set(index, count);

		return prevCount;
	}

	/**
	 * Find the frequency of an element in this multiset
	 *
	 * @param element
	 *            The element to be counted
	 * @return The frequency of the element
	 */
	public int count(Object element) {
		int index = values.indexOf(element);

		return (index == -1) ? 0 : frequency.get(index);
	}

	/**
	 * @return A view of the set of distinct elements in this multiset
	 */
	public Set<E> elementSet() {
		return values.stream().collect(Collectors.toSet());
	}

	/**
	 * @return true if this multiset is empty
	 */
	public boolean isEmpty() {
		return values.size() == 0;
	}

	/**
	 * @return Total number of elements in this multiset, including duplicates
	 */
	public int size() {
		int size = 0;
		for (Integer i : frequency) {
			size += i;
		}
		return size;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < values.size(); i++) {
			sb.append(values.get(i));

			if (frequency.get(i) > 1)
				sb.append(" x ").append(frequency.get(i));

			if (i != values.size() - 1)
				sb.append(", ");
		}

		return sb.append("]").toString();
	}

	// Multiset Implementation in Java
	public static void main(String[] args) {
		MultiSet<String> multiset = new MultiSet();

		multiset.add("USA");
		multiset.add("Japan", 2);
		multiset.addAll("India", "China");
		multiset.addAll(Arrays.asList("USA", "India", "China", "Japan"));

		System.out.println(multiset);

		multiset.remove("China");
		multiset.remove("Japan", 2);

		System.out.println(multiset);

		multiset.setCount("USA", 4);
		multiset.setCount("Japan", 5);
		multiset.setCount("Mexico", 3);
		multiset.setCount("China", 0);

		System.out.println(multiset);
	}
}
