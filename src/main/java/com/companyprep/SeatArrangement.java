package com.companyprep;

/**
 * There is a row of seats. Assume that it contains 15 seats adjacent to each other. There is a group of people who are
 * already seating in that row randomly. i.e. some are sitting together & some are scattered. Take the row as a String
 * in java.
 * 
 * The seat which is occupied is marked with a character 'X' & which is not occupied is marked with a dot '.'
 * 
 * Now your target is to make the whole group sit together i.e. next to each other and without having any vacant seat
 * between them in such a way that the total number of hops or jumps at the end of the grouping them together should be
 * minimum.
 * 
 * Ok let me try to explain you in details.
 * 
 * Here is the row having 15 seats represented by the String (0, 1, 2, 3, ......... , 14) -
 * 
 * . . . . x . . x x . . . x . .
 * 
 * Now to make them sit together one of approaches is - . . . . . . x x x x . . . . . Following are the steps to achieve
 * this - 1 - Move the person sitting at 4th index to 6th index - Number of jumps by him = (6 - 4) = 2 2 - Bring the
 * person sitting at 12th index to 9th index - Number of jumps by him = (12 - 9) = 3
 * ------------------------------------------------------------------------------------------------------------------------------------------------------------
 * So now the total number of jumps made = ( 2 + 3) = 5 which is the minimum possible jumps to make them seat together.
 * 
 * There are also other ways to make them sit together but the number of jumps will exceed 5 & that will not be minimum.
 * 
 * For example bring them all towards the starting of the row i.e. start placing them from index 0. In that case the
 * total number of jumps will be ( 4 + 6 + 6 + 9 ) = 25 which is very costly and not an optimized way to do this
 * movement.
 * 
 * Now write an algorithm which will return the minimum number of jumps required to make them sit together.
 * 
 * Algorithm : Greedy
 * 
 * Number of jumps is always minimum when people shift towards median. Every time two groups of people need to be merged
 * together, the group with lesser number of people should join the larger group so that hops are minimum
 * 
 * Category : Hard
 */
public class SeatArrangement {

	// T - O(n * n) -- wrong solution
	public int minimumJumps(char[] seats) {
		int minJumps = Integer.MAX_VALUE;
		for (int i = 0; i < seats.length; i++) {
			int jumps = shift(seats, i);
			System.out.println(jumps);
			minJumps = Math.min(minJumps, jumps);
		}
		return minJumps;
	}

	// T - O(n)
	public int minimumJumpsOptimized(String s) {
		int i = findMedian(s.toCharArray());
		if (i == -1) {
			return 0;
		}
		return shift(s.toCharArray(), i);
	}

	private int findMedian(char[] seats) {
		int count1 = 0;
		for (int i = 0; i < seats.length; i++) {
			if (seats[i] == 'x') {
				count1++;
			}
		}

		if (count1 == 0) {
			return -1;
		}

		count1 = (count1 + 1) / 2; // half
		int count2 = 0;
		for (int i = 0; i < seats.length; i++) {
			if (seats[i] == 'x') {
				count2++;
				if (count2 == count1) {
					return i;
				}
			}
		}
		return -1;
	}

	private int shift(char[] seats, int pos) {
		int count = 0;
		// left scan of pos
		int j = 0, k = pos;
		while (j < k) {
			if (seats[j] == '.') {
				j++;
			} else if (seats[k] == 'x') {
				k--;
			} else {
				seats[k] = seats[j];
				seats[j] = '.';
				count += (k - j);
				j++;
				k--;
			}
		}

		// right scan of pos
		j = seats.length - 1;
		k = pos;
		while (j > k) {
			if (seats[j] == '.') {
				j--;
			} else if (seats[k] == 'x') {
				k++;
			} else {
				seats[k] = seats[j];
				seats[j] = '.';
				count += (j - k);
				j--;
				k++;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		SeatArrangement sa = new SeatArrangement();
		System.out.println(sa.minimumJumps("....x..xx...x..".toCharArray()));
		System.out.println(sa.minimumJumpsOptimized("....x..xx...x..")); // median solution works
	}

}
