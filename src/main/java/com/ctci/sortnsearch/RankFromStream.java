package com.ctci.sortnsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CTCI - 10.10
 * 
 * Rank from Stream: Imagine you are reading in a stream of integers. Periodically, you wish to be
 * able to look up the rank of a number x (the number of values less than or equal to x). Implement
 * the data structures and algorithms to support these operations.That is, implement the method
 * track(int x), which is called when each number is generated, and the method getRankOfNumber(int
 * x) , which returns the number of values less than or equal to X (not including x itself).
 * 
 * EXAMPLE:
 * 
 * Stream(in order of appearance): 5 ,1 ,4 ,4 ,5 ,9 ,7 ,13 ,3
 * 
 * getRankOfNumber(1) = 0
 * 
 * getRankOfNumber(3) = 1
 * 
 * getRankOfNumber(4) = 3
 * 
 * @author ctsuser1
 *
 */
public class RankFromStream {

	// naive solution

	List<Integer> list = new ArrayList<Integer>();

	public void track(int x) {
		list.add(x);
		Collections.sort(list);
	}

	public int getRankOfNumber(int x) {
		for (int index = 0; index < list.size(); index++) {
			if (list.get(index).equals(x)) {
				return index;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		RankFromStream rs = new RankFromStream();
		rs.track(5);
		rs.track(1);
		rs.track(4);
		rs.track(4);
		rs.track(5);
		rs.track(9);
		rs.track(7);
		rs.track(13);
		rs.track(3);
		System.out.println(rs.getRankOfNumber(1));
		System.out.println(rs.getRankOfNumber(3));
		System.out.println(rs.getRankOfNumber(4));
	}

}
