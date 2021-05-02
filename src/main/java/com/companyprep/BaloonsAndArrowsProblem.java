package com.companyprep;

/**
 * N number of balloons are kept at different heights. You are asked to find out number of arrows to burst them. When an
 * arrow hits the balloon it goes one level down. Assume that the balloons are having same size.
 * 
 * for example given the balloons heights as array(Array will be given in decreasing order of size) : 5 4 3 3 2 2 1 1 1
 * minimum number of arrows to shoot them is: 3
 * 
 * explanation: <br/>
 * using first arrow shoot: 5 4 3 2 1 <br/>
 * using second arrow shoot: 3 2 1 <br/>
 * using third arrow shoot: 1
 * 
 * Example 2: 5 4 2 1
 * 
 * minimum number of arrows to shoot them is: 2 <br/>
 * using first arrow shoot: 5 4 <br/>
 * using second arrow shoot: 2 1
 * 
 * Expecting the solution to be in O(1) space complexity.
 * 
 */
public class BaloonsAndArrowsProblem {

	public static void main(String[] args) {

		BaloonsAndArrowsProblem baa = new BaloonsAndArrowsProblem();
		int[] arr1 = { 5, 4, 3, 3, 2, 2, 1, 1, 1 };
		int[] arr2 = { 5, 4, 2, 1 };
		System.out.println(baa.numberOfArrows1(arr1));
		System.out.println(baa.numberOfArrows1(arr2));

		System.out.println(baa.numberOfArrows2(arr1));
		System.out.println(baa.numberOfArrows2(arr2));
	}

	public int numberOfArrows1(int[] arr) {

		if (arr.length == 0)
			return 0;

		int sameNo = 0;
		int seq = 1; // if array is not empty then there will be 1 sequence by default
		int prev = 0; // to keep track of number of repeated numbers.

		for (int i = 1; i < arr.length; i++) {
			// when there is break b/w numbers.
			if (arr[i - 1] - arr[i] > 1) {
				seq++;
			}
			// when number or equal its the same number, hence we can form another set with this.
			if (arr[i] == arr[i - 1])
				sameNo++;
			if (arr[i] != arr[i - 1])
				sameNo = 0;
			if (prev < sameNo)
				prev = sameNo;
		}
		// combine both break in sequence and same numbers.
		return (seq + prev);

	}

	// simpler approach
	public int numberOfArrows2(int[] arr) {
		int result = 0;
		int len = arr.length;

		for (int i = 0; i < len; i++) {
			int v = arr[i];
			if (v == -1) { // no balloons at this height
				continue;
			}

			v--; // for every burst balloon decrease by 1
			result++;
			arr[i] = -1;
			for (int j = i + 1; j < len; j++) {
				if (arr[j] == v) {
					arr[j] = -1;
					v--;
				}
			}
		}

		return result;
	}

}
