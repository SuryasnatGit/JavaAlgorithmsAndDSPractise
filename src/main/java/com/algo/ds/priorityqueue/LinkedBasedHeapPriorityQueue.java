package com.algo.ds.priorityqueue;

public class LinkedBasedHeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    private class Node {

        private Entry<K, V> entry;
        private Node leftChild;
        private Node rightChild;

        public Node(K key, V value) {
            entry = new PQEntry(key, value);
            leftChild = null;
            rightChild = null;
        }
    }

    // root node of heap
    private Node root;

    public LinkedBasedHeapPriorityQueue() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
    /**
     * Time complexity - O(1)
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Time complexity - O(log N)
     */
    @Override
    public Entry<K, V> insert(K key, V value) {
        checkKey(key);
        Node temp = new Node(key, value);
        if (root == null)
            root = temp;
        else
            merge(temp, root);
        return temp.entry;
    }

    private Node merge(Node leftHeap, Node rightHeap) {
        // heap with higher priority at root is at left
        if (compare(leftHeap.entry, rightHeap.entry) < 0)
            swap(leftHeap, rightHeap);
        if (leftHeap.leftChild == null) {
            leftHeap.leftChild = rightHeap;
        } else if (leftHeap.rightChild == null) {
            leftHeap.rightChild = rightHeap;
        } else if (compare(leftHeap.leftChild.entry, leftHeap.rightChild.entry) > 0) {
            leftHeap.leftChild = merge(leftHeap.leftChild, rightHeap);
        } else {
            leftHeap.rightChild = merge(leftHeap.rightChild, rightHeap);
        }
        return leftHeap;
    }

    public Entry<K, V> delete() {
        Entry<K, V> entry = root.entry;
        if (root.leftChild == null && root.rightChild == null) {
            root = null;
        } else if (root.leftChild == null) {
            root = root.rightChild;
        } else if (root.rightChild == null) {
            root = root.leftChild;
        } else {
            root = merge(root.leftChild, root.rightChild);
        }
        return entry;
    }

    private void swap(Node leftHeap, Node rightHeap) {
        Node temp = leftHeap;
        leftHeap = rightHeap;
        rightHeap = temp;
    }

    /**
     * Time complexity - O(1)
     */
    @Override
    public Entry<K, V> min() {
        // XXX Auto-generated method stub
        return null;
    }

    /**
     * Time complexity - O(log N)
     */
    @Override
    public Entry<K, V> removeMin() {
        // XXX Auto-generated method stub
        return null;
    }

}
