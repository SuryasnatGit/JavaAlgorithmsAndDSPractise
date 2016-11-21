package com.algo.ds.linkedlist;

public interface Position<E> {
	/**
	 * return element at specified position or throws
	 * {@link IllegalStateException} if position is invalid
	 * 
	 * @return
	 * @throws IllegalStateException
	 */
	E getElement() throws IllegalStateException;
}
