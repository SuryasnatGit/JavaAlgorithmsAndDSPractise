package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WordsToNumberConverter {

	public void wordsToNumber(String[] args) {

	}

	public static String numberToWords(long num, String s) {
		int len = String.valueOf(num).length();
		if (len >= 4) {// for numbers >= thousand
			for (Entry<String, Long> entry : getBigNumMap().entrySet()) {
				int div = (int) (num / entry.getValue());
				if (div > 0) {
					for (Entry<String, Integer> smallNumEntry : getSmallNumMap().entrySet()) {
						if (smallNumEntry.getValue().intValue() == div)
							s += " " + smallNumEntry.getKey();
					}
					s += " " + entry.getKey();
					return numberToWords(num % entry.getValue(), s);
				}
			}
		} else { // for numbers < 1000
			int div = (int) num / 100;
			if (div > 0) {
				for (Entry<String, Integer> entry : getSmallNumMap().entrySet()) {
					if (entry.getValue().intValue() == div)
						s += " " + entry.getKey();
					s += " " + "hundred";
					return numberToWords(num % 100, s);
				}
			}

			div = (int) num / 10;
			if (div > 0) {
				for (Entry<String, Integer> entry : getSmallNumMap().entrySet()) {
					if (entry.getValue().intValue() == (div * 10)) {
						s += " " + entry.getKey();
						return numberToWords(num % 10, s);
					}
				}
			}
			div = (int) num / 1;
			for (Entry<String, Integer> entry : getSmallNumMap().entrySet()) {
				if (entry.getValue().intValue() == div)
					s += " " + entry.getKey();
			}
		}
		return s;
	}

	public static String withSeparator(long number) {
		if (number < 0) {
			return "-" + withSeparator(-number);
		}
		if (number / 1000L > 0) {
			return withSeparator(number / 1000L) + "," + String.format("%1$03d", number % 1000L);
		} else {
			return String.format("%1$d", number);
		}
	}

	public static long parseNumerals(String text) throws Exception {
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

	private static long getValueOf(String word) {
		return getSmallNumMap().get(word);
	}

	private static Map<String, Integer> getSmallNumMap() {
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

	private static Map<String, Long> getBigNumMap() {
		Map<String, Long> bigNumMap = new HashMap<>();
		bigNumMap.put("trillion", 1000000000000L);
		bigNumMap.put("billion", 1000000000L);
		bigNumMap.put("million", 1000000L);
		bigNumMap.put("thousand", 1000L);
		return bigNumMap;
	}

	public static long parse(String text) throws Exception {
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

				totalValue = parseNumerals(text1) * set.getValue() + parse(text2);
				processed = true;
				break;
			}
		}

		if (processed)
			return totalValue;
		else
			return parseNumerals(text);
	}

	public static void main(String[] args) throws Exception {
		// while (true) {
		// Scanner in = new Scanner(System.in);
		// System.out.print("Number in words : ");
		// String numberWordsText = in.nextLine();
		// if (numberWordsText.equalsIgnoreCase("bye")) {
		// break;
		// }
		// // System.out.println(
		// // "Value : " +
		// // WordsToNumberConverter.withSeparator(WordsToNumberConverter.parse(numberWordsText)));
		// System.out.println("Value : " + WordsToNumberConverter.parse(numberWordsText));
		// }
		System.out.println(WordsToNumberConverter.numberToWords(5984, ""));
		System.out.println(WordsToNumberConverter.numberToWords(2005984, ""));
		System.out.println(WordsToNumberConverter.numberToWords(2001005984, ""));
	}
}
