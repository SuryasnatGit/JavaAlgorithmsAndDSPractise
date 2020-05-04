package com.algo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://www.techiedelight.com/first-k-non-repeating-characters-string/
 *
 * T - O(n log n) S - O(n)
 */
public class FirstKNonRepeatCharsInString {

	public List<Character> firstKNonRepeatChars(String s, int k) {

		Map<Character, Pair> map = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			if (!map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), new Pair(1, i));
			} else {
				Pair pair = map.get(s.charAt(i));
				pair.setCount(pair.getCount() + 1);
				pair.setIndex(i);
			}
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (Map.Entry<Character, Pair> entry : map.entrySet()) {
			int count = entry.getValue().getCount();
			int index = entry.getValue().getIndex();
			if (count == 1) {
				pq.add(index);
			}
		}

		List<Character> res = new ArrayList<>();
		while (k-- > 0 && !pq.isEmpty()) {
			int index = pq.poll();
			res.add(s.charAt(index));
		}

		return res;
	}

	public static void main(String[] args) {
		FirstKNonRepeatCharsInString fi = new FirstKNonRepeatCharsInString();
		System.out.println(fi.firstKNonRepeatChars("ABCDBAGHCHFAC", 3));
	}
}

class Pair {

	int count;
	int index;

	public Pair(int c, int i) {
		this.count = c;
		this.index = i;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
