package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given the default configuration and expected configuration of an application, merge these two configuration files,
 * the expected configuration can overwrite the default configuration, if there is no expected configuration, use the
 * default configuration (in fact, it is to merge two dictionaries); follow-up is how to deal with if the variables in
 * the configuration file can refer to each other, such as foo='123' bar=$foo baz=$bar, you need to write a program to
 * parse and generate the final configuration file.
 * 
 * The third round is to convert the problem into topological sorting. If a variable depends on other variables, create
 * an edge to which other variables point to it, first put the in-degree 0 into the queue, and then deal with the
 * elements that depend on the head of the queue one by one point. Of course, DFS can also be used.
 *
 * Really cow, this question can think of topological sorting!
 *
 */
public class MergeProperties {

	void merge(Map<String, String> map1, Map<String, String> map2) {
		for (Map.Entry<String, String> entry : map2.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			map1.put(key, value); // Just override or add
		}
		// Done with first question
	}

	void topSort(Map<String, String> map1) {
		// Topological Sort
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<String, Integer> degree = new HashMap<String, Integer>();
		Queue<String> queue = new LinkedList<String>();

		for (Map.Entry<String, String> entry : map1.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			if (value.startsWith("$")) {
				value = value.substring(1, value.length());

				if (!map.containsKey(value)) {
					map.put(value, new ArrayList<String>());
				}

				map.get(value).add(key);
				degree.put(key, degree.getOrDefault(key, 0) + 1);
			} else {
				degree.put(key, 0);
				queue.offer(key);
			}
		}

		while (!queue.isEmpty()) {
			String now = queue.poll();
			System.out.println(now + "=========" + map1.get(now));

			if (map.containsKey(now)) { //
				for (String next : map.get(now)) {
					map1.put(next, map1.get(now));

					degree.put(next, degree.get(next) - 1);

					if (degree.get(next) == 0) {
						queue.offer(next);
					}
				}
			}
		}
	}
}
