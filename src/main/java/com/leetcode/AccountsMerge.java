package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a
 * name, and the rest of the elements are emails representing emails of the account.
 * 
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email
 * that is common to both accounts. Note that even if two accounts have the same name, they may belong to different
 * people as people could have the same name. A person can have any number of accounts initially, but all of their
 * accounts definitely have the same name.
 * 
 * After merging the accounts, return the accounts in the following format: the first element of each account is the
 * name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 * 
 * Example 1: Input: accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"],
 * ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]] Output: [["John",
 * 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'], ["John", "johnnybravo@mail.com"], ["Mary",
 * "mary@mail.com"]] Explanation: The first and third John's are the same person as they have the common email
 * "johnsmith@mail.com". The second John and Mary are different people as none of their email addresses are used by
 * other accounts. We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John',
 * 'johnnybravo@mail.com'], ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be
 * accepted.
 * 
 * Intuition : Draw an edge between two emails if they occur in the same account. The problem comes down to finding
 * connected components of this graph.
 * 
 * Category : Medium
 * 
 * Tags : Union-Find, DFS
 * 
 * TODO : Revisit
 */
public class AccountsMerge {

	/**
	 * T - O(A log A), S - O(A)
	 * 
	 * @param accounts
	 * @return
	 */
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		// email, indexes
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

		for (int i = 0; i < accounts.size(); i++) {
			for (String s : accounts.get(i)) {
				if (!map.containsKey(s)) {
					map.put(s, new ArrayList<Integer>());
				}
				map.get(s).add(i);
			}
		}

		UnionFind uf = new UnionFind(accounts.size());

		for (List<Integer> group : map.values()) {
			for (int i = 1; i < group.size(); i++) {
				uf.union(group.get(i), group.get(i - 1));
			}
		}

		return uf.findGroup();
	}
}

class UnionFind {

	private int[] parent = null;

	public UnionFind(int size) {
		parent = new int[size];

		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
	}

	public void union(int i, int j) {
		int p1 = find(i);
		int p2 = find(j);

		if (p1 != p2) {
			parent[p1] = p2;
		}
	}

	public int find(int i) {
		while (parent[i] != i) {
			parent[i] = parent[parent[i]];
			i = parent[i];
		}
		return i;
	}

	public List<List<String>> findGroup() {
		List<List<String>> res = new ArrayList<>();
		List<Integer>[] groups = new List[parent.length];

		for (int i = 0; i < parent.length; i++) {
			int pos = find(i);
			if (groups[pos] == null) {
				groups[pos] = new HashSet<Integer>();
			}

			groups[pos].add(i);
		}

		for (List<Integer> set : groups) {
			if (set != null) {
				res.add(set);
			}
		}

		return res;
	}
}
