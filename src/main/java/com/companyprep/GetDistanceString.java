package com.companyprep;

/**
 * given n pairs of numbers (1122...nn) and arrange them so that the each number x is x spaces apart from another number
 * x.
 * 
 * Category : Hard
 * 
 * Tags : DP, DFS
 * 
 * TODO : check later
 *
 */
public class GetDistanceString {

	private String result;

	public String getDistanceString(int n) {
		int[] visited = new int[n + 1];
		dfs(n, visited, 0, "");
		return result;
	}

	private void dfs(int n, int[] visited, int count, String now) {
		if (count == 2 * n) {
			result = now;
			return;
		}

		for (int i = 1; i <= n; i++) {
			// The first occurence, add to anywhere
			if (visited[i] == 0) {
				visited[i]++;
				dfs(n, visited, count + 1, now + i);
				visited[i]--;
			} else if (visited[i] == 1) {
				if (now.length() - i >= 0 && now.charAt(now.length() - i) == '0' + i) {
					visited[i] = 2;
					dfs(n, visited, count + 1, now + i);
					visited[i]--;
				}
			} // If it is already used twice, just ignore
		}
	}

	public static void main(String[] args) {
		GetDistanceString gds = new GetDistanceString();
		System.out.println(gds.getDistanceString(0));
		System.out.println(gds.getDistanceString(1));
		System.out.println(gds.getDistanceString(2)); // doesn't work
		System.out.println(gds.getDistanceString(3)); // doesn't work
		System.out.println(gds.getDistanceString(4)); // works
		System.out.println(gds.getDistanceString(5)); // works
	}

}
