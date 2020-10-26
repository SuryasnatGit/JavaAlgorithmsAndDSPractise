package com.algo.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible
 * results. Note: The input string may contain letters other than the parentheses ( and ).
 * 
 * Examples: "()())()" -> ["()()()", "(())()"]
 * 
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * 
 * ")(" -> [""]
 * 
 * Time complexity:
 * 
 * In BFS we handle the states level by level, in the worst case, we need to handle all the levels, we can analyze the
 * time complexity level by level and add them up to get the final complexity.
 * 
 * On the first level, there's only one string which is the input string s, let's say the length of it is n, to check
 * whether it's valid, we need O(n) time. On the second level, we remove one ( or ) from the first level, so there are
 * C(n, n-1) new strings, each of them has n-1 characters, and for each string, we need to check whether it's valid or
 * not, thus the total time complexity on this level is (n-1) x C(n, n-1). Come to the third level, total time
 * complexity is (n-2) x C(n, n-2), so on and so forth...
 * 
 * Finally we have this formula: T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).
 * 
 * Category : Hard
 *
 */
public class RemoveInvalidParenthesis {

	public String minRemoveToMakeValid(String s) {
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder(s);

		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);

			if (cur == ')') {
				if (stack.isEmpty()) {
					sb.setCharAt(i, '*');
				} else {
					stack.pop();
				}
			} else if (cur == '(') {
				stack.push(i);
			}
		}

		while (!stack.isEmpty()) {
			int cur = stack.pop();
			sb.replace(cur, cur + 1, "*");
		}

		return sb.toString().replaceAll("\\*", "");
	}

	/**
	 * Solution 1 - Using DFS.
	 * 
	 * 1. Generate unique answer once and only once, do not rely on Set.
	 * 
	 * 2. Do not need preprocess.
	 * 
	 * We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter. The
	 * counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more
	 * ‘)’ than ‘(‘ in the prefix.
	 * 
	 * To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any ‘)’ in the
	 * prefix. However, if we remove any one, we will generate duplicate results, for example: s = ()), we can remove
	 * s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of
	 * consecutive )s. After the removal, the prefix is then valid. We then call the function recursively to solve the
	 * rest of the string.
	 * 
	 * However, we need to keep another information: the last removal position. If we do not have this position, we will
	 * generate duplicate by removing two ) in two steps only with a different order. For this, we keep tracking the
	 * last removal position and only remove ) after that.
	 * 
	 * Now one may ask. What about (? What if s = (()(() in which we need remove (? The answer is: do the same from
	 * right to left. However, a cleverer idea is: reverse the string and reuse the code!
	 * 
	 * @param s
	 * @return
	 */
	public List<String> removeInvalidParentheses(String s) {

		List<String> ans = new ArrayList<>();

		remove(s, ans, 0, 0, new char[] { '(', ')' });
		return ans;
	}

	private void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {

		int stack = 0, i;

		// Search for mismatch
		for (i = last_i; i < s.length(); ++i) {
			if (s.charAt(i) == par[0])
				stack++;

			if (s.charAt(i) == par[1])
				stack--;

			if (stack < 0)
				break;
		}

		// Remove a parenthesis
		if (stack < 0) {
			for (int j = last_j; j <= i; ++j) {
				// removing the first one in the series
				if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
					String candidate = s.substring(0, j) + s.substring(j + 1, s.length());
					remove(candidate, ans, i, j, par);
				}
			}
			return;
		}

		// If no mismatch, try reverse the string

		String reversed = new StringBuilder(s).reverse().toString();

		if (par[0] == '(')
			remove(reversed, ans, 0, 0, new char[] { ')', '(' }); // switch the parentheses
		else
			ans.add(reversed); // both sides are finished, got an answer

	}

	/**
	 * Solution 2 - Using DFS with optimizer
	 * 
	 * DFS solution with optimizations:
	 * 
	 * 1. Before starting DFS, calculate the total numbers of opening and closing parentheses that need to be removed in
	 * the final solution, then these two numbers could be used to speed up the DFS process.
	 * 
	 * 2. Use while loop to avoid duplicate result in DFS, instead of using HashSet.
	 * 
	 * 3. Use count variable to validate the parentheses dynamically.
	 * 
	 * openN and closeN means the total numbers of opening and closing parentheses that need to be removed in the final
	 * solution (removed the minimum number of invalid parentheses). dfs(s, p + i, count + i, openN, closeN, result, sb)
	 * means not to remove the current parenthesis. dfs(s, p + 1, count, openN - 1, closeN, result, sb) means to remove
	 * the current parenthesis. We won't need to do the dfs if openN has been decreased to zero - if the openN is zero,
	 * that means there are already enough open parentheses has been removed, and continually removing open parenthesis
	 * won't be a possible solution.
	 * 
	 * @param s
	 * @return
	 */
	public List<String> removeInvalidParentheses1(String s) {
		int count = 0, openN = 0, closeN = 0;

		// calculate the total numbers of opening and closing parentheses
		// that need to be removed in the final solution
		for (char c : s.toCharArray()) {
			if (c == '(') {
				count++;
			} else if (c == ')') {
				if (count == 0)
					closeN++;
				else
					count--;
			}
		}
		openN = count;
		count = 0;

		if (openN == 0 && closeN == 0)
			return Arrays.asList(s);

		List<String> result = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		dfs(s.toCharArray(), 0, count, openN, closeN, result, sb);

		return result;
	}

	private void dfs(char[] s, int p, int count, int openN, int closeN, List<String> result, StringBuilder sb) {
		if (count < 0)
			return; // the parentheses is invalid

		if (p == s.length) {
			if (openN == 0 && closeN == 0) { // the minimum number of invalid parentheses have been removed
				result.add(sb.toString());
			}
			return;
		}

		if (s[p] != '(' && s[p] != ')') {
			sb.append(s[p]);
			dfs(s, p + 1, count, openN, closeN, result, sb);
			sb.deleteCharAt(sb.length() - 1);
		} else if (s[p] == '(') {
			int i = 1;
			while (p + i < s.length && s[p + i] == '(')
				i++; // use while loop to avoid duplicate result in DFS, instead of using HashSet
			sb.append(s, p, i);
			dfs(s, p + i, count + i, openN, closeN, result, sb);
			sb.delete(sb.length() - i, sb.length());

			if (openN > 0) {
				// remove the current opening parenthesis
				dfs(s, p + 1, count, openN - 1, closeN, result, sb);
			}
		} else {
			int i = 1;
			while (p + i < s.length && s[p + i] == ')')
				i++; // use while loop to avoid duplicate result in DFS, instead of using HashSet
			sb.append(s, p, i);
			dfs(s, p + i, count - i, openN, closeN, result, sb);
			sb.delete(sb.length() - i, sb.length());

			if (closeN > 0) {
				// remove the current closing parenthesis
				dfs(s, p + 1, count, openN, closeN - 1, result, sb);
			}
		}
	}

	/**
	 * Solution 3 - Using BFS.
	 * 
	 * The naive BFS solution is quite simple to implement. To speed up we can use a Set to record all the strings
	 * generated and avoid revisit. But a better and faster solution is to avoid generate duplicate strings all
	 * together.
	 * 
	 * The first observation is when we want to remove a ')' or '(' from several consecutive ones we only remove the
	 * first one, because remove any one the result will be the same.
	 * 
	 * For example "())" ---> "()" only remove the first one of '))'
	 * 
	 * The second observation is when we remove a character it must behind it's parent removal position. For example
	 * 
	 * we need remove 2 from "(())((" we want to remove positions combination i,j with no duplicate so we let i < j then
	 * it will not generate duplicate combinations in practice, we record the position i and put it in to queue which is
	 * then polled out and used as the starting point of the next removal
	 * 
	 * A third observation is if the previous step we removed a "(", we should never remove a ")" in the following
	 * steps. This is obvious since otherwise we could just save these two removals and still be valid with less
	 * removals. With this observation all the possible removals will be something like this ")))))))))((((((((("
	 * 
	 * All the removed characters forming a string with consecutive left bracket followed by consecutive right bracket.
	 * 
	 * By applying these restrictions, we can avoid generate duplicate strings and the need of a set which saves a lot
	 * of space. Ultimately we can further improve the algorithm to eliminate isValid calls. To do this we need to
	 * remove and only remove those characters that would lead us to valid strings. This needs some preprocess and can
	 * reduce the time to around 3ms.
	 * 
	 * 
	 * @param s
	 * @return
	 */
	public List<String> removeInvalidParentheses2(String s) {
		if (isValid(s))
			return Collections.singletonList(s);
		List<String> ans = new ArrayList<>();
		// The queue only contains invalid middle steps
		Queue<Tuple> queue = new LinkedList<>();
		// The 3-Tuple is (string, startIndex, lastRemovedChar)
		queue.add(new Tuple(s, 0, ')'));
		while (!queue.isEmpty()) {
			Tuple x = queue.poll();
			// Observation 2, start from last removal position
			for (int i = x.start; i < x.string.length(); ++i) {
				char ch = x.string.charAt(i);
				// Not parentheses
				if (ch != '(' && ch != ')')
					continue;
				// Observation 1, do not repeatedly remove from consecutive ones
				if (i != x.start && x.string.charAt(i - 1) == ch)
					continue;
				// Observation 3, do not remove a pair of valid parentheses
				if (x.removed == '(' && ch == ')')
					continue;
				String t = x.string.substring(0, i) + x.string.substring(i + 1);
				// Check isValid before add
				if (isValid(t))
					ans.add(t);
				// Avoid adding leaf level strings
				else if (ans.isEmpty())
					queue.add(new Tuple(t, i, ch));
			}
		}
		return ans;
	}

	private boolean isValid(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (c == '(')
				++count;
			if (c == ')' && count-- == 0)
				return false;
		}
		return count == 0;
	}

	private class Tuple {
		public final String string;
		public final int start;
		public final char removed;

		public Tuple(String string, int start, char removed) {
			this.string = string;
			this.start = start;
			this.removed = removed;
		}
	}

	public static void main(String[] args) {
		RemoveInvalidParenthesis r = new RemoveInvalidParenthesis();
		System.out.println(r.removeInvalidParentheses("()())()"));
		System.out.println(r.removeInvalidParentheses1("(a)())()"));
		System.out.println(r.removeInvalidParentheses2(")("));

		System.out.println(r.minRemoveToMakeValid("()())()"));
	}
}
