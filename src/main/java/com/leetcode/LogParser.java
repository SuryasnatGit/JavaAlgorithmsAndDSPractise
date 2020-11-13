package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// TODO : incomplete
public class LogParser {

	public static void main(String[] args) {
		List<LogEntry> list = new ArrayList<LogEntry>();
		Comparator<LogEntry> com = new Comparator<LogEntry>(){
			public int compare(LogEntry en1, LogEntry en2) {
				if(en1.getDate() != en2.getDate()) {
					return en1.getDate() - en2.getDate();
				} else if() {
					
				}
			}
		};
		
		Collections.sort(list, com);
		
		return list; // Return sorted list of LogEntry
	}

	// Use binary search to fetch Logs under a specific window
	List<LogEntry> fetch(int hour, int min, int date, int hour2) {
		return null;
	}

	static int binarySearchRecursion(int[] nums, int target, int left, int right) {
		if (nums == null || nums.length == 0) {
			return -1;
		}

		if (left + 1 < right) { // Deliberately dont overlap, will add judgement later
			int mid = left + (right - left) / 2; // To avoid it is too huge if we do (left + right) / 2
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				return binarySearchRecursion(nums, target, mid, right);
			} else if (nums[mid] > target) {
				return binarySearchRecursion(nums, target, left, mid);
			}
		}

		if (nums[left] == target) {
			return left;
		}
		if (nums[right] == target) {
			return right;
		}

		return -1;
	}

	static int binarySearchWhile(int[] nums, int target) {

		if (nums == null || nums.length == 0) {
			return -1;
		}

		int left = 0;
		int right = nums.length - 1;

		while (left + 1 < right) { // Deliberately dont overlap, will add judgement later
			int mid = left + (right - left) / 2; // To avoid it is too huge if we do (left + right) / 2
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				left = mid;
			} else if (nums[mid] > target) {
				right = mid;
			}
		}

		// Either find the first occurance or the last occurance
		if (nums[left] == target) {
			return left;
		}
		if (nums[right] == target) {
			return right;
		}

		return -1;
	}
}

class LogEntry {
	int hour;
	int min;
	int date;
	String val;

	String getKey() {
		return date + ":" + hour + ":" + min;
	}

	public int getDate() {
		return date;
	}
}