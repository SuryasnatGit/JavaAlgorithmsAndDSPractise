package com.misc;

public class Test {

	public static void main(String[] args) {
		Test t = new Test();
		System.out.println(t.maxNumericValue1("100klh564abc365bg"));
	}

	public String maxNumericValue1(String in) {
		int len = in.length();
		String currNum = "";
		String res = "";

		for (int i = 0; i < len; i++) {
			// for leading 0s
			while (i < len && in.charAt(i) == '0')
				i++;

			while (i < len && Character.isDigit(in.charAt(i))) {
				currNum += in.charAt(i);
				i++;
			}

			if (i == len)
				break;

			res = max(currNum, res);

			// reset
			currNum = "";
		}

		// if only 0
		if (currNum.length() == 0 && res.length() == 0)
			res += '0';

		res = max(currNum, res);

		return res;
	}

	private String max(String currNum, String res) {
		int l1 = currNum.length();
		int l2 = res.length();

		if (l1 == l2 && l1 > 0 && l2 > 0) {
			int i = 0;
			while (currNum.charAt(i) == res.charAt(i))
				i++;

			if (currNum.charAt(i) < res.charAt(i))
				return res;
			else
				return currNum;
		}

		return l1 < l2 ? res : currNum;
	}

}
