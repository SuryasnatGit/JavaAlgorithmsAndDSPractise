package com.algo.ds.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Count number of smaller elements on right side of an array for every element.
 * 
 * You are given an integer array nums and you have to return a new counts array. The counts array
 * has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * 
 * // Example: // // Given nums = [5, 2, 6, 1] // // To the right of 5 there are 2 smaller elements
 * (2 and 1). // To the right of 2 there is only 1 smaller element (1). // To the right of 6 there
 * is 1 smaller element (1). // To the right of 1 there is 0 smaller element. // Return the array
 * [2, 1, 1, 0].
 *
 *
 * 
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public class CountSmallerOnRight {

	/**
	 * Approach 1 - Using 2 loops. time complexity - O(n^2).
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> countSmaller_simple(int[] nums) {
		int len = nums.length;
		int[] count = new int[len]; // new array to hold the counts
		for (int i = 0; i < len; i++) {
			int c = 0;
			for (int j = i + 1; j < len; j++) {
				if (nums[j] < nums[i])
					c++;
			}
			count[i] = c;
		}
		return Arrays.stream(count).boxed().collect(Collectors.toList());
	}

	/**
	 * Approach 2 - using binary search
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> countSmaller_binarySearch(int[] nums) {
		Integer[] ans = new Integer[nums.length];
		List<Integer> sorted = new ArrayList<Integer>();
		for (int i = nums.length - 1; i >= 0; i--) {
			int index = findIndex(sorted, nums[i]);
			ans[i] = index;
			sorted.add(index, nums[i]);
		}
		return Arrays.asList(ans);
	}

	private int findIndex(List<Integer> sorted, int target) {
		if (sorted.size() == 0)
			return 0;
		int start = 0;
		int end = sorted.size() - 1;
		if (sorted.get(end) < target)
			return end + 1;
		if (sorted.get(start) >= target)
			return 0;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (sorted.get(mid) < target) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		if (sorted.get(start) >= target)
			return start;
		return end;
	}

    static class NumberIndex {
        int val;
        int index;
        NumberIndex(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

	/**
	 * Approach 3 - Using merge sort with top down approach
	 * 
	 * Time complexity is O(n log n).<br/>
	 * Space complexity is O(n)
	 * 
	 * @param nums
	 * @return
	 */
    public List<Integer> countSmaller_mergeSort_topDown(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        NumberIndex[] input = new NumberIndex[nums.length];
        for (int i = 0; i < nums.length; i++) {
            input[i] = new NumberIndex(nums[i], i);
        }

        int result[] = new int[nums.length];

        mergeUtil(input, result, 0, input.length - 1);

		return Arrays.stream(result).boxed().collect(Collectors.toList());
	}

    private void mergeUtil(NumberIndex[] nums, int[] result, int low, int high) {
        if (low == high) {
            return;
        }
        int mid = (low + high)/2;
        mergeUtil(nums, result, low, mid);
        mergeUtil(nums, result, mid + 1, high);

        int i = low;
        int j = mid + 1;
        NumberIndex[] t = new NumberIndex[high - low + 1];
        int k = 0;
        int tempResult[] = new int[high - low + 1];
        while (i <= mid && j <= high) {
            if (nums[i].val <= nums[j].val) {
                tempResult[nums[i].index - low] = j - mid - 1;
                t[k++] = nums[i++];
            } else {
                tempResult[nums[i].index - low] = j - mid;
                t[k++] = nums[j++];
            }
        }
        int i1= i;
        while (i1 <= mid) {
            tempResult[nums[i1].index - low] = j - mid - 1;
            t[k++] = nums[i1++];
        }

        while (j <= high) {
            t[k++] = nums[j++];
        }

        k = 0;
        for (i = low; i <= high; i++) {
            nums[i] = t[k];
            result[i] += tempResult[k++];
        }
    }

	private void merge(int[] nums, int[] indices, int[] aux, int[] count, int start, int end) {
		if (start >= end)
			return;
		int mid = start + (end - start) / 2;
		merge(nums, indices, aux, count, start, mid);
		merge(nums, indices, aux, count, mid + 1, end);
		for (int i = start, j = mid + 1, k = start; k <= end; k++) {
			if (i == mid + 1)
				aux[k] = indices[j++];
			else if (j == end + 1) {
				aux[k] = indices[i];
				count[indices[i++]] += j - mid - 1;
			} else if (nums[indices[i]] <= nums[indices[j]]) {
				aux[k] = indices[i];
				count[indices[i++]] += j - mid - 1;
			} else {
				aux[k] = indices[j++];
			}
		}
		for (int i = start; i <= end; i++) {
			indices[i] = aux[i];
		}
	}

	/**
	 * Approach 5
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> countSmaller_mergeSort_topDown_anotherWay(int[] nums) {
		int n = nums.length;
		int[] aux = new int[n], indices = new int[n], count = new int[n];
		for (int i = 0; i < n; i++) {
			indices[i] = i;
		}
		merge(nums, indices, aux, count, 0, nums.length - 1);
		LinkedList<Integer> result = new LinkedList<>();
		for (int c : count)
			result.add(c);
		return result;
	}

	private void merge(int[] nums, int[] indices, int[] aux, int[] count, int start, int end, int step) {
		for (int i = start, j = start + step, k = start; k <= end; k++) {
			if (i == start + step)
				aux[k++] = indices[j++];
			else if (j == end + 1) {
				aux[k++] = indices[i];
				count[indices[i++]] += j - start - step;
			} else if (nums[indices[i]] <= nums[indices[j]]) {
				aux[k++] = indices[i];
				count[indices[i++]] += j - start - step;
			} else {
				aux[k++] = indices[j++];
			}
		}
		for (int i = start; i <= end; i++) {
			indices[i] = aux[i];
		}
	}

	/**
	 * Approach 6
	 * 
	 * @param nums
	 * @return
	 */
	public List<Integer> countSmaller_mergeSort_bottomUp(int[] nums) {
		int n = nums.length;
		int[] aux = new int[n], indices = new int[n], count = new int[n];
		for (int i = 0; i < n; i++)
			indices[i] = i;
		for (int i = 1; i < n; i *= 2) {
			for (int j = 0; j < n; j += 2 * i) {
				if (j + i >= nums.length)
					continue;
				merge(nums, indices, aux, count, j, Math.min(j + i * 2 - 1, nums.length - 1), i);
			}
		}
		LinkedList<Integer> result = new LinkedList<>();
		for (int c : count) {
			result.add(c);
		}
		return result;
	}

	/**
	 * Approach 4 - Using Binary Search Tree
	 * 
	 * @author surya
	 *
	 */
	private class TreeNode {
		int val, leftchildren;
		TreeNode left, right;

		public TreeNode(int v) {
			val = v;
			leftchildren = 0;
		}
	}

	private int insert(TreeNode root, int val) {
		int count = 0;
		while (true) {
			if (val <= root.val) {
				root.leftchildren++;
				if (root.left != null)
					root = root.left;
				else {
					root.left = new TreeNode(val);
					break;
				}
			} else {
				count += root.leftchildren + 1;
				if (root.right != null)
					root = root.right;
				else {
					root.right = new TreeNode(val);
					break;
				}
			}
		}
		return count;
	}

	public List<Integer> countSmaller_usingBinarySearchTree(int[] nums) {
		LinkedList<Integer> result = new LinkedList<>();
		if (nums.length == 0)
			return result;
		result.add(0);
		TreeNode root = new TreeNode(nums[nums.length - 1]);
		for (int i = nums.length - 2; i >= 0; i--) {
			result.addFirst(insert(root, nums[i]));
		}
		return result;
	}

    public static void main(String args[]) {
        CountSmallerOnRight csr = new CountSmallerOnRight();
        int nums[] = {5, 2, 6, 1, 0, 3};
        List<Integer> result = csr.countSmaller_mergeSort_topDown(nums);
		// result.forEach(r -> System.out.print(r + " "));
		System.out.println(csr.countSmaller_simple(nums));
		System.out.println(csr.countSmaller_binarySearch(nums));
		System.out.println(csr.countSmaller_mergeSort_topDown_anotherWay(nums));
    }
}
