package com.algo.string;

/**
 * You have been given an alphanumeric string S, extract maximum numeric value from that string. Alphabets will only be
 * in lower case.
 * 
 * Example:
 * 
 * Input:
 * 
 * 100klh564abc365bg
 * 
 * abvhd9sdnkjdfs
 * 
 * abchsd0sdhs
 * 
 * Output:
 * 
 * 564
 * 
 * 9
 * 
 * 0
 * 
 * @author surya
 *
 */
public class MaximumNumericValue {

	// Method to extract the maximum value
	public int extractMaximum(String str) {
		int num = 0, res = 0;

		// Start traversing the given string
		for (int i = 0; i < str.length(); i++) {
			// If a numeric value comes, start converting
			// it into an integer till there are consecutive
			// numeric digits
			if (Character.isDigit(str.charAt(i)))
				num = num * 10 + (str.charAt(i) - '0');

			// Update maximum value
			else {
				res = Math.max(res, num);

				// Reset the number
				num = 0;
			}
		}

		// Return maximum value
		return Math.max(res, num);
	}

	/**
	 * But in the case of large numbers above program wouldn’t work because of integer range in C and C++. So, to handle
	 * the case of large numbers we have to take each numeric value as separate string and then find maximum value.
	 * 
	 * @return
	 */
	// Method to extract the maximum value
	public String extractMaximumBetter(String str) {
		int n = str.length();
		String curr_num = "";
		String res = "";

		// Start traversing the string
		for (int i = 0; i < n; i++) {
			// Ignore leading zeroes
			while (i < n && str.charAt(i) == '0')
				i++;

			// Store numeric value into a string
			while (i < n && Character.isDigit(str.charAt(i))) {
				curr_num = curr_num + str.charAt(i);
				i++;
			}

			if (i == n)
				break;

			// Update maximum string
			res = maximumNum(curr_num, res);

			curr_num = "";
		}

		// To handle the case if there is only
		// 0 numeric value
		if (curr_num.length() == 0 && res.length() == 0)
			res = res + '0';

		// Return maximum string
		return maximumNum(curr_num, res);
	}

	/**
	 * Utility method to find maximum string
	 * 
	 * @param curr_num
	 * @param res
	 * @return
	 */
	private String maximumNum(String curr_num, String res) {
		int len1 = curr_num.length();
		int len2 = res.length();

		// If both having equal lengths
		if (len1 == len2 && len1 > 0 && len2 > 0) {
			// Reach first unmatched character / value
			int i = 0;
			while (curr_num.charAt(i) == res.charAt(i))
				i++;

			// Return string with maximum value
			if (curr_num.charAt(i) < res.charAt(i))
				return res;
			else
				return curr_num;
		}

		// If different lengths
		// return string with maximum length
		return len1 < len2 ? res : curr_num;
	}

	public static void main(String[] args) {
		MaximumNumericValue v = new MaximumNumericValue();
		String s = "abc100def200gh";
		System.out.println(v.extractMaximum(s));
		System.out.println(v.extractMaximumBetter(s));
	}

}
