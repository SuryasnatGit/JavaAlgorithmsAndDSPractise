package com.misc;

import java.util.HashMap;
import java.util.Map;

public class GuidewireProblem {

	public int numberShuffle(int in) {
		String str = Integer.toString(in);
		System.out.println(str);
		int len = str.length();
		char[] ar = new char[len];
		int c = 0;
		for (int i = 0; i < len / 2; i++) {
			ar[c++] = str.charAt(i);
			ar[c++] = str.charAt(len - i - 1);
		}

		if (len % 2 != 0) // if odd
			ar[c++] = str.charAt(len / 2); // middle one

		System.out.println(new String(ar));
		return Integer.parseInt(new String(ar));
	}

	public int airlineSeatProblem(int N, String S) {
		String[] reservations = S.split(" ");

		// init a N * 10 array
		char[][] seatmap = new char[N][10];
		for (int i = 0; i < seatmap.length; i++) {
			for (int j = 0; j < seatmap[0].length; j++) {
				seatmap[i][j] = ' ';
			}
		}

		for (String reservation : reservations) {
			int l = reservation.length();
			if (l > 0) {
				char seatLetter = reservation.charAt(l - 1);
				int seatNum = mapSeatLetterToIndex().get(seatLetter);
				int numPortion = Integer.parseInt(reservation.substring(0, reservation.indexOf(seatLetter)));
				seatmap[numPortion - 1][seatNum] = 'X'; // reserved
			}
		}

		int count = 0;
		for (int i = 0; i < seatmap.length; i++) {
			if (seatmap[i][1] != 'X' && seatmap[i][2] != 'X' && seatmap[i][3] != 'X' && seatmap[i][4] != 'X') {
				seatmap[i][1] = 'X';
				seatmap[i][2] = 'X';
				seatmap[i][3] = 'X';
				seatmap[i][4] = 'X';
				count++;
			}

			if (seatmap[i][3] != 'X' && seatmap[i][4] != 'X' && seatmap[i][5] != 'X' && seatmap[i][6] != 'X') {
				seatmap[i][3] = 'X';
				seatmap[i][4] = 'X';
				seatmap[i][5] = 'X';
				seatmap[i][6] = 'X';
				count++;
			}

			if (seatmap[i][5] != 'X' && seatmap[i][6] != 'X' && seatmap[i][7] != 'X' && seatmap[i][8] != 'X') {
				seatmap[i][5] = 'X';
				seatmap[i][6] = 'X';
				seatmap[i][7] = 'X';
				seatmap[i][8] = 'X';
				count++;
			}

		}

		return count;
	}

	private Map<Character, Integer> mapSeatLetterToIndex() {
		Map<Character, Integer> map = new HashMap<>();
		map.put('A', 0);
		map.put('B', 1);
		map.put('C', 2);
		map.put('D', 3);
		map.put('E', 4);
		map.put('F', 5);
		map.put('G', 6);
		map.put('H', 7);
		map.put('J', 8);
		map.put('K', 9);
		return map;
	}

	public static void main(String[] args) {
		GuidewireProblem sn = new GuidewireProblem();
		// System.out.println(sn.solution(123456));
		// System.out.println(sn.solution(130));

		System.out.println(sn.airlineSeatProblem(2, "1A 2F 1C"));
		System.out.println(sn.airlineSeatProblem(1, ""));
	}
}
