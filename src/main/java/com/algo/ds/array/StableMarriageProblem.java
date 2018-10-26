package com.algo.ds.array;

/**
 * The Stable Marriage Problem states that given N men and N women, where each person has ranked all
 * members of the opposite sex in order of preference, marry the men and women together such that
 * there are no two people of opposite sex who would both rather have each other than their current
 * partners. If there are no such people, all the marriages are “stable”.
 * 
 * It is always possible to form stable marriages from lists of preferences (See references for
 * proof). Following is Gale–Shapley algorithm to find a stable matching: The idea is to iterate
 * through all free men while there is any free man available. Every free man goes to all women in
 * his preference list according to the order. For every woman he goes to, he checks if the woman is
 * free, if yes, they both become engaged. If the woman is not free, then the woman chooses either
 * says no to him or dumps her current engagement according to her preference list. So an engagement
 * done once can be broken if a woman gets better option.
 * 
 * Time Complexity of Gale-Shapley Algorithm is O(n2) where n is the number of men or women.
 * 
 * The Gale–Shapley algorithm involves a number of "rounds" (or "iterations"):
 * 
 * In the first round, first each unengaged man proposes to the woman he prefers most, and then each
 * woman replies "maybe" to her suitor she most prefers and "no" to all other suitors. She is then
 * provisionally "engaged" to the suitor she most prefers so far, and that suitor is likewise
 * provisionally engaged to her.
 * 
 * In each subsequent round, first a) each unengaged man proposes to the most-preferred woman to
 * whom he has not yet proposed (regardless of whether the woman is already engaged), and then b)
 * each woman replies "maybe" if she is currently not engaged or if she prefers this man over her
 * current provisional partner (in this case, she rejects her current provisional partner who
 * becomes unengaged). The provisional nature of engagements preserves the right of an
 * already-engaged woman to "trade up" (and, in the process, to "jilt" her until-then partner).
 * 
 * This process is repeated until everyone is engaged.
 * 
 * Algorithm:
 * 
 * Initialize all men and women to free while there exist a free man m who still has a woman w to
 * propose to { w = m's highest ranked such woman to whom he has not yet proposed if w is free (m,
 * w) become engaged else some pair (m', w) already exists if w prefers m to m' (m, w) become
 * engaged m' becomes free else (m', w) remain engaged }
 * 
 * 
 * @author surya
 *
 */
public class StableMarriageProblem {

	private boolean checkIfNewIsBetter(int priority[][], int bride, int currentGroom, int suitor) {
		for (int groom : priority[bride]) {
			if (currentGroom == groom) {
				return false;
			}
			if (suitor == groom) {
				return true;
			}
		}
		return false;
	}

	public int[] findPair(int[][] priority) {
		int pair = priority[0].length;
		int groomToBride[] = new int[pair];
		int brideToGroom[] = new int[pair];
		for (int i = 0; i < groomToBride.length; i++) {
			groomToBride[i] = -1; // initialize
		}
		for (int i = 0; i < brideToGroom.length; i++) {
			brideToGroom[i] = -1; // initialize
		}
		int groom;
		int remaingGrooms = pair;
		while (remaingGrooms > 0) {
			groom = -1;
			for (int hasBride : groomToBride) {
				if (hasBride != -1) { // if the bride is already engaged.
					continue;
				}
				groom++;
				for (int bride : priority[groom]) {
					if (brideToGroom[bride - pair] == -1) { // if bride is un-engaged
						groomToBride[groom] = bride;
						brideToGroom[bride - pair] = groom;
						remaingGrooms--;
						break;
					} else {
						boolean flag = checkIfNewIsBetter(priority, bride, brideToGroom[bride - pair], groom);
						if (flag) {
							int currentGroom = brideToGroom[bride - pair];
							brideToGroom[bride - pair] = groom;
							groomToBride[groom] = bride;
							groomToBride[currentGroom] = -1; // resetting or jilting the previous groom :-)
						}
					}
				}
			}
		}
		return groomToBride;
	}

	public static void main(String args[]) {
		int priority[][] = { { 5, 4, 7, 6 }, { 4, 5, 6, 7 }, { 5, 4, 6, 7 }, { 5, 4, 7, 6 }, { 0, 1, 2, 3 },
				{ 0, 1, 3, 2 }, { 0, 3, 1, 2 }, { 0, 1, 2, 3 } };
		StableMarriageProblem smp = new StableMarriageProblem();
		int[] result = smp.findPair(priority);
		for (int i = 0; i < result.length; i++) {
			System.out.println(i + " " + result[i]);
		}
	}
}
