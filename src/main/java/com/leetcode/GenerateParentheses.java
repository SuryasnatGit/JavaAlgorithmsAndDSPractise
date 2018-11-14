package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed
 * parentheses. For example, given n = 3, a solution set is: [ "((()))", "(()())", "(())()",
 * "()(())", "()()()" ]
 * 
 * @author surya
 *
 */
public class GenerateParentheses {

	List<String> res = new ArrayList<>();

	/**
	 * Easy solution: DFS. Left and right represents the remaining number of ( and ) that need to be
	 * added. When left > right, there are more ")" placed than "(". Such cases are wrong and the method
	 * stops.
	 * 
	 * @param n
	 * @return
	 */
	public List<String> generateParenthesis(int n) {
		generate("", n, n);
		return res;
	}

	private void generate(String s, int left, int right) {
		if (left == 0 && right == 0) {
			res.add(s);
		}
		if (left < 0 || right < 0 || left > right)
			return;

		generate(s + '(', left - 1, right);
		generate(s + ')', left, right - 1);

	}

	/**
	 * Complicated solution.
	 * 
	 * @param n
	 * @return
	 */
	public List<String> generateParenthesis1(int n) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<Integer> diff = new ArrayList<Integer>();

		result.add("");
		diff.add(0);

		for (int i = 0; i < 2 * n; i++) {
			ArrayList<String> temp1 = new ArrayList<String>();
			ArrayList<Integer> temp2 = new ArrayList<Integer>();

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
		// List<String> list = gp.generateParenthesis(4);
		// list.forEach(a -> System.out.println(a));
		List<String> list = gp.generateParenthesis1(3);
		list.forEach(a -> System.out.println(a));
	}

}
