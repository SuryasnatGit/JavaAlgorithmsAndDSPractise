package com.algo.ds.array;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into 
 * k non-empty subsets whose sums are all equal.

Example 1:
Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.


 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DivideNumbersInEqualGroupWithClosestSum {

	/**
	 * Approach 1 - Simple solution using recursion.
	 * 
	 * Time Complexity: O(k^{N-k} k!)O(k N−k k!), where NN is the length of nums, and kk is as given. As
	 * we skip additional zeroes in groups, naively we will make O(k!)O(k!) calls to search, then an
	 * additional O(k^{N-k})O(k N−k ) calls after every element of groups is nonzero.
	 * 
	 * Space Complexity: O(N)O(N), the space used by recursive calls to search in our call stack.
	 * 
	 * 
	 * 
	 * @param num
	 * @param k
	 * @return
	 */
	public boolean canPartitionKSubSets(int[] num, int k) {
		if (num == null || num.length == 0 || k == 0)
			return false;
		Arrays.sort(num); // O(n log n)
		int sum = 0;
		for (int n : num) { // O(n)
			sum += n;
		}
		if (sum % k != 0 || sum < k)
			return false;
		sum /= k;
		return possible(num, sum, new int[k], num.length - 1);
	}

	private boolean possible(int[] nums, int sum, int[] p, int idx) {
		if (idx == -1) {// base condition for exit
			for (int s : p)
				if (s != sum)
					return false;
			return true;
		}

		int num = nums[idx];

		for (int i = 0; i < p.length; i++) {
			if (p[i] + num <= sum) {
				p[i] += num;
				if (possible(nums, sum, p, idx - 1))
					return true;
				p[i] -= num;
			}
		}
		return false;
	}

	/**
	 * Approach 2 - Using memoization
	 * 
	 * @author surya
	 *
	 */
	static class Context {
		int mask;
		int avg;
		int count;
		int r;

		public Context(int mask, int avg, int count, int remainder) {
			this.mask = mask;
			this.avg = avg;
			this.count = count;
			this.r = remainder;
		}

		@Override
		public int hashCode() {
			return mask ^ avg ^ count ^ r;
		}

		@Override
		public boolean equals(Object that) {
			if (that == null)
				return false;
			else if (!(that instanceof Context))
				return false;
			else if (that == this)
				return true;
			else {
				Context newThat = (Context) that;

				return this.mask == newThat.mask && this.avg == newThat.avg && this.count == newThat.count
						&& this.r == newThat.r;
			}
		}
	}

	public boolean canPartitionKSubsets(int[] nums, int k) {
		int total = 0;
		for (int n : nums)
			total += n;
		if (total % k != 0)
			return false;

		int avg = total / k;
		int mask = 0;
		for (int i = 0; i < nums.length; i++) {
			mask |= 1 << i;
		}

		Map<Context, Boolean> cache = new HashMap<Context, Boolean>();

		Context initialContext = new Context(mask, avg, k, 0);

		return check(nums, initialContext, cache);
	}

	private boolean check(int[] nums, Context context, Map<Context, Boolean> cache) {
		if (cache.containsKey(context))
			return cache.get(context);

		if (context.r == 0) {
			if (context.count == 0) {
				cache.put(context, true);
				return true;
			} else {
				Context newContext = new Context(context.mask, context.avg, context.count - 1, context.avg);
				cache.put(context, check(nums, newContext, cache));
				return cache.get(context);
			}
		}

		for (int i = 0; i < nums.length; i++) {
			int shift = 1 << i;

			if ((context.mask & shift) != 0) {
				int num = nums[i];

				if (num > context.r)
					continue;

				int newMask = context.mask ^ shift;

				Context newContext = new Context(newMask, context.avg, context.count, context.r - num);
				boolean result = check(nums, newContext, cache);
				if (result) {
					cache.put(context, true);
					return true;
				}
			}
		}

		cache.put(context, false);
		return false;
	}

	/**
	 * Approach 3 - DFS solution. The idea is allocate number into bucket. Start from large number with
	 * reduce DFS calls. Target is sum of each group, equals sum / k.
	 * 
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public boolean canPartitionKSubsets_dfs(int[] nums, int k) {
		if (nums == null || nums.length == 0)
			return false;
		int sum = 0;
		for (int num : nums)
			sum += num;
		if (sum % k != 0)
			return false;
		Arrays.sort(nums);
		int[] bucket = new int[k];
		return dfs(nums, bucket, nums.length - 1, sum / k);
	}

	public boolean dfs(int[] nums, int[] bucket, int index, int target) {
		if (index == -1)
			return true;
		for (int i = 0; i < bucket.length; i++) {
			if (bucket[i] + nums[index] <= target) {
				bucket[i] += nums[index];
				if (dfs(nums, bucket, index - 1, target))
					return true;
				bucket[i] -= nums[index];
			}
		}
		return false;
	}

	/**
	 * Approach 4 - Java dfs + backtracking + pruning
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public boolean canPartitionKSubsets_dfs1(int[] nums, int k) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if (sum % k != 0) {
			return false;
		}
		avg = sum / k;
		return markK(nums, avg, 0, k, 0);
	}

	int avg;

	boolean markK(int[] nums, int rem, int start, int depth, int nxt) {
		if (depth == 1) {
			return true;
		}
		if (start == nxt) {
			// pruning step: If the nums[start] doesn't form a valid subset, return false without try start + 1
			// to nums.length
			while (start < nums.length && nums[start] == 0)
				start++;
			rem -= nums[start];
			nxt = start + 1;
		}
		if (rem == 0) {
			return markK(nums, avg, start + 1, depth - 1, start + 1);
		}
		if (rem < 0) {
			return false;
		}
		for (int i = nxt; i < nums.length; i++) {
			int val = nums[i];
			if (val == 0 || rem < val) {
				continue;
			}
			nums[i] = 0;
			if (markK(nums, rem - val, start, depth, i + 1)) {
				return true;
			}
			nums[i] = val;
		}
		return false;
	}

	public static void main(String args[]) {
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		int arr[] = { 1, 3, 5, 7, 4 };
		int arr1[] = { 23, 45, 34, 12, 11, 98, 99, 4, 189, 1, 7, 19, 105, 201 };

		DivideNumbersInEqualGroupWithClosestSum dn = new DivideNumbersInEqualGroupWithClosestSum();
		System.out.println(dn.canPartitionKSubSets(arr, 2));
		System.out.println(dn.canPartitionKSubSets(arr, 4));
		// dn.divide(arr, list1, list2);
		// System.out.println(list1);
		// System.out.println(list2);
		//
		// list1.clear();
		// list2.clear();
		// dn.divide(arr1, list1, list2);
		// System.out.println(list1);
		// System.out.println(list2);

	}
}
