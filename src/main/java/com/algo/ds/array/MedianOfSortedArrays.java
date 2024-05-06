package com.algo.ds.array;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * You may assume nums1 and nums2 cannot be both empty.
 * 
 * Input : A = [1,2,3,4,5,6] B = [2,3,4,5] Output : 3.5 . Explain - The merged array is [1,2,2,3,3,4,4,5,5,6] and the
 * median is (3 + 4) / 2.
 *
 * Input : A = [1,2,3] B = [4,5] Output = 3 . Explain - The merged array is [1,2,3,4,5] with a median of 3.
 * 
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * 
 * Difficulty - Hard
 *
 */
public class MedianOfSortedArrays {

	public static void main(String[] args) {
		MedianOfSortedArrays me = new MedianOfSortedArrays();
		System.out.println("Using findMedian1()");
		System.out.println(me.findMedian1(new int[] { 1 }, new int[] { 2, 3 }));
		System.out.println(me.findMedian1(new int[] { 1, 2 }, new int[] { 3, 4 }));
		System.out.println(me.findMedian1(new int[] { 1, 2 }, new int[] { 3 }));
		System.out.println(me.findMedian1(new int[] { 1, 2 }, new int[] {}));
		System.out.println(me.findMedian1(new int[] { 1, 2, 3, 4, 5, 6 }, new int[] { 2, 3, 4, 5 }));
		System.out.println("Using findMedianSortedArrays()");
		System.out.println(me.findMedian2(new int[] { 1 }, new int[] { 2, 3 }));
		System.out.println(me.findMedian2(new int[] { 1, 2 }, new int[] { 3, 4 }));
		System.out.println(me.findMedian2(new int[] { 1, 2 }, new int[] { 3 }));
		System.out.println(me.findMedian2(new int[] { 1, 2 }, new int[] {}));
		System.out.println(me.findMedian2(new int[] { 1, 2, 3, 4, 5, 6 }, new int[] { 2, 3, 4, 5 }));
	}

	/**
	 * Algorithm process : merge method
	 * 
	 * Let the pointers p1 and pointer p2 point to two arrays respectively, initially pointing to the position 0. We
	 * need to traverse (m + n)/2 + 1 times, each time comparing the elements at two positions. In the first k
	 * comparison, the smaller value is the smallest k + 1 number in the two arrays. If a pointer has reached the end of
	 * the array, then move the other pointer, otherwise move the pointer pointing to a smaller number backward each
	 * time until the middle number is traversed. left In order to combine odd and even situations, and are used in the
	 * code right to save the intermediate value. If it is an odd number, it is returned directly right, and if it is an
	 * even number, it is returned (left + right) / 2.
	 * 
	 * Complexity analysis :
	 * 
	 * Time complexity: O (m + n ) , m and n are the lengths of the two arrays respectively. Double pointers traverse
	 * two arrays, and the number of pointer moves is 0(m+n)level. Space complexity: O ( 1 )
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public double findMedian1(int[] A, int[] B) {
		int p1 = 0, p2 = 0;
		int left = -1, right = -1;
		int m = A.length, n = B.length;
		for (int i = 0; i < (m + n) / 2 + 1; i++) {
			left = right;
			if (p1 >= A.length || p2 < B.length && A[p1] > B[p2]) {
				// move p2 to right
				right = B[p2++];
			} else {
				// move p1 to right
				right = A[p1++];
			}
		}
		return (m + n) % 2 == 1 ? right : (left + right) / 2.0;
	}

	/**
	 * Problem-solving ideas :
	 * 
	 * The required time complexity of the question is O ( log ( m + n ) ) , it is not difficult to think of the
	 * dichotomy. In the double pointer method, we exclude impossible elements one by one. If you make full use of the
	 * orderliness of the array, you can eliminate half and half of them. Specifically, assuming we want to find the
	 * kdecimal point, by dividing it into two, we can eliminate k/2the number in each loop. Algorithm process Create an
	 * auxiliary function getKth. The parameters include the array A, Athe start pointer start1and the end pointer end1.
	 * The corresponding ones are B, start2sum end2, and integer k. The goal is to find the smallest element in
	 * A[start1:end1]the sum .B[start2:end2]k In the main program, look m + nat the parity and call getKththe function.
	 * If it is an odd number, the smallest element of Athe array sum is returned ; if it is an even number, the mean of
	 * the smallest and smallest elements of the array sum is returned.B(m + n) // 2 + 1AB(m + n) // 2(m + n) // 2 + 1
	 * getKth(nums1, start1, end1, nums2, start2, end2, k)How to implement the function: If an array [start:end]is empty
	 * in the range, it means that the array has been eliminated. The smallest kelement must exist in another array.
	 * Just calculate the index position and return it. If kit is 1, it means that the smallest number has been found k,
	 * which is the smaller value in the sum, A[start1]and can be returned directly. Define the pointers and to point to
	 * and respectively , so that the lengths of the sums are respectively . By comparing the sizes of the sums, we can
	 * determine which elements to exclude. If , it means that it is impossible to be the smallest element. We recurse
	 * on and continue feeding , which should be updated to . * On the contrary, it means that it cannot be the smallest
	 * element. We recurse on and continue feeding , which should be updated to .B[start2]ijABA[start1:i]B[start2:j]k //
	 * 2A[i]B[j]A[i] > B[j]B[start2:j]kA[start1:end1]B[j + 1:end2]getKthkk - (j - start2 + 1)A[start1:i]kA[i +
	 * 1:end1]B[start2:end2]getKthkk - (i - start1 + 1) the complexity time complexity: O ( l o g ( m + n ) ) . mand
	 * nare the lengths of the two arrays respectively. The complexity of the dichotomy is O ( l o g ( m + n ) ) 。 Space
	 * complexity: O ( 1 ) 。
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public double findMedian2(int[] A, int[] B) {
		int m = A.length, n = B.length;
		// 如果是奇数
		if ((m + n) % 2 == 1) {
			return getKth(A, 0, A.length - 1, B, 0, B.length - 1, (m + n) / 2 + 1);
		}
		// 如果是偶数
		int left = getKth(A, 0, A.length - 1, B, 0, B.length - 1, (m + n) / 2);
		int right = getKth(A, 0, A.length - 1, B, 0, B.length - 1, (m + n) / 2 + 1);
		return (left + right) / 2.0;
	}

	private int getKth(int[] A, int start1, int end1, int[] B, int start2, int end2, int k) {
		int len1 = end1 - start1 + 1;
		int len2 = end2 - start2 + 1;
		// 让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
		if (len1 > len2) {
			return getKth(B, start2, end2, A, start1, end1, k);
		}
		// A数组排除完毕
		if (len1 == 0) {
			return B[start2 + k - 1];
		}
		// 已经找到第k小的数
		if (k == 1) {
			return Math.min(A[start1], B[start2]);
		}
		// 开始二分
		int i = start1 + Math.min(len1, k / 2) - 1;
		int j = start2 + Math.min(len2, k / 2) - 1;

		if (A[i] > B[j]) {
			return getKth(A, start1, end1, B, j + 1, end2, k - (j - start2 + 1));
		} else {
			return getKth(A, i + 1, end1, B, start2, end2, k - (i - start1 + 1));
		}
	}

}
