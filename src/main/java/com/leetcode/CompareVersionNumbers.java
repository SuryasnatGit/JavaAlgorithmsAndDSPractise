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
 * Category : Medium
 *
 */
public class CompareVersionNumbers {

	public int compareVersion(String version1, String version2) {
		String[] ver1Strings = version1.split("\\.");
		String[] ver2Strings = version2.split("\\.");

		int i = 0, j = 0;

		while (i < ver1Strings.length && j < ver2Strings.length) {

			if (ver1Strings[i].compareTo(ver2Strings[j]) != 0) {
				String ver1String = removeLeadingZeros(ver1Strings[i]);
				String ver2String = removeLeadingZeros(ver2Strings[i]);

				int compare = compareTo(ver1String, ver2String);
				if (compare != 0) {
					return compare;
				}
			}

			i++;
			j++;
		}

		if (i == ver1Strings.length && j == ver2Strings.length) {
			return 0;
		}

		if (i < ver1Strings.length) {
			if (onlyZeroLeft(ver1Strings, i)) {
				return 0;
			}
			return 1;
		} else {
			if (onlyZeroLeft(ver2Strings, j)) {
				return 0;
			}
			return -1;
		}
	}

	private String removeLeadingZeros(String version) {
		int zeroIndex = 0;
		while (zeroIndex < version.length() && version.charAt(zeroIndex) == '0') {
			zeroIndex++;
		}
		return version.substring(zeroIndex);
	}

	private int compareTo(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return s1.length() > s2.length() ? 1 : -1;
		}

		if (s1.compareTo(s2) == 0) {
			return 0;
		}

		return s1.compareTo(s2) < 0 ? -1 : 1;
	}

	private boolean onlyZeroLeft(String[] arr, int start) {
		for (int i = start; i < arr.length; i++) {
			if (!arr[i].equals("0")) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		CompareVersionNumbers com = new CompareVersionNumbers();
		System.out.println(com.compareVersion("0.1", "1.1"));
		System.out.println(com.compareVersion("1.0.1", "1"));
		System.out.println(com.compareVersion("7.5.2.4", "7.5.3"));
		System.out.println(com.compareVersion("1.01", "1.001"));
		System.out.println(com.compareVersion("1.0", "1.0.0"));
		System.out.println(com.compareVersion("1.23", "3.1"));

		// TODO: this test case does not work. to check later
		String s1 = "19.8.3.17.5.01.0.0.4.0.0.0.0.0.0.0.0.0.0.0.0.0.00.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000";
		String s2 = "19.8.3.17.5.01.0.0.4.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0000.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.000000";

		System.out.println(com.compareVersion(s1, s2));
	}
}
