package com.companyprep.amazon;

/**
 * given n pairs of numbers (1122...nn) and arrange them so that the each number x is x spaces apart from another number
 * x.
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
			if (visited[i] == 0) // The first occurance, add to anywhere
			{
				visited[i]++;
				dfs(n, visited, count + 1, now + i);
				visited[i]--;
			} else if (visited[i] == 1) { // now.length() will be current character's potential position, must be
											// exactly i space apart
				if (now.length() - i >= 0 && now.charAt(now.length() - i) == '0' + i) // i positions ahead of current
																						// position should be i
				{
					visited[i] = 2;
					dfs(n, visited, count + 1, now + i);
					visited[i]--;
				}
			} // If it is already used twice, just ignore
		}
	}

	public static void main(String[] args) {
		GetDistanceString gds = new GetDistanceString();
		System.out.println(gds.getDistanceString(4));
	}

}
