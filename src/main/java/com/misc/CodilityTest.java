package com.misc;

public class CodilityTest {

	public boolean solution(String S, String T) {
		if (S == null || T == null)
			return false;

		String tempS = expandEncodedString(S);
		System.out.println(tempS);
		String tempT = expandEncodedString(T);
		System.out.println(tempT);

		if (tempS.length() != tempT.length()) {
			return false;
		}

		int count = tempS.length();
		for (int i = 0; i < count; i++) {
			char s = tempS.charAt(i);
			char t = tempT.charAt(i);
			if (s == '?' || t == '?') {
				continue;
			}

			if (s != t) {
				return false;
			}
		}

		return true;

	}

	private String expandEncodedString(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				int count = Character.getNumericValue(c);
				while (count-- > 0) {
					sb.append("?");
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	// TODO : have to find a solution for this. purpose to find the maxium time the person can take rest from meetings.
	public int solution(String S) {
		String[] timeEntries = S.split("\n");
		int num = timeEntries.length;
		for (String s : timeEntries) {
			System.out.println(s);
			String day = s.substring(0, 3);
			String start = s.substring(4, 9);
			String end = s.substring(s.indexOf("-") + 1, s.length());
			System.out.println(day + "," + start + "," + end);

			int startI = Integer.valueOf(start.replace(":", ""));
			int endI = Integer.valueOf(end.replace(":", ""));
			System.out.println(day + "," + startI + "," + endI);

			int hourDiff = endI / 100 - startI / 100 - 1;

			int minDiff = endI % 100 + (60 - startI % 100);

			if (minDiff >= 60) {
				hourDiff++;
				minDiff = minDiff - 60;
			}
			System.out.println(minDiff);
		}
		return 0;
	}

	public static void main(String[] args) {
		CodilityTest ct = new CodilityTest();
		// Solution 1
		// System.out.println(ct.solution("A2Le", "2pL1"));
		// System.out.println(ct.solution("A2Le", "2p2"));
		// System.out.println(ct.solution("a10", "10a"));
		// System.out.println(ct.solution("ba1", "1Ad"));

		String s = "Mon 01:00-23:00\nTue 01:00-23:00\nWed 01:00-23:00\n";
		ct.solution(s);
	}
}

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}
