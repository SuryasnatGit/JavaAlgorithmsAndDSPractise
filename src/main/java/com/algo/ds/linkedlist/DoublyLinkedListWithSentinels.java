package com.algo.ds.linkedlist;

public class DoublyLinkedListWithSentinels<E> {

    private class Node<E> {

        private E data;
        private Node<E> prev;
        private Node<E> next;

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    // sentinel references -- header n trailer
    // actual first node is next of header. actual last node is prev of trailer
    private Node<E> header;
    private Node<E> trailer;
    private int size;

    // for a empty list header will point to trailer and trailer to header
    public DoublyLinkedListWithSentinels() {
        this.header = new Node<E>(null, null, null);
        this.trailer = new Node<E>(null, header, null);
        header.setNext(trailer);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty())
            return null;
        return header.getNext().getData();
    }

    public E last() {
        if (isEmpty())
            return null;
        return trailer.getPrev().getData();
    }

    public void addFirst(E elem) {
        addBetween(elem, header, header.getNext());
    }

    public void addLast(E elem) {
        addBetween(elem, trailer.getPrev(), trailer);
    }

    public E removeFirst() {
        return remove(header.getNext());
    }

    public E removeLast() {
        return remove(trailer.getPrev());
    }

    // E is inserted betwwen before and after nodes
    private void addBetween(E elem, Node<E> before, Node<E> after) {
        Node<E> newElem = new Node<E>(elem, before, after);
        before.setNext(newElem);
        after.setPrev(newElem);
        size++;
    }

    private E remove(Node<E> next) {
        if (isEmpty())
            return null;
        Node<E> before = next.getPrev();
        Node<E> after = next.getNext();
        before.setNext(after);
        after.setPrev(before);
        size--;
        return next.getData();
    }

}
