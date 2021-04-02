package com.companyprep;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 * 
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 * 
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant. (Note that because the prison is a row, the first and the last cells in the row can't
 * have two adjacent neighbors.)
 * 
 * We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else
 * cells[i] == 0.
 * 
 * Given the initial state of the prison, return the state of the prison after N days (and N such changes described
 * above.)
 * 
 * Example 1:
 * 
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7 <br/>
 * Output: [0,0,1,1,0,0,0,0]
 * 
 * Explanation: The following table summarizes the state of the prison on each day: <br/>
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1] <br/>
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0] <br/>
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0] <br/>
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0] <br/>
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0] <br/>
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0] <br/>
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0] <br/>
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000 Output: [0,0,1,1,1,1,1,0]
 * 
 * 
 * Note:
 * 
 * cells.length == 8 cells[i] is in {0, 1} 1 <= N <= 10^9
 * 
 * Category : Hard
 * 
 *
 */
public class PrisonCellsAfterNDays {

	/**
	 * brute force. but this will cause time limit exceeded in LC if N is too high
	 * 
	 */
	public int[] prisonAfterNDays1(int[] cells, int N) {
		System.out.println("Input : " + Arrays.toString(cells));

		for (int d = 1; d <= N; d++) {
			int[] temp = new int[cells.length];

			for (int i = 1; i <= 6; i++) {
				if (cells[i - 1] == cells[i + 1]) {
					temp[i] = 1;
				} else {
					temp[i] = 0;
				}
			}
			temp[0] = 0;
			temp[7] = 0;
			System.out.println("cells on day :" + d + " " + Arrays.toString(temp));
			cells = temp;
		}

		return cells;
	}

	/**
	 * Optimized approach : A straight forward solution to this question is to generate state for each iteration for N
	 * times. However, this gets TLE when N becomes very large. But there is some way to optimize the time complexity
	 * such that we do not need to simulate each iteration.
	 * 
	 * There are 8 cells in total in the array, and each cell can have 2 states, 1 and 0. So the total number of
	 * possible conditions are fixed, which is 2^8. This means once the number N becomes very large, the pattern will
	 * begin to repeat itself. So we keep a seen HashMap mapping a state to the iteration index. Each time we meet a
	 * state that is in seen, we know that it occurred t steps before. Hence we could fast forward N to be N = N % t,
	 * here t = seen.get(state) - N.
	 * 
	 * Also note that the first and last position in the cells are always 0, so we only need to consider position 1 to
	 * 6.
	 * 
	 * Time complexity O(2^N) when N is small. O(N) when N is large.
	 * 
	 * Space complexity O(2^8)
	 */
	public int[] prisonAfterNDays2(int[] cells, int N) {
		Map<String, Integer> seen = new HashMap<>();

		while (N > 0) {
			int[] temp = new int[8];
			seen.put(Arrays.toString(cells), N);
			N--;
			for (int i = 1; i <= 6; i++) {
				if (cells[i - 1] == cells[i + 1]) {
					temp[i] = 1;
				}
			}
			cells = temp;
			if (seen.containsKey(Arrays.toString(cells))) {
				N = N % (seen.get(Arrays.toString(cells)) - N);
			}
		}

		return cells;
	}

	public static void main(String[] args) {
		PrisonCellsAfterNDays pr = new PrisonCellsAfterNDays();
		System.out.println("Output :" + Arrays.toString(pr.prisonAfterNDays1(new int[] { 1, 0, 0, 0, 0, 1, 0, 0 }, 1)));
		System.out.println();
		System.out.println("Output :" + Arrays.toString(pr.prisonAfterNDays2(new int[] { 1, 0, 0, 0, 0, 1, 0, 0 }, 1)));
		System.out.println();
		System.out.println("Output :" + Arrays.toString(pr.prisonAfterNDays1(new int[] { 1, 0, 0, 0, 0, 1, 0, 0 }, 7)));
		System.out.println();
		System.out.println("Output :" + Arrays.toString(pr.prisonAfterNDays2(new int[] { 1, 0, 0, 0, 0, 1, 0, 0 }, 7)));

	}

}
