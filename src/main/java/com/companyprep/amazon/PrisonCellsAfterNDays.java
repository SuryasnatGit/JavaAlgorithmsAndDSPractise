package com.companyprep.amazon;

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
 * 
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
 * 
 * TODO : to check and complete
 *
 */
public class PrisonCellsAfterNDays {

	/**
	 * We simulate each day of the prison. Because there are at most 256 possible states for the prison, eventually the
	 * states repeat into a cycle rather quickly. We can keep track of when the states repeat to find the period t of
	 * this cycle, and skip days in multiples of t.
	 * 
	 * Algorithm
	 * 
	 * Let's do a naive simulation, iterating one day at a time. For each day, we will decrement N, the number of days
	 * remaining, and transform the state of the prison forward (state -> nextDay(state)). If we reach a state we have
	 * seen before, we know how many days ago it occurred, say t. Then, because of this cycle, we can do N %= t. This
	 * ensures that our algorithm only needs O(2**{\text{cells.length}})O(2∗∗cells.length) steps. Time Complexity:
	 * O(2^N)O(2 N ), where NN is the number of cells in the prison. Space Complexity: O(2^N * N)O(2 N ∗N).
	 * 
	 * @param cells
	 * @param N
	 * @return
	 */
	public int[] prisonAfterNDays(int[] cells, int N) {
		Map<Integer, Integer> seen = new HashMap();

		// state = integer representing state of prison
		int state = 0;
		for (int i = 0; i < 8; ++i) {
			if (cells[i] > 0)
				state ^= 1 << i;
		}

		// While days remaining, simulate a day
		while (N > 0) {
			// If this is a cycle, fast forward by
			// seen.get(state) - N, the period of the cycle.
			if (seen.containsKey(state)) {
				N %= seen.get(state) - N;
			}
			seen.put(state, N);

			if (N >= 1) {
				N--;
				state = nextDay(state);
			}
		}

		// Convert the state back to the required answer.
		int[] ans = new int[8];
		for (int i = 0; i < 8; ++i) {
			if (((state >> i) & 1) > 0) {
				ans[i] = 1;
			}
		}

		return ans;
	}

	public int nextDay(int state) {
		int ans = 0;

		// We only loop from 1 to 6 because 0 and 7 are impossible,
		// as those cells only have one neighbor.
		for (int i = 1; i <= 6; ++i) {
			if (((state >> (i - 1)) & 1) == ((state >> (i + 1)) & 1)) {
				ans ^= 1 << i;
			}
		}

		return ans;

	}

	// SOLUTION 2
	public int[] prisonAfterNDays1(int[] cells, int N) {
		int[] ocells = new int[cells.length];
		int[] fcells = new int[cells.length];
		for (int i = 1; i <= 6; i++) {
			ocells[i] = 1 - cells[i - 1] ^ cells[i + 1];
			fcells[i] = ocells[i];
		}
		int[] ccells = new int[cells.length];
		int cnt = 1;
		while (true) {
			for (int i = 1; i <= 6; i++)
				ccells[i] = 1 - fcells[i - 1] ^ fcells[i + 1];
			for (int i = 1; i <= 6; i++)
				fcells[i] = ccells[i];
			boolean isSame = true;
			for (int i = 1; i <= 6; i++) {
				if (ccells[i] != ocells[i]) {
					isSame = false;
					break;
				}
			}
			if (isSame)
				break;
			cnt++;
		}
		int n = (N - 1) % cnt;
		while (n-- > 0) {
			for (int i = 1; i <= 6; i++)
				ccells[i] = 1 - fcells[i - 1] ^ fcells[i + 1];
			for (int i = 1; i <= 6; i++)
				fcells[i] = ccells[i];
		}
		return fcells;

	}

	public static void main(String[] args) {
		PrisonCellsAfterNDays pr = new PrisonCellsAfterNDays();
		System.out.println(Arrays.toString(pr.prisonAfterNDays(new int[] { 1, 0, 0, 0, 0, 1, 0, 0 }, 1)));
		System.out.println(Arrays.toString(pr.prisonAfterNDays1(new int[] { 1, 0, 0, 0, 0, 1, 0, 0 }, 1)));
		System.out.println(Arrays.toString(pr.prisonAfterNDays(new int[] { 1, 1, 1, 0, 1, 1, 1, 1 }, 2)));
		System.out.println(Arrays.toString(pr.prisonAfterNDays1(new int[] { 1, 1, 1, 0, 1, 1, 1, 1 }, 2)));
	}

}
