package com.misc;

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
 * 
 * @author ctsuser1
 *
 */
public class SortBigFile {

	class MinHeapNode {
		// The element to be stored
		int element;

		// index of the array from which the element is taken
		int i;
	}

	// A class for Min Heap
	class MinHeap {
		MinHeapNode harr; // pointer to array of elements in heap
		int heap_size; // size of min heap

		public MinHeap() {
			// TODO Auto-generated constructor stub
		}

		// Constructor: creates a min heap of given size
		public MinHeap(MinHeapNode a[], int size) {
			heap_size = size;
			int i = (heap_size - 1) / 2;
			while (i >= 0) {
				MinHeapify(i);
				i--;
			}
		}

		// to get index of left child of node at index i
		int left(int i) {
			return (2 * i + 1);
		}

		// to get index of right child of node at index i
		int right(int i) {
			return (2 * i + 2);
		}

		// to get the root
		MinHeapNode getMin() {
			return harr[0];
		}

		// to replace root with new node x and heapify()
		// new root
		void replaceMin(MinHeapNode x) {
			harr[0] = x;
			MinHeapify(0);
		}
	}

	// A recursive method to heapify a subtree with root
	// at given index. This method assumes that the
	// subtrees are already heapified
	public void MinHeapify(int i) {
		int l = left(i);
		int r = right(i);
		int smallest = i;
		if (l < heap_size && harr[l].element < harr[i].element)
			smallest = l;
		if (r < heap_size && harr[r].element < harr[smallest].element)
			smallest = r;
		if (smallest != i) {
			swap(harr[i], harr[smallest]);
			MinHeapify(smallest);
		}
	}

	// A utility function to swap two elements
	void swap(MinHeapNode x, MinHeapNode y) {
		MinHeapNode temp = x;
		x = y;
		y = temp;
	}

	// Merges two subarrays of arr[].
	// First subarray is arr[l..m]
	// Second subarray is arr[m+1..r]
	void merge(int arr[], int l, int m, int r) {
		int i, j, k;
		int n1 = m - l + 1;
		int n2 = r - m;

		/* create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		/* Copy data to temp arrays L[] and R[] */
		for (i = 0; i < n1; i++)
			L[i] = arr[l + i];
		for (j = 0; j < n2; j++)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays back into arr[l..r] */
		i = 0; // Initial index of first subarray
		j = 0; // Initial index of second subarray
		k = l; // Initial index of merged subarray
		while (i < n1 && j < n2) {
			if (L[i] <= R[j])
				arr[k++] = L[i++];
			else
				arr[k++] = R[j++];
		}

		/*
		 * Copy the remaining elements of L[], if there are any
		 */
		while (i < n1)
			arr[k++] = L[i++];

		/*
		 * Copy the remaining elements of R[], if there are any
		 */
		while (j < n2)
			arr[k++] = R[j++];
	}

