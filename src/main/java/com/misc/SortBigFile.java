package com.misc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 * CTCI - 10.6
 * 
 * Sort Big File: Imagine you have a 20 GB file with one string per line. Explain how you would sort
 * the file.
 * 
 * External sorting is a term for a class of sorting algorithms that can handle massive amounts of
 * data. External sorting is required when the data being sorted do not fit into the main memory of
 * a computing device (usually RAM) and instead they must reside in the slower external memory
 * (usually a hard drive). External sorting typically uses a hybrid sort-merge strategy. In the
 * sorting phase, chunks of data small enough to fit in main memory are read, sorted, and written
 * out to a temporary file. In the merge phase, the sorted sub-files are combined into a single
 * larger file.
 * 
 * One example of external sorting is the external merge sort algorithm, which sorts chunks that
 * each fit in RAM, then merges the sorted chunks together. We first divide the file into runs such
 * that the size of a run is small enough to fit into main memory. Then sort each run in main memory
 * using merge sort sorting algorithm. Finally merge the resulting runs together into successively
 * bigger runs, until the file is sorted.
 * 
 * Prerequisite for the algorithm/code: MergeSort :
 * 
 * Used for sort individual runs (a run is part of file that is small enough to fit in main memory)
 * 
 * Merge K Sorted Arrays : Used to merge sorted runs.
 * 
 * Inputs: input_file : Name of input file. input.txt output_file : Name of output file, output.txt
 * run_size : Size of a run (can fit in RAM) num_ways : Number of runs to be merged
 * 
 * Output: 1) Read input_file such that at most 'run_size' elements are read at a time. Do following
 * for the every run read in an array. a) Sort the run using MergeSort. b) Store the sorted run in a
 * temporary file, say 'i' for i'th run. 2) Merge the sorted files using the approach discussed
 * 
 * Category : Hard
 */
public class SortBigFile {

	public class MinHeapNode implements Comparable<MinHeapNode> {
		int element;
		int i; // index of array from which it is picked

		public MinHeapNode(int el, int i1) {
			element = el;
			i = i1;
		}

		public int compareTo(MinHeapNode n) {
			if (element < n.element) {
				return -1;
			} else if (element > n.element) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	private void mergeKSortedFiles(String outputFileName, int k) throws Exception {
		Scanner[] sc = new Scanner[k];
		for (int i = 0; i < k; i++) {
			String fileName = String.format("%d", i);
			sc[i] = new Scanner(new File(fileName));
		}

		MinHeapNode[] minHeapNodeArr = new MinHeapNode[k];
		PriorityQueue<MinHeapNode> minHeap = new PriorityQueue<>();

		int i; // need i for max count, so can break from next while loop
		for (i = 0; i < k; i++) {
			if (!sc[i].hasNext()) {
				break;
			}
			String nextData = sc[i].next();
			minHeapNodeArr[i] = new MinHeapNode(Integer.parseInt(nextData), i);
			minHeap.add(minHeapNodeArr[i]);
		}


		int count = 0;

		// Now one by one get the minimum element from min heap and replace it with the next element
		// run this all filled input files reach EOF
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
		while (count != i) {
			MinHeapNode root = minHeap.poll();
			writer.write(root.element + " ");

			if (!sc[root.i].hasNext()) {
				root.element = Integer.MAX_VALUE;
				count++;
			} else {
				root.element = sc[root.i].nextInt();
			}

			minHeap.add(root);
		}

		// close input output files
		for (int j = 0; j < k; j++) {
			sc[j].close();
		}
		writer.close();

	}

	// Using a merge-sort algorithm, create the initial run
	// and divide them evenly among the output files
	private void createIntialRuns(String inputFileName, int run_size, int num_ways) throws Exception {
		Scanner sc = new Scanner(new File(inputFileName));
		BufferedWriter[] writers = new BufferedWriter[num_ways];

		for (int i = 0; i < num_ways; i++) {
			String outputFileName = String.format("%d", i);
			writers[i] = new BufferedWriter(new FileWriter(outputFileName));
		}

		int[] arr = new int[run_size];
		boolean more_input = true;
		int next_output_file = 0;

		int i;

		while (more_input) {
			// write run_size elements into arr from input
			for (i = 0; i < run_size; i++) {
				if (!sc.hasNext()) {
					more_input = false;
					break;
				}

				arr[i] = sc.nextInt();
			}

			mergeSort(arr, 0, i - 1);

			// write the records to the appropriate scratch output file
			// can't assume that the loop runs to run_size
			// since the last run's length may be less than run_size
			for (int j = 0; j < i; j++) {
				writers[next_output_file].write(arr[j] + " ");
			}

			next_output_file++;
		}

		// close input and output files
		for (int j = 0; j < num_ways; j++) {
			writers[j].close();
		}
		sc.close();
	}

	private void mergeSort(int[] arr, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);

			merge(arr, l, m, r);
		}
	    }

	private void merge(int[] arr, int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		// create temp arrays
		int[] L = new int[n1];
		int[] R = new int[n2];

		// Copy data into temp arrays
		for (int i = 0; i < n1; i++) {
			L[i] = arr[l + i];
		}

		for (int j = 0; j < n2; j++) {
			R[j] = arr[m + 1 + j];
		}

		// merge temp arrays, initialize indices of first and second subarrays
		int i = 0, j = 0;
		int k = l; // initial index of merged subarray

		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		// Copy remaining elements of L[] and R[] if any
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}

	}

	public void externalSort(String inputFile, String outputFile, int num_ways, int run_size) throws Exception {
		createIntialRuns(inputFile, run_size, num_ways);
		mergeKSortedFiles(outputFile, num_ways);
	}

	public static void main(String args[]) throws Exception {

		SortBigFile sort = new SortBigFile();
		sort.externalSort(
				"/Users/M_402201/Developer/Code/personal/JavaAlgorithmsAndDSPractise/src/main/java/com/misc/input.txt",
				"/Users/M_402201/Developer/Code/personal/JavaAlgorithmsAndDSPractise/src/main/java/com/misc/output.txt",
				3, 5);
	}
}
