package com.algo.dp;

/**
 * https://www.techiedelight.com/implement-diff-utility/
 * 
 */
public class DiffUtility {

	public void printDiff(String x, String y, int m, int n, int[][] lookup) {
		// if last char of x and y match
		if (m > 0 && n > 0 && x.charAt(m - 1) == y.charAt(n - 1)) {
			printDiff(x, y, m - 1, n - 1, lookup);
			System.out.print(x.charAt(m - 1) + " ");
		} else if (m > 0 && (n == 0 || lookup[m][n - 1] < lookup[m - 1][n])) { // current char of x not present in y
			printDiff(x, y, m - 1, n, lookup);
			System.out.print("-" + x.charAt(m - 1) + " ");
		} else if (n > 0 && (m == 0 || lookup[m][n - 1] >= lookup[m - 1][n])) { // current char of y not present in x
			printDiff(x, y, m, n - 1, lookup);
			System.out.print("+" + y.charAt(n - 1) + " ");
		}
	}

	private void populateLookup(String x, String y, int m, int n, int[][] lookup) {
		// first column is always 0
		for (int i = 0; i <= m; i++) {
			lookup[i][0] = 0;
		}

		// first row is always 0
		for (int j = 0; j <= n; j++) {
			lookup[0][j] = 0;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					lookup[i][j] = lookup[i - 1][j - 1] + 1;
				} else {
					lookup[i][j] = Math.max(lookup[i][j - 1], lookup[i - 1][j]);
				}
			}
		}
	}

	public static void main(String[] args) {
		DiffUtility du = new DiffUtility();

		String x = "BAT";
		String y = "BAD";

		int[][] lookup = new int[x.length() + 1][y.length() + 1];
		du.populateLookup(x, y, x.length(), y.length(), lookup);

		du.printDiff(x, y, x.length(), y.length(), lookup);
	}

}
