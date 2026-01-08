package com.algo.ds.priorityqueue;

import java.util.Arrays;

public class Heapify {

    public static void main(String[] args) {
        Heapify heapify = new Heapify();
        heapify.minHeap(new int[]{3, 4, 1, 2, 5});
        heapify.maxHeap(new int[]{3, 4, 1, 2, 5});
    }

    public void minHeap(int[] array) {
        for (int i = 0; i < array.length; i++) {
            shiftUp(array, i);
        }
        System.out.println(Arrays.toString(array));
    }

    // T - O(n log n)
    private void shiftUp(int[] array, int index) {
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (array[index] > array[parent]) {
                break;
            }
            swap(array, index, parent);
            index = parent;
        }
    }

    private void swap(int[] array, int index, int parent) {
        int temp = array[index];
        array[index] = array[parent];
        array[parent] = temp;
    }

    public void maxHeap(int[] array) {
        for (int i = array.length / 2; i >= 0; i--) {
            shiftDown(array, i);
        }
        System.out.println(Arrays.toString(array));
    }

    // T - O(n)
    private void shiftDown(int[] array, int index) {
        while (index < array.length) {
            int smallest = index;
            if (index * 2 + 1 < array.length && array[index * 2 + 1] < array[smallest]) { // for LC
                smallest = index * 2 + 1;
            }
            if (index * 2 + 2 < array.length && array[index * 2 + 2] < array[smallest]) { // for RC
                smallest = index * 2 + 2;
            }
            if (smallest == index) {
                break;
            }
            swap(array, index, smallest);
            index = smallest;
        }
    }
}
