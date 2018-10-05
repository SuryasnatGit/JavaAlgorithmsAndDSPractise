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
public class PalindromePermutation {

	/**
	 * Brute force - If a string with an even length is a palindrome, every character in the string must
	 * always occur an even number of times. If the string with an odd length is a palindrome, every
	 * character except one of the characters must always occur an even number of times. Thus, in case
	 * of a palindrome, the number of characters with odd number of occurences can't exceed 1(1 in case
	 * of odd length and 0 in case of even length).
	 * 
	 * Time complexity : O(128*n). We iterate constant number of times(128) over the string s of length
	 * n giving a time complexity of 128n
	 * 
	 * Space complexity : O(1). Constant extra space is used.
	 * 
	 * @param input
	 * @return
	 */
	public boolean canPermutatePalindrome1(String input) {
		// count = count of odd characters, for even length palindrome count = 0, for odd length palindrome
		// count = 1
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
	 * From the discussion above, we know that to solve the given problem, we need to count the number
	 * of characters with odd number of occurences in the given string ss. To do so, we can also make
	 * use of a hashmap, mapmap. This mapmap takes the form (character_i, number of occurences of
	 * character_i)(character ​i ​​ ,numberofoccurencesofcharacter ​i ​​ ).
	 * 
	 * Time complexity : O(n). We traverse over the given string s with n characters once. We also
	 * traverse over the map which can grow upto a size of n in case all characters in s are distinct.
	 * 
	 * Space complexity : O(n). The hashmap can grow upto a size of n, in case all the characters in s
	 * are distinct.
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
	 * Instead of making use of the inbuilt Hashmap, we can make use of an array as a hashmap. For this,
	 * we make use of an array mapmap with length 128. Each index of this mapmap corresponds to one of
	 * the 128 ASCII characters possible.
	 * 
	 * Time complexity : O(n). We traverse once over the string s of length n. Then, we traverse over
	 * the map of length 128(constant).
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
	 * Instead of first traversing over the string s for finding the number of occurrences of each
	 * element and then determining the count of characters with odd number of occurrences in s, we can
	 * determine the value of count on the fly while traversing over s.
	 * 
	 * Time complexity : O(n). We traverse over the string s of length n once only.
	 * 
	 * Space complexity : O(128). A map of constant size(128) is used.
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
	 * Another modification of the last approach could be by making use of a set for keeping track of
	 * the number of elements with odd number of occurrences in s. For doing this, we traverse over the
	 * characters of the string s. Whenever the number of occurrences of a character becomes odd, we put
	 * its entry in the set. Later on, if we find the same element again, lead to its number of
	 * occurrences as even, we remove its entry from the set. Thus, if the element occurs
	 * again(indicating an odd number of occurrences), its entry won't exist in the set.
	 * 
	 * Based on this idea, when we find a character in the string s that isn't present in the
	 * set(indicating an odd number of occurrences currently for this character), we put its
	 * corresponding entry in the set. If we find a character that is already present in the
	 * set(indicating an even number of occurrences currently for this character), we remove its
	 * corresponding entry from the set.
	 * 
	 * At the end, the size of set indicates the number of elements with odd number of occurrences in s.
	 * If it is lesser than 2, a palindromic permutation of the string s is possible, otherwise not.
	 * 
	 * Time complexity : O(n). We traverse over the string s of length n once only.
	 * 
	 * Space complexity : O(n)O(n). The set can grow upto a maximum size of n in case of all distinct
	 * elements.
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

	/**
	 * If you think more deeply about this problem, you might notice that we don't actually need to know
	 * the counts. We just need to know if the count is even or odd. Think about flipping a light on/off
	 * (that is initially off). If the light winds up in the off state, we don't know how many times we
	 * flipped it, but we do know it was an even count. Given this, we can use a single integer (as a
	 * bit vector). When we see a letter, we map it to an integer between 0 and 26 (assuming an English
	 * alphabet). Then we toggle the bit at that value. At the end of the iteration, we check that at
	 * most one bit in the integer is set to 1. We can easily check that no bits in the integer are 1:
	 * just compare the integer to o. There is actually a very elegant way to check that an integer has
	 * exactly one bit set to 1. Picture an integer like eaaHleaa. We could of course shift the integer
	 * repeatedly to check that there's only a single 1. Alternatively, if we subtract 1 from the
	 * number, we'll get 131313131111. What's notable about this is that there is no overlap between the
	 * numbers (as opposed to say 00lEn000, which, when we subtract 1 from, we get 00100111.) So, we can
	 * check to see that a number has exactly one 1 because if we subtract 1 from it and then AND it
	 * with the new number, we should get 0. 00010000 - 1 = 00001111 00010000 & 00001111 = 0
	 * 
	 * @param input
	 * @return
	 */
	public boolean canPermutatePalindrome_usingBit(String input) {
		int bitVector = createBitVector(input);
		return bitVector == 6 || checkExactlyOneBitSet(bitVector);
	}

	private int createBitVector(String input) {
		int bitVector = 0;
		for (char c : input.toCharArray()) {
			int num = getCharNumber(c);
			bitVector = toggle(bitVector, num);
		}
		return bitVector;
	}

	private int getCharNumber(char c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int cn = Character.getNumericValue(c);
		if (a <= cn && cn <= z)
			return cn - a;
		return -1;
	}

	private int toggle(int bitVector, int index) {
		if (index < 0)
			return bitVector;
		int mask = 1 << index;
		if ((bitVector & mask) == 0)
			bitVector |= mask;
		else
			bitVector &= ~mask;
		return bitVector;
	}

	/*
	 * Check that exactly one bit is set by subtracting one from the integer and ANDing it with the
	 * original integer.
	 */
	boolean checkExactlyOneBitSet(int bitVector) {
		return (bitVector & (bitVector - 1)) == 0;
	}

	public static void main(String[] args) {
		PalindromePermutation p = new PalindromePermutation();
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
