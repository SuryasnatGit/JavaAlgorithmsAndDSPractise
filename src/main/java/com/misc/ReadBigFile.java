package com.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Ebay question - How to remove duplicate lines in a large text file? I think it's easy to find
 * duplicate lines, but how do we efficiently remove them from the file?
 * 
 * Input: A file containing strings
 * 
 * Output: Another file without the duplicates
 * 
 * Constraint: Limited memory for the program, arbitrary file size
 * 
 * Hint : Use external merge sort.
 *
 */
public class ReadBigFile {

	public void readFile(File f) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(f);
	}

}
