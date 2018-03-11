package com.algo.ds.sorting;

/**
 * integer sorting algorithm.
 * 
 * 
 * The lower bound for Comparison based sorting algorithm (Merge Sort, Heap
 * Sort, Quick-Sort .. etc) is Ω(nLogn), i.e., they cannot do better than nLogn.
 * <br/><br/>
 * 
 * Counting sort is a linear time sorting algorithm that sort in O(n+k) time
 * when elements are in range from 1 to k.
 * <br/><br/>
 * 
 * What if the elements are in range from 1 to n2? We can’t use counting sort
 * because counting sort will take O(n2) which is worse than comparison based
 * sorting algorithms. Can we sort such an array in linear time? Radix Sort is
 * the answer. The idea of Radix Sort is to do digit by digit sort starting from
 * least significant digit to most significant digit. Radix sort uses counting
 * sort as a subroutine to sort.
 * <br/><br/>
 * 
 * What is the running time of Radix Sort? Let there be d digits in input
 * integers. Radix Sort takes O(d*(n+b)) time where b is the base for
 * representing numbers, for example, for decimal system, b is 10. What is the
 * value of d? If k is the maximum possible value, then d would be O(logb(k)).
 * So overall time complexity is O((n+b) * logb(k)). Which looks more than the
 * time complexity of comparison based sorting algorithms for a large k. Let us
 * first limit k. Let k <= nc where c is a constant. In that case, the
 * complexity becomes O(nLogb(n)). But it still doesn’t beat comparison based
 * sorting algorithms. What if we make value of b larger?. What should be the
 * value of b to make the time complexity linear? If we set b as n, we get the
 * time complexity as O(n). In other words, we can sort an array of integers
 * with range from 1 to nc if the numbers are represented in base n (or every
 * digit takes log2(n) bits).
 * <br/><br/>
 * 
 * Is Radix Sort preferable to Comparison based sorting algorithms like
 * Quick-Sort? If we have log2n bits for every digit, the running time of Radix
 * appears to be better than Quick Sort for a wide range of input numbers. The
 * constant factors hidden in asymptotic notation are higher for Radix Sort and
 * Quick-Sort uses hardware caches more effectively. Also, Radix sort uses
 * counting sort as a subroutine and counting sort takes extra space to sort
 * numbers.
 * 
 * 
 * 
 * 
 * @author surya
 *
 */
public class RadixSort {

    private void countSort(int arr[],int exp){
        
        int[] count = new int[10];
        int[] output = new int[arr.length];
        
        for(int i=0; i < arr.length; i++){
            count[(arr[i]/exp)%10]++;
        }
        
        for(int i=1; i < count.length; i++){
            count[i] += count[i-1];
        }
        
        for(int i=arr.length-1; i >= 0; i--){
            output[count[(arr[i]/exp)%10]-1] = arr[i];
            count[(arr[i]/exp)%10]--;
        }
        
        for(int i=0; i < arr.length; i++){
            arr[i] = output[i];
        }
    }
    
    private int max(int arr[]){
        int max = arr[0];
        for(int i=1; i < arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        return max;
    }
    
    public void radixSort(int arr[]){
        
        int max = max(arr);
        for(int exp = 1; exp <= max; exp *= 10){
            countSort(arr,exp);
        }
    }
    
    public static void main(String args[]){
        int arr[] = {101,10,11,66,94,26,125};
        RadixSort rs = new RadixSort();
        rs.radixSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }
}