	/*
	 * l is for left index and r is right index of the sub-array of arr to be sorted
	 */
	void mergeSort(int arr[], int l, int r) {
		if (l < r) {
			// Same as (l+r)/2, but avoids overflow for
			// large l and h
			int m = l + (r - l) / 2;

			// Sort first and second halves
			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);

			merge(arr, l, m, r);
		}
	}

	public void openFile(char fileName, char mode) {
//	    FILE* fp = fopen(fileName, mode); 
//	    if (fp == NULL) 
//	    { 
//	        perror("Error while opening the file.\n"); 
//	        exit(EXIT_FAILURE); 
//	    } 
	}

	// Using a merge-sort algorithm, create the initial runs
	// and divide them evenly among the output files
	void createInitialRuns(String inputFile, int run_size, int num_ways) 
	{ 
	    // For big input file 
	    FILE *in = openFile(input_file, "r"); 
	  
	    // output scratch files 
	    FILE* out[num_ways]; 
	    char fileName[2]; 
	    for (int i = 0; i < num_ways; i++) 
	    { 
	        // convert i to string 
	        snprintf(fileName, sizeof(fileName), "%d", i); 
	  
	        // Open output files in write mode. 
	        out[i] = openFile(fileName, "w"); 
	    } 
	  
	    // allocate a dynamic array large enough 
	    // to accommodate runs of size run_size 
	    int* arr = (int*)malloc(run_size * sizeof(int)); 
	  
	    boolean more_input = true; 
	    int next_output_file = 0; 
	  
	    int i; 
	    while (more_input) 
	    { 
	        // write run_size elements into arr from input file 
	        for (i = 0; i < run_size; i++) 
	        { 
	            if (fscanf(in, "%d ", &arr[i]) != 1) 
	            { 
	                more_input = false; 
	                break; 
	            } 
	        } 
	  
	        // sort array using merge sort 
	        mergeSort(arr, 0, i - 1); 
	  
	        // write the records to the appropriate scratch output file 
	        // can't assume that the loop runs to run_size 
	        // since the last run's length may be less than run_size 
	        for (int j = 0; j < i; j++) 
	            fprintf(out[next_output_file], "%d ", arr[j]); 
	  
	        next_output_file++; 
	    } 
	  
	    // close input and output files 
	    for (int i = 0; i < num_ways; i++) 
	        fclose(out[i]); 
	  
	    fclose(in); 
	}

	// Merges k sorted files. Names of files are assumed
	// to be 1, 2, 3, ... k
	void mergeFiles(String output_file, int n, int k) 
	{ 
	    FILE* in[k]; 
	    for (int i = 0; i < k; i++) 
	    { 
	        char fileName[2]; 
	  
	        // convert i to string 
	        snprintf(fileName, sizeof(fileName), "%d", i); 
	  
	        // Open output files in read mode. 
	        in[i] = openFile(fileName, "r"); 
	    } 
	  
	    // FINAL OUTPUT FILE 
	    FILE *out = openFile(output_file, "w"); 
	  
	    // Create a min heap with k heap nodes.  Every heap node 
	    // has first element of scratch output file 
	    MinHeapNode harr = new MinHeapNode[k]; 
	    int i; 
	    for (i = 0; i < k; i++) 
	    { 
	        // break if no output file is empty and 
	        // index i will be no. of input files 
	        if (fscanf(in[i], "%d ", &harr[i].element) != 1) 
	            break; 
	  
	        harr[i].i = i; // Index of scratch output file 
	    }

	MinHeap hp(harr, i); // Create the heap 
	  
	    int count = 0; 
	  
	    // Now one by one get the minimum element from min 
	    // heap and replace it with next element. 
	    // run till all filled input files reach EOF 
	    while (count != i) 
	    { 
	        // Get the minimum element and store it in output file 
	        MinHeapNode root = hp.getMin(); 
	        fprintf(out, "%d ", root.element); 
	  
	        // Find the next element that will replace current 
	        // root of heap. The next element belongs to same 
	        // input file as the current min element. 
	        if (fscanf(in[root.i], "%d ", &root.element) != 1 ) 
	        { 
	            root.element = INT_MAX; 
	            count++; 
	        } 
	  
	        // Replace root with next element of input file 
	        hp.replaceMin(root); 
	    } 
	  
	    // close input and output files 
	    for (int i = 0; i < k; i++) 
	        fclose(in[i]); 
	  
	    fclose(out); 
	}

	// For sorting data stored on disk
	void externalSort(String inputFile, String outputFile, int num_ways, int run_size) {
		// read the input file, create the initial runs,
		// and assign the runs to the scratch output files
		createInitialRuns(inputFile, run_size, num_ways);

		// Merge the runs using the K-way merging
		mergeFiles(outputFile, run_size, num_ways);
	}

	// Driver program to test above
	public static void main(String[] args) {
		// No. of Partitions of input file.
		int num_ways = 10;

		// The size of each partition
		int run_size = 1000;

		String inputFile = "input.txt";
		String outputFile = "output.txt";

//		openFile(input_file, "w");

		externalSort(inputFile, outputFile, num_ways, run_size);
	}

}
