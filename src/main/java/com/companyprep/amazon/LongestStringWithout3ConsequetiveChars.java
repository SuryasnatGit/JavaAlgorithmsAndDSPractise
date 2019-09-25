package com.companyprep.amazon;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given A, B, C, find any string of maximum length that can be created such that no 3 consecutive characters are same.
 * There can be at max A 'a', B 'b' and C 'c'.
 * 
 * Example 1:
 * 
 * Input: A = 1, B = 1, C = 6
 * 
 * Output: "ccbccacc"
 * 
 * Example 2:
 * 
 * Input: A = 1, B = 2, C = 3
 * 
 * Output: "acbcbc"
 * 
 * Related questions:
 * 
 * https://leetcode.com/problems/reorganize-string<br/>
 * https://leetcode.com/problems/distant-barcodes<br/>
 * https://leetcode.com/problems/rearrange-string-k-distance-apart (premium)<br/>
 * 
 * Category : Hard
 *
 */
public class LongestStringWithout3ConsequetiveChars {

	public static String generateString(Map<Character, Integer> map) {
		PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<Map.Entry<Character, Integer>>(
				(a, b) -> b.getValue() - a.getValue());

		int cnt = 0;
		for (Map.Entry<Character, Integer> e : map.entrySet()) {
			cnt += e.getValue();
			maxHeap.add(e);
		}

		// only one char can be on hold
		Map.Entry<Character, Integer> onHold = null;

		StringBuilder sb = new StringBuilder();

		while (!maxHeap.isEmpty()) {
			Map.Entry<Character, Integer> cur = maxHeap.poll();
			sb.append(cur.getKey());

			if (onHold != null) {
				maxHeap.add(onHold);
				onHold = null;
			}
			int curValue = cur.getValue();
			if (curValue > 1) {
				cur.setValue(curValue - 1);
				if (sb.length() >= 2 && cur.getKey() == sb.charAt(sb.length() - 2)) { // on hold
					onHold = cur;
				} else { // add back to heap
					maxHeap.add(cur);
				}
			}

		}
		return sb.length() == cnt ? sb.toString() : "";
	}
}
