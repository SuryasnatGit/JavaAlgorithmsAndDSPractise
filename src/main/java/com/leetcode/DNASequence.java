package com.leetcode;

/**
 * 187. All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". 
 * When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].

Category : Medium
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DNASequence {

	public static void main(String[] args) {
		DNASequence dna = new DNASequence();
		System.out.println(dna.findRepeatedDnaSequences2("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
	}

	/**
	 * Post Java7 O(N) on substring the behaviour of substring changed to create a copy - so every String refers to a
	 * char[] which is not shared with any other object, as far as I'm aware. So at that point, substring() became an
	 * O(n) operation.
	 * 
	 * Pre Java7 O(1) on substring It simply builds a new String object referring to the same underlying char[] but with
	 * different offset and count values. So the cost is the time taken to perform validation and construct a single new
	 * (reasonably small) object. That's O(1) as far as it's sensible to talk about the complexity of operations which
	 * can vary in time based on garbage collection, CPU caches etc. In particular, it doesn't directly depend on the
	 * length of the original string or the substring.
	 */

	public List<String> findRepeatedDnaSequences2(String s) {
		List<String> res = new ArrayList<String>();
		Map<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0; i <= s.length() - 10; i++) {
			String sub = s.substring(i, i + 10);
			map.put(sub, map.getOrDefault(sub, 0) + 1);
		}

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			if (value > 1) {
				res.add(key);
			}
		}

		return res;
	}
}
