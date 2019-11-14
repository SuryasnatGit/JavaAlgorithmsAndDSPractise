package com.algo.string;

/**
 * Given an input string, write a function that returns the Run Length Encoded string for the input string.
 * 
 * For example, if the input string is �wwwwaaadexxxxxx�, then the function should return �w4a3d1e1x6�.
 * 
 * Time Complexity: O(n)
 * 
 * 
 * @author Suryasnat
 *
 */
public class RunLengthEncoding {

	public String encoding1(String in) {
		String res = "";
		int sum = 1;

		for (int i = 0; i < in.length() - 1; i++) {
			if (in.charAt(i) == in.charAt(i + 1)) {
				sum++;
			} else {
				res = res + in.charAt(i) + sum;
				sum = 1; // reset
			}
		}

		res = res + in.charAt(in.length() - 1) + sum;
		// System.out.println(res);
		return res.length() > in.length() ? in : res;
	}

	public static void main(String args[]) {

		// String str = "A";
		// char result[] = new char[str.length() * 2];
		RunLengthEncoding rle = new RunLengthEncoding();
		// int current = rle.encoding(str.toCharArray(), result);
		// for (int i = 0; i < current; i++) {
		// System.out.print(result[i]);
		// }

		System.out.println(rle.encoding1("a"));
		System.out.println(rle.encoding1("aaa"));
		System.out.println(rle.encoding1("aaabbb"));
		System.out.println(rle.encoding1("aaabccc"));
		System.out.println(rle.encoding1("wwwwaaadexxxxxx"));
	}
}
