package com.algo.ds.array;

import java.util.Arrays;


public class ThreeSum {

	/**
	 * To demonstrate the brute way of counting the number of triplets of
	 * doing a 3 sum computation for a N integer array, so that for (i,j,k) where 
	 * i < j < k , a[i] + a[j] + a[k] = 0
	 * @author Suryasnat
	 *
	 */
	public void threeSumSlow(int[] intArr){
		int N = intArr.length;
		int count = 0;
		for(int i=0;i<N;i++)
			for(int j=i+1;j<N;j++)
				for(int k=j+1;k<N;k++)
					if(intArr[i] + intArr[j] + intArr[k] == 0)
						count++;
		
		System.out.println(count);	
	}
	
	/**
	 * complexity is N^2 log N (linearithimic)
	 * @param arr
	 * @throws Exception
	 */
	public void threeSumFast(int[] arr) throws Exception{
		int N = arr.length;
		int count = 0;
		java.util.Arrays.sort(arr); // Done using merge sort. Complexity - N log N
		if(duplicatePresent(arr))
			throw new Exception("");
		
		for(int i=0;i<N;i++){
			for(int j=i+1;j<N;j++){
				int k = Arrays.binarySearch(arr, -(arr[i]+arr[j])); // Complexity for N binary search is log N
				if(k > j)
					count++;
			}
		}
		
		System.out.println(count);
	}
	
	private boolean duplicatePresent(int[] arr){
		for(int i=1;i<arr.length;i++){
			if(arr[i] == arr[i-1])
				return true;
		}
		return false;
	}

}
