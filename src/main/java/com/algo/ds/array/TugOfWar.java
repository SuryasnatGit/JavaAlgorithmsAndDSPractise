package com.algo.ds.array;

/**
 * http://www.geeksforgeeks.org/tug-of-war/.
 * 
 * Given a set of n integers, divide the set in two subsets of n/2 sizes each such that the difference of the sum of two
 * subsets is as minimum as possible. If n is even, then sizes of two subsets must be strictly n/2 and if n is odd, then
 * size of one subset must be (n-1)/2 and size of other subset must be (n+1)/2.
 * 
 * Category : Hard
 */
public class TugOfWar {

	public int minDiff;

	// main function that generate an arr
	public void tugOfWar(int arr[]) {
		int totalNumOfElements = arr.length;

		// the boolean array that contains the inclusion and exclusion of an element in current set. The number excluded
		// automatically form the other set
		boolean[] containsCurrentElements = new boolean[totalNumOfElements];

		// The inclusion/exclusion array for final solution
		boolean[] solution = new boolean[totalNumOfElements];

		minDiff = Integer.MAX_VALUE;

		int totalSum = 0;
		for (int i = 0; i < totalNumOfElements; i++) {
			totalSum += arr[i];
			containsCurrentElements[i] = solution[i] = false;
		}
		System.out.println("Array sum :" + totalSum);

		// Find the solution using recursive function TOWUtil()
		TOWUtil(arr, totalNumOfElements, containsCurrentElements, 0, solution, totalSum, 0, 0);

		// Print the solution
		System.out.println("The first subset is: ");
		totalSum = 0;
		for (int i = 0; i < totalNumOfElements; i++) {
			if (solution[i] == true) {
				System.out.print(arr[i] + " ");
				totalSum += arr[i];
			}
		}
		System.out.println("\nThe first subset sum is :" + totalSum);
		totalSum = 0;
		System.out.println("\nThe second subset is: ");
		for (int i = 0; i < totalNumOfElements; i++) {
			if (solution[i] == false) {
				System.out.print(arr[i] + " ");
				totalSum += arr[i];
			}
		}
		System.out.println("\nThe second subset sum is :" + totalSum);
	}

	// Recursive function using backtracking that tries every possible solution by calling itself
	private void TOWUtil(int arr[], int totalNumOfElements, boolean containsCurrentElements[],
			int numOfSelectedElements, boolean solution[], int totalSum, int currSum, int currPosition) {

		// checks whether the it is going out of bound
		if (currPosition == totalNumOfElements)
			return;

		// checks that the numbers of elements left are not less than the number of elements required to form the
		// solution
		if ((totalNumOfElements / 2 - numOfSelectedElements) > (totalNumOfElements - currPosition))
			return;

		// consider the cases when current element is not included in the solution
		TOWUtil(arr, totalNumOfElements, containsCurrentElements, numOfSelectedElements, solution, totalSum, currSum,
				currPosition + 1);

		// add the current element to the solution
		numOfSelectedElements++;
		currSum = currSum + arr[currPosition];
		containsCurrentElements[currPosition] = true;

		// checks if a solution is formed
		if (numOfSelectedElements == totalNumOfElements / 2) {
			// checks if the solution formed is better than the best solution so far
			if (Math.abs(totalSum / 2 - currSum) < minDiff) {
				minDiff = Math.abs(totalSum / 2 - currSum);
				for (int i = 0; i < totalNumOfElements; i++)
					solution[i] = containsCurrentElements[i];
			}
		} else {
			// consider the cases where current element is included in the solution
			TOWUtil(arr, totalNumOfElements, containsCurrentElements, numOfSelectedElements, solution, totalSum,
					currSum, currPosition + 1);
		}

		// backtrack by removeing current element before returning to the caller of this function
		containsCurrentElements[currPosition] = false;
	}

	public static void main(String args[]) {
		TugOfWar tow = new TugOfWar();
		int arr[] = { 23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4 };

		tow.tugOfWar(arr);
	}
}
