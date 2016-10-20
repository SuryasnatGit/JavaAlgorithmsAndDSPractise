package com.algo.ds.array;

public class Arrays {

	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6,7,8,9,10};
		
		int l = a.length;
		// display
		for(int i=0;i<l;i++){
			System.out.print(a[i]);
		}
		System.out.println("\n");
		// reverse elements within array.
		for(int i=0;i<l/2;i++){
			int temp = a[i];
			a[i] = a[l-1-i];
			a[l-1-i] = temp;
		}
		
		// display
		for(int i=0;i<l;i++){
			System.out.print(a[i]);
		}

	}

}
