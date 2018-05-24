package com.algo.ds.array;

/**
 * http://www.geeksforgeeks.org/maximum-contiguous-circular-sum/. The maximum subarray problem is
 * the task of finding the contiguous subarray within a one-dimensional array of numbers which has
 * the largest sum.
 * 
 * Example: int [] A = {−2, 1, −3, 4, −1, 2, 1, −5, 4}; Output: contiguous subarray with the largest
 * sum is 4, −1, 2, 1, with sum 6.
 * 
 * Test cases:<br/>
 * All negative<br/>
 * All positives <br/>
 * all 0s
 */

class Triplet{
    int start;
    int end;
    int sum;
    @Override
    public String toString() {
        return "Triplet [start=" + start + ", end=" + end + ", sum=" + sum
                + "]";
    }
}

public class KadaneWrapArray {

	/**
	 * complexity - O(n^3) in worst case.
	 * 
	 * @param arr
	 * @return
	 */
	public int naiveSoln(int[] arr) {
		int maxSum = 0;
		// starting point
		for (int i = 0; i < arr.length; i++) {
			// ending point
			for (int j = i; j < arr.length; j++) {
				// subarray between current start and end points
				int sum = 0;
				for (int c = i; c <= j; c++) {
					sum += arr[c];
				}
				if (sum > maxSum)
					maxSum = sum;
			}
		}
		return maxSum;
	}

	/**
	 * complexity - O(n).
	 * 
	 * start: 
	 * 	max_so_far = 0 
	 * 	max_ending_here = 0
	 * 
	 * loop i= 0 to n 
	 * 	(i) max_ending_here = max_ending_here + a[i] 
	 * 	(ii) if(max_ending_here < 0) max_ending_here = 0 
	 * 	(iii) if(max_so_far < max_ending_here) max_so_far = max_ending_here return max_so_far
	 * 
	 * @param arr
	 * @return
	 */
    public Triplet kadaneWrap(int arr[]){
        Triplet straightKadane = kadane(arr);
        int sum =0;
        for(int i=0; i < arr.length; i++){
            sum += arr[i];
            arr[i] = -arr[i];
        }
        Triplet wrappedNegKadane = kadane(arr);
		for (int i = 0; i < arr.length; i++) {
            arr[i] = -arr[i];
        }
        if(straightKadane.sum < sum + wrappedNegKadane.sum){
            straightKadane.sum = wrappedNegKadane.sum + sum;
            straightKadane.start = wrappedNegKadane.end+1;
            straightKadane.end = wrappedNegKadane.start-1;
        }
        return straightKadane;
    }
    
    /**
     * This method assumes there is at least one positive number in the array.
     * Otherwise it will break
     * @param arr
     * @return
     */
    public Triplet kadane(int arr[]){
        int sum =0;
        int cStart = 0;
        int mStart = 0;
        int end = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i=0; i < arr.length; i++){
            sum += arr[i];
            if(sum <= 0){
                sum = 0;
                cStart = i+1;
            }else{
                if(sum > maxSum){
                    maxSum = sum;
                    mStart = cStart;
                    end = i;
                }
            }
        }
        Triplet p = new Triplet();
        p.sum = maxSum;
        p.start = mStart;
        p.end = end;
        return p;
    }
    
	// Kadane algorithm
	public int kandane(int[] arrA) {
		int max_end_here = 0;
		int max_so_far = 0;
		for (int i = 0; i < arrA.length; i++) {
			max_end_here += arrA[i];
			if (max_end_here < 0) {
				max_end_here = 0;
			}
			if (max_so_far < max_end_here) {
				max_so_far = max_end_here;
			}
		}
		return max_so_far;
	}

	// Below modification will allow the program to work even if all the
	// elements in the array are negative
	/**
	 * start: 
	 * 	max_so_far = a[0] 
	 * 	max_ending_here = a[0]
	 * 
	 * loop i= 1 to n 
	 * 	(i) max_end_here = Max(arrA[i], max_end_here+a[i]); 
	 * 	(ii) max_so_far = Max(max_so_far,max_end_here);
	 * 
	 * return max_so_far
	 * 
	 * @param arrA
	 * @return
	 */
	public int KandaneModify(int[] arrA) {
		int max_end_here = arrA[0];
		int max_so_far = arrA[0];
		for (int i = 1; i < arrA.length; i++) {
			max_end_here = Math.max(arrA[i], max_end_here + arrA[i]);
			max_so_far = Math.max(max_so_far, max_end_here);
		}
		return max_so_far;
	}

    public static void main(String args[]){
        KadaneWrapArray kwa = new KadaneWrapArray();
        int input[] = {12, -2, -6, 5, 9, -7, 3};
        int input1[] = {8, -8, 9, -9, 10, -11, 12};
		int[] A = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(kwa.kadaneWrap(A));
        System.out.println(kwa.kadaneWrap(input1));
		System.out.println(kwa.naiveSoln(A));
		System.out.println(kwa.kandane(A));
    }
}
