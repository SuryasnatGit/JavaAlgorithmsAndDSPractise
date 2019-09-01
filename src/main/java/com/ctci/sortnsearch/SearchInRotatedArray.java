package com.ctci.sortnsearch;


/**
 * CTCI - 10.3
 * 
 * Search in Rotated Array: Given a sorted array of n integers that has been
 * rotated an unknown number of times, write code to find an element in the
 * array. You may assume that the array was originally sorted in increasing
 * order.
 * 
 * EXAMPLE : Input: find 5 in {15, 16, 19, 20, 25, 1, 3 ,4 ,5 ,7 ,1 0 , 14}
 * 
 * Output 8 (the index of 5 in the array).
 * 
 * Complexity: O(log n) if all elements are unique. But if many duplicates are
 * there, then its O(n).
 * 
 * @author ctsuser1
 */
public class SearchInRotatedArray {

    public int search(int[] arr, int left, int right, int x) {
        int mid = (left + right) / 2;

        if (x == arr[mid]) // found element
            return mid;
        if (right < left)
            return -1;

        if (arr[left] < arr[mid]) { // left side normally ordered
            if (x >= arr[left] && x < arr[mid]) {
                return search(arr, left, mid - 1, x);// search left
            }
            else {
                return search(arr, mid + 1, right, x);// search right
            }
        }
        else if (arr[mid] < arr[left]) { // right side normally ordered
            if (x > arr[mid] && x <= arr[right]) {
                return search(arr, mid + 1, right, x);// search right
            }
            else {
                return search(arr, left, mid - 1, x);// search left
            }
        }
        else if (arr[left] == arr[mid]) { // left or right half is all repeats
            if (arr[mid] != arr[right]) { // right is different , search it
                return search(arr, mid + 1, right, x);
            }
            else { // search both halves
                int res = search(arr, left, mid - 1, x); // search left
                if (res == -1) {
                    return search(arr, mid + 1, right, x);// search right
                }
                else
                    return res;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedArray s = new SearchInRotatedArray();
        int[] arr = new int[] { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
        System.out.println(s.search(arr, 0, arr.length - 1, 200));
    }

}
