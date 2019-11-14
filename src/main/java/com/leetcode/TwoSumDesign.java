package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * 
 * add - Add the number to an internal data structure.
 * 
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 * 
 * For example,
 * 
 * add(1); add(3); add(5);
 * 
 * find(4) -> true
 * 
 * find(7) -> false
 *
 */
public class TwoSumDesign {

	class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}
	}

	interface TwoSumI {
		void add(int num);

		Pair find(int sum);
	}

	/**
	 * This method is useful if the number of find operations far exceeds the number of add operations
	 * 
	 * @author surya
	 *
	 */
	public class TwoSum implements TwoSumI {

		private List<Integer> numbers = new ArrayList<>(); // space - O(n)
		private Map<Integer, Pair> numMap = new HashMap<>(); // space - O(n^2)

		@Override
		public void add(int num) {
			numbers.add(num);
			// break numbers into pairs
			for (int i = 0; i < numbers.size(); i++) {
				for (int j = 0; j < numbers.size(); j++) {
					if (i != j) {
						Pair p = new Pair(numbers.get(i), numbers.get(j));
						int sum = p.x + p.y;
						if (!numMap.containsKey(sum))
							numMap.put(sum, p);
					}
				}
			}
		}

		/**
		 * time - O(1)
		 */
		@Override
		public Pair find(int sum) {
			return numMap.get(sum);
		}

	}

	/**
	 * add � O(log n) runtime, find � O(n) runtime, O(n) space � Binary search + Two pointers:
	 * 
	 * @author surya
	 *
	 */
	public class TwoSum1 implements TwoSumI {

		private List<Integer> numbers = new ArrayList<>();

		@Override
		public void add(int num) {
			Collections.sort(numbers);
			numbers.add(num);
		}

		@Override
		public Pair find(int sum) {
			int l = 0;
			int r = numbers.size() - 1;
			while (l < r) {
				int s = numbers.get(l) + numbers.get(r);
				if (s > sum) {
					r--;
				} else if (s < sum) {
					l++;
				} else {
					return new Pair(numbers.get(l), numbers.get(r));
				}
			}
			return null;
		}

	}

	/**
	 * add � O(1) runtime, find � O(n) runtime, O(n) space � Store input in hash table: A simpler approach is to store
	 * each input into a hash table. To find if a pair sum exists, just iterate through the hash table in O(n) runtime.
	 * Make sure you are able to handle duplicates correctly.
	 * 
	 * @author surya
	 *
	 */
	public class TwoSum2 implements TwoSumI {

		private Map<Integer, Integer> map = new HashMap<>();

		@Override
		public void add(int num) {
			map.put(num, num);

		}

		@Override
		public Pair find(int sum) {
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {// O(n)
				if (map.containsKey(sum - entry.getKey())) {
					return new Pair(entry.getKey(), sum - entry.getKey());
				}
			}
			return null;
		}

	}

	public static void main(String[] args) {
		TwoSumDesign tsd = new TwoSumDesign();
		TwoSum2 ts = tsd.new TwoSum2();
		ts.add(2);
		ts.add(5);
		ts.add(10);
		System.out.println(ts.find(15));
	}

}
