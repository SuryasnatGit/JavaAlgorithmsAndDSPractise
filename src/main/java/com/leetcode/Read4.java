package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Read N Characters Given Read4
 * 
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * 
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters
 * left in the file.
 * 
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * 
 * Note: The read function will only be called once for each test case.
 *
 */
public class Read4 {

	private LinkedList<Character> leftOver = new LinkedList<Character>();

	/*
	 * The read4 API is defined in the parent class Reader4.
	 * 
	 * int read4(char[] buf);
	 */
	int read4(char[] buf) {
		return -1;
	}

	/**
	 * 
	 * @param buf
	 *            Destination buffer
	 * 
	 * @param n
	 *            Maximum number of characters to read
	 * 
	 * @return The number of characters read
	 * 
	 */

	public int read(char[] buf, int n) {

		int count = 0;

		while (leftOver.size() > 0 && count < n) {
			char c = leftOver.removeFirst();
			buf[count++] = c;
		}

		while (count < n) {
			char[] curBuf = new char[4]; // This doesnt matter
			int charsRead = read4(curBuf);
			if (charsRead == 0) {
				return count; // Nothing to read any more
			}

			int index = 0;

			while (index < charsRead && count < n) {
				// Transfer to destination buffer
				buf[count++] = curBuf[index++]; //
			}

			while (index < charsRead) {
				leftOver.add(curBuf[index++]);
			}
		}

		return count;
	}

	public int readMultipleTimes(char[] buf, int n) {
		int index = 0;
		List<Character> list = new ArrayList<Character>();

		// First read from leftover
		while (index < n && !leftOver.isEmpty()) {
			list.add(leftOver.pollFirst());
			index++;
		}

		while (index < n) {
			char[] arr = new char[4];

			int k = read4(arr);

			int i = 0;
			for (; i < k && index < n; i++) {
				list.add(arr[i]);
				index++;
			}

			while (i < k) { // Means there is leftOver
				leftOver.addLast(arr[i]);
				i++;
			}

			if (k < 4) { // No more from the stream
				break;
			}
		}

		return index;
	}

	/**
	 * There are read4K_1 (), read4k_2 (), which read from two files respectively, and the two files can’t fit in the
	 * huge memory. You have to return whether the contents of the two files are the same.
	 */
	public boolean isSameRead4K() {
		while (true) {
			char[] arr1 = new char[4000];
			char[] arr2 = new char[4000];

			int count1 = read4K_1(arr1);
			int count2 = read4K_2(arr2);

			if (count1 != count2) {
				return false;
			}

			int pos = 0;
			while (pos < count1) {
				if (arr1[pos] != arr2[pos]) {
					return false;
				}
				pos++;
			}

			if (count1 < 4000 && count2 < 4000) { // end.
				return true;
			}
		}
	}

	private int read4K_1(char[] arr1) {
		return -1;
	}

	private int read4K_2(char[] arr1) {
		return -1;
	}

	/*
	 * 这个代码写的很好 // Read4K - Given a function which reads from a file or network stream up to 4k at a time, give a
	 * function which can satisfy requests for arbitrary amounts of data
	 * 
	 * private int read4K(char[] buf) { // GIVEN }
	 * 
	 * // IMPLEMENT: public int read(char[] buf, int toRead) { } Due to network latency, if the read4K method return a
	 * value < 4k, it does not necessarily mean that we reach the End of File (EOF), in this case, you should continue
	 * to read the file until you reach toRead or EOF.
	 */
	public int read4(char[] buf, int toRead) {
		int index = 0;
		boolean EOF = false;
		char eof = 'e';

		while (index < toRead && !EOF) {
			char[] arr = new char[4];

			int now = read4K(arr);

			for (int i = 0; i < now && index < toRead; i++) {
				buf[index++] = arr[i];
			}

			if (index > 0 && buf[index - 1] == eof) { // Until reading EOF
				EOF = true;
			}
		}

		return index;
	}

	private int read4K(char[] buf) {
		return -1;
	}
}
