package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Category : Hard
 * 
 * CTCI : 16.8
 *
 */
public class WordsNumberConverter {

	HashMap<Integer, String> map = new HashMap<Integer, String>();

	public String numberToWords(int num) {
		fillMap();
		StringBuilder sb = new StringBuilder();

		if (num == 0) {
			return map.get(0);
		}

		if (num >= 1000000000) {
			int extra = num / 1000000000;
			sb.append(convert(extra) + " Billion");
			num = num % 1000000000;
		}

		if (num >= 1000000) {
			int extra = num / 1000000;
			sb.append(convert(extra) + " Million");
			num = num % 1000000;
		}

		if (num >= 1000) {
			int extra = num / 1000;
			sb.append(convert(extra) + " Thousand");
			num = num % 1000;
		}

		if (num > 0) {
			sb.append(convert(num));
		}

		return sb.toString().trim();
	}

	public String convert(int num) {

		StringBuilder sb = new StringBuilder();

		if (num >= 100) {
			int numHundred = num / 100;
			sb.append(" " + map.get(numHundred) + " Hundred");
			num = num % 100;
		}

		if (num > 0) {
			if (num > 0 && num <= 20) {
				sb.append(" " + map.get(num));
			} else {
				int numTen = num / 10;
				sb.append(" " + map.get(numTen * 10));

				int numOne = num % 10;
				if (numOne > 0) {
					sb.append(" " + map.get(numOne));
				}
			}
		}

		return sb.toString();
	}

	public void fillMap() {
		map.put(0, "Zero");
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
		map.put(4, "Four");
		map.put(5, "Five");
		map.put(6, "Six");
		map.put(7, "Seven");
		map.put(8, "Eight");
		map.put(9, "Nine");
		map.put(10, "Ten");
		map.put(11, "Eleven");
		map.put(12, "Twelve");
		map.put(13, "Thirteen");
		map.put(14, "Fourteen");
		map.put(15, "Fifteen");
		map.put(16, "Sixteen");
		map.put(17, "Seventeen");
		map.put(18, "Eighteen");
		map.put(19, "Nineteen");
		map.put(20, "Twenty");
		map.put(30, "Thirty");
		map.put(40, "Forty");
		map.put(50, "Fifty");
		map.put(60, "Sixty");
		map.put(70, "Seventy");
		map.put(80, "Eighty");
		map.put(90, "Ninety");
	}

	public String withSeparator(long number) {
		if (number < 0) {
			return "-" + withSeparator(-number);
		}
		if (number / 1000L > 0) {
			return withSeparator(number / 1000L) + "," + String.format("%1$03d", number % 1000L);
		} else {
			return String.format("%1$d", number);
		}
	}

	public long wordsToNumber(String text) throws Exception {
		text = text.toLowerCase().replaceAll("[\\-,]", " ").replaceAll(" and ", " ");
		long totalValue = 0;
		boolean processed = false;
		Map<String, Long> map = getBigNumMap();
		for (Entry<String, Long> set : map.entrySet()) {
			int index = text.indexOf(set.getKey());
			if (index >= 0) {
				String text1 = text.substring(0, index).trim();
				String text2 = text.substring(index + set.getKey().length()).trim();

				if (text1.equals(""))
					text1 = "one";

				if (text2.equals(""))
					text2 = "zero";

				totalValue = parseNumerals(text1) * set.getValue() + wordsToNumber(text2);
				processed = true;
				break;
			}
		}

		if (processed)
			return totalValue;
		else
			return parseNumerals(text);
	}

	private Map<String, Long> getBigNumMap() {
		Map<String, Long> bigNumMap = new HashMap<>();
		bigNumMap.put("trillion", 1000000000000L);
		bigNumMap.put("billion", 1000000000L);
		bigNumMap.put("million", 1000000L);
		bigNumMap.put("thousand", 1000L);
		return bigNumMap;
	}

	public long parseNumerals(String text) throws Exception {
		long value = 0;
		String[] words = text.replaceAll(" and ", " ").split("\\s");
		for (String word : words) {
			if (!getSmallNumMap().containsKey(word)) {
				throw new Exception("Unknown token : " + word);
			}

			long subval = getValueOf(word);
			if (subval == 100) {
				if (value == 0)
					value = 100;
				else
					value *= 100;
			} else
				value += subval;
		}

		return value;
	}

	private long getValueOf(String word) {
		return getSmallNumMap().get(word);
	}

	private Map<String, Integer> getSmallNumMap() {
		Map<String, Integer> smallNumMap = new HashMap<>();
		smallNumMap.put("zero", 0);
		smallNumMap.put("one", 1);
		smallNumMap.put("two", 2);
		smallNumMap.put("three", 3);
		smallNumMap.put("four", 4);
		smallNumMap.put("five", 5);
		smallNumMap.put("six", 6);
		smallNumMap.put("seven", 7);
		smallNumMap.put("eight", 8);
		smallNumMap.put("nine", 9);
		smallNumMap.put("ten", 10);
		smallNumMap.put("eleven", 11);
		smallNumMap.put("twelve", 12);
		smallNumMap.put("thirteen", 13);
		smallNumMap.put("fourteen", 14);
		smallNumMap.put("fifteen", 15);
		smallNumMap.put("sixteen", 16);
		smallNumMap.put("seventeen", 17);
		smallNumMap.put("eighteen", 18);
		smallNumMap.put("nineteen", 19);
		smallNumMap.put("twenty", 20);
		smallNumMap.put("thirty", 30);
		smallNumMap.put("fourty", 40);
		smallNumMap.put("fifty", 50);
		smallNumMap.put("sixty", 60);
		smallNumMap.put("seventy", 70);
		smallNumMap.put("eighty", 80);
		smallNumMap.put("ninety", 90);
		smallNumMap.put("hundred", 100);
		return smallNumMap;
	}

	public static void main(String[] args) throws Exception {
		WordsNumberConverter w = new WordsNumberConverter();

		System.out.println("Value : " + w.wordsToNumber("five thousand nine hundred eighty four"));

		System.out.println(w.numberToWords(5984));
		System.out.println(w.numberToWords(2005984));
		System.out.println(w.numberToWords(2001005984));
	}
}
