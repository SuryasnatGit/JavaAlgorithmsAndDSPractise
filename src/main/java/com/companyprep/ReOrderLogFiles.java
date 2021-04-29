package com.companyprep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Same as https://leetcode.com/problems/reorder-log-files/
 * 
 * You have an array of logs. Each log is a space delimited string of words.
 * 
 * For each log, the first word in each log is an alphanumeric identifier. Then, either:
 * 
 * Each word after the identifier will consist only of lowercase letters, or; Each word after the identifier will
 * consist only of digits. We will call these two varieties of logs letter-logs and digit-logs. It is guaranteed that
 * each log has at least one word after its identifier.
 * 
 * Reorder the logs so that all of the letter-logs come before any digit-log. The letter-logs are ordered
 * lexicographically ignoring identifier, with the identifier used in case of ties. The digit-logs should be put in
 * their original order.
 * 
 * Return the final order of the logs.
 * 
 * Example 1:
 * 
 * Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
 * 
 * Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 * 
 * Note:
 * 
 * 0 <= logs.length <= 100
 * 
 * 3 <= logs[i].length <= 100
 * 
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 *
 * TAGS : Amazon
 */
public class ReOrderLogFiles {

	public static void main(String[] args) {
		ReOrderLogFiles ap = new ReOrderLogFiles();
		List<String> orderList = new ArrayList<String>();
		orderList.add("1a2b apple iphone");
		orderList.add("12bc 123 567");
		orderList.add("1b2c apple ipad");
		orderList.add("1c2d 987 235");
		List<String> prioritizedOrders = ap.prioritizedOrders(4, orderList);

		prioritizedOrders.forEach(o -> System.out.println(o));

		List<String> res = ap.reorderLogFiles(orderList);
		System.out.println(res);
	}

	// Solution 1
	/**
	 * Time complexity - O(m * n) + k log k where m = number of strings, n = number of words in a string, k = number of
	 * prime orders
	 * 
	 * @param numOrders
	 * @param orderList
	 * @return
	 */
	public List<String> prioritizedOrders(int numOrders, List<String> orderList) {

		List<String> nonprimeOrders = new ArrayList<String>();
		List<Order> list = new ArrayList<Order>();

		for (String order : orderList) {

			List<String> primekeywords = new ArrayList<String>();

			String[] split = order.split(" ");
			String identifier = split[0]; // identifier
			boolean isNum = false;

			for (int i = 1; i < split.length; i++) {
				String s = split[i];
				if (!isNumStr(s))
					primekeywords.add(s);
				else {
					nonprimeOrders.add(order);
					isNum = true;
					break;
				}
			}

			if (!isNum) {
				Order o = new Order(identifier, primekeywords);
				list.add(o);
			}
		}

		Collections.sort(list, new OrderSort());
		List<String> res = formResult(list);
		res.addAll(nonprimeOrders);

		return res;
	}

	private List<String> formResult(List<Order> list) {
		List<String> res = new ArrayList<>();
		for (Order order : list) {
			String s = order.identifier + " ";
			for (String keyword : order.keywords) {
				s += keyword + " ";
			}
			res.add(s);
		}
		return res;

	}

	private boolean isNumStr(String s) {
		for (char ch : s.toCharArray()) {
			if (Character.isLetter(ch))
				return false;
		}
		return true;
	}

	class OrderSort implements Comparator<Order> {
		@Override
		public int compare(Order o1, Order o2) {

			String comp = listCompare(o1.keywords, o2.keywords);
			if (comp.equals("less"))
				return -1;
			else if (comp.equals("more"))
				return 1;
			else if (comp.equals("equal")) {
				return o1.identifier.compareTo(o2.identifier);
			}

			return 0;
		}

		private String listCompare(List<String> l1, List<String> l2) {
			int i = 0;
			int j = 0;
			while (i < l1.size() && j < l2.size()) {
				String s1 = l1.get(i);
				String s2 = l2.get(j);
				if (s1.compareTo(s2) < 0) {
					return "less";
				} else if (s1.compareTo(s2) > 0) {
					return "more";
				}
				i++;
				j++;
			}

			while (i < l1.size()) {
				return "more";
			}

			while (j < l2.size()) {
				return "less";
			}

			return "equal";
		}
	}

	class Order {

		private String identifier;
		private List<String> keywords;

		public Order(String id, List<String> keywords) {
			this.identifier = id;
			this.keywords = keywords;
		}
	}

	// Solution 2 - Using Priority Queue. T - O(n) + O(n log n) S - O(n)
	public List<String> reorderLogFiles(List<String> logLines) {
		if (logLines.size() < 2)
			return logLines;

		Queue<String> numbers = new LinkedList<String>();

		PriorityQueue<String> letters = new PriorityQueue<String>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				int ind1 = o1.indexOf(" ");
				int ind2 = o2.indexOf(" ");
				int val = o1.substring(ind1 + 1).compareTo(o2.substring(ind2 + 1));
				if (val != 0) {
					return val;
				} else {
					return o1.substring(0, ind1).compareTo(o2.substring(0, ind2));
				}
			}
		});

		for (String s : logLines) {
			int ind = s.indexOf(" ");
			if (Character.isDigit(s.charAt(ind + 1))) {
				numbers.offer(s);
			} else {
				letters.offer(s);
			}
		}

		List<String> result = new ArrayList<>();
		while (!letters.isEmpty()) {
			result.add(letters.poll());
		}
		while (!numbers.isEmpty()) {
			result.add(numbers.poll());
		}

		return result;
	}
}
