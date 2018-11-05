

package com.ctci.recursendp;


/**
 * Magic Index: A magic index in an array A[1. .. n-1] is defined to be an index such that A[ i] i. Given a sorted array
 * of distinct integers, write a method to find a magic index, if one exists, in array A. FOLLOW UP What if the values
 * are not distinct?
 * 
 * @author ctsuser1
 */
public class MagicIndex {

    public int magicFast(int[] arr) {
        return magicFast(arr, 0, arr.length - 1);
    }

    private int magicFast(int[] arr, int start, int end) {
        if (start > end)
            return -1;

        int mid = (start + end) / 2;
        if (arr[mid] == mid)
            return mid;
        else if (arr[mid] > mid)
            return magicFast(arr, start, mid - 1); // search left side
        else
            return magicFast(arr, mid + 1, end); // search right side
    }


    /**
     * if elements are not distinct then the above algo will fail. When we see that A[mid] < mid, we cannot conclude which
     * side the magic index is on. It could be on the right side, as before. Or, it could be on the left side. The general
     * pattern is that we compare midIndex and midValue for equality first. Then, if they are not equal, we recursively
     * search the left and right sides as follows: Left side: search indices start through Math.min(midIndex-1,midValue).•
     * Right side: search indices Math.max(midIndex+1,midValue) through end.
     * 
     * @param arr
     * @return
     */
    public int magicFast_nodupes(int[] arr) {
        return magicFast_nodupes(arr, 0, arr.length - 1);
    }

    private int magicFast_nodupes(int[] arr, int start, int end) {
        if (start > end)
            return -1;

        int midIndex = (start + end) / 2;
        int midValue = arr[midIndex];
        if (midIndex == midValue)
            return midIndex;

        // search left
        int leftIndex = Math.min(midIndex - 1, midValue);
        int left = magicFast_nodupes(arr, start, leftIndex);
        if (left >= 0)
            return left;

        // search right
        int rightIndex = Math.max(midIndex + 1, midValue);
        int right = magicFast_nodupes(arr, rightIndex, end);
        return right;
    }


}
