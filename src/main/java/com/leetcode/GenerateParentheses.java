package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses. For example,
 * given n = 3, a solution set is: [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
 * 
 * Category : Medium
 *
 */
public class GenerateParentheses {

	/**
	 * Easy solution: DFS. Left and right represents the remaining number of ( and ) that need to be added. When left >
	 * right, there are more ")" placed than "(". Such cases are wrong and the method stops.
	 *
	 */
	public List<String> generateParenthesis1(int n) {
		List<String> res = new ArrayList<>();
		generate("", n, n, res);
		return res;
	}

	private void generate(String s, int left, int right, List<String> res) {
		if (left == 0 && right == 0) {
			res.add(s);
		}
		if (left < 0 || right < 0 || left > right)
			return;

		generate(s + '(', left - 1, right, res);
		generate(s + ')', left, right - 1, res);
	}

	/**
	 * Complicated solution.
	 * 
	 * @param n
	 * @return
	 */
	public List<String> generateParenthesis2(int n) {
		List<String> result = new ArrayList<String>();
		List<Integer> diff = new ArrayList<Integer>();

		result.add("");
		diff.add(0);

		for (int i = 0; i < 2 * n; i++) {
			List<String> temp1 = new ArrayList<String>();
			List<Integer> temp2 = new ArrayList<Integer>();

			for (int j = 0; j < result.size(); j++) {
				String s = result.get(j);
				int k = diff.get(j);

				if (i < 2 * n - 1) {
					temp1.add(s + "(");
					temp2.add(k + 1);
				}

				if (k > 0 && i < 2 * n - 1 || k == 1 && i == 2 * n - 1) {
					temp1.add(s + ")");
					temp2.add(k - 1);
				}
			}

			result = new ArrayList<String>(temp1);
			diff = new ArrayList<Integer>(temp2);
		}

		return result;
	}

	public static void main(String[] args) {
		GenerateParentheses gp = new GenerateParentheses();
		gp.generateParenthesis1(3).forEach(a -> System.out.println(a));
		System.out.println();
		gp.generateParenthesis2(3).forEach(a -> System.out.println(a));
	}

}
