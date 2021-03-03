package com.leetcode;

/**
 * 给你一堆域名，其中一些是另一些的parent，比如.com是.youku.com的parent，然后.youku.com是.service.youku.com的parent这样，
 * 然后再给你一个网址，让你在那堆域名中找到这个网址的parent里面最长的一个，然后再往前退一个返回。语言有点不好描述， 举个栗子：
 * Domains:[“.com”,“.cn”“.service.com”“.net”“.youku.net”] url: “yeah.hello.youku.net”
 * 这里.net和.youku.net都是这个url的parent,其中最长的是.youku.net，再往前退一个是hello,所以返回“hello.youku.net”
 * 
 * 后来想了想有点像trie，从后往前看就是了
 *
 * Tags : Trie
 */
public class LongestDomain {

	public static void main(String[] args) {
		LongestDomain ld = new LongestDomain();
		String res = ld.findLongest();
		System.out.println(res);
	}

	String[] arr = { ".com", ".cn", ".service.com", ".net", ".youku.net" };
	String url = "yeah.hello.youku.net";
	TrieNode root = new TrieNode();

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
