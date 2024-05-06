package com.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6',
 * '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 * 
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * 
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock
 * will stop turning and you will be unable to open it.
 * 
 * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of
 * turns required to open the lock, or -1 if it is impossible.
 * 
 * Example 1: Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202" Output: 6
 * 
 * Explanation: A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid, because the wheels of the
 * lock become stuck after the display becomes the dead end "0102".
 * 
 * Example 2: Input: deadends = ["8888"], target = "0009" Output: 1
 * 
 * Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".
 * 
 * Example 3: Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888" Output: -1
 * 
 * Explanation: We can't reach the target without getting stuck.
 * 
 * Example 4: Input: deadends = ["0000"], target = "8888"
 * 
 * Output: -1
 * 
 * Note: The length of deadends will be in the range [1, 500]. target will not be in the list deadends. Every string in
 * deadends and the string target will be a string of 4 digits from the 10,000 possibilities '0000' to '9999'.
 * 
 * Why not DFS?
 * 
 * Because the problem here asks for the minimum number of steps to achieve the target state. Using BFS, we can report
 * the answer as long as we reach the target state. But using DFS, we can't guarantee that the initial target state that
 * we reach is the optimal solution. You still have to search the whole search space. Think about the problem that to
 * find the depth of a binary tree, it is quite similar in this sense.
 * 
 * TODO : to understand the logic
 *
 */
public class OpenTheLock {

	int[] dir = { 1, -1 };

	public int openLock(String[] ends, String target) {
		Set<String> deadends = new HashSet<>();
		for (String dead : ends) {
			deadends.add(dead);
			if (dead.equals("0000") || dead.equals(target)) {
				return -1;
			}
		}

		Queue<String> combination = new LinkedList<>();
		combination.add("0000");
		int ans = 0;
		deadends.add("0000");
		while (!combination.isEmpty()) {
			int size = combination.size();
			while (--size >= 0) {
				String lock = combination.poll();
				char[] lockArray = lock.toCharArray();
				if (lock.equals(target)) {
					return ans;
				}
				for (int i = 0; i < 4; i++) { // for each lock
					char ch = lockArray[i];
					for (int d : dir) { // for each forward / backward direction
						rotate(lockArray, i, d);
						String newLock = new String(lockArray);
						if (!deadends.contains(newLock)) {
							deadends.add(newLock);
							combination.add(newLock);
						}
						lockArray[i] = ch;
					}
				}
			}
			ans++;
		}
		return -1;
	}

	private void rotate(char[] lock, int i, int d) {
		int n = lock[i] - '0';
		n = (n + d + 10) % 10;
		lock[i] = (char) (n + '0');
	}

	public static void main(String[] args) {
		OpenTheLock open = new OpenTheLock();
		System.out.println(open.openLock(new String[] { "0201", "0101", "0102", "1212", "2002" }, "0202"));
		System.out.println(open.openLock(new String[] { "8888" }, "0009"));
		System.out.println(
				open.openLock(new String[] { "8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888" }, "8888"));
		System.out.println(open.openLock(new String[] { "0000" }, "8888"));
	}
}
