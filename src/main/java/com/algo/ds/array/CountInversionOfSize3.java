package com.algo.ds.array;

/**
 * Given input array find number of inversions where i < j < k and input[i] > input[j] > input[k]
 *
 * http://www.geeksforgeeks.org/count-inversions-of-size-three-in-a-give-array/.
 * 
 * Example:
 * 
 * Input: {8, 4, 2, 1} Output: 4 The four inversions are (8,4,2), (8,4,1), (4,2,1) and (8,2,1).
 * 
 * Input: {9, 6, 4, 5, 8} Output: 2 The two inversions are {9, 6, 4} and {9, 6, 5}
 */
public class CountInversionOfSize3 {

	/**
	 * complexity = O(n^3)
	 * 
	 * @param input
	 * @return
	 */
	public int findInversions_Simple(int[] input) {
		int inversion = 0;
		int len = input.length;
		for (int i = 0; i < len - 2; i++) {
			for (int j = i + 1; j < len - 1; j++) {
				if (input[i] > input[j]) {
					for (int k = j + 1; k < len; k++) {
						if (input[j] > input[k])
							inversion++;
					}
				}
			}
		}
		return inversion;
	}

    /**
	 * We can reduce the complexity if we consider every element arr[i] as middle element of inversion,
	 * find all the numbers greater than a[i] whose index is less than i, find all the numbers which are
	 * smaller than a[i] and index is more than i. We multiply the number of elements greater than a[i]
	 * to the number of elements smaller than a[i] and add it to the result. <br/>
	 * Time complexity of this method is O(n^2)<br/>
	 * Space complexity is O(1)
	 */
    public int findInversions(int input[]) {
        int inversion = 0;
        for (int i = 1; i < input.length - 1 ; i++) {
            int larger = 0;
			// count all large elements on left of input[i]
            for (int k = 0; k < i; k++) {
                if (input[k] > input[i]) {
                    larger++;
                }
            }
            int smaller = 0;
			// count all small elements on right of input[i]
            for (int k = i+1; k < input.length; k++) {
                if (input[k] < input[i]) {
                    smaller++;
                }
            }
            inversion += smaller*larger;
        }
        return inversion;
    }

	/**
	 * another approach is using Binary Indexed Tree which is covered in {@link CountInversionInArray}
	 * 
	 * @param args
	 */

    public static void main(String args[]) {
        int input[] = {9, 6, 4, 5, 8};
        CountInversionOfSize3 ci = new CountInversionOfSize3();
        System.out.print(ci.findInversions(input));
    }
}
