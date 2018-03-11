package com.algo.ds.sorting;

/**
 * integer sorting algorithm. <br/>
 * It operates by counting the number of objects that have each distinct key
 * value, and using arithmetic on those counts to determine the positions of
 * each key value in the output sequence. Its running time is linear in the
 * number of items and the difference between the maximum and minimum key
 * values, so it is only suitable for direct use in situations where the
 * variation in keys is not significantly greater than the number of items.
 * However, it is often used as a subroutine in another sorting algorithm, radix
 * sort, that can handle larger keys more efficiently.<br/>
 * Because counting sort uses key values as indexes into an array, it is not a
 * comparison sort, and the Ω(n log n) lower bound for comparison sorting does
 * not apply to it.<br/>
 * 
 * Time Complexity: O(n+k) where n is the number of elements in input array and
 * k is the range of input. Auxiliary Space: O(n+k)
 * 
 * 
 * 
 * @author surya
 *
 */
public class CountingSort {

    private static int TOTAL = 10;

    public void sort(int arr[]) {

        int count[] = new int[TOTAL];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        int c = 0;
        for (int i = 0; i < TOTAL; i++) {
            while (count[i] > 0) {
                arr[c++] = i;
                count[i]--;
            }
        }
    }

    public void sort1(int arr[]) {

        int count[] = new int[TOTAL];
        // output array to hold element at specific place
        int output[] = new int[arr.length];
        // make the count array
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        // modified count array so that each element stores sum of previous counts
        for(int i=1; i < TOTAL; i++){
            count[i] += count[i-1];
        }
        // build the output array
        for(int i=0; i <arr.length; i++){
            output[count[arr[i]]-1] = arr[i];
            count[arr[i]]--;
        }
        // copy the output array to arr, so arr contains sorted elements
        for(int i=0; i < arr.length; i++){
            arr[i] = output[i];
        }
    }

    public static void main(String args[]) {
        int arr[] = { 6, 1, 6, 7, 3, 1 };
        CountingSort cs = new CountingSort();
        cs.sort1(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
