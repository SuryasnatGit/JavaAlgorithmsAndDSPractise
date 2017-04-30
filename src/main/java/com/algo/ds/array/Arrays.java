package com.algo.ds.array;

public class Arrays {

	public static void main(String[] args) {

		Arrays arrays = new Arrays();
		String s = "the dog is black";
		System.out.println(s);
		System.out.println(arrays.reverseWordsinString1(s.toCharArray()));
		System.out.println(arrays.reverseWordsInString2(s));
		/*
		 * int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		 * 
		 * arrays.reverse(a); arrays.maxArray(a);
		 * 
		 * int[] b = { 3, 4, 1, 2, 5, 6, 6, 9 }; System.out.println(arrays.unique2(b));
		 */

		// Scanner sc = new Scanner(System.in);
		// int n = sc.nextInt();
		// int[] intarr = new int[n];
		// for (int i = 0; i < n; i++) {
		// intarr[i] = sc.nextInt();
		// }
		// for (int j = n - 1; j >= 0; j--) {
		// System.out.print(intarr[j] + " ");
		// }

	}

	public void reverse(int[] a) {
		int l = a.length;
		// reverse elements within array.
		for (int i = 0; i < l / 2; i++) {
			int temp = a[i];
			a[i] = a[l - 1 - i];
			a[l - 1 - i] = temp;
		}

		// display
		for (int i = 0; i < l; i++) {
			System.out.print(a[i] + "\n");
		}
	}

	/**
	 * runs in O(n) time.
	 * 
	 * @param arr
	 */
	public void maxArray(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max)
				max = arr[i];
		}
		System.out.println(max);
	}

	/**
	 * 
	 * 2 loops. run in O(n^2)
	 */
	public boolean unique1(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] == arr[j])
					return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * final time taken is O(n log n) + O(n) ==> O(n log n)
	 */
	public boolean unique2(int[] arr) {
		int[] tempArr = java.util.Arrays.copyOf(arr, arr.length);
		// first sort. best is O(n log n) time
		java.util.Arrays.sort(tempArr);
		// then compare in O(n) time.. 1 loop
		for (int i = 0; i < tempArr.length - 1; i++) {
			if (tempArr[i] == tempArr[i + 1])
				return false;
		}
		return true;
	}

	/**
	 * takes quadriatic time..O(n2)
	 * 
	 * @param arr
	 */
	public void prefixAverage1(long[] arr) {
		int[] newArr = new int[arr.length]; // O(n) time
		for (int i = 0; i < arr.length; i++) { // O(n) time
			int total = 0;
			for (int j = 0; j <= i; j++)
				total += arr[j]; // O(n2) as this happens for 1,2,..n =>
									// n(n+1)/2
			newArr[i] = total / (i + 1);
		}
	}

	/**
	 * takes linear time.. O(n)
	 * 
	 * @param arr
	 */
	public void prefixAverage2(long[] arr) {
		int[] newArr = new int[arr.length]; // O(n)
		int total = 0; // O(1)
		for (int i = 0; i < arr.length; i++) { // O(n)
			total += arr[i];
			newArr[i] = total / (i + 1);
		}
	}

	/**
	 * rotate int arr by k steps. using intermediate array takes O(n) space and O(n) time
	 * 
	 * @param arr
	 * @param steps
	 */
	public void rotateArray1(int[] arr, int k) {
		if (k > arr.length)
			k = k % arr.length;

		int[] tempArr = new int[arr.length];
		for (int i = 0; i < k; i++) {
			tempArr[i] = arr[arr.length - k + i];
		}
		int j = 0;
		for (int i = k; i < arr.length; i++) {
			tempArr[i] = arr[j];
			j++;
		}
		System.arraycopy(tempArr, 0, arr, 0, arr.length);
	}

	/**
	 * using bubble sort, it takes O(1) space but O(n * k) time where n = number of elements in array and k is number of
	 * steps
	 * 
	 * @param arr
	 * @param k
	 */
	public void rotateArray2(int[] arr, int k) {
		if (arr == null || k < 0 || arr.length == 0)
			throw new IllegalArgumentException("invalid input");

		for (int i = 0; i < k; i++) {
			for (int j = arr.length - 1; j > 0; j--) {
				int temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
			}
		}
	}

	/**
	 * first split the array in 2 halfs. reverse first half reverse second half reverse whole array takes O(1) space and
	 * O(n) time
	 * 
	 * @param arr
	 * @param k
	 */
	public void rotateArray3(int[] arr, int k) {
		if (arr == null || k < 0 || arr.length == 0)
			throw new IllegalArgumentException("invalid input");

		// first half
		reverse(arr, 0, k - 1);
		// second half
		reverse(arr, k, arr.length - 1);
		// whole array
		reverse(arr, 0, arr.length - 1);
	}

	private void reverse(int[] arr, int start, int end) {
		if (arr == null || arr.length == 1)
			return;

		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}

	/**
	 * reverse words in string with extra space allocation
	 * 
	 * @param s
	 * @return
	 */
	public String reverseWordsinString1(char[] s) {
		int i = 0;
		for (int j = 0; j < s.length; j++) {
			if (s[j] == ' ') {
				reverse(s, i, j - 1);
				System.out.println("Step 0:" + new String(s));
				i = j + 1;
			}
		}

		reverse(s, i, s.length - 1);
		System.out.println("Step 1:" + new String(s));

		reverse(s, 0, s.length - 1);
		System.out.println("Step 2:" + new String(s));

		return new String(s);
	}

	public void reverse(char[] s, int i, int j) {
		while (i < j) {
			char temp = s[i];
			s[i] = s[j];
			s[j] = temp;
			i++;
			j--;
		}
	}

	public String reverseWordsInString2(String s) {
		String[] split = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = split.length - 1; i >= 0; i--) {
			sb.append(split[i] + " ");
		}
		return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
	}

}
