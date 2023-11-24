package com.algo.search;

import java.util.HashMap;
import java.util.Map;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of
 * your product fails the quality check. Since each version is developed based on the previous version, all the versions
 * after a bad version are also bad. Suppose you have n versions[1, 2, ..., n] and you want to find out the first bad
 * one, which causes all the following ones to be bad. You are given an APIbool isBadVersion(version)which will return
 * whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls
 * to the API. Example: Given n = 5, and version = 4 is the first bad version.
 * 
 * call isBadVersion(3) - > false
 * 
 * call isBadVersion(5) - > true
 * 
 * call isBadVersion(4) - > true
 * 
 * Then 4 is the first bad version.
 */
public class FirstBadVersion {

	public boolean isBadVersion(int version) {
		Map<Integer, Boolean> map = new HashMap<>();
		map.put(1, false);
		map.put(2, true);
		map.put(3, true);
		map.put(4, true);
		map.put(5, true);
		return map.get(version);
	}

	public int getFirstBadVersion(int[] versions) {
		int low = 1;
		int high = versions.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (isBadVersion(mid)) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return low;
	}

	public static void main(String[] args) {
		FirstBadVersion bad = new FirstBadVersion();
		System.out.println(bad.getFirstBadVersion(new int[] { 1, 2, 3, 4, 5 }));
	}

}
