package com.leetcode;

import java.util.ArrayList;

public class PrettyJson {

	public ArrayList<String> prettyJSON(String A) {

		ArrayList<String> res = new ArrayList<>();
		StringBuilder str = new StringBuilder();
		int n = A.length();
		int tabs = 0;

		for (int i = 0; i < n;) {

			i = skipSpace(A, i);

			if (i >= n)
				break;

			str = new StringBuilder();
			char c = A.charAt(i);

			if (delimiter(c)) {

				if (isOpenBracket(c)) {
					for (int j = 0; j < tabs; j++)
						str.append("\t");
					tabs++;
				} else if (isClosedBracket(c)) {
					tabs--;
					for (int j = 0; j < tabs; j++)
						str.append("\t");
				}

				str.append(c);
				i++;

				if (i < n && canAdd(A.charAt(i))) {
					str.append(A.charAt(i));
					i++;
				}

				res.add(str.toString());

				continue;
			}

			while (i < n && !delimiter(A.charAt(i))) {
				str.append(A.charAt(i));
				i++;
			}

			if (i < n && canAdd(A.charAt(i))) {
				str.append(A.charAt(i));
				i++;
			}

			StringBuilder strB = new StringBuilder();

			for (int j = 0; j < tabs; j++)
				strB.append("\t");

			strB.append(str);
			res.add(strB.toString());
		}

		return res;

	}

	public boolean canAdd(char c) {
		if (c == ',' || c == ':')
			return true;

		return false;
	}

	public boolean delimiter(char c) {
		if (c == ',' || isOpenBracket(c) || isClosedBracket(c))
			return true;
		return false;
	}

	public boolean isOpenBracket(char c) {
		if (c == '[' || c == '{')
			return true;
		return false;
	}

	public boolean isClosedBracket(char c) {
		if (c == ']' || c == '}')
			return true;
		return false;
	}

	public int skipSpace(String A, int i) {
		int n = A.length();
		while (i < n && A.charAt(i) == ' ')
			i++;
		return i;
	}
}
