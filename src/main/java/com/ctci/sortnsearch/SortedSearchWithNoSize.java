package com.ctci.sortnsearch;


/**
 * CTCI - 10.4
 * 
 * Sorted Search, No Size: You are given an array-like data structure Listy
 * which lacks a size method. It does, however, have an elementAt (i) method
 * that returns the element at index i in 0(1) time. If i is beyond the bounds
 * of the data structure, it returns - 1. (For this reason, the data structure
 * only supports positive integers.) Given a Listy which contains sorted,
 * positive integers, find the index at which an element x occurs. If x occurs
 * multiple times, you may return any index.
 * 
 * Complexity - O(log n)
 * 
 * @author ctsuser1
 */
public class SortedSearchWithNoSize {

    // assume this method returns the element at index i
    class Listy {
        public int elementAt(int i) {
            return 0;
        }
    }

    public int search(Listy list, int value) {
        int index = 1;
        while (list.elementAt(index) != -1 && list.elementAt(index) < value)
            index *= 2;

        return binarySearch(list, value, index / 2, index);
    }

    private int binarySearch(Listy list, int value, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            int middle = list.elementAt(mid);
            if (middle > value || middle == -1) // if out of bounds
                end = mid - 1;
            else if (middle < value)
                start = mid + 1;
            else
                return mid;
        }
        return -1;
    }
}
