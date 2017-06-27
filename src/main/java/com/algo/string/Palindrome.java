package com.algo.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 * 
 * For example, "code" -> False, "aab" -> True, "carerac" -> True.
 * 
 * @author Suryasnat
 *
 */
public class Palindrome {

	/**
	 * Brute force - If a string with an even length is a palindrome, every character in the string must always occur an
	 * even number of times. If the string with an odd length is a palindrome, every character except one of the
	 * characters must always occur an even number of times. Thus, in case of a palindrome, the number of characters
	 * with odd number of occurences can't exceed 1(1 in case of odd length and 0 in case of even length).
	 * 
	 * Time complexity : O(128*n). We iterate constant number of times(128) over the string s of length n giving a time
	 * complexity of 128n
	 * 
	 * Space complexity : O(1). Constant extra space is used.
	 * 
	 * @param input
	 * @return
	 */
	public boolean canPermutatePalindrome1(String input) {
		// count = count of odd characters, for even length palindrome count = 0, for odd length palindrome count = 1
		int count = 0;
		for (int i = 0; i < 128; i++) {
			int ct = 0;
			for (int j = 0; j < input.length(); j++) {
				if (input.charAt(j) == i)
					ct++;
			}
			count += ct % 2;
		}
		return count <= 1;
	}

	/**
	 * From the discussion above, we know that to solve the given problem, we need to count the number of characters
	 * with odd number of occurences in the given string ss. To do so, we can also make use of a hashmap, mapmap. This
	 * mapmap takes the form (character_i, number of occurences of character_i)(character ​i ​​
	 * ,numberofoccurencesofcharacter ​i ​​ ).
	 * 
	 * Time complexity : O(n). We traverse over the given string s with n characters once. We also traverse over the map
	 * which can grow upto a size of n in case all characters in s are distinct.
	 * 
	 * Space complexity : O(n). The hashmap can grow upto a size of n, in case all the characters in s are distinct.
	 * 
	 * @param input
	 * @return
	 */
	public boolean canPermutatePalindrome2(String input) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < input.length(); i++) {
			Character ch = input.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}
		System.out.println(map);
		int count = 0;
		for (Character ch : map.keySet()) {
			count += map.get(ch) % 2;
		}
		return count <= 1;
	}

	/**
	 * Instead of making use of the inbuilt Hashmap, we can make use of an array as a hashmap. For this, we make use of
	 * an array mapmap with length 128. Each index of this mapmap corresponds to one of the 128 ASCII characters
	 * possible.
	 * 
	 * Time complexity : O(n). We traverse once over the string s of length n. Then, we traverse over the map of length
	 * 128(constant).
	 * 
	 * Space complexity : O(1). Constant extra space is used for map of size 128.
	 * 
	 * @param input
	 * @return
	 */
	public boolean canPermutatePalindrome3(String input) {
		// array of ascii chars
		int[] arr = new int[128];
		for (int i = 0; i < input.length(); i++) {
			arr[input.charAt(i)]++;
		}
		System.out.println(Arrays.asList(arr));
		int count = 0;
		for (int i = 0; i < arr.length && count <= 1; i++) {
			count += arr[i] % 2;
		}
		return count <= 1;
	}

	/**
	 * Instead of first traversing over the string ss for finding the number of occurences of each element and then
	 * determining the countcount of characters with odd number of occurences in ss, we can determine the value of
	 * countcount on the fly while traversing over ss.
	 * 
	 * Time complexity : O(n)O(n). We traverse over the string ss of length nn once only.
	 * 
	 * Space complexity : O(128)O(128). A mapmap of constant size(128) is used.
	 * 
	 * @param input
	 * @return
	 */
	public boolean canPermutatePalindrome4(String input) {
		int[] arr = new int[128];
		int count = 0;
		for (int i = 0; i < input.length(); i++) {
			arr[input.charAt(i)]++;
			// if value is odd , increment count, if even decrement count
			if (arr[input.charAt(i)] % 2 == 0)
				count--;
			else
				count++;
		}
		return count <= 1;
	}

	/**
	 * Another modification of the last approach could be by making use of a setset for keeping track of the number of
	 * elements with odd number of occurences in ss. For doing this, we traverse over the characters of the string ss.
	 * Whenver the number of occurences of a character becomes odd, we put its entry in the setset. Later on, if we find
	 * the same element again, lead to its number of occurences as even, we remove its entry from the setset. Thus, if
	 * the element occurs again(indicating an odd number of occurences), its entry won't exist in the setset.
	 * 
	 * Based on this idea, when we find a character in the string ss that isn't present in the setset(indicating an odd
	 * number of occurences currently for this character), we put its corresponding entry in the setset. If we find a
	 * character that is already present in the setset(indicating an even number of occurences currently for this
	 * character), we remove its corresponding entry from the setset.
	 * 
	 * At the end, the size of setset indicates the number of elements with odd number of occurences in ss. If it is
	 * lesser than 2, a palindromic permutation of the string ss is possible, otherwise not.
	 * 
	 * Time complexity : O(n)O(n). We traverse over the string ss of length nn once only.
	 * 
	 * Space complexity : O(n)O(n). The setset can grow upto a maximum size of nn in case of all distinct elements.
	 * 
	 * @param input
	 * @return
	 */
	public boolean canPermutatePalindrome5(String input) {
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < input.length(); i++) {
			if (!set.add(input.charAt(i)))
				set.remove(input.charAt(i));
		}
		System.out.println(set);
		return set.size() <= 1;
	}

	public static void main(String[] args) {
		Palindrome p = new Palindrome();
		System.out.println(p.canPermutatePalindrome1("table"));
		System.out.println(p.canPermutatePalindrome1("abba"));
		System.out.println(p.canPermutatePalindrome2("table"));
		System.out.println(p.canPermutatePalindrome2("abba"));
		System.out.println(p.canPermutatePalindrome3("table"));
		System.out.println(p.canPermutatePalindrome3("abba"));
		System.out.println(p.canPermutatePalindrome4("table"));
		System.out.println(p.canPermutatePalindrome4("abba"));
		System.out.println(p.canPermutatePalindrome5("table"));
		System.out.println(p.canPermutatePalindrome5("abba"));
		System.out.println(p.canPermutatePalindrome5("aba"));
	}

}
