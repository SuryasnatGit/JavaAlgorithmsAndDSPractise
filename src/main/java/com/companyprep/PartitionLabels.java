package com.companyprep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that
 * each letter appears in at most one part, and return a list of integers representing the size of these parts.
 * 
 * Example 1:
 * 
 * Input: S = "ababcbacadefegdehijhklij"
 * 
 * Output: [9,7,8]
 * 
 * Explanation:
 * 
 * The partition is "ababcbaca", "defegde", "hijhklij". This is a partition so that each letter appears in at most one
 * part. A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * 
 * Note:
 * 
 * S will have length in range [1, 500].
 * 
 * S will consist of lowercase letters ('a' to 'z') only.
 * 
 * Category : Medium
 *
 * TODO: to complete
 */
public class PartitionLabels {

	/**
	 * Using merging interval approach. time - O(n) space - O(n)
	 * 
	 * @param S
	 * @return
	 */
	public List<Integer> partitionLabels(String S) {
		Map<Character, int[]> map = new HashMap<>();

		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			int[] range = map.getOrDefault(c, new int[] { -1, -1 });
			if (range[0] == -1) {
				range[0] = i;
				range[1] = i;
			} else {
				range[1] = i;
			}
			map.put(c, range);
		}

		int[] temp = null;
		List<Integer> result = new ArrayList<>();
		for (Map.Entry<Character, int[]> entry : map.entrySet()) {
			int[] curr = entry.getValue();
			if (temp == null) {
				temp = curr;
			} else {
				if (curr[0] < temp[1]) { // if range overlap then take the max of the range
					temp = new int[] { temp[0], Math.max(curr[1], temp[1]) };
				} else {
					result.add(temp[1] - temp[0] + 1); // clear distinction. time to split.
					temp = curr;
				}
			}
		}

		result.add(temp[1] - temp[0] + 1);

		return result;
	}

	// Problem 2
	/**
	 * Given a string S of lowercase letters, partition S into as many as parts so that one letter only appear in one
	 * part. Return the partitions as a list.
	 * 
	 * Example 1:
	 * 
	 * Input:
	 * 
	 * S = “bbeadcxede”
	 * 
	 * Output:
	 * 
	 * [“bb”, “eadcxede”]
	 * 
	 * Example 2:
	 * 
	 * Input:
	 * 
	 * S = “baddacx”
	 * 
	 * Output:
	 * 
	 * [“b”, “adda", “c”, “x”]
	 * 
	 */

	public static void main(String[] args) {
		PartitionLabels pl = new PartitionLabels();
		List<Integer> labels = pl.partitionLabels("ababcbacadefegdehijhklij");
		System.out.println(labels);
	}
}
