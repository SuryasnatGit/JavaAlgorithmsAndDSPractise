package com.companyprep;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Each year, employees of an organization are rated based on their performance. The employees are then ranked based on
 * the ratings. Enployees with the same ratings will have the same rankings, but the next employee with the next lowest
 * score will be rated based on the position within the list of all rankings. Employees below the cutoff rank are placed
 * in a layoff list. Give the ratings each employee receives and the cutoff rank, return the number of employees who are
 * not in the layoff list.
 * 
 * The input to the function/method consists of three arguments:
 * 
 * cutOffRank, an integer representing the cutoff rank;<br/>
 * num, an integer representing the total number of scores;<br/>
 * scores, a list of integers representing the scores of the employees.<br/>
 * 
 * Return an integer representing the number of employees who are not in the layoff list.
 * 
 * Constraints :
 * 
 * 1 <= num <= 10^5<br/>
 * 0 <= scores[i] <= 100<br/>
 * 0 <= i < num<br/>
 * cutOffRank <= num<br/>
 * 
 * Example 1:
 * 
 * Input:
 * 
 * cutoffrank = 2
 * 
 * num = 5
 * 
 * scores = [100, 90, 80, 70, 60]
 * 
 * Output: 2
 * 
 * Explanation: The employee rankings are [1, 2, 3, 4, 5]. And with a cutoff rank of two, only the first 2 employees are
 * not in the layoff list.
 * 
 * Example 2:
 * 
 * Input:
 * 
 * cutoffrank = 4
 * 
 * num = 5
 * 
 * scores = [100, 100, 80, 70, 60]
 * 
 * Output: 4
 * 
 * Explanation: The first two employees have equal rating and both receives a ranking of 1. Employee with score 80 has 2
 * employees in front of him so he receives a ranking of 3. The final employee rankings are [1, 1, 3, 4, 5]. With a
 * cutoff rank of 4, the first 4 employees are out of the layoff list.
 * 
 * Example 3:
 * 
 * Input:
 * 
 * cutOffRank = 3
 * 
 * num= 4
 * 
 * scores=[100, 50, 50, 25]
 * 
 * Output: 3
 * 
 * Explanation: There are num= 4 employees, where the cutOffRank is 3 and scores = [100, 50,50, 25]. These employees'
 * ranks are [1, 2, 2, 4]. Because the players need to have a rank of at least 3, return first three employees. So, the
 * output is 3.
 * 
 * Example 4:
 * 
 * Input:
 * 
 * cutOffRank = 4
 * 
 * num=5
 * 
 * scores=[2,2,3,4,5]
 * 
 * Output: 5
 * 
 * Explanation: In order, the players achieve the ranks [4,4,3,2,1]. Since the cutOffRank is 4, all 5 employees will be
 * left. So, the output is 5
 * 
 * Category : Medium
 *
 */
public class CutoffRanks {

	/**
	 * Brute force approach. T - O(n) + O(n log n) S - O(n)
	 * 
	 * 
	 */
	public int cutOffRank1(int cutOffRank, int num, int[] scores) {

		// System.out.println(Arrays.toString(scores));

		// map of score in descending order to count of players having that score
		SortedMap<Integer, Integer> scoreCountMap = new TreeMap<>((a, b) -> b - a);

		for (int i = 0; i < num; i++) {
			scoreCountMap.put(scores[i], scoreCountMap.getOrDefault(scores[i], 0) + 1);
		}

		// System.out.println(scoreCountMap);

		int count = 0;
		for (Map.Entry<Integer, Integer> scoreCountMapEntry : scoreCountMap.entrySet()) {
			if (cutOffRank > 0 && scoreCountMapEntry.getKey() > 0) {
				count += scoreCountMapEntry.getValue();
			}
			cutOffRank -= scoreCountMapEntry.getValue();
		}

		return count;

	}

	/**
	 * Efficient approach. T - O(n) S - O(1)
	 * 
	 */
	public int cutOffRank2(int cutOffRank, int num, int[] scores) {
		int[] ranks = new int[100 + 1]; // equal to scores

		for (int i = 0; i < num; i++) {
			ranks[scores[i]]++;
		}

		int rank = 1, count = 0;
		for (int score = 100; score >= 0; score--) {
			if (rank > cutOffRank) {
				break;
			}
			count += ranks[score];
			rank += ranks[score];
		}

		return count;
	}

	public static void main(String[] args) {
		CutoffRanks cr = new CutoffRanks();

		System.out.println(cr.cutOffRank1(2, 5, new int[] { 100, 90, 80, 70, 60 })); // 2
		System.out.println(cr.cutOffRank1(4, 5, new int[] { 100, 100, 80, 70, 60 })); // 4
		System.out.println(cr.cutOffRank1(3, 4, new int[] { 100, 50, 25, 50 })); // 3
		System.out.println(cr.cutOffRank1(4, 5, new int[] { 2, 2, 3, 4, 5 })); // 5
		System.out.println();
		System.out.println(cr.cutOffRank2(2, 5, new int[] { 100, 90, 80, 70, 60 })); // 2
		System.out.println(cr.cutOffRank2(4, 5, new int[] { 100, 100, 80, 70, 60 })); // 4
		System.out.println(cr.cutOffRank2(3, 4, new int[] { 100, 50, 25, 50 })); // 3
		System.out.println(cr.cutOffRank2(4, 5, new int[] { 2, 2, 3, 4, 5 })); // 5

	}
}
