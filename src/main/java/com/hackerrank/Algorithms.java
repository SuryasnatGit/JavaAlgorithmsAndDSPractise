package com.hackerrank;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
		System.out.println(algo.birthdayBar(Arrays.asList(1, 2, 1, 3, 2), 3, 2));
		System.out.println(algo.birthdayBar(Arrays.asList(1, 1, 1, 1, 1, 1), 3, 2));
		System.out.println(algo.birthdayBar(Arrays.asList(4), 4, 1));
		int[] input = new int[] { 7, -5, -3, -3, 0, 4, -5, -5, 0, 7, 7 };
		System.out.println(algo.rearrangeDuplicates(input));
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
	 * John works at a clothing store. He has a large pile of socks that he must pair by color for sale.
	 * Given an array of integers representing the color of each sock, determine how many pairs of socks
	 * with matching colors there are.
	 * 
	 * For example, there are socks with colors . There is one pair of color and one of color . There
	 * are three odd socks left, one of each color. The number of pairs is .
	 * 
	 * Function Description
	 * 
	 * Complete the sockMerchant function in the editor below. It must return an integer representing
	 * the number of matching pairs of socks that are available.
	 * 
	 * sockMerchant has the following parameter(s):
	 * 
	 * n: the number of socks in the pile ar: the colors of each sock
	 * 
	 * Input Format
	 * 
	 * The first line contains an integer , the number of socks represented in . The second line
	 * contains space-separated integers describing the colors of the socks in the pile.
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
	 * Lily has a chocolate bar that she wants to share it with Ron for his birthday. Each of the
	 * squares has an integer on it. She decides to share a contiguous segment of the bar selected such
	 * that the length of the segment matches Ron's birth month and the sum of the integers on the
	 * squares is equal to his birth day. You must determine how many ways she can divide the chocolate.
	 * 
	 * Consider the chocolate bar as an array of squares, . She wants to find segments summing to Ron's
	 * birth day, with a length equaling his birth month, . In this case, there are two segments meeting
	 * her criteria: and .
	 * 
	 * Function Description
	 * 
	 * Complete the birthday function in the editor below. It should return an integer denoting the
	 * number of ways Lily can divide the chocolate bar.
	 * 
	 * birthday has the following parameter(s):
	 * 
	 * s: an array of integers, the numbers on each of the squares of chocolate d: an integer, Ron's
	 * birth day m: an integer, Ron's birth month Input Format
	 * 
	 * The first line contains an integer , the number of squares in the chocolate bar. The second line
	 * contains space-separated integers , the numbers on the chocolate squares where . The third line
	 * contains two space-separated integers, and , Ron's birth day and his birth month.
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

}
