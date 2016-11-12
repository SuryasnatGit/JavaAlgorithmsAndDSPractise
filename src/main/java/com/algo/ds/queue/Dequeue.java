package com.algo.ds.queue;

/**
 * double ended queue where we can insert and remove elements from both front and back of the dequeue
 * 
 */
public interface Dequeue<E> {

    /**
     * Adds element to front of the dequeue
     * 
     * @param element
     * @throws Exception
     */
    public void insertFront(E element) throws Exception;

    /**
     * Adds element to back of the dequeue
     * 
     * @param element
     * @throws Exception
     */
    public void insertBack(E element) throws Exception;

    /**
     * Returns ond removes element from front of the dequeue. returns null if dequeue is empty
     * 
     * @return
     * @throws Exception
     */
    public E removeFront() throws Exception;

    /**
     * Returns ond removes element from back of the dequeue. returns null if dequeue is empty
     * 
     * @return
     * @throws Exception
     */
    public E removeBack() throws Exception;

    /**
     * Returns but does not remove element from front of the dequeue. returns null if dequeue is empty
     * 
     * @return
     * @throws Exception
     */
    public E front() throws Exception;

    /**
     * Returns but does not remove element from back of the dequeue. returns null if dequeue is empty
     * 
     * @return
     * @throws Exception
     */
    public E back() throws Exception;

    public int size();

    public boolean isEmpty();
}
