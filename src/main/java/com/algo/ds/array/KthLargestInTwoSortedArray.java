package com.algo.ds.array;

/**
 * http://stackoverflow.com/questions/4686823/given-2-sorted-arrays-of-integers-find-the-nth-largest-number-in-sublinear-time
 * http://articles.leetcode.com/2011/01/find-k-th-smallest-element-in-union-of.html.
 * 
 * Given two sorted arrays of size m and n respectively, you are tasked with finding the element
 * that would be at the k’th position of the final sorted array.
 * 
 * Examples:
 * 
 * Input : Array 1 - 2 3 6 7 9 Array 2 - 1 4 8 10 k = 5 Output : 6 Explanation: The final sorted
 * array would be - 1, 2, 3, 4, 6, 7, 8, 9, 10 The 5th element of this array is 6. Input : Array 1 -
 * 100 112 256 349 770 Array 2 - 72 86 113 119 265 445 892 k = 7 Output : 256 Explanation: Final
 * sorted array is - 72, 86, 100, 112, 113, 119, 256, 265, 349, 445, 770, 892 7th element of this
 * array is 256.
 * 
 */
public class KthLargestInTwoSortedArray {

	/**
	 * Approach 1 - SImple. merge 2 sorted arrays and find the kth element from the merged array. time
	 * complexity - O(m + n). Space complexity - O(m + n)
	 * 
	 * @param arr1
	 * @param arr2
	 * @param k
	 * @return
	 */
	public int findKthElement(int[] arr1, int[] arr2, int k) {
		int m = 0, n = 0, c = 0;
		// temp array
		int[] result = new int[arr1.length + arr2.length];
		while (m < arr1.length && n < arr2.length) {
			if (arr1[m] < arr2[n])
				result[c++] = arr1[m++];
			else
				result[c++] = arr2[n++];
		}
		// leftovers from arr1 and arr2
		if (m < arr1.length)
			result[c++] = arr1[m++];
		if (n < arr2.length)
			result[c++] = arr2[n++];

		return result[k - 1];
	}
    
	/**
	 * not working revisit later.
	 * 
	 * @param arr1
	 * @param arr2
	 * @param low1
	 * @param high1
	 * @param low2
	 * @param high2
	 * @param k
	 * @return
	 */
    public int kthLargest1(int arr1[],int arr2[],int low1,int high1,int low2,int high2,int k){
		int len1 = high1 - low1 + 1;
		int len2 = high2 - low2 + 1;
        
        if(len1 == 0){
            return arr2[low2+k];
        }
        if(len2 ==0){
            return arr1[low1+k];
        }
        if(k == 0){
            return Math.min(arr1[low1], arr2[low2]);
        }
        
        int mid1 = len1*k/(len1 + len2);
        int mid2 = k - mid1 - 1;
        
		mid1 = low1 + mid1;
        mid2 = low2 + mid2;
        
        if(arr1[mid1] > arr2[mid2]){
            k = k - mid2 + low2 -1;
            high1 = mid1;
            low2 = mid2 + 1;
        }else{
            k = k - mid1 + low1 -1;
            high2 = mid2;
			low1 = mid1 + 1;
        }
        return kthLargest(arr1, arr2, low1, high1, low2, high2, k);
    }

    
	/**
	 * Approach 2 - Divide and concqur. Using a modified binary search.
	 * 
	 * time complexity - O(log m + log n)
	 * 
	 * @param input1
	 * @param input2
	 * @param k
	 * @return
	 */
    public int kthLargest(int input1[],int input2[],int k){
        return kthLargest(input1, input2, 0, input1.length-1, 0, input2.length-1, k);
    }
    
    private int kthLargest(int input1[],int input2[],int l1, int h1, int l2,int h2,int k){
        if(l1 > h1){
            return input2[k-1];
        }
        if(l2 > h2){
            return input1[k-1];
        }
        
		if ((h1 - l1 + 1) + (h2 - l2 + 1) == k) { // if k is at the end
            return Math.max(input1[h1], input2[h2]);
        }

		if (k == 1) { // if k is at the beginning
            return Math.min(input1[l1],input2[l2]);
        }

        //handle the situation where only one element is left
        //in either of array.
		// e.g k =2 and arr1 = 3 arr2 = 5,9,11
		// we try to find if 8 is before 9 between 1 and 9
        //or before 1. In all these cases k will be either
        //1 8 or 9 
        if(l2 == h2 || l1 == h1){
            if(l2 == h2){
                if(input1[l1+k-1] < input2[l2]){
                    return input1[l1+k-1];
                }else if(input1[l1+k-2] > input2[l2]){
                    return input1[l1+k-2];
                }else{
                    return input2[l2];
                }
            }
            if(l1 == h1){
                if(input2[l2+k-1] < input1[l1]){
                    return input2[l2+k-1];
                }else if(input2[l2+k-2] > input1[l1]){
                    return input2[l2+k-2];
                }else{
                    return input1[l1];
                }
            }
        }
        
    
        int m1 = (h1 + l1)/2;
        int m2 = (h2 + l2)/2;
        
        int diff1 = m1 - l1+1;
        int diff2 = m2 - l2+1;
        if(diff1 + diff2 >= k){
            if(input1[m1] < input2[m2]){
                return kthLargest(input1, input2, l1, h1, l2, m2, k);
            }else{
                return kthLargest(input1, input2, l1, m1, l2, h2, k);
            }
        }else{
            if(input1[m1] < input2[m2]){
                return kthLargest(input1, input2,m1+1, h1, l2, h2, k - diff1);
            }else{
				return kthLargest(input1, input2, l1, h1, m2 + 1, h2, k - diff2);
            }
        }
    }
    
    public static void main(String args[]){
        KthLargestInTwoSortedArray kis = new KthLargestInTwoSortedArray();
        int input1[] = {1,4,7,11,17,21};
		int input2[] = { -4, -1, 3, 5, 6, 28, 35, 41, 56, 70 };
		// for (int i = 0; i < input1.length + input2.length; i++) {
		// System.out.println("****");
		// System.out.println(Arrays.toString(input1));
		// System.out.println(Arrays.toString(input2));
		System.out.println(kis.kthLargest(input1, input2, 6));
		// System.out.println("****");
		// }
		System.out.println(kis.findKthElement(input1, input2, 6));
		System.out.println(kis.kthLargest1(input1, input2, 0, input1.length - 1, 0, input2.length - 1, 6));
    }
}
