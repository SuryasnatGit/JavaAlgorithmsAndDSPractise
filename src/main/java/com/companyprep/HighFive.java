package com.companyprep;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * There are two properties in the node student id and scores, to ensure that each student will have at least 5 points,
 * find the average of 5 highest scores for each person. Given results =
 * [[1,91],[1,92],[2,93],[2,99],[2,98],[2,97],[1,60],[1,58],[2,100],[1,61]]
 * 
 * Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score. The average score is
 * calculated using integer division.
 * 
 * @author surya
 *
 */
public class HighFive {

	public Map<Integer, Double> average(List<Record> records, int k) {
		Map<Integer, Double> result = new HashMap<>();
		Map<Integer, PriorityQueue<Record>> highest = new HashMap<>();

		for (Record record : records) {
			int id = record.id;
			if (!highest.containsKey(id)) {
				PriorityQueue<Record> pq = new PriorityQueue<Record>((a, b) -> b.score - a.score);
				highest.put(id, pq);
			}
			highest.get(id).add(record);
			if (highest.get(id).size() > k) {
				highest.get(id).poll();
			}
		}

		for (Map.Entry<Integer, PriorityQueue<Record>> entry : highest.entrySet()) {
			int id = entry.getKey();
			int sum = 0;
			PriorityQueue<Record> value = entry.getValue();
			while (!value.isEmpty()) {
				sum += value.poll().score;
			}
			double avg = sum / k;
			result.put(id, avg);
		}

		return result;
	}

	public static void main(String[] args) {
		HighFive hf = new HighFive();

		List<Record> records = Arrays.asList(new Record(1, 91), new Record(1, 92), new Record(2, 93), new Record(2, 99),
				new Record(2, 98), new Record(2, 97), new Record(1, 60), new Record(1, 58), new Record(2, 100),
				new Record(1, 61));
		System.out.println(hf.average(records, 3));
	}
}

class Record {
	int id;
	int score;

	public Record(int id, int score) {
		this.id = id;
		this.score = score;
	}
}