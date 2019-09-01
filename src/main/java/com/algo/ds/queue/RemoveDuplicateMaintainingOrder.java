
package com.algo.ds.queue;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Given a string remove duplicates from the string maintaining order and getting lexicographically smallest string.
 * Reference: https://leetcode.com/problems/remove-duplicate-letters/. Example 1: Input: "bcabc" Output: "abc" Example
 * 2: Input: "cbacdcbc" Output: "acdb".
 * 
 * Intuition :
 * 
 * First we should make sure we understand what "lexicographical order" means. Comparing strings doesn't work the same
 * way as comparing numbers. Strings are compared from the first character to the last one. Which string is greater
 * depends on the comparison between the first unequal corresponding character in the two strings. As a result any
 * string beginning with a will always be less than any string beginning with b, regardless of the ends of both strings.
 * 
 * Because of this, the optimal solution will have the smallest characters as early as possible. We draw two conclusions
 * that provide different methods of solving this problem in O(N)O(N):
 * 
 * The leftmost letter in our solution will be the smallest letter such that the suffix from that letter contains every
 * other. This is because we know that the solution must have one copy of every letter, and we know that the solution
 * will have the lexicographically smallest leftmost character possible.
 * 
 * If there are multiple smallest letters, then we pick the leftmost one simply because it gives us more options. We can
 * always eliminate more letters later on, so the optimal solution will always remain in our search space.
 * 
 * As we iterate over our string, if character i is greater than character i+1 and another occurrence of character i
 * exists later in the string, deleting character i will always lead to the optimal solution. Characters that come later
 * in the string i don't matter in this calculation because i is in a more significant spot. Even if character i+1 isn't
 * the best yet, we can always replace it for a smaller character down the line if possible.
 * 
 * Since we try to remove characters as early as possible, and picking the best letter at each step leads to the best
 * solution, "greedy" should be going off like an alarm.
 * 
 * Complexity Analysis
 * 
 * Time complexity : O(N)O(N). Although there is a loop inside a loop, the time complexity is still O(N)O(N). This is
 * because the inner while loop is bounded by the total number of elements added to the stack (each time it fires an
 * element goes). This means that the total amount of time spent in the inner loop is bounded by O(N)O(N), giving us a
 * total time complexity of O(N)O(N)
 * 
 * Space complexity : O(1)O(1). At first glance it looks like this is O(N)O(N), but that is not true! seen will only
 * contain unique elements, so it's bounded by the number of characters in the alphabet (a constant). You can only add
 * to stack if an element has not been seen, so stack also only consists of unique elements. This means that both stack
 * and seen are bounded by constant, giving us O(1)O(1) space complexity.
 * 
 * Category: Hard
 */
public class RemoveDuplicateMaintainingOrder {
	public String removeDuplicateLetters(String s) {

		Deque<Character> stack = new LinkedList<>();
		// this lets us know if there are any more instances of s[i] left in s
		Map<Character, Integer> count = new HashMap<>();
		// keeps track of whats in solution in O(1) time
		Set<Character> visited = new HashSet<>();

		for (int i = 0; i < s.length(); i++) {
			count.compute(s.charAt(i), (key, val) -> {
				if (val == null) {
					return 1;
				} else {
					return val + 1;
				}
			});
		}

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			count.put(ch, count.get(ch) - 1);
			if (visited.contains(ch)) {
				continue;
			}
			while (!stack.isEmpty() && stack.peekFirst() > ch && count.get(stack.peekFirst()) > 0) {
				visited.remove(stack.peekFirst());
				stack.pollFirst();
			}

			stack.offerFirst(ch);
			visited.add(ch);
		}

		StringBuffer buff = new StringBuffer();
		while (!stack.isEmpty()) {
			buff.append(stack.pollLast());
		}
		return buff.toString();
	}

	public static void main(String args[]) {
		RemoveDuplicateMaintainingOrder rm = new RemoveDuplicateMaintainingOrder();
		System.out.println(rm.removeDuplicateLetters("cbacdcbc"));
	}
}
