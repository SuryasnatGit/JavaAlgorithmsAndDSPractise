package com.algo.ds.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashTableProblems {

	/**
	 * You're given stringsJrepresenting the types of stones that are jewels, andSrepresenting the stones you have. Each
	 * character inSis a type of stone you have. You want to know how many of the stones you have are also jewels. The
	 * letters inJare guaranteed distinct, and all characters inJandSare letters. Letters are case sensitive, so"a"is
	 * considered a different type of stone from"A".
	 * 
	 * Example 1: Input: J = "aA", S = "aAAbbbb" Output: 3
	 * 
	 * Example 2: Input: J = "z", S = "ZZ" Output: 0
	 * 
	 */
	public int jewelsAndStones(String J, String S) {
		if (J == null || J.length() == 0 || S == null || S.length() == 0) {
			return -1;
		}

		Set<Character> set = new HashSet<Character>();

		for (char ch : J.toCharArray()) {
			set.add(ch);
		}

		int count = 0;
		for (char ch : S.toCharArray()) {
			if (set.contains(ch)) {
				count++;
			}
		}

		return count;
	}

	/**
	 * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants
	 * represented by strings. You need to help them find out their common interest with the least list index sum. If
	 * there is a choice tie between answers, output all of them with no order requirement. You could assume there
	 * always exists an answer.
	 * 
	 * HashMap - Time: O(n1 + n2), Space - O(n1 * x)
	 * 
	 */
	public List<String> getMinIndexSum(List<String> list1, List<String> list2) {
		List<String> result = new ArrayList<String>();
		Map<String, Integer> indexMap = new HashMap<String, Integer>();

		for (int i = 0; i < list1.size(); i++) {
			indexMap.put(list1.get(i), i);
		}

		int leastIndexSum = Integer.MAX_VALUE;

		for (int i = 0; i < list2.size(); i++) {
			if (indexMap.containsKey(list2.get(i))) {
				int indexSum = i + indexMap.get(list2.get(i));
				if (indexSum < leastIndexSum) {
					result.clear();
					result.add(list2.get(i));
					leastIndexSum = indexSum;
				} else if (indexSum == leastIndexSum) {
					result.add(list2.get(i));
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		HashTableProblems htp = new HashTableProblems();
		System.out.println(htp.jewelsAndStones("aA", "aAAbbbb"));
		System.out.println(htp.jewelsAndStones("z", "ZZ"));
		System.out.println(htp.getMinIndexSum(Arrays.asList("Shogun", "Tapioca Express", "Burger King", "KFC"),
				Arrays.asList("KFC", "Shogun", "Burger King")));
	}
}
