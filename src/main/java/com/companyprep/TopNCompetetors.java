package com.companyprep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// TODO: fix all edge cases.
public class TopNCompetetors {

	public ArrayList<String> topNCompetetors(int numCompetitors, int topNCompetitors, List<String> competitors,
			int numReviews, List<String> reviews) {

		Set<String> set = new HashSet<>(competitors);
		System.out.println("set :" + set);

		Map<String, Integer> compCount = new HashMap<>();

		for (String review : reviews) {
			String[] split = review.split(" ");
			String key = "";
			boolean found = false;
			for (String s : split) {
				if (!Character.isAlphabetic(s.charAt(0))) {
					s = s.substring(1);
				}
				if (!Character.isAlphabetic(s.charAt(s.length() - 1))) {
					s = s.substring(0, s.length() - 2);
				}
				s = s.toLowerCase();
				if (set.contains(s)) {
					found = true;
					key = s;
					// compCount.put(s, compCount.containsKey(s) ? compCount.get(s) + 1 : 1);
				}
			}
			if (found) {
				compCount.put(key, compCount.containsKey(key) ? compCount.get(key) + 1 : 1);
			}
		}

		System.out.println("compCount map :" + compCount);

		PriorityQueue<Competetor> pq = new PriorityQueue<>(new Comparator<Competetor>() {

			@Override
			public int compare(Competetor o1, Competetor o2) {
				if (o2.count > o1.count)
					return o2.count - o1.count;
				else if (o2.count == o1.count)
					return o1.name.compareTo(o2.name);
				else
					return 0;
			}
		});

		for (Map.Entry<String, Integer> entry : compCount.entrySet()) {
			pq.add(new Competetor(entry.getKey(), entry.getValue()));
		}

		System.out.println("pq :" + pq);

		ArrayList<String> result = new ArrayList<>();
		while (!pq.isEmpty() && topNCompetitors-- > 0) {
			result.add(pq.poll().name);
		}

		return result;

	}

	class Competetor {
		String name;
		int count;
		boolean foundOnce;

		public Competetor(String name, int count) {
			this.name = name;
			this.count = count;
			this.foundOnce = false;
		}

		@Override
		public String toString() {
			return "Competetor [name=" + name + ", count=" + count + "]";
		}
	}

	public static void main(String[] args) {
		TopNCompetetors top = new TopNCompetetors();
		List<String> competetors = Arrays.asList("newshop", "shopnow", "afashion", "fashionbeats", "bymarket",
				"tcellular");

		List<String> reviews = Arrays.asList("newshop is: everyone newshop", "best service newshop and fashionbeats",
				"fashionbeats great value", "proud fashionbeats", "bymarket awesome", "thanks newshop",
				"bymarket great");
		System.out.println(top.topNCompetetors(6, 2, competetors, 6, reviews));
	}
}
