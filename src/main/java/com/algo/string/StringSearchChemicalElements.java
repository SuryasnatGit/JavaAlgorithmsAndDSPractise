package com.algo.string;

import java.util.HashSet;
import java.util.Set;

/**
 * input: firstname lastname output: returns the first chemical element in each name, such as lithium (Li), sodium
 * (Na)...
 *
 * I used hashset to store the elements and searched violently, because I think each name is not long... follow up:
 * Priority 2-letters element> 1-letter element Kneeling fell into the pit where I forgot the 2-letter name of the
 * element at the beginning, and wrote it in char for a long time. Then I suddenly remembered that there is a name like
 * Li Na Ba in the chemical element table of Te Mo Middle School, wasting a lot of time
 *
 * Either with length 1 or length 2
 *
 */
public class StringSearchChemicalElements {

	public static void main(String[] args) {
		StringSearchChemicalElements ss = new StringSearchChemicalElements();

		Set<String> set = new HashSet<String>();
		set.add("li");
		set.add("na");
		set.add("k");
		set.add("n");

		String fullName = "helnli jacknam";
		String[] res = ss.find(fullName, set);
		for (String s : res) {
			System.out.println(s);
		}
	}

	String[] find(String fullName, Set<String> set) {
		String[] names = fullName.split("\\s+");

		String[] res = new String[2];
		res[0] = search(names[0], set);
		res[1] = search(names[1], set);

		return res;
	}

	private String search(String S, Set<String> set) {
		String potentialS = null;
		for (int i = 0; i < S.length() - 1; i++) {
			String s1 = S.substring(i, i + 1);
			String s2 = S.substring(i, i + 2);

			if (set.contains(s2)) {
				return s2;
			} else if (set.contains(s1)) {
				potentialS = s1;
				// return s1;
			}
		}
		return potentialS;
	}
}
