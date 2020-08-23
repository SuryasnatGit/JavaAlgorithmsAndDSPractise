package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Given a sorted integer array without duplicates, return the summary of its ranges. For example, given [0,1,2,4,5,7],
 * return ["0->2","4->5","7"].
 *
 * Solution - Just check if num[i] + 1 != num[i + 1]. If its not equal means you need to add previous range to result
 * and start a new range.
 * 
 * Time complexity O(n)
 *
 * https://leetcode.com/problems/summary-ranges/
 */
public class SummaryRanges {

	public List<String> summaryRanges(int[] nums) {
		if (nums == null || nums.length == 0) {
			return Collections.emptyList();
		}

		int start = nums[0];
		int end = nums[0];

		List<String> result = new ArrayList<>();
		for (int i = 1; i < nums.length; i++) {
			if (end + 1 == nums[i]) {
				end = nums[i];
			} else {
				addToResult(start, end, result);
				start = nums[i];
				end = nums[i];
			}
		}

		addToResult(start, end, result);

		return result;
	}

	private void addToResult(int start, int end, List<String> result) {
		String range = (start == end) ? Integer.toString(start) : makeRange(start, end);
		result.add(range);
	}

	private String makeRange(int start, int end) {
		return start + "->" + end;
	}

	public static void main(String[] args) {
		SummaryRanges sr = new SummaryRanges();
		int[] nums = new int[] { 0, 1, 2, 4, 5, 7 };
		System.out.println(sr.summaryRanges(nums));
		System.out.println(sr.summaryRanges(new int[] { 0, 2, 3, 4, 6, 8, 9 }));
	}
}
