package com.ctci.sortnsearch;


import java.util.Arrays;


/**
 * Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough buffer at the end to hold B. Write
 * a method to merge B into A in sorted order. Since we know that A has enough buffer at the end, we won't need to
 * allocate additional space. Our logic should involve simply comparing elements of A and B and inserting them in order,
 * until we've exhausted all elements in Aand in B. The only issue with this is that if we insert an element into the
 * front of A, then we'll have to shift the existing elements backwards to make room for it. It's better to insert
 * elements into the back of the array, where there's empty space.
 */
public class SortedMerge {

    /**
     * Merges array
     * 
     * @param a first array
     * @param b second array
     * @param lastA number of "real" elements in a
     * @param lastB number of "real" elements in b
     */
    public static void merge(int[] a, int[] b, int lastA, int lastB) {
        int indexMerged = lastB + lastA - 1; /* Index of last location of merged array */
        int indexA = lastA - 1; /* Index of last element in array b */
        int indexB = lastB - 1; /* Index of last element in array a */

        /* Merge a and b, starting from the last element in each */
        while (indexB >= 0) {
            if (indexA >= 0 && a[indexA] > b[indexB]) { /* end of a is bigger than end of b */
                a[indexMerged] = a[indexA]; // copy element
                indexA--;
            }
            else {
                a[indexMerged] = b[indexB]; // copy element
                indexB--;
            }
            indexMerged--; // move indices
        }
    }

    public static void main(String[] args) {
        int[] a = { 2, 3, 4, 5, 6, 8, 10, 100, 0, 0, 0, 0, 0, 0 };
        int[] b = { 1, 4, 6, 7, 7, 7 };
        merge(a, b, 8, 6);
        System.out.println(Arrays.toString(a));
    }

}
