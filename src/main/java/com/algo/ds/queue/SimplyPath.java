package com.algo.ds.queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 
 * Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
 * 
 * In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the
 * directory up a level.
 * 
 * Note that the returned canonical path must always begin with a slash /, and there must be only a single slash /
 * between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the
 * canonical path must be the shortest string representing the absolute path.
 * 
 * Example 1:
 * 
 * Input: "/home/" Output: "/home" Explanation: Note that there is no trailing slash after the last directory name.
 * 
 * Example 2:
 * 
 * Input: "/../" Output: "/" Explanation: Going one level up from the root directory is a no-op, as the root level is
 * the highest level you can go.
 * 
 * Example 3:
 * 
 * Input: "/home//foo/" Output: "/home/foo" Explanation: In the canonical path, multiple consecutive slashes are
 * replaced by a single one.
 * 
 * Example 4:
 * 
 * Input: "/a/./b/../../c/" Output: "/c"
 * 
 * Example 5:
 * 
 * Input: "/a/../../b/../c//.//" Output: "/c" Example 6:
 * 
 * Input: "/a//b////c/d//././/.." Output: "/a/b/c"
 *
 * 
 * https://leetcode.com/problems/simplify-path/
 * 
 * Category : Medium
 */
public class SimplyPath {

	/**
	 * T - O(n) S - O(n)
	 * 
	 * @param path
	 * @return
	 */
	public String simplifyPath(String path) {
		Deque<String> stack = new LinkedList<>();
		Set<String> set = new HashSet<>(Arrays.asList("..", ".", ""));
		StringTokenizer token = new StringTokenizer(path, "/");
		while (token.hasMoreTokens()) {
			String tok = token.nextToken();
			if (tok.equals("..") && !stack.isEmpty()) {
				stack.pop();
			} else if (!set.contains(tok)) {
				stack.push(tok);
			}
		}

		String result = "";
		for (String dir : stack) {
			result = "/" + dir + result;
		}

		return result.isEmpty() ? "/" : result;
	}

	public static void main(String args[]) {
		SimplyPath mfc = new SimplyPath();
		System.out.println(mfc.simplifyPath("/a/./b/../../c/"));
		System.out.println(mfc.simplifyPath("/home/"));
		System.out.println(mfc.simplifyPath("/../"));
		System.out.println(mfc.simplifyPath("/home//foo/"));
		System.out.println(mfc.simplifyPath("/a/../../b/../c//.//"));
		System.out.println(mfc.simplifyPath("/a//b////c/d//././/.."));
	}
}
