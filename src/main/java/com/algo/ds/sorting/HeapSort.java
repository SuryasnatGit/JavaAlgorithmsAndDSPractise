package com.algo.ds.sorting;

/**
 * * Heap Sort: Given an array, sort it using heap sort.
 * 
 * Solution : First convert the original array to create the heap out of the
 * array Then move the max element to last position and do heapify to recreate
 * the heap with rest of the array element. Repeat this process. If the heap
 * used was a min-heap, the resulting list will be in ascending order, and a
 * max-heap will give them in descending order.
 * 
 * Time complexity O(nlogn).
 * 
 * Unfortunately heapsort is not stable so sorting a list that is already sorted
 * could quite possibly end up in a different order.
 * 
 * Test cases Null array 1 element array 2 element array sorted array reverse
 * sorted array
 * 
 * 
 */
public class HeapSort {

    public void sort(int arr[]){
        for(int i=1; i < arr.length; i++){
            buildMaxHeap(arr, i);
        }
        
        for(int i = arr.length-1; i > 0 ; i--){
            swap(arr, 0, i);
            heapify(arr, i-1);
        }
    }
    
	/**
	 * complexity - O(log n) called n-1 times
	 * 
	 * @param arr
	 * @param end
	 */
    private void heapify(int arr[], int end){
        int i = 0;
        int leftIndex;
        int rightIndex;
        while(i <= end){
            leftIndex = 2*i + 1;
            if(leftIndex > end){
                break;
            }
            rightIndex = 2*i + 2;
            if(rightIndex > end){
                rightIndex = leftIndex;
            }
            if(arr[i]  >= Math.max(arr[leftIndex], arr[rightIndex])){
                break;
            }
            if(arr[leftIndex] >= arr[rightIndex]){
                swap(arr, i, leftIndex);
                i = leftIndex;
            }else{
                swap(arr, i, rightIndex);
                i = rightIndex;
            }
        }
    }
    
    private void swap(int arr[], int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
    
	/**
	 * complexity - O(n)
	 * 
	 * @param arr
	 * @param end
	 */
    private void buildMaxHeap(int arr[], int end){
        int i = end;
        while(i > 0){
            if(arr[i] > arr[(i-1)/2]){
                swap(arr, i, (i-1)/2);
                i = (i - 1)/2;
            }else{
                break;
            }
        }
    }
    
    public static void main(String args[]){
        HeapSort hs = new HeapSort();
        int arr[] = {-1,5,8,2,-6,-8,11,5};
        hs.sort(arr);
        for(int a : arr){
            System.out.println(a);
        }
    }
}
