package com.hackerrank;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Algorithms {

	public static void main(String[] args) {
		Algorithms algo = new Algorithms();
		int[] arr = new int[] { -4, 3, -9, 0, 4, 1 };
		// algo.diagonalAbsoluteDiff();
		// algo.precisionProblem(6, arr);
		// int[] ar = new int[] { 10, 20, 20, 10, 10, 30, 50, 10, 20 };
		// System.out.println(algo.sockMerchant(9, ar));
		// System.out.println(algo.birthdayBar(Arrays.asList(1, 2, 1, 3, 2), 3, 2));
		// System.out.println(algo.birthdayBar(Arrays.asList(1, 1, 1, 1, 1, 1), 3, 2));
		// System.out.println(algo.birthdayBar(Arrays.asList(4), 4, 1));
		// int[] input = new int[] { 7, -5, -3, -3, 0, 4, -5, -5, 0, 7, 7 };
		// System.out.println(algo.rearrangeDuplicates(input));
		// algo.extraLongFactorials(50);
		// algo.extraLongFactorials(100);
		// System.out.println(algo.appendAndDelete("hackerhappy", "hackerrank", 9));
		// System.out.println(algo.appendAndDelete("aba", "aba", 7));
		// System.out.println(algo.appendAndDelete("ashley", "ash", 200));

		List<Integer> ql1 = Arrays.asList(1, 5);
		List<Integer> ql2 = Arrays.asList(1, 6);
		List<Integer> ql3 = Arrays.asList(3, 2);
		List<Integer> ql4 = Arrays.asList(1, 10);
		List<Integer> ql5 = Arrays.asList(1, 10);
		List<Integer> ql6 = Arrays.asList(1, 6);
		List<Integer> ql7 = Arrays.asList(2, 5);
		List<Integer> ql8 = Arrays.asList(3, 2);

		List<List<Integer>> res = new ArrayList<List<Integer>>();
		res.add(ql1);
		res.add(ql2);
		res.add(ql3);
		res.add(ql4);
		res.add(ql5);
		res.add(ql6);
		res.add(ql7);
		res.add(ql8);

		// System.out.println(algo.freqQuery(res));

		// System.out.println(algo.countTriplets(LongStream.of(1, 4, 16, 64).boxed().collect(Collectors.toList()), 4));
		// System.out.println(algo.countTriplets(LongStream.of(1, 2, 2, 4).boxed().collect(Collectors.toList()), 2));
		// System.out.println(algo.countTriplets(LongStream.of(1, 1, 1, 1).boxed().collect(Collectors.toList()), 1));

		System.out.println(algo.sherlockAndAnagrams("abba"));
		System.out.println(algo.sherlockAndAnagrams("kkkk"));
	}

	/**
	 * Replace duplicates with 0. Sort the final output.
	 * 
	 * @param input
	 * @return
	 */
	public List<Integer> rearrangeDuplicates(int[] input) {
		List<Integer> negative = new ArrayList<>();
		List<Integer> positive = new ArrayList<>();
		List<Integer> res = new ArrayList<>();
		SortedMap<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < input.length; i++) {
			if (map.containsKey(input[i])) {
				int count = map.get(input[i]) + 1;
				map.put(input[i], count);
			} else {
				map.put(input[i], 1);
			}
		}
		System.out.println(map);
		int count = 0;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getKey() < 0) {
				negative.add(entry.getKey());
				count += (entry.getValue() - 1);
			} else if (entry.getKey() == 0) {
				count += entry.getValue();
			} else {
				count += (entry.getValue() - 1);
				positive.add(entry.getKey());
			}
		}

		for (Integer i : negative)
			res.add(i);
		for (int i = 0; i < count; i++)
			res.add(0);
		for (Integer i : positive)
			res.add(i);

		return res;
	}

	public void diagonalAbsoluteDiff() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		sc.close();
		int s1 = 0;
		int s2 = 0;
		for (int i = 0; i < n; i++) {
			s1 += arr[i][i];
			s2 += arr[i][n - 1 - i];
		}
		System.out.println(Math.abs(s1 - s2));
	}

	public void precisionProblem(int n, int[] arr) {

		int posc = 0, negc = 0, zeroc = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] < 0)
				negc++;
			else if (arr[i] > 0)
				posc++;
			else
				zeroc++;
		}

		// Float f = new Float(zeroc);
		DecimalFormat df = new DecimalFormat("#0.000000");
		// df.setMaximumFractionDigits(6);
		// System.out.println(df.format(negc));
		float p = (float) posc / n;
		float ne = (float) negc / n;
		float z = (float) zeroc / n;
		System.out.println(p);
		System.out.println(ne);
		System.out.println(z);
		System.out.println(df.format(ne));
		System.out.println(df.format(p));
		System.out.println(df.format(z));
	}

	/**
	 * John works at a clothing store. He has a large pile of socks that he must pair by color for sale. Given an array
	 * of integers representing the color of each sock, determine how many pairs of socks with matching colors there
	 * are.
	 * 
	 * For example, there are socks with colors . There is one pair of color and one of color . There are three odd
	 * socks left, one of each color. The number of pairs is .
	 * 
	 * Function Description
	 * 
	 * Complete the sockMerchant function in the editor below. It must return an integer representing the number of
	 * matching pairs of socks that are available.
	 * 
	 * sockMerchant has the following parameter(s):
	 * 
	 * n: the number of socks in the pile ar: the colors of each sock
	 * 
	 * Input Format
	 * 
	 * The first line contains an integer , the number of socks represented in . The second line contains
	 * space-separated integers describing the colors of the socks in the pile.
	 * 
	 * Constraints
	 * 
	 * where
	 * 
	 * Output Format
	 * 
	 * Print the total number of matching pairs of socks that John can sell.
	 * 
	 * Sample Input
	 * 
	 * 9 10 20 20 10 10 30 50 10 20
	 * 
	 * Sample Output
	 * 
	 * 3
	 * 
	 * @param n
	 * @param ar
	 * @return
	 */
	public int sockMerchant(int n, int[] ar) {
		Set<Integer> set = new HashSet<>();
		int count = 0;
		for (int i = 0; i < ar.length; i++) {
			if (set.contains(ar[i])) {
				count++;
				set.remove(ar[i]);
			} else {
				set.add(ar[i]);
			}
		}
		return count;
	}

	/**
	 * Lily has a chocolate bar that she wants to share it with Ron for his birthday. Each of the squares has an integer
	 * on it. She decides to share a contiguous segment of the bar selected such that the length of the segment matches
	 * Ron's birth month and the sum of the integers on the squares is equal to his birth day. You must determine how
	 * many ways she can divide the chocolate.
	 * 
	 * Consider the chocolate bar as an array of squares, . She wants to find segments summing to Ron's birth day, with
	 * a length equaling his birth month, . In this case, there are two segments meeting her criteria: and .
	 * 
	 * Function Description
	 * 
	 * Complete the birthday function in the editor below. It should return an integer denoting the number of ways Lily
	 * can divide the chocolate bar.
	 * 
	 * birthday has the following parameter(s):
	 * 
	 * s: an array of integers, the numbers on each of the squares of chocolate d: an integer, Ron's birth day m: an
	 * integer, Ron's birth month Input Format
	 * 
	 * The first line contains an integer , the number of squares in the chocolate bar. The second line contains
	 * space-separated integers , the numbers on the chocolate squares where . The third line contains two
	 * space-separated integers, and , Ron's birth day and his birth month.
	 * 
	 * Sample Input 0
	 * 
	 * 5 <br/>
	 * 1 2 1 3 2<br/>
	 * 3<br/>
	 * 2
	 * 
	 * Sample Output 0
	 * 
	 * 2
	 * 
	 * 
	 * @param s
	 * @param d
	 * @param m
	 * @return
	 */
	public int birthdayBar(List<Integer> s, int d, int m) {
		int count = 0;
		for (int i = 0; i < s.size(); i++) {
			int sum = 0;
			int start = i;
			int end = i + m - 1;
			if (end < s.size()) {
				while (start <= end) {
					sum += s.get(start);
					start++;
				}
			}
			if (sum == d)
				count++;
		}
		return count;
	}

	/**
	 * Consider two sets of positive integers, and . We say that a positive integer,A={a0,a1,a2..,an} and
	 * B={b0,b1,b2...,bm} , is between sets and if the following conditions are satisfied:
	 * 
	 * All elements in are factors of . is a factor of all elements in . Given and , find and print the number of
	 * integers (i.e., possible 's) that are between the two sets.
	 */
	public void between2sets() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[] a = new int[n];
		// find max of a[] and min of b[]
		int max_a = 0;
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
			max_a = Integer.max(a[a_i], max_a);
		}
		int min_b = Integer.MAX_VALUE;
		int[] b = new int[m];
		for (int b_i = 0; b_i < m; b_i++) {
			b[b_i] = in.nextInt();
			min_b = Integer.min(b[b_i], min_b);
		}
		int count = 0;
		for (int i = max_a; i <= min_b; i++) {
			if (min_b % i == 0)
				count++;
		}
		System.out.println(count);

	}

	/**
	 * We recommend solving this challenge using BigIntegers.
	 * 
	 * @param num
	 */
	public void extraLongFactorials(int num) {
		BigInteger bi = new BigInteger("1");
		for (int i = 2; i <= num; i++) {
			bi = bi.multiply(new BigInteger(String.valueOf(i)));
		}
		System.out.println(bi);
	}

	/**
	 * https://www.hackerrank.com/challenges/append-and-delete/problem
	 * 
	 * @param s
	 * @param t
	 * @param k
	 * @return
	 */
	public boolean appendAndDelete(String s, String t, int k) {
		if (k >= s.length() + t.length())
			return true;

		for (int i = s.length(); i >= 0; i--) {
			String prefix = s.substring(0, i);
			if (t.startsWith(prefix)) {
				int diff = s.length() - i + t.length() - i;
				return diff <= k && (k - diff) % 2 == 0;
			}
		}
		return false;
	}

	// Complete the freqQuery function below.
	/**
	 * You are given queries. Each query is of the form two integers described below: - 1 x: Insert x in your data
	 * structure. - 2 y: Delete one occurence of y from your data structure, if present. - 3 z: Check if any integer is
	 * present whose frequency is exactly . If yes, print 1 else 0.
	 * 
	 * The queries are given in the form of a 2-D array of size q where queries[i][0] contains the operation, and
	 * queries[i][1] contains the data element.
	 * 
	 * Example: queries[(1,1)(2,2)(3,2)(1,1)(1,1)(2,1)(3,2)]
	 * 
	 * The results of each operation are:
	 * 
	 * <br/>
	 * Operation Array Output <br/>
	 * (1,1) [1] <br/>
	 * (2,2) [1] <br/>
	 * (3,2) 0 <br/>
	 * (1,1) [1,1] <br/>
	 * (1,1) [1,1,1] <br/>
	 * (2,1) [1,1] <br/>
	 * (3,2) 1
	 * 
	 * Return an array with the output:[0,1]
	 * 
	 * @param queries
	 * @return
	 */
	public List<Integer> freqQuery(List<List<Integer>> queries) {

		// 1st map between value and frequency of occurrences
		Map<Integer, Integer> valueCountMap = new HashMap<>();
		// 2nd array to keep track of frequencies
		int[] frequencies = new int[queries.size() + 1];

		List<Integer> result = new ArrayList<>();

		for (List<Integer> query : queries) {
			int type = query.get(0);
			int value = query.get(1);
			System.out.println("Type :" + type + " Value :" + value);

			if (type == 1) {
				// add
				int count = valueCountMap.getOrDefault(value, 0);
				valueCountMap.put(value, count + 1);

				// process frequencies
				frequencies[count]--;
				frequencies[count + 1]++;

			} else if (type == 2) {
				// remove
				if (valueCountMap.containsKey(value)) {
					int count = valueCountMap.get(value);
					if (count > 0) {
						valueCountMap.put(value, count - 1);

						// process frequencies
						frequencies[count]--;
						frequencies[count - 1]++;

					} else {
						valueCountMap.remove(value);
					}
				}
			} else if (type == 3) {
				if (value >= frequencies.length) {
					result.add(0); // out of bounds
				} else {
					result.add(frequencies[value] > 0 ? 1 : 0);
				}
			}

			System.out.println("VC map : " + valueCountMap);
			System.out.println();
		}

		return result;
	}

	/**
	 * You are given an array and you need to find number of tripets of indices (i,j,k) such that the elements at those
	 * indices are in geometric progression for a given common ratio r and i<j<k.
	 * 
	 * Example: arr=[1,4,16,64] r = 4
	 * 
	 * There are 1,4,6 and 4,16,64 at indices 0,1,2 and 1,2,3. Return 2
	 * 
	 * Function Description
	 * 
	 * Complete the countTriplets function in the editor below.
	 * 
	 * countTriplets has the following parameter(s):
	 * 
	 * int arr[n]: an array of integers
	 * 
	 * int r: the common ratio
	 * 
	 * Returns
	 * 
	 * int: the number of triplets
	 * 
	 * Input Format
	 * 
	 * The first line contains two space-separated integers n and r, the size of arr and the common ratio. The next line
	 * contains n space-separated integers arr[i]
	 * 
	 * TODO: understand properly
	 */
	// Complete the countTriplets function below.
	public long countTriplets(List<Long> arr, long r) {

		Map<Long, Long> potential = new HashMap<>();
		Map<Long, Long> counter = new HashMap<>();
		long count = 0;
		for (int i = 0; i < arr.size(); i++) {
			long a = arr.get(i);
			long key = a / r;

			if (counter.containsKey(key) && a % r == 0) {
				count += counter.get(key);
			}

			if (potential.containsKey(key) && a % r == 0) {
				long c = potential.get(key);
				counter.put(a, counter.getOrDefault(a, 0L) + c);
			}

			potential.put(a, potential.getOrDefault(a, 0L) + 1); // Every number can be the start of a triplet.
		}
		return count;
	}

	/**
	 * Two strings are anagrams of each other if the letters of one string can be rearranged to form the other string.
	 * Given a string, find the number of pairs of substrings of the string that are anagrams of each other.
	 * 
	 * Example: s = mom
	 * 
	 * The list of all anagrammatic pairs m,m and mo,om is at positions 0,2 and 0,1 and 1,2 respectively.
	 * 
	 * Function Description
	 * 
	 * Complete the function sherlockAndAnagrams in the editor below.
	 */
	public int sherlockAndAnagrams(String s) {

		List<String> subStrings = new ArrayList<String>();

		int count = 0;

		// find all substrings
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j <= s.length(); j++) {
				String ss = s.substring(i, j);
				char[] chArr = ss.toCharArray();
				// subStrings.add(new String(chArr));

				Arrays.sort(chArr);
				String sortedStr = new String(chArr);
				subStrings.add(sortedStr);
			}
		}

		System.out.println(subStrings);
		Collections.sort(subStrings);
		System.out.println(subStrings);

		for (int i = 0; i < subStrings.size(); i++) {
			for (int j = i + 1; j < subStrings.size(); j++) {
				if (subStrings.get(i).equals(subStrings.get(j))) {
					count++;
				}
			}
		}

		return count;

	}

}
