package com.leetcode;

/**
 * A password is considered strong if below conditions are all met:
 * 
 * It has at least 6 characters and at most 20 characters.
 * 
 * It must contain at least one lowercase letter, at least one uppercase letter, and at least one digit.
 * 
 * It must NOT contain three repeating characters in a row ("...aaa..." is weak, but "...aa...a..." is strong, assuming
 * other conditions are met).
 * 
 * Write a function strongPasswordChecker(s), that takes a string s as input, and return the MINIMUM change required to
 * make s a strong password. If s is already strong, return 0.
 * 
 * Insertion, deletion or replace of any one character are all considered as one change.
 * 
 * Category : Hard
 * 
 * TODO : to understand properly
 */
public class StrongPasswordChecker {

	public int strongPasswordChecker(String s) {
		int res = 0, n = s.length(), lower = 1, upper = 1, digit = 1;
		int[] v = new int[n];

		for (int i = 0; i < n;) {
			if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				lower = 0; // lowercase letter found
			}

			if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
				upper = 0; // uppercase letter found
			}

			if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				digit = 0; // digit found
			}

			int start = i;

			while (i < n && s.charAt(i) == s.charAt(start)) {
				i++;
			}

			v[start] = i - start; // number of repetition
		}

		int missType = lower + upper + digit; // the type of letter that doesn't exists

		/*
		 * if n is smaller than 6, we have to insert the diff (6-n) if diff is smaller than 3 (the # of letter types),
		 * we need to insert missType e.g. aaaaa, we must add uppercase and digit anyway, even if diff=1
		 */
		if (n < 6) {
			res = Math.max(missType, 6 - n);
		} else {
			// if number is greater than 20
			int over = Math.max(n - 20, 0), replace = 0;
			// we will need to remove `over` characters anyway
			res += over;
			/*
			 * We know that for (3m+2) letters, we only need to replace m letters Remove 1 or 2 letters to convert
			 * v[start] in the form of 3m+2 where m is an integer. e.g. aaaaaaaaabbbbbbbb (9 a's and 8 b's), remove 1 a
			 * to make 8 = 3m+2, where m = 2
			 */
			for (int i = 0; i < n && over > 0; i++) {
				if (v[i] < 3) {
					continue;
				}

				if (v[i] % 3 == 0) { // e.g. 9 a's, need to remove 1
					v[i] -= 1;
					over -= 1; // already removed one;
				}
			}

			for (int i = 0; i < n && over > 0; i++) {
				if (v[i] < 3) {
					continue;
				}

				if (v[i] % 3 == 1) { // e.g. 7 a's, need to remove 2 to become 3*1+2=5
					v[i] -= Math.min(2, over);
					over -= 2;
				}
			}

			// for (int k = 1; k < 3; k++) {
			// for (int i = 0; i < n && over > 0; i++) {
			// if (v[i] < 3 || v[i] % 3 != (k - 1)) continue;
			// v[i] -= Math.min(over, k);
			// over -= k;
			// }
			// }

			// over is the remaining letters that need to be removed
			// if removal can fix the repetition issue, we don't need to replace
			for (int i = 0; i < n; i++) {
				if (v[i] >= 3 && over > 0) {
					int needToRemove = v[i] - 2; // 3m
					v[i] -= over; // do not need to remove v[i] if <=2
					over -= needToRemove;
				}

				if (v[i] >= 3) {
					replace += v[i] / 3; // at least replace m
				}
			}

			// System.out.println(missType + " " + replace);
			res += Math.max(missType, replace);
		}
		return res;
	}

}
