package com.leetcode;

/**
 * Compare two version numbers version1 and version2. If version1 > version2 return 1; if version1 < version2 return
 * -1;otherwise return 0.
 * 
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * 
 * The . character does not represent a decimal point and is used to separate number sequences.
 * 
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of
 * the second first-level revision.
 * 
 * You may assume the default revision number for each level of a version number to be 0. For example, version number
 * 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level
 * revision number are both 0.
 * 
 * Example 1:
 * 
 * Input: version1 = "0.1", version2 = "1.1"
 * 
 * Output: -1
 * 
 * Example 2:
 * 
 * Input: version1 = "1.0.1", version2 = "1"
 * 
 * Output: 1
 * 
 * Example 3:
 * 
 * Input: version1 = "7.5.2.4", version2 = "7.5.3"
 * 
 * Output: -1
 * 
 * Example 4:
 * 
 * Input: version1 = "1.01", version2 = "1.001"
 * 
 * Output: 0
 * 
 * Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”
 * 
 * Example 5:
 * 
 * Input: version1 = "1.0", version2 = "1.0.0"
 * 
 * Output: 0
 * 
 * Explanation: The first version number does not have a third level revision number, which means its third level
 * revision number is default to "0"
 * 
 * Note:
 * 
 * Version strings are composed of numeric strings separated by dots . and this numeric strings may have leading zeroes.
 * 
 * Version strings do not start or end with dots, and they will not be two consecutive dots.
 * 
 * The time complexity of the given code can be considered to be O(max(M, N)), where M is the length of version1 and N
 * is the length of version2. This is because the code uses two while loops that iterate through each character of both
 * version1 and version2 at most once. The inner while loops, which convert the version numbers from string to integer,
 * contribute to the same overall time complexity because they iterate through each subsection of the versions delimited
 * by the period character '.', still not exceeding the total length of the versions.
 * 
 * The space complexity of the code is O(1), since it only uses a fixed number of integer variables and does not
 * allocate any variable-sized data structures dependent on the size of the input.
 * 
 * Category : Medium
 *
 */
public class CompareVersionNumbers {

	public int compareVersion1(String version1, String version2) {
		int length1 = version1.length(), length2 = version2.length(); // Store the lengths of the version strings

		// Initialize two pointers for traversing the strings
		for (int i = 0, j = 0; (i < length1) || (j < length2); ++i, ++j) {
			int chunkVersion1 = 0, chunkVersion2 = 0; // Initialize version number chunks

			// Compute the whole chunk for version1 until a dot is encountered or the end of the string
			while (i < length1 && version1.charAt(i) != '.') {
				// Update the chunk by multiplying by 10 (moving one decimal place)
				// and adding the integer value of the current character
				chunkVersion1 = chunkVersion1 * 10 + (version1.charAt(i) - '0');
				i++; // Move to the next character
			}

			// Compute the whole chunk for version2 until a dot is encountered or the end of the string
			while (j < length2 && version2.charAt(j) != '.') {
				chunkVersion2 = chunkVersion2 * 10 + (version2.charAt(j) - '0');
				j++; // Move to the next character
			}

			// Compare the extracted chunks from version1 and version2
			if (chunkVersion1 != chunkVersion2) {
				// Return -1 if chunkVersion1 is smaller, 1 if larger
				return chunkVersion1 < chunkVersion2 ? -1 : 1;
			}
			// If chunks are equal, proceed to the next set of chunks
		}
		// If all chunks have been successfully compared and are equal, return 0
		return 0;
	}

	public static void main(String[] args) {
		CompareVersionNumbers com = new CompareVersionNumbers();

		String s1 = "19.8.3.17.5.01.0.0.4.0.0.0.0.0.0.0.0.0.0.0.0.0.00.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000";
		String s2 = "19.8.3.17.5.01.0.0.4.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0000.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000";

		System.out.println(com.compareVersion1("0.1", "1.1")); // -1
		System.out.println(com.compareVersion1("1.0.1", "1")); // 1
		System.out.println(com.compareVersion1("7.5.2.4", "7.5.3")); // -1
		System.out.println(com.compareVersion1("1.01", "1.001")); // 0
		System.out.println(com.compareVersion1("1.0", "1.0.0")); // 0
		System.out.println(com.compareVersion1("1.23", "3.1")); // -1
		System.out.println(com.compareVersion1(s1, s2)); // 0
	}
}
