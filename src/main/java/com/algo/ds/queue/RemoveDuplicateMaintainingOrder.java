

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
 * 2: Input: "cbacdcbc" Output: "acdb"
 */
public class RemoveDuplicateMaintainingOrder {
    public String removeDuplicateLetters(String s) {
        Deque<Character> stack = new LinkedList<>();
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            count.compute(s.charAt(i), (key, val) -> {
                if (val == null) {
                    return 1;
                } else {
                    return val + 1;
                }
            });
        }

        Set<Character> visited = new HashSet<>();
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
