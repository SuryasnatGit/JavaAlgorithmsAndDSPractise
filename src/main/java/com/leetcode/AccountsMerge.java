package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
 * Category : Hard
 * 
 * Tags : Union-Find, DFS
 * 
 */
public class AccountsMerge {

	/**
	 * T - O(A log A), S - O(A)
	 * 
	 * @param accounts
	 * @return
	 */
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		UnionFind dsu = new UnionFind();

		Map<String, String> emailToName = new HashMap<>();
		Map<String, Integer> emailToID = new HashMap<>();

		int id = 0;
		for (List<String> account : accounts) {
			String name = "";
			for (String email : account) {
				// first string is name
				if (name == "") {
					name = email;
					continue;
				}
				emailToName.put(email, name);
				if (!emailToID.containsKey(email)) {
					emailToID.put(email, id++);
				}

				// joining y email to x email subset
				dsu.union(emailToID.get(account.get(1)), emailToID.get(email));
			}
		}

		Map<Integer, List<String>> ans = new HashMap<>();
		for (String email : emailToName.keySet()) {
			int index = dsu.find(emailToID.get(email));
			ans.computeIfAbsent(index, x -> new ArrayList<>()).add(email);
		}

		for (List<String> component : ans.values()) {
			Collections.sort(component);
			component.add(0, emailToName.get(component.get(0)));
		}

		return new ArrayList<>(ans.values());
	}

	public static void main(String[] args) {
		AccountsMerge am = new AccountsMerge();
		List<List<String>> accounts = new ArrayList<>();
		accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
		accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
		accounts.add(Arrays.asList("Mary", "mary@mail.com"));
		accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));

		System.out.println(am.accountsMerge(accounts));
	}
}

class UnionFind {

	// subset array
	int[] parent;

	public UnionFind() {
		parent = new int[10001];
		for (int i = 0; i <= 10000; ++i) {
			parent[i] = i;
		}
	}

	// determine which subset a particular element is in
	public int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}

		return parent[x];
	}

	// join 2 subsets into a single set
	public void union(int x, int y) {
		parent[find(x)] = find(y);
	}

}
