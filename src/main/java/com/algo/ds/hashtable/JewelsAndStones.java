package com.algo.ds.hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have. Each
 * character in S is a type of stone you have. You want to know how many of the stones you have are also jewels. The
 * letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so"a"is
 * considered a different type of stone from"A".
 * 
 * Example 1:
 * 
 * Input: J = "aA", S = "aAAbbbb"
 * 
 * Output: 3
 * 
 * Example 2: Input: J = "z", S = "ZZ"
 * 
 * Output: 0
 * 
 * Note: S and J will consist of letters and have length at most 50. The characters in J are distinct.
 */
public class JewelsAndStones {

	public int howManyStonesAreJewels(String J, String S) {
		Set<Character> set = new HashSet<>();
		for (char c : J.toCharArray()) {
			set.add(c);
		}

		int count = 0;
		for (char c : S.toCharArray()) {
			if (set.contains(c))
				count++;
		}
		return count;
	}

	public static void main(String[] args) {
		JewelsAndStones j = new JewelsAndStones();
		System.out.println(j.howManyStonesAreJewels("aA", "aAAbbbb"));
		System.out.println(j.howManyStonesAreJewels("z", "ZZ"));
		System.out.println(j.howManyStonesAreJewels("aAb", "aAAbbbb"));
	}

}
