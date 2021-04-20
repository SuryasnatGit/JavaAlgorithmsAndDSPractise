package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * The person facing me is a Westerner eldest brother. This round of test data structure design, time travel table.
 * Interfaces, legal input values, return values, etc. have to be discussed with the interviewer. But the specific
 * functions are as follows:
 * 
 * Input: "key1" time1 "val1" // Insert a record <br/>
 * Input: "Key1" time2 "val2" // Insert a record<br/>
 * Input: "key1" time2 "val3" // Change the value of the time2 record of key1 from val2 to val3 <br/>
 * Input: "key1" time6 "val4" // Insert a record <br/>
 * Input: "key1" time1 // Take out the record, val1 <br/>
 * Input: "key1" time2 // Take out the record, val3 <br/>
 * Input: "key1" time4 // Take out the record, val3, take out the value of the first timestamp that is less than the
 * current time <br/>
 * Input: "key1" time0 // Take a non-existent timestamp less than or equal to the input value, and return null.
 * 
 * I used hashmap+treeMap to solve the problem.
 * 
 */
public class TimeTravelTable {

	public static void main(String[] args) {

	}

	// Key, Timestamp, Value
	Map<String, TreeMap<Integer, String>> map = new HashMap<String, TreeMap<Integer, String>>();

	void put(String key, int timestamp, String value) {
		if (map.containsKey(key)) {
			TreeMap<Integer, String> treeMap = map.get(key);
			treeMap.put(timestamp, value);
		} else {
			TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();
			treeMap.put(timestamp, value);
			map.put(key, treeMap);
		}
	}

	String get(String key, int timestamp) {
		if (!map.containsKey(key)) {
			return null;
		}

		TreeMap<Integer, String> treeMap = map.get(key);
		Entry<Integer, String> floorEntry = treeMap.floorEntry(timestamp);

		return floorEntry.getValue();
	}
}