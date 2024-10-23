package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Introduce this question specifically, input is a String array (both upper and lower case), and a String name (all
 * lower case) The output requirements are a bit complicated. Look for each substring in the name. If the substring does
 * not appear in the array, it will be output directly. If it has appeared in the array, find the longest possible one
 * that can be matched in the array, then capitalize the first letter and add symbols before and after the output. For
 * example, array = {"b", "Bc", "e"} name = "abcde"; output is ad Here because b can match b, bc can match bc and is
 * longer, so the answer is this.
 *
 * Write an auto complete program. It is required that the program can run test. At the time, I wrote the trie, but the
 * final dfs was not finished. The interviewer did not allow me to use boolean to mark it, saying that it is not
 * suitable for many searches at the same time.
 *
 * Given a bunch of words, each word may appear multiple times, and find the top k words with a certain sequence as the
 * prefix. Said it can be done with trie
 * 
 * Category : Hard
 * 
 * TODO : to understand
 *
 */
public class TrieBreakingBad {

	TrieNode root = new TrieNode();

	public static void main(String[] args) {
		TrieBreakingBad t = new TrieBreakingBad();

		String source = "helloword";
		String[] dict = { "abc", "dec", "UDA", "abcde", "abd", "abfeU" };

		// Build Trie
		for (String s : dict) {
			t.root.insert(s, 0);
		}

		List<String> list = t.autoCompleteDFS("ab");
		for (String s : list) {
			System.out.println(s);
		}
	}

	public List<String> autoCompleteDFS(String s) {
		TrieNode node = root.search(s, 0);

		List<String> res = new ArrayList<String>();
		if (node == null) {
			return res;
		}

		if (node.hasWord) {
			res.add(node.word);
		}

		dfs(res, node);
		return res;
	}

	boolean flag = false;

	private void dfs(List<String> res, TrieNode node) {
		if (flag) {
			return;
		}

		for (Map.Entry<Character, TrieNode> child : node.children.entrySet()) {
			TrieNode childNode = child.getValue();

			if (childNode.hasWord) {
				res.add(childNode.word);

				if (res.size() == 3) {
					flag = true;
					return;
				}
			}

			dfs(res, childNode);
		}
	}

	/**
	 * implement a prefix search-given a set of characters, return the top-10 most relevant results. Relevancy here
	 * being the shorter word is more relevant than a longer word. An empty prefix is ​​not a valid input. In the middle
	 * of the question, I asked how to define relevance as based on lexico order (that is, bathroom <baths, for bath).
	 * shorter word Use BFS for relevance and DFS for lex-order. It’s easier to use boolean indicator on the node of the
	 * word end. Don’t save strings. You can use a queue to save candidates in BFS. node, a queue to store the
	 * corresponding prefix string to construct string
	 */
	public List<String> autoCompleteBFS(String s) {
		TrieNode node = root.search(s, 0);

		List<String> res = new ArrayList<String>();
		if (node == null) {
			return res;
		}

		if (node.hasWord) {
			res.add(node.word);
		}

		Queue<TrieNode> queue = new LinkedList<TrieNode>();
		queue.addAll(node.children.values());

		while (!queue.isEmpty()) {
			TrieNode now = queue.poll();

			if (now.hasWord) {
				res.add(now.word);
			}

			if (res.size() == 3) {
				break;
			}

			if (!now.children.isEmpty()) {
				queue.addAll(now.children.values());
			}
		}

		return res;
	}

	static class TrieNode {
		Map<Character, TrieNode> children = null; // Could use TreeMap to make sure it is sorted
		boolean hasWord = false;
		String word = null;

		TrieNode() {
			this.children = new HashMap<Character, TrieNode>();
		}

		void insert(String s, int pos) {
			if (pos == s.length()) {
				this.hasWord = true;
				this.word = s;
				return;
			}

			char now = s.charAt(pos);
			if (!this.children.containsKey(now)) {
				this.children.put(now, new TrieNode());
			}

			this.children.get(now).insert(s, pos + 1);
		}

		TrieNode search(String s, int pos) {
			if (pos == s.length()) {
				return this;
			}

			char now = s.charAt(pos);
			if (!this.children.containsKey(now)) {
				return null;
			}

			return this.children.get(now).search(s, pos + 1);
		}

		boolean startsWith(String s) {
			TrieNode node = search(s, 0);
			if (node == null) {
				return false;
			}

			return true;
		}

		boolean contains(String s) {
			TrieNode node = search(s, 0);
			if (node == null) {
				return false;
			}

			return node.hasWord;
		}
	}

	/**
	 * Give you a bunch of domain names, some of which are the parents of others, for example, .com is the parent of
	 * .youku.com, and .youku.com is the parent of .service.youku.com. Then I will give you a URL, let you find the
	 * longest one in the parent of this URL among the pile of domain names, and then go back one more time. The
	 * language is a bit difficult to describe, for example: Domains:[".com",".cn", ".service.com", ".net",
	 * ".youku.net"] url: "yeah.hello.youku.net" Here both .net and .youku.net are the parents of this url, the longest
	 * of which is .youku.net, and the next one is hello, so it returns "hello.youku.net"
	 *
	 * Later, I thought about it and thought it was a bit like a trie, just look from the back to the front
	 *
	 */
	static class LongestDomain {

		public static void main(String[] args) {
			LongestDomain ld = new LongestDomain();
			String res = ld.findLongest();
			System.out.println(res);
		}

		String[] arr = { ".com", ".cn", ".service.com", ".net", ".youku.net" };
		String url = "yeah.hello.youku.net";
		TrieBreakingBad.TrieNode root = new TrieBreakingBad.TrieNode();

		String findLongest() {
			for (String domain : arr) {
				String reversed = reverse(domain);
				root.insert(reversed, 0);
			}

			String reversedUrl = reverse(url);
			String[] sections = reversedUrl.split("\\.");
			String now = "";
			for (String sec : sections) {
				now = now + sec + ".";

				if (!root.contains(now)) {
					break;
				}
			}

			return reverse(now);
		}

		String reverse(String s) {
			char[] arr = s.toCharArray();

			int left = 0, right = s.length() - 1;
			while (left < right) {
				char tmp = arr[left];
				arr[left] = arr[right];
				arr[right] = tmp;

				left++;
				right--;
			}

			return new String(arr);
		}
	}
}
