package com.algo.ds.array;

/**
 * http://www.geeksforgeeks.org/increasing-subsequence-of-length-three-with-maximum-product/.
 * 
 * Examples:
 * 
 * 
 * Input: arr[] = {6, 7, 8, 1, 2, 3, 9, 10} Output: 8 9 10
 * 
 * Input: arr[] = {1, 5, 10, 8, 9} Output: 5 8 9
 * 
 * 
 * Keep two arrays which keeps max from current position to right side. Other array keeps max on
 * left side which is smaller than current element. Once you have these two arrays from 2nd to 2nd
 * last position keep multiplying elements at 3 arrays index position to get max product. Test cases
 * Negative numbers 0 in the input.
 * 
 * We can do this in O(nLogn) time. For simplicity, let us first create two arrays LSL[] and LGR[]
 * of size n each where n is number of elements in input array arr[]. The main task is to fill two
 * arrays LSL[] and LGR[]. Once we have these two arrays filled, all we need to find maximum product
 * LSL[i]*arr[i]*LGR[i] where 0 < i < n-1 (Note that LSL[i] doesn't exist for i = 0 and LGR[i]
 * doesn't exist for i = n-1). We can fill LSL[] in O(nLogn) time. The idea is to use a Balanced
 * Binary Search Tree like AVL. We start with empty AVL tree, insert the leftmost element in it.
 * Then we traverse the input array starting from the second element to second last element. For
 * every element currently being traversed, we find the floor of it in AVL tree. If floor exists, we
 * store the floor in LSL[], otherwise we store NIL. After storing the floor, we insert the current
 * element in the AVL tree.
 * 
 * We can fill LGR[] in O(n) time. The idea is similar to this post. We traverse from right side and
 * keep track of the largest element. If the largest element is greater than current element, we
 * store it in LGR[], otherwise we store NIL.
 * 
 * Finally, we run a O(n) loop and return maximum of LSL[i]*arr[i]*LGR[i]
 * 
 * Overall complexity of this approach is O(nLogn) + O(n) + O(n) which is O(nLogn). Auxiliary space
 * required is O(n). Note that we can avoid space required for LSL, we can find and use LSL values
 * in final loop.
 * 
 * 
 */
public class IncreasingSubsequnceOfLength3WithMaxProduct {

	public int maxProduct(int arr[]) {
		int RGN[] = new int[arr.length]; // right greater number
		int LGN[] = new int[arr.length]; // left greater number
		RGN[arr.length - 1] = arr[arr.length - 1];
		int max = arr[arr.length - 1];
		for (int i = arr.length - 2; i >= 0; i--) {
			if (max < arr[i]) {
				max = arr[i];
			}
			if (max > arr[i]) {
				RGN[i] = max;
			} else {
				RGN[i] = 0;
			}
		}
		LGN[0] = 0;
		// This can be implemented using an AVL tree instead of this way which will
		// make it O(nLogn) operation instead of O(n2).
		for (int i = 1; i < arr.length; i++) {
			getLGN(arr, i, LGN);
		}
		int maxProduct = 0;
		for (int i = 1; i < arr.length - 1; i++) {
			int product = arr[i] * LGN[i] * RGN[i];
			if (maxProduct < product) {
				maxProduct = product;
			}
		}
		return maxProduct;
	}

	private void getLGN(int arr[], int pos, int LGN[]) {
		int max = 0;
		int i = 0;
		while (i < pos) {
			if (arr[i] < arr[pos]) {
				if (arr[i] > max) {
					max = arr[i];
				}
			}
			i++;
		}
		LGN[pos] = max;
	}

	public static void main(String args[]) {
		int arr[] = { 6, 7, 8, 1, 2, 3, 9, 10 };
		IncreasingSubsequnceOfLength3WithMaxProduct iss = new IncreasingSubsequnceOfLength3WithMaxProduct();
		System.out.println(iss.maxProduct(arr));
		int arr1[] = { 1, 5, 10, 8, 9 };
		System.out.println(iss.maxProduct(arr1));
	}
}
